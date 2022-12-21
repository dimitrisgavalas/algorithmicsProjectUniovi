package algstudent.s6;
//class to create a test ()
import java.nio.file.Paths;

public class ImageAveragerBench {
	
	// Benchmarking settings
	private static String REAL_IMG = Paths.get("").toAbsolutePath().toString() + "/algstudent/s6/einstein_1_256.png";
	private static String BAD_IMG = Paths.get("").toAbsolutePath().toString() + "/algstudent/s6/einstein_1_256.png";
	private static String OUT_DIR_G = Paths.get("").toAbsolutePath().toString() + "/algstudent/s6/out_g/";
	private static String OUT_DIR_B = Paths.get("").toAbsolutePath().toString() + "/algstudent/s6/out_bt";
	private static int N_IMGS = 13; 
	private static double PERCENTAGE_BAD = 25; // %
	private static double S_NOISE = 5.0; // Noise level - Gaussian sigma
		
	public static void main(String[] args) {
		
		int n_real, n_bad;
		ImageAverager img_avger;
		
		// Generating and testing a single dataset instance
		n_bad = (int) ((PERCENTAGE_BAD/100.)*N_IMGS); //25%*13//with (int) 1. type conversion from double to int. 2. dont print digits after comma
		n_real = N_IMGS - n_bad;
		img_avger = new ImageAverager(REAL_IMG, BAD_IMG, n_real, n_bad, S_NOISE); //constructor from ImageAverager class. "","",10,3,5.0. 10 "good" and 3 "bad" images are generated
				
		//testing greedy algorithm
		System.out.print("TESTING GREEDY:\n");
		img_avger.splitSubsetsGreedy(N_IMGS);//N_IMGS = 13
		System.out.printf("  -ZNCC: %f\n",  img_avger.zncc()); //prints the zncc of photos
		System.out.printf("  -Counter: %d\n",  img_avger.getCounter()); //prints pics in the 2 groups
		img_avger.saveResults(OUT_DIR_G);//save results to out_g folder
			
		//test backtraking with balancing
		System.out.print("TESTING BACKTRACKING BALANCING:\n");
		img_avger.splitSubsetsBacktracking(0,1,0,0);
		System.out.printf("  -ZNCC: %f\n",  img_avger.zncc());
		System.out.printf("  -Counter: %d\n",  img_avger.getCounter());
		img_avger.saveResults(OUT_DIR_B);
		
//		//test backtraking WITHOUT balancing
		System.out.print("TESTING BACKTRACKING WITHOUT BALANCING:\n");
		img_avger.splitSubsetsBacktracking(0);
		System.out.printf("  -ZNCC: %f\n",  img_avger.zncc());
		System.out.printf("  -Counter: %d\n",  img_avger.getCounter());
		img_avger.saveResults(OUT_DIR_B);
	}

}