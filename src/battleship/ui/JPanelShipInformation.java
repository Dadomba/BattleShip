package battleship.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import battleship.Constant;
import battleship.core.Game;
import battleship.core.Grid;
import battleship.entities.Ship;

public class JPanelShipInformation extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6217329123065271827L;
	private ArrayList<JLabel> shipListName = new ArrayList<JLabel>();
	private ArrayList<JLabelBordered> shipListLife = new ArrayList<JLabelBordered>();
	private Grid grid = null;
	private JLabel playerName = null;

	public JPanelShipInformation(JLabel playerName, Grid grid) {
		this.grid = grid;
		this.playerName = playerName;
		this.grid.addObserver(this);

		setLayout(new BorderLayout());

		Font font = Constant.DEFAULT_INFORMATION_FIELD_FONT;
		for (Ship s : this.grid.getShipList()) {
			JLabelBordered jl_shipLife = new JLabelBordered("" + s.getLife()
					+ "/" + s.getSize());
			jl_shipLife.setColors(Constant.HIGH_LIFE_COLOR,
					Constant.HIGH_LIFE_BORDER_COLOR);
			jl_shipLife.setFont(font);
			jl_shipLife.setHorizontalTextPosition(JLabel.RIGHT);
			shipListLife.add(jl_shipLife);

			JLabel jl_shipName = new JLabel(s.getClass().getSimpleName()
					+ " : ");
			jl_shipName.setFont(font);
			jl_shipName.setHorizontalTextPosition(JLabel.LEFT);
			shipListName.add(jl_shipName);
		}

		JPanel informations = new JPanel(new GridLayout(10, 1));

		for (int i = 0; i < shipListName.size(); i++) {
			JPanel jp_shipInfo = new JPanel(new BorderLayout());
			jp_shipInfo.add(shipListName.get(i), BorderLayout.WEST);
			jp_shipInfo.add(shipListLife.get(i), BorderLayout.EAST);
			informations.add(jp_shipInfo);
		}

		playerName.setHorizontalAlignment(JLabel.CENTER);
		playerName.setFont(Constant.DEFAULT_INFORMATION_FIELD_PLAYER_NAME_FONT);
		playerName
				.setForeground(Constant.DEFAULT_INFORMATION_FIELD_PLAYER_NAME_COLOR);

		JPanel margeEst = new JPanel();
		JPanel margeOuest = new JPanel();
		Dimension margeSize = new Dimension(10, 10);
		margeEst.setMinimumSize(margeSize);
		margeEst.setPreferredSize(margeSize);
		margeEst.setMaximumSize(margeSize);
		margeOuest.setMinimumSize(margeSize);
		margeOuest.setPreferredSize(margeSize);
		margeOuest.setMaximumSize(margeSize);
		add(playerName, BorderLayout.NORTH);
		add(informations, BorderLayout.CENTER);
		add(margeOuest, BorderLayout.WEST);
		add(margeEst, BorderLayout.EAST);
	}

	public void setPlayerName(JLabel playerName) {
		this.playerName.setText(playerName.getText());
		this.playerName.setFont(playerName.getFont());
		this.playerName.setForeground(playerName.getForeground());

		Game.getInstance().refresh();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		Ship ship = (Ship) arg0;

		int life = ship.getLife();
		int maxLife = ship.getSize();

		int index = grid.findShipIndex(ship);

		if (life > 0) {
			shipListLife.get(index).setText("" + life + "/" + maxLife);
			if (life > maxLife / 2)
				shipListLife.get(index).setColors(Constant.HIGH_LIFE_COLOR,
						Constant.HIGH_LIFE_BORDER_COLOR);
			else
				shipListLife.get(index).setColors(Constant.AVERAGE_LIFE_COLOR,
						Constant.AVERAGE_LIFE_BORDER_COLOR);
		} else {
			shipListLife.get(index).setText("Sunk");
			shipListLife.get(index).setColors(Constant.NO_MORE_LIFE_COLOR,
					Constant.NO_MORE_LIFE_COLOR_BORDER);
		}

		Game.getInstance().refresh();
	}
}
