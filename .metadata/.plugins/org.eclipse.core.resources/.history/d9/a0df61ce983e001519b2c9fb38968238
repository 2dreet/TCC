package tela;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utilitario.UtilitarioTela;
import java.awt.Component;
import javax.swing.border.BevelBorder;
import java.awt.Color;

public class Home extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(UtilitarioTela.getTamanhoMunitor()); 
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(46, 49, 56));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		addHomeFuncionario();
	}
	
	public void addHomeFuncionario(){
		HomeFuncionario homeFuncionario = new HomeFuncionario();
		contentPane.add(homeFuncionario);
		contentPane.repaint();
	}
	
}
