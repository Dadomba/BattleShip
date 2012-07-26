package battleship.ui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import battleship.Constant;

public class NewGameMenu extends JDialog implements WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7142409917876859650L;

	private static NewGameMenu newGameMenu = null;

	private NewGameMenu(JFrame frame) {
		super(frame);
		setIconImage(Constant.DEFAULT_FRAME_ICON);

		add(new JLabel("Under construction"));

		pack();
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);

		this.addWindowListener(this);
	}

	public static NewGameMenu getInstance(JFrame frame) {
		if (newGameMenu == null)
			newGameMenu = new NewGameMenu(frame);
		return newGameMenu;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		setVisible(false);
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
}
