package battleship;

import global.Constant;

import javax.swing.JFrame;

public class Screen extends JFrame{

	private static final long serialVersionUID = -4385410151814338486L;

	public Screen()
	{
		pack();
	}
	
	public static void displayPlayerGridInConsole(Player player){
		Grid playerGrid = player.getPlayerGrid();
		
		for(int i = 0; i<Constant.XMAX+1; i++){
			for(int j = 0; j<Constant.YMAX+1; j++){
				System.out.print(playerGrid.getCaseStatus(i, j));
			}
			System.out.println();
		}
	}
	
}
