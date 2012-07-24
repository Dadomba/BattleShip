package battleship.frames;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import battleship.Grid;
import battleship.entities.*;
import battleship.global.Constant;
import battleship.global.Coord;

public class JPanelGridPlacement extends JPanel implements MouseListener, MouseMotionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3133409935406150766L;

	private CreateGridFrame createGridFrame = null;
	private int[] shipPlaced = new int[Constant.SHIP_AMOUNT.length];
	
	private final int scale = 3;
	
	private Dimension size = new Dimension(scale*100+11,scale*100+11);
	
	private Ship currentShipToPlace = null;
	
	public JPanelGridPlacement(CreateGridFrame createGridFrame)
	{
		this.createGridFrame = createGridFrame;
		
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
		
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void paint(Graphics graph)
	{
		Graphics2D g = (Graphics2D) graph;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//on affine les traits
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		super.paint(g);
		g.setColor(Constant.GRID_COLOR);
		for (int i = 0; i < 11; i++)
		{
			g.drawLine(0, i*scale*10+i, size.width, i*scale*10+i);
			g.drawLine(i*scale*10+i, 0, i*scale*10+i, size.height);
		}
		
		if(createGridFrame.getGrid() == null)
			return;
		
		for(int i = 0; i <= Constant.XMAX; i++)
			for(int j = 0; j <= Constant.YMAX; j++)
				if(createGridFrame.getGrid().getBoxStatus(i, j) == Grid.BUSY)
					drawPoint(g,i,j);
		
		if(currentShipToPlace != null)
		{
			int x = currentShipToPlace.getCoord().getX();
			int y = currentShipToPlace.getCoord().getY();
			int length = currentShipToPlace.getSize();
			int orientation = currentShipToPlace.getOrientation();
			for(int i = 0; i < length; i++)
			{
				if(orientation == Ship.HORIZONTAL)
				{
					if(createGridFrame.getGrid().getBoxStatus(x+i,y) == Grid.BUSY)
						fillRectangle(g,x+i,y);
					else
						drawPoint(g, x+i, y);
				}
				else 
				{
					if(createGridFrame.getGrid().getBoxStatus(x,y+i) == Grid.BUSY)
						fillRectangle(g,x,y+i);
					else
						drawPoint(g, x, y+i);
				}
			}
		}
	}
	
	public void fillRectangle(Graphics2D g, int i, int j)
	{
		int x = (i*scale*10+i)+1;
		int y = (j*scale*10+j)+1;
		
		Color tmp = g.getColor();
		g.setColor(Color.RED);
		g.fillRect(x, y, scale*10, scale*10);
		g.setColor(tmp);
	}	
	
	public void drawPoint(Graphics2D g, int i, int j)
	{
		int size = scale*10/3;
		Color tmp = g.getColor();
		
		int x = (i*scale*10+i) + (scale*10-size)/2;
		int y = (j*scale*10+j) + (scale*10-size)/2;
		
		g.setColor(Constant.BORDER_SHIP_COLOR);
		g.fillOval(x, y, size, size);
		g.setColor(Constant.UNTOUCHED_SHIP);
		g.fillOval(x+1, y+1, size-2, size-2);
		g.setColor(tmp);
	}

	public int getNbShipPlaced(int i) {
		return shipPlaced[i];
	}

	public boolean allShipsArePlaced() {
		for(int i = 0; i < shipPlaced.length; i++)
			if(shipPlaced[i] != Constant.SHIP_AMOUNT[i])
				return false;
		return true;
	}

	public void tryPlaceShip(int idShip) {
		try
		{
			String[] orientations = {"Horizontal","Vertical"};
			
			int orientation;
			if(idShip != 4)
				orientation = JOptionPane.showOptionDialog(this, "Orientation ? ", "Orientation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, orientations, 0);
			else
				orientation = 0;
			
			if(idShip == 0)
					currentShipToPlace = new Aircraft(new Coord(0,0),orientation);
			else if(idShip == 1)
				currentShipToPlace = new Battleship(new Coord(0,0),orientation);
			else if(idShip == 2)
				currentShipToPlace = new Cruiser(new Coord(0,0),orientation);
			else if(idShip == 3)
				currentShipToPlace = new Destroyer(new Coord(0,0),orientation);
			else if(idShip == 4)
				currentShipToPlace = new Submarine(new Coord(0,0),orientation);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isCorrectlyPositioned()
	{
		int x = currentShipToPlace.getCoord().getX();
		int y = currentShipToPlace.getCoord().getY();
		int size = currentShipToPlace.getSize();
		int orientation = currentShipToPlace.getOrientation();
		
		for(int i = 0; i < size; i++)
		{
			if(orientation == Ship.HORIZONTAL)
			{
				if(createGridFrame.getGrid().getBoxStatus(x+i, y) == Grid.BUSY)
					return false;
			}
			else
			{
				if(createGridFrame.getGrid().getBoxStatus(x, y+i) == Grid.BUSY)
					return false;
			}
		}
		return true;	
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(currentShipToPlace != null && isCorrectlyPositioned())
		{
			try {
				shipPlaced[Math.abs(currentShipToPlace.getSize()-Constant.MAXSHIPSIZE)]++;
				createGridFrame.placeShip(currentShipToPlace);
				currentShipToPlace = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(currentShipToPlace == null)
		{
			Point position = arg0.getPoint();
			int i = position.x / (scale*10+1);
			int j = position.y / (scale*10+1);
			int index = createGridFrame.getGrid().tryDeleteShip(i,j);
			if(index > -1)
			{
				shipPlaced[index]--;
				createGridFrame.refresh();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		if(currentShipToPlace == null)
			createGridFrame.setCursor(Constant.DELETE_CURSOR);
		else
			createGridFrame.setCursor(Constant.PLACEMENT_CURSOR);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		createGridFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {

		if(currentShipToPlace != null)
		{
			createGridFrame.setCursor(Constant.PLACEMENT_CURSOR);
			Point position = arg0.getPoint();
			int i = position.x / (scale*10+1);
			int j = position.y / (scale*10+1);
		
			int shipSize = currentShipToPlace.getSize();
			
			if(currentShipToPlace.getOrientation() == Ship.HORIZONTAL)
			{
				if(i + shipSize > Constant.XMAX)
					i = Constant.XMAX - shipSize + 1;
				if(j > Constant.YMAX)
					j = Constant.YMAX;
			}
			else
			{
				if(i > Constant.XMAX)
					i = Constant.XMAX;
				if(j + shipSize > Constant.YMAX)
					j = Constant.YMAX - shipSize + 1 ;
			}
			currentShipToPlace.setCoord(new Coord(i,j));
			
			createGridFrame.refresh();
		}
		else
		{
			createGridFrame.setCursor(Constant.DELETE_CURSOR);
		}
	}

	public void setCurrentShip(Ship ship) {
		currentShipToPlace = ship;
		createGridFrame.repaint();
	}

}
