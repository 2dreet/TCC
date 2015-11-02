package crud;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import componente.ComboBox;
import componente.DadoComIcone;
import componente.Menssage;
import dao.BanimentoDao;
import dao.JogadorBanimentoDao;
import dao.MarcaDao;
import dao.TimeDao;
import dialog.DialogCrudBanimento;
import dialog.DialogCrudJogadorBanimento;
import dialog.DialogCrudMarca;
import entidade.Banimento;
import entidade.Jogador;
import entidade.JogadorBanimento;
import entidade.Marca;
import entidade.Time;
import utilitario.BordaSombreada;
import utilitario.MascaraCrud;
import utilitario.ParametroCrud;
import utilitario.UtilitarioCrud;
import utilitario.UtilitarioTabela;
import utilitario.UtilitarioTela;
import menu.MenuConfiguracoes;
import menu.MenuJogador;

public class CrudHistoricoBanimentoJogador extends JPanel{

	private MenuJogador menuPai;
	private JLabel lblMsg;
	private JPanel msg;
	private JPanel header;
	private JLabel lblHeader;
	private JPanel meio;
	private JButton btAdd;
	private JButton btAlt;
	private JButton btDel;
	private JTextField txBusca;
	private ComboBox metodoBusca;
	private JogadorBanimento JogadorBanimentoSelecionado;
	private Jogador jogadorSelecionado;
	private List<JogadorBanimento> listaJogadorBanimento;
	private static JTable tabela;
	private static Object[][] colunas = new Object[][] { new String[] { "Código" },
		new String[] { "Descrição" },
		new String[] { "Banimento" },
		new String[] { "Data Banimento" }};
	private static String[] linhaBusca = new String[] { "Código", "Descrição"};
	private boolean inicio;
	
	public CrudHistoricoBanimentoJogador(Jogador jogadorSelecionado,MenuJogador menuPai){
		this.menuPai = menuPai;
		this.jogadorSelecionado = jogadorSelecionado;
		setSize(UtilitarioTela.getTamanhoMeio());
		setLayout(null);
		setBackground(null);
		JogadorBanimentoSelecionado = null;
		inicio = true;
		header = new JPanel();
		header.setSize(650, 30);
		header.setLocation((getWidth() / 2) - 250, 10);
		header.setLayout(null);
		header.setBackground(UtilitarioTela.getFontColorPadrao());
		header.setBorder(null);
		add(header);

		lblHeader = new JLabel("Histórico Banimento");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setBounds(0, 0, header.getWidth(), header.getHeight());
		lblHeader.setFont(UtilitarioTela.getFont(14));
		lblHeader.setForeground(UtilitarioTela.getFontColorCrud());
		header.add(lblHeader);

		meio = new JPanel();
		meio.setSize(650, getHeight() - 50);
		meio.setLocation((getWidth() / 2) - 250, 40);
		meio.setLayout(null);
		meio.setBackground(UtilitarioTela.getFundoCrud());
		meio.setBorder(null);
		add(meio);
		
		tabela = new JTable();
		tabela.setModel(UtilitarioTabela.getModelo(colunas));
		TableColumnModel tcm = tabela.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(100);
		tcm.getColumn(0).setMinWidth(100);
		tcm.getColumn(0).setResizable(false);
		tcm.getColumn(1).setPreferredWidth(220);
		tcm.getColumn(1).setMinWidth(220);
		tcm.getColumn(1).setResizable(false);
		tcm.getColumn(2).setPreferredWidth(216);
		tcm.getColumn(2).setMinWidth(216);
		tcm.getColumn(2).setResizable(false);
		tcm.getColumn(3).setPreferredWidth(100);
		tcm.getColumn(3).setMinWidth(100);
		tcm.getColumn(3).setResizable(false);
		
		UtilitarioTabela.pintarColona(UtilitarioTabela.getFundoHeaderPadrao(),
				UtilitarioTabela.getFontColotHeaderPadrao(), tcm, colunas);
		UtilitarioTabela.pintarLinha(new Color(255, 153, 153), Color.black, tabela);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.setPreferredScrollableViewportSize(tabela.getPreferredSize());
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(5, 10, 640, (int) (UtilitarioTela.getTamanhoMeio().getHeight() - 100));
		meio.add(scroll);
		
		
		localizar();
		
		if(!BanimentoDao.banimentosAtivos()){
			btAdd.setEnabled(false);
			btAlt.setEnabled(false);
		}
	}
	
	public boolean selecionar() {
		if (tabela.getRowCount() > 0) {
			if (tabela.getSelectedRow() > -1) {
				JogadorBanimentoSelecionado = JogadorBanimentoDao
						.getJogadorBanimento(Integer.parseInt(String.valueOf(tabela
								.getValueAt(tabela.getSelectedRow(), 0))));
				return true;
			} else {
				Menssage.setMenssage("Banimento não Selecionado",
						"Deve selecionar um Banimento!",
						ParametroCrud.getModoCrudDeletar(), meio);
				return false;
			}
		} else {
			Menssage.setMenssage("Banimento não Selecionado",
					"Deve selecionar um Banimento!",
					ParametroCrud.getModoCrudDeletar(), meio);
			return false;
		}

	}
	
	public void localizar() {
		listaJogadorBanimento = JogadorBanimentoDao.getHistorico(jogadorSelecionado.getCodigoJogador());
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.setNumRows(0);
		if (listaJogadorBanimento != null && listaJogadorBanimento.size() > 0) {
			for (JogadorBanimento jb : listaJogadorBanimento) {
				modelo.addRow(new Object[] {
						String.valueOf(jb.getCodigoJogBan()), jb.getDescricao() , jb.getBanimento().getDescricao() , MascaraCrud.macaraDataBanco(jb.getDataBanimento()) });
			}
		} else if(!inicio){
			listaJogadorBanimento = new ArrayList<JogadorBanimento>();
			Menssage.setMenssage("Banimento não Encontrado",
					"Nenhum Banimento foi encontrado!",
					ParametroCrud.getModoCrudDeletar(), meio);
		}
	}
	
}
