package battleship.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import battleship.Constant;
import battleship.core.Game;

public class NetworkMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2260302488677061587L;

	private static NetworkMenu networkMenu = null;

	private JLabel jl_host = new JLabel("Host IP/Name :");
	private JLabel jl_port = new JLabel("Port :");

	private JTextField jtf_host = new JTextField();
	private JTextField jtf_port = new JTextField(""
			+ Constant.DEFAULT_CONNECTION_PORT);

	private JButton jb_ok = new JButton("Connect");
	private JButton jb_cancel = new JButton("Cancel");

	private NetworkMenu() {
		super("Network Configuration");
		setIconImage(Constant.DEFAULT_FRAME_ICON);

		setLayout(new GridLayout(3, 2));

		jl_port.setHorizontalAlignment(JLabel.CENTER);
		jl_port.setHorizontalTextPosition(JLabel.CENTER);

		jtf_host.setHorizontalAlignment(JTextField.CENTER);
		jtf_port.setHorizontalAlignment(JTextField.CENTER);

		add(jl_host);
		add(jtf_host);
		add(jl_port);
		add(jtf_port);
		add(jb_ok);
		add(jb_cancel);

		jb_cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				afficher(false);
			}
		});

		jb_ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Game.getInstance().connect(jtf_host.getText(),
						Integer.parseInt(jtf_port.getText()));
				afficher(false);
			}
		});

		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	public static NetworkMenu getInstance() {
		if (networkMenu == null)
			networkMenu = new NetworkMenu();
		return networkMenu;
	}

	public void afficher(boolean state) {
		setVisible(state);
	}
}
