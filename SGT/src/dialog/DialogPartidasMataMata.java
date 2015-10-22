package dialog;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.net.MalformedURLException;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.print.DocFlavor.URL;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import componente.ComboBox;
import componente.Menssage;
import componente.MenssageConfirmacao;
import dao.CampeonatoDao;
import dao.EntityManagerLocal;
import dao.MarcaDao;
import dao.PartidaDao;
import dao.PerifericoDao;
import tela.HomeFuncionario;
import utilitario.BordaSombreada;
import utilitario.ParametroCrud;
import utilitario.Parametros;
import utilitario.UtilitarioLogo;
import utilitario.UtilitarioTela;
import entidade.Campeonato;
import entidade.Driver;
import entidade.Marca;
import entidade.Partida;
import entidade.Periferico;

import javax.swing.JTree;

import java.awt.BorderLayout;

public class DialogPartidasMataMata {
	
	private JPanel meio;
	
	
	public DialogPartidasMataMata() {
		JFrame jf = new JFrame();
		jf.setSize(UtilitarioTela.getTamanhoMunitor());
		if(UtilitarioTela.getTamanhoMunitor().getWidth() <= 1100){
			jf.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		}
		Parametros.setPai(jf);
		DialogPartidasMataMata(CampeonatoDao.getCampeonato(1));

	}
	
	public void DialogPartidasMataMata(Campeonato camp) {
		JDialog dialog = new JDialog(Parametros.getPai(), true);
		dialog.setUndecorated(true);
		dialog.getContentPane().setLayout(null);
		dialog.setSize(Parametros.getPai().getSize());
		dialog.getContentPane().setBackground(new Color(232, 234, 239));
		dialog.setLocationRelativeTo(Parametros.getPai());
		
		JPanel panel = new JPanel();
		panel.setBorder(new BordaSombreada(new Color(46, 49, 56), new Color(102, 102, 102)));
		panel.setLayout(null);
		panel.setSize(dialog.getSize());
		panel.setBackground(null);
		panel.setLocation(0, 0);
		panel.setFocusable(true);
		panel.requestFocusInWindow();
		
		JPanel header = new JPanel();
		header.setSize(dialog.getWidth()-4, 30);
		header.setLayout(null);
		header.setLocation(2, 2);
		header.setBackground(UtilitarioTela.getColorCrud(ParametroCrud.getModoVisualizar()));
		header.setBorder(null);
		panel.add(header);
		
		String textoHeader = "Partidas Mata Mata";
		
		
		JLabel lbHeader = new JLabel(textoHeader);
		lbHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lbHeader.setSize(header.getSize().width - 30, 30);
		lbHeader.setFont(UtilitarioTela.getFont(14));
		lbHeader.setForeground(UtilitarioTela.getFontColorCrud());
		lbHeader.setBorder(new BordaSombreada(false, true, false, false));
		header.add(lbHeader);
		
		JButton btFechar = new JButton("");
		btFechar.setIcon(new ImageIcon(HomeFuncionario.class
				.getResource("/imagem/cancelBlack.png")));
		btFechar.setName("inicio");
		btFechar.setSize(30,30);
		btFechar.setLocation(header.getSize().width-30, 0);
		btFechar.setBackground(UtilitarioTela.getFontColorPadrao());
		btFechar.setFocusPainted(false);
		btFechar.setBorder(new BordaSombreada(false, true, false, false));
		btFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialog.dispose();
			}
		});
		header.add(btFechar);
		
		meio = new JPanel();
		meio.setSize(panel.getSize().width-4, panel.getSize().height-32);
		meio.setLayout(null);
		meio.setLocation(2, 30);
		meio.setBackground(new Color(232, 234, 239));
		panel.add(meio);
		
		gerarArvore(camp);
		
		dialog.getContentPane().add(panel);
		dialog.setVisible(true);
	}
	
	public void gerarArvore(Campeonato camp) {
		Partida partida = PartidaDao.getPartidaFinal(camp.getCodigoCampeonato(),false);
		int posicao = 0;
		if(partida != null){
			List<Partida> listaPai = PartidaDao.getPartidasPai(partida.getCodigoPartida());
			JPanel header = new JPanel();
			header.setSize(150, 70);
			int x = (int) (((meio.getSize().getWidth()/2) - 75) + posicao);
			int y = (int) (((meio.getSize().getHeight()/2) - 75) + posicao);
			header.setLocation( x, y);
			header.setBackground(Color.red);
			header.setBorder(null);
			meio.add(header);
			posicao += 80;
			for(int i = 0; i < listaPai.size(); i++){
				if(i == 0){
					gerarArvore(listaPai.get(0), posicao, 1, 0);
				} else{
					gerarArvore(listaPai.get(1), posicao, 0, 0);
				}
			}
		}
	}
	
	public void gerarArvore(Partida partida, int posicao, int sinal, int posicaoX){
		int x = 0;
		int y = 0;
		if(sinal == 0){
			x = (int) (((meio.getSize().getWidth()/2) - 75) + posicao);
		} else{
			x = (int) (((meio.getSize().getWidth()/2) - 75) - posicao);
		}
		
		if(posicaoX == 0){
			y = (int) (((meio.getSize().getHeight()/2) - 75) + posicao);
		} else {
			y = (int) (((meio.getSize().getHeight()/2) - 75) - posicao);
		}
		posicao += 70;
		if(PartidaDao.isPartidaFilho(partida.getCodigoPartida())){
			List<Partida> listaPai = PartidaDao.getPartidasPai(partida.getCodigoPartida());
			JPanel header = new JPanel();
			header.setSize(150, 70);
			header.setLocation(x, y);
			header.setBackground(Color.red);
			header.setBorder(null);
			meio.add(header);
			for(int i = 0; i < listaPai.size(); i++){
				if(i == 0){
					gerarArvore(listaPai.get(0), posicao, sinal,0);
				} else{
					gerarArvore(listaPai.get(1), posicao, sinal,1);
				}
			}
		} else {
			JPanel header = new JPanel();
			header.setSize(150, 70);
			header.setLocation( x, y);
			header.setBackground(Color.red);
			header.setBorder(null);
			meio.add(header);
		}
	}
	
	public static void main(String [] args) {
		DialogPartidasMataMata dp = new DialogPartidasMataMata();
	}
}
