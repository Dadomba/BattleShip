package entities;

import global.Constant;
import global.Coord;
import global.RandomSynchronized;

public class Destroyer extends Ship {
	protected int size = 2;
	
	public Destroyer(int x, int y, int orientation) throws Exception
	{
		super(x,y,orientation);
	}
	
	public Destroyer(Coord coord, int orientation) throws Exception
	{
		super(coord.getX(),coord.getY(),orientation);
	}
	
	public Destroyer() throws Exception
	{
		super(RandomSynchronized.nextInt(Constant.XMAX+1),RandomSynchronized.nextInt(Constant.YMAX+1),Ship.HORIZONTAL);
	}
}
