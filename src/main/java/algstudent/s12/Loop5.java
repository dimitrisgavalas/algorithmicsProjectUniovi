package algstudent.s12;

//done in class
public class Loop5 {
	public static long loop5(int n) {
		long count = 0;
		
		for (int i = 1; i <= n; i++)
			for (int j = n; j >= 0; j--)
				for (int k = 1; k <= n; k*=2)
					for(int l=n; l>=0; l--)
						count++;
		return count;

	}

	public static void main(String arg[]) {
		long c = 0;
		long t1, t2;
		int nV = Integer.parseInt(arg[0]);

		for (int n = 8; n <= 100000; n *= 2) {
			t1 = System.currentTimeMillis();

			for (int r = 1; r <= nV; r++) {
				c += loop5(n);
			}

			t2 = System.currentTimeMillis();

			//System.out.println(n + "\t" + (t2 - t1) + "\t" + c);
			System.out.println("n="+n+ "\tTIME="+(t2-t1)+"\tCONT="+c);	

		} // for

	} // main
} // class

