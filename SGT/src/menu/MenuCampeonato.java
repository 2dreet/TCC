package menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import crud.CrudCampeonato;
import crud.CrudComputador;
import crud.CrudJogador;
import crud.CrudPartida;
import entidade.Campeonato;
import entidade.Jogador;
import entidade.Pc;
import entidade.Usuario;
import tela.HomeFuncionario;
import utilitario.BordaEscura;
import utilitario.Computador;
import utilitario.ParametroCrud;
import utilitario.Parametros;
import utilitario.UtilitarioTela;

import java.awt.Font;

import localizar.LocalizarCampeonato;
import localizar.LocalizarJogador;
import localizar.LocalizarPc;

public class MenuCampeonato extends JPanel {

	/**
	 * Create the panel.
	 */
	private JPanel menuMeio;
	private JButton btLocalizar;
	private JButton btNovo;
	private JButton btAlterar;
	private JButton btDeletar;
	private JButton btVisualizar;
	public JButton btPartida;
	private Campeonato campSelecionado;

	public MenuCampeonato() {
		campSelecionado = null;

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

		JLabel tituloMenu = new JLabel("Campeonato");
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
		jp1.setBounds(0, 0, 240, 40);
		jp1.setBackground(null);
		jp1.setLayout(null);
		jp1.setBorder(new BordaEscura());
		menuLateralBaixo.add(jp1);

		btLocalizar = new JButton("Localizar");
		btLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btLocalizar, true);
				limpar();
				localizarCampeonato();
			}
		});
		btLocalizar.setBounds(5, 5, 230, 30);
		btLocalizar.setName("localizar");
		btLocalizar.setBorderPainted(false);
		btLocalizar.setHorizontalAlignment(SwingConstants.LEFT);
		getIcon(btLocalizar, false);
		jp1.add(btLocalizar);

		JPanel jp2 = new JPanel();
		jp2.setBounds(0, 40, 240, 40);
		jp2.setBackground(null);
		jp2.setLayout(null);
		jp2.setBorder(new BordaEscura());
		menuLateralBaixo.add(jp2);

		btNovo = new JButton("Cadastrar");

		btNovo.setBounds(5, 5, 230, 30);
		btNovo.setBorderPainted(false);
		btNovo.setBackground(null);
		btNovo.setLayout(null);
		btNovo.setName("cadastrarCampeonato");
		btNovo.setHorizontalAlignment(SwingConstants.LEFT);
		btNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btNovo, true);
				limpar();
				alterarMenu(null, ParametroCrud.getModoCrudNovo());
			}
		});
		jp2.add(btNovo);

		JPanel jp3 = new JPanel();
		jp3.setBounds(0, 80, 240, 40);
		jp3.setBackground(null);
		jp3.setLayout(null);
		jp3.setBorder(new BordaEscura());
		menuLateralBaixo.add(jp3);

		btVisualizar = new JButton("Visualizar");
		btVisualizar.setBounds(5, 5, 230, 30);
		btVisualizar.setBorderPainted(false);
		btVisualizar.setBackground(null);
		btVisualizar.setLayout(null);
		btVisualizar.setName("visualizarCampeonato");
		btVisualizar.setHorizontalAlignment(SwingConstants.LEFT);
		btVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btVisualizar, true);
				alterarMenu(campSelecionado, ParametroCrud.getModoVisualizar());
			}
		});
		jp3.add(btVisualizar);

		JPanel jp4 = new JPanel();
		jp4.setBounds(0, 120, 240, 40);
		jp4.setBackground(null);
		jp4.setLayout(null);
		jp4.setBorder(new BordaEscura());
		menuLateralBaixo.add(jp4);

		btAlterar = new JButton("Alterar");
		btAlterar.setBounds(5, 5, 230, 30);
		btAlterar.setBorderPainted(false);
		btAlterar.setBackground(null);
		btAlterar.setLayout(null);
		btAlterar.setName("alterarCampeonato");
		btAlterar.setHorizontalAlignment(SwingConstants.LEFT);
		btAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btAlterar, true);
				alterarMenu(campSelecionado, ParametroCrud.getModoCrudAlterar());
			}
		});
		jp4.add(btAlterar);

		JPanel jp5 = new JPanel();
		jp5.setBounds(0, 160, 240, 40);
		jp5.setBackground(null);
		jp5.setLayout(null);
		jp5.setBorder(new BordaEscura());
		menuLateralBaixo.add(jp5);

		btDeletar = new JButton("Deletar");
		btDeletar.setBounds(5, 5, 230, 30);
		btDeletar.setBorderPainted(false);
		btDeletar.setBackground(null);
		btDeletar.setLayout(null);
		btDeletar.setName("deletarCampeonato");
		btDeletar.setHorizontalAlignment(SwingConstants.LEFT);
		btDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btDeletar, true);
				alterarMenu(campSelecionado, ParametroCrud.getModoCrudDeletar());
			}
		});

		jp5.add(btDeletar);

		JPanel jp6 = new JPanel();
		jp6.setBounds(0, 200, 240, 40);
		jp6.setBackground(null);
		jp6.setLayout(null);
		jp6.setBorder(new BordaEscura());
		menuLateralBaixo.add(jp6);

		btPartida = new JButton("Partidas");
		btPartida.setBounds(5, 5, 230, 30);
		btPartida.setBorderPainted(false);
		btPartida.setBackground(null);
		btPartida.setLayout(null);
		btPartida.setName("partida");
		btPartida.setHorizontalAlignment(SwingConstants.LEFT);
		btPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				abreMenuPartida(campSelecionado);
			}
		});

		jp6.add(btPartida);

		JPanel jp7 = new JPanel();
		jp7.setBounds(0, 240, 240, 40);
		jp7.setBackground(null);
		jp7.setLayout(null);
		jp7.setBorder(new BordaEscura());
		menuLateralBaixo.add(jp7);

		getIcon(btNovo, false);
		getIcon(btAlterar, false);
		getIcon(btDeletar, false);
		getIcon(btVisualizar, false);
		getIcon(btLocalizar, false);
		getIcon(btPartida, false);

		btDeletar.setForeground(UtilitarioTela.getFontColorSelecao(false));
		btAlterar.setForeground(UtilitarioTela.getFontColorSelecao(false));
		btNovo.setForeground(UtilitarioTela.getFontColorSelecao(false));
		btVisualizar.setForeground(UtilitarioTela.getFontColorSelecao(false));
		btLocalizar.setForeground(UtilitarioTela.getFontColorSelecao(false));
		btPartida.setForeground(UtilitarioTela.getFontColorSelecao(false));

		btNovo.setFont(UtilitarioTela.getFont(14));
		btAlterar.setFont(UtilitarioTela.getFont(14));
		btDeletar.setFont(UtilitarioTela.getFont(14));
		btVisualizar.setFont(UtilitarioTela.getFont(14));
		btLocalizar.setFont(UtilitarioTela.getFont(14));
		btPartida.setFont(UtilitarioTela.getFont(14));
		
		limpar();
	}

	public void home() {
		zeraSelecao();
		getIcon(btLocalizar, true);
		limpar();
		localizarCampeonato();
	}

	public void limpar() {
		btAlterar.setEnabled(false);
		btDeletar.setEnabled(false);
		btVisualizar.setEnabled(false);
		btPartida.setEnabled(false);
	}

	public void exibirCampeonato(Campeonato camp) {
		zeraSelecao();
		getIcon(btVisualizar, true);
		this.campSelecionado = camp;
		liberarCrud();
		alterarMenu(campSelecionado, ParametroCrud.getModoVisualizar());
	}

	public void localizarCampeonato() {
		menuMeio.removeAll();
		LocalizarCampeonato localizar = new LocalizarCampeonato(this);
		menuMeio.add(localizar);
		menuMeio.revalidate();
		menuMeio.repaint();
	}

	public void liberarCrud() {
		btAlterar.setEnabled(true);
		btDeletar.setEnabled(true);
		btVisualizar.setEnabled(true);
		if(campSelecionado.getDataInicio() != null){
			btPartida.setEnabled(true);
		} else{	
			btPartida.setEnabled(false);
		}
	}

	public void abreMenuPartida(Campeonato camp){
		zeraSelecao();
		getIcon(btPartida, true);
		menuMeio.removeAll();
		CrudPartida c = new CrudPartida(camp, this);
		menuMeio.add(c);
		menuMeio.revalidate();
		menuMeio.repaint();
	}
	
	public void alterarMenu(Campeonato camp, int modoCrud) {
		menuMeio.removeAll();
		CrudCampeonato c = new CrudCampeonato(camp, modoCrud, this);
		menuMeio.add(c);
		c.getTxDescricao().requestFocus();
		menuMeio.revalidate();
		menuMeio.repaint();
	}

	public void zeraSelecao() {

		btLocalizar.setIcon(new ImageIcon(HomeFuncionario.class
				.getResource("/imagem/crud/locPc.png")));
		btLocalizar.setBackground(UtilitarioTela.getBtnFundo(false));
		btLocalizar.setForeground(UtilitarioTela.getFontColorSelecao(false));

		btNovo.setIcon(new ImageIcon(HomeFuncionario.class
				.getResource("/imagem/crud/cadCamp.png")));
		btNovo.setBackground(UtilitarioTela.getBtnFundo(false));
		btNovo.setForeground(UtilitarioTela.getFontColorSelecao(false));

		btAlterar.setIcon(new ImageIcon(HomeFuncionario.class
				.getResource("/imagem/crud/altCamp.png")));
		btAlterar.setBackground(UtilitarioTela.getBtnFundo(false));
		btAlterar.setForeground(UtilitarioTela.getFontColorSelecao(false));

		btDeletar.setIcon(new ImageIcon(HomeFuncionario.class
				.getResource("/imagem/crud/delCamp.png")));
		btDeletar.setBackground(UtilitarioTela.getBtnFundo(false));
		btDeletar.setForeground(UtilitarioTela.getFontColorSelecao(false));

		btVisualizar.setIcon(new ImageIcon(HomeFuncionario.class
				.getResource("/imagem/crud/visuPc.png")));
		btVisualizar.setBackground(UtilitarioTela.getBtnFundo(false));
		btVisualizar.setForeground(UtilitarioTela.getFontColorSelecao(false));
		
		btPartida.setBackground(UtilitarioTela.getBtnFundo(false));
		btPartida.setForeground(UtilitarioTela.getFontColorSelecao(false));
	}

	public void getIcon(JButton botao, boolean selecionado) {
		String url = "";
		if (botao.getName() != null) {
			if (botao.getName().equals("localizar")) {
				if (selecionado) {
					url = "/imagem/crud/locPcSelect.png";
				} else {
					url = "/imagem/crud/locPc.png";
				}
			}
			if (botao.getName().equals("cadastrarCampeonato")) {
				if (selecionado) {
					url = "/imagem/crud/cadCampSelect.png";
				} else {
					url = "/imagem/crud/cadCamp.png";
				}
			}
			if (botao.getName().equals("alterarCampeonato")) {
				if (selecionado) {
					url = "/imagem/crud/altCampSelect.png";
				} else {
					url = "/imagem/crud/altCamp.png";
				}
			}
			if (botao.getName().equals("deletarCampeonato")) {
				if (selecionado) {
					url = "/imagem/crud/delCampSelect.png";
				} else {
					url = "/imagem/crud/delCamp.png";
				}
			}
			if (botao.getName().equals("visualizarCampeonato")) {
				if (selecionado) {
					url = "/imagem/crud/visuPcSelect.png";
				} else {
					url = "/imagem/crud/visuPc.png";
				}
			}
		}
		botao.setFocusPainted(false);
		botao.setBackground(UtilitarioTela.getBtnFundo(selecionado));
		if (url != null) {
			botao.setIcon(new ImageIcon(HomeFuncionario.class.getResource(url)));
		}
		botao.setForeground(UtilitarioTela.getFontColorSelecao(true));
		repaint();
	}
}
