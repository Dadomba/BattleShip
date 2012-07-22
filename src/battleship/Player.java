package battleship;

import battleship.entities.Ship;


public class Player {
	
	private Grid playerGrid;

	private String name;

	public Player(String name, boolean activated) throws Exception {
		this(name,new Grid(Grid.BLANK_GRID));
	}
	
	public Player(String name, Grid grid)
	{
		this.name = name;
		try {
			playerGrid = grid;
		} catch (Exception e) {
			System.err.println("Grid instantiation error (" + this.name
					+ ") : ");
			e.printStackTrace();
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public void addShipOnUserGrid(Ship ship) throws Exception {
		try {
			playerGrid.placeShip(ship);
		} catch (Exception e) {
			throw new Exception("Ship Adding error (" + this.name + ") : \n" + e.getMessage());

		}
	}

	public Grid getPlayerGrid() {
		return playerGrid;
	}
}
