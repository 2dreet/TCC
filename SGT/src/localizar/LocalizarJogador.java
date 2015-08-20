package localizar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import utilitario.BordaSombreada;
import utilitario.ParametroCrud;
import utilitario.Parametros;
import utilitario.UtilitarioTela;

import javax.swing.JComboBox;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.JButton;

import componente.ComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

public class LocalizarJogador extends JPanel {

	
	String[] items = { "jw", "ja", "la" };

	/**
	 * Create the panel.
	 */
	public LocalizarJogador() {
		setSize(UtilitarioTela.getTamanhoMeio());
		setLayout(null);
		setBackground(null);
		
		JPanel header = new JPanel();
		header.setSize(getWidth()-20, 30);
		header.setLocation(10, 10);
		header.setLayout(null);
		header.setBackground(Color.white);
		header.setBorder(null);
		add(header);
		
		JLabel lblHeader = new JLabel("Localizar Jogador");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setBounds(0, 0, header.getWidth(), header.getHeight());
		lblHeader.setFont(UtilitarioTela.getFont(14));
		lblHeader.setForeground(UtilitarioTela.getFontColorCrud());
		header.add(lblHeader);
		
		JPanel meio = new JPanel();
		meio.setSize(getWidth()-20, getHeight()-50);
		meio.setLocation(10, 40);
		meio.setLayout(null);
		meio.setBackground(new Color(232, 234, 239));
		meio.setBorder(new BordaSombreada());
		add(meio);
		
		ComboBox combo = new ComboBox(new Dimension(300, 25));
		combo.setModel(new DefaultComboBoxModel(new String[] { "Enero", "Febrero", "Marzo", "Diciembre" }));
		combo.setLocation(10, 100);
		meio.add(combo);
	}
}


