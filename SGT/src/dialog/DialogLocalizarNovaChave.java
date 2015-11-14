package dialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RepaintManager;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import localizar.LocalizarJogador;
import utilitario.BordaSombreada;
import utilitario.MascaraCrud;
import utilitario.ParametroCrud;
import utilitario.Parametros;
import utilitario.UtilitarioImagem;
import utilitario.UtilitarioTabela;
import utilitario.UtilitarioTela;
import componente.ComboBox;
import componente.DadoComIcone;
import componente.Menssage;
import componente.TextoIconeCell;
import dao.CampeonatoDao;
import dao.ChaveDao;
import dao.EntityManagerLocal;
import dao.JogadorDao;
import dao.TimeDao;
import entidade.Campeonato;
import entidade.Jogador;
import entidade.Time;

public class DialogLocalizarNovaChave {
	
	private static JDialog dialog;
	private static JPanel meio;
	private static Campeonato campeonatoSelecionado;
	private static boolean selecionou;
	public DialogLocalizarNovaChave(){
		super();
		campeonatoSelecionado = null;
	}
	
	public static void localizarChave(JPanel painelPai, Campeonato campeonato) {
		campeonatoSelecionado = campeonato;
		dialog = new JDialog(Parametros.getPai(), true);
		dialog.setUndecorated(true);
		dialog.setLayout(null);
		dialog.setSize(300, 200);
		dialog.getContentPane().setBackground(new Color(51, 153, 255));
		dialog.setLocationRelativeTo(painelPai);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BordaSombreada(new Color(46, 49, 56), new Color(0, 128, 255)));
		panel.setLayout(null);
		panel.setSize(dialog.getSize());
		panel.setBackground(null);
		panel.setLocation(0, 0);
		panel.setFocusable(true);
		panel.requestFocusInWindow();
		
		JLabel lbHeader = new JLabel("Nova Chave");
		lbHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lbHeader.setBounds(2, 10, 300, 30);
		lbHeader.setFont(UtilitarioTela.getFont(14));
		lbHeader.setForeground(UtilitarioTela.getFontColorCrud());
		lbHeader.setBorder(new BordaSombreada(false, true, false, false));
		panel.add(lbHeader);
		
		JPanel meio = new JPanel();
		meio.setSize(300, 150);
		meio.setLayout(null);
		meio.setLocation(2, 40);
		meio.setBackground(new Color(224, 224, 224));
		panel.add(meio);
		
		JLabel lbNome = new JLabel("Selecione a nova Chave");
		lbNome.setBounds(10, 10, 250, 20);
		lbNome.setFont(UtilitarioTela.getFont(14));
		lbNome.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbNome);
		
		JButton btSelecionar = new JButton("Mata - Mata");
		btSelecionar.setBounds(5, 50, 120, 25);
		btSelecionar.setForeground(UtilitarioTela.getColorErro());
		btSelecionar.setFont(UtilitarioTela.getFont(14));
		btSelecionar.setFocusPainted(false);
		btSelecionar.setBackground(new Color(46, 49, 56));
		btSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selecionou = true;
				campeonatoSelecionado.setChave(ChaveDao.getChave(1));
				EntityManagerLocal.begin();
				EntityManagerLocal.merge(campeonatoSelecionado);
				EntityManagerLocal.commit();
				dialog.dispose();
			}
		});
		meio.add(btSelecionar);

		JButton btLimparSelecao = new JButton("Winner Lower");
		btLimparSelecao.setBounds(130, 50, 150, 25);
		btLimparSelecao.setFont(UtilitarioTela.getFont(14));
		btLimparSelecao.setFocusPainted(false);
		btLimparSelecao.setForeground(new Color(46, 49, 56));
		btLimparSelecao.setBackground(UtilitarioTela.getFontColorPadrao());
		btLimparSelecao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selecionou = true;
				campeonatoSelecionado.setChave(ChaveDao.getChave(2));
				EntityManagerLocal.begin();
				EntityManagerLocal.merge(campeonatoSelecionado);
				EntityManagerLocal.commit();
				dialog.dispose();
			}
		});
		meio.add(btLimparSelecao);
		
		dialog.getContentPane().add(panel);
		dialog.setVisible(true);
	}
	
	public Campeonato isSelecionado(){
		if(selecionou){
			return campeonatoSelecionado;
		}
		return null;
	}
	
	public static void main(String [] args){
		JFrame janela = new JFrame();
		janela.setSize(500, 500);
		JPanel panel = new JPanel();
		panel.setSize(500,500);
		janela.add(panel);
		localizarChave(panel, CampeonatoDao.getCampeonato(1));
	}
}
