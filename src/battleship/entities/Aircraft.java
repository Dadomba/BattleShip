package battleship.entities;

import battleship.global.Constant;
import battleship.global.Coord;
import battleship.global.RandomSynchronized;

public class Aircraft extends Ship {

	public Aircraft(int x, int y, int orientation) throws Exception {
		super(x, y, orientation,Constant.AIRCRAFTSIZE);
	}

	public Aircraft(Coord coord, int orientation) throws Exception {
		super(coord.getX(), coord.getY(), orientation,Constant.AIRCRAFTSIZE);
	}

	public Aircraft() throws Exception {
		super(0, 0, RandomSynchronized.nextInt(2),Constant.AIRCRAFTSIZE);
		setCoord(
				RandomSynchronized.nextInt(Constant.XMAX + 1
						- ((orientation == Ship.HORIZONTAL) ? size - 1 : 0)),
				RandomSynchronized.nextInt(Constant.YMAX + 1
						- ((orientation == Ship.VERTICAL) ? size - 1 : 0)));
	}
}
