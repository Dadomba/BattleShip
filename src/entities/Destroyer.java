package entities;

import global.Constant;
import global.Coord;
import global.RandomSynchronized;

public class Destroyer extends Ship {
	
	public Destroyer(int x, int y, int orientation) throws Exception {
		super(x, y, orientation);
		this.size = 2;
	}

	public Destroyer(Coord coord, int orientation) throws Exception {
		super(coord.getX(), coord.getY(), orientation);
		this.size = 2;
	}

	public Destroyer() throws Exception {
		super(0, 0, RandomSynchronized.nextInt(2));
		this.size = 2;
		setCoord(
				RandomSynchronized.nextInt(Constant.XMAX + 1
						- ((orientation == Ship.HORIZONTAL) ? size - 1 : 0)),
				RandomSynchronized.nextInt(Constant.YMAX + 1
						- ((orientation == Ship.VERTICAL) ? size - 1 : 0)));
	}
}
