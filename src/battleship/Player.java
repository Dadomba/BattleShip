package battleship;

import java.util.ArrayList;

import entities.Aircraft;
import entities.Battleship;
import entities.Cruiser;
import entities.Destroyer;
import entities.Ship;
import entities.Submarine;

public class Player {
	private ArrayList<Ship> shipList = new ArrayList<Ship>();
	private Grid playerGrid;
	private Grid opponentGrid;

	private String name;

	public Player(String name) {
		this.name = name;
		try {
			playerGrid = new Grid(Grid.BLANK_GRID);
			opponentGrid = new Grid(Grid.UNKNOWN_GRID);
		} catch (Exception e) {
			System.err.println("Grid instantiation error (" + this.name
					+ ") : ");
			e.printStackTrace();
		}
	}

	public void addShipOnUserGrid(Ship ship) {
		try{
			playerGrid.placeShip(ship);
		} catch (Exception e) {
			System.err.println("Ship Adding error (" + this.name
					+ ") : ");
			e.printStackTrace();
		}
	}
	
	public Grid getPlayerGrid() {
		return playerGrid;
	}
}
