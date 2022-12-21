package algstudent.s3;

import java.util.*;

public class Tromino {
	
	private int[][] grid; //array with dimensions user inputs
	private int currentNumber;
	
	// Pre-condition: size must be a perfect power of 2 and 0<=x<size, 0<=y<size
	// Post-condition: creates an empty tromino object with dimensions size x size.
	
	//constructor
	public Tromino(int size, int x, int y) { 
		
		int actualsize = 1;
		while (actualsize < size) { //elegxei an size dunamh tou 2
			 actualsize*=2;			
		}
		
		// adding dimensions in array
		grid = new int[actualsize][actualsize]; 
		currentNumber = 1; 
		
		// Fills array with empty squares.
		for (int i=0; i<actualsize; i++) {
			for (int j=0; j<actualsize; j++) { 
				grid[i][j] = 0;
			}
		}
		
		// cell position with user inputs x y 
		grid[x][y] = -1; 
	}
	
	// Wrapper call for recursive method.
	public void tile() {
		tileRec(grid.length, 0, 0); //we pass dimensions and
	}
	
	private void tileRec(int size, int topx, int topy) {
		
		// No recursive case needed here, bc we need to fill once. just fill in one tromino...
		if (size == 2) {
		
			// Fill in the one necessary tromino. The hole is identified by a
			// non-zero number, so don't fill in that one square.	
			for (int i=0; i<size; i++)   
				for (int j=0; j<size; j++)
					if (grid[topx+i][topy+j] == 0)
						grid[topx+i][topy+j] = currentNumber;
		
			
			currentNumber++;
		}
		
		// Recursive case...
		else {
			
			// Find coordinates of missing hole
			int savex=topx;
			int savey=topy;
			System.out.println("Enter Tromino grid dimensions?" + savex);

			for (int x=topx; x<topx+size; x++) 
				for (int y=topy; y<topy+size; y++)
					if (grid[x][y] != 0) {
						savex = x;
						savey = y;
					}
				
			// Hole in upper left quadrant.		
			if (savex < topx + size/2 && savey < topy + size/2) {
				
				// Recursively tile upper left quadrant.
				tileRec(size/2, topx, topy);
				
				// Fill in middle tromino
				grid[topx+size/2][topy+size/2-1] = currentNumber;
				grid[topx+size/2][topy+size/2] = currentNumber;
				grid[topx+size/2-1][topy+size/2] = currentNumber;
				
				// Advance to the next tromino
				currentNumber++;
				
				// Now we can make our three other recursive calls.
				tileRec(size/2, topx, topy+size/2);
				tileRec(size/2, topx+size/2, topy);
				tileRec(size/2, topx+size/2, topy+size/2);
				
			}
			
			// Hole in upper right quadrant
			else if (savex < topx + size/2 && savey >= topy + size/2) {
				
				// Recursively tile upper right quadrant.
				tileRec(size/2, topx, topy+size/2);
				
				// Fill in middle tromino
				grid[topx+size/2][topy+size/2-1] = currentNumber;
				grid[topx+size/2][topy+size/2] = currentNumber;
				grid[topx+size/2-1][topy+size/2-1] = currentNumber;
				
				// Advance to the next tromino
				currentNumber++;
				
				// Now we can make our three other recursive calls.
				tileRec(size/2, topx, topy);
				tileRec(size/2, topx+size/2, topy);
				tileRec(size/2, topx+size/2, topy+size/2);
				
			}
			
			// Hole in bottom left quadrant
			else if (savex >= topx + size/2 && savey < topy + size/2) {
				
				// Recursively tile bottom left quadrant.
				tileRec(size/2, topx+size/2, topy);
				
				// Fill in middle tromino
				grid[topx+size/2-1][topy+size/2] = currentNumber;
				grid[topx+size/2][topy+size/2] = currentNumber;
				grid[topx+size/2-1][topy+size/2-1] = currentNumber;
				
				// Advance to the next tromino
				currentNumber++;
				
				// Now we can make our three other recursive calls.
				tileRec(size/2, topx, topy);
				tileRec(size/2, topx, topy+size/2);
				tileRec(size/2, topx+size/2, topy+size/2);
			}
			else {
				
				// Recursively tile bottom right quadrant.
				tileRec(size/2, topx+size/2, topy+size/2);
				
				// Fill in middle tromino
				grid[topx+size/2-1][topy+size/2] = currentNumber;
				grid[topx+size/2][topy+size/2-1] = currentNumber;
				grid[topx+size/2-1][topy+size/2-1] = currentNumber;
				
				// Advance to the next tromino
				currentNumber++;
				
				// Now we can make our three other recursive calls.
				tileRec(size/2, topx+size/2, topy);
				tileRec(size/2, topx, topy+size/2);
				tileRec(size/2, topx, topy);
			}
			
		} // end large if-else
		
	} // end tileRec
	
	
	// Prints out the current object.
	public void print() {
		
		for (int i=0; i<grid.length; i++) {
			for (int j=0; j<grid[i].length; j++)
				System.out.print(grid[i][j] + "\t");
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		//user input of tromino dimensions
		System.out.println("Enter Tromino grid dimensions?");
		System.out.println("Please enter a perfect power of 2.");
		int size = sc.nextInt(); //size saves the dimensions
		
		System.out.println("Add empty cell position?");
		System.out.println("Answer with an x and y separated by spaces.");
		int x = sc.nextInt(); //x cell position
		int y = sc.nextInt();//y cell position
		
		// object
		Tromino tr = new Tromino(size, x, y);  //adds size into array, fills array with zeros and then adds cell position based on user input
		tr.tile();
		
		// Print out the trominoed grid.
		System.out.println("Result:\n");
		tr.print();
		
	}
}