package exemplos;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;

public class Fre extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 * @throws ColumnBuilderException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ColumnBuilderException, ClassNotFoundException {
		ExemploRelatorio er = new ExemploRelatorio();
		er.getRelatorio().setVisible(true);
	}

	

}
