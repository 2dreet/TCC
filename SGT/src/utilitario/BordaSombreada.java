package utilitario;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

public class BordaSombreada implements Border {
	public void paintBorder(Component c, Graphics g, int x, int y, int width,
			int height) {

		g.setColor(new Color(51, 153, 255));
//		g.drawRect(x + 2, y + 2, width - 5, height - 5);
		g.drawRect(x - 2, y + 2, width - 5, height - 5);

		g.setColor(new Color(81, 169, 255));
		g.fillRect(x + 1, y + 2, 1, height);
		g.fillRect(x + width - 2, y, 1, height);
		g.fillRect(x + 1, y , width, 2);
		g.fillRect(x + 1, y + height - 2, width, 2);

		g.setColor(new Color(121, 187, 253));
		g.fillRect(x, y + 1, 1, height);
		g.fillRect(x + width - 1, y, 1, height);
		g.fillRect(x, y - 1, width, 2);
		g.fillRect(x, y + height - 1, width, 2);

	}

	public Insets getBorderInsets(Component c) {
		return new Insets(3, 3, 3, 3);
	}

	public boolean isBorderOpaque() {
		return true;
	}
}
