
public class GameBoardNode {
	private GameBoard board; 
	private boolean isEnd; 
	private Box currentTurn; 
	private Box winner; 
	private GameBoardNode config[] = new GameBoardNode[9]; 
	private double winProb; 
	private double loseProb; 
	private double drawProb; 
	
	
	public GameBoardNode(GameBoard board, Box currentTurn) throws IllegalArgumentException{
		if (board == null || currentTurn == Box.EMPTY)
			throw new IllegalArgumentException(); 
		this.board = board; 
		this.currentTurn = currentTurn;
		if (hasWon(board.getBoard(), currentTurn)){
			winner = currentTurn;
			isEnd = true;
		}
		else if (hasWon(board.getBoard(), getOppositeTurn(currentTurn))){
			winner = getOppositeTurn(currentTurn); 
			isEnd = true;
		}
		else if (isDraw(board.getBoard())){
			winner = Box.EMPTY;
			isEnd = true;
		}
		else
			isEnd = false; 
		
	}
	
	public void createChildren(){
		int i = 0;
		if (isEnd == true) {
			config = null;
		} else{
			if (i < 9){
				for (int j = 0; j < board.getBoard().length;j++){
					Box[] temp = new Box[9];
					System.arraycopy(board.getBoard(), 0, temp, 0, temp.length);
					if (temp[j] == Box.EMPTY){
						temp[j] = currentTurn;
						GameBoard newBoard = new GameBoard(temp); 
						GameBoardNode possibility = new GameBoardNode(newBoard, getOppositeTurn(currentTurn));
						config[i++] = possibility; 
						possibility.createChildren();
					}
				}
			}
		}
	}
	public void initializeBoard(){
		board.initialize(); 
	}
	public void setProbabilities(){
		winProb = Math.round((double) 100 * findWinningLeaves(Box.O)/findLeaves());
		winProb /= 100; 
		drawProb = Math.round((double) 100*findDrawLeaves(Box.O)/findLeaves());
		drawProb /= 100; 
		loseProb = Math.round((double) 100*(findLosingLeaves(Box.O))/findLeaves());
		loseProb /= 100;
		if (config != null){
			for (int i = 0; i < config.length; i++){
				if (config[i] != null)
					config[i].setProbabilities();
			}
		}
	}
	
	public int findLeaves(){
		int sum = 0;
		if (config != null){
			for (int i = 0; i < config.length; i++){
				if (config[i] != null)
					sum += config[i].findLeaves();
			}
		}
		else{
			return 1; 
		}
		return sum; 
	}
	public int findWinningLeaves(Box a){
		int sum = 0; 
		if (config != null){
			for (int i = 0; i < config.length; i++){
				if (config[i] != null)
					sum += config[i].findWinningLeaves(a); 
			}
		}else{
			if (hasWon(board.getBoard(), a))
				return 1; 
			else 
				return 0;
		}
		return sum;
	}
	public int findLosingLeaves(Box a){
		int sum = 0; 
		if (config != null){
			for (int i = 0; i < config.length; i++){
				if (config[i] != null)
					sum += config[i].findLosingLeaves(a);
			}
		}
		else{
			if (hasWon(board.getBoard(), getOppositeTurn(a)))
				return 1;
			else 
				return 0; 
		}
		return sum; 
	}
	public int findDrawLeaves(Box a){
		int sum = 0; 
		if (config != null){
			for (int i = 0; i < config.length; i++){
				if (config[i] != null)
					sum += config[i].findDrawLeaves(a);
			}
		}
		else{
			if (isDraw(board.getBoard()))
				return 1; 
			else 
				return 0; 
		}
		return sum; 
	}
	public void setTurn(Box turn){
		currentTurn = turn; 
	}
	public static boolean hasWon(Box[] board, Box a){
		if (board[0] == a && board[1] == a && board[2] == a)
			return true;
		else if (board[3] == a && board[4] == a && board[5] == a)
			return true;
		else if (board[6] == a && board[7] == a && board[8] == a)
			return true; 
		else if (board[0] == a && board[3] == a && board[6] == a)
			return true;
		else if (board[1] == a && board[4] == a && board[7] == a)
			return true; 
		else if (board[2] == a && board[5] == a && board[8] == a)
			return true; 
		else if (board[0] == a && board[4] == a && board[8] == a)
			return true; 
		else if (board[2] == a && board[4] == a && board[6] == a)
			return true;
		return false; 
	}
	public static boolean isDraw(Box[] board){
		for (int i = 0; i < board.length; i++){
			if (board[i] == Box.EMPTY)
				return false;
		}
		if (!hasWon(board, Box.X) && !hasWon(board, Box.O))
			return true; 
		return false;
	}
	public Box getOppositeTurn(Box turn){
		if (turn == Box.O)
			return Box.X; 
		return Box.O; 
	}
	
	public GameBoardNode[] getChildren(){
		return config; 
	}
	
	public Box getTurn(){
		return currentTurn; 
	}
	public Box getWinner(){
		return winner;
	}
	public boolean isEnd(){
		return isEnd;
	}
	public double getWinProb(){
		return winProb; 
	}
	public GameBoard getBoard(){
		return board; 
	}
	public String toString(){
		return board.toString() + "Probability of winning: " + winProb + "\n" + "Probability of losing: " + loseProb + "\n" + "Probability of draw: " + drawProb ; 
	}
}
