package battleship.entities;

import battleship.global.Constant;
import battleship.global.Coord;
import battleship.global.RandomSynchronized;

public class Submarine extends Ship {
	
	public Submarine(int x, int y, int orientation) throws Exception {
		super(x, y, orientation, Constant.SUBMARINESIZE);
	}

	public Submarine(Coord coord, int orientation) throws Exception {
		super(coord.getX(), coord.getY(), orientation, Constant.SUBMARINESIZE);
	}

	public Submarine() throws Exception {
		super(0, 0, RandomSynchronized.nextInt(2), Constant.SUBMARINESIZE);
		setCoord(
				RandomSynchronized.nextInt(Constant.XMAX + 1
						- ((orientation == Ship.HORIZONTAL) ? size - 1 : 0)),
				RandomSynchronized.nextInt(Constant.YMAX + 1
						- ((orientation == Ship.VERTICAL) ? size - 1 : 0)));
	}
}
