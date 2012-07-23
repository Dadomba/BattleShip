package battleship;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

import battleship.global.Constant;
import battleship.global.Coord;

public class JButtonPlayerGrid extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1722134626493668726L;
	Grid grid = null;
	Coord coord = null;
	
	public JButtonPlayerGrid(int x, int y)
	{
		this(new Coord(x,y));
	}
	
	public JButtonPlayerGrid(Coord coord)
	{
		super();
		grid = Game.getInstance().getPlayer().getPlayerGrid();
		this.coord = coord;
		setEnabled(false);
	}
	
	public int getStatus()
	{
		if(grid == null)
			return Grid.UNKNOWN;
		return grid.getBoxStatus(coord.getX(), coord.getY());
	}
	
	public Coord getCoord()
	{
		if(coord == null)
			return null;
		return new Coord(coord);
	}
	
	@Override
	public void paint(Graphics graphics)
	{
		Graphics2D g = (Graphics2D) graphics;
		int height = getSize().height;
		int width = getSize().width;
	
		g.clearRect(0, 0, width, height);
		g.setColor(Constant.GRID_COLOR);
		g.drawRect(0, 0, width-1, height-1);
		
		int size = (height>width)?width:height;
		size = size/3;
		
		int status = getStatus();
		
		if(status == Grid.BUSY)
		{
			g.setColor(Constant.UNTOUCHED_SHIP);
			g.fillOval(size, size, size, size);
		}
		else if(status == Grid.MISSED)
		{
			g.setColor(Constant.MISSED_BOX);
			g.fillOval(size, size, size, size);
		}
		else if(status == Grid.TOUCHED)
		{
			g.setColor(Constant.TOUCHED_BOX);
			g.fillOval(size, size, size, size);
		}
		else if(status == Grid.SINK)
		{
			g.setColor(Constant.SUNK_BOX);
			g.fillOval(size, size, size, size);
		}
	}
}
