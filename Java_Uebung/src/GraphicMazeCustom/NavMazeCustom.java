package GraphicMazeCustom;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NavMazeCustom extends JFrame {

	private static final long serialVersionUID = 1L;
	Container gm;
	Graphic mazeDrawing;
	Maze maze;
	List<Point> solution;
	int stepOfSolution = 0;
	JButton next;
	JButton prev;
	JPanel controllButtons;
	JButton newMaze;

	private final static int size = 100;
	private final static int offset = 10;

	public void drawMaze() {
		mazeDrawing.clear();
		for (int row = 0; row < maze.size(); row++) {
			for (int col = 0; col < maze.size(); col++) {
				mazeDrawing.setColor(Color.BLACK);
				if (maze.getMaze().contains(new Point(row, col)))
					mazeDrawing.fillRect(col * size, row * size, size, size);
				else {
					mazeDrawing.drawRect(col * size, row * size, size, size);
				}
			}
		}
		drawSolution();
		mazeDrawing.redraw();
	}

	public void drawSolution() {
		mazeDrawing.setColor(Color.BLUE);
		for (Point p : solution) {
			if (solution.get(stepOfSolution).equals(p))
				mazeDrawing.fillOval(p.y * size + offset, p.x * size + offset, size - 2 * offset, size - 2 * offset);
		}

	}

	public NavMazeCustom(Maze m) {
		gm = getContentPane();
		maze = m;
		solution = maze.getSolution();
		stepOfSolution = solution.size() - 1;
		mazeDrawing = new Graphic(maze.size() * size, maze.size() * size);
		next = new JButton("vor");
		prev = new JButton("zurück");
		newMaze = new JButton("neues Maze");
		controllButtons = new JPanel();

		ActionListener n = e -> drawNext();
		ActionListener p = e -> drawPrev();
		next.addActionListener(n);
		prev.addActionListener(p);

		controllButtons.add(newMaze);
		controllButtons.add(prev);
		controllButtons.add(next);
		
		gm.add(mazeDrawing, BorderLayout.CENTER);
		gm.add(controllButtons, BorderLayout.SOUTH);

	}

	private void drawNext() {
		if (stepOfSolution > 0)
			stepOfSolution--;
		drawMaze();
	}

	private void drawPrev() {
		if (stepOfSolution < solution.size() - 1)
			stepOfSolution++;
		drawMaze();
	}

	public static void main(String[] args) {

	}
}
