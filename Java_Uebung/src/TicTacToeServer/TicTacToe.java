package TicTacToeServer;

public class TicTacToe {

	public Boolean[][] gamefield = new Boolean[3][3];

	public TicTacToe() {
		printGamefield();
	}

	public void printGamefield() {
		System.out.println("=========");
		for (Boolean[] row : gamefield) {
			System.out.print("| ");
			for (Boolean field : row) {
				char zeichen;

				if (field == null)
					zeichen = ' ';
				else if (field)
					zeichen = 'X';
				else
					zeichen = 'O';

				System.out.print(zeichen + " ");
			}
			System.out.println("|");
		}
		System.out.println("=========");
	}

	public Boolean winCondition() {
		for (Boolean[] row : gamefield) { // drei gleiche in einer Zeile
			Boolean zeichen = row[0];
			
			if (zeichen.equals(row[2]) && zeichen.equals(row[1]))
				return zeichen;
		}
		for (int spalte = 0; spalte < gamefield.length; spalte++) { // drei in einer Spalte
			Boolean zeichen = gamefield[1][spalte];
			if (gamefield[0][spalte] == zeichen == gamefield[2][spalte])
				return zeichen;
		}
		// diagonal
		if (gamefield[1][1] == gamefield[0][0] == gamefield[2][2])
			return gamefield[1][1];

		if (gamefield[1][1] == gamefield[0][2] == gamefield[2][0])
			return gamefield[1][1];

		return null;
	}

	public static void main(String[] args) {

		TicTacToe game1 = new TicTacToe();
		//System.out.println(game1.winCondition());
		game1.gamefield[0][0] = true;
		game1.gamefield[2][1] = true;
		game1.gamefield[0][2] = true;
		game1.gamefield[1][1] = false;
		game1.gamefield[2][2] = true;
		

		game1.printGamefield();
		System.out.println(game1.winCondition());
	}
}
