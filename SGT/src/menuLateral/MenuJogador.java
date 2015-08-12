package menuLateral;

import javax.swing.JPanel;

import utilitario.UtilitarioTela;
import javax.swing.JButton;

public class MenuJogador extends JPanel {

	/**
	 * Create the panel.
	 */
	public MenuJogador() {
		setSize(UtilitarioTela.getTamanhoMenuLateral());
		setBackground(null);
		setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(71, 316, 89, 23);
		add(btnNewButton);
	}

}
