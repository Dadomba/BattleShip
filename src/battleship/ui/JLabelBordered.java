package battleship.ui;

import java.awt.Color;

import javax.swing.JLabel;

public class JLabelBordered extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2848631721459158481L;

	@SuppressWarnings("unused")
	private Color borderColor = Color.BLACK;

	public JLabelBordered(String text) {
		super(text);
		setOpaque(false);
	}

	public void setColors(Color foreground, Color border) {
		setForeground(foreground);
		borderColor = border;
	}

	// @Override
	// public void paintComponent(Graphics graphics)
	// {
	// setOpaque(false);
	// super.paintComponent(graphics);
	// // Graphics2D g = (Graphics2D) graphics;
	// //
	// // g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	// RenderingHints.VALUE_ANTIALIAS_ON);//on affine les traits
	// // g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	// RenderingHints.VALUE_INTERPOLATION_BICUBIC);
	// //
	//
	// }
}
