import java.util.Scanner;

public class TicTacToeAI {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		System.out.println("Welcome to TicTacToe!\nThis is your board\n|1|2|3|\n|4|5|6|\n|7|8|9|\nWhat position would you like to mark? ");
		playGame(); 
		
	}

	public static void playGame(){
		GameTree game = new GameTree();
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);	
		while(GameTree.checkWin(game.getCurrentBoard()) == null){
			try{
				
					int move = input.nextInt();	 
					game.makeMove(move);		
					System.out.println(game.getMaxChild());
					System.out.print("Make your move: ");
					game = new GameTree(game.getMaxChild(), game.getTurn());
				
			}catch (Exception e){
				System.out.println("Invalid input. Try again.");
		}
		}
		System.out.print("The winner is " + GameTree.checkWin(game.getCurrentBoard()));
	}
}
