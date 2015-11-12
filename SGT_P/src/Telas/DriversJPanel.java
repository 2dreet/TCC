package Telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import utilitario.ExecutarComandos;
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
		
		JButton btnMouse = new JButton("");
		btnMouse.setIcon(new ImageIcon(HomeJogador.class
				.getResource("/imagem/razer.png")));
		btnMouse.setBackground(UtilitarioTela.getFontColorPadrao());
		btnMouse.setFocusPainted(false);
		btnMouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ExecutarComandos.execCommand("control main.cpl");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnMouse.setBounds(50, 20, 60, 60);
		meio.add(btnMouse);
		
		JLabel lbRazer = new JLabel("Razer");
		lbRazer.setHorizontalAlignment(SwingConstants.LEFT);
		lbRazer.setBounds(120, 20, 60, 60);
		lbRazer.setFont(UtilitarioTela.getFont(14));
		lbRazer.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbRazer);
		
		JButton btnTeclado = new JButton("");
		btnTeclado.setIcon(new ImageIcon(HomeJogador.class
				.getResource("/imagem/steek.png")));
		btnTeclado.setFocusPainted(false);
		btnTeclado.setBackground(UtilitarioTela.getFontColorPadrao());
		btnTeclado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ExecutarComandos.execCommand("control keyboard");
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		btnTeclado.setBounds(50, 100, 60, 60);
		meio.add(btnTeclado);
		
		JLabel lbSteelSeries = new JLabel("SteelSeries");
		lbSteelSeries.setHorizontalAlignment(SwingConstants.LEFT);
		lbSteelSeries.setBounds(120, 100, 150, 60);
		lbSteelSeries.setFont(UtilitarioTela.getFont(14));
		lbSteelSeries.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbSteelSeries);
		
		JButton btnAudio = new JButton("");
		btnAudio.setIcon(new ImageIcon(HomeJogador.class
				.getResource("/imagem/som.png")));
		btnAudio.setFocusPainted(false);
		btnAudio.setBackground(UtilitarioTela.getFontColorPadrao());
		btnAudio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ExecutarComandos.execCommand("control mmsys.cpl sounds");
				} catch (IOException ee) {
					// TODO Auto-generated catch block
					ee.printStackTrace();
				}
			}
		});
		btnAudio.setBounds(50, 180, 60, 60);
		meio.add(btnAudio);
		
		JLabel lbls = new JLabel("SteelSeries");
		lbls.setHorizontalAlignment(SwingConstants.LEFT);
		lbls.setBounds(120, 180, 60, 60);
		lbls.setFont(UtilitarioTela.getFont(14));
		lbls.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbls);
		
	}
}
