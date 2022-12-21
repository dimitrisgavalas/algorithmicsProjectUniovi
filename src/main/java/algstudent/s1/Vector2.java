package algstudent.s1;

/* which is a program that should be 
 * able to measure the time of an 
 * operation (algorithm), namely the 
 * addition of the n elements of a vector, 
 * that we have in the Vector1 class.
 * */


public class Vector2
{
	static int []v;

	public static void main (String arg [] )
	{
		int n= Integer.parseInt (arg[0]);  //size of problem. whatever i add in argument
		v = new int [n] ;
		Vector1.fillIn (v);

		//long variables to collect the data of milliseconds
		long t1,t2;

		t1=System.currentTimeMillis();	// t1 milliseconds 
		System.out.println("t1 = " + t1);
		int s=Vector1.sum(v);
		t2=System.currentTimeMillis();	// t2 milliseconds 
		System.out.println("t2 = " + t2);
		System.out.println ("Problem size = " + n + "\t" + "\nDifference = " + (t2-t1));

		System.out.println ("Sum of n elements = "+ s);
	}//main

}