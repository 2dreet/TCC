package localizar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import utilitario.BordaSombreada;
import utilitario.MascaraCrud;
import utilitario.ParametroCrud;
import utilitario.Parametros;
import utilitario.UtilitarioCrud;
import utilitario.UtilitarioTabela;
import utilitario.UtilitarioTela;

import javax.swing.JComboBox;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.JButton;

import componente.ComboBox;
import componente.Menssage;
import componente.TabelaCell;
import componente.TextoIconeCell;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import menu.MenuCampeonato;
import menu.MenuFuncionario;
import menu.MenuJogador;
import dao.CampeonatoDao;
import dao.FuncionarioDao;
import dao.JogadorDao;
import entidade.Campeonato;
import entidade.Funcionario;
import exemplos.Tabela;

public class LocalizarCampeonato extends JPanel {

	private List<Campeonato> listaCampeonato;
	private JTable tabela;
	private JTextField txBusca;
	private ComboBox metodoBusca;
	private Object[][] colunas = new Object[][] { new String[] { "C�digo" },
			new String[] { "Descri��o" }, new String[] { "Data Cadastro" },
			new String[] { "Data Inicio" }, new String[] { "Modalidade" }};
	private String[] linhaBusca = new String[] { "C�digo", "Descri��o"};
	private Campeonato campeonatoSelecionado;
	private MenuCampeonato menuPai;
	private JPanel meio;
	private boolean inicio;
	/**
	 * Create the panel.
	 */

	public LocalizarCampeonato(MenuCampeonato menuPai) {
		this();
		this.menuPai = menuPai;
	}

	public LocalizarCampeonato() {
		super();
		campeonatoSelecionado = null;
		listaCampeonato = new ArrayList<Campeonato>();
		inicio = true;
		setSize(UtilitarioTela.getTamanhoMeio());
		setLayout(null);
		setBackground(null);

		JPanel header = new JPanel();
		header.setSize(650, 30);
		header.setLocation((getWidth() / 2) - 325, 10);
		header.setLayout(null);
		header.setBackground(Color.white);
		header.setBorder(null);
		add(header);

		JLabel lblHeader = new JLabel("Localizar Campeonato");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setBounds(0, 0, header.getWidth(), header.getHeight());
		lblHeader.setFont(UtilitarioTela.getFont(14));
		lblHeader.setForeground(UtilitarioTela.getFontColorCrud());
		header.add(lblHeader);

		meio = new JPanel();
		meio.setSize(650, getHeight() - 50);
		meio.setLocation((getWidth() / 2) - 325, 40);
		meio.setLayout(null);
		meio.setBackground(new Color(232, 234, 239));
		meio.setBorder(new BordaSombreada());
		add(meio);

		JLabel lbNome = new JLabel("Busca :");
		lbNome.setBounds(10, 10, 55, 20);
		lbNome.setFont(UtilitarioTela.getFont(14));
		lbNome.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbNome);

		metodoBusca = new ComboBox(new Dimension(150, 25));
		metodoBusca.setModel(new DefaultComboBoxModel(linhaBusca));
		metodoBusca.setLocation(70, 10);
		meio.add(metodoBusca);

