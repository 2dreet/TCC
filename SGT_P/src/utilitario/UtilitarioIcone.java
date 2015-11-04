package utilitario;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

public class UtilitarioIcone implements Icon {

	private Icon left;
	private Icon right;

	public UtilitarioIcone(Icon left, Icon right) {
		this.left = left;
		this.right = right;
	}

	public int getIconHeight() {
		return Math.max(left.getIconHeight(), right.getIconHeight());
	}

	public int getIconWidth() {
		return Math.max(left.getIconWidth(), right.getIconWidth());
	}
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		right.paintIcon(c, g, 4, 5);
		left.paintIcon(c, g, 5, 4);
	}

}
