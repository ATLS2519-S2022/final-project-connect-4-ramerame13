
public class minimaxPlayer implements Player {
	private static java.util.Random rand = new java.util.Random();
	int id;
	int opponentId;
	int cols;

    @Override
    public String name() {
        return "Minnie";
    }

    @Override
    public void init(int id, int msecPerMove, int rows, int cols) {
    	this.id = id; //id is your player's id, opponent's id is 3-id
    	this.opponentId = id - 3;
    	this.cols = cols;
    }

    @Override
    public void calcMove(
        Connect4Board board, int oppMoveCol, Arbitrator arb) 
        throws TimeUpException {
        // Make sure there is room to make a move.
        if (board.isFull()) {
            throw new Error ("Complaint: The board is full!");
        }
        
        // Make a random valid move.
        int col = 0;
        int tempMove = 0;
        int bestMove = 0;
        int maxDepth = 1;
        
        //while there's time remianing and seach depth is <= the number of moves remaining
        while (!arb.isTimeUp() && maxDepth <= board.numEmptyCells()) {
        	//run minimax search and set move to be the best column corresponding to the best score
        	
        	maxDepth ++;
        	arb.setMove(move);
        }
    }
    
    public int minimax(Connect4Board board, int depth, boolean isMaximizing, Arbitrator arb) {
    	//if depth = 0 or no more moves or time is up
    	//return the heuristic value of node
    	
    	if(depth == 0 || board.isFull() || arb.isTimeUp()) {
    		return calcScore(board,id) - calcScore(board, opponentId);
    	
    }
        
//        do { col = rand.nextInt(board.numCols());
//        } while (!board.isValidMove(col));
        
        for (board.isValid()){
        	tempMove = board.move();
        	
        	calcScore();
        	
        	board.unmove();
        }
        return bestMove;
        
        //find maximum score from all possible moves
        
        arb.setMove(col);
}


    
	public int calcScore(Connect4Board board, int id)
	{
		final int rows = board.numRows();
		final int cols = board.numCols();
		int score = 0;
		// Look for horizontal connect-4s.
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c <= cols - 4; c++) {
				if (board.get(r, c + 0) != id) continue;
				if (board.get(r, c + 1) != id) continue;
				if (board.get(r, c + 2) != id) continue;
				if (board.get(r, c + 3) != id) continue;
				score++;
			}
		}
		// Look for vertical connect-4s.
		for (int c = 0; c < cols; c++) {
			for (int r = 0; r <= rows - 4; r++) {
				if (board.get(r + 0, c) != id) continue;
				if (board.get(r + 1, c) != id) continue;
				if (board.get(r + 2, c) != id) continue;
				if (board.get(r + 3, c) != id) continue;
				score++;
			}
		}
		// Look for diagonal connect-4s.
		for (int c = 0; c <= cols - 4; c++) {
			for (int r = 0; r <= rows - 4; r++) {
				if (board.get(r + 0, c + 0) != id) continue;
				if (board.get(r + 1, c + 1) != id) continue;
				if (board.get(r + 2, c + 2) != id) continue;
				if (board.get(r + 3, c + 3) != id) continue;
				score++;
			}
		}
		for (int c = 0; c <= cols - 4; c++) {
			for (int r = rows - 1; r >= 4 - 1; r--) {
				if (board.get(r - 0, c + 0) != id) continue;
				if (board.get(r - 1, c + 1) != id) continue;
				if (board.get(r - 2, c + 2) != id) continue;
				if (board.get(r - 3, c + 3) != id) continue;
				score++;
			}
		}
		return score;
	}
}
