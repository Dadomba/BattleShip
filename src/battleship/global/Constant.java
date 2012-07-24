package battleship.global;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

public class Constant {

	//GRID SIZE
	public static final int XMAX = 9;
	public static final int XMIN = 0;
	public static final int YMAX = 9;
	public static final int YMIN = 0;

	
	
	
	
	//SHIP NUMBER
	public static final int AIRCRAFTMAX = 1;
	public static final int BATTLESHIPMAX = 1;
	public static final int CRUISERMAX = 1;
	public static final int DESTROYERMAX = 2;
	public static final int SUBMARINEMAX = 2;

	public static final int[] SHIP_AMOUNT = {AIRCRAFTMAX, BATTLESHIPMAX, CRUISERMAX, DESTROYERMAX, SUBMARINEMAX};
	
	
	//SHIP SIZE
	public static final int AIRCRAFTSIZE = 5;
	public static final int BATTLESHIPSIZE = 4;
	public static final int CRUISERSIZE = 3;
	public static final int DESTROYERSIZE= 2;
	public static final int SUBMARINESIZE = 1;
	
	public static final int MAXSHIPSIZE = 5;
	
	
	
	
	
	//NETWORK PARAMETERS
	public static final int DEFAULT_CONNECTION_PORT = 64321;
	
	public static final int LISTENING_PORT = 64322;
	public static final int SENDING_PORT = 64321;
	
	
	
	
	
	//FONT PARAMETERS
	public static final Font DEFAULT_MESSAGE_FONT = new Font("Comic sans ms", Font.BOLD,13);
	public static final Font DEFAULT_INFORMATION_FIELD_FONT = new Font("Comin sans ms",Font.BOLD,13);
	public static final Font DEFAULT_INFORMATION_FIELD_PLAYER_NAME = new Font("Comic Sans MS",Font.BOLD,15);
	
	
	
	
	
	
	//COLOR PARAMETERS
	public static final Color DEFAULT_INFORMATION_FIELD_PLAYER_NAME_COLOR = Color.DARK_GRAY;
	
	//INFORMATION PANEL LIFE COLOR
	public static final Color HIGH_LIFE_COLOR = Color.GREEN;
	public static final Color HIGH_LIFE_COLOR_BORDER = Color.BLACK;
	public static final Color AVERAGE_LIFE_COLOR = Color.BLUE;
	public static final Color AVERAGE_LIFE_COLOR_BORDER = Color.BLACK;
	public static final Color NO_MORE_LIFE_COLOR = Color.RED;
	public static final Color NO_MORE_LIFE_COLOR_BORDER = Color.BLACK;
	
	//GRID COLORS
	public static final Color UNTOUCHED_SHIP = Color.GREEN;
	public static final Color MISSED_BOX = Color.BLACK;
	public static final Color TOUCHED_BOX = Color.BLUE;
	public static final Color SUNK_BOX = Color.RED;
	public static final Color GRID_COLOR = Color.BLACK;
	public static final Color BORDER_SHIP_COLOR = Color.BLACK;
	
	
	
	
	
	//CURSORS
	public static final Cursor DELETE_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage("res/cursors/remove.png"), new Point(16,16), "Remove");
	public static final Cursor PLACEMENT_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage("res/cursors/hand.png"), new Point(16,8), "Hand");
	
	
	
	
	//FRAME ICON
	public static final Image DEFAULT_FRAME_ICON = Toolkit.getDefaultToolkit().getImage("res/img/icon.png");
}
