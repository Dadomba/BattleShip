package battleship.menus;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutMenu extends JDialog implements WindowListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7142409917876859650L;

	private static AboutMenu aboutMenu = null;
	
	private JPanel jp_infos = new JPanel();
	private JLabel jl_infos = new JLabel("Game developped by ARUS Joshua and Wlotzko Vincent just for fun !");
	private AboutMenu(JFrame frame)
	{
		super(frame);
		
		jp_infos.add(jl_infos);
		add(jp_infos,BorderLayout.CENTER);
		pack();
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		
		this.addWindowListener(this);
	}
	
	public static AboutMenu getInstance(JFrame frame)
	{
		if (aboutMenu == null)
			aboutMenu = new AboutMenu(frame);
		return aboutMenu;
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
		setVisible(false);
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		setVisible(false);
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
	}
}
