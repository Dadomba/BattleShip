package battleship;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import battleship.menus.*;

public class BattleShipMenuBar extends JMenuBar{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8463130955387027138L;
	
	
	private JMenu jm_file = new JMenu("Fichier");
	private JMenu jm_network = new JMenu("Reseaux");
	private JMenu jm_help = new JMenu("Aide");
	private JMenuItem jmi_newGame = new JMenuItem("Nouvelle Partie");
	private JMenuItem jmi_save = new JMenuItem("Sauvegarder");
	private JMenuItem jmi_load = new JMenuItem("Charger");
	private JMenuItem jmi_quit = new JMenuItem("Quitter");
	private JMenuItem jmi_connect = new JMenuItem("Connexion");
	private JMenuItem jmi_connectTo = new JMenuItem("Connexion à...");
	private JMenuItem jmi_about = new JMenuItem("A propos");
	
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
				Game.getInstance().connect(JOptionPane.showInputDialog("Entrez le nom de l'hote ou l'adresse IP :"));
			}
		});
	}
}
