
public class GameTree {
	private GameBoardNode root; 
	private GameBoardNode cursor; 
	
	public GameTree(){
		GameBoard initial = new GameBoard();
		initial.initialize();
		root = new GameBoardNode(initial, Box.X);
		root.createChildren();
		root.setProbabilities();
		cursor = root; 
	}
	public GameTree(GameBoardNode root, Box turn){
		this.root = root; 
		this.root.setTurn(turn);
		this.root.createChildren();
		this.root.setProbabilities();
		cursor = root; 
	}
	public void makeMove(int position) throws IllegalArgumentException{
		if (position < 1 || position > 9 || root.getBoard().getBoard()[position-1] != Box.EMPTY)
			throw new IllegalArgumentException(); 
		cursor.getBoard().getBoard()[position-1] = cursor.getTurn();
		for (int i = 0; i < cursor.getChildren().length; i++){
			if (cursor.getChildren() != null){
				if (cursor.getChildren()[i].getBoard().equals(cursor.getBoard())){
					cursor = cursor.getChildren()[i];
					break;
				}
			}
		}
	}
	
	public static GameBoardNode buildTree(GameBoardNode root, Box turn){
		GameTree subTree = new GameTree(root, turn); 
		return subTree.root;
	}
	public Box getTurn(){
		return root.getTurn(); 
	}
	public static Box checkWin(GameBoardNode node){
		return node.getWinner();
	}
	public GameBoardNode getCurrentBoard(){
		return cursor; 
	}
	public GameBoardNode getMaxChild(){
		GameBoardNode max = cursor.getChildren()[0];
		for (int i = 0; i < cursor.getChildren().length; i++){
			if (cursor.getChildren()[i] != null){
				if (cursor.getChildren()[i].getWinProb() > max.getWinProb()){
					max = cursor.getChildren()[i];
				}
				}
			}
		max.setProbabilities();
		return max; 
	}
	
	public double cursorProbability(){
		return cursor.getWinProb(); 
	}
	
	public String toString(){
		return root.toString(); 
	}
	public int totalLeaves(){
		return root.findLeaves();
	}
}

