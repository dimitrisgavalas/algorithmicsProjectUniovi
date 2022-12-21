package algstudent.s1;

/* class that will increase the size of the vector, 
 * obtaining times for each case. In this way,
 * you will be able to follow more conveniently 
 * the evolution of the execution time.
 * */



public class Vector3
{
	static int []v;

	public static void main (String arg [] )
	{
		long t1,t2;

		System.out.println("Size\t\tTime Dif\tSum\n");
		for ( int n=10; n<= 1000000000 ; n*=3) // n increases exponentially (*3)
		{
			System.out.print (n+"\t");
			v = new int [n] ;
			Vector1.fillIn(v);

			//Measure the time of the add() operation
			t1=System.currentTimeMillis();
			int s=Vector1.sum(v);
			t2=System.currentTimeMillis();
			System.out.print ("\t" + (t2-t1)+"\t");

			System.out.println("\t" + s); //prints sum of vector
		} // end problem size for

		System.out.println("\n***** The End *****");

	} // end main

} // end of class 