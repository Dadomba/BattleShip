package battleship;

import javax.swing.JFrame;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Player vince = new Player("Vince");
		Player josh = new Player("Josh");
		
		Screen screen = new Screen();
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setVisible(true);
		screen.setLocationRelativeTo(null);// Centrage
	}
}