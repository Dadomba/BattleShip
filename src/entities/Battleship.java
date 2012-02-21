package entities;

import global.Constant;
import global.Coord;
import global.RandomSynchronized;

public class Battleship extends Ship {
	protected int size = 4;
	
	public Battleship(int x, int y, int orientation) throws Exception
	{
		super(x,y,orientation);
	}
	
	public Battleship(Coord coord, int orientation) throws Exception
	{
		super(coord.getX(),coord.getY(),orientation);
	}
	
	public Battleship() throws Exception
	{
		super(RandomSynchronized.nextInt(Constant.XMAX+1),RandomSynchronized.nextInt(Constant.YMAX+1),Ship.HORIZONTAL);
	}
}
