package battleship;

import entities.Ship;
import global.Constant;

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
	private int shipOnGrid = 0;
	private int maximumShips = Constant.AIRCRAFTMAX + Constant.BATTLESHIPMAX
			+ Constant.CRUISERMAX + Constant.DESTROYERMAX
			+ Constant.SUBMARINEMAX;

	public Grid(int gridInitialStatus) throws Exception {
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				if (gridInitialStatus == Grid.BLANK_GRID) {
					boxStatus[i][j] = FREE;
				} else if (gridInitialStatus == Grid.UNKNOWN_GRID) {
					boxStatus[i][j] = UNKNOWN;
				} else {
					throw new Exception(
							"Initial Status of the grid is incorrect.");
				}
	}

	public int getBoxStatus(int i, int j) {
		return boxStatus[i][j];
	}

	public void setBoxStatus(int i, int j, int valeur) {
		if (valeur > -1 && valeur < 5)
			boxStatus[i][j] = valeur;
	}

	public void placeShip(Ship ship) throws Exception {
		if (shipOnGrid < maximumShips) {
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
				shipOnGrid++;
			}
		} else {
			throw new Exception("Maximum of " + maximumShips
					+ " Ships on the grid reached.");
		}

	}
}
