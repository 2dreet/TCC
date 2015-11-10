package dialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RepaintManager;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import jxl.BooleanCell;
import jxl.CellFeatures;
import jxl.CellType;
import jxl.format.CellFormat;
import jxl.write.Boolean;
import localizar.LocalizarJogador;
import utilitario.BordaSombreada;
import utilitario.MascaraCrud;
import utilitario.ParametroCrud;
import utilitario.Parametros;
import utilitario.UtilitarioImagem;
import utilitario.UtilitarioTabela;
import utilitario.UtilitarioTela;
import componente.ComboBox;
import componente.DadoComIcone;
import componente.Menssage;
import componente.TextoIconeCell;
import dao.CampeonatoDao;
import dao.JogadorDao;
import dao.PcDao;
import dao.TimeDao;
import entidade.Campeonato;
import entidade.Jogador;
import entidade.Partida;
import entidade.Pc;
import entidade.PcPartida;
import entidade.Time;

public class DialogLocalizarCampeonato {
	
	private static List<Campeonato> listaCampeonato;
	private static JTable tabela;
	private static JTextField txBusca;
	private static ComboBox metodoBusca;
	private static Object[][] colunas = new Object[][] { new String[] { "C�digo" },
			new String[] { "Descri��o" }};
	private static String[] linhaBusca = new String[] { "C�digo", "Descri��o"};
	private static Campeonato campeonatoSelecionado;
	private static JDialog dialog;
	private static JPanel meio;
	private static boolean inicio;
	public DialogLocalizarCampeonato(){
		super();
		campeonatoSelecionado = null;
		listaCampeonato = new ArrayList<Campeonato>();
		
	}
	
	public static void localizarCampeonato(JPanel painelPai) {
		inicio = true;
		campeonatoSelecionado = null;
		dialog = new JDialog(Parametros.getPai(), true);
		dialog.setUndecorated(true);
		dialog.setLayout(null);
		dialog.setSize(664, 392);
		dialog.getContentPane().setBackground(new Color(51, 153, 255));
		dialog.setLocationRelativeTo(painelPai);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BordaSombreada(new Color(46, 49, 56), new Color(0, 128, 255)));
		panel.setLayout(null);
		panel.setSize(dialog.getSize());
		panel.setBackground(null);
		panel.setLocation(0, 0);
		panel.setFocusable(true);
		panel.requestFocusInWindow();
		
		JLabel lbHeader = new JLabel("Localizar Campeonato");
		lbHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lbHeader.setBounds(2, 10, 660, 30);
		lbHeader.setFont(UtilitarioTela.getFont(14));
		lbHeader.setForeground(UtilitarioTela.getFontColorCrud());
		lbHeader.setBorder(new BordaSombreada(false, true, false, false));
		panel.add(lbHeader);
		
		JPanel meio = new JPanel();
		meio.setSize(660, 350);
		meio.setLayout(null);
		meio.setLocation(2, 40);
		meio.setBackground(new Color(224, 224, 224));
		panel.add(meio);
		
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
		TextoIconeCell renderer = new TextoIconeCell();
		tcm.getColumn(0).setCellRenderer(renderer);
		tcm.getColumn(0).setPreferredWidth(50);
		tcm.getColumn(0).setMinWidth(50);
		tcm.getColumn(0).setResizable(false);
		tcm.getColumn(1).setPreferredWidth(550);
		tcm.getColumn(1).setMinWidth(550);
		tcm.getColumn(1).setResizable(false);
		

		UtilitarioTabela.pintarColona(UtilitarioTabela.getFundoHeaderPadrao(),
				UtilitarioTabela.getFontColotHeaderPadrao(), tcm, colunas);
		UtilitarioTabela.pintarLinha(new Color(255, 153, 153), Color.black,
				tabela);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.setPreferredScrollableViewportSize(tabela.getPreferredSize());
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.setRowHeight(50);
		tabela.setFont(UtilitarioTela.getFont(14));
		tabela.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(2, 45, 640, meio.getHeight() - 85);
		scroll.setBackground(Color.red);
		meio.add(scroll);

		JButton btSelecionar = new JButton("Selecionar");
		btSelecionar.setBounds(120, meio.getHeight() - 35, 180, 25);
		btSelecionar.setForeground(UtilitarioTela.getColorErro());
		btSelecionar.setFont(UtilitarioTela.getFont(14));
		btSelecionar.setFocusPainted(false);
		btSelecionar.setBackground(new Color(46, 49, 56));
		btSelecionar.setIcon(new ImageIcon(LocalizarJogador.class
				.getResource("/imagem/done.png")));
		meio.add(btSelecionar);
		btSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selecionar();
			}
		});

		JButton btLimparSelecao = new JButton("Cancelar");
		btLimparSelecao.setBounds(350, meio.getHeight() - 35, 180, 25);
		btLimparSelecao.setFont(UtilitarioTela.getFont(14));
		btLimparSelecao.setFocusPainted(false);
		btLimparSelecao.setForeground(new Color(46, 49, 56));
		btLimparSelecao.setBackground(UtilitarioTela.getFontColorPadrao());
		btLimparSelecao.setIcon(new ImageIcon(LocalizarJogador.class
				.getResource("/imagem/cancelBlack.png")));
		meio.add(btLimparSelecao);
		btLimparSelecao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelar();
			}
		});
		localizar();
		dialog.getContentPane().add(panel);
		dialog.setVisible(true);
		
	}

	public static Campeonato getCampeonatoSelecionado(){
		return campeonatoSelecionado;
	}
	
	private static void selecionar() {
		if (tabela.getRowCount() > 0) {
			if (tabela.getSelectedRow() > -1) {
				Campeonato camp = CampeonatoDao.getCampeonato(Integer.parseInt(String.valueOf(tabela.getValueAt(tabela.getSelectedRow(), 0))));
				 if (camp != null) {
					 campeonatoSelecionado = camp;
					dialog.dispose();
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

	public static void cancelar() {
		tabela.clearSelection();
		campeonatoSelecionado = null;
		dialog.setVisible(false);
	}

	public static void localizar() {
		listaCampeonato = CampeonatoDao.getListaPesquisa(metodoBusca
				.getSelectedItem().toString(), txBusca.getText());
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.setNumRows(0);
		if (listaCampeonato != null && listaCampeonato.size() > 0) {
			for (Campeonato p : listaCampeonato) {
				modelo.addRow(new Object[] {
						String.valueOf(String.valueOf(p.getCodigoCampeonato())),
						p.getDescricao()});
			}
		} else if(!inicio){
			listaCampeonato = new ArrayList<Campeonato>();
			Menssage.setMenssage("Campeonato n�o Encontrado",
					"Nenhum Campeonato foi encontrado!",
					ParametroCrud.getModoCrudDeletar(), meio);
		}
	}
	
}
