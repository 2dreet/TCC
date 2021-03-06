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
import dao.TimeDao;
import dialog.DialogCrudMarca;
import entidade.Jogador;
import entidade.Marca;
import entidade.Time;
import utilitario.BordaSombreada;
import utilitario.ParametroCrud;
import utilitario.UtilitarioTabela;
import utilitario.UtilitarioTela;
import menu.MenuConfiguracoes;
import menu.MenuJogador;

public class CrudMarca extends JPanel{

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
	private Marca marcaSelecionado;
	private List<Marca> listaMarca;
	private static JTable tabela;
	private static Object[][] colunas = new Object[][] { new String[] { "C�digo" },
		new String[] { "Descri��o" }};
	private static String[] linhaBusca = new String[] { "C�digo", "Descri��o"};
	private boolean inicio;
	public CrudMarca(MenuConfiguracoes menuPai){
		this.menuPai = menuPai;
		setSize(UtilitarioTela.getTamanhoMeio());
		setLayout(null);
		setBackground(null);
		marcaSelecionado = null;
		inicio = true;
		header = new JPanel();
		header.setSize(500, 30);
		header.setLocation((getWidth() / 2) - 250, 10);
		header.setLayout(null);
		header.setBackground(Color.white);
		header.setBorder(null);
		add(header);

		lblHeader = new JLabel("Marca");
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
				DialogCrudMarca c = new DialogCrudMarca();
				c.crudMarca(null, ParametroCrud.getModoCrudNovo(),meio);
				if(c.getConfirmado()){
					Menssage.setMenssage("Nova Marca", "Marca cadastrada com Sucesso!", ParametroCrud.getModoCrudNovo(), meio);
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
					DialogCrudMarca c = new DialogCrudMarca();
					c.crudMarca(marcaSelecionado, ParametroCrud.getModoCrudAlterar(),meio);
					if(c.getConfirmado()){
						Menssage.setMenssage("Altera Marca", "Marca alterada com Sucesso!", ParametroCrud.getModoCrudAlterar(), meio);
						localizar();
					}
				}
			}
		});
		meio.add(btAlt);
		
		btDel = new JButton("Remover");
		btDel.setBounds(270, 365, 100, 30);
		btDel.setFocusPainted(false);
		btDel.setBackground(UtilitarioTela.getColorCrud(ParametroCrud.getModoCrudDeletar()));
		btDel.setLayout(null);
		btDel.setHorizontalAlignment(SwingConstants.LEFT);
		btDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(selecionar()){
					DialogCrudMarca c = new DialogCrudMarca();
					c.crudMarca(marcaSelecionado, ParametroCrud.getModoCrudDeletar(),meio);
					if(c.getConfirmado()){
						Menssage.setMenssage("Deletar Marca", "Marca deletada com Sucesso!\nTodos os vinculos foram perdidos!", ParametroCrud.getModoCrudDeletar(), meio);
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
				marcaSelecionado = MarcaDao
						.getMarca(Integer.parseInt(String.valueOf(tabela
								.getValueAt(tabela.getSelectedRow(), 0))));
				return true;
			} else {
				Menssage.setMenssage("Marca n�o Selecionada",
						"Deve selecionar uma Marca!",
						ParametroCrud.getModoCrudDeletar(), meio);
				return false;
			}
		} else {
			Menssage.setMenssage("Marca n�o Selecionada",
					"Deve selecionar uma Marca!",
					ParametroCrud.getModoCrudDeletar(), meio);
			return false;
		}

	}
	
	public void localizar() {
		listaMarca = MarcaDao.getListaPesquisa(metodoBusca.getSelectedItem()
				.toString(), txBusca.getText());
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.setNumRows(0);
		if (listaMarca != null && listaMarca.size() > 0) {
			for (Marca m : listaMarca) {
				modelo.addRow(new Object[] {
						String.valueOf(m.getCodigoMarca()), m.getDescricao() });
			}
		} else if(!inicio) {
			listaMarca = new ArrayList<Marca>();
			Menssage.setMenssage("Marca n�o Encontrado",
					"Nenhuma Marca foi encontrada!",
					ParametroCrud.getModoCrudDeletar(), meio);
		}
	}
	
}
