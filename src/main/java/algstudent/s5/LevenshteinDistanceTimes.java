package algstudent.s5;
//import s5.LevenshteinDistance;;
/**
 * Date 29/03/22
 * @author Dimitrios Gavalas
 *
 * Levenshtein Distance Times
 *  Class to measure experimental times using strings
 *  with the same length (n=m) giving character values to the strings in a random way. Make the
 *  size of the problem grow for different sizes of the strings (100, 200, 400, 800, 1600, etc.).
 * 
 *
 * References:
 * MatrixOperationsTimes class from Lab number 2
 * https://www.geeksforgeeks.org/generate-random-string-of-given-size-in-java/
 * https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuilder.html
 * https://www.geeksforgeeks.org/stringbuilder-class-in-java-with-examples/
 * https://www.youtube.com/watch?v=oYcb0N1YfVw
 * https://www.geeksforgeeks.org/generate-random-string-of-given-size-in-java/
 */

public class LevenshteinDistanceTimes {
	public static void main(String[] args) {				
		int nTimes = Integer.parseInt (args[0]);	// times the operation is repeated

		long t1,t2;

		System.out.println("n = "+ nTimes);
		System.out.println ("Size\t\tTime\tLD\n");
		/**
		 * checking time difference before and after calculating 
		 * Levenshtein Distance using currentTimeMillis
		 */

		for ( int n=100; n<= 100000000 ; n*=2) // n increments *2
		{
			LevenshteinDistance ld = new LevenshteinDistance();
			//random generated Strings with length=n
			String s1 = randomString(n);
			String s2 = randomString(n);

			t1=System.currentTimeMillis();
			int s= 0;
			/*depending on the arguments we add the code will check
			 * nTimes the ld distance
			 */
			for (int repetition= 1; repetition<=nTimes; repetition++)
			{  	
				s = ld.findDistance(s1 , s2);
			}
			
			t2=System.currentTimeMillis();
			System.out.println (n+"\t\t"+(t2-t1)+"\t"+s);   

		}//for
		
		System.out.println("\n****** *****");
	}
	  
	/*
	 * in this class we generate random string with length=n
	 * @var int n == length of string we want to generate
	 * */
    static String randomString(int n)
    {
  
        // available characters to choose from
        String s1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvxyz";
  
        // StringBuffer size of s1
        StringBuilder sbf = new StringBuilder(n);
  
        for (int i = 0; i < n; i++) {
  
            // random number between 0-s1.length
        	//chose a random Character from String s1
            int randomNum = (int)(s1.length() * Math.random());
  
            // add Character one by one in end of sb
            //me to append pros9etoume string se ena adeio h prohgoumeno string
            sbf.append(s1.charAt(randomNum)); 
        }
        
        //return string
        return sbf.toString();
    }
}

