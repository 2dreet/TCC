package utilitario;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

public class BordaClara implements Border {

	public void paintBorder(Component c, Graphics g, int x, int y, int width,
			int height) {
		
	
			g.setColor(new Color(102, 102, 102));
			g.drawRect(-2, y + height + -3, width + 4, 1);

			g.setColor(new Color(208, 208, 208));
			g.fillRect(0, y + height + -2, width + 4, 4);
		
	}

	public Insets getBorderInsets(Component c) {
		return new Insets(0, 0, 0, 0);
	}

	public boolean isBorderOpaque() {
		return true;
	}
}
