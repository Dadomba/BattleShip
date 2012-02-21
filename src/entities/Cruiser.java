package entities;

import global.Constant;
import global.Coord;
import global.RandomSynchronized;

public class Cruiser extends Ship {
	protected int size = 3;
	
	public Cruiser(int x, int y, int orientation) throws Exception
	{
		super(x,y,orientation);
	}
	
	public Cruiser(Coord coord, int orientation) throws Exception
	{
		super(coord.getX(),coord.getY(),orientation);
	}
	
	public Cruiser() throws Exception
	{
		super(RandomSynchronized.nextInt(Constant.XMAX+1),RandomSynchronized.nextInt(Constant.YMAX+1),Ship.HORIZONTAL);
	}
}
