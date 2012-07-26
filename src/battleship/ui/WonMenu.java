package battleship.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import battleship.Constant;
import battleship.core.Game;

public class WonMenu extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7142409917876859650L;

	private static WonMenu wonMenu = null;
	
	private JPanel jp_infos = new JPanel();
	private JLabel jl_infos = new JLabel("Well done, you beat "+Game.getInstance().getOpponent().getName()+" !");
	private JButton jb_close = new JButton("End game");
	
	private WonMenu(JFrame frame)
	{
		super(frame);
		setIconImage(Constant.DEFAULT_FRAME_ICON);
		
		jb_close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Game.getInstance().quit();
				dispose();
			}
		});
		
		jp_infos.add(jl_infos);
		add(jp_infos,BorderLayout.CENTER);
		add(jb_close,BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(null);
	}
	
	public static WonMenu getInstance(JFrame frame) {
		if (wonMenu == null)
			wonMenu = new WonMenu(frame);
		return wonMenu;
	}
}
