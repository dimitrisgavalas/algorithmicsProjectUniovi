package algstudent.s7.pyramid;

import java.util.ArrayList;
import java.util.UUID;

import algstudent.s7.pyramid.utils.BranchAndBound;
import algstudent.s7.pyramid.utils.Node;

/**
 * To solve a reduced version of the Pyramid Puzzle
 * Instructions at http://www2.stetson.edu/~efriedma/puzzle/pyramid/
 */
public class PyramidPuzzle extends BranchAndBound {	
	/**
	 * Constructor for Pyramid Puzzle objects
	 * @param board Representation of the board for playing the puzzle
	 */
    public PyramidPuzzle(ImageBoard board) {
    	rootNode = board; //we create the puzzle to start playing
    }
}
/***************************************************/


/***************************************************/
class ImageBoard extends Node {
	private int[][] board; //board for playing
	private int row; //current row of this board
	private int column; //current column of this board
	private static int n; //size of the side of the board to save the pyramid

	/**
	 * Constructor for Pyramid puzzle objects (root node)
	 * @param n Size of the board
	 */
	public ImageBoard(int n) { //Generates an empty board  //this is different for pictures problem. we will need two different groups for that
		ImageBoard.n = n;	 
		board = new int[n][n];  	//board not static
		row = n-1;	//pointing down right
		column = n-1;
	}

	
	/**
	 * Inserts the values of a line from the pyramid 
	 * It is call once per every row of the pyramid to initialize all the values
	 * @param values Values of a row of the pyramid
	 * @param row Number of the current row
	 */
	public void insertValues(String[] values, int row) { //read text file line by line
		for (int i=0; i<row+1; i++) { //in row 0,i have 1 value; in row 1, i have 2 values...
			if (values[i].equals("*")) //convet * to 0 
				board[row][i] = 0;
			else
				board[row][i] = Integer.parseInt(values[i]);
		}
	}
		
    @Override
    public String toString() { //maybe print pyramid in table
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<n; i++) { 
			//To complete
			for (int j=0; j<=i; j++){			
				if (board[i][j] == 0) //empty
					sb.append("* ");
				else 
					sb.append(board[i][j] + " ");
			}
			sb.append("\n");
		}
		return sb.toString();
    }

    /**
     * Counts the number of blanks that are not yet filled
     */
    @Override
    public void calculateHeuristicValue() {
    	int counter = 0;
    	if(prune()) {
    		heuristicValue = Integer.MAX_VALUE;
    	}else {
    		for(int i=0; i<n; i++) {
    			for(int j=0; j<=1; j++) {
    				if(board[i][j] < i) {
    					counter++;
    				}
    				
    			}
    		}
    		heuristicValue = counter;
    	}
    }
    
	/**
	 * Checks if we should prune when the conditions are not longer met
	 * @return True if we should prune. False otherwise
	 */
	private boolean prune() {
		for(int i=0; i<n-1; i++) { //n-1 because if go last row and check below there is nothing so we get an error
			for(int j=0; j<1; j++) {
				if(board[i][j] > 0 && board[i+1][j]>0 && board[i+1][j+1]>0) {
					boolean valid = false;
					if(board[i][j] == board[i+1][j] + board[i+1][j+1]) {
    					valid = true;
    				}else if(board[i][j] == board[i+1][j] - board[i+1][j+1]) {
    					valid = true;
    				}else if(board[i][j] == board[i+1][j] - board[i+1][j]) {
    					valid = true;
    				}
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean isSolution() {
		//i know becasue i dont have asterisc
		return (heuristicValue == 0) ? true : false;
	}
    
	/**
	 * To get the children of the current node. They 
     * point to their parent through the parentID
	 */
	@Override
	public ArrayList<Node> expand() {
		ArrayList<Node> result = new ArrayList<Node>();
		int[][] newBoard;
		ImageBoard temp;
		
		while(board[row][column] != 0) {
			if(column > 0 ) {
				column--;
			}else {
				row--;
				column = row;
			}
		}
		
		//we now have the position of the asteriustic
		for (int k=1; k<10; k++) {
			newBoard = copyBoard(row, column, k);
			temp = new ImageBoard(newBoard, depth+1, this.getID(), row, column); //basicly we are crating the new nodes. getID isthe parent id of the node we are creating
		}
		
		return result;
	}
	
	private int[][] copyBoard(int row, int column, int k) {
		int[][] newBoard = new int[n][n];
		
		for (int i=0; i<n; i++) 
			for (int j=0; j<=i; j++)
				newBoard[i][j] = board[i][j];				      
		
		newBoard[row][column] = k;	
		return newBoard;
	}
	
	/**
	 * Constructor for Pyramid puzzle objects (children of the root node)
	 * @param board
	 * @param depth
	 * @param parentID
	 */
    public ImageBoard(int[][] board, int depth, UUID parentID, int row, int column) {
    	super();
		this.board = board;
		this.depth = depth;
		this.parentID= parentID;
		this.row = row;
		this.column = column;
		calculateHeuristicValue();
	}

} 


