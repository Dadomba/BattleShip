package battleship.entities;

import java.io.Serializable;
import java.util.Observable;

import battleship.Constant;
import battleship.Coord;

public abstract class Ship extends Observable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4541497791004110511L;

	public final static int HORIZONTAL = 0;
	public final static int VERTICAL = 1;

	protected int size;
	protected int life;
	protected Coord coord;
	protected int orientation = HORIZONTAL;
	
	protected Ship(int x, int y, int orientation, int size) throws Exception {
		this.orientation = orientation;
		this.size = size;
		this.life = size;
		
		if (x < Constant.XMIN
				|| x > Constant.XMAX
						- ((orientation == HORIZONTAL) ? size - 1 : 0)
				|| y < Constant.YMIN
				|| y > Constant.YMAX
						- ((orientation == VERTICAL) ? size - 1 : 0)) {
			throw new Exception("Error while constructing a Ship: X (" + x
					+ ") or Y (" + y + ") OUT OF Range !!!");
		} else {
			this.coord = new Coord(x, y);
		}
	}

	protected Ship(Coord coord, int orientation, int size) throws Exception {
		this(coord.getX(), coord.getY(), orientation, size);
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		if (size > 0 || size <= Constant.MAXSHIPSIZE)
			this.size = size;
	}

	public boolean isDestroyed() {
		return life == 0;
	}

	public Coord getCoord() {
		return coord;
	}

	protected void setCoord(int x, int y) {
		if (x < Constant.XMIN
				|| x > Constant.XMAX
						- ((orientation == HORIZONTAL) ? size - 1 : 0)
				|| y < Constant.YMIN
				|| y > Constant.YMAX
						- ((orientation == VERTICAL) ? size - 1 : 0)) {
			return;// on ne change pas les coordonnées
		}
		this.coord = new Coord(x, y);
	}

	public int getOrientation() {
		return orientation;
	}

	public boolean standOn(Coord coord) {
		int x = coord.getX();
		int y = coord.getY();
		int startx = this.coord.getX();
		int starty = this.coord.getY();

		if (orientation == HORIZONTAL) {
			if (((x - startx) < size) && startx <= x && starty == y)
				return true;
			else
				return false;
		} else {
			if (((y - starty) < size) && starty <= y && startx == x)
				return true;
			else
				return false;
		}
	}

	public int hurt() {
		life--;
		fireUpdate();
		return life;
	}

	public void sink() {
		life = 0;
		fireUpdate();
	}

	public int getLife() {
		return life;
	}

	public void fireUpdate() {
		setChanged();
		notifyObservers(life);
	}

	public void setCoord(Coord coord) {
		this.coord = coord;
	}
}
