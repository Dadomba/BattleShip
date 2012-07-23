package battleship;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import battleship.entities.Ship;
import battleship.global.Constant;

public class JPanelShipInformation extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6217329123065271827L;
	private ArrayList<JLabel> shipListName = new ArrayList<JLabel>();
	private ArrayList<JLabel> shipListLife = new ArrayList<JLabel>();
	private Grid grid = null;
	
	public JPanelShipInformation(JLabel playerName,Grid grid) {
		this.grid = grid; 
		this.grid.addObserver(this);
		
		setLayout(new BorderLayout());
		
		Font font = Constant.DEFAULT_INFORMATION_FIELD_FONT;
		for(Ship s : this.grid.getShipList())
		{
			JLabel jl_shipLife = new JLabel(""+s.getLife()+"/"+s.getSize());
			jl_shipLife.setForeground(Constant.HIGH_LIFE_COLOR);
			jl_shipLife.setFont(font);
			jl_shipLife.setHorizontalTextPosition(JLabel.RIGHT);
			shipListLife.add(jl_shipLife);
			
			JLabel jl_shipName = new JLabel(s.getClass().getSimpleName()+" : ");
			jl_shipName.setFont(font);
			jl_shipName.setHorizontalTextPosition(JLabel.LEFT);
			shipListName.add(jl_shipName);
		}
		
		JPanel informations = new JPanel(new GridLayout(10,1));
//		setLayout(new GridBagLayout());
//		GridBagConstraints gbc = new GridBagConstraints();
//		gbc.gridx = GridBagConstraints.WEST;
//		gbc.gridy = GridBagConstraints.RELATIVE;
//		 
//		gbc.fill = GridBagConstraints.HORIZONTAL;
		
//		gbc.insets = new Insets(30, 30, 30, 30);
		for(int i = 0 ; i < shipListName.size(); i++)
		{
			JPanel jp_shipInfo = new JPanel(new BorderLayout());
			jp_shipInfo.add(shipListName.get(i), BorderLayout.WEST);
			jp_shipInfo.add(shipListLife.get(i), BorderLayout.EAST);
			informations.add(jp_shipInfo);
		}
		
		playerName.setHorizontalAlignment(JLabel.CENTER);
		playerName.setFont(Constant.DEFAULT_INFORMATION_FIELD_PLAYER_NAME);
		playerName.setForeground(Constant.DEFAULT_INFORMATION_FIELD_PLAYER_NAME_COLOR);
		
		JPanel margeEst = new JPanel();
		JPanel margeOuest = new JPanel();
		Dimension margeSize = new Dimension(10,10);
		margeEst.setMinimumSize(margeSize);
		margeEst.setPreferredSize(margeSize);
		margeEst.setMaximumSize(margeSize);
		margeOuest.setMinimumSize(margeSize);
		margeOuest.setPreferredSize(margeSize);
		margeOuest.setMaximumSize(margeSize);
		add(playerName,BorderLayout.NORTH);
		add(informations,BorderLayout.CENTER);
		add(margeOuest,BorderLayout.WEST);
		add(margeEst,BorderLayout.EAST);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		Ship ship = (Ship) arg0;
		
		int life = ship.getLife();
		int maxLife = ship.getSize();
		
		int index = grid.findShipIndex(ship);
				
		if(life > 0)
		{
			shipListLife.get(index).setText(""+life+"/"+maxLife);
			if(life > maxLife/2)
				shipListLife.get(index).setForeground(Constant.HIGH_LIFE_COLOR);
			else
				shipListLife.get(index).setForeground(Constant.AVERAGE_LIFE_COLOR);
		}
		else
		{
			shipListLife.get(index).setText("Sunk");
			shipListLife.get(index).setForeground(Constant.NO_MORE_LIFE_COLOR);
		}
		
		Game.getInstance().refresh();
	}
}
