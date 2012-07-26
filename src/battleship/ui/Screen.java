package battleship.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import battleship.Constant;
import battleship.core.Game;

public class Screen extends JFrame {

	private static final long serialVersionUID = -4385410151814338486L;

	private static Screen screen = null;

	private JMenuBar menuBar = new BattleShipMenuBar();

	private Dimension size = new Dimension(300, 600);

	private JPanel jp_gameArea = new JPanel(new BorderLayout());
	private JPanel jp_separator = new JPanel(new BorderLayout());
	private JPanel jp_playerArea = new JPanel(new GridLayout(Constant.YMAX
			- Constant.YMIN + 1, Constant.XMAX - Constant.XMIN + 1));
	private JPanel jp_opponnentArea = new JPanel(new GridLayout(Constant.YMAX
			- Constant.YMIN + 1, Constant.XMAX - Constant.XMIN + 1));
	private JPanel jp_infoArea = new JPanel(new BorderLayout());

	private JPanelShipInformation jp_playerShipInformations = null;
	private JPanelShipInformation jp_opponentShipInformations = null;

	private JButtonOpponentGrid[][] jButtonOpponentGridList = new JButtonOpponentGrid[Constant.XMAX
			- Constant.XMIN + 1][Constant.YMAX - Constant.YMIN + 1];
	private JButtonPlayerGrid[][] jButtonPlayerGridList = new JButtonPlayerGrid[Constant.XMAX
			- Constant.XMIN + 1][Constant.YMAX - Constant.YMIN + 1];

	private JLabel jl_separatorMessage = new JLabel();

	private Screen() {
		super("BattleShip v0.1 - ARUS Joshua & WLOTZKO Vincent");
		setIconImage(Constant.DEFAULT_FRAME_ICON);

		// Menu
		setJMenuBar(menuBar);

		jp_infoArea.setMinimumSize(size);
		jp_infoArea.setPreferredSize(size);
		jp_infoArea.setMaximumSize(size);

		Dimension dimSeparatorPanel = new Dimension(10, 30);
		Dimension dimPlayersPanel = new Dimension(size.width, size.height / 2
				- dimSeparatorPanel.height);
		jp_opponnentArea.setMinimumSize(dimPlayersPanel);
		jp_opponnentArea.setPreferredSize(dimPlayersPanel);
		jp_opponnentArea.setMaximumSize(dimPlayersPanel);
		jp_playerArea.setMinimumSize(dimPlayersPanel);
		jp_playerArea.setPreferredSize(dimPlayersPanel);
		jp_playerArea.setMaximumSize(dimPlayersPanel);

		jp_separator.setMinimumSize(dimSeparatorPanel);
		jp_separator.setPreferredSize(dimSeparatorPanel);
		jp_separator.setMaximumSize(dimSeparatorPanel);

		jl_separatorMessage.setHorizontalAlignment(JLabel.CENTER);
		jl_separatorMessage.setHorizontalTextPosition(JLabel.CENTER);

		jp_separator.add(jl_separatorMessage, BorderLayout.CENTER);

		jp_gameArea.add(jp_opponnentArea, BorderLayout.NORTH);
		jp_gameArea.add(jp_separator, BorderLayout.CENTER);
		jp_gameArea.add(jp_playerArea, BorderLayout.SOUTH);

		add(jp_gameArea, BorderLayout.CENTER);
		add(jp_infoArea, BorderLayout.EAST);

		// gameArea
		for (int i = Constant.XMIN; i < Constant.XMAX + 1; i++)
			for (int j = Constant.YMIN; j < Constant.YMAX + 1; j++) {
				JButtonOpponentGrid opponentButton = new JButtonOpponentGrid(i,
						j);
				jButtonOpponentGridList[i][j] = opponentButton;
				jp_opponnentArea.add(opponentButton);
				JButtonPlayerGrid playerButton = new JButtonPlayerGrid(i, j);
				jButtonPlayerGridList[i][j] = playerButton;
				jp_playerArea.add(playerButton);
			}

		JLabel jl_playerName = new JLabel(Game.getInstance().getPlayer()
				.getName());
		jp_playerShipInformations = new JPanelShipInformation(jl_playerName,
				Game.getInstance().getPlayer().getPlayerGrid());
		jp_infoArea.add(jp_playerShipInformations, BorderLayout.SOUTH);

		loadGrids();
		loadOpponentInformationPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);// Centering
	}

	public void loadOpponentInformationPanel() {
		if (Game.getInstance().getOpponent() != null) {
			JLabel jl_opponentName = new JLabel(Game.getInstance()
					.getOpponent().getName());
			jp_opponentShipInformations = new JPanelShipInformation(
					jl_opponentName, Game.getInstance().getOpponent()
							.getPlayerGrid());
			jp_infoArea.add(jp_opponentShipInformations, BorderLayout.NORTH);
		}
	}

	public void loadGrids() {
		if (Game.getInstance().getOpponent() != null)
			for (JButtonOpponentGrid[] jbTab : jButtonOpponentGridList)
				for (JButtonOpponentGrid jb : jbTab)
					jb.loadGrid();
		if (Game.getInstance().getPlayer() != null)
			for (JButtonPlayerGrid[] jbTab : jButtonPlayerGridList)
				for (JButtonPlayerGrid jb : jbTab)
					jb.loadGrid();
	}

	public static Screen getInstance() {
		if (screen == null)
			screen = new Screen();
		return screen;
	}

	public void disableJButtonOpponentGrid(int i, int j) {
		jButtonOpponentGridList[i][j].setEnabled(false);
	}

	public void showWonMenu() {
		WonMenu.getInstance(this).setVisible(true);
	}

	public void showLostMenu() {
		LostMenu.getInstance(this).setVisible(true);
	}

	public void setMessage(SeparatorMessage separatorMessage) {
		jl_separatorMessage.setText(separatorMessage.getText());
		jl_separatorMessage.setForeground(separatorMessage.getColor());
		jl_separatorMessage.setFont(separatorMessage.getFont());
		Game.getInstance().refresh();
	}
}
