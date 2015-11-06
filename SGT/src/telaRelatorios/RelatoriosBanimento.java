package telaRelatorios;

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
import javax.swing.JTabbedPane;
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
import dao.MarcaDao;
import dao.TimeDao;
import dialog.DialogCrudBanimento;
import dialog.DialogCrudMarca;
import entidade.Banimento;
import entidade.Jogador;
import entidade.Marca;
import entidade.Time;
import utilitario.BordaSombreada;
import utilitario.ParametroCrud;
import utilitario.UtilitarioTabela;
import utilitario.UtilitarioTela;
import menu.MenuConfiguracoes;
import menu.MenuJogador;

public class RelatoriosBanimento extends JPanel{

	private JPanel header;
	private JLabel lblHeader;
	private JPanel meio;
	private JTabbedPane abas;
	
	public RelatoriosBanimento(Dimension tamanho){
		
		abas = new JTabbedPane();
		
		setSize(tamanho);
		setLayout(null);
		setBackground(null);
		
		header = new JPanel();
		header.setSize(500, 30);
		header.setLocation((getWidth() / 2) - 250, 10);
		header.setLayout(null);
		header.setBackground(Color.white);
		header.setBorder(null);
		add(header);

		lblHeader = new JLabel("Relatórios Banimento");
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
		
		abas.setSize(meio.getSize());
		abas.setLocation(0, 0);
		meio.add(abas);
	}
}
