package battleship;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import battleship.global.Constant;

import battleship.menus.LostMenu;
import battleship.menus.WonMenu;

public class Screen extends JFrame {

	private static final long serialVersionUID = -4385410151814338486L;
	
	private static Screen screen = null;
	
	private JMenuBar menuBar = new BattleShipMenuBar();

	private Dimension size = new Dimension(300,600);
	
	private JPanel jp_gameArea = new JPanel(new BorderLayout());
	private JPanel jp_separator = new JPanel();
	private JPanel jp_playerArea = new JPanel(new GridLayout(Constant.YMAX+1, Constant.XMAX+1));
	private JPanel jp_opponnentArea = new JPanel(new GridLayout(Constant.YMAX+1, Constant.XMAX+1));
	private JPanel jp_infoArea = new JPanel(new BorderLayout());
	
	private JPanel jp_playerShipInformations = null;
	private JPanel jp_opponentShipInformations = null;
	
	private JButtonOpponentGrid[][] jButtonOpponentGridList = new JButtonOpponentGrid[Constant.XMAX+1][Constant.YMAX+1];
	private JButtonPlayerGrid[][] jButtonPlayerGridList = new JButtonPlayerGrid[Constant.XMAX+1][Constant.YMAX+1];
	
	private JLabel jl_separatorMessage = new JLabel();
	
	private Screen() {
		super("BattleShip v0.1 - ARUS Joshua & WLOTZKO Vincent");
		
		// Menu
		setJMenuBar(menuBar);
		
		// Central area
//		jp_gameArea.setMinimumSize(size);
//		jp_gameArea.setPreferredSize(size);
//		jp_gameArea.setMaximumSize(size);
		
		Dimension dimSeparatorPanel = new Dimension (10,30);
		Dimension dimPlayersPanel = new Dimension(size.width,size.height/2 - dimSeparatorPanel.height);
		jp_opponnentArea.setMinimumSize(dimPlayersPanel);
		jp_opponnentArea.setPreferredSize(dimPlayersPanel);
		jp_opponnentArea.setMaximumSize(dimPlayersPanel);
		jp_playerArea.setMinimumSize(dimPlayersPanel);
		jp_playerArea.setPreferredSize(dimPlayersPanel);
		jp_playerArea.setMaximumSize(dimPlayersPanel);
		
		jp_separator.setMinimumSize(dimSeparatorPanel);
		jp_separator.setPreferredSize(dimSeparatorPanel);
		jp_separator.setMaximumSize(dimSeparatorPanel);
		
		jp_separator.add(jl_separatorMessage);
		
		jp_gameArea.add(jp_opponnentArea,BorderLayout.NORTH);
		jp_gameArea.add(jp_separator,BorderLayout.CENTER);
		jp_gameArea.add(jp_playerArea,BorderLayout.SOUTH);

		JLabel jl_playerName = new JLabel(Game.getInstance().getPlayer().getName());
		JLabel jl_opponentName = new JLabel(Game.getInstance().getOpponent().getName());
		jp_playerShipInformations = new JPanelShipInformation(jl_playerName,Game.getInstance().getPlayer().getPlayerGrid());
		jp_opponentShipInformations = new JPanelShipInformation(jl_opponentName,Game.getInstance().getOpponent().getPlayerGrid());
		jp_infoArea.add(jp_opponentShipInformations, BorderLayout.NORTH);
		jp_infoArea.add(jp_playerShipInformations, BorderLayout.SOUTH);
		
		add(jp_gameArea,BorderLayout.CENTER);
		add(jp_infoArea,BorderLayout.EAST);
		
		//gameArea
		for(int j = 0 ; j < Constant.YMAX+1; j++)
			for(int i = 0; i < Constant.XMAX+1; i++)
			{
				JButtonOpponentGrid opponentButton = new JButtonOpponentGrid(j,i);
				jButtonOpponentGridList[j][i] = opponentButton;
				jp_opponnentArea.add(opponentButton);
				JButtonPlayerGrid playerButton = new JButtonPlayerGrid(j,i);
				jButtonPlayerGridList[j][i] = playerButton;
				jp_playerArea.add(playerButton);
			}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);// Centering
	}
	
	public static Screen getInstance()
	{
		if (screen == null)
			screen = new Screen();
		return screen;
	}

	public static void displayPlayerGridInConsole(Player player) {
		displayGridInConsole(player.getPlayerGrid());
	}
	
	public static void displayGridInConsole(Grid grid) {
		for (int i = 0; i < Constant.YMAX + 1; i++) {
			for (int j = 0; j < Constant.XMAX + 1; j++) {
				System.out.print(grid.getBoxStatus(j, i) + "  ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public void disableJButtonOpponentGrid(int i,int j)
	{
		jButtonOpponentGridList[i][j].setEnabled(false);
	}

	public void showWonMenu() {
		WonMenu.getInstance(this).setVisible(true);
	}

	public void showLostMenu() {
		LostMenu.getInstance(this).setVisible(true);
	}
	
	public void setMessage(SeparatorMessage separatorMessage)
	{
		jl_separatorMessage.setText(separatorMessage.getText());
		jl_separatorMessage.setForeground(separatorMessage.getColor());
		jl_separatorMessage.setFont(separatorMessage.getFont());
		Game.getInstance().refresh();
	}
}
