package algstudent.s12;

import java.util.Random;
/*
 * complexity = O(n^4)
 * Done in class
 * */
public class Loop4 {
	public static long loop3(int n) {
		Random rn = new Random();
		int count = 0;
		
		for(int i = 1; i < n; i++) {
		    for(int j = 1; j < i * i; j++) {
		        if(j % i == 0) {
		            for(int k = 0; k < j; k++) {
		            	count += rn.nextInt();		            }
		        }
		    }
		}
		return count;
	}
	
	public static void main(String arg []){
		long t1, t2;
		long cont = 0;
		int repetitions = Integer.parseInt(arg[0]);
	
		for (int n=1; n<=Integer.MAX_VALUE; n*=2) {
			t1 = System.currentTimeMillis();
	 
			for (int repetition=1; repetition<=repetitions; repetition++) {
				cont = loop3(n);
			} 
	 
			t2 = System.currentTimeMillis();
			System.out.println("n="+n+ "\tTIME="+(t2-t1)+"\tCONT="+cont+"\trepetitions="+repetitions);	
	 }
		
		
	
	} 
} //class