package utilitario;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

public class BordaSombreada implements Border {

	private int modoCrud = 0;

	public BordaSombreada(int modoCrud) {
		this.modoCrud = modoCrud;
	}

	public BordaSombreada() {
		modoCrud = 0;
	}

	public void paintBorder(Component c, Graphics g, int x, int y, int width,
			int height) {

		if (modoCrud != 0) {
			g.setColor(UtilitarioTela.getColorCrud(modoCrud));
		} else {
			g.setColor(Color.white);
		}
		g.drawRect(x, y, width - 1, height - 1);

		g.setColor(new Color(53, 56, 64));
		g.fillRect(x + 1, y + 1, 1, height - 2);
		g.fillRect(x + width - 2, y + 1, 1, height - 2);
		g.fillRect(x + 1, y + 1, width - 2, 1);
		g.fillRect(x + 1, y + height - 2, width - 2, 1);

	}

	public Insets getBorderInsets(Component c) {
		return new Insets(3, 3, 3, 3);
	}

	public boolean isBorderOpaque() {
		return true;
	}
}
