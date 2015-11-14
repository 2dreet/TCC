package dialog;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Date;
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
import componente.MenssageConfirmacao;
import componente.TextoIconeCell;
import dao.CampeonatoTimeDao;
import dao.EntityManagerLocal;
import dao.JogadorDao;
import dao.PcDao;
import dao.TimeDao;
import entidade.Campeonato;
import entidade.CampeonatoTime;
import entidade.Jogador;
import entidade.JogadorPartida;
import entidade.Partida;
import entidade.Pc;
import entidade.PcPartida;
import entidade.Time;

public class DialogJogadorPartida {

	private static JTable tabela;
	private static List<JogadorPartida> listaJogador;
	private static Object[][] colunas = new Object[][] { new String[] { "Código" },
			new String[] { "Nome" }, new String[] { "Usuário" }};
	public static JDialog dialog;
	private static JPanel meio;
	private static Campeonato campeonatoSelecionado;
	private static Partida partidaSelecionado;
	private static boolean inicio;
	private static JogadorPartida jogadorSelecionado;
	private static Time time;

	public DialogJogadorPartida() {
		super();
		campeonatoSelecionado = null;
	}

	public static void localizarJogador(Container painelPai, Partida partida, Time timeSelecionado) {
		partidaSelecionado = partida;
		time = timeSelecionado;
		campeonatoSelecionado = partida.getCampeonato();
		listaJogador = new ArrayList<JogadorPartida>();
		inicio = true;
		
		dialog = new JDialog(Parametros.getPai(), true);
		dialog.setUndecorated(true);
		dialog.setLayout(null);
		dialog.setSize(664, 392);
		dialog.getContentPane().setBackground(new Color(51, 153, 255));
		dialog.setLocationRelativeTo(painelPai);

		JPanel panel = new JPanel();
		panel.setBorder(new BordaSombreada(new Color(46, 49, 56), new Color(0,
				128, 255)));
		panel.setLayout(null);
		panel.setSize(dialog.getSize());
		panel.setBackground(null);
		panel.setLocation(0, 0);
		panel.setFocusable(true);
		panel.requestFocusInWindow();

		JLabel lbHeader = new JLabel("Jogadores Partida");
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

		tabela = new JTable();
		tabela.setModel(UtilitarioTabela.getModelo(colunas));
		TableColumnModel tcm = tabela.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(80);
		tcm.getColumn(0).setMinWidth(80);
		tcm.getColumn(0).setResizable(false);
		tcm.getColumn(1).setPreferredWidth(300);
		tcm.getColumn(1).setMinWidth(300);
		tcm.getColumn(1).setResizable(false);
		tcm.getColumn(2).setPreferredWidth(200);
		tcm.getColumn(2).setMinWidth(200);
		tcm.getColumn(2).setResizable(false);

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
		meio.add(scroll);

		JButton btSelecionar = new JButton("Adicionar");
		btSelecionar.setBounds(50, meio.getHeight() - 35, 150, 25);
		btSelecionar.setForeground(new Color(46, 49, 56));
		btSelecionar.setFont(UtilitarioTela.getFont(14));
		btSelecionar.setFocusPainted(false);
		btSelecionar.setBackground(UtilitarioTela.getColorCrud(ParametroCrud
				.getModoCrudNovo()));
		meio.add(btSelecionar);
		btSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JogadorDao.getListaJogadorPartidaTime(partidaSelecionado.getCodigoPartida(), time.getCodigoTime()).
						size() < 5) {
					DialogLocalizarJogadorPartidaTime.localizarJogador(meio, partidaSelecionado, time);
					if (DialogLocalizarJogadorPartidaTime.getJogadorSelecionado() != null) {
						List<Jogador> listJP = DialogLocalizarJogadorPartidaTime.getJogadorSelecionado();
						EntityManagerLocal.begin();
						for (Jogador jp : listJP) {
							JogadorPartida jpT = new JogadorPartida();
							jpT.setTime(time);
							jpT.setJogador(jp);
							jpT.setPartida(partidaSelecionado);
							EntityManagerLocal.persist(jpT);
							EntityManagerLocal.flush();
						}
						EntityManagerLocal.commit();
						localizar();
					}
				} else {
					Menssage.setMenssage("Numero de Jogadores",
							"A Partida não pode adicionar mais Jogadore!\nMaximo são 5",
							ParametroCrud.getModoCrudDeletar(), meio);
				}
			}
		});

		JButton btRemover = new JButton("Remover");
		btRemover.setBounds(250, meio.getHeight() - 35, 150, 25);
		btRemover.setForeground(UtilitarioTela.getColorErro());
		btRemover.setFont(UtilitarioTela.getFont(14));
		btRemover.setFocusPainted(false);
		btRemover.setBackground(new Color(46, 49, 56));
		meio.add(btRemover);
		btRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selecionar();
				localizar();
			}
		});

		JButton btLimparSelecao = new JButton("Fechar");
		btLimparSelecao.setBounds(450, meio.getHeight() - 35, 150, 25);
		btLimparSelecao.setFont(UtilitarioTela.getFont(14));
		btLimparSelecao.setFocusPainted(false);
		btLimparSelecao.setForeground(new Color(46, 49, 56));
		btLimparSelecao.setBackground(UtilitarioTela.getFontColorPadrao());
		meio.add(btLimparSelecao);
		btLimparSelecao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialog.dispose();
			}
		});

		localizar();

		dialog.getContentPane().add(panel);
		dialog.setVisible(true);
	}

	
	
	public static void selecionar() {
		if (tabela.getRowCount() > 0) {
			if (tabela.getSelectedRow() > -1) {
				int[] selecao = tabela.getSelectedRows(); 
				List<JogadorPartida> jogadorPartidaSelecionado = new ArrayList<JogadorPartida>();
				for (int i = 0; i < selecao.length; i++) {  
					jogadorPartidaSelecionado.add(JogadorDao.getJogadorPartidaTime(partidaSelecionado.getCodigoPartida(), time.getCodigoTime(),Integer
							.parseInt(String.valueOf(tabela.getValueAt(
									selecao[i], 0)))));
				}
				
				if (jogadorPartidaSelecionado != null && jogadorPartidaSelecionado.size() > 0) {
					boolean confirmado = true;
					MenssageConfirmacao.setMenssage("Remover Jogador da Partida",
							"Deseja Remover Jogador da Partida?",
							ParametroCrud.getModoCrudDeletar(), meio);
					confirmado = MenssageConfirmacao.isConfirmado();
					if (confirmado) {
						EntityManagerLocal.begin();
						for(JogadorPartida jp : jogadorPartidaSelecionado){
							EntityManagerLocal.delete(jp);
							EntityManagerLocal.flush();
						}
						EntityManagerLocal.commit();
						EntityManagerLocal.clear();
						jogadorSelecionado = null;
					}
				} else {
					Menssage.setMenssage("Computador não Selecionado",
							"Deve selecionar um Computador!",
							ParametroCrud.getModoCrudDeletar(), meio);
				}
			} else {
				Menssage.setMenssage("Computador não Selecionado",
						"Deve selecionar um Computador!",
						ParametroCrud.getModoCrudDeletar(), meio);
			}
		} else {
			Menssage.setMenssage("Computador não Selecionado",
					"Deve selecionar um Computador!",
					ParametroCrud.getModoCrudDeletar(), meio);
		}

	}

	public static void localizar() {
		listaJogador = JogadorDao.getListaJogadorPartidaTime(partidaSelecionado.getCodigoPartida(), time.getCodigoTime());
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.setNumRows(0);
		if (listaJogador != null && listaJogador.size() > 0) {
			for (JogadorPartida p : listaJogador) {
				modelo.addRow(new String[] {
						String.valueOf(String.valueOf(p.getJogador().getCodigoJogador())),
						String.valueOf(String.valueOf(p.getJogador().getUsuario().getNome())),
						String.valueOf(String.valueOf(p.getJogador().getUsuario().getUsuario()))});
			}
		} else {
			listaJogador = new ArrayList<JogadorPartida>();
		}
	}

}
