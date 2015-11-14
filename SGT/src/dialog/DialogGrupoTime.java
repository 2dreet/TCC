package dialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.omg.CORBA.TCKind;

import componente.ComboBox;
import componente.DadoComIcone;
import componente.Menssage;
import componente.MenssageConfirmacao;
import componente.TextoIconeCell;
import dao.CampeonatoDao;
import dao.CampeonatoTimeDao;
import dao.EntityManagerLocal;
import dao.MarcaDao;
import dao.PartidaDao;
import dao.PerifericoDao;
import dao.TimeDao;
import utilitario.BordaSombreada;
import utilitario.ParametroCrud;
import utilitario.Parametros;
import utilitario.UtilitarioImagem;
import utilitario.UtilitarioLogo;
import utilitario.UtilitarioTabela;
import utilitario.UtilitarioTela;
import entidade.Campeonato;
import entidade.CampeonatoTime;
import entidade.Grupo;
import entidade.Marca;
import entidade.Periferico;
import entidade.Time;
import entidade.TimeGrupo;

public class DialogGrupoTime {
	
	public JTextField txNome;
	private boolean confirmado;
	private Grupo grupoSelecionado;
	private JLabel lblMsg;
	private JPanel msg;
	private JPanel meio;
	private JTable tabela;
	private Object[][] colunas = new Object[][] { new String[] { "Logo" } , new String[] { "Código" },
			 new String[] { "Nome" }, new String[] { "Placar" } };
	private List<TimeGrupo> listaTime;
	private Time seedA;
	private Time seedB;
	private Campeonato campeonatoSelecionado;
	private TimeGrupo tg;
	
	public DialogGrupoTime(){
	
	}
	
	public void getGrupoSelectTime(Grupo grupoSelecionado, Campeonato campeonatoSelecionado, JFrame painelPai, String textoHeader, int qtd, TimeGrupo tg){
		this.grupoSelecionado = grupoSelecionado;
		this.campeonatoSelecionado = campeonatoSelecionado;
		this.tg = tg;
		JDialog dialog = new JDialog(Parametros.getPai(), true);
		dialog.setUndecorated(true);
		dialog.getContentPane().setLayout(null);
		dialog.setSize(675, 352);
		dialog.getContentPane().setBackground(new Color(232, 234, 239));
		dialog.setLocationRelativeTo(painelPai);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BordaSombreada(new Color(46, 49, 56), new Color(102, 102, 102)));
		panel.setLayout(null);
		panel.setSize(dialog.getSize());
		panel.setBackground(null);
		panel.setLocation(0, 0);
		panel.setFocusable(true);
		panel.requestFocusInWindow();
		
		JPanel header = new JPanel();
		header.setSize(671, 30);
		header.setLayout(null);
		header.setLocation(2, 2);
		header.setBorder(null);
		panel.add(header);
		
		JLabel lbHeader = new JLabel(textoHeader);
		lbHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lbHeader.setBounds(0, 0, 671, 30);
		lbHeader.setFont(UtilitarioTela.getFont(14));
		lbHeader.setForeground(UtilitarioTela.getFontColorCrud());
		lbHeader.setBorder(new BordaSombreada(false, true, false, false));
		header.add(lbHeader);
		
		meio = new JPanel();
		meio.setSize(671, 320);
		meio.setLayout(null);
		meio.setLocation(2, 30);
		meio.setBackground(new Color(232, 234, 239));
		panel.add(meio);
		
		tabela = new JTable();
		tabela.setModel(UtilitarioTabela.getModelo(colunas));

		TableColumnModel tcm = tabela.getColumnModel();
		TextoIconeCell renderer = new TextoIconeCell();
		tcm.getColumn(0).setCellRenderer(renderer);
		tcm.getColumn(0).setPreferredWidth(50);
		tcm.getColumn(0).setMinWidth(50);
		tcm.getColumn(0).setResizable(false);
		tcm.getColumn(1).setPreferredWidth(50);
		tcm.getColumn(1).setMinWidth(50);
		tcm.getColumn(1).setResizable(false);
		tcm.getColumn(2).setPreferredWidth(465);
		tcm.getColumn(2).setMinWidth(465);
		tcm.getColumn(2).setResizable(false);
		tcm.getColumn(1).setPreferredWidth(50);
		tcm.getColumn(1).setMinWidth(50);
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
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(5, 10, 660, 200);
		meio.add(scroll);
		
