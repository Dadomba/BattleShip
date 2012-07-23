package battleship.menus;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import battleship.Game;

public class LostMenu extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7142409917876859650L;

	private static LostMenu lostMenu = null;
	
	private JPanel jp_infos = new JPanel();
	private JLabel jl_infos = new JLabel("Too bad, "+Game.getInstance().getOpponent().getName()+" beat you !");
	private LostMenu(JFrame frame)
	{
		super(frame);
		
		jp_infos.add(jl_infos);
		add(jp_infos,BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
	}
	
	public static LostMenu getInstance(JFrame frame)
	{
		if (lostMenu == null)
			lostMenu = new LostMenu(frame);
		return lostMenu;
	}
}
