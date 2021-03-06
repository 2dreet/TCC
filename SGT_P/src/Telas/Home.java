package Telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import utilitario.Parametros;
import utilitario.UtilitarioTela;

import java.awt.Component;

import javax.swing.border.BevelBorder;

import java.awt.Color;

import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.UIManager;

public class Home extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public Home() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,400);
		
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, new Color(176, 177, 184), new Color(225, 225, 225)));
		contentPane.setBackground(new Color(70, 74, 84));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		Parametros.setPai(this);
		addHomeJogador();
	}
	
	public void addHomeJogador() throws Exception{
		HomeJogador homeJogador = new HomeJogador();
		contentPane.add(homeJogador);
		contentPane.repaint();
	}
	
}
