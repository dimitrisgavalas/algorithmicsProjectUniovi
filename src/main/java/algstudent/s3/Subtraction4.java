package algstudent.s3;

public class Subtraction4 {

	/**
	 * The Parameters: a=3; b=2; k=1.
	 * Then the complexity is: O(3^(n/2))
	 */
	static long cont;
	public static boolean rec4(int n) {
		if (n <= 0)
			cont++;
		else {
			for (int i = 1; i < n; i++) {
				cont++; // O(n) k=1
			}
			rec4(n - 2);
			rec4(n - 2);
			rec4(n - 2); // a=3 b=2
		}
		return true;
	}

	public static void main(String arg[]) {
		long t1, t2, cont;
		int nTimes = Integer.parseInt(arg[0]);
		boolean b = true;

		for (int n = 1; n <= 100; n++) {
			t1 = System.currentTimeMillis();

			for (int repetitions = 1; repetitions <= nTimes; repetitions++) {
				cont = 0;
				b = rec4(n);
			}

			t2 = System.currentTimeMillis();
			System.out.println(b + " n=" + n + "**TIME=" + (t2 - t1) + "**cont=" + nTimes);
		} // for
	} // main
} // class

