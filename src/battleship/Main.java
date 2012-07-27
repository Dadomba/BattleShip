package battleship;

import battleship.core.Game;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args){
		Game game = Game.getInstance();
		game.start();
	}
}
