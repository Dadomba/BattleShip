package battleship.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import battleship.Constant;
import battleship.Coord;
import battleship.core.Game;
import battleship.core.Grid;
import battleship.entities.Ship;

public class JButtonPlayerGrid extends JButtonGrid {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1722134626493668726L;

	public JButtonPlayerGrid(int x, int y) {
		super(x, y);
	}

	public JButtonPlayerGrid(Coord coord) {
		super(coord);
		setEnabled(false);
	}

	@Override
	public void loadGrid() {
		try {
			grid = Game.getInstance().getPlayer().getPlayerGrid();
		} catch (Exception e) {
			System.err.println("Error loading player grid !");
		}
	}

	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);

		Graphics2D g = (Graphics2D) graphics;

		g.setColor(Constant.BORDER_SHIP_COLOR);

		int status = getStatus();

		if (status == Grid.BUSY || status == Grid.TOUCHED || status == Grid.SUNK)
			// draw(g, size, Constant.UNTOUCHED_SHIP_BOX_COLOR);
			drawShip(g);

		if (status == Grid.MISSED)
			draw(g, size, Constant.MISSED_BOX_COLOR);
		else if (status == Grid.TOUCHED)
			draw(g, size, Constant.TOUCHED_BOX_COLOR);
		else if (status == Grid.SUNK)
			draw(g, size, Constant.SUNK_BOX_COLOR);

	}

	private void drawShip(Graphics2D g) {
		int line = -1;
		int column = -1;
		int orientation = -1;
		for (Ship s : grid.getShipList()) {
			if (s.standOn(coord)) {
				line = Constant.MAXSHIPSIZE - s.getSize();
				Coord shipSartCoord = s.getCoord();
				orientation = s.getOrientation();

				if (orientation == Ship.HORIZONTAL)
					column = coord.getX() - shipSartCoord.getX();
				else
					column = coord.getY() - shipSartCoord.getY();
			}
		}

		if (orientation == -1 || Constant.SHIPS_GRAPHICS_FILE == null)
			return;

		AffineTransform affineTransform = new AffineTransform();
		if (orientation == Ship.VERTICAL)
			affineTransform.rotate(Math.toRadians(90), width / 2, height / 2);
		AffineTransformOp affineTransformOp = new AffineTransformOp(
				affineTransform, AffineTransformOp.TYPE_BILINEAR);

		Image tmp = Constant.SHIPS_GRAPHICS_FILE.getSubimage(
				column * Constant.SHIPS_GRAPHICS_FILE_BOX_SIZE,
				line * Constant.SHIPS_GRAPHICS_FILE_BOX_SIZE,
				Constant.SHIPS_GRAPHICS_FILE_BOX_SIZE,
				Constant.SHIPS_GRAPHICS_FILE_BOX_SIZE).getScaledInstance(width,
				height, Image.SCALE_SMOOTH);
		// BufferedImage conversion for affineTransformOp.filter
		BufferedImage boxToDraw = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		boxToDraw.getGraphics().drawImage(tmp, 0, 0, null);

		g.drawImage(affineTransformOp.filter(boxToDraw, null), 0, 0, null);
	}
}
