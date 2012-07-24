package battleship;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import battleship.frames.*;

public class BattleShipMenuBar extends JMenuBar{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8463130955387027138L;
	
	
	private JMenu jm_file = new JMenu("File");
	private JMenu jm_network = new JMenu("Network");
	private JMenu jm_help = new JMenu("Help");
	private JMenuItem jmi_newGame = new JMenuItem("New game");
	private JMenuItem jmi_save = new JMenuItem("Save");
	private JMenuItem jmi_load = new JMenuItem("Load");
	private JMenuItem jmi_quit = new JMenuItem("Quit");
	private JMenuItem jmi_connect = new JMenuItem("Connection");
	private JMenuItem jmi_connectTo = new JMenuItem("Connection to...");
	private JMenuItem jmi_about = new JMenuItem("About");
	
	public BattleShipMenuBar()
	{
		add(jm_file);
		add(jm_network);
		add(jm_help);
		
		jm_file.add(jmi_newGame);
		jm_file.addSeparator();
		jm_file.add(jmi_save);
		jm_file.add(jmi_load);
		jm_file.addSeparator();
		jm_file.add(jmi_quit);
		
		jm_network.add(jmi_connect);
		jm_network.add(jmi_connectTo);
		
		jm_help.add(jmi_about);
		
		jmi_save.setEnabled(false);
		jmi_load.setEnabled(false);
		
		addListeners();
	}
	
	private void addListeners()
	{
		jmi_about.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AboutMenu.getInstance(Screen.getInstance()).setVisible(true);
			}
		});
		
		jmi_newGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				NewGameMenu.getInstance(Screen.getInstance()).setVisible(true);
			}
		});
		
		jmi_quit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Game.getInstance().quit();
			}
		});
		
		jmi_connect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Game.getInstance().connect();
			}
		});
		
		jmi_connectTo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				NetworkMenu.getInstance().afficher(true);
			}
		});
	}
}
