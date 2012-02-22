package entities;

import global.Constant;
import global.Coord;
import global.RandomSynchronized;

public class Cruiser extends Ship {
	
	public Cruiser(int x, int y, int orientation) throws Exception {
		super(x, y, orientation);
		this.size = 3;
	}

	public Cruiser(Coord coord, int orientation) throws Exception {
		super(coord.getX(), coord.getY(), orientation);
		this.size = 3;
	}

	public Cruiser() throws Exception {
		super(0, 0, RandomSynchronized.nextInt(2));
		this.size = 3;
		setCoord(
				RandomSynchronized.nextInt(Constant.XMAX + 1
						- ((orientation == Ship.HORIZONTAL) ? size - 1 : 0)),
				RandomSynchronized.nextInt(Constant.YMAX + 1
						- ((orientation == Ship.VERTICAL) ? size - 1 : 0)));
	}
}
