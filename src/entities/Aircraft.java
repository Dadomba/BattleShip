package entities;

import global.Constant;
import global.Coord;
import global.RandomSynchronized;

public class Aircraft extends Ship {
	protected int size = 5;
	
	public Aircraft(int x, int y, int orientation) throws Exception
	{
		super(x,y,orientation);
	}
	
	public Aircraft(Coord coord, int orientation) throws Exception
	{
		super(coord.getX(),coord.getY(),orientation);
	}
	
	public Aircraft() throws Exception
	{
		super(RandomSynchronized.nextInt(Constant.XMAX+1),RandomSynchronized.nextInt(Constant.YMAX+1),Ship.HORIZONTAL);
	}
}
