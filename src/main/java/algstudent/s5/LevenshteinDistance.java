package algstudent.s5;
import java.util.Arrays;
import java.util.List;

/**
 * Date 29/03/22
 * @author Dimitrios Gavalas
 *
 * Levenshtein Distance
 * LD is the minimum number of operations required to transform one string to another.
 *
 * Time complexity is O(m*n) 
 *
 * References:
 * https://www.geeksforgeeks.org/java-program-to-implement-levenshtein-distance-computing-algorithm/
 * https://en.wikipedia.org/wiki/Edit_distance
 * https://www.geeksforgeeks.org/convert-a-string-to-character-array-in-java/
 * https://www.javatips.net/api/Duke-master/duke-core/src/test/java/no/priv/garshol/duke/comparators/LevenshteinTest.java
 * https://www.youtube.com/watch?v=MiqoA-yF-0M&t=1s
 * https://www.youtube.com/watch?v=We3YDTzNXEk&t=305s
 * 
 */

public class LevenshteinDistance {
	
	
	public int findDistance(String strOne, String strTwo){
		
		//arrays with words
		char[] str1 = strOne.toCharArray();
		char[] str2 = strTwo.toCharArray();
		
		//array that we save all possible LevenshteinDistances between the 2 arrays we choose
        int lavenDistance[][] = new int[str1.length+1][str2.length+1];
        
        //stin prwti grammi vazoume 0-n
        for(int i=0; i < lavenDistance[0].length; i++){
        	lavenDistance[0][i] = i;
        }
        //stin prwti stilli vazoume 0-n
        for(int i=0; i < lavenDistance.length; i++){
        	lavenDistance[i][0] = i;
        }
        
        for(int i=1;i <=str1.length; i++){
            for(int j=1; j <= str2.length; j++){
                        	
            	if(str1[i-1] == str2[j-1]){
                    lavenDistance[i][j] = lavenDistance[i-1][j-1];
                }
            	//if strings are different distance = minimum of left top and diagonal
            	else{
                    lavenDistance[i][j] = 1 + min(lavenDistance[i-1][j-1], lavenDistance[i-1][j], lavenDistance[i][j-1]);
                }
            	
            }
        }
        
        return lavenDistance[str1.length][str2.length]; //epistrefoume thn teleutai thesi opou kai exei to ld twn duo lejewn 
    }
		
	//prints Levenshtein Distance array
	public void printLevenshteinDistane(String a, String b) {
		
		char[] str1 = a.toCharArray();
		char[] str2 = b.toCharArray();
		
		//array that we save all possible LevenshteinDistances between the 2 arrays we choose
        int lavenDistance[][] = new int[str1.length+1][str2.length+1];
        
        
        for(int i=0; i < lavenDistance[0].length; i++){
        	lavenDistance[0][i] = i;
        }
        
        for(int i=0; i < lavenDistance.length; i++){
        	lavenDistance[i][0] = i;
        }
        
        for(int i=1;i <=str1.length; i++){
            for(int j=1; j <= str2.length; j++){
                        	
            	if(str1[i-1] == str2[j-1]){
                    lavenDistance[i][j] = lavenDistance[i-1][j-1];
                }
            	//if strings are different distance = minimum of left top and diagonal
            	else{
                    lavenDistance[i][j] = 1 + min(lavenDistance[i-1][j-1], lavenDistance[i-1][j], lavenDistance[i][j-1]);
                }
            	
            }
        }
        System.out.println(Arrays.deepToString(lavenDistance).replace("], ", "]\n"));
	}
	
	//finds minimum between a,b,c
	private int min(int a,int b, int c){
        int min = Math.min(a, b);
        return Math.min(min, c);
    }
	
	 public static void main(String args[]){
	        String str1 = "lelel";
	        String str2 = "a";
	        
	        LevenshteinDistance ld = new LevenshteinDistance();
	        int result = ld.findDistance(str1, str2);
	        System.out.print(result);
	    }

}
