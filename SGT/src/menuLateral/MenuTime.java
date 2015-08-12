package menuLateral;

import javax.swing.JPanel;

import utilitario.UtilitarioTela;
import javax.swing.JRadioButton;

public class MenuTime extends JPanel {

	/**
	 * Create the panel.
	 */
	public MenuTime() {
		setSize(UtilitarioTela.getTamanhoMenuLateral());
		setBackground(null);
		setLayout(null);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		rdbtnNewRadioButton.setBounds(59, 127, 109, 23);
		add(rdbtnNewRadioButton);

	}
}
