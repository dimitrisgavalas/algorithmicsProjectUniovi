package algstudent.s1;

import algstudent.s0.MatrixOperationsDif;


/**
 * @author Dimitrios Gavalas
 *
 * References:
 * pdf lab files
 * https://www.geeksforgeeks.org/java-lang-system-currenttimemillis-method-with-examples/
 * 
 * 
 * We add the number of arguments we want the for to loop
 * and Calculate the time difference before and after the calculation experimental times
 * Also we Make the size of the problem grow for different sizes 
 * 
 */
public class MatrixOperationsTimes {
	public static void main(String[] args) {				
		int nTimes = Integer.parseInt (args[0]);	// times the operation is repeated

		long t1,t2;

		System.out.println("n = "+ nTimes);
		System.out.println ("Size\t\tTime\tSum\n"); 
		for ( int n=3; n<= 100000000 ; n*=2) // change accordingly to exercise
		{
			//Notes for LD ex:(1)change class to the LevenshteinDistance.java.
			MatrixOperationsDif mat= new MatrixOperationsDif(n);
			
			//Notes for LD ex:(2) save into string random generated strings of length n with new method

			t1=System.currentTimeMillis();

			int s= 0;
			for (int repetition= 1; repetition<=nTimes; repetition++)
			{  	
				//Notes from class for LD ex:here you will put the name of the object from (1) 
				//and add the method from the other class that finds the LevenshteinDistance.
				
				//measure times of operationssd1,sd2
				s = mat.sumDiagonal2();
				s = mat.sumDiagonal1();
			}

			t2=System.currentTimeMillis();
			System.out.println (n+"\t\t"+(t2-t1)+"\t"+s);   

		}//for
		
		System.out.println("\n****** *****");
	}
	
	//Notes for LD ex:new method that will generate random string of length n
}
