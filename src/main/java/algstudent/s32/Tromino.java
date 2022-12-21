package algstudent.s32;

public class Tromino {
	
	private String[][] board;//tromino array 
	private final static String DOT = "#"; 
	private int elem;//field that inidcates the number of the tromino
	private static final String EMPTY = " ";
	private int inPosX;//initilal postion of #
	private int inPosY;
	
	//ex.8,1,5
	public Tromino(int size, int x, int y) {
		initializeBoard(size);//method that initializes tromino array and fills it with spaces
		placeDot(x, y);//start the tromino from position x,y.

	}
	
	//we initialize the size of the board and fill the board with empty spaces 
	private void initializeBoard(int size) {
		elem = 0;
		board = new String[size][size];
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				board[i][j] = EMPTY;

	}
	
	//change empty cell of tromino to #
	private void placeDot(int x, int y) {
		board[x][y] = DOT;
		inPosX = x;
		inPosY = y;
	}

	public void solveTromino() {
		fillinTable(0, 0, board.length, inPosX, inPosY);
		//solving tromino. we start from (0,0) position of array. we also pass the position of the initial cell we choose
	} 

	 /**
     * Algorithm for filling the trominoes.
     * @param startingX The starting row of the board
     * @param startingY The starting column of the board
     * @param n The size of the board (n x n)
     * @param emptyX The row that cannot be occupied
     * @param emptyY The column that cannot be occupied
     */
	private void fillinTable(int startingX, int startingY, int n, int emptyX, int emptyY) {
		if (n < 2) {
			//if length is smaller than first position x,y errors occur.
		} else {
			int quadrant;
			if (emptyX < startingX + n / 2 && emptyY >= startingY + n / 2)
				 quadrant = 1;
			else if (emptyX >= startingX + n / 2 && emptyY >= startingY + n / 2)
				 quadrant = 4;
			else if (emptyX >= startingX + n / 2 && emptyY < startingY + n / 2)
				 quadrant = 3;
			else
				quadrant = 2;
			elem++;
			int newN = n/2; //we divided the dimension by 2 for the next cases
			
			switch (quadrant) {
			case (1):
				board[startingX + newN - 1][startingY + newN - 1] = String.valueOf(elem);//b[3][3]==1
				board[startingX + newN][startingY + newN] = String.valueOf(elem);//b[][]
				board[startingX + newN][startingY + newN - 1] = String.valueOf(elem);

				fillinTable(startingX, startingY + newN, newN, emptyX, emptyY);
				fillinTable(startingX, startingY, newN, startingX + newN - 1, startingY + newN - 1);
				fillinTable(startingX + newN, startingY + newN, newN, startingX + newN, startingY + newN);
				fillinTable(startingX + newN, startingY, newN, startingX + newN, startingY + newN - 1);

				break;
			case (2):
				board[startingX + newN - 1][startingY + newN] = String.valueOf(elem);
				board[startingX + newN][startingY + newN] = String.valueOf(elem);
				board[startingX + newN][startingY + newN - 1] = String.valueOf(elem);

				fillinTable(startingX, startingY + newN, newN, startingX + newN - 1, startingY + newN);
				fillinTable(startingX, startingY, n / 2, emptyX, emptyY);
				fillinTable(startingX + newN, startingY + newN, newN, startingX + newN, startingY + newN);
				fillinTable(startingX + newN, startingY, newN, startingX + newN, startingY + newN - 1);

				break;
			case (3):
				board[startingX + newN - 1][startingY + newN - 1] = String.valueOf(elem);
				board[startingX + newN - 1][startingY + newN] = String.valueOf(elem);
				board[startingX + newN][startingY + newN] = String.valueOf(elem);

				fillinTable(startingX, startingY + newN, newN, startingX + newN - 1, startingY + newN);
				fillinTable(startingX, startingY, newN, startingX + newN - 1, startingY + newN - 1);
				fillinTable(startingX + newN, startingY + newN, newN, startingX + newN, startingY + newN);
				fillinTable(startingX + newN, startingY, newN, emptyX, emptyY);

				break;
			default:
				board[startingX + newN - 1][startingY + newN - 1] = String.valueOf(elem);
				board[startingX + newN - 1][startingY + newN] = String.valueOf(elem);
				board[startingX + newN][startingY + newN - 1] = String.valueOf(elem);

				fillinTable(startingX, startingY + newN, newN, startingX + newN - 1, startingY + newN);
				fillinTable(startingX, startingY, newN, startingX + newN - 1, startingY + newN - 1);
				fillinTable(startingX + newN, startingY + newN, newN, emptyX, emptyY);
				fillinTable(startingX + newN, startingY, newN, startingX + newN, startingY + newN - 1);

				break;
			}
		}
	}

	public String toString() {
		String aux = "";
		for (int i = 0; i < board.length; i++) {
			aux += "________________________________________\n";
			for (int j = 0; j < board.length; j++) {
				aux += "| " + board[i][j] + " |";
			}
			aux += "\n________________________________________ \n";
		}
		return aux;
	}

	public static void main(String[] args) {
		Tromino tr = new Tromino(8,1,5);
		System.out.println(tr.toString());//print tromino array with initial position		
		tr.solveTromino();
		System.out.println(tr.toString());

	}
}
