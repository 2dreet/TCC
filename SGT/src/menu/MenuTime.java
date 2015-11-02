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

import crud.CrudJogador;
import crud.CrudTime;
import entidade.Jogador;
import entidade.Time;
import entidade.Usuario;
import tela.HomeFuncionario;
import utilitario.BordaEscura;
import utilitario.ParametroCrud;
import utilitario.Parametros;
import utilitario.UtilitarioTela;

import java.awt.Font;

import localizar.LocalizarJogador;
import localizar.LocalizarTime;

public class MenuTime extends JPanel {

	/**
	 * Create the panel.
	 */
	private JPanel menuMeio;
	private JButton btLocalizar;
	private JButton btNovo;
	private JButton btAlterar;
	private JButton btDeletar;
	private JButton btVisualizar;
	private JButton btJogadores;
	private Time timeSelecionado;

	public MenuTime() {
		timeSelecionado = null;
		
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

		JLabel tituloMenu = new JLabel("Time");
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
				localizarTime();
			}
		});
		btLocalizar.setBounds( 5, 5,  230, 30);
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
		btNovo.setName("cadastrarTime");
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
		btVisualizar.setName("visualizarTime");
		btVisualizar.setHorizontalAlignment(SwingConstants.LEFT);
		btVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btVisualizar, true);
				alterarMenu(timeSelecionado, ParametroCrud.getModoVisualizar());
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
		btAlterar.setName("alterarTime");
		btAlterar.setHorizontalAlignment(SwingConstants.LEFT);
		btAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btAlterar, true);
				alterarMenu(timeSelecionado,
						ParametroCrud.getModoCrudAlterar());
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
		btDeletar.setName("deletarTime");
		btDeletar.setHorizontalAlignment(SwingConstants.LEFT);
		btDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btDeletar, true);
				alterarMenu(timeSelecionado,
						ParametroCrud.getModoCrudDeletar());
			}
		});

		jp5.add(btDeletar);

		getIcon(btNovo, false);
		getIcon(btAlterar, false);
		getIcon(btDeletar, false);
		getIcon(btVisualizar, false);
		getIcon(btLocalizar, false);
		
		btDeletar.setForeground(UtilitarioTela
				.getFontColorSelecao(false));
		btAlterar.setForeground(UtilitarioTela
				.getFontColorSelecao(false));
		btNovo.setForeground(UtilitarioTela.getFontColorSelecao(false));
		btVisualizar.setForeground(UtilitarioTela.getFontColorSelecao(false));
		btLocalizar.setForeground(UtilitarioTela
				.getFontColorSelecao(false));
		
		btNovo.setFont(UtilitarioTela.getFont(14));
		btAlterar.setFont(UtilitarioTela.getFont(14));
		btDeletar.setFont(UtilitarioTela.getFont(14));
		btVisualizar.setFont(UtilitarioTela.getFont(14));
		btLocalizar.setFont(UtilitarioTela.getFont(14));
		
		limpar();
		
		zeraSelecao();
		getIcon(btLocalizar, true);
		limpar();
		localizarTime();
	}

	public void home(){
		zeraSelecao();
		getIcon(btLocalizar, true);
		limpar();
		localizarTime();
	}
	
	public void limpar(){
		btAlterar.setEnabled(false);
		btDeletar.setEnabled(false);
		btVisualizar.setEnabled(false);
	}
	
	public void exibirTime(Time time){
		zeraSelecao();
		getIcon(btVisualizar, true);
		this.timeSelecionado = time;
		liberarCrud();
		alterarMenu(timeSelecionado , ParametroCrud.getModoVisualizar());
	}
	
	public void localizarTime() {
		menuMeio.removeAll();
		LocalizarTime localizar = new LocalizarTime(this);
		menuMeio.add(localizar);
		menuMeio.revalidate();
		menuMeio.repaint();
	}

	public void liberarCrud(){
		btAlterar.setEnabled(true);
		btDeletar.setEnabled(true);
		btVisualizar.setEnabled(true);
	}
	
	public void alterarMenu(Time time, int modoCrud) {
		menuMeio.removeAll();
		CrudTime c = new CrudTime(time, modoCrud, this);
		menuMeio.add(c);
		c.getTxDescricao().requestFocus();
		menuMeio.revalidate();
		menuMeio.repaint();
	}

	public void zeraSelecao() {
		
		btLocalizar.setIcon(new ImageIcon(HomeFuncionario.class
				.getResource("/imagem/crud/locTime.png")));
		btLocalizar.setBackground(UtilitarioTela.getBtnFundo(false));
		btLocalizar.setForeground(UtilitarioTela.getFontColorSelecao(false));
		
		btNovo.setIcon(new ImageIcon(HomeFuncionario.class
				.getResource("/imagem/crud/cadTime.png")));
		btNovo.setBackground(UtilitarioTela.getBtnFundo(false));
		btNovo.setForeground(UtilitarioTela.getFontColorSelecao(false));

		btAlterar.setIcon(new ImageIcon(HomeFuncionario.class
				.getResource("/imagem/crud/altTime.png")));
		btAlterar.setBackground(UtilitarioTela.getBtnFundo(false));
		btAlterar.setForeground(UtilitarioTela
				.getFontColorSelecao(false));

		btDeletar.setIcon(new ImageIcon(HomeFuncionario.class
				.getResource("/imagem/crud/delTime.png")));
		btDeletar.setBackground(UtilitarioTela.getBtnFundo(false));
		btDeletar.setForeground(UtilitarioTela
				.getFontColorSelecao(false));
		
		btVisualizar.setIcon(new ImageIcon(HomeFuncionario.class
				.getResource("/imagem/crud/visuTime.png")));
		btVisualizar.setBackground(UtilitarioTela.getBtnFundo(false));
		btVisualizar.setForeground(UtilitarioTela
				.getFontColorSelecao(false));
		

	}

	public void getIcon(JButton botao, boolean selecionado) {
		String url = "";
		if (botao.getName() != null) {
			if (botao.getName().equals("localizar")) {
				if (selecionado) {
					url = "/imagem/crud/locTimeSelect.png";
				} else {
					url = "/imagem/crud/locTime.png";
				}
			}
			if (botao.getName().equals("cadastrarTime")) {
				if (selecionado) {
					url = "/imagem/crud/cadTimeSelect.png";
				} else {
					url = "/imagem/crud/cadTime.png";
				}
			}
			if (botao.getName().equals("alterarTime")) {
				if (selecionado) {
					url = "/imagem/crud/altTimeSelect.png";
				} else {
					url = "/imagem/crud/altTime.png";
				}
			}
			if (botao.getName().equals("deletarTime")) {
				if (selecionado) {
					url = "/imagem/crud/delTimeSelect.png";
				} else {
					url = "/imagem/crud/delTime.png";
				}
			}
			if (botao.getName().equals("visualizarTime")) {
				if (selecionado) {
					url = "/imagem/crud/visuTimeSelect.png";
				} else {
					url = "/imagem/crud/visuTime.png";
				}
			}
			botao.setFocusPainted(false);
			botao.setBackground(UtilitarioTela.getBtnFundo(selecionado));
			botao.setIcon(new ImageIcon(HomeFuncionario.class.getResource(url)));
			botao.setForeground(UtilitarioTela.getFontColorSelecao(true));
			repaint();
		}
	}
}
