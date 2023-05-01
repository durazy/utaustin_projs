import java.awt.Graphics;
import java.awt.Color;
import java.io.IOException;

/**
 * CS312 Assignment 3.
 * 
 * On my honor, Daniel Duru, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 * A program to print out various scintillation grids and a student designed drawing. 
 */

public class ScintillationGrid {

    // Main method that creates the DrawingPanel with scintillation grids.
    // Restricted to chapters 1 - 3 of Building Java Programs
    public static void main(String[] args) {
        /* In the final version of the program DO NOT call method drawingOne 
           from main or anywhere else in the program */
        final int WIDTH = 950;
        final int HEIGHT = 650;
        DrawingPanel dp = new DrawingPanel(WIDTH, HEIGHT);
        Graphics g = dp.getGraphics();		
		//sets background to cyan
		dp.setBackground(Color.CYAN);	
        
        // CS312 Students add you four methods calls to draw the four
        // required scintillation grids here.
        drawScan(g, 0, 0, 348, 3, 16);
		drawScan(g, 400, 50, 422, 6, 12);
		drawScan(g, 50, 400, 220, 1, 20);
		drawScan(g, 500, 500, 148, 7, 4);
        
        // CS312 students, do not alter the following line of code.
        saveDrawingPanel(dp, "grid.png");
    }
    
    //method for scintillation
    public static void drawScan(Graphics g, int x, int y, int size, 
			final int LINES, final int LINE_THICKNESS) {
		final int X1 = x;
		final int Y1 = y;
		final int SQUARE_SIZE = ((size - (LINE_THICKNESS * LINES)) / (LINES + 1));
		final int JUMP = SQUARE_SIZE + LINE_THICKNESS;
		final int EDGE_RIGHT = X1 + size - 1;
		final int EDGE_DOWN = Y1 + size - 1;
		final int OVAL_DIFFERENCE = Math.max(LINE_THICKNESS * 4 / 10, 4);
		final int OVAL_SIZE = LINE_THICKNESS + OVAL_DIFFERENCE;

		//draws black rectangle
		g.setColor(Color.BLACK);
		g.fillRect(X1, Y1, size, size);
		//draws gray lines
		g.setColor(Color.GRAY);
		//horizontal lines
		for (int i = 1; i <= LINES; i++) {
			int xl = X1;
			int yl = Y1 + i * JUMP;
			for (int j = LINE_THICKNESS; j > 1; j--) {
				int yf = yl - j;
				g.drawLine(xl, yf, EDGE_RIGHT, yf);
			}

		}
		//vertical lines
		for (int i = 1; i <= LINES; i++) {
			int yl = Y1;
			int xl = X1 + i * JUMP;
			for (int j = LINE_THICKNESS; j > 1; j--) {
				int xf = xl - j;
				g.drawLine(xf, yl, xf, EDGE_DOWN);
			}
		}
		// white dots
		g.setColor(Color.WHITE);
		for (int i = 1; i <= LINES; i++) {
			int yl = Y1 + i* JUMP - LINE_THICKNESS - OVAL_DIFFERENCE/2; 
			for (int j = 1; j <= LINES; j++) {
				int xl = X1 + j*JUMP - LINE_THICKNESS - OVAL_DIFFERENCE/2;
				g.fillOval(xl, yl, OVAL_SIZE, OVAL_SIZE);
			}
		}
	}


    // method for the student designed drawing
    // NOT restricted to chapters 1 - 3 of Building Java Programs
    // DO NOT ADD ANY PARAMETERS TO THIS METHOD!!!
    public static void drawingOne() {
        // DO NOT ADD ANY PARAMETERS TO THIS METHOD!!!

        // CS312 Students, you may increase the size of the drawing panel if
        // doing a non standard drawing.
        final int WIDTH = 400;
        final int HEIGHT = 200;
        DrawingPanel dp = new DrawingPanel(WIDTH, HEIGHT);

        // CS312 Students, add your code, including method calls here
		final int MID = HEIGHT/2;
		final int HALF_MID = MID/2;
		final int THREE_QT = HALF_MID + MID;
		final int THREE_QT_W = HEIGHT + MID;
		
		Graphics g = dp.getGraphics();
		//sets background to green
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		//draws two red ovals
		g.setColor(Color.red);
		g.fillOval(0, 0, HEIGHT, MID);
		g.fillOval(HEIGHT, MID, HEIGHT, MID);
		//draws oval outlines in black
		g.setColor(Color.BLACK);
		g.drawOval(0, 0, HEIGHT, MID);
		g.drawOval(HEIGHT, MID, HEIGHT, MID);
		// divides panels with black lines
		g.drawLine(HEIGHT, 0, HEIGHT, HEIGHT);
		g.drawLine(0, MID, WIDTH, MID);		
		//divides ovals with black lines
		g.drawLine(0, HALF_MID, HEIGHT, MID/2);
		g.drawLine(MID, 0, MID, MID);
		g.drawLine(HEIGHT, THREE_QT, WIDTH, THREE_QT);
		g.drawLine(THREE_QT_W, MID, THREE_QT_W, HEIGHT);       
        // Do not alter this line of code. It saves the panel to a file for later viewing
        saveDrawingPanel(dp, "drawing_one.png");
    }
    
    
    
    // Save the current drawing panel to the given file. 
    // CS312 Students, do not alter this method.
    public static void saveDrawingPanel(DrawingPanel dp, String fileName) {
        try {
            dp.save(fileName);
        } catch (IOException e) {
            System.out.println("Unable to save DrawingPanel");
        }
    }
}

