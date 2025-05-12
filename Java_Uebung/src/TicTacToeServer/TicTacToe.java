package TicTacToeServer;

public class TicTacToe {

	public Boolean[][] gamefield = new Boolean[3][3];
	
	
	public TicTacToe() {
		printGamefield();
	}
	
	
	public void printGamefield() {
		System.out.println("=========");
		for(Boolean[] row : gamefield) {
			System.out.print("| ");
			for (Boolean field : row) {
				char zeichen;
				
				if(field == null)
					zeichen = ' ';
				else if(field)
					zeichen = 'X';
				else
					zeichen = 'O';
				
				System.out.print(zeichen +" ");
			}
			System.out.println("|");
		}
		System.out.println("=========");
	}
	
	
	public static void main(String[] args) {
		
		TicTacToe game1 = new TicTacToe();
		game1.gamefield[0][0] = true;
		game1.gamefield[1][1] = false;
		game1.gamefield[2][2] = true;
		
		game1.printGamefield();
		
	}
}
