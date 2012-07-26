package battleship.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;

import battleship.Constant;
import battleship.Coord;
import battleship.core.Game;
import battleship.core.Grid;
import battleship.listeners.AttackListener;

public class JButtonOpponentGrid extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3386045377554987900L;

	Grid grid = null;
	Coord coord = null;

	public JButtonOpponentGrid(int x, int y) {
		this(new Coord(x, y));
	}

	public JButtonOpponentGrid(Coord coord) {
		super();
		this.coord = coord;
	}

	public void loadGrid() {
		try {
			grid = Game.getInstance().getOpponent().getPlayerGrid();
			addActionListener(new AttackListener(grid, coord));
		} catch (Exception e) {
			System.err.println("Error loading opponent grid !");
		}
	}

	public int getStatus() {
		if (grid == null)
			return Grid.UNKNOWN;
		return grid.getBoxStatus(coord.getX(), coord.getY());
	}

	public Coord getCoord() {
		if (coord == null)
			return null;
		return new Coord(coord);
	}

	@Override
	public void paint(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);// on affine les traits
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC);

		int height = getSize().height;
		int width = getSize().width;

		g.clearRect(0, 0, width, height);
		g.setColor(Constant.GRID_COLOR);
		g.drawRect(0, 0, width - 1, height - 1);

		int size = (height > width) ? width : height;
		size = size / 3;

		g.setColor(Constant.BORDER_SHIP_COLOR);

		int status = getStatus();

		if (status == Grid.MISSED)
			draw(g, size, Constant.MISSED_BOX_COLOR);
		else if (status == Grid.TOUCHED)
			draw(g, size, Constant.TOUCHED_BOX_COLOR);
		else if (status == Grid.SINK)
			draw(g, size, Constant.SUNK_BOX_COLOR);
	}

	private void draw(Graphics2D g, int size, Color c) {
		Color tmp = g.getColor();
		g.fillOval(size, size, size, size);
		g.setColor(c);
		g.fillOval(size + 1, size + 1, size - 2, size - 2);
		g.setColor(tmp);
	}
}
