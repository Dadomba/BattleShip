package battleship.frames;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import battleship.Game;
import battleship.global.Constant;

public class WonMenu extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7142409917876859650L;

	private static WonMenu wonMenu = null;
	
	private JPanel jp_infos = new JPanel();
	private JLabel jl_infos = new JLabel("Well done, you beat "+Game.getInstance().getOpponent().getName()+" !");
	private WonMenu(JFrame frame)
	{
		super(frame);
		setIconImage(Constant.DEFAULT_FRAME_ICON);
		
		jp_infos.add(jl_infos);
		add(jp_infos,BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
	}
	
	public static WonMenu getInstance(JFrame frame)
	{
		if (wonMenu == null)
			wonMenu = new WonMenu(frame);
		return wonMenu;
	}
}
