package algstudent.s1;


public class Vector4
{
	static int []v;

	public static void main (String arg [] )
	{
		int nTimes = Integer.parseInt (arg[0]);	// times the operation is repeated

		long t1,t2;

		System.out.println("n = " + nTimes);
		System.out.println("Size\t\tTime\tSum"); 
		for ( int n= 10; n<= 100000000 ; n*=3)  // n is increasing *3
		{
			v = new int [n] ;
			Vector1.fillIn (v);

			t1=System.currentTimeMillis();

			int s= 0;
			// you have to repeat the whole process to measure (what was between t1 and t2)
			for (int i= 1; i<=n; i++){
				
				s= Vector1.sum(v);
			}

			t2=System.currentTimeMillis();
			System.out.println (n+"\t\t"+(t2-t1)+"\t"+s);   

		}//for
		
		System.out.println("\n***** The End *****");
		
	} //main

}
