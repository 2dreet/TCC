package Telas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
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
import java.net.InetAddress;
import java.net.UnknownHostException;


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
	private JPanel menuMeio;

	public HomeJogador() throws Exception {
		setSize(490,390);
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
		baner.setBounds(0, 0, 100, 48);
		baner.setBorder(null);
		topo.add(baner);

		JPanel menuLateral = new JPanel();
		menuLateral.setSize(200, 350);
		menuLateral.setBorder(new BordaEscura());
		menuLateral.setBackground(Color.white);
		menuLateral.setLocation(0, 60);
		add(menuLateral);
		menuLateral.setLayout(null);
		
		JLabel baner2 = new JLabel("News");
		baner2.setHorizontalAlignment(SwingConstants.CENTER);
		baner2.setBounds(0, 0, 200, 30);
		baner2.setBorder(null);
		menuLateral.add(baner2);
		

		JPanel menuLateralBaixo = new JPanel();
		menuLateralBaixo.setBounds(0, 30, 200, 350);
		menuLateralBaixo.setBackground(new Color(46, 49, 56));
		menuLateralBaixo.setLayout(null);
		menuLateral.add(menuLateralBaixo);

		JLabel ip = new JLabel("IP: "+InetAddress.getLocalHost().getHostAddress());
		ip.setHorizontalAlignment(SwingConstants.CENTER);
		ip.setForeground(UtilitarioTela.getFontColorPadrao());
		ip.setFont(UtilitarioTela.getFont(12));
		ip.setBounds(0, 270, 200, 30);
		ip.setBorder(null);
		menuLateralBaixo.add(ip);
		
		menuMeio = new JPanel();
		menuMeio.setSize(280, getHeight() - 30);
		menuMeio.setLocation(210, 60);
		menuMeio.setLayout(null);
		menuMeio.setBackground(new Color(46, 49, 56));
		add(menuMeio);
		
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
		btSair.setBounds(getWidth() - 42, 3, 42, 42);
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

		JPanel jp1 = new JPanel();
		jp1.setBounds(0, 0, 200, 50);
		jp1.setBackground(null);
		jp1.setLayout(null);
		jp1.setBorder(new BordaEscura());
		menuLateralBaixo.add(jp1);
		
		btHome = new JButton("Início");
		btHome.setBounds(0, 3, 200, 42);
		btHome.setName("home");
		btHome.setBorderPainted(false);
		btHome.setFocusPainted(false);
		btHome.setHorizontalAlignment(SwingConstants.LEFT);
		getIcon(btHome, false);
		btHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btHome, true);
				alterarMenuHome();
			}
		});
		jp1.add(btHome);

		JPanel jp2 = new JPanel();
		jp2.setBounds(0, 50, 200, 50);
		jp2.setBackground(null);
		jp2.setLayout(null);
		jp2.setBorder(new BordaEscura());
		menuLateralBaixo.add(jp2);
		
		btConfiguracoes = new JButton("Configurações");
		btConfiguracoes.setBounds(0, 2, 200, 42);
		btConfiguracoes.setName("configuracoes");
		btConfiguracoes.setBorderPainted(false);
		btConfiguracoes.setFocusPainted(false);
		btConfiguracoes.setHorizontalAlignment(SwingConstants.LEFT);
		getIcon(btConfiguracoes, false);
		btConfiguracoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btConfiguracoes, true);
				alterarMenuConfiguracao();
			}
		});
		jp2.add(btConfiguracoes);
		
		JPanel jp3 = new JPanel();
		jp3.setBounds(0, 100, 200, 50);
		jp3.setBackground(null);
		jp3.setLayout(null);
		jp3.setBorder(new BordaEscura());
		menuLateralBaixo.add(jp3);
		
		btDriver = new JButton("Drivers");
		btDriver.setBounds(0, 2, 200, 42);
		btDriver.setName("driver");
		btDriver.setFocusPainted(false);
		btDriver.setBorderPainted(false);
		btDriver.setHorizontalAlignment(SwingConstants.LEFT);
		getIcon(btDriver, false);
		btDriver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btDriver, true);
				alterarMenuDriver();
			}
		});
		jp3.add(btDriver);

		
		btConfiguracoes.setForeground(UtilitarioTela.getFontColorSelecao(false));
		btDriver.setForeground(UtilitarioTela.getFontColorSelecao(false));
		btHome.setForeground(UtilitarioTela.getFontColorSelecao(false));

		btHome.setFont(UtilitarioTela.getFont(14));
		btConfiguracoes.setFont(UtilitarioTela.getFont(14));
		btDriver.setFont(UtilitarioTela.getFont(14));

		
		zeraSelecao();
		getIcon(btHome, true);
		alterarMenuHome();
	}
	
	public void abreMenu(JPanel panel){
		baixo.removeAll();
		baixo.add(panel);
		baixo.repaint();
	}
	

	public void alterarMenuHome(){
		menuMeio.removeAll();
		HomeJPanel c = new HomeJPanel();
		menuMeio.add(c);
		menuMeio.revalidate();
		menuMeio.repaint();
	}
	
	public void alterarMenuConfiguracao(){
		menuMeio.removeAll();
		ConfiguracoesJPanel c = new ConfiguracoesJPanel();
		menuMeio.add(c);
		menuMeio.revalidate();
		menuMeio.repaint();
	}
	
	public void alterarMenuDriver(){
		menuMeio.removeAll();
		DriversJPanel c = new DriversJPanel();
		menuMeio.add(c);
		menuMeio.revalidate();
		menuMeio.repaint();
	}
	
	
	public void zeraSelecao() {
		btHome.setIcon(new ImageIcon(HomeJogador.class
				.getResource("/imagem/home.png")));
		btHome.setBackground(getBtnFundo(false));
		btHome.setForeground(UtilitarioTela.getFontColorSelecao(false));

		btConfiguracoes.setIcon(new ImageIcon(HomeJogador.class
				.getResource("/imagem/conf.png")));
		btConfiguracoes.setBackground(getBtnFundo(false));
		btConfiguracoes.setForeground(UtilitarioTela.getFontColorSelecao(false));

		btDriver.setIcon(new ImageIcon(HomeJogador.class
				.getResource("/imagem/controle.png")));
		btDriver.setBackground(getBtnFundo(false));
		btDriver.setForeground(UtilitarioTela.getFontColorSelecao(false));

		repaint();
		
		
	}

	public void getIcon(JButton botao, boolean selecionado) {
		String url = "";
		if (botao.getName() != null) {
			if (botao.getName().equals("home")) {
				if (selecionado) {
					url = "/imagem/home_select.png";
				} else {
					url = "/imagem/home.png";
				}
			} else if (botao.getName().equals("driver")) {
				if (selecionado) {
					url = "/imagem/controle_select.png";
				} else {
					url = "/imagem/controle.png";
				}
			} else if (botao.getName().equals("configuracoes")) {
				if (selecionado) {
					url = "/imagem/conf_select.png";
				} else {
					url = "/imagem/conf.png";
				}
			} 

			botao.setFocusPainted(false);
			botao.setBackground(UtilitarioTela.getBtnFundo(selecionado));
			if (url != null) {
				botao.setIcon(new ImageIcon(HomeJogador.class.getResource(url)));
			}
			botao.setForeground(UtilitarioTela.getFontColorSelecao(true));
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