		atualizarTabela();
		if(qtd == 2){
			JButton btSeedA = new JButton("Selecionar Time 1º");
			btSeedA.setBounds(5, 215, 310, 50);
			btSeedA.setBackground(UtilitarioTela.getBtnFundo(false));
			btSeedA.setFocusPainted(false);
			btSeedA.setForeground(UtilitarioTela.getBtnFundo(true));
			btSeedA.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(selecionar(1)){
						btSeedA.setIcon(UtilitarioImagem.converterImage(seedA.getLogo()));
						btSeedA.setText("Time 1º "+seedA.getDescricao());
					}
				}
			});
			meio.add(btSeedA);
		}
		JButton btSeedB = new JButton("Selecionar Time 2º");
		btSeedB.setBounds(355, 215, 310, 50);
		btSeedB.setFocusPainted(false);
		btSeedB.setBackground(UtilitarioTela.getBtnFundo(true));
		btSeedB.setForeground(UtilitarioTela.getBtnFundo(false));
		btSeedB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(selecionar(2)){
					btSeedB.setIcon(UtilitarioImagem.converterImage(seedB.getLogo()));
					btSeedB.setText("Time 2º "+seedB.getDescricao());
				}
			}
		});
		meio.add(btSeedB);
		
		JButton finalizar = new JButton("Finalizar");
		finalizar.setBounds(240, 280, 180, 30);
		finalizar.setBackground(UtilitarioTela.getColorCrud(ParametroCrud.getModoCrudNovo()));
		finalizar.setFocusPainted(false);
		finalizar.setVisible(true);
		finalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(seedA != null && seedB != null){
					dialog.setVisible(false);
					return;
				} else if(seedA == null){
					Menssage.setMenssage("Selecione o Seed A",
							"Selecionar o Seed A",
							ParametroCrud.getModoCrudDeletar(), meio);
				} else if(seedB == null){
					Menssage.setMenssage("Selecione o Seed B",
							"Selecionar o Seed B",
							ParametroCrud.getModoCrudDeletar(), meio);
				}
			}
		});
		meio.add(finalizar);
		
		Menssage.setMenssage("Selecione os Times",
				"Selecionar os Times para a Faze de "+campeonatoSelecionado.getChave().getDescricao(),
				ParametroCrud.getModoCrudDeletar(), meio);
		
		atualizarTabela();
		
		dialog.getContentPane().add(panel);
		dialog.setVisible(true);
	}
	
	public boolean selecionar(int seed) {
		if (tabela.getRowCount() > 0) {
			if (tabela.getSelectedRow() > -1) {
				Time timeSelecionado = TimeDao
						.getTime(Integer.parseInt(String.valueOf(tabela
								.getValueAt(tabela.getSelectedRow(), 1))));
				if (timeSelecionado != null) {
					if(seed == 1 && seedB != timeSelecionado){
						seedA = timeSelecionado;
						return true;
					} else if(seed == 2 && seedA != timeSelecionado) {
						seedB = timeSelecionado;
						return true;
					}
				} 
			} else {
				Menssage.setMenssage("Time não Selecionado",
						"Deve selecionar um Time!",
						ParametroCrud.getModoCrudDeletar(), meio);
			}
		} else {
			Menssage.setMenssage("Time não Selecionado",
					"Deve selecionar um Time!",
					ParametroCrud.getModoCrudDeletar(), meio);
		}
		return false;

	}
	
	public void atualizarTabela() {
		listaTime = CampeonatoTimeDao
				.getListaTimeGrupo(tg, grupoSelecionado);
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.setNumRows(0);
		if (listaTime != null) {
			for (TimeGrupo t : listaTime) {
				modelo.addRow(new Object[] {
						new DadoComIcone("", UtilitarioImagem.converterImage(t
								.getTime().getLogo())),
								t.getTime().getCodigoTime(),
								t.getTime().getDescricao(),
						PartidaDao.getPlacar(t.getTime().getCodigoTime(), t.getGrupo().getCampeonato().getCodigoCampeonato(), t.getGrupo().getCodigoGrupo())});

			}
		} else {
			listaTime = new ArrayList<TimeGrupo>();
		}
	}
	
	
	
	public Time getSeedA() {
		return seedA;
	}

	public void setSeedA(Time seedA) {
		this.seedA = seedA;
	}

	public Time getSeedB() {
		return seedB;
	}

	public void setSeedB(Time seedB) {
		this.seedB = seedB;
	}

	public List<Time> getSeeds(){
		List<Time> listaSeeds = new ArrayList<Time>();
		listaSeeds.add(seedA);
		listaSeeds.add(seedB);
		return listaSeeds;
	}
}
