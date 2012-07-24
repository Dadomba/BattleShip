package battleship.entities;

import battleship.global.Constant;
import battleship.global.Coord;
import battleship.global.RandomSynchronized;

public class Destroyer extends Ship {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3883905339027483948L;

	public Destroyer(int x, int y, int orientation) throws Exception {
		super(x, y, orientation, Constant.DESTROYERSIZE);
	}

	public Destroyer(Coord coord, int orientation) throws Exception {
		super(coord.getX(), coord.getY(), orientation, Constant.DESTROYERSIZE);
	}

	public Destroyer() throws Exception {
		super(0, 0, RandomSynchronized.nextInt(2), Constant.DESTROYERSIZE);
		setCoord(
				RandomSynchronized.nextInt(Constant.XMAX + 1
						- ((orientation == Ship.HORIZONTAL) ? size - 1 : 0)),
				RandomSynchronized.nextInt(Constant.YMAX + 1
						- ((orientation == Ship.VERTICAL) ? size - 1 : 0)));
	}
}
