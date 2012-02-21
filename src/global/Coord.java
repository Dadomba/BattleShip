package global;

public class Coord {
	protected int x;
	protected int y;
	
	public Coord(int pos_x, int pos_y){
		x = pos_x;
		y = pos_y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
