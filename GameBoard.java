
public class GameBoard {
	private Box[] board; 
	private final int boardSize = 9; 
	
	public GameBoard(){
		board = new Box[boardSize]; 
	}
	public GameBoard(Box[] board){
		this.board = board; 
	}
	public void initialize(){
		for (int i = 0; i < boardSize; i++)
			board[i] = Box.EMPTY; 
	}
	public void setBoard(Box a, int position){
		board[position] = a; 
	}
	public Box[] getBoard(){
		return board;
	}
	
	public boolean equals(Object o) throws IllegalArgumentException{
		if (!(o instanceof GameBoard))
			throw new IllegalArgumentException();
		GameBoard temp = (GameBoard) o; 
		for (int i = 0; i < boardSize; i++){
			if (board[i] != temp.board[i] )
				return false;
		}
		return true; 
	}
	
	public String toString(){
		String s = ""; 
		for (int i = 0; i < boardSize; i++){
			if (i == 0 || i == 3 || i == 6){
				if (board[i] == Box.O)
					s += "|O|" ; 
				else if (board[i] == Box.X)
					s+= "|X|";
				else 
					s += "|_|";
			}
			else if (i == 2 || i == 5 || i == 8){
				if (board[i] == Box.O)
					s += "|O|\n" ; 
				else if (board[i] == Box.X)
					s+= "|X|\n";
				else 
					s += "|_|\n";
			}
			else{
				if (board[i] == Box.O)
					s += "O" ; 
				else if (board[i] == Box.X)
					s+= "X";
				else 
					s += "_";
			}
		}
		return s; 
	}
}
