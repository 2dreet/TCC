package localizar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import utilitario.BordaSombreada;
import utilitario.ParametroCrud;
import utilitario.Parametros;
import utilitario.UtilitarioCrud;
import utilitario.UtilitarioTabela;
import utilitario.UtilitarioTela;

import javax.swing.JComboBox;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.JButton;

import componente.ComboBox;
import componente.TabelaCell;
import componente.TextoIconeCell;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import exemplos.Tabela;

public class LocalizarJogador extends JPanel {

	
	private JTable tabela;
	private JTextField txBusca;

	/**
	 * Create the panel.
	 */
	public LocalizarJogador() {
		setSize(UtilitarioTela.getTamanhoMeio());
		setLayout(null);
		setBackground(null);
		
		JPanel header = new JPanel();
		header.setSize(650, 30);
		header.setLocation((getWidth()/2)-400, 10);
		header.setLayout(null);
		header.setBackground(Color.white);
		header.setBorder(null);
		add(header);
		
		JLabel lblHeader = new JLabel("Localizar Jogador");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setBounds(0, 0, header.getWidth(), header.getHeight());
		lblHeader.setFont(UtilitarioTela.getFont(14));
		lblHeader.setForeground(UtilitarioTela.getFontColorCrud());
		header.add(lblHeader);
		
		JPanel meio = new JPanel();
		meio.setSize(650, getHeight()-50);
		meio.setLocation((getWidth()/2)-400, 40);
		meio.setLayout(null);
		meio.setBackground(new Color(232, 234, 239));
		meio.setBorder(new BordaSombreada());
		add(meio);
		
		JLabel lbNome = new JLabel("Busca :");
		lbNome.setBounds(10, 10, 55, 20);
		lbNome.setFont(UtilitarioTela.getFont(14));
		lbNome.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbNome);
		
		ComboBox metodoBusca = new ComboBox(new Dimension(150, 25));
		metodoBusca.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Feminino"}));
		metodoBusca.setLocation(70, 10);
		meio.add(metodoBusca);
		
		txBusca = new JTextField();
		txBusca.setColumns(100);
		txBusca.setBounds(222, 10, 310, 25);
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
		btLocalizar.setBounds(meio.getWidth()-110, 10, 100, 25);
		btLocalizar.setFont(UtilitarioTela.getFont(14));
		btLocalizar.setFocusPainted(false);
		//btLocalizar.setBackground(UtilitarioTela.getColorCrud(modoCrud));
		//btLocalizar.setIcon(UtilitarioCrud.getIconeCrud(modoCrud));
		meio.add(btLocalizar);
		btLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		Object[][] colunas = new Object[][] {
			new String[] { "Código" }, 
			new String[] { "Nome" },
			new String[] { "Usuário" },
			new String[] { "RG" },
			new String[] { "Telefone" },
			new String[] { "Email" }};
		tabela = new JTable();
		tabela.setModel(UtilitarioTabela.getModelo(colunas));
		TableColumnModel tcm = tabela.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(60);
	    tcm.getColumn(0).setMinWidth(60);
	    tcm.getColumn(0).setResizable(false);
	    tcm.getColumn(1).setPreferredWidth(170);
	    tcm.getColumn(1).setMinWidth(170);
	    tcm.getColumn(1).setResizable(false);
	    tcm.getColumn(2).setPreferredWidth(120);
	    tcm.getColumn(2).setMinWidth(120);
	    tcm.getColumn(2).setResizable(false);
	    tcm.getColumn(3).setPreferredWidth(83);
	    tcm.getColumn(3).setMinWidth(83);
	    tcm.getColumn(3).setResizable(false);
	    tcm.getColumn(4).setPreferredWidth(80);
	    tcm.getColumn(4).setMinWidth(80);
	    tcm.getColumn(4).setResizable(false);
	    tcm.getColumn(5).setPreferredWidth(130);
	    tcm.getColumn(5).setMinWidth(130);
	    tcm.getColumn(5).setResizable(false);
	    
		UtilitarioTabela.pintarColona(UtilitarioTabela.getFundoHeaderPadrao(), UtilitarioTabela.getFontColotHeaderPadrao(), tcm, colunas);
		UtilitarioTabela.pintarLinha(Color.red, Color.black, tabela);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.setPreferredScrollableViewportSize(tabela.getPreferredSize());
		tabela.getTableHeader().setReorderingAllowed(false);
		JScrollPane scroll = new JScrollPane(tabela); 
		scroll.setBounds( 2, 45, 646, 250);
		meio.add(scroll);
	}
}


