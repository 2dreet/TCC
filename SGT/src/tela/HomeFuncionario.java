package tela;

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
import utilitario.Computador;
import utilitario.Login;
import utilitario.Parametros;
import utilitario.UtilitarioTela;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.border.MatteBorder;
import javax.swing.JToggleButton;

import java.awt.GridLayout;

import javax.swing.border.EtchedBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.JTextArea;

import dao.ParametroDao;
import dao.PermissaoDao;
import menu.MenuCampeonato;
import menu.MenuComputador;
import menu.MenuConfiguracoes;
import menu.MenuFuncionario;
import menu.MenuJogador;
import menu.MenuRelatorio;
import menu.MenuTime;

public class HomeFuncionario extends JPanel {

	/**
	 * Create the panel.
	 * 
	 */

	private JButton btCampeonato;
	private JButton btTime;
	private JButton btJogador;
	private JButton btConfiguracao;
	private JButton btRelatorio;
	private JButton btPc;
	private JButton btFunc;
	private JLabel baner2;
	private JPanel baixo;
	

	public HomeFuncionario() {
		setSize(UtilitarioTela.getTamanhoMunitorJpanel());
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
		baner.setIcon(new ImageIcon(HomeFuncionario.class
				.getResource("/imagem/baner.png")));
		baner.setBounds(100, 0, 100, 48);
		baner.setBorder(null);
		topo.add(baner);

		baner2 = new JLabel("");
		baner2.setIcon(new ImageIcon(HomeFuncionario.class
				.getResource("/imagem/baner2.png")));
		baner2.setBorder(null);
		baner2.setBounds(210, 0, 600, 48);
		topo.add(baner2);

		JPanel menuTopo = new JPanel();
		menuTopo.setBackground(new Color(46, 49, 56));
		menuTopo.setBorder(null);
		menuTopo.setBounds(0, 51, getWidth(), 62);
		add(menuTopo);
		menuTopo.setLayout(null);

		JPanel menuIconesTopo = new JPanel();
		menuIconesTopo.setBackground(new Color(45, 49, 56));
		menuIconesTopo.setBounds((getWidth() / 2) - 229, 0, 500, 61);
		menuTopo.add(menuIconesTopo);
		menuIconesTopo.setLayout(null);

		baixo = new JPanel();
		baixo.setLocation(0, 120);
		baixo.setSize(UtilitarioTela.getTamanhoMenuBaixo());
		add(baixo);
		baixo.setLayout(null);
		baixo.setBackground(null);

		JButton btSair = new JButton("");
		btSair.setIcon(new ImageIcon(HomeFuncionario.class
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
		btDeslogar.setIcon(new ImageIcon(HomeFuncionario.class
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

		btCampeonato = new JButton("");
		btCampeonato.setBounds(20, 10, 42, 42);
		btCampeonato.setName("campeonato");
		btCampeonato.setBorderPainted(false);
		getIcon(btCampeonato, false);
		btCampeonato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btCampeonato, true);
				abreMenu(new MenuCampeonato());
			}
		});
		menuIconesTopo.add(btCampeonato);

		btTime = new JButton("");
		btTime.setBounds(82, 10, 42, 42);
		btTime.setName("time");
		btTime.setBorderPainted(false);
		getIcon(btTime, false);
		btTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btTime, true);
				abreMenu(new MenuTime());
			}
		});
		menuIconesTopo.add(btTime);

		btJogador = new JButton("");
		btJogador.setBounds(144, 10, 42, 42);
		btJogador.setName("jogador");
		btJogador.setBorderPainted(false);
		getIcon(btJogador, false);
		btJogador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btJogador, true);
				abreMenu(new MenuJogador());
			}
		});
		menuIconesTopo.add(btJogador);

		btConfiguracao = new JButton("");
		btConfiguracao.setBounds(206, 10, 42, 42);
		btConfiguracao.setName("configuracao");
		btConfiguracao.setBorderPainted(false);
		getIcon(btConfiguracao, false);
		btConfiguracao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btConfiguracao, true);
				abreMenu(new MenuConfiguracoes());
			}
		});
		menuIconesTopo.add(btConfiguracao);

		btRelatorio = new JButton("");
		btRelatorio.setBounds(268, 10, 42, 42);
		btRelatorio.setName("relatorio");
		btRelatorio.setBorderPainted(false);
		getIcon(btRelatorio, false);
		btRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btRelatorio, true);
				abreMenu(new MenuRelatorio());
			}
		});
		menuIconesTopo.add(btRelatorio);

		btPc = new JButton("");
		btPc.setBounds(330, 10, 42, 42);
		btPc.setName("pc");
		btPc.setBorderPainted(false);
		getIcon(btPc, false);
		btPc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btPc, true);
				abreMenu(new MenuComputador());
			}
		});
		menuIconesTopo.add(btPc);
		
		btFunc = new JButton("");
		btFunc.setBounds(396, 10, 42, 42);
		btFunc.setName("funcionario");
		btFunc.setBorderPainted(false);
		getIcon(btFunc, false);
		btFunc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btFunc, true);
				abreMenu(new MenuFuncionario());
			}
		});
		menuIconesTopo.add(btFunc);
		
		
		if(utilitario.Login.usuario.getUsuario().getPermissao().getCodigoPermissao() != 3){
			btFunc.setEnabled(false);
		} else {
			btFunc.setEnabled(true);
		}
		
		zeraSelecao();
		getIcon(btCampeonato, true);
		abreMenu(new MenuCampeonato());
	}
	
	public void abreMenu(JPanel panel){
		Computador.stopPCconectado();
		baixo.removeAll();
		baixo.add(panel);
		baixo.revalidate();
		baixo.repaint();
		
	}
	 public void atualizarPermissao(){
		 if(utilitario.Login.usuario.getUsuario().getPermissao().getCodigoPermissao() != 3){
			btFunc.setEnabled(false);
		} else {
			btFunc.setEnabled(true);
		}
	 }
	

	public void zeraSelecao() {
		btCampeonato.setIcon(new ImageIcon(HomeFuncionario.class
				.getResource("/imagem/camp.png")));
		btCampeonato.setBackground(getBtnFundo(false));

		btJogador.setIcon(new ImageIcon(HomeFuncionario.class
				.getResource("/imagem/jogador.png")));
		btJogador.setBackground(getBtnFundo(false));

		btTime.setIcon(new ImageIcon(HomeFuncionario.class
				.getResource("/imagem/time.png")));
		btTime.setBackground(getBtnFundo(false));

		btRelatorio.setIcon(new ImageIcon(HomeFuncionario.class
				.getResource("/imagem/relatorio.png")));
		btRelatorio.setBackground(getBtnFundo(false));

		btConfiguracao.setIcon(new ImageIcon(HomeFuncionario.class
				.getResource("/imagem/conf.png")));
		btConfiguracao.setBackground(getBtnFundo(false));

		btPc.setIcon(new ImageIcon(HomeFuncionario.class
				.getResource("/imagem/pc.png")));
		btPc.setBackground(getBtnFundo(false));
		repaint();
		
		btFunc.setIcon(new ImageIcon(HomeFuncionario.class
				.getResource("/imagem/func.png")));
		btFunc.setBackground(getBtnFundo(false));
		repaint();
		
		
	}

	public void getIcon(JButton botao, boolean selecionado) {
		String url = "";
		if (botao.getName() != null) {
			if (botao.getName().equals("inicio")) {
				if (selecionado) {
					url = "/imagem/home_select.png";
				} else {
					url = "/imagem/home.png";
				}
			} else if (botao.getName().equals("campeonato")) {
				if (selecionado) {
					url = "/imagem/camp_select.png";
				} else {
					url = "/imagem/camp.png";
				}
			} else if (botao.getName().equals("time")) {
				if (selecionado) {
					url = "/imagem/time_select.png";
				} else {
					url = "/imagem/time.png";
				}
			} else if (botao.getName().equals("jogador")) {
				if (selecionado) {
					url = "/imagem/jogador_select.png";
				} else {
					url = "/imagem/jogador.png";
				}
			} else if (botao.getName().equals("configuracao")) {
				if (selecionado) {
					url = "/imagem/conf_select.png";
				} else {
					url = "/imagem/conf.png";
				}
			} else if (botao.getName().equals("relatorio")) {
				if (selecionado) {
					url = "/imagem/relatorio_select.png";
				} else {
					url = "/imagem/relatorio.png";
				}
			} else if (botao.getName().equals("pc")) {
				if (selecionado) {
					url = "/imagem/pc_select.png";
				} else {
					url = "/imagem/pc.png";
				}
			} else if (botao.getName().equals("funcionario")) {
				if (selecionado) {
					url = "/imagem/func_select.png";
				} else {
					url = "/imagem/func.png";
				}
			}
			

			botao.setBackground(getBtnFundo(selecionado));
			botao.setIcon(new ImageIcon(HomeFuncionario.class.getResource(url)));
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
