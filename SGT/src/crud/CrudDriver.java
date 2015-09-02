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
import dao.DriverDao;
import dao.MarcaDao;
import dao.PerifericoDao;
import dao.TimeDao;
import dialog.DialogCrudDriver;
import dialog.DialogCrudMarca;
import dialog.DialogCrudPeriferico;
import entidade.Driver;
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

public class CrudDriver extends JPanel{

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
	private Driver driverSelecionado;
	private List<Driver> listaDriver;
	private static JTable tabela;
	private static Object[][] colunas = new Object[][] { new String[] { "C�digo" },
		new String[] { "Descri��o" }};
	private static String[] linhaBusca = new String[] { "C�digo", "Descri��o"};
	
	public CrudDriver(MenuConfiguracoes menuPai){
		this.menuPai = menuPai;
		setSize(UtilitarioTela.getTamanhoMeio());
		setLayout(null);
		setBackground(null);
		driverSelecionado = null;
		
		header = new JPanel();
		header.setSize(500, 30);
		header.setLocation((getWidth() / 2) - 250, 10);
		header.setLayout(null);
		header.setBackground(null);
		header.setBorder(null);
		add(header);

		lblHeader = new JLabel("");
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
				DialogCrudDriver c = new DialogCrudDriver();
				c.crudDriver(null, ParametroCrud.getModoCrudNovo(),meio);
				if(c.getConfirmado()){
					Menssage.setMenssage("Novo Driver", "Driver cadastrado com Sucesso!", ParametroCrud.getModoCrudNovo(), meio);
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
					DialogCrudDriver c = new DialogCrudDriver();
					c.crudDriver(driverSelecionado, ParametroCrud.getModoCrudAlterar(),meio);
					if(c.getConfirmado()){
						Menssage.setMenssage("Altera Driver", "Driver alterado com Sucesso!", ParametroCrud.getModoCrudAlterar(), meio);
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
					DialogCrudDriver c = new DialogCrudDriver();
					c.crudDriver(driverSelecionado, ParametroCrud.getModoCrudDeletar(),meio);
					if(c.getConfirmado()){
						Menssage.setMenssage("Deletar Driver", "Driver deletado com Sucesso!", ParametroCrud.getModoCrudDeletar(), meio);
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
				driverSelecionado = DriverDao
						.getDriver(Integer.parseInt(String.valueOf(tabela
								.getValueAt(tabela.getSelectedRow(), 0))));
				return true;
			} else {
				Menssage.setMenssage("Driver n�o Selecionado",
						"Deve selecionar um Driver!",
						ParametroCrud.getModoCrudDeletar(), meio);
				return false;
			}
		} else {
			Menssage.setMenssage("Driver n�o Selecionado",
					"Deve selecionar um Driver!",
					ParametroCrud.getModoCrudDeletar(), meio);
			return false;
		}

	}
	
	public void localizar() {
		listaDriver = DriverDao.getListaPesquisa(metodoBusca.getSelectedItem()
				.toString(), txBusca.getText());
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.setNumRows(0);
		if (listaDriver != null && listaDriver.size() > 0) {
			for (Driver d : listaDriver) {
				modelo.addRow(new Object[] {
						String.valueOf(d.getCodigoDriver()), d.getDescricao() });
			}
		} else {
			listaDriver = new ArrayList<Driver>();
			Menssage.setMenssage("Driver n�o Encontrado",
					"Nenhum Driver foi encontrado!",
					ParametroCrud.getModoCrudDeletar(), meio);
		}
	}
	
}