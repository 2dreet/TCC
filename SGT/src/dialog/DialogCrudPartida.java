package dialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Date;
import java.util.List;
import java.util.Vector;

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

import org.omg.CORBA.TCKind;

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
import entidade.Driver;
import entidade.Marca;
import entidade.Partida;
import entidade.Periferico;
import entidade.Time;
import entidade.TimePartida;

public class DialogCrudPartida {
	
	public JTextField txtPlacar;
	public JTextField txtPlacar2;
	private boolean confirmado;
	private Partida partidaSelecionado;
	private JLabel lblMsg;
	private JPanel msg;
	private JPanel meio;
	private TimePartida time1;
	private TimePartida time2;
	
	public DialogCrudPartida() {
		JFrame jf = new JFrame();
		jf.setSize(UtilitarioTela.getTamanhoMunitor());
		if(UtilitarioTela.getTamanhoMunitor().getWidth() <= 1100){
			jf.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		}
		Parametros.setPai(jf);
		crudPartida(PartidaDao.getPartida(1));

	}
	
	public void crudPartida(Partida partidaSelecionado){
		this.partidaSelecionado = partidaSelecionado;
		setTimes();
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
		
		String textoHeader = "";
		if (partidaSelecionado.getHoraInicio() == null) {
			textoHeader = "Partida n�o Iniciada!";
		} else {
			textoHeader = "Partida J� Iniciada!";
		} 
		
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
		
		JLabel lbNome = new JLabel("Time 1 :");
		lbNome.setBounds(60, 10, 300, 50);
		lbNome.setFont(UtilitarioTela.getFont(14));
		lbNome.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbNome);
		
