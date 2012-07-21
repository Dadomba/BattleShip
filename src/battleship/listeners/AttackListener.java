package battleship.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import battleship.global.Coord;
import battleship.Game;
import battleship.Grid;
import battleship.Screen;

public class AttackListener implements ActionListener{

	Grid grid = null;
	Coord coord = null;
	
	public AttackListener(Grid grid, Coord coord)
	{
		this.grid = grid;
		this.coord = coord;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(grid == null)
			return;
	
		Game g = Game.getInstance();
		if(!g.canPlay())
			return;
		g.sendMessage("attack:"+coord.getX()+","+coord.getY());
		Screen.getInstance().disableJButtonOpponentGrid(coord.getX(), coord.getY());
		g.endTurn();
	}
}