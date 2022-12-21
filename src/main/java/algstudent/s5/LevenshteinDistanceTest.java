package algstudent.s5;

/**
 * Date 29/03/22
 * @author Dimitrios Gavalas
 *
 * Levenshtein Distance Test
 *  Test all different cases of the Levenshtein Distance Result
 *
 * Note:
 * 
 * References:
 * 
 */

public class LevenshteinDistanceTest {
  
	
	public static void main(String args[]){
               
        LevenshteinDistance ld = new LevenshteinDistance();
        
        String s1 = "dipopo";
		String s2 = "dipopo";
        int result = ld.findDistance(s1,s2);
        System.out.print("When both the strings are the same. We exept 0 as a result.\nResult = " + result + "\n");
        ld.printLevenshteinDistane(s1,s2);   
    
        s1 = "";
		s2 = "";
        result = ld.findDistance(s1,s2);
        System.out.print("\nWhen both the strings are empty. We exept 0 as a result.\nResult = " + result + "\n");
        ld.printLevenshteinDistane(s1,s2);
        
        s1 = "";
		s2 = "dopopo";
        result = ld.findDistance(s1,s2);
        System.out.print("\nWhen 1 strin is empty we except LD to be equal to the length of the other String. We exept 6 as a result.\nResult = " + result + "\n");
        ld.printLevenshteinDistane(s1,s2);
        
        s1 = "ddd1";
		s2 = "ddd";
        result = ld.findDistance(s1,s2);
        System.out.print("\nWhen there is one difference between 2 strings. We exept 1 as a result.\nResult = " + result + "\n");
        ld.printLevenshteinDistane(s1,s2);
        
        s1 = "BARGES";
		s2 = "ABRACADABRA";
        result = ld.findDistance(s1,s2);
        System.out.print("\nWe except 9 as a result.\nResult = " + result + "\n");
        ld.printLevenshteinDistane(s1,s2);
        
        s1="BARCAZAS";
        s2 = "ABRACADABRA";
        result = ld.findDistance(s1,s2);
        System.out.print("\nClass example.\nResult = " + result + "\n");
        ld.printLevenshteinDistane(s1,s2);
        
    }

	

}