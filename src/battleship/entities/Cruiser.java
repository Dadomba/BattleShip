package battleship.entities;

import battleship.global.Constant;
import battleship.global.Coord;
import battleship.global.RandomSynchronized;

public class Cruiser extends Ship {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1924411880911219111L;

	public Cruiser(int x, int y, int orientation) throws Exception {
		super(x, y, orientation, Constant.CRUISERSIZE);
	}

	public Cruiser(Coord coord, int orientation) throws Exception {
		super(coord.getX(), coord.getY(), orientation, Constant.CRUISERSIZE);
	}

	public Cruiser() throws Exception {
		super(0, 0, RandomSynchronized.nextInt(2), Constant.CRUISERSIZE);
		setCoord(
				RandomSynchronized.nextInt(Constant.XMAX + 1
						- ((orientation == Ship.HORIZONTAL) ? size - 1 : 0)),
				RandomSynchronized.nextInt(Constant.YMAX + 1
						- ((orientation == Ship.VERTICAL) ? size - 1 : 0)));
	}
}
