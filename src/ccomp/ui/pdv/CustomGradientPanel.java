package ccomp.ui.pdv;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


/**
 * JPanel com gradiente de fundo
 **/
public class CustomGradientPanel extends JPanel {

	private static final long serialVersionUID = -5353908095089887498L;

	private Color colorTop;
	private Color colorBottom;
	
	public CustomGradientPanel(Color colorTop, Color colorBottom) {
		this.colorTop = colorTop;
		this.colorBottom = colorBottom;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D graphics2d = (Graphics2D) g;
		GradientPaint gradientPaint = new GradientPaint(getWidth() / 2, 0, 
				colorTop, getWidth() / 2, getHeight(), colorBottom);
		graphics2d.setPaint(gradientPaint);
		graphics2d.fillRect(0, 0, getWidth(), getHeight());
	}
	
	public Color getColorTop() {
		return colorTop;
	}

	public void setColorTop(Color colorTop) {
		this.colorTop = colorTop;
	}

	public Color getColorBottom() {
		return colorBottom;
	}

	public void setColorBottom(Color colorBottom) {
		this.colorBottom = colorBottom;
	}
	
	
	
	
}
