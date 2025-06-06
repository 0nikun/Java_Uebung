package GraphicMazeCustom;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MazeCreator extends JFrame {

	private static final long serialVersionUID = 1L;
	Container gm;
	Graphic mazeDrawing;
	Maze maze;
	List<Point> solution;
	int stepOfSolution = 0;
	JButton next;
	JButton prev;
	NavMazeCustom nm;

	JPanel mazeBuilder;
	JButton finishMaze;
	private int mazeSize;

	private final int buttonSize = 25;
	private final static int size = 100;

	boolean solutionFound = false;

//	public void drawMaze() {
//
//		mazeDrawing.clear();
//		for (int row = 0; row < maze.size(); row++) {
//			for (int col = 0; col < maze.size(); col++) {
//				mazeDrawing.setColor(Color.BLACK);
//				if (maze.getMaze().contains(new Point(row, col)))
//					mazeDrawing.fillRect(col * size, row * size, size, size);
//				else {
//					mazeDrawing.drawRect(col * size, row * size, size, size);
//				}
//			}
//		}
//		drawSolution();
//		mazeDrawing.redraw();
//	}
//
//	public void drawSolution() {
//		mazeDrawing.setColor(Color.BLUE);
//		for (Point p : solution) {
//			if (solution.get(stepOfSolution).equals(p))
//				mazeDrawing.fillOval(p.y * size + offset, p.x * size + offset, size - 2 * offset, size - 2 * offset);
//		}
//
//	}

	public MazeCreator(int mazeSize) {

		gm = getContentPane();
		this.mazeSize = mazeSize;

		mazeBuilder = new JPanel();
		mazeBuilder.setLayout(new GridLayout(mazeSize, mazeSize));
		for (int row = 0; row < mazeSize; row++) {
			for (int col = 0; col < mazeSize; col++) {
				JButton b = new JButton();
				b.setBackground(Color.WHITE);
				b.setSize(size, size);
				ActionListener bl = e -> b
						.setBackground(b.getBackground().equals(Color.BLACK) ? Color.WHITE : Color.BLACK);
				b.addActionListener(bl);
				mazeBuilder.add(b);
			}
		}
		gm.setPreferredSize(new Dimension(mazeSize * size, mazeSize * size));
		gm.add(mazeBuilder, BorderLayout.CENTER);
		finishMaze = new JButton("fertig");
		ActionListener f = e -> checkMaze();
		finishMaze.addActionListener(f);
		finishMaze.setPreferredSize(new Dimension(mazeSize * size, buttonSize));

		gm.add(finishMaze, BorderLayout.SOUTH);

		mazeDrawing = new Graphic(mazeSize * size, mazeSize * size);

	}
//		next = new JButton("vor");
//		prev = new JButton("zurück");
//		JPanel controllButtons = new JPanel();
//
//		ActionListener n = e -> drawNext();
//		ActionListener p = e -> drawPrev();
//		next.addActionListener(n);
//		prev.addActionListener(p);
//
//		controllButtons.add(prev);
//		controllButtons.add(next);
//		gm.add(mazeDrawing, BorderLayout.CENTER);
//		gm.add(controllButtons, BorderLayout.SOUTH);

	private void checkMaze() {
		Maze m = createMaze();
		m.printMaze();
		if (m.canExit(0, 0)) {
			// es gibt eine Lösung
			System.out.println("Lösung gefunden");
			this.maze = m;
			nm = new NavMazeCustom(m);
//			setVisible(false);
			showMaze();
		} else {
			// es gibt keine Lösung
		}

	}

	private void showMaze() {

		nm.setTitle("NavMaze");
		nm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(false);

		nm.gm.setPreferredSize(gm.getPreferredSize());
		nm.controllButtons.setPreferredSize(new Dimension(mazeSize * size, 2 * buttonSize));
		ActionListener builder = e -> buildNewMaze();
		nm.newMaze.addActionListener(builder);
//		nm.setSize(mazeSize * size, mazeSize * size + buttonSize);
		nm.setPreferredSize(new Dimension(mazeSize * size, mazeSize * size + 4* buttonSize));
		nm.drawMaze();
		nm.setVisible(true);
		nm.pack();

	}

	private void buildNewMaze() {
		nm.setVisible(false);
		setVisible(true);

	}

	private Maze createMaze() {
		char[][] mazeChars = new char[mazeSize][mazeSize];

		for (Component comp : mazeBuilder.getComponents()) {
			if (comp instanceof JButton button) {
				int yFaktor = size - buttonSize / (mazeSize - 1) - 2;
				int row = button.getX() / size;
				int col = (button.getY()) / yFaktor;
				System.out.println("row:" + row + " col:" + col + " y: " + button.getY() + " yF: " + yFaktor);
				mazeChars[col][row] = button.getBackground().equals(Color.BLACK) ? 'X' : ' ';
			}
		}

		return new Maze(mazeChars);
	}

//	private void drawNext() {
//		if (stepOfSolution > 0)
//			stepOfSolution--;
//		drawMaze();
//	}
//
//	private void drawPrev() {
//		if (stepOfSolution < solution.size() - 1)
//			stepOfSolution++;
//		drawMaze();
//	}

	public static void main(String[] args) {

		char[][] maze = { { ' ', 'X', ' ', 'X', ' ', ' ' }, { ' ', 'X', ' ', ' ', ' ', 'X' },
				{ ' ', ' ', 'X', 'X', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', 'X' }, { ' ', ' ', ' ', 'X', ' ', 'X' },
				{ 'X', 'X', ' ', ' ', ' ', ' ' } };

		Maze mymaze = new Maze(maze);
		mymaze.canExit(0, 0);
		mymaze.printMaze();

		MazeCreator mc = new MazeCreator(6);
		mc.setTitle("MazeBuilder");
		mc.setSize(mc.mazeSize * size, mc.mazeSize * size + mc.buttonSize);
		mc.setVisible(true);
		mc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
