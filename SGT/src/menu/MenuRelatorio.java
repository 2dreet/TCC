package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import crud.CrudCampeonato;
import entidade.Campeonato;
import tela.HomeFuncionario;
import telaRelatorios.RelatoriosBanimento;
import telaRelatorios.RelatoriosCampeonato;
import telaRelatorios.RelatoriosDriver;
import telaRelatorios.RelatoriosJogador;
import telaRelatorios.RelatoriosTime;
import utilitario.BordaEscura;
import utilitario.ParametroCrud;
import utilitario.UtilitarioTela;

public class MenuRelatorio extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private JButton btRelatorioCampeonato;
	private JButton btRelatorioBanimento;
	private JButton btRelatorioDriver;
	private JButton btRelatorioJogador;
	private JButton btRelatorioTime;
	private JPanel menuMeio;
	
	public MenuRelatorio() {
		setSize(UtilitarioTela.getTamanhoMenuBaixo());
		setBackground(null);
		setLayout(null);
		setVisible(true);

		JPanel menuLateral = new JPanel();
		menuLateral.setSize(UtilitarioTela.getTamanhoMenuLateral());
		menuLateral.setBorder(new BordaEscura());
		menuLateral.setBackground(new Color(252, 79, 63));
		menuLateral.setLocation(0, 0);
		add(menuLateral);
		menuLateral.setLayout(null);

		JPanel menuLateralTopo = new JPanel();
		menuLateralTopo.setBounds(0, 0, 240, 30);
		menuLateralTopo.setBorder(new BordaEscura());
		menuLateralTopo.setBackground(new Color(232, 234, 239));
		menuLateral.add(menuLateralTopo);
		menuLateralTopo.setLayout(null);

		JLabel tituloMenu = new JLabel("Relatório");
		tituloMenu.setForeground(Color.DARK_GRAY);
		tituloMenu.setFont(new Font("SansSerif", Font.BOLD, 18));
		tituloMenu.setHorizontalAlignment(SwingConstants.CENTER);
		tituloMenu.setBounds(0, 0, 240, 25);
		menuLateralTopo.add(tituloMenu);

		menuMeio = new JPanel();
		menuMeio.setSize(UtilitarioTela.getTamanhoMeio());
		menuMeio.setLocation(250, 0);
		menuMeio.setLayout(null);
		menuMeio.setBackground(new Color(46, 49, 56));
		add(menuMeio);

		JPanel menuLateralBaixo = new JPanel();
		menuLateralBaixo.setBounds(0, 30, 240, getHeight() - 30);
		menuLateralBaixo.setBackground(new Color(46, 49, 56));
		menuLateralBaixo.setLayout(null);
		menuLateral.add(menuLateralBaixo);
		
		JPanel jp1 = new JPanel();
		jp1.setBounds(0, 0, getWidth(), 40);
		jp1.setBackground(null);
		jp1.setLayout(null);
		jp1.setBorder(new BordaEscura());
		menuLateralBaixo.add(jp1);
		
		btRelatorioCampeonato = new JButton("Relatórios Campeonato");
		btRelatorioCampeonato.setBounds(5, 5, 230, 30);
		btRelatorioCampeonato.setBorderPainted(false);
		btRelatorioCampeonato.setBackground(null);
		btRelatorioCampeonato.setLayout(null);
		btRelatorioCampeonato.setName("campeonato");
		btRelatorioCampeonato.setHorizontalAlignment(SwingConstants.LEFT);
		btRelatorioCampeonato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btRelatorioCampeonato, true);
				alterarCampeonato();
			}
		});
		jp1.add(btRelatorioCampeonato);
		
		JPanel jp2 = new JPanel();
		jp2.setBounds(0, 40, getWidth(), 40);
		jp2.setBackground(null);
		jp2.setLayout(null);
		jp2.setBorder(new BordaEscura());
		menuLateralBaixo.add(jp2);
		
		btRelatorioBanimento = new JButton("Relatórios Banimento");
		btRelatorioBanimento.setBounds(5, 5, 230, 30);
		btRelatorioBanimento.setBorderPainted(false);
		btRelatorioBanimento.setBackground(null);
		btRelatorioBanimento.setLayout(null);
		btRelatorioBanimento.setName("banimento");
		btRelatorioBanimento.setHorizontalAlignment(SwingConstants.LEFT);
		btRelatorioBanimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btRelatorioBanimento, true);
				alterarBanimento();
			}
		});
		jp2.add(btRelatorioBanimento);
		
		
		JPanel jp3 = new JPanel();
		jp3.setBounds(0, 80, getWidth(), 40);
		jp3.setBackground(null);
		jp3.setLayout(null);
		jp3.setBorder(new BordaEscura());
		menuLateralBaixo.add(jp3);
		
		btRelatorioDriver = new JButton("Relatórios Drivers");
		btRelatorioDriver.setBounds(5, 5, 230, 30);
		btRelatorioDriver.setBorderPainted(false);
		btRelatorioDriver.setBackground(null);
		btRelatorioDriver.setLayout(null);
		btRelatorioDriver.setName("driver");
		btRelatorioDriver.setHorizontalAlignment(SwingConstants.LEFT);
		btRelatorioDriver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btRelatorioDriver, true);
				alterarDriver();
			}
		});
		jp3.add(btRelatorioDriver);
		
		JPanel jp4 = new JPanel();
		jp4.setBounds(0, 120, getWidth(), 40);
		jp4.setBackground(null);
		jp4.setLayout(null);
		jp4.setBorder(new BordaEscura());
		menuLateralBaixo.add(jp4);
		
		btRelatorioJogador = new JButton("Relatórios Jogador");
		btRelatorioJogador.setBounds(5, 5, 230, 30);
		btRelatorioJogador.setBorderPainted(false);
		btRelatorioJogador.setBackground(null);
		btRelatorioJogador.setLayout(null);
		btRelatorioJogador.setName("jogador");
		btRelatorioJogador.setHorizontalAlignment(SwingConstants.LEFT);
		btRelatorioJogador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btRelatorioJogador, true);
				alterarJogador();
			}
		});
		jp4.add(btRelatorioJogador);
		
		JPanel jp5 = new JPanel();
		jp5.setBounds(0, 160, getWidth(), 40);
		jp5.setBackground(null);
		jp5.setLayout(null);
		jp5.setBorder(new BordaEscura());
		menuLateralBaixo.add(jp5);
		
		btRelatorioTime = new JButton("Relatórios Time");
		btRelatorioTime.setBounds(5, 5, 230, 30);
		btRelatorioTime.setBorderPainted(false);
		btRelatorioTime.setBackground(null);
		btRelatorioTime.setLayout(null);
		btRelatorioTime.setName("time");
		btRelatorioTime.setHorizontalAlignment(SwingConstants.LEFT);
		btRelatorioTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btRelatorioTime, true);
				alterarTime();
			}
		});
		jp5.add(btRelatorioTime);
		
		getIcon(btRelatorioCampeonato, false);
		getIcon(btRelatorioBanimento, false);
		getIcon(btRelatorioDriver, false);
		getIcon(btRelatorioJogador, false);
		getIcon(btRelatorioTime, false);
		

		btRelatorioCampeonato.setForeground(UtilitarioTela.getFontColorSelecao(false));
		btRelatorioBanimento.setForeground(UtilitarioTela.getFontColorSelecao(false));
		btRelatorioDriver.setForeground(UtilitarioTela.getFontColorSelecao(false));
		btRelatorioJogador.setForeground(UtilitarioTela.getFontColorSelecao(false));
		btRelatorioTime.setForeground(UtilitarioTela.getFontColorSelecao(false));
		

		btRelatorioCampeonato.setFont(UtilitarioTela.getFont(14));
		btRelatorioBanimento.setFont(UtilitarioTela.getFont(14));
		btRelatorioDriver.setFont(UtilitarioTela.getFont(14));
		btRelatorioJogador.setFont(UtilitarioTela.getFont(14));
		btRelatorioTime.setFont(UtilitarioTela.getFont(14));
		

		zeraSelecao();
		getIcon(btRelatorioCampeonato, true);
		alterarCampeonato();
		
	}
	
	public void alterarCampeonato() {
		menuMeio.removeAll();
		RelatoriosCampeonato c = new RelatoriosCampeonato(menuMeio.getSize());
		menuMeio.add(c);
		menuMeio.revalidate();
		menuMeio.repaint();
	}
	
	public void alterarBanimento() {
		menuMeio.removeAll();
		RelatoriosBanimento c = new RelatoriosBanimento(menuMeio.getSize());
		menuMeio.add(c);
		menuMeio.revalidate();
		menuMeio.repaint();
	}
	
	public void alterarDriver() {
		menuMeio.removeAll();
		RelatoriosDriver c = new RelatoriosDriver(menuMeio.getSize());
		menuMeio.add(c);
		menuMeio.revalidate();
		menuMeio.repaint();
	}
	
	public void alterarJogador() {
		menuMeio.removeAll();
		RelatoriosJogador c = new RelatoriosJogador(menuMeio.getSize());
		menuMeio.add(c);
		menuMeio.revalidate();
		menuMeio.repaint();
	}
	
	public void alterarTime() {
		menuMeio.removeAll();
		RelatoriosTime c = new RelatoriosTime(menuMeio.getSize());
		menuMeio.add(c);
		menuMeio.revalidate();
		menuMeio.repaint();
	}
	
	public void zeraSelecao() {

		btRelatorioCampeonato.setBackground(UtilitarioTela.getBtnFundo(false));
		btRelatorioCampeonato.setForeground(UtilitarioTela.getFontColorSelecao(false));

		btRelatorioBanimento.setBackground(UtilitarioTela.getBtnFundo(false));
		btRelatorioBanimento.setForeground(UtilitarioTela.getFontColorSelecao(false));

		btRelatorioDriver.setBackground(UtilitarioTela.getBtnFundo(false));
		btRelatorioDriver.setForeground(UtilitarioTela.getFontColorSelecao(false));

		btRelatorioJogador.setBackground(UtilitarioTela.getBtnFundo(false));
		btRelatorioJogador.setForeground(UtilitarioTela.getFontColorSelecao(false));

		btRelatorioTime.setBackground(UtilitarioTela.getBtnFundo(false));
		btRelatorioTime.setForeground(UtilitarioTela.getFontColorSelecao(false));
		
	}
	

	public void getIcon(JButton botao, boolean selecionado) {
		botao.setFocusPainted(false);
		botao.setBackground(UtilitarioTela.getBtnFundo(selecionado));
		botao.setForeground(UtilitarioTela.getFontColorSelecao(true));
		repaint();
	}
	
}
