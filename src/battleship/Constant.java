package battleship;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Constant {

	// GRID SIZE
	public static final int XMAX = 9;
	public static final int XMIN = 0;
	public static final int YMAX = 9;
	public static final int YMIN = 0;

	// SHIP NUMBER
	public static final int AIRCRAFTMAX = 1;
	public static final int BATTLESHIPMAX = 1;
	public static final int CRUISERMAX = 1;
	public static final int DESTROYERMAX = 2;
	public static final int SUBMARINEMAX = 2;

	public static final int[] SHIP_AMOUNT = { AIRCRAFTMAX, BATTLESHIPMAX, CRUISERMAX, DESTROYERMAX, SUBMARINEMAX };

	// SHIP SIZE
	public static final int AIRCRAFTSIZE = 5;
	public static final int BATTLESHIPSIZE = 4;
	public static final int CRUISERSIZE = 3;
	public static final int DESTROYERSIZE = 2;
	public static final int SUBMARINESIZE = 1;

	public static final int MAXSHIPSIZE = 5;

	// NETWORK PARAMETERS
	public static final int DEFAULT_CONNECTION_PORT = 64321;

	public static final int LISTENING_PORT = 64322;
	public static final int SENDING_PORT = 64321;

	// FONT PARAMETERS
	public static final Font DEFAULT_MESSAGE_SEPARATOR_FONT = new Font("Comic sans ms", Font.BOLD, 13);
	public static final Font DEFAULT_INFORMATION_FIELD_FONT = new Font("Comin sans ms", Font.BOLD, 13);
	public static final Font DEFAULT_INFORMATION_FIELD_PLAYER_NAME_FONT = new Font("Comic Sans MS", Font.BOLD, 15);

	// COLOR PARAMETERS
	public static final Color DEFAULT_INFORMATION_FIELD_PLAYER_NAME_COLOR = Color.DARK_GRAY;
	public static final Color GREEN = new Color(0x0D, 0x88, 0x00);
	public static final Color CLEAR_GREEN = Color.GREEN;
	public static final Color BLUE = new Color(0x46, 0x71, 0xd5);
	public static final Color CLEAR_BLUE = Color.BLUE;
	public static final Color RED = new Color(0xA6, 0x00, 0x00);
	public static final Color CLEAR_RED = Color.RED;

	// INFORMATION PANEL LIFE COLOR
	public static final Color HIGH_LIFE_COLOR = GREEN;
	public static final Color HIGH_LIFE_BORDER_COLOR = Color.BLACK;
	public static final Color AVERAGE_LIFE_COLOR = BLUE;
	public static final Color AVERAGE_LIFE_BORDER_COLOR = Color.BLACK;
	public static final Color NO_MORE_LIFE_COLOR = RED;
	public static final Color NO_MORE_LIFE_COLOR_BORDER = Color.BLACK;

	// MESSAGE SEPARATOR COLOR
	public static final Color STANDARD_TEXT_COLOR = Color.BLACK;
	public static final Color MISSED_TEXT_COLOR = Color.BLACK;
	public static final Color TOUCHED_TEXT_COLOR = BLUE;
	public static final Color SUNK_TEXT_COLOR = RED;

	// GRID COLORS
	public static final Color UNTOUCHED_SHIP_BOX_COLOR = CLEAR_GREEN;
	public static final Color MISSED_BOX_COLOR = Color.BLACK;
	public static final Color TOUCHED_BOX_COLOR = Color.WHITE;
	public static final Color SUNK_BOX_COLOR = CLEAR_RED;
	public static final Color GRID_COLOR = Color.BLACK;
	public static final Color BORDER_SHIP_COLOR = Color.BLACK;

	// CURSORS
	public static final Cursor DELETE_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage("res/cursors/remove.png"), new Point(16, 16), "Remove");
	public static final Cursor PLACEMENT_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage("res/cursors/hand.png"),new Point(16, 8), "Hand");

	// FRAME ICON
	public static final Image DEFAULT_FRAME_ICON = Toolkit.getDefaultToolkit().getImage("res/img/icon.png");

	// GRAPHICS
	public static final BufferedImage SHIPS_GRAPHICS_FILE = loadGraphicsFile("res/img/ships.png");
	public static final Coord AIRCRAFT_GRAPHICS_COORDONATES = new Coord(0, 0);
	public static final Coord BATTLESHIP_GRAPHICS_COORDONATES = new Coord(0, 1);
	public static final Coord CRUISER_GRAPHICS_COORDONATES = new Coord(0, 2);
	public static final Coord DESTROYER_GRAPHICS_COORDONATES = new Coord(0, 3);
	public static final Coord SUBMARINE_GRAPHICS_COORDONATES = new Coord(0, 4);
	public static final int SHIPS_GRAPHICS_FILE_BOX_SIZE = 16;
	
	// GRAPHICS LOADING
	private static BufferedImage loadGraphicsFile(String fileName) {
		BufferedImage res = null;
		try {
			res = ImageIO.read(new File(fileName));
			for (int i = 0; i < res.getWidth(); i++)
				for (int j = 0; j < res.getHeight(); j++)
					if (res.getRGB(i, j) == 0xffff00ff)
						res.setRGB(i, j, 0x00000000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
}
