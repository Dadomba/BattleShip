package battleship.entities;

import battleship.global.Constant;
import battleship.global.Coord;
import battleship.global.RandomSynchronized;

public class Battleship extends Ship {
	
	public Battleship(int x, int y, int orientation) throws Exception {
		super(x, y, orientation, Constant.BATTLESHIPSIZE);
	}

	public Battleship(Coord coord, int orientation) throws Exception {
		super(coord.getX(), coord.getY(), orientation, Constant.BATTLESHIPSIZE);
	}

	public Battleship() throws Exception {
		super(0, 0, RandomSynchronized.nextInt(2), Constant.BATTLESHIPSIZE);
		setCoord(
				RandomSynchronized.nextInt(Constant.XMAX + 1
						- ((orientation == Ship.HORIZONTAL) ? size - 1 : 0)),
				RandomSynchronized.nextInt(Constant.YMAX + 1
						- ((orientation == Ship.VERTICAL) ? size - 1 : 0)));
	}
}
