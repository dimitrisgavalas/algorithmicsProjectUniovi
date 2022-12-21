package algstudent.s32;

public class TrominoTimes {

	public static void main(String[] args) {
		int nTimes = Integer.valueOf(args[0]);
		long t1,t2;
		
		System.out.println("Repetitions = "+ nTimes);
		System.out.println ("Size\tTime\t");
		
		for (int n = 8; n <= Integer.MAX_VALUE; n *= 2) {
			
			Tromino tr = new Tromino(n, 1, 5);			
			t1 = System.currentTimeMillis();
			
			for (int reps = 0; reps < nTimes; reps++) {
				tr.solveTromino();
			}
			
			t2 = System.currentTimeMillis();
			
			System.out.println("N: "+ n +" \tms: "+ (t2-t1));
		}

	}

}
