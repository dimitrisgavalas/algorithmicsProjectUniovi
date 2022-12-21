package algstudent.s3;

public class Division4 {

	/**
	 * It's a recursive method by Dividing.
	 * The Parameters are: a=4, b=3, k=2.
	 * So a<b*k, and the complexity is O(n^2).
	 */

	static long cont;

	public static boolean rec4(int n) {
		if (n <= 0)
			cont++;
		else {
			for (int i = 1; i < n; i++) {
				for (int j = 1; j < i; j++) {
					cont++; // O(n^2) k=2
				}
			}
			rec4(n / 3);
			rec4(n / 3);
			rec4(n / 3);
			rec4(n / 3); 
		}
		return true;
	}

	public static void main(String arg[]) {
		long t1, t2, cont;
		int nTimes = Integer.parseInt(arg[0]);
		boolean b = true;

		for (int n = 1; n <= 10000000; n *= 2) {
			t1 = System.currentTimeMillis();

			for (int repetitions = 1; repetitions <= nTimes; repetitions++) {
				cont = 0;
				b = rec4(n);
			}

			t2 = System.currentTimeMillis();

			System.out.println(b + " n=" + n + "**Time=" + (t2 - t1) + "**count=" + nTimes);
		} // for
	} // main
} // class