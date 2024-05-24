import java.util.Arrays;

import javax.swing.text.html.parser.Element;

public class ArrayIntList {
	//fields
	public static final int DEFAULT_CAPACITY = 100;

	private int[] elementData;	//list of integers
	private int size;			// numbers of elements in the list

	

	//constructors

	//post: constructs an empty list of default capacity
	public ArrayIntList() {
		/*using this makes it easier as it defaults 
		 *the capacity to 100 without typing those other lines
		 *while reducing redundancy by only having to call one constructor */

		this(DEFAULT_CAPACITY);
	}

	//pre: capacity >= 0
	//post: constructs an empty list of given capacity
	public ArrayIntList(int capacity) {
		elementData = new int[capacity];
		size = 0;
	}
	


	//methods

	//pre: size() < capacity
	//post: appends the given value to the end of the list
	public void add(int value) {
		elementData[size] = value;
		size++;
	}

	//pre: size() < capacity && 0 <= index <= size()
	public void add(int value, int index) {
		ensureCapacity(size + 1);
		for (int i = size; i >= index + 1; i--) {
			elementData[i] = elementData[i-1];
		}
		elementData[index] = value;
		size++;
	}

	public void set (int index, int value) {
		checkIndex(index);
		elementData[index] = value;
	}

	public void remove(int index) {
		for (int i = index; i < size - 1; i++) {
			elementData[i] = elementData[i + 1];
		}
		size--;
	}

	//pre: 0 <= index < size()
	//post: returns the integer at the given index in the list
	public int get (int index) {
		return elementData[index];
	}

	public int lastIndexOf(int target) {
		int result = -1;
		for (int i = 0; i < size; i ++) {
			if (elementData[i] == target) {
				result = i;
			}
		}
		return result;
	}

	public boolean contains(int value) {
		return lastIndexOf(value) >= 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public String toString() {
		if (size == 0 ) {
			return "[]";

		} else {
			String result = "[" + elementData[0];
			for (int i = 1; i < size; i++) {
				result += ", " + elementData[i];
			}
			result += "]";
			return result;
		}
	}

	//post: adjusts the size of ArrayIntList if necessary by either doubling it or changing it to a desired capacity
	public void ensureCapacity(int capacity) {
		if (capacity > elementData.length) { 
			int newCapacity = elementData.length * 2;
			if (capacity > newCapacity) {
				newCapacity = capacity;
			}
			elementData = Arrays.copyOf(elementData, newCapacity);
		}
	}

	private void checkIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("index: " + index);		
		}
	}	



	public void replaceAll(int target, int replacement) {
		for (int i = 0; i < size; i++) {
			if (elementData[i] == target) {
				elementData[i] = replacement;
			}
		} 
	}
}
