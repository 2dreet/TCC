package main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import utilitario.BordaEscura;
import utilitario.Login;
import utilitario.Parametros;
import utilitario.UtilitarioTela;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class HomeJogador extends JPanel {

	/**
	 * Create the panel.
	 * 
	 */

	private JButton btHome;
	private JButton btDriver;
	private JButton btConfiguracoes;
	private JLabel baner2;
	private JPanel baixo;
	

	public HomeJogador() {
		setSize(500,400);
		setLocation(5, 5);
		setLayout(null);
		setBackground(new Color(102, 102, 102));

		JPanel topo = new JPanel();
		topo.setBorder(new BordaEscura());
		topo.setBackground(new Color(46, 49, 56));
		topo.setBounds(0, 0, getWidth(), 51);
		add(topo);
		topo.setLayout(null);

		JLabel baner = new JLabel("");
		baner.setIcon(new ImageIcon(HomeJogador.class
				.getResource("/imagem/baner.png")));
		baner.setBounds(100, 0, 100, 48);
		baner.setBorder(null);
		topo.add(baner);

		baner2 = new JLabel("");
		baner2.setIcon(new ImageIcon(HomeJogador.class
				.getResource("/imagem/baner2.png")));
		baner2.setBorder(null);
		baner2.setBounds(210, 0, 600, 48);
		topo.add(baner2);

		JPanel menuLateral = new JPanel();
		menuLateral.setSize(UtilitarioTela.getTamanhoMenuLateral());
		menuLateral.setBorder(new BordaEscura());
		menuLateral.setBackground(new Color(252, 79, 63));
		menuLateral.setLocation(0, 0);
		add(menuLateral);
		menuLateral.setLayout(null);
		
		JPanel menuLateralBaixo = new JPanel();
		menuLateralBaixo.setBounds(0, 30, 240, getHeight() - 30);
		menuLateralBaixo.setBackground(new Color(46, 49, 56));
		menuLateralBaixo.setLayout(null);
		menuLateral.add(menuLateralBaixo);

		
		baixo = new JPanel();
		baixo.setLocation(0, 120);
		baixo.setSize(UtilitarioTela.getTamanhoMenuBaixo());
		add(baixo);
		baixo.setLayout(null);
		baixo.setBackground(null);

		JButton btSair = new JButton("");
		btSair.setIcon(new ImageIcon(HomeJogador.class
				.getResource("/imagem/power.png")));
		btSair.setName("inicio");
		btSair.setBounds(getWidth() - 45, 3, 42, 42);
		btSair.setBorderPainted(false);
		btSair.setBackground(new Color(46, 49, 56));
		btSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		topo.add(btSair);
		
		JButton btDeslogar = new JButton("");
		btDeslogar.setIcon(new ImageIcon(HomeJogador.class
				.getResource("/imagem/sair.png")));
		btDeslogar.setName("inicio");
		btDeslogar.setBounds(getWidth() - 95, 3, 42, 42);
		btDeslogar.setBorderPainted(false);
		btDeslogar.setBackground(new Color(46, 49, 56));
		btDeslogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login.usuario = null;
				Parametros.getPai().dispose();
				try {
					TelaLogin tl = new TelaLogin();
					tl.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(0);
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		topo.add(btDeslogar);

		JPanel jp3 = new JPanel();
		jp3.setBounds(0, 80, 240, 40);
		jp3.setBackground(null);
		jp3.setLayout(null);
		jp3.setBorder(new BordaEscura());
		menuLateralBaixo.add(jp3);
		
		btDriver = new JButton("");
		btDriver.setBounds(20, 10, 42, 42);
		btDriver.setName("driver");
		btDriver.setBorderPainted(false);
		getIcon(btDriver, false);
		btDriver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btDriver, true);
				
			}
		});
		jp3.add(btDriver);

		
		JPanel jp2 = new JPanel();
		jp2.setBounds(0, 40, 240, 40);
		jp2.setBackground(null);
		jp2.setLayout(null);
		jp2.setBorder(new BordaEscura());
		menuLateralBaixo.add(jp2);
		
		btConfiguracoes = new JButton("");
		btConfiguracoes.setBounds(82, 10, 42, 42);
		btConfiguracoes.setName("configuracoes");
		btConfiguracoes.setBorderPainted(false);
		getIcon(btConfiguracoes, false);
		btConfiguracoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btConfiguracoes, true);
				
			}
		});
		jp2.add(btConfiguracoes);

		JPanel jp1 = new JPanel();
		jp1.setBounds(0, 0, 240, 40);
		jp1.setBackground(null);
		jp1.setLayout(null);
		jp1.setBorder(new BordaEscura());
		menuLateralBaixo.add(jp1);
		
		btHome = new JButton("");
		btHome.setBounds(144, 10, 42, 42);
		btHome.setName("home");
		btHome.setBorderPainted(false);
		getIcon(btHome, false);
		btHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btHome, true);
				
			}
		});
		jp1.add(btHome);

		
		zeraSelecao();
		getIcon(btHome, true);
		
	}
	
	public void abreMenu(JPanel panel){
		baixo.removeAll();
		baixo.add(panel);
		baixo.repaint();
	}
	

	public void zeraSelecao() {
		btHome.setIcon(new ImageIcon(HomeJogador.class
				.getResource("/imagem/camp.png")));
		btHome.setBackground(getBtnFundo(false));

		btConfiguracoes.setIcon(new ImageIcon(HomeJogador.class
				.getResource("/imagem/jogador.png")));
		btConfiguracoes.setBackground(getBtnFundo(false));

		btDriver.setIcon(new ImageIcon(HomeJogador.class
				.getResource("/imagem/time.png")));
		btDriver.setBackground(getBtnFundo(false));

		repaint();
		
		
	}

	public void getIcon(JButton botao, boolean selecionado) {
		String url = "";
		if (botao.getName() != null) {
			if (botao.getName().equals("driver")) {
				if (selecionado) {
					url = "/imagem/home_select.png";
				} else {
					url = "/imagem/home.png";
				}
			} else if (botao.getName().equals("configuracoes")) {
				if (selecionado) {
					url = "/imagem/camp_select.png";
				} else {
					url = "/imagem/camp.png";
				}
			} else if (botao.getName().equals("home")) {
				if (selecionado) {
					url = "/imagem/time_select.png";
				} else {
					url = "/imagem/time.png";
				}
			} 

			botao.setBackground(getBtnFundo(selecionado));
			botao.setIcon(new ImageIcon(HomeJogador.class.getResource(url)));
			repaint();
		}
	}

	public Color getBtnFundo(boolean selecionado) {
		int r = 0, g = 0, b = 0;

		if (selecionado) {
			r = 252;
			g = 79;
			b = 63;
		} else {
			r = 46;
			g = 49;
			b = 56;
		}

		return new Color(r, g, b);
	}
}
