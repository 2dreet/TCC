package crud;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import componente.DadoComIcone;
import componente.TextoIconeCell;
import dao.CampeonatoDao;
import dao.CampeonatoTimeDao;
import dao.GrupoDao;
import dao.PartidaDao;
import tela.HomeFuncionario;
import utilitario.BordaSombreada;
import utilitario.Computador;
import utilitario.ParametroCrud;
import utilitario.Parametros;
import utilitario.UtilitarioImagem;
import utilitario.UtilitarioTabela;
import utilitario.UtilitarioTela;
import entidade.Campeonato;
import entidade.CampeonatoTime;
import entidade.Grupo;
import entidade.Partida;
import entidade.PojoMataMata;
import entidade.Time;
import entidade.TimeGrupo;
import entidade.TimePartida;

public class TelaGrupo extends JFrame {

	private static Campeonato campeonatoSelecionado;

	private int xPai;
	private int yPai;
	private int xFilho;
	private int yFilho;

	private int maiorX;
	private int maiorY;
	private JScrollPane scrollPane;
	private JPanel panel;
	private Object[][] colunas = new Object[][] { new String[] { "Logo" },
			new String[] { "Nome" }, new String[] { "Pontos" } };
	private int posicaoInicialLower;

	public Partida partidaEmAndamento;

	public TelaGrupo(Campeonato campeonato) {
		campeonatoSelecionado = campeonato;
		if (panel != null) {
			panel.removeAll();
		}

		maiorX = 0;
		maiorY = 0;
		posicaoInicialLower = 0;
		getContentPane().setLayout(
				new javax.swing.BoxLayout(getContentPane(),
						javax.swing.BoxLayout.LINE_AXIS));

		panel = new JPanel();
		panel.setLayout(null);
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(panel);
		getContentPane().add(scrollPane);
		setSize(UtilitarioTela.getTamanhoMunitor());
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false);
		setLocationRelativeTo(null);
		repaint();
	}

	public void atualizarTela(Campeonato campeonato) {
		campeonatoSelecionado = campeonato;
		scrollPane.remove(panel);
		maiorX = 0;
		maiorY = 0;

		getContentPane().setLayout(
				new javax.swing.BoxLayout(getContentPane(),
						javax.swing.BoxLayout.LINE_AXIS));
		panel = new JPanel();
		panel.setLayout(null);

		montarGrupos(campeonato);
		panel.setPreferredSize(new java.awt.Dimension(maiorX + 300,
				maiorY + 300));
		scrollPane.setViewportView(panel);
		repaint();
	}

	public void montarGrupos(Campeonato campeonato) {
		List<Grupo> grupos = GrupoDao.getListaGrupo(campeonato
				.getCodigoCampeonato());
		int l = 0;
		int posicao = 50;
		for (Grupo g : grupos) {

			JLabel label = new JLabel(g.getDescricao());
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setSize(355, 20);
			label.setFont(UtilitarioTela.getFont(16));
			label.setForeground(UtilitarioTela.getFontColorCrud());
			label.setLocation(10 + (l * 370), posicao);
			panel.add(label);

			JTable tabela = new JTable();
			tabela.setModel(UtilitarioTabela.getModelo(colunas));

			TableColumnModel tcm = tabela.getColumnModel();
			TextoIconeCell renderer = new TextoIconeCell();
			tcm.getColumn(0).setCellRenderer(renderer);
			tcm.getColumn(0).setPreferredWidth(50);
			tcm.getColumn(0).setMinWidth(50);
			tcm.getColumn(0).setResizable(false);
			tcm.getColumn(1).setPreferredWidth(250);
			tcm.getColumn(1).setMinWidth(250);
			tcm.getColumn(1).setResizable(false);
			tcm.getColumn(2).setPreferredWidth(50);
			tcm.getColumn(2).setMinWidth(50);
			tcm.getColumn(2).setResizable(false);

			UtilitarioTabela.pintarColona(
					UtilitarioTabela.getFundoHeaderPadrao(),
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
			scroll.setBounds(10 + (l * 370), posicao + 20, 355, 230);
			panel.add(scroll);

			List<TimeGrupo> listaTime = GrupoDao.getListaTimeGrupo(
					campeonatoSelecionado.getCodigoCampeonato(),
					g.getCodigoGrupo());
			DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
			modelo.setNumRows(0);
			if (listaTime != null) {
				for (TimeGrupo t : listaTime) {
					modelo.addRow(new Object[] {
							new DadoComIcone("", UtilitarioImagem
									.converterImage(t.getTime().getLogo())),
							String.valueOf(t.getTime().getDescricao()),
							t.getPontuacao() });

				}
			}

			if (l < 2) {
				l++;
			} else {
				posicao = posicao + 280;
				l = 0;
			}
		}

	}

	public static void main(String[] args) {
		Campeonato campeonato = CampeonatoDao.getCampeonato(12);
		TelaGrupo tm = new TelaGrupo(campeonato);
		tm.atualizarTela(campeonato);
		tm.setVisible(true);
	}
}