		txBusca = new JTextField();
		txBusca.setColumns(100);
		txBusca.setBounds(222, 10, 280, 25);
		txBusca.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txBusca.setBorder(UtilitarioTela.jTextFieldComFocus());
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				txBusca.setBorder(UtilitarioTela.jTextFieldNormal());
			}
		});
		txBusca.setLayout(null);
		txBusca.setBorder(UtilitarioTela.jTextFieldNormal());
		meio.add(txBusca);

		JButton btLocalizar = new JButton("Localizar");
		btLocalizar.setBounds(meio.getWidth() - 140, 10, 130, 25);
		btLocalizar.setFont(UtilitarioTela.getFont(14));
		btLocalizar.setFocusPainted(false);
		btLocalizar.setBackground(UtilitarioTela.getFundoLocalizar());
		btLocalizar.setIcon(UtilitarioTela.getIconeLocalizar());
		meio.add(btLocalizar);
		btLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inicio = false;
				localizar();
			}
		});

		tabela = new JTable();
		tabela.setModel(UtilitarioTabela.getModelo(colunas));
		TableColumnModel tcm = tabela.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(60);
		tcm.getColumn(0).setMinWidth(60);
		tcm.getColumn(0).setResizable(false);
		tcm.getColumn(1).setPreferredWidth(200);
		tcm.getColumn(1).setMinWidth(200);
		tcm.getColumn(1).setResizable(false);
		tcm.getColumn(2).setPreferredWidth(100);
		tcm.getColumn(2).setMinWidth(100);
		tcm.getColumn(2).setResizable(false);
		tcm.getColumn(3).setPreferredWidth(83);
		tcm.getColumn(3).setMinWidth(83);
		tcm.getColumn(3).setResizable(false);
		tcm.getColumn(4).setPreferredWidth(200);
		tcm.getColumn(4).setMinWidth(200);
		tcm.getColumn(4).setResizable(false);
		

		UtilitarioTabela.pintarColona(UtilitarioTabela.getFundoHeaderPadrao(),
				UtilitarioTabela.getFontColotHeaderPadrao(), tcm, colunas);
		UtilitarioTabela.pintarLinha(new Color(255, 153, 153), Color.black,
				tabela);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.setPreferredScrollableViewportSize(tabela.getPreferredSize());
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(2, 45, 646, meio.getHeight() - 85);
		meio.add(scroll);

		JButton btSelecionar = new JButton("Selecionar");
		btSelecionar.setBounds(120, meio.getHeight() - 35, 180, 25);
		btSelecionar.setForeground(UtilitarioTela.getFontColorPadrao());
		btSelecionar.setFont(UtilitarioTela.getFont(14));
		btSelecionar.setFocusPainted(false);
		btSelecionar.setBackground(new Color(46, 49, 56));
		btSelecionar.setIcon(new ImageIcon(LocalizarCampeonato.class
				.getResource("/imagem/done.png")));
		meio.add(btSelecionar);
		btSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selecionar();
			}
		});

		JButton btLimparSelecao = new JButton("Limpar Sele��o");
		btLimparSelecao.setBounds(350, meio.getHeight() - 35, 180, 25);
		btLimparSelecao.setFont(UtilitarioTela.getFont(14));
		btLimparSelecao.setFocusPainted(false);
		btLimparSelecao.setForeground(new Color(46, 49, 56));
		btLimparSelecao.setBackground(UtilitarioTela.getFontColorPadrao());
		btLimparSelecao.setIcon(new ImageIcon(LocalizarCampeonato.class
				.getResource("/imagem/cancelBlack.png")));
		meio.add(btLimparSelecao);
		btLimparSelecao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparSelecao();
			}
		});
		localizar();
	}

	public void selecionar() {
		if (tabela.getRowCount() > 0) {
			if (tabela.getSelectedRow() > -1) {
				campeonatoSelecionado = CampeonatoDao.getCampeonato(Integer
						.parseInt(String.valueOf(tabela.getValueAt(
								tabela.getSelectedRow(), 0))));
				if (campeonatoSelecionado != null) {
					if (menuPai != null) {
						menuPai.exibirCampeonato(campeonatoSelecionado);
					}
				} else {
					Menssage.setMenssage("Campeonato n�o Selecionado",
							"Deve selecionar um Campeonato!",
							ParametroCrud.getModoCrudDeletar(), meio);
				}
			} else {
				Menssage.setMenssage("Campeonato n�o Selecionado",
						"Deve selecionar um Campeonato!",
						ParametroCrud.getModoCrudDeletar(), meio);
			}
		} else {
			Menssage.setMenssage("Campeonato n�o Selecionado",
					"Deve selecionar um Campeonato!",
					ParametroCrud.getModoCrudDeletar(), meio);
		}

	}

	public void limparSelecao() {
		tabela.clearSelection();
	}

	public void localizar() {
		listaCampeonato = CampeonatoDao.getListaPesquisa(metodoBusca
				.getSelectedItem().toString(), txBusca.getText());
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.setNumRows(0);
		if (listaCampeonato != null && listaCampeonato.size() > 0) {
			for (Campeonato c : listaCampeonato) {
				modelo.addRow(new String[] {
						String.valueOf(c.getCodigoCampeonato()),
						c.getDescricao(),
						MascaraCrud.macaraDataBanco(c.getDataCadastro()),
						MascaraCrud.macaraDataBanco(c.getDataInicio()),
						c.getModalidade().getDescricao()});

			}
		} else if(!inicio){
			listaCampeonato = new ArrayList<Campeonato>();
			Menssage.setMenssage("Campeonato n�o Encontrado",
					"Nenhum Campeonato foi encontrado!",
					ParametroCrud.getModoCrudDeletar(), meio);
		}
	}
}
