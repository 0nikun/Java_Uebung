package TicTacToeServer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TicTacToeWindow extends JFrame {
	JLabel gamefield;
	Container menu;
	JButton[][] fields = new JButton[3][3];
	boolean turn = false; // false = player1 true = player2
	JButton reset;
	JButton newGame;
	JLabel win = new JLabel("You Win!", JLabel.CENTER);
	JLabel player1;
	JLabel player2;
	JPanel players;

	int turnCount = 0;
	JPanel controllButtons;
	int p1Punkte = 0;
	int p2Punkte = 0;
	JLabel punkteAnzeige;

	Color p1Color;
	Color p2Color;

	public TicTacToeWindow(Color p1, Color p2) {

		p1Color = p1;
		p2Color = p2;

		menu = getContentPane();
		gamefield = new JLabel();
		gamefield.setLayout(new GridLayout(3, 3));
		reset = new JButton("again <3");
		newGame = new JButton("new Game :(");

		controllButtons = new JPanel();

		player1 = new JLabel("Player 1", JLabel.CENTER);
		player2 = new JLabel("Player 2", JLabel.CENTER);
		player1.setForeground(p1Color);
		player2.setForeground(p2Color);

		players = new JPanel();
		players.setLayout(new GridLayout(2, 0));
		players.add(player1);
		players.add(player2);

		players.setPreferredSize(new Dimension(100, 100));
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				fields[row][col] = new JButton();
				JButton b = fields[row][col];
				b.setBackground(Color.DARK_GRAY);
				ActionListener bl = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton s = (JButton) e.getSource();

						if (s.getBackground().equals(Color.DARK_GRAY) && (checkForWin() == null)) {
							if (turn)
								s.setBackground(p1Color);
							else
								s.setBackground(p2Color);

							setTurn();
						}

						if (checkForWin() != null) {
							showWinner(checkForWin());
						}

					}
				};
				b.addActionListener(bl);
				gamefield.add(b);
			}
		}
		ActionListener rs = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				resetGame();
			}
		};

		ActionListener ng = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				p1Punkte = 0;
				p2Punkte = 0;
				resetGame();

			}
		};
		controllButtons.add(newGame);
		controllButtons.add(reset);

		punkteAnzeige = new JLabel("Player 1: " + p1Punkte + " | Player 2: " + p2Punkte, JLabel.CENTER);
		menu.add(punkteAnzeige, BorderLayout.NORTH);
		newGame.addActionListener(ng);
		reset.addActionListener(rs);
		menu.add(controllButtons, BorderLayout.SOUTH);
		menu.add(gamefield, BorderLayout.CENTER);

		menu.add(players, BorderLayout.WEST);

		win.setFont(new Font("SansSerif", Font.BOLD, 30));
		setTurn();

	}

	protected void draw() {
		System.out.println("Draw");
		menu.remove(gamefield);
		menu.remove(players);
		win.setForeground(Color.BLACK);
		win.setText("Unentschieden !");

		menu.add(win, BorderLayout.CENTER);
		pack();
		setSize(500, 500);
		turnCount = 1;
	}

	protected void showWinner(Boolean winner) {
		System.out.println("Winner");
		menu.remove(gamefield);
		menu.remove(players);
		win.setForeground(winner ? p2Color : p1Color);
		win.setText(winner ? "Player2 Wins!" : "Player1 Wins!");
		if (winner)
			p2Punkte++;
		else
			p1Punkte++;
		punkteAnzeige.setText("Player 1: " + p1Punkte + " | Player 2: " + p2Punkte);
		menu.add(win, BorderLayout.CENTER);
		pack();
		setSize(500, 500);
		turnCount = 0;
	}

	public Boolean checkForWin() {
		Color left;
		Color mid;
		Color right;
		for (int row = 0; row < fields.length; row++) {
			left = fields[row][0].getBackground();
			mid = fields[row][1].getBackground();
			right = fields[row][2].getBackground();
			if (left.equals(mid) && left.equals(right) && !left.equals(Color.DARK_GRAY)) // 3 in einer Reihe
				return left.equals(p2Color) ? true : false;
			left = fields[0][row].getBackground();
			mid = fields[1][row].getBackground();
			right = fields[2][row].getBackground();
			if (left.equals(mid) && left.equals(right) && !left.equals(Color.DARK_GRAY)) // 3 in einer Spalte
				return left.equals(p2Color) ? true : false;
		}
		left = fields[0][0].getBackground();
		mid = fields[1][1].getBackground();
		right = fields[2][2].getBackground();
		if (left.equals(mid) && left.equals(right) && !left.equals(Color.DARK_GRAY)) // Diagonal von oben links nach
																						// unten // rechts
			return left.equals(p2Color) ? true : false;
		left = fields[0][2].getBackground();
		mid = fields[1][1].getBackground();
		right = fields[2][0].getBackground();
		if (left.equals(mid) && left.equals(right) && !left.equals(Color.DARK_GRAY)) // Diagobal von oben rechts nach
																						// unten Links
			return left.equals(p2Color) ? true : false;
		return null;
	}

	private void resetGame() {
		menu.remove(win);
		menu.add(players, BorderLayout.WEST);
		menu.add(gamefield);

		for (JButton[] row : fields) {
			for (JButton field : row) {
				field.setBackground(Color.DARK_GRAY);
			}
		}
		punkteAnzeige.setText("Player 1: " + p1Punkte + " | Player 2: " + p2Punkte);

		turnCount = 0;
		pack();
		setSize(500, 500);
	}

	public void setTurn() {
		punkteAnzeige.setText("Player 1: " + p1Punkte + " | Player 2: " + p2Punkte);

		if (turn) {
			player2.setForeground(Color.white);
			player1.setForeground(p2Color);
			players.setBackground(p2Color);
			// player2.setBorder(BorderFactory.createLineBorder(Color.WHITE,6));
			// player1.setBorder(BorderFactory.createLineBorder(player1.getBackground()));
		} else {
			player2.setForeground(p1Color);
			player1.setForeground(Color.WHITE);
			players.setBackground(p1Color);

//			player1.setBorder(BorderFactory.createLineBorder(p1Color,6));
//			player2.setBorder(BorderFactory.createLineBorder(player2.getBackground()));

		}
		turn = !turn;
		turnCount++;
		if (turnCount > 9)
			draw();

	}

	public static void main(String[] args) {
		TicTacToeWindow fenster = new TicTacToeWindow(new Color(128,0,128), Color.cyan);
		fenster.setTitle("TicTacToe");
		fenster.setSize(600, 500);
		fenster.setVisible(true);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
