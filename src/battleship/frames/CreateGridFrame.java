package battleship.frames;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import battleship.AI;
import battleship.Game;
import battleship.Grid;
import battleship.Player;
import battleship.entities.Ship;
import battleship.global.Constant;
import battleship.listeners.PlaceShipListener;

public class CreateGridFrame extends JFrame implements WindowListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3499118735800772773L;

	private String playerName = null;
	private Grid grid = null;
	
	private JPanelGridPlacement jp_grid = null;
	private JPanel jp_ship = new JPanel(new GridLayout(5,1));
	private JPanel jp_buttons = new JPanel();
	
	private JButton jb_addAircraft = new JButton();
	private JButton jb_addBattleship = new JButton();
	private JButton jb_addCruiser = new JButton();
	private JButton jb_addDestroyer = new JButton();
	private JButton jb_addSubmarine = new JButton();
	
	private JButton jb_ok = new JButton("Create");
	private JButton jb_auto = new JButton("Auto-Generated");
	private JButton jb_cancel = new JButton("Cancel");
	
	public CreateGridFrame(String title, String name) {
		super(title);
		setIconImage(Constant.DEFAULT_FRAME_ICON);
		
		playerName = name;
		
		try {
			grid = new Grid(Grid.BLANK_GRID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		jp_grid = new JPanelGridPlacement(this);
		
		jp_ship.add(jb_addAircraft);
		jp_ship.add(jb_addBattleship);
		jp_ship.add(jb_addCruiser);
		jp_ship.add(jb_addDestroyer);
		jp_ship.add(jb_addSubmarine);
		
		jb_ok.setEnabled(false);
		
		jp_buttons.add(jb_ok);
		jp_buttons.add(jb_auto);
		jp_buttons.add(jb_cancel);
		
		add(jp_grid,BorderLayout.WEST);
		add(jp_ship,BorderLayout.EAST);
		add(new JPanel(),BorderLayout.CENTER);
		add(jp_buttons,BorderLayout.SOUTH);
		
		addListeners();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(this);
		updateJButtonText();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void updateJButtonText() {
		int nbAircraftLeft = Constant.AIRCRAFTMAX - jp_grid.getNbShipPlaced(0);
		int nbBattleshipLeft = Constant.BATTLESHIPMAX - jp_grid.getNbShipPlaced(1);
		int nbCruiserLeft = Constant.CRUISERMAX - jp_grid.getNbShipPlaced(2);
		int nbDestroyerLeft = Constant.DESTROYERMAX - jp_grid.getNbShipPlaced(3);
		int nbSubmarineLeft = Constant.SUBMARINEMAX - jp_grid.getNbShipPlaced(4);
		
		jb_addAircraft.setText("Add aircraft (size = "+Constant.AIRCRAFTSIZE+"). "+nbAircraftLeft+" left");
		jb_addBattleship.setText("Add battleship (size = "+Constant.BATTLESHIPSIZE+"). "+nbBattleshipLeft+" left");
		jb_addCruiser.setText("Add cruiser (size = "+Constant.CRUISERSIZE+"). "+nbCruiserLeft+" left");
		jb_addDestroyer.setText("Add destroyer (size = "+Constant.DESTROYERSIZE+"). "+nbDestroyerLeft+" left");
		jb_addSubmarine.setText("Add submarine (size = "+Constant.SUBMARINESIZE+"). "+nbSubmarineLeft+" left");
		
		if(nbAircraftLeft == 0)
			jb_addAircraft.setEnabled(false);
		else
			jb_addAircraft.setEnabled(true);
		if(nbBattleshipLeft == 0)
			jb_addBattleship.setEnabled(false);
		else
			jb_addBattleship.setEnabled(true);
		if(nbCruiserLeft == 0)
			jb_addCruiser.setEnabled(false);
		else
			jb_addCruiser.setEnabled(true);
		if(nbDestroyerLeft == 0)
			jb_addDestroyer.setEnabled(false);
		else
			jb_addDestroyer.setEnabled(true);
		if(nbSubmarineLeft == 0)
			jb_addSubmarine.setEnabled(false);
		else
			jb_addSubmarine.setEnabled(true);
		
		repaint();
	}
	
	public JPanelGridPlacement getJpGrid()
	{
		return jp_grid;
	}
	
	public Grid getGrid()
	{
		return grid;
	}
	
	private void addListeners()
	{
		getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("ESCAPE" ), "action ESCAPE" );
		getRootPane().getActionMap().put("action ESCAPE", new AbstractAction() {
		    /**
			 * 
			 */
			private static final long serialVersionUID = 776491403544925397L;

			public void actionPerformed(ActionEvent ae) {
		        jp_grid.setCurrentShip(null);
		    }
		}
		);
		
		jb_addAircraft.addActionListener(new PlaceShipListener(this,0));
		jb_addBattleship.addActionListener(new PlaceShipListener(this,1));
		jb_addCruiser.addActionListener(new PlaceShipListener(this,2));
		jb_addDestroyer.addActionListener(new PlaceShipListener(this,3));
		jb_addSubmarine.addActionListener(new PlaceShipListener(this,4));
		
		jb_cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Game.getInstance().quit();
				dispose();
			}
		});
		
		jb_ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(jp_grid.allShipsArePlaced())
				{
					Game.getInstance().setPlayer(new Player(playerName,grid));
					dispose();
				}
			}
		});
		
		jb_auto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Game.getInstance().setPlayer(new Player(playerName,AI.autoGeneratedGrid(Constant.SHIP_AMOUNT)));
					dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	
	
	
	
	
	
	
	//WindowListener methods
	
	@Override
	public void windowActivated(WindowEvent arg0) {
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		Game.getInstance().quit();
		dispose();
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
	}

	@Override
	public void windowOpened(WindowEvent arg0) {	
	}

	public void placeShip(Ship ship) throws Exception {
		grid.placeShip(ship);
		if(grid.isFilled())
			jb_ok.setEnabled(true);
		updateJButtonText();
		
	}

	public void refresh() {
		updateJButtonText();
	}
}
