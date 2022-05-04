
public class GreedyPlayer implements Player{
	
	private static java.util.Random rand = new java.util.Random();
	int id;
	int opponentId;
	int cols;

    @Override
    public String name() {
        return "Greedy Boi";
    }

    @Override
    public void init(int id, int msecPerMove, int rows, int cols) {
    	this.id = id; //id is your player's id, opponent's id is 3-id
    	this.opponentId = 3 - id;
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
        int bestMove = -1000;
        
        for (int i = 0; i < 7; i++){
        	
    		if (board.isValidMove(i)){  		        	
        	board.move(i, id);
        	int tempScore = myScore(board, id);
        	board.unmove(i, id);
        	if (tempScore > bestMove){
        		bestMove = tempScore;
        		col = i;       		
        		}
    		}
        }
        
        //find maximum score from all possible moves       
        arb.setMove(col);
    }
    
    public int myScore(Connect4Board board, int id) {
    	int score = calcScore(board, id);
    	int oppScore = calcScore(board, opponentId);
    	return score - oppScore;
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
