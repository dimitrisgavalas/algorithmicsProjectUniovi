package algstudent.s0;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

//import java.util.Random;
/*
 * @author Dimitrios Gavalas
 * */
public class MatrixOperations {
	private int[][] a;
	 public static void main(String[] args){
		 
		 MatrixOperations m = new MatrixOperations(10);//constructor called where n passes 
		 m.write();	 
		 System.out.println("Matrix size is : " + m.getSize());
		 System.out.println("(Sumdiagonal1)Complexity is quadratic : " + m.sumDiagonal1());
		 System.out.println("(Sumdiagonal2)Complexity is linear : " + m.sumDiagonal2());

		 System.out.println("\n");

		 m.travelPath(3, 0);		 
		 m.arFile("matrix01.txt");	
		 m.write();
		 m.travelPath(5, 0);
	 }
	 
	 
	 /**
	 * Create an array of size nxn and fill it with random values
	 * @param n size of the square matrix
	 */
	 public MatrixOperations(int n) {
		 a= new int[n][n];
		 fillArray(n, 1, 4);
	 }

	 /**Creates a new matrix of size
	  * n x n and fills it with random values.
	  *  These random values must be parameterizable 
	  *  between a maximum (max) and a minimum (min) value.**/
	 public void fillArray(int n, int min, int max){

	        //Random rand = new Random();

	        //int[][] multi = new int[n][n]; //declared nxn array

	        //will add random numbers between min and max in arrya a. 
	        for (int row = 0; row < a.length; row++) {
	            for (int col = 0; col < a[row].length; col++) {
	                a[row][col] = (int) Math.floor(Math.random()*(max-min+1)+min);
	            }
	        }	        
	 }	
	
	
	/**
	* Create an array from the data in the file
	* @param filename name of the file in the working directory
	*/
	public void arFile(String fileName) {
		
			System.out.println("\nPrinting : " + fileName );

			File f = new File(fileName);

			BufferedReader file= null;
			String line;
			int n;
			int[][] elements= null;

			try {
			// Open the text file
			file= new BufferedReader(new FileReader(fileName));
			line= file.readLine();
			// The first line contains the number of elements
			n= Integer.parseInt(line);

			// Create the array of the right size
			elements= new int[n][n];
			for (int i= 0; i<n; i++) {
				line= file.readLine();
				String values[]= line.split("\t");
				
				for (int j= 0; j<n; j++)
					elements[i][j]= Integer.parseInt(values[j]);
			}

			} catch (FileNotFoundException e) {
				System.out.println("File not found: "+fileName);
			} catch(IOException e) {
				System.out.println("Error reading the file: "+fileName);
			}
			
			a = elements;
//			//decided to print file matrix inside the method 
//			for(int i = 0; i < elements.length; i++) {
//	            for(int j = 0; j < elements[i].length; j++) {
//	                System.out.print(elements[i][j] + "\t");
//	            }
//	            System.out.println();
//	        }
//	        
//			 System.out.println("\n\n");
					
	}
 
	 /* This method prints the array
	 */
	 public void write(){
		 	int n = a.length;
	        for(int i = 0; i < n; i++) {
	            for(int j = 0; j < a[i].length; j++) {
	                System.out.print(a[i][j] + "\t");
	            }
	            System.out.println();
	        }
	        
			 System.out.println("\n\n");
	 }
	 
	 /*Returns the size of the matrix */
	 public int getSize(){
	        return a.length;
	 }
	 
	//quadratic sum of diagonal
	 public int sumDiagonal1()
	{
			int n= a.length;
			int sum= 0;
			for(int i=0; i<n; i++)
				for(int j=0; j<n; j++)
					if (i==j) sum+= a[i][j];
			return sum; 
	}

	 //linear sum of diagonal
	 public int sumDiagonal2()
		{
			int n= a.length;
			int sum= 0;
			for(int i=0; i<n; i++)
				sum+= a[i][i];
			return sum; 
		} 

	
	 /**
	  * when 1 – move up; 2 – move right;
	  *  3 – move down; 4 – move left.
	  *  Traversed elements would be set to -1 value. 
	  *  The process will finish if it goes beyond the
limits of the matrix or an already traversed position is reached.
	  * **/
	 public void travelPath(int i, int j){
		 
         System.out.print("travelPath Calculting path...\n");
		 boolean fin= false;
			int n= a.length;
			//int[][] cam= new int[n][n];

			while (!fin) {
				int val= a[i][j];
				a[i][j]= -1;
				write();				
				switch(val) {
				case 1: i= i-1;
				break;
				case 2: j= j+1;
				break;
				case 3: i= i+1;
				break;
				case 4: j= j-1;
				break;
				case -1: fin= true;
				break;
				}
				if (i<0 || i>=n || j<0 || j>=n)
					fin= true;
			}
			//write();
	    }

}

