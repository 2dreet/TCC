package componente;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

import localizar.LocalizarJogador;

public class CustomUI extends BasicComboBoxUI {

	
	public static ComboBoxUI createUI(JComponent c) {
		return new CustomUI();
	}

	@Override
	protected JButton createArrowButton() {
		BasicArrowButton basicArrowButton = new BasicArrowButton(
				BasicArrowButton.SOUTH, Color.WHITE, new Color(252, 79, 63),
				new Color(252, 79, 63), Color.WHITE);
		basicArrowButton.setBorder(BorderFactory.createLineBorder(new Color(176,177,184), 1));
		basicArrowButton.setContentAreaFilled(false);
		return basicArrowButton;
	}

	// aqui se quiser um icone
	/*
	 * @Override protected JButton createArrowButton() { JButton button = new
	 * JButton(); //se quita el efecto 3d del boton, sombra y darkShadow no se
	 * aplican button.setText("asdas");
	 * button.setBorder(BorderFactory.createLineBorder(red,2));
	 * button.setContentAreaFilled(false); button.setIcon(new
	 * ImageIcon(CustomUI.class.getResource("/imagem/diverssos/add34.png")));
	 * return button; }
	 */

	@Override
	public void paintCurrentValueBackground(Graphics g, Rectangle bounds,
			boolean hasFocus) {
		g.setColor(Color.WHITE);
		g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
	}

	@Override
	protected ListCellRenderer createRenderer() {
		return new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index,
						isSelected, cellHasFocus);
				list.setSelectionBackground(Color.WHITE);
				if (isSelected) {
					setBackground(new Color(176,177,184));
					setForeground(new Color(252,79,63));
				} else {
					setBackground(Color.WHITE);
					setForeground(new Color(46,44,56));
				}
				return this;
			}
		};
	}
}
