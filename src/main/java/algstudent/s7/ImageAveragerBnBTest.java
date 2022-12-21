package algstudent.s7;

import static org.junit.Assert.assertEquals;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Test;

import algstudent.s7.ImageAveragerBnB;
import algstudent.s7.ImageBoard;

/**
 * JUnit Test for Pyramid Puzzle
 */
public class ImageAveragerBnBTest {
	@Test
	public void test5() {
		boolean result = executeFromFile( "/algstudent/s6/einstein_1_256.png");
		assertEquals(true, result);
	}
	
	@Test
	public void test15() {
		boolean result = executeFromFile("src/main/java/labs/examples/branchandbound/pyramid/case15.txt");
		assertEquals(true, result);
	}
	
	/**
	 * Reads the initial pyramid from a text file and creates an object to deal with the problem
	 * @param file File from which 
	 * @return True if we find a solution for the problem, false otherwise
	 */
	private boolean executeFromFile(String file) {
		boolean result = false;
		//input
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			
			String size = br.readLine(); //height of the pyramid
			int n = Integer.parseInt(size); //n
			
			ImageBoard board = new ImageBoard(n);
			
			//next lines
			for (int i=0; i<n; i++) {
				String[] values = br.readLine().split(" ");				
				board.randomIndexes(n);
			}
						
			ImageAveragerBnB imAv = new ImageAveragerBnB(board);	
			imAv.branchAndBound(imAv.getRootNode()); 		
			imAv.printSolutionTrace();
			
			result = imAv.getBestNode() != null ? true : false; 
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return result;
	}
}
