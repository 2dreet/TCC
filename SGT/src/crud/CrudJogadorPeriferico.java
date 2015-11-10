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
import dao.JogadorDao;
import dao.MarcaDao;
import dao.TimeDao;
import dialog.DialogCrudBanimento;
import dialog.DialogCrudJogadorBanimento;
import dialog.DialogCrudJogadorPeriferico;
import dialog.DialogCrudMarca;
import entidade.Banimento;
import entidade.Jogador;
import entidade.JogadorBanimento;
import entidade.JogadorPeriferico;
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

public class CrudJogadorPeriferico extends JPanel{

	private MenuJogador menuPai;
	private JLabel lblMsg;
	private JPanel msg;
	private JPanel header;
	private JLabel lblHeader;
	private JPanel meio;
	private JButton btAdd;
	private JButton btAlt;
	private JButton btDel;
	private JogadorPeriferico jogadorPerifericoSelecionado;
	private Jogador jogadorSelecionado;
	private List<JogadorPeriferico> listaJogadorPeriferico;
	private static JTable tabela;
	private static Object[][] colunas = new Object[][] { new String[] { "Código" },
		new String[] { "Periférico" },
		new String[] { "Marca" }};
	private boolean inicio;
	public CrudJogadorPeriferico(MenuJogador menuPai, Jogador jogadorSelecionado){
		this.menuPai = menuPai;
		this.jogadorSelecionado = jogadorSelecionado;
		setSize(UtilitarioTela.getTamanhoMeio());
		setLayout(null);
		setBackground(null);
		jogadorPerifericoSelecionado = null;
		inicio = true;
		header = new JPanel();
		header.setSize(560, 30);
		header.setLocation((getWidth() /2) - 280 , 10);
		header.setLayout(null);
		header.setBackground(Color.white);
		header.setBorder(null);
		add(header);

		lblHeader = new JLabel("Jogador Periférico");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setBounds(0, 0, header.getWidth(), header.getHeight());
		lblHeader.setFont(UtilitarioTela.getFont(14));
		lblHeader.setForeground(UtilitarioTela.getFontColorCrud());
		header.add(lblHeader);

		meio = new JPanel();
		meio.setSize(560, getHeight() - 50);
		meio.setLocation((getWidth() /2) - 280 , 40);
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

		
		UtilitarioTabela.pintarColona(UtilitarioTabela.getFundoHeaderPadrao(),
				UtilitarioTabela.getFontColotHeaderPadrao(), tcm, colunas);
		UtilitarioTabela.pintarLinha(new Color(255, 153, 153), Color.black, tabela);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.setPreferredScrollableViewportSize(tabela.getPreferredSize());
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(10, 10, 540, 300);
		meio.add(scroll);
		
		btAdd = new JButton("Adicionar");
		btAdd.setBounds(10, 315, 100, 30);
		btAdd.setFocusPainted(false);
		btAdd.setBackground(UtilitarioTela.getColorCrud(ParametroCrud.getModoCrudNovo()));
		btAdd.setLayout(null);
		btAdd.setHorizontalAlignment(SwingConstants.LEFT);
		btAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DialogCrudJogadorPeriferico c = new DialogCrudJogadorPeriferico();
				c.crudJogadorPeriferico(null, ParametroCrud.getModoCrudNovo(), meio, jogadorSelecionado);
				if(c.getConfirmado()){
					menuPai.liberarCrud();
					Menssage.setMenssage("Periférico de Jogador", "Periférico Cadastrado com Sucesso!", ParametroCrud.getModoCrudNovo(), meio);
					localizar();
				}
			}
		});
		meio.add(btAdd);
		
		btAlt = new JButton("Alterar");
		btAlt.setBounds(120, 315, 100, 30);
		btAlt.setFocusPainted(false);
		btAlt.setBackground(UtilitarioTela.getColorCrud(ParametroCrud.getModoCrudAlterar()));
		btAlt.setLayout(null);
		btAlt.setHorizontalAlignment(SwingConstants.LEFT);
		btAlt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(selecionar()){
					DialogCrudJogadorPeriferico c = new DialogCrudJogadorPeriferico();
					c.crudJogadorPeriferico(jogadorPerifericoSelecionado, ParametroCrud.getModoCrudAlterar(),meio, jogadorSelecionado);
					if(c.getConfirmado()){
						Menssage.setMenssage("Periférico de Jogador", "Periférico alterado com Sucesso!", ParametroCrud.getModoCrudAlterar(), meio);
						localizar();
					}
				}
			}
		});
		//meio.add(btAlt);
		
		btDel = new JButton("Remover");
		btDel.setBounds(120, 315, 100, 30);
		btDel.setFocusPainted(false);
		btDel.setBackground(UtilitarioTela.getColorCrud(ParametroCrud.getModoCrudDeletar()));
		btDel.setLayout(null);
		btDel.setHorizontalAlignment(SwingConstants.LEFT);
		btDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(selecionar()){
					DialogCrudJogadorPeriferico c = new DialogCrudJogadorPeriferico();
					c.crudJogadorPeriferico(jogadorPerifericoSelecionado, ParametroCrud.getModoCrudDeletar(),meio, jogadorSelecionado);
					if(c.getConfirmado()){
						Menssage.setMenssage("Periférico de Jogador", "Periférico deletado com Sucesso!", ParametroCrud.getModoCrudDeletar(), meio);
						localizar();
					}
				}
			}
		});
		meio.add(btDel);
		localizar();
		
	}
	
	public boolean selecionar() {
		if (tabela.getRowCount() > 0) {
			if (tabela.getSelectedRow() > -1) {
				jogadorPerifericoSelecionado = JogadorDao.getJogadorPeriferico(Integer.parseInt(String.valueOf(tabela
						.getValueAt(tabela.getSelectedRow(), 0))));
				return true;
			} else {
				Menssage.setMenssage("Periférico não Selecionado",
						"Deve selecionar um Periférico!",
						ParametroCrud.getModoCrudDeletar(), meio);
				return false;
			}
		} else {
			Menssage.setMenssage("Periférico não Selecionado",
					"Deve selecionar um Periférico!",
					ParametroCrud.getModoCrudDeletar(), meio);
			return false;
		}

	}
	
	public void localizar() {
		listaJogadorPeriferico = JogadorDao.getListaJogadorPeriferico(jogadorSelecionado.getCodigoJogador());
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.setNumRows(0);
		if (listaJogadorPeriferico != null && listaJogadorPeriferico.size() > 0) {
			for (JogadorPeriferico jb : listaJogadorPeriferico) {
				modelo.addRow(new Object[] {
						String.valueOf(jb.getCodigoJD()), jb.getMarca().getDescricao() , jb.getPeriferico().getDescricao()});
			}
		} else if(!inicio){
			listaJogadorPeriferico = new ArrayList<JogadorPeriferico>();
		}
	}
	
}
