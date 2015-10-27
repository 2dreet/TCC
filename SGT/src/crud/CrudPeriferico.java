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
import dao.MarcaDao;
import dao.PerifericoDao;
import dao.TimeDao;
import dialog.DialogCrudMarca;
import dialog.DialogCrudPeriferico;
import entidade.Jogador;
import entidade.Marca;
import entidade.Periferico;
import entidade.Time;
import utilitario.BordaSombreada;
import utilitario.ParametroCrud;
import utilitario.UtilitarioTabela;
import utilitario.UtilitarioTela;
import menu.MenuConfiguracoes;
import menu.MenuJogador;

public class CrudPeriferico extends JPanel{

	private MenuConfiguracoes menuPai;
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
	private Periferico perifericoSelecionado;
	private List<Periferico> listaPeriferico;
	private static JTable tabela;
	private static Object[][] colunas = new Object[][] { new String[] { "Código" },
		new String[] { "Descrição" }};
	private static String[] linhaBusca = new String[] { "Código", "Descrição"};
	private boolean inicio;
	public CrudPeriferico(MenuConfiguracoes menuPai){
		this.menuPai = menuPai;
		setSize(UtilitarioTela.getTamanhoMeio());
		setLayout(null);
		setBackground(null);
		perifericoSelecionado = null;
		inicio = true;
		header = new JPanel();
		header.setSize(500, 30);
		header.setLocation((getWidth() / 2) - 250, 10);
		header.setLayout(null);
		header.setBackground(null);
		header.setBorder(null);
		add(header);

		lblHeader = new JLabel("Periférico");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setBounds(0, 0, header.getWidth(), header.getHeight());
		lblHeader.setFont(UtilitarioTela.getFont(14));
		lblHeader.setForeground(UtilitarioTela.getFontColorCrud());
		header.add(lblHeader);

		meio = new JPanel();
		meio.setSize(500, getHeight() - 50);
		meio.setLocation((getWidth() / 2) - 250, 40);
		meio.setLayout(null);
		meio.setBackground(UtilitarioTela.getFundoCrud());
		meio.setBorder(null);
		add(meio);
		
		metodoBusca = new ComboBox(new Dimension(90, 25));
		metodoBusca.setModel(new DefaultComboBoxModel(linhaBusca));
		metodoBusca.setLocation(50, 20);
		meio.add(metodoBusca);

		txBusca = new JTextField();
		txBusca.setColumns(100);
		txBusca.setBounds(150, 20, 170, 25);
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
		btLocalizar.setBounds(330, 20, 120, 25);
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
		tcm.getColumn(0).setPreferredWidth(100);
		tcm.getColumn(0).setMinWidth(100);
		tcm.getColumn(0).setResizable(false);
		tcm.getColumn(1).setPreferredWidth(297);
		tcm.getColumn(1).setMinWidth(297);
		tcm.getColumn(1).setResizable(false);
		
		UtilitarioTabela.pintarColona(UtilitarioTabela.getFundoHeaderPadrao(),
				UtilitarioTabela.getFontColotHeaderPadrao(), tcm, colunas);
		UtilitarioTabela.pintarLinha(new Color(255, 153, 153), Color.black, tabela);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.setPreferredScrollableViewportSize(tabela.getPreferredSize());
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(50, 55, 400, 300);
		meio.add(scroll);
		
		btAdd = new JButton("Adicionar");
		btAdd.setBounds(50, 365, 100, 30);
		btAdd.setFocusPainted(false);
		btAdd.setBackground(UtilitarioTela.getColorCrud(ParametroCrud.getModoCrudNovo()));
		btAdd.setLayout(null);
		btAdd.setHorizontalAlignment(SwingConstants.LEFT);
		btAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DialogCrudPeriferico c = new DialogCrudPeriferico();
				c.crudPeriferico(null, ParametroCrud.getModoCrudNovo(),meio);
				if(c.getConfirmado()){
					Menssage.setMenssage("Novo Periférico", "Periférico cadastrado com Sucesso!", ParametroCrud.getModoCrudNovo(), meio);
					localizar();
				}
			}
		});
		meio.add(btAdd);
		
		btAlt = new JButton("Alterar");
		btAlt.setBounds(160, 365, 100, 30);
		btAlt.setFocusPainted(false);
		btAlt.setBackground(UtilitarioTela.getColorCrud(ParametroCrud.getModoCrudAlterar()));
		btAlt.setLayout(null);
		btAlt.setHorizontalAlignment(SwingConstants.LEFT);
		btAlt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(selecionar()){
					DialogCrudPeriferico c = new DialogCrudPeriferico();
					c.crudPeriferico(perifericoSelecionado, ParametroCrud.getModoCrudAlterar(),meio);
					if(c.getConfirmado()){
						Menssage.setMenssage("Altera Periférico", "Periférico alterado com Sucesso!", ParametroCrud.getModoCrudAlterar(), meio);
						localizar();
					}
				}
			}
		});
		meio.add(btAlt);
		
		btDel = new JButton("Remover");
		btDel.setBounds(330, 365, 100, 30);
		btDel.setFocusPainted(false);
		btDel.setBackground(UtilitarioTela.getColorCrud(ParametroCrud.getModoCrudDeletar()));
		btDel.setLayout(null);
		btDel.setHorizontalAlignment(SwingConstants.LEFT);
		btDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(selecionar()){
					DialogCrudPeriferico c = new DialogCrudPeriferico();
					c.crudPeriferico(perifericoSelecionado, ParametroCrud.getModoCrudDeletar(),meio);
					if(c.getConfirmado()){
						Menssage.setMenssage("Deletar Periférico", "Periférico deletado com Sucesso!\nTodos os vinculos foram perdidos!", ParametroCrud.getModoCrudDeletar(), meio);
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
				perifericoSelecionado = PerifericoDao
						.getPeriferico(Integer.parseInt(String.valueOf(tabela
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
		listaPeriferico = PerifericoDao.getListaPesquisa(metodoBusca.getSelectedItem()
				.toString(), txBusca.getText());
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.setNumRows(0);
		if (listaPeriferico != null && listaPeriferico.size() > 0) {
			for (Periferico p : listaPeriferico) {
				modelo.addRow(new Object[] {
						String.valueOf(p.getCodigoPeriferico()), p.getDescricao() });
			}
		} else if(!inicio){
			listaPeriferico = new ArrayList<Periferico>();
			Menssage.setMenssage("Periférico não Encontrado",
					"Nenhum Periférico foi encontrado!",
					ParametroCrud.getModoCrudDeletar(), meio);
		}
	}
	
}
