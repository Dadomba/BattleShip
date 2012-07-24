package battleship;

import javax.swing.JOptionPane;

import battleship.frames.CreateGridFrame;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Game game = Game.getInstance();
		game.start();
		String name = JOptionPane.showInputDialog("Enter your name");
		if(name == null || name.equals(""))
			game.quit();
		else
			new CreateGridFrame("Create your grid", name);
	}

}
