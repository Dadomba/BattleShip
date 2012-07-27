package battleship.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;

import battleship.Constant;
import battleship.Coord;
import battleship.core.Grid;

public class JButtonGrid extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3386045377554987900L;

	Grid grid = null;
	protected Coord coord = null;

	protected int height = getSize().height;
	protected int width = getSize().width;
	protected int size = (height > width) ? width : height;

	public JButtonGrid(int x, int y) {
		this(new Coord(x, y));
	}

	public JButtonGrid(Coord coord) {
		this.coord = coord;
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

	public void loadGrid() {

	}

	@Override
	public void paint(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);// on affine les traits
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC);

		height = getSize().height;
		width = getSize().width;
		size = (height > width) ? width : height;
		size = size / 3;

		g.clearRect(0, 0, width, height);
		g.setColor(Constant.GRID_COLOR);
		g.drawRect(0, 0, width - 1, height - 1);
	}

	protected void draw(Graphics g, int size, Color c) {
		Color tmp = g.getColor();
		g.fillOval(size + 1, size + 1, size, size);
		g.setColor(c);
		g.fillOval(size + 2, size + 2, size - 2, size - 2);
		g.setColor(tmp);
	}

}