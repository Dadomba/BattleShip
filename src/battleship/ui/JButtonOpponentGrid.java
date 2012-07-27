package battleship.ui;



import java.awt.Graphics;
import java.awt.Graphics2D;

import battleship.Constant;
import battleship.Coord;
import battleship.core.Game;
import battleship.core.Grid;
import battleship.listeners.AttackListener;

public class JButtonOpponentGrid extends JButtonGrid {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1241753199789692799L;

	public JButtonOpponentGrid(int x, int y) {
		this(new Coord(x, y));
	}

	public JButtonOpponentGrid(Coord coord) {
		super(coord);
	}
	
	@Override
	public void loadGrid() {
		try {
			grid = Game.getInstance().getOpponent().getPlayerGrid();
			addActionListener(new AttackListener(grid, coord));
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
		
		if (status == Grid.MISSED)
			draw(g, size, Constant.MISSED_BOX_COLOR);
		else if (status == Grid.TOUCHED)
			draw(g, size, Constant.TOUCHED_BOX_COLOR);
		else if (status == Grid.SUNK)
			draw(g, size, Constant.SUNK_BOX_COLOR);
	}
}
