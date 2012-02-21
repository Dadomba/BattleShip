package entities;

import global.Constant;
import global.Coord;

public abstract class Ship {

	public final static int HORIZONTAL = 0;
	public final static int VERTICAL = 1;

	protected int size;
	protected boolean destroyed;
	protected Coord coord;
	protected int orientation = HORIZONTAL;

	protected Ship(int x, int y, int orientation) throws Exception {
		this.destroyed = false;
		this.orientation = orientation;

		if (x < Constant.XMIN
				|| x > Constant.XMAX
						- ((orientation == HORIZONTAL) ? size + 1 : 0)
				|| y < Constant.YMIN
				|| y > Constant.YMAX
						- ((orientation == VERTICAL) ? size + 1 : 0)) {
			throw new Exception(
					"Error while constructing a Ship: X or Y OUT OF Range !!!");
		} else {
			this.coord = new Coord(x, y);
		}
	}

	protected Ship(Coord coord, int orientation) throws Exception {
		this.destroyed = false;
		this.orientation = orientation;

		if (coord.getX() < Constant.XMIN
				|| coord.getX() > Constant.XMAX
						- ((orientation == HORIZONTAL) ? size + 1 : 0)
				|| coord.getY() < Constant.YMIN
				|| coord.getY() > Constant.YMAX
						- ((orientation == VERTICAL) ? size + 1 : 0)) {
			throw new Exception(
					"Error while constructing a Ship: X or Y OUT OF Range !!!");
		} else {
			this.coord = new Coord(coord.getX(), coord.getY());
		}
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	public Coord getCoord() {
		return coord;
	}

	public int getOrientation() {
		return orientation;
	}

}
