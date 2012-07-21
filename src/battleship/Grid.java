package battleship;

import java.util.ArrayList;

import battleship.entities.Ship;
import battleship.global.Constant;
import battleship.global.Coord;

public class Grid {
	public static final int FREE = 0;
	public static final int BUSY = 1;
	public static final int MISSED = 2;
	public static final int TOUCHED = 3;
	public static final int SINK = 4;
	public static final int UNKNOWN = 9;

	public static final int BLANK_GRID = 0;
	public static final int UNKNOWN_GRID = 9;

	private int[][] boxStatus = new int[Constant.XMAX + 1][Constant.YMAX + 1];
	
	private ArrayList<Ship> shipList = new ArrayList<Ship>();
	private int maximumShips = Constant.AIRCRAFTMAX + Constant.BATTLESHIPMAX
			+ Constant.CRUISERMAX + Constant.DESTROYERMAX
			+ Constant.SUBMARINEMAX;

	public Grid(int gridInitialStatus) throws Exception {
		for (int i = Constant.XMIN; i <= Constant.XMAX; i++)
			for (int j = Constant.YMIN; j <= Constant.YMAX; j++)
			{
				if (gridInitialStatus == Grid.BLANK_GRID) {
					boxStatus[i][j] = FREE;
				} else if (gridInitialStatus == Grid.UNKNOWN_GRID) {
					boxStatus[i][j] = UNKNOWN;
				} else {
					throw new Exception(
							"Initial Status of the grid is incorrect.");
				}
			}
	}

	public int getBoxStatus(int i, int j) 
	{
		return boxStatus[i][j];
	}

	public void setBoxStatus(int i, int j, int valeur) {
		if (valeur > -1 && valeur < 5)
			boxStatus[i][j] = valeur;
		Game.getInstance().refresh();
	}

	public void placeShip(Ship ship) throws Exception {
		if (shipList.size() < maximumShips) {
			boolean place_free = true;
			int x_err = -1;
			int y_err = -1;
			int orientation = ship.getOrientation();
			for (int i = 0; i < ship.getSize(); i++) {
				int Status = boxStatus[ship.getCoord().getX()
						+ ((orientation == Ship.HORIZONTAL) ? i : 0)][ship
						.getCoord().getY()
						+ ((orientation == Ship.VERTICAL) ? i : 0)];
				if (Status != Grid.FREE) {
					place_free = false;
					x_err = ship.getCoord().getX()
							+ ((orientation == Ship.HORIZONTAL) ? i : 0);
					y_err = ship.getCoord().getY()
							+ ((orientation == Ship.VERTICAL) ? i : 0);
					break;
				}
			}
			if (!place_free) {
				throw new Exception(
						"The place from "
								+ ship.getCoord().getX()
								+ ","
								+ ship.getCoord().getY()
								+ " to "
								+ (ship.getCoord().getX() + ((orientation == Ship.HORIZONTAL) ? ship
										.getSize() - 1 : 0))
								+ ","
								+ (ship.getCoord().getY() + ((orientation == Ship.VERTICAL) ? ship
										.getSize() - 1 : 0))
								+ " is not free !\nCollision at: " + x_err
								+ "," + y_err
								+ ". The ship hasn\'t been added to the Grid.");
			} else {
				for (int i = 0; i < ship.getSize(); i++) {
					boxStatus[ship.getCoord().getX()
							+ ((orientation == Ship.HORIZONTAL) ? i : 0)][ship
							.getCoord().getY()
							+ ((orientation == Ship.VERTICAL) ? i : 0)] = Grid.BUSY;
				}
				shipList.add(ship);
			}
		} else {
			throw new Exception("Maximum of " + maximumShips
					+ " Ships on the grid reached.");
		}

	}

	public void attack(Coord coord) {
		int boxStatus = getBoxStatus(coord.getX(), coord.getY());
		String networkMessage = "status:"+coord.getX()+","+coord.getY()+",";
		if(boxStatus == Grid.FREE)
		{
			setBoxStatus(coord.getX(), coord.getY(), MISSED);
			Game.getInstance().sendMessage(networkMessage+MISSED);
		}
		else if(boxStatus == Grid.BUSY)
		{
			setBoxStatus(coord.getX(), coord.getY(), TOUCHED);
			Game.getInstance().sendMessage(networkMessage+TOUCHED);
			for(Ship ship : shipList)
			{
				if (ship.standOn(coord))
				{
					int life = ship.hurt();
					if(life == 0)
					{
						Coord c = ship.getCoord();
						int orientation = ship.getOrientation();
						int size = ship.getSize();
						for(int i = 0; i < size; i++)
						{
							if(orientation == Ship.HORIZONTAL)
							{
								setBoxStatus(c.getX()+i, c.getY(), SINK);
								Game.getInstance().sendMessage("status:"+(c.getX()+i)+","+c.getY()+","+SINK);
							}
							else
							{
								setBoxStatus(c.getX(), c.getY()+i, SINK);
								Game.getInstance().sendMessage("status:"+c.getX()+","+(c.getY()+i)+","+SINK);
							}
						}
						Game.getInstance().sendMessage("sink:"+shipList.indexOf(ship));
					}
				}
			}
		}
		Game.getInstance().refresh();
	}

	public boolean hasMoreShip() {
		int res = 0;
		for(Ship ship : shipList)
			if(!ship.isDestroyed())
				res++;
		return res != 0;
	}

	public void sinkShip(int shipID) {
		shipList.get(shipID).sink();
		Game.getInstance().refresh();
	}
}
