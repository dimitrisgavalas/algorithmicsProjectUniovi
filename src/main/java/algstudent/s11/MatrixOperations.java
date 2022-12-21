package algstudent.s11;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

//import java.util.Random;

public class MatrixOperations {
	
	 public static void main(String[] args) throws IOException{
		 
         int[][] a =  newMatrix(5, 4, 56 ); //n = array length. min = arrays smallest possible value. max = arrays largest possible value
         write(a);
         sumDiagonal2(a);
         sumDiagonal1(a);
         System.out.println("\nMatrix size is : " + getSize(a) + "x" + getSize(a) + "\n");
         
         fileMatrix("Lab1/matrix01.txt");         
         
         travelPath(8,6);
         
         if (Desktop.isDesktopSupported()) {
        	    try {
        	        File myFile = new File("C:\\Users\\UO293079\\git\\algorithmicsGavalasDimitriosUO293079\\src\\main\\java\\algstudent\\s11\\session0.pdf");
        	        Desktop.getDesktop().open(myFile);
        	    } catch (IOException ex) {
        	        // no application registered for PDFs
        	    }
        }      

		 
	 }
	 
	 public static int[][] newMatrix(int n, int min, int max){

	        //Random rand = new Random();

	        int[][] multi = new int[n][n]; //declared nxn array

	        //will add random numbers between min and max
	        for (int row = 0; row < multi.length; row++) {
	            for (int col = 0; col < multi[row].length; col++) {
	                multi[row][col] = (int) Math.floor(Math.random()*(max-min+1)+min);
	            }
	        }

	        //display output
//	        write(multi);
//	        sumDiagonal2(multi);
//	        System.out.println("\n\n");

	        return multi;
	 }
	 
	 public static void write(int[][] matrix){

	        for(int i = 0; i < matrix.length; i++) {
	            for(int j = 0; j < matrix[i].length; j++) {
	                System.out.print(matrix[i][j] + "\t");
	            }
	            System.out.println();
	        }
	 }
	 
	 public static int getSize(int[][] matrixDimensions){
	        return matrixDimensions.length;
	 }
	 
	 public static void fileMatrix(String fileName) throws IOException {


	        String content = Files.readString(Path.of(fileName), StandardCharsets.US_ASCII);
	        System.out.println(content);

	        //replace 2+ spaces to one
	        String strArray = content.trim().replaceAll("\\s+", " ");

	        //convert string to string array
	        String newArray[] = strArray.split(" ");

	        System.out.println("String : " + strArray);
	        System.out.println("String array : ");

	        // Iterating over the string
	        System.out.println(newArray[0] + "\n");
	        for (int i = 1; i < newArray.length; i++) {

	            if (i == 11||i==21||i==31||i==41||i==51||i==61||i==71||i==81||i==91){
	                System.out.println("\n");
	            }
	            // Printing the elements of String array
	            System.out.print(newArray[i] + "    ");
	        }
	        
	        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

	   }

	 //linear
	 public static void sumDiagonal1(int mat[][]){

	        System.out.print("Secondary Diagonal: ");
	        int k = mat.length - 1;

	        for (int i = 0; i < mat.length; i++)
	        {
	            System.out.print(mat[i][k--] + ", ");
	        }
	        System.out.println();
	  }

	 //quadratic
	 public static void sumDiagonal2(int mat[][]){

	        System.out.print("\nPrincipal Diagonal: ");

	        for (int i = 0; i < mat.length; i++) {
	            for (int j = 0; j < mat.length; j++) {

	                // Condition for principal diagonal
	                if (i == j) {
	                    System.out.print(mat[i][j] + ", ");
	                }
	            }
	        }
	        System.out.println("\n");
	 }

	 public static void travelPath(int i, int j){

	        int[][] a = newMatrix(10,1,4);
	        System.out.println("New Matrix:\n");
	        write(a); //prints new a matrix
	        System.out.println("\n\n");

	        int count = 0;

	        while ( (i<a.length && i>=0) && (j<a.length && j>=0)){
	            if (a[i][j] == 1) {
	                a[i][j] = -1;
	                i -= 1;
	                count++;
	            }else if (a[i][j] == 2) {
	                a[i][j] = -1;
	                j = j + 1;
	                count++;
	            }else if (a[i][j] == 3) {
	                a[i][j] = - 1;
	                i += 1;
	                count++;
	            } else if (a[i][j] == 4) {
	                a[i][j] = -1;
	                j -= 1;
	                count++;
	            }else{
	                break;
	            }
	            System.out.println("i is : " + i + "    j is : " + j);
	        }

	        System.out.println("The number of moves are : " + count);
	        write(a);
	    }

}

