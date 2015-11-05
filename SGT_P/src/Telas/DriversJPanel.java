package Telas;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import utilitario.UtilitarioTela;


public class DriversJPanel extends JPanel{

	private JPanel header;
	private JLabel lblHeader;
	private JPanel meio;

	public DriversJPanel(){
		setSize(280, 360);
		setLayout(null);
		setBackground(null);
		header = new JPanel();
		header.setSize(280, 30);
		header.setLocation(0, 0);
		header.setLayout(null);
		header.setBackground(Color.white);
		header.setBorder(null);
		add(header);

		lblHeader = new JLabel("Drivers");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setBounds(0, 0, header.getWidth(), header.getHeight());
		lblHeader.setFont(UtilitarioTela.getFont(14));
		lblHeader.setForeground(UtilitarioTela.getFontColorCrud());
		header.add(lblHeader);

		meio = new JPanel();
		meio.setSize(280, 330);
		meio.setLocation(0,30);
		meio.setLayout(null);
		meio.setBackground(UtilitarioTela.getFundoCrud());
		meio.setBorder(null);
		add(meio);
		
	}
}
