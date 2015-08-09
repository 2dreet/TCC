package tela;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import utilitario.DropShadowBorder;
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

public class HomeFuncionario extends JPanel {

	/**
	 * Create the panel.
	 * 
	 */

	private JButton btInicio;
	private JButton btCampeonato;
	private JButton btTime;
	private JButton btJogador;
	private JButton btDriver;
	private JButton btRelatorio;
	private JButton btPc;

	public HomeFuncionario() {
		setSize(UtilitarioTela.getTamanhoMunitor());
		setLayout(null);
		setBackground(new Color(102, 102, 102));
		
		DropShadowBorder shadow = new DropShadowBorder();
		
		JPanel topo = new JPanel();
		topo.setBackground(new Color(46, 49, 56));
		topo.setBorder(shadow);
		topo.setBounds(0, 0, getWidth(), 51);
		add(topo);

		
        
		
		JPanel menuTopo = new JPanel();
		menuTopo.setBackground(new Color(46, 49, 56));
		menuTopo.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(0, 0, 0)));
		menuTopo.setBounds(0, 51, getWidth(), 62);
		add(menuTopo);
		menuTopo.setLayout(null);
		
		
		JPanel menuIconesTopo = new JPanel();
		menuIconesTopo.setBackground(new Color(45, 49, 56));
		menuIconesTopo.setBounds((getWidth()/2)-229, 0, 458, 61);
		menuTopo.add(menuIconesTopo);
		menuIconesTopo.setLayout(null);

		btInicio = new JButton("");
		btInicio.setBounds(20, 10, 42, 42);
		menuIconesTopo.add(btInicio);
		btInicio.setBackground(getBtnFundo(true));
		btInicio.setName("inicio");
		getIcon(btInicio, true);
		btInicio.setBorderPainted(false);
		
		btCampeonato = new JButton("");
		btCampeonato.setBounds(82, 10, 42, 42);
		btCampeonato.setName("campeonato");
		btCampeonato.setBorderPainted(false);
		btCampeonato.setBackground(getBtnFundo(false));
		getIcon(btCampeonato, false);
		menuIconesTopo.add(btCampeonato);
		
		btTime = new JButton("");
		btTime.setBounds(144, 10, 42, 42);
		btTime.setName("time");
		btTime.setBorderPainted(false);
		getIcon(btTime, false);
		btTime.setBackground(getBtnFundo(false));
		menuIconesTopo.add(btTime);
		
		btJogador = new JButton("");
		btJogador.setBounds(206, 10, 42, 42);
		btJogador.setName("jogador");
		btJogador.setBorderPainted(false);
		getIcon(btJogador, false);
		btJogador.setBackground(getBtnFundo(false));
		menuIconesTopo.add(btJogador);
		
		btDriver = new JButton("");
		btDriver.setBounds(268, 10, 42, 42);
		btDriver.setName("driver");
		btDriver.setBorderPainted(false);
		getIcon(btDriver, false);
		btDriver.setBackground(getBtnFundo(false));
		menuIconesTopo.add(btDriver);
		
		btRelatorio = new JButton("");
		btRelatorio.setBounds(330, 10, 42, 42); 
		btRelatorio.setName("relatorio");
		btRelatorio.setBorderPainted(false);
		getIcon(btRelatorio, false);
		btRelatorio.setBackground(getBtnFundo(false));
		menuIconesTopo.add(btRelatorio);
		
		btPc = new JButton("");
		btPc.setBounds(392, 10, 42, 42);
		btPc.setName("pc");
		btPc.setBorderPainted(false);
		getIcon(btPc, false);
		btPc.setBackground(getBtnFundo(false));
		menuIconesTopo.add(btPc);

		/*
		 * ImageIcon img = new
		 * ImageIcon(HomeFuncionario.class.getResource("/imagem/menuTopo.png"));
		 * for(int x = 0; x < menuTopo.getWidth(); x = x+3){ JLabel imagemTopo =
		 * new JLabel(img); imagemTopo.setBounds(x, 0, 5, menuTopo.getHeight());
		 * menuTopo.add(imagemTopo); }
		 */

		JPanel menulateral = new JPanel();
		menulateral.setBackground(new Color(46, 49, 56));
		menulateral.setBorder(new MatteBorder(1, 0, 0, 1, (Color) new Color(68,
				68, 68)));
		menulateral.setBounds(0, 124, 200, 568);
		add(menulateral);

		JPanel meio = new JPanel();
		meio.setBackground(new Color(46, 49, 56));
		meio.setBorder(new MatteBorder(1, 1, 0, 0,
				(Color) new Color(68, 68, 68)));
		meio.setBounds(210, 124, 1020, 568);
		add(meio);
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
			} else if (botao.getName().equals("driver")) {
				if (selecionado) {
					url = "/imagem/controle_select.png";
				} else {
					url = "/imagem/controle.png";
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
			}
		
			botao.setIcon(new ImageIcon(HomeFuncionario.class.getResource(url)));
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
