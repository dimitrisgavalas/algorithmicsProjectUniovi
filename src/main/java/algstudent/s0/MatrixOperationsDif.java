package algstudent.s0;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//import java.util.Random;
/*
 * @author Dimitrios Gavalas
 * 
 * */
public class MatrixOperationsDif {
	 private int[][] arr;
	 public static void main(String[] args){
		 
		 MatrixOperationsDif m = new MatrixOperationsDif(10);//constructor called where n=size passes 
		 m.write();	//print array with length 10 and random numbers between min-max initialized in constructor
		 System.out.println("Matrix size is : " + m.getSize());
		 
		 
		 //sumDiagonal1 will calculate the sum of the main diagonal
		 System.out.println("(Sumdiagonal1)Complexity is quadratic : " + m.sumDiagonal1());
		 System.out.println("(Sumdiagonal2)Complexity is linear : " + m.sumDiagonal2());

		 System.out.println("\n");

		 m.travelPath(3, 0);//prints travel path that will start from 3,0		 
		 //("src/algstudent/s0/matrix01.txt");//creates array from the file we added	
		 m.write();
		 m.travelPath(5, 0);
	 }
	 
	 
	 /**
	 * Create an array of size nxn and fill it with random values
	 * @param n size of the square matrix
	 */
	 public MatrixOperationsDif(int n) {
		 arr = new int[n][n];
		 fillArray(1, 4); //calls constructor with size n=10, min=1, max=4
	 }
	 
	 
	//This function loads the values of the array of integers from a file//
		private int[][] carry(String fileName)
		{
			BufferedReader file = null;
			String line;
			int n;
			int[][] elements = null;

			try {
				// Opening the matrix file
				file = new BufferedReader(new FileReader(fileName));
				line = file.readLine();
				// the first line contains the numbers of elements
				n = Integer.parseInt(line);

				// making the array memory
				elements = new int[n][n];
				for (int i = 0; i<n; i++) {
					line = file.readLine();
					String values[] = line.split("\t");
					for (int j = 0; j<n; j++)
						elements[i][j]= Integer.parseInt(values[j]);
				}

			} catch (FileNotFoundException e) {
				System.out.println("I can't find the file: "+fileName);
			} catch (IOException e) {
				System.out.println("Error reading the file: "+fileName);
			}

			return elements;
		}
	 

	 /** Creates arr new matrix of size
	  *  n x n and fills it with random values.
	  *  These random values are between arr maximum
	  *  (max) and arr minimum (min).
	  *  **/
	 public void fillArray(int min, int max){
	        //Random rand = new Random();
	        //int[][] multi = new int[n][n]; //declared nxn array
		 
		 	int n = arr.length;
	        //will add random numbers between min and max in arrya arr. 
	        for (int row = 0; row < n; row++) {
	            for (int col = 0; col < arr[row].length; col++) {
	                arr[row][col] = (int) Math.floor(Math.random()*(max-min+1)+min);
	            }
	        }	        
	 }	
	
	
//	/**
//	* Create an array from the data in the file ex=matrix01.txt
//	* @param filename name of the file in the working directory
//	*/
//	public  MatrixOperationsDif(String fileName) {
//		
//			System.out.println("\nPrinting : " + fileName );
//			
//			//we create an object 
//			//with the file dir we are intrested in order to handle files
//			File f = new File(fileName); BufferedReader file= null;
//			
//			String line;
//			int n;
//			int[][] elements= null;
//
//			try {
//			// Open the text file
//			file = new BufferedReader(new FileReader(fileName));
//			line = file.readLine();
//			// The first line contains the number of elements
//			n = Integer.parseInt(line);
//
//			// Create the array of the right size
//			elements = new int[n][n];
//			for (int i = 0; i<n; i++) {
//				line = file.readLine();
//				String values[] = line.split("\t");
//				
//				for (int j = 0; j<n; j++)
//					elements[i][j] = Integer.parseInt(values[j]);
//			}
//
//			} catch (FileNotFoundException e) {
//				System.out.println("File not found: " + fileName);
//			} catch(IOException e) {
//				System.out.println("Error reading the file: " + fileName);
//			}
//			
//			arr = elements;
////			//decided to print file matrix inside the method 
////			for(int i = 0; i < elements.length; i++) {
////	            for(int j = 0; j < elements[i].length; j++) {
////	                System.out.print(elements[i][j] + "\t");
////	            }
////	            System.out.println();
////	        }
////	        
////			 System.out.println("\n\n");
//					
//	}
 
	 /* This method prints the array
	 */
	 public void write(){
		 	int n = arr.length;
	        for(int i = 0; i < n; i++) {
	            for(int j = 0; j < arr[i].length; j++) {
	                System.out.print(arr[i][j] + "\t");
	            }
	            System.out.println();
	        }
	        
			 System.out.println("\n\n");
	 }
	 
	 /*Returns the size of the matrix */
	 public int getSize(){
	        return arr.length;
	 }
	 
	//quadratic sum of diagonal
	 public int sumDiagonal1()
	{
			int n = arr.length;
			int sum = 0;
			for(int i=0; i<n; i++)
				for(int j=0; j<n; j++) {
					if (i==j) {
						sum = sum + arr[i][j];
					}
				}
			return sum; 
	}

	 //linear sum of diagonal
	 public int sumDiagonal2()
		{
			int n = arr.length;
			int sum = 0;
			for(int i=0; i<n; i++)
				sum = sum + arr[i][i];
			return sum; 
		} 

	 
	// Create an array from the data in the file//
		 public MatrixOperationsDif(String fileName) {
				arr = carry(fileName);//			
		 }
	
	 /**
	  * when 1 – move up; 2 – move right;
	  *  3 – move down; 4 – move left.
	  *  Traversed elements would be set to -1 value. 
	  *  The process will finish if it goes beyond the
	  *  limits of the matrix or an already traversed position is reached.
	  * **/
	 public void travelPath(int i, int j){
		 
		 	boolean travelPathEnd = false;//when it changes to true it has gone beyond limit or already traversed
		 	System.out.print("travelPath Calculting path...\n");
			int n = arr.length;

			while (!travelPathEnd) {
				int val = arr[i][j];
				arr[i][j] = -1;
				write();
				switch(val) {
				case 1: i = i-1;
				break;
				case 2: j = j+1;
				break;
				case 3: i = i+1;
				break;
				case 4: j = j-1;
				break;
				case -1: travelPathEnd= true;
				break;
				}
				if (i<0 || i>=n || j<0 || j>=n)
					travelPathEnd= true;
			}
			//write();
	    }

}

