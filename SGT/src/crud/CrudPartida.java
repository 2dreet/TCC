package crud;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JPanel;

import componente.DadoComIcone;
import componente.Menssage;
import componente.MenssageConfirmacao;
import componente.TextoIconeCell;
import utilitario.BordaEscura;
import utilitario.BordaSombreada;
import utilitario.MascaraCrud;
import utilitario.MoverArquivo;
import utilitario.ParametroCrud;
import utilitario.Parametros;
import utilitario.UtilitarioCrud;
import utilitario.UtilitarioImagem;
import utilitario.UtilitarioLogo;
import utilitario.UtilitarioTabela;
import utilitario.UtilitarioTela;
import utilitario.ValidadorCrud;

import javax.swing.JButton;

import dao.EntityManagerLocal;
import dao.JogadorBanimentoDao;
import dao.JogadorDao;
import dao.PartidaDao;
import dao.PermissaoDao;
import dao.TimeDao;
import dialog.DialogLocalizarJogador;
import entidade.Campeonato;
import entidade.CampeonatoTime;
import entidade.Jogador;
import entidade.Partida;
import entidade.Time;
import entidade.TimePartida;
import entidade.Usuario;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;

import componente.ComboBox;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTextArea;

import menu.MenuCampeonato;
import menu.MenuJogador;
import menu.MenuTime;

public class CrudPartida extends JPanel {
	private JLabel lblMsg;
	private Partida partidaSelecionada;
	private Partida partidaSelecionadaLower;
	private MenuCampeonato menuPai;
	private Campeonato campeonatoSelecionado;
	private JPanel header;
	private JLabel lblHeader;
	private JPanel headerLower;
	private JLabel lblHeaderLower;
	private JPanel meio;
	private JTable tabela;
	private JTable tabelaLower;
	private JButton btPartidasIniciadas;
	private JButton btPartidasNaoIniciadas;
	private JButton btPartidasIniciadasLower;
	private JButton btPartidasNaoIniciadasLower;
	private JScrollPane scrollTitularLower;
	private Object[][] colunas = new Object[][] { new String[] { "Código" },
			new String[] { "Time 1" }, new String[] { "Time 2" },
			new String[] { "Data Inicio" }, new String[] { "Data Fim" }};

