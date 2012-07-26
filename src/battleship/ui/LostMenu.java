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

public class LostMenu extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7142409917876859650L;

	private static LostMenu lostMenu = null;

	private JPanel jp_infos = new JPanel();
	private JLabel jl_infos = new JLabel("Too bad, "
			+ Game.getInstance().getOpponent().getName() + " beat you !");
	private JButton jb_close = new JButton("End Game");

	private LostMenu(JFrame frame) {
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
		add(jp_infos, BorderLayout.CENTER);
		add(jb_close, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(null);
	}

	public static LostMenu getInstance(JFrame frame) {
		if (lostMenu == null)
			lostMenu = new LostMenu(frame);
		return lostMenu;
	}
}
