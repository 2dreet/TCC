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
import entidade.Partida;
import entidade.Pc;
import entidade.PcPartida;
import entidade.Time;

public class DialogPcPartida {

	private static List<PcPartida> listaPc;
	private static JTable tabela;
	private static JTextField txBusca;
	private static ComboBox metodoBusca;
	private static Object[][] colunas = new Object[][] {
			new String[] { "Código" }, new String[] { "Descrição" },
			new String[] { "IP" } };
	private static Pc pcSelecionado;
	private static JDialog dialog;
	private static JPanel meio;
	private static Campeonato campeonatoSelecionado;
	private static Partida partidaSelecionado;
	private static boolean inicio;

	public DialogPcPartida() {
		super();
		campeonatoSelecionado = null;
		listaPc = new ArrayList<PcPartida>();
	}

	public static void localizarPc(Container painelPai, Partida partida) {
		partidaSelecionado = partida;
		campeonatoSelecionado = partida.getCampeonato();
		inicio = true;
		listaPc = new ArrayList<PcPartida>();
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

		JLabel lbHeader = new JLabel("PC partida");
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
		tcm.getColumn(1).setPreferredWidth(462);
		tcm.getColumn(1).setMinWidth(462);
		tcm.getColumn(1).setResizable(false);
		tcm.getColumn(2).setPreferredWidth(100);
		tcm.getColumn(2).setMinWidth(100);
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
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(2, 45, 640, meio.getHeight() - 85);
		scroll.setBackground(Color.red);
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
				if (PcDao.getListaPcPartida(
						partidaSelecionado.getCodigoPartida()).size() < 10) {
					DialogLocalizarPc.localizarPc(meio, partidaSelecionado);
					if (DialogLocalizarPc.getPcSelecionado() != null) {
						List<Pc> listPc = DialogLocalizarPc.getPcSelecionado();
						EntityManagerLocal.begin();
						for (Pc pc : listPc) {
							PcPartida pcP = new PcPartida();
							pcP.setPc(pc);
							pcP.setPartida(partidaSelecionado);
							EntityManagerLocal.merge(pcP);
						}
						EntityManagerLocal.commit();
						localizar();
					}
				} else {
					Menssage.setMenssage("Numero de Computadores",
							"A Partida não pode adicionar mais Computadores!",
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
				pcSelecionado = PcDao
						.getPc(Integer.parseInt(String.valueOf(tabela
								.getValueAt(tabela.getSelectedRow(), 0))));
				if (pcSelecionado != null) {
					boolean confirmado = true;
					MenssageConfirmacao.setMenssage("Remover Pc da Partida",
							"Deseja Remover Esse Pc da Partida?\nPc: "
									+ pcSelecionado.getIp(),
							ParametroCrud.getModoCrudDeletar(), meio);
					confirmado = MenssageConfirmacao.isConfirmado();
					if (confirmado) {
						PcPartida pcPartida = PcDao.getPcPartida(
								pcSelecionado.getCodigoPC(),
								partidaSelecionado.getCodigoPartida());
						EntityManagerLocal.begin();
						EntityManagerLocal.delete(pcPartida);
						EntityManagerLocal.commit();
						EntityManagerLocal.clear();
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
		listaPc = PcDao
				.getListaPcPartida(partidaSelecionado.getCodigoPartida());
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.setNumRows(0);
		if (listaPc != null && listaPc.size() > 0) {
			for (PcPartida p : listaPc) {
				modelo.addRow(new String[] {
						String.valueOf(String.valueOf(p.getPc().getCodigoPC())),
						p.getPc().getDescricao(), p.getPc().getIp() });
			}
		} else {
			listaPc = new ArrayList<PcPartida>();
		}
	}

}