	/**
	 * Create the panel.
	 */
	public CrudPartida(Campeonato campeonato, MenuCampeonato menuPai) {
		this.menuPai = menuPai;
		setSize(UtilitarioTela.getTamanhoMeio());
		setLayout(null);
		setBackground(null);
		campeonatoSelecionado = campeonato;

		header = new JPanel();
		header.setSize(650, 30);
		header.setLocation((getWidth() / 2) - 325, 10);
		header.setLayout(null);
		header.setBackground(UtilitarioTela.getColorCrud(ParametroCrud.getModoVisualizar()));
		header.setBorder(null);
		add(header);

		String textoHeader = "Partidas";
		
		lblHeader = new JLabel(textoHeader);
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setBounds(0, 0, header.getWidth(), header.getHeight());
		lblHeader.setFont(UtilitarioTela.getFont(14));
		lblHeader.setForeground(UtilitarioTela.getFontColorCrud());
		header.add(lblHeader);

		meio = new JPanel();
		meio.setSize(650, getHeight() - 50);
		meio.setLocation((getWidth() / 2) - 325, 40);
		meio.setLayout(null);
		meio.setBackground(UtilitarioTela.getFundoCrud());
		meio.setBorder(new BordaSombreada(ParametroCrud.getModoVisualizar()));
		add(meio);
		
		
		tabela = new JTable();
		tabela.setModel(UtilitarioTabela.getModelo(colunas));

		TableColumnModel tcmTitular = tabela.getColumnModel();
		TextoIconeCell renderer = new TextoIconeCell();
		tcmTitular.getColumn(0).setPreferredWidth(60);
		tcmTitular.getColumn(0).setMinWidth(60);
		tcmTitular.getColumn(0).setResizable(false);
		tcmTitular.getColumn(1).setCellRenderer(renderer);
		tcmTitular.getColumn(1).setPreferredWidth(170);
		tcmTitular.getColumn(1).setMinWidth(170);
		tcmTitular.getColumn(1).setResizable(false);
		tcmTitular.getColumn(2).setCellRenderer(renderer);
		tcmTitular.getColumn(2).setPreferredWidth(170);
		tcmTitular.getColumn(2).setMinWidth(170);
		tcmTitular.getColumn(2).setResizable(false);
		tcmTitular.getColumn(3).setPreferredWidth(120);
		tcmTitular.getColumn(3).setMinWidth(120);
		tcmTitular.getColumn(3).setResizable(false);
		tcmTitular.getColumn(4).setPreferredWidth(120);
		tcmTitular.getColumn(4).setMinWidth(120);
		tcmTitular.getColumn(4).setResizable(false);
		

		UtilitarioTabela.pintarColona(UtilitarioTabela.getFundoHeaderPadrao(),
				UtilitarioTabela.getFontColotHeaderPadrao(), tcmTitular,
				colunas);
		UtilitarioTabela.pintarLinha(new Color(255, 153, 153), Color.black,
				tabela);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.setPreferredScrollableViewportSize(tabela
				.getPreferredSize());
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.setFont(UtilitarioTela.getFont(12));
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollTitular = new JScrollPane(tabela);
		scrollTitular.setBounds(1, 0, 646, 210);
		meio.add(scrollTitular);
		
		btPartidasNaoIniciadas = new JButton("Partidas não Iniciadas");
		btPartidasNaoIniciadas.setBounds(70, 215, 250, 35);
		btPartidasNaoIniciadas.setFont(UtilitarioTela.getFont(14));
		btPartidasNaoIniciadas.setFocusPainted(false);
		btPartidasNaoIniciadas.setBackground(UtilitarioTela.getColorCrud(ParametroCrud.getModoCrudAlterar()));
		meio.add(btPartidasNaoIniciadas);
		btPartidasNaoIniciadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparSelecao();
				mudaCorBotao(btPartidasNaoIniciadas, true);
				atualizarTabela(false);
			}
		});
		
		btPartidasIniciadas = new JButton("Partidas Iniciadas");
		btPartidasIniciadas.setBounds(350, 215, 250, 35);
		btPartidasIniciadas.setFont(UtilitarioTela.getFont(14));
		btPartidasIniciadas.setFocusPainted(false);
		btPartidasIniciadas.setBackground(UtilitarioTela.getColorCrud(ParametroCrud.getModoCrudNovo()));
		meio.add(btPartidasIniciadas);
		btPartidasIniciadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparSelecao();
				mudaCorBotao(btPartidasIniciadas, true);
				atualizarTabela(true);
			}
		});
		
		
		headerLower = new JPanel();
		headerLower.setSize(646, 30);
		headerLower.setLocation(1, 290);
		headerLower.setLayout(null);
		headerLower.setBackground(UtilitarioTela.getColorCrud(ParametroCrud.getModoVisualizar()));
		headerLower.setBorder(null);
		meio.add(headerLower);

		String textoHeaderLower = "Partidas Lower";
		
		lblHeaderLower = new JLabel(textoHeaderLower);
		lblHeaderLower.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeaderLower.setBounds(0, 0, headerLower.getWidth(), headerLower.getHeight());
		lblHeaderLower.setFont(UtilitarioTela.getFont(14));
		lblHeaderLower.setForeground(UtilitarioTela.getFontColorCrud());
		headerLower.add(lblHeaderLower);
		
		tabelaLower = new JTable();
		tabelaLower.setModel(UtilitarioTabela.getModelo(colunas));

		TableColumnModel tcmTitularLower = tabelaLower.getColumnModel();
		TextoIconeCell rendererLower = new TextoIconeCell();
		tcmTitularLower.getColumn(0).setPreferredWidth(60);
		tcmTitularLower.getColumn(0).setMinWidth(60);
		tcmTitularLower.getColumn(0).setResizable(false);
		tcmTitularLower.getColumn(1).setCellRenderer(renderer);
		tcmTitularLower.getColumn(1).setPreferredWidth(170);
		tcmTitularLower.getColumn(1).setMinWidth(170);
		tcmTitularLower.getColumn(1).setResizable(false);
		tcmTitularLower.getColumn(2).setCellRenderer(renderer);
		tcmTitularLower.getColumn(2).setPreferredWidth(170);
		tcmTitularLower.getColumn(2).setMinWidth(170);
		tcmTitularLower.getColumn(2).setResizable(false);
		tcmTitularLower.getColumn(3).setPreferredWidth(120);
		tcmTitularLower.getColumn(3).setMinWidth(120);
		tcmTitularLower.getColumn(3).setResizable(false);
		tcmTitularLower.getColumn(4).setPreferredWidth(120);
		tcmTitularLower.getColumn(4).setMinWidth(120);
		tcmTitularLower.getColumn(4).setResizable(false);
		

		UtilitarioTabela.pintarColona(UtilitarioTabela.getFundoHeaderPadrao(),
				UtilitarioTabela.getFontColotHeaderPadrao(), tcmTitularLower,
				colunas);
		UtilitarioTabela.pintarLinha(new Color(255, 153, 153), Color.black,
				tabela);
		tabelaLower.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabelaLower.setPreferredScrollableViewportSize(tabela
				.getPreferredSize());
		tabelaLower.getTableHeader().setReorderingAllowed(false);
		tabelaLower.setFont(UtilitarioTela.getFont(12));
		tabelaLower.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollTitularLower = new JScrollPane(tabela);
		scrollTitularLower.setBounds(1, 320, 646, 210);
		meio.add(scrollTitularLower);
		
		btPartidasNaoIniciadasLower = new JButton("Partidas não Iniciadas Lower");
		btPartidasNaoIniciadasLower.setBounds(70, 535, 250, 35);
		btPartidasNaoIniciadasLower.setFont(UtilitarioTela.getFont(14));
		btPartidasNaoIniciadasLower.setFocusPainted(false);
		btPartidasNaoIniciadasLower.setBackground(UtilitarioTela.getColorCrud(ParametroCrud.getModoCrudAlterar()));
		meio.add(btPartidasNaoIniciadasLower);
		btPartidasNaoIniciadasLower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparSelecao();
				mudaCorBotao(btPartidasNaoIniciadasLower, true);
				atualizarTabela(false);
			}
		});
		
		btPartidasIniciadasLower = new JButton("Partidas Iniciadas Lower");
		btPartidasIniciadasLower.setBounds(350, 535, 250, 35);
		btPartidasIniciadasLower.setFont(UtilitarioTela.getFont(14));
		btPartidasIniciadasLower.setFocusPainted(false);
		btPartidasIniciadasLower.setBackground(UtilitarioTela.getColorCrud(ParametroCrud.getModoCrudNovo()));
		meio.add(btPartidasIniciadasLower);
		btPartidasIniciadasLower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparSelecao();
				mudaCorBotao(btPartidasIniciadasLower, true);
				atualizarTabela(true);
			}
		});
		
		limparSelecao();
		mudaCorBotao(btPartidasNaoIniciadas, true);
		atualizarTabela(false); 
	}
	
	public void limparSelecao(){
		btPartidasIniciadas.setBackground(UtilitarioTela.getBtnFundo(false));
		btPartidasIniciadas.setForeground(UtilitarioTela.getFontColorSelecao(false));
		
		btPartidasNaoIniciadas.setBackground(UtilitarioTela.getBtnFundo(false));
		btPartidasNaoIniciadas.setForeground(UtilitarioTela.getFontColorSelecao(false));
		
		btPartidasNaoIniciadasLower.setBackground(UtilitarioTela.getBtnFundo(false));
		btPartidasNaoIniciadasLower.setForeground(UtilitarioTela.getFontColorSelecao(false));
		
		btPartidasIniciadasLower.setBackground(UtilitarioTela.getBtnFundo(false));
		btPartidasIniciadasLower.setForeground(UtilitarioTela.getFontColorSelecao(false));
	}
	
	public void mudaCorBotao(JButton botao, boolean selecionado){
		botao.setFocusPainted(false);
		botao.setBackground(UtilitarioTela.getBtnFundo(selecionado));
		botao.setForeground(UtilitarioTela.getFontColorSelecao(true));
		repaint();
	}

	public void atualizarTabela(boolean partidasIniciadas) {
		
		List<Partida> listaPartida = null;
		if(partidasIniciadas){
			listaPartida = PartidaDao.getPartidasIniciadas(campeonatoSelecionado.getCodigoCampeonato());
		} else {
			listaPartida = PartidaDao.getPartidasNaoIniciadas(campeonatoSelecionado.getCodigoCampeonato());
		}
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.setNumRows(0);
		if (listaPartida != null) {
			for (Partida partida : listaPartida) {
				Time time1 = PartidaDao.getTimePartida(campeonatoSelecionado.getCodigoCampeonato(), partidaSelecionada.getCodigoPartida(), "ASC").getTime();
				Time time2 = PartidaDao.getTimePartida(campeonatoSelecionado.getCodigoCampeonato(), partidaSelecionada.getCodigoPartida(), "ASC").getTime();
				modelo.addRow(new String[] {
						String.valueOf(partida.getCodigoPartida()),
						UtilitarioImagem.converterImage(time1.getLogo()) + time1.getDescricao(),
						UtilitarioImagem.converterImage(time2.getLogo()) + time2.getDescricao(),
						MascaraCrud.mascaraDataHora(partida.getHoraInicio()),
						MascaraCrud.mascaraDataHora(partida.getHoraFim())
						});

			}
		} else {
			listaPartida = new ArrayList<Partida>();
		}
	}

	public void selecionar() {
		if (tabela.getRowCount() > 0) {
			if (tabela.getSelectedRow() > -1) {
				partidaSelecionada = PartidaDao.getAllPartida(Integer
						.parseInt(String.valueOf(tabela.getValueAt(
								tabela.getSelectedRow(), 0))));
			} else {
				Menssage.setMenssage("Partida não Selecionada",
						"Deve selecionar uma Partida!",
						ParametroCrud.getModoCrudDeletar(), meio);
			}
		} else {
			Menssage.setMenssage("Partida não Selecionada",
					"Deve selecionar uma Partida!",
					ParametroCrud.getModoCrudDeletar(), meio);
		}
	}
	

}
