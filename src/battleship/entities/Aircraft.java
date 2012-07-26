package battleship.entities;

import battleship.Constant;
import battleship.Coord;
import battleship.RandomSynchronized;

public class Aircraft extends Ship {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2807674951585194165L;

	public Aircraft(int x, int y, int orientation) throws Exception {
		super(x, y, orientation, Constant.AIRCRAFTSIZE);
	}

	public Aircraft(Coord coord, int orientation) throws Exception {
		super(coord.getX(), coord.getY(), orientation, Constant.AIRCRAFTSIZE);
	}

	public Aircraft() throws Exception {
		super(0, 0, RandomSynchronized.nextInt(2), Constant.AIRCRAFTSIZE);
		setCoord(
				RandomSynchronized.nextInt(Constant.XMAX + 1
						- ((orientation == Ship.HORIZONTAL) ? size - 1 : 0)),
				RandomSynchronized.nextInt(Constant.YMAX + 1
						- ((orientation == Ship.VERTICAL) ? size - 1 : 0)));
	}
}
