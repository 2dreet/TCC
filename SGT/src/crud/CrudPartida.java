package crud;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JPanel;

import componente.DadoComIcone;
import componente.Menssage;
import componente.MenssageConfirmacao;
import componente.TextoIconeCell;
import tela.HomeFuncionario;
import utilitario.BordaEscura;
import utilitario.BordaSombreada;
import utilitario.MascaraCrud;
import utilitario.MoverArquivo;
import utilitario.ParametroCrud;
import utilitario.Parametros;
import utilitario.UtilitarioCrud;
import utilitario.UtilitarioImagem;
import utilitario.UtilitarioLogo;
import utilitario.UtilitarioTabela;
import utilitario.UtilitarioTela;
import utilitario.ValidadorCrud;

import javax.swing.JButton;

import dao.EntityManagerLocal;
import dao.JogadorBanimentoDao;
import dao.JogadorDao;
import dao.PartidaDao;
import dao.PermissaoDao;
import dao.TimeDao;
import dialog.DialogCrudPartida;
import dialog.DialogLocalizarJogador;
import entidade.Campeonato;
import entidade.CampeonatoTime;
import entidade.Jogador;
import entidade.Partida;
import entidade.Time;
import entidade.TimePartida;
import entidade.Usuario;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;

import componente.ComboBox;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTextArea;

import menu.MenuCampeonato;
import menu.MenuJogador;
import menu.MenuTime;

public class CrudPartida extends JPanel {
	private JLabel lblMsg;
	private Partida partidaSelecionada;
	private MenuCampeonato menuPai;
	private Campeonato campeonatoSelecionado;
	private JPanel header;
	private JLabel lblHeader;
	private JPanel meio;
	
	
	/**
	 * Create the panel.
	 */
	public CrudPartida(Campeonato campeonato, MenuCampeonato menuPai) {
		this.menuPai = menuPai;
		setSize(UtilitarioTela.getTamanhoMeio());
		setLayout(null);
		setBackground(null);
		campeonatoSelecionado = campeonato;
		header = new JPanel();
		header.setSize(650, 30);
		header.setLocation((getWidth() / 2) - 325, 10);
		header.setLayout(null);
		header.setBackground(UtilitarioTela.getColorCrud(ParametroCrud.getModoVisualizar()));
		header.setBorder(null);
		add(header);

		String textoHeader = "Partidas";
		
		lblHeader = new JLabel(textoHeader);
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setBounds(0, 0, header.getWidth(), header.getHeight());
		lblHeader.setFont(UtilitarioTela.getFont(14));
		lblHeader.setForeground(UtilitarioTela.getFontColorCrud());
		header.add(lblHeader);

		meio = new JPanel();
		meio.setSize(650, getHeight() - 50);
		meio.setLocation((getWidth() / 2) - 325, 40);
		meio.setLayout(null);
		meio.setBackground(UtilitarioTela.getFundoCrud());
		meio.setBorder(new BordaSombreada(ParametroCrud.getModoVisualizar()));
		add(meio);
		
		JButton btProximaPartida = new JButton("Proxima Partida");
		btProximaPartida.setSize(150, 30);
		btProximaPartida.setLocation(20, 20);
		btProximaPartida.setBackground(UtilitarioTela.getFontColorPadrao());
		btProximaPartida.setFocusPainted(false);
		btProximaPartida.setBorder(new BordaSombreada(false, true, false, false));
		btProximaPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Partida partida = PartidaDao.getProximaPartidaPartida(campeonatoSelecionado);
				DialogCrudPartida dcp = new DialogCrudPartida(partida);
			}
		});
		meio.add(btProximaPartida);
	}
	
}