		txtPlacar = new JTextField();
		txtPlacar.setColumns(100);
		txtPlacar.setBounds(10, 25, 50, 25);
		txtPlacar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtPlacar.setBorder(UtilitarioTela.jTextFieldComFocus());
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				txtPlacar.setBorder(UtilitarioTela.jTextFieldNormal());
			}
		});
		txtPlacar.setLayout(null);
		txtPlacar.setBorder(UtilitarioTela.jTextFieldNormal());
		meio.add(txtPlacar);
		
		JLabel lbNome2 = new JLabel("Time 2 :");
		lbNome2.setBounds((meio.getSize().width/2) + 110, 10, 300, 50);
		lbNome2.setFont(UtilitarioTela.getFont(14));
		lbNome2.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbNome2);
		
		txtPlacar2 = new JTextField();
		txtPlacar2.setColumns(100);
		txtPlacar2.setBounds((meio.getSize().width/2) + 50, 25, 50, 25);
		txtPlacar2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtPlacar2.setBorder(UtilitarioTela.jTextFieldComFocus());
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				txtPlacar2.setBorder(UtilitarioTela.jTextFieldNormal());
			}
		});
		txtPlacar2.setLayout(null);
		txtPlacar2.setBorder(UtilitarioTela.jTextFieldNormal());
		meio.add(txtPlacar2);
		
		JLabel lbPeriferico = new JLabel("Perif�rico :");
		lbPeriferico.setBounds(20, 45, 90, 20);
		lbPeriferico.setFont(UtilitarioTela.getFont(14));
		lbPeriferico.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbPeriferico);
		
		msg = new JPanel();
		msg.setSize(396, 35);
		msg.setLocation(0, 70);
		msg.setLayout(null);
		msg.setBackground(null);
		meio.add(msg);
		
		String texto = "";
		 if (partidaSelecionado.getHoraInicio()==null) {
			texto = "Iniciar";
		}else{
			texto = "Terminar";
		} 
		
		
		JButton confirmar = new JButton(texto);
		confirmar.setSize(150,30);
		confirmar.setLocation(20, 160);
		confirmar.setBackground(UtilitarioTela.getColorCrud(ParametroCrud.getModoCrudNovo()));
		confirmar.setFocusPainted(false);
		confirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(confirmar()){
					dialog.setVisible(false);
				}
			}
		});
		
		JButton cancelar = new JButton("Cancelar");
		cancelar.setSize(150,30);
		cancelar.setLocation(223, 160);
		cancelar.setBackground(UtilitarioTela.getFundoLocalizar());
		cancelar.setFocusPainted(false);
		cancelar.setIcon(new ImageIcon(Menssage.class.getResource("/imagem/cancelBlack.png")));
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelar();
				dialog.setVisible(false);
			}
		});
		
		meio.add(confirmar);
		meio.add(cancelar);
		
		
		if(partidaSelecionado.getHoraInicio() != null){
			txtPlacar.setEditable(true);
			txtPlacar2.setEditable(true);
		} else {
			txtPlacar.setEditable(false);
			txtPlacar2.setEditable(false);
		}
		
		
		dialog.getContentPane().add(panel);
		dialog.setVisible(true);
	}
	
	public void cancelar(){
		this.confirmado = false;
	}
	
	public void msgErro(String erro) {
		msg.removeAll();
		lblMsg = new JLabel(erro);
		lblMsg.setHorizontalAlignment(SwingConstants.CENTER);
		lblMsg.setBounds(0, 0, 396, 35);
		lblMsg.setFont(UtilitarioTela.getFont(14));
		lblMsg.setForeground(UtilitarioTela.getFontColorCrud());
		msg.add(lblMsg);
		msg.setBackground(UtilitarioTela.getColorErro());
		msg.repaint();
	}
	
	public boolean confirmar(){
		boolean confirmado = true;
		if (partidaSelecionado.getHoraInicio() != null) {
			if(txtPlacar.getText() == null || txtPlacar.getText().isEmpty()){
				txtPlacar.requestFocus();
				msgErro("Placar � Obrigat�rio!");
				return false;
			} else if(txtPlacar2.getText() == null || txtPlacar2.getText().isEmpty()){
				txtPlacar2.requestFocus();
				msgErro("Placar � Obrigat�rio!");
				return false;
			} else if(valorEmpatado(Integer.parseInt(txtPlacar.getText()), Integer.parseInt(txtPlacar2.getText()))){
				txtPlacar.requestFocus();
				msgErro("Placares devem serem diferentes!");
				return false;
			}
			
			MenssageConfirmacao.setMenssage("Finalizar Partida", "Confirma a Finaliza��o da partida?",ParametroCrud.getModoCrudDeletar(), meio);
			confirmado = MenssageConfirmacao.isConfirmado();
			
			if(confirmado){
				getTimeVencedor(Integer.parseInt(txtPlacar.getText()), Integer.parseInt(txtPlacar2.getText()));
				partidaSelecionado.setAtivo(false);
				partidaSelecionado.setHoraFim(new Date());
				if(partidaSelecionado.getCampeonato().getChave().getCodigoChave()==1 || partidaSelecionado.getCampeonato().getChave().getCodigoChave()==2){
					if(partidaSelecionado.getPartidaFilho() != null){
						TimePartida time = PartidaDao.getTimePartidaTimeNull(partidaSelecionado.getCampeonato().getCodigoCampeonato(), partidaSelecionado.getCodigoPartida(), "ASC");
						time.setTime(partidaSelecionado.getTimeVencedor());
						EntityManagerLocal.begin();
						EntityManagerLocal.merge(time);
						EntityManagerLocal.commit();
						EntityManagerLocal.clear();
					}
				}
				
				EntityManagerLocal.begin();
				EntityManagerLocal.merge(partidaSelecionado);
				EntityManagerLocal.commit();
				EntityManagerLocal.clear();
			}
		} else {
			
		}
		return confirmado;
	}
	
	public boolean getConfirmado(){
		return this.confirmado;
	}
	
	public boolean valorEmpatado(int valor1, int valor2){
		if(valor1 == valor2){
			return true;
		}
		return false;
	}
	public void getTimeVencedor(int valor1, int valor2){
		
		if(valor1 > valor2){
			partidaSelecionado.setPlacarTimeVencedor(valor1);
			partidaSelecionado.setPlacarTimePerdedor(valor2);
			partidaSelecionado.setTimeVencedor(time1.getTime());
		} else {
			partidaSelecionado.setPlacarTimeVencedor(valor2);
			partidaSelecionado.setPlacarTimePerdedor(valor1);
			partidaSelecionado.setTimeVencedor(time2.getTime());
		}
	}
	
	public void setTimes(){
		if(PartidaDao.getTimePartida(partidaSelecionado.getCampeonato().getCodigoCampeonato(), partidaSelecionado.getCodigoPartida(), "ASC") != null){
			time1 = PartidaDao.getTimePartida(partidaSelecionado.getCampeonato().getCodigoCampeonato(), partidaSelecionado.getCodigoPartida(), "ASC");
		}
		if(PartidaDao.getTimePartida(partidaSelecionado.getCampeonato().getCodigoCampeonato(), partidaSelecionado.getCodigoPartida(), "DESC") != null){
			time2 = PartidaDao.getTimePartida(partidaSelecionado.getCampeonato().getCodigoCampeonato(), partidaSelecionado.getCodigoPartida(), "DESC");
		}
		
	}
	
	public static void main(String [] args) {
		DialogCrudPartida dp = new DialogCrudPartida();
	}
}
