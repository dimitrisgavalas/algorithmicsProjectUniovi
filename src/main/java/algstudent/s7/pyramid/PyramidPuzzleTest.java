package algstudent.s7.pyramid;

import static org.junit.Assert.assertEquals;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Test;

/**
 * JUnit Test for Pyramid Puzzle
 */
public class PyramidPuzzleTest {
	@Test
	public void test5() {
		boolean result = executeFromFile("src/main/java/labs/examples/branchandbound/pyramid/case5.txt");
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
			
			//first line (HEIGHT OF THE PYRAMID)
			String size = br.readLine(); //height of the pyramid
			int n = Integer.parseInt(size); //n
			
			ImageBoard board = new ImageBoard(n);
			
			//next lines
			for (int i=0; i<n; i++) {
				String[] values = br.readLine().split(" ");				
				board.insertValues(values, i);
			}
						
			PyramidPuzzle puzzle = new PyramidPuzzle(board);	
			puzzle.branchAndBound(puzzle.getRootNode()); 		
			puzzle.printSolutionTrace();
			
			result = puzzle.getBestNode() != null ? true : false; 
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return result;
	}
	
}
