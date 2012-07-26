package battleship.ui;

import java.awt.Color;
import java.awt.Font;

import battleship.Constant;

public class SeparatorMessage {

	private String text = null;
	private Color color = Constant.STANDARD_TEXT_COLOR;
	private Font font = Constant.DEFAULT_MESSAGE_SEPARATOR_FONT;
	private long displayTime = 0;

	public SeparatorMessage(String text) {
		this.text = text;
	}

	public SeparatorMessage(String text, long displayTime) {
		this.text = text;
		this.displayTime = displayTime;
	}

	public SeparatorMessage(String text, Color color) {
		this.text = text;
		this.color = color;
	}

	public SeparatorMessage(String text, Font font) {
		this.text = text;
		this.font = font;
	}

	public SeparatorMessage(String text, Color color, Font font) {
		this.text = text;
		this.color = color;
		this.font = font;
	}

	public SeparatorMessage(String text, Color color, long displayTime) {
		this.text = text;
		this.color = color;
		this.displayTime = displayTime;
	}

	public SeparatorMessage(String text, Font font, long displayTime) {
		this.text = text;
		this.font = font;
		this.displayTime = displayTime;
	}

	public SeparatorMessage(String text, Color color, Font font,
			long displayTime) {
		this.text = text;
		this.color = color;
		this.font = font;
		this.displayTime = displayTime;
	}

	public String getText() {
		return text;
	}

	public Color getColor() {
		return color;
	}

	public Font getFont() {
		return font;
	}

	public long getDisplayTime() {
		return displayTime;
	}
}
