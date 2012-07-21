package battleship.global;

public class Coord {
	protected int x;
	protected int y;

	public Coord(int pos_x, int pos_y) {
		x = pos_x;
		y = pos_y;
	}

	public Coord(Coord coord) {
		x = coord.getX();
		y = coord.getY();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		if (x >= Constant.XMIN && x <= Constant.XMAX)
			this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		if (y >= Constant.YMIN && y < Constant.YMAX)
			this.y = y;
	}
	
	public String toString()
	{
		return x+","+y; 
	}
}
