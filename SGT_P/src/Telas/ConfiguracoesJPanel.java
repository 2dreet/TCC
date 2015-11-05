package Telas;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import utilitario.ExecutarComandos;
import utilitario.UtilitarioTela;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;


public class ConfiguracoesJPanel extends JPanel{

	private JPanel header;
	private JLabel lblHeader;
	private JPanel meio;

	public ConfiguracoesJPanel(){
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

		lblHeader = new JLabel("Configurações");
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
		
		JButton btnMouse = new JButton("Mouse");
		btnMouse.setIcon(new ImageIcon(HomeJogador.class
				.getResource("/imagem/mouse.png")));
		btnMouse.setBorderPainted(false);
		btnMouse.setBackground(new Color(46, 49, 56));
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
		btnMouse.setBounds(80, 20, 120, 30);
		meio.add(btnMouse);
		
		JButton btnTeclado = new JButton("Teclado");
		btnTeclado.setIcon(new ImageIcon(HomeJogador.class
				.getResource("/imagem/teclado.png")));
		btnTeclado.setBorderPainted(false);
		btnTeclado.setFocusPainted(false);
		btnTeclado.setBackground(new Color(46, 49, 56));
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
		btnTeclado.setBounds(80, 70, 120, 30);
		meio.add(btnTeclado);
		
		JButton btnAudio = new JButton("Audio");
		btnAudio.setIcon(new ImageIcon(HomeJogador.class
				.getResource("/imagem/som.png")));
		btnAudio.setBorderPainted(false);
		btnAudio.setFocusPainted(false);
		btnAudio.setBackground(new Color(46, 49, 56));
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
		btnAudio.setBounds(80, 120, 120, 30);
		meio.add(btnAudio);
		
		JButton btnVideo = new JButton("Video");
		btnVideo.setIcon(new ImageIcon(HomeJogador.class
				.getResource("/imagem/tela.png")));
		btnVideo.setBorderPainted(false);
		btnVideo.setFocusPainted(false);
		btnVideo.setBackground(new Color(46, 49, 56));
		btnVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ExecutarComandos.execCommand("control desk.cpl");
				} catch (IOException ed) {
					// TODO Auto-generated catch block
					ed.printStackTrace();
				}
			}
		});
		btnVideo.setBounds(80, 170, 120, 30);
		meio.add(btnVideo);
		
		btnMouse.setForeground(UtilitarioTela.getFontColorSelecao(false));
		btnTeclado.setForeground(UtilitarioTela.getFontColorSelecao(false));
		btnAudio.setForeground(UtilitarioTela.getFontColorSelecao(false));
		btnVideo.setForeground(UtilitarioTela.getFontColorSelecao(false));

		btnMouse.setFont(UtilitarioTela.getFont(12));
		btnTeclado.setFont(UtilitarioTela.getFont(12));
		btnAudio.setFont(UtilitarioTela.getFont(12));
		btnVideo.setFont(UtilitarioTela.getFont(12));
	}
}
