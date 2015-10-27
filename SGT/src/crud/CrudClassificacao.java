package crud;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import dao.ClassificacaoDao;
import dao.EntityManagerLocal;
import dao.PartidaDao;
import menu.MenuCampeonato;
import utilitario.BordaSombreada;
import utilitario.ParametroCrud;
import utilitario.UtilitarioImagem;
import utilitario.UtilitarioTela;
import entidade.Campeonato;
import entidade.Classificacao;
import entidade.Partida;
import entidade.Time;
import entidade.TimePartida;

public class CrudClassificacao extends JPanel{

	private MenuCampeonato menuPai;
	private Campeonato campeonatoSelecionado;
	private JPanel header;
	private JLabel lblHeader;
	private JPanel meio;
	
	
	public CrudClassificacao(Campeonato campeonato, MenuCampeonato menuPai) {
		this.menuPai = menuPai;
		setSize(UtilitarioTela.getTamanhoMeio());
		setLayout(null);
		setBackground(null);
		campeonatoSelecionado = campeonato;
		header = new JPanel();
		header.setSize(400, 30);
		header.setLocation((getWidth() / 2) - 200, 10);
		header.setLayout(null);
		header.setBackground(UtilitarioTela.getColorCrud(ParametroCrud
				.getModoVisualizar()));
		header.setBorder(null);
		add(header);

		String textoHeader = "Classificação";

		lblHeader = new JLabel(textoHeader);
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setBounds(0, 0, header.getWidth(), header.getHeight());
		lblHeader.setFont(UtilitarioTela.getFont(14));
		lblHeader.setForeground(UtilitarioTela.getFontColorCrud());
		header.add(lblHeader);

		meio = new JPanel();
		meio.setSize(400, 400);
		meio.setLocation((getWidth() / 2) - 200, 40);
		meio.setLayout(null);
		meio.setBackground(UtilitarioTela.getFundoCrud());
		meio.setBorder(new BordaSombreada(ParametroCrud.getModoVisualizar()));
		add(meio);
		
		Classificacao classificacao = ClassificacaoDao
				.getClassificacaoPorCampeonato(campeonatoSelecionado);
		mostrarClassificacao(classificacao);

	}
	
	public void mostrarClassificacao(Classificacao classificacao) {
		
		JPanel painelPrimeio = new JPanel();
		painelPrimeio.setSize(390, 70);
		painelPrimeio.setLocation(5, 70);
		painelPrimeio.setLayout(null);
		painelPrimeio.setBackground(UtilitarioTela.getFontColorPadrao());
		meio.add(painelPrimeio);
		
		JPanel painelSegundo = new JPanel();
		painelSegundo.setSize(390, 70);
		painelSegundo.setLocation(5, 150);
		painelSegundo.setLayout(null);
		painelSegundo.setBackground(UtilitarioTela.getFontColorCrud());
		meio.add(painelSegundo);
		
		JPanel painelTerceiro = new JPanel();
		painelTerceiro.setSize(390, 70);
		painelTerceiro.setLocation(5, 230);
		painelTerceiro.setLayout(null);
		painelTerceiro.setBackground(UtilitarioTela.getFontColorCrud());
		meio.add(painelTerceiro);
		
		JLabel lblCampeonato = new JLabel(classificacao.getCampeonato().getDescricao());
		lblCampeonato.setBounds((meio.getWidth()/2) - 100, 10, 200, 50);
		lblCampeonato.setFont(UtilitarioTela.getFont(20));
		meio.add(lblCampeonato);
		
		JLabel lbLgMedalha = new JLabel(new ImageIcon(CrudPartida.class
				.getResource("/imagem/medalha.png")));
		lbLgMedalha.setBounds(10, 10, 50, 50);
		painelPrimeio.add(lbLgMedalha);

		JLabel lbLg = new JLabel(UtilitarioImagem.converterImage(classificacao
				.getTimePrimeiro().getLogo()));
		lbLg.setBounds(70, 10, 50, 50);
		painelPrimeio.add(lbLg);

		JLabel lbNome = new JLabel(classificacao.getTimePrimeiro()
				.getDescricao());
		lbNome.setBounds(130, 10, 150, 50);
		lbNome.setFont(UtilitarioTela.getFont(20));
		lbNome.setForeground(UtilitarioTela.getFontColorCrud());
		painelPrimeio.add(lbNome);
		
		JLabel lbLgMedalha2 = new JLabel(new ImageIcon(CrudPartida.class
				.getResource("/imagem/medalhaPrata.png")));
		lbLgMedalha2.setBounds(10, 10, 50, 50);
		painelSegundo.add(lbLgMedalha2);

		JLabel lbLg2 = new JLabel(UtilitarioImagem.converterImage(classificacao
				.getTimeSegundo().getLogo()));
		lbLg2.setBounds(70, 10, 50, 50);
		painelSegundo.add(lbLg2);

		JLabel lbNome2 = new JLabel(classificacao.getTimeSegundo()
				.getDescricao());
		lbNome2.setBounds(130, 10, 150, 50);
		lbNome2.setFont(UtilitarioTela.getFont(20));
		lbNome2.setForeground(UtilitarioTela.getFontColorPadrao());
		painelSegundo.add(lbNome2);
		
		JLabel lbLgMedalha3 = new JLabel(new ImageIcon(CrudPartida.class
				.getResource("/imagem/medalhaBronze.png")));
		lbLgMedalha3.setBounds(10, 10, 50, 50);
		painelTerceiro.add(lbLgMedalha3);

		JLabel lbLg3 = new JLabel(UtilitarioImagem.converterImage(classificacao
				.getTimeTerceiro().getLogo()));
		lbLg3.setBounds(70, 10, 50, 50);
		painelTerceiro.add(lbLg3);

		JLabel lbNome3 = new JLabel(classificacao.getTimeTerceiro()
				.getDescricao());
		lbNome3.setBounds(130, 10, 150, 50);
		lbNome3.setFont(UtilitarioTela.getFont(20));
		lbNome3.setForeground(UtilitarioTela.getFontColorPadrao());
		painelTerceiro.add(lbNome3);

	}
}
