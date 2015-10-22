package dialog;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
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

import localizar.LocalizarPc;

import org.omg.CORBA.TCKind;

import componente.ComboBox;
import componente.Menssage;
import componente.MenssageConfirmacao;
import dao.CampeonatoDao;
import dao.EntityManagerLocal;
import dao.MarcaDao;
import dao.PartidaDao;
import dao.PcDao;
import dao.PerifericoDao;
import tela.HomeFuncionario;
import utilitario.BordaSombreada;
import utilitario.Computador;
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
	private JPanel meioTime1;
	private JPanel meioTime2;
	private TimePartida time1;
	private TimePartida time2;
	private List<Container> pcs1;
	private List<Container> pcs2;
	
	private JPanel jpPc1Time1;
	private JPanel jpPc2Time1;
	private JPanel jpPc3Time1;
	private JPanel jpPc4Time1;
	private JPanel jpPc5Time1;
	
	private JPanel jpPc1Time2;
	private JPanel jpPc2Time2;
	private JPanel jpPc3Time2;
	private JPanel jpPc4Time2;
	private JPanel jpPc5Time2;
	
	public DialogCrudPartida() {
		JFrame jf = new JFrame();
		jf.setSize(UtilitarioTela.getTamanhoMunitor());
		if(UtilitarioTela.getTamanhoMunitor().getWidth() <= 1100){
			jf.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		}
		Parametros.setPai(jf);
		crudPartida(PartidaDao.getPartida(86), jf);

	}
	
	public void crudPartida(Partida partidaSelecionado, JFrame jf){
		this.partidaSelecionado = partidaSelecionado;
		pcs1 = new ArrayList<Container>();
		pcs2 = new ArrayList<Container>();
		setTimes();
		
		
		jf.setUndecorated(true);
		jf.getContentPane().setLayout(null);
		jf.setSize(Parametros.getPai().getSize());
		jf.getContentPane().setBackground(new Color(232, 234, 239));
		jf.setLocationRelativeTo(Parametros.getPai());
		
		JPanel panel = new JPanel();
		panel.setBorder(new BordaSombreada(new Color(46, 49, 56), new Color(102, 102, 102)));
		panel.setLayout(null);
		panel.setSize(jf.getSize());
		panel.setBackground(null);
		panel.setLocation(0, 0);
		panel.setFocusable(true);
		panel.requestFocusInWindow();
		
		JPanel header = new JPanel();
		header.setSize(jf.getWidth()-4, 30);
		header.setLayout(null);
		header.setLocation(2, 2);
		header.setBackground(UtilitarioTela.getColorCrud(ParametroCrud.getModoVisualizar()));
		header.setBorder(null);
		panel.add(header);
		
		String textoHeader = "";
		if (partidaSelecionado.getHoraInicio() == null) {
			textoHeader = "Partida não Iniciada!";
		} else {
			textoHeader = "Partida Já Iniciada!";
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
				jf.dispose();
			}
		});
		header.add(btFechar);
		
		meio = new JPanel();
		meio.setSize(panel.getSize().width-4, panel.getSize().height-32);
		meio.setLayout(null);
		meio.setLocation(2, 30);
		meio.setBackground(new Color(232, 234, 239));
		panel.add(meio);
		
		meioTime1 = new JPanel();
		meioTime1.setSize((meio.getWidth()/2) - 5, meio.getSize().height-82);
		meioTime1.setLayout(null);
		meioTime1.setLocation(2, 80);
		meioTime1.setBackground(UtilitarioTela.getBtnFundo(false));
		meioTime1.setBorder(new BordaSombreada(new Color(46, 49, 56), new Color(102, 102, 102)));
		meio.add(meioTime1);
		
		meioTime2 = new JPanel();
		meioTime2.setSize((meio.getWidth()/2) - 2, meio.getSize().height-82);
		meioTime2.setLayout(null);
		meioTime2.setLocation((meio.getWidth()/2), 80);
		meioTime2.setBackground(UtilitarioTela.getBtnFundo(false));
		meioTime2.setBorder(new BordaSombreada(new Color(46, 49, 56), new Color(102, 102, 102)));
		meio.add(meioTime2);
		
		JLabel lbNome = new JLabel("Time 1 :");
		lbNome.setBounds(70, 10, 300, 50);
		lbNome.setFont(UtilitarioTela.getFont(14));
		lbNome.setForeground(UtilitarioTela.getFontColorPadrao());
		meioTime1.add(lbNome);
		
		txtPlacar = new JTextField();
		txtPlacar.setColumns(100);
		txtPlacar.setBounds(10, 10, 50, 50);
		txtPlacar.setFont(UtilitarioTela.getFont(30));
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
		meioTime1.add(txtPlacar);
		
		jpPc1Time1 = new JPanel();
		jpPc1Time1.setSize((meioTime1.getWidth()) - 50, 50);
		jpPc1Time1.setLayout(null);
		jpPc1Time1.setLocation(10 , 100);
		jpPc1Time1.setBackground(UtilitarioTela.getBtnFundo(false));
		jpPc1Time1.setBorder(null);
		meioTime1.add(jpPc1Time1);
		pcs1.add(jpPc1Time1);
		
		jpPc2Time1 = new JPanel();
		jpPc2Time1.setSize((meioTime1.getWidth()) - 50, 50);
		jpPc2Time1.setLayout(null);
		jpPc2Time1.setLocation(10 , 160);
		jpPc2Time1.setBackground(UtilitarioTela.getBtnFundo(false));
		jpPc2Time1.setBorder(null);
		meioTime1.add(jpPc2Time1);
		pcs1.add(jpPc2Time1);
		
		jpPc3Time1 = new JPanel();
		jpPc3Time1.setSize((meioTime1.getWidth()) - 50, 50);
		jpPc3Time1.setLayout(null);
		jpPc3Time1.setLocation(10 , 220);
		jpPc3Time1.setBackground(UtilitarioTela.getBtnFundo(false));
		jpPc3Time1.setBorder(null);
		meioTime1.add(jpPc3Time1);
		pcs1.add(jpPc3Time1);
		
		jpPc4Time1 = new JPanel();
		jpPc4Time1.setSize((meioTime1.getWidth()) - 50, 50);
		jpPc4Time1.setLayout(null);
		jpPc4Time1.setLocation(10 , 280);
		jpPc4Time1.setBackground(UtilitarioTela.getBtnFundo(false));
		jpPc4Time1.setBorder(null);
		meioTime1.add(jpPc4Time1);
		pcs1.add(jpPc4Time1);
		
		jpPc5Time1 = new JPanel();
		jpPc5Time1.setSize((meioTime1.getWidth()) - 50, 50);
		jpPc5Time1.setLayout(null);
		jpPc5Time1.setLocation(10 , 340);
		jpPc5Time1.setBackground(UtilitarioTela.getBtnFundo(false));
		jpPc5Time1.setBorder(null);
		meioTime1.add(jpPc5Time1);
		pcs1.add(jpPc5Time1);
		
		JLabel lbNome2 = new JLabel("Time 2 :");
		lbNome2.setBounds(70, 10, 300, 50);
		lbNome2.setFont(UtilitarioTela.getFont(14));
		lbNome2.setForeground(UtilitarioTela.getFontColorPadrao());
		meioTime2.add(lbNome2);
		
		txtPlacar2 = new JTextField();
		txtPlacar2.setColumns(100);
		txtPlacar2.setBounds(10 , 10, 50, 50);
		txtPlacar2.setFont(UtilitarioTela.getFont(30));
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
		meioTime2.add(txtPlacar2);
		
		jpPc1Time2 = new JPanel();
		jpPc1Time2.setSize((meioTime2.getWidth()) - 50, 50);
		jpPc1Time2.setLayout(null);
		jpPc1Time2.setLocation(10 , 100);
		jpPc1Time2.setBackground(UtilitarioTela.getBtnFundo(false));
		jpPc1Time2.setBorder(null);
		meioTime2.add(jpPc1Time2);
		pcs2.add(jpPc1Time2);
		
		jpPc2Time2 = new JPanel();
		jpPc2Time2.setSize((meioTime2.getWidth()) - 50, 50);
		jpPc2Time2.setLayout(null);
		jpPc2Time2.setLocation(10 , 160);
		jpPc2Time2.setBackground(UtilitarioTela.getBtnFundo(false));
		jpPc2Time2.setBorder(null);
		meioTime2.add(jpPc2Time2);
		pcs2.add(jpPc2Time2);
		
		jpPc3Time2 = new JPanel();
		jpPc3Time2.setSize((meioTime2.getWidth()) - 50, 50);
		jpPc3Time2.setLayout(null);
		jpPc3Time2.setLocation(10 , 220);
		jpPc3Time2.setBackground(UtilitarioTela.getBtnFundo(false));
		jpPc3Time2.setBorder(null);
		meioTime2.add(jpPc3Time2);
		pcs2.add(jpPc3Time2);
		
		jpPc4Time2 = new JPanel();
		jpPc4Time2.setSize((meioTime2.getWidth()) - 50, 50);
		jpPc4Time2.setLayout(null);
		jpPc4Time2.setLocation(10 , 280);
		jpPc4Time2.setBackground(UtilitarioTela.getBtnFundo(false));
		jpPc4Time2.setBorder(null);
		meioTime2.add(jpPc4Time2);
		pcs2.add(jpPc4Time2);
		
		jpPc5Time2 = new JPanel();
		jpPc5Time2.setSize((meioTime2.getWidth()) - 50, 50);
		jpPc5Time2.setLayout(null);
		jpPc5Time2.setLocation(10 , 340);
		jpPc5Time2.setBackground(UtilitarioTela.getBtnFundo(false));
		jpPc5Time2.setBorder(null);
		meioTime2.add(jpPc5Time2);
		pcs2.add(jpPc5Time2);
		
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
		confirmar.setLocation(20, 10);
		confirmar.setBackground(UtilitarioTela.getColorCrud(ParametroCrud.getModoCrudNovo()));
		confirmar.setFocusPainted(false);
		confirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(confirmar()){
					jf.setVisible(false);
				}
			}
		});
		
		JButton btPcPartida = new JButton("PC Partida");
		btPcPartida.setSize(150,30);
		btPcPartida.setLocation(223, 10);
		btPcPartida.setBackground(UtilitarioTela.getFundoLocalizar());
		btPcPartida.setFocusPainted(false);
		btPcPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DialogPcPartida lp = new DialogPcPartida();
				lp.localizarPc(jf.getContentPane(), partidaSelecionado);
			}
		});
		
		meio.add(confirmar);
		meio.add(btPcPartida);
		
		
		if(partidaSelecionado.getHoraInicio() != null){
			txtPlacar.setEditable(true);
			txtPlacar2.setEditable(true);
		} else {
			txtPlacar.setEditable(false);
			txtPlacar2.setEditable(false);
		}
		
		atualizarPc();
		jf.getContentPane().add(panel);
		jf.setVisible(true);
		if(PcDao.getListaPcPartida(partidaSelecionado.getCodigoPartida())==null || PcDao.getListaPcPartida(partidaSelecionado.getCodigoPartida()).size() < 10){
			Menssage.setMenssage("Numero Pc's",
					"Deve Adicionar Mais PC's Para Iniciar Partida!",
					ParametroCrud.getModoCrudDeletar(), meio);
			DialogPcPartida lp = new DialogPcPartida();
			lp.localizarPc(jf.getContentPane(), partidaSelecionado);
		}
	}
	
	public void cancelar(){
		this.confirmado = false;
	}
	
	public void atualizarPc(){
		Computador.t2 = new Thread() {
			@Override
			public void run() {
				try {
					while (true) {
						meio.repaint();
						Computador.getJogadorConectado(partidaSelecionado, time1.getTime(), pcs1);
						meio.repaint();
						Computador.getJogadorConectado(partidaSelecionado, time2.getTime(), pcs2);
						sleep(1000);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		Computador.t2.start();
	}
	
	public void atualizarJogadorLogado(){
		
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
				msgErro("Placar é Obrigatório!");
				return false;
			} else if(txtPlacar2.getText() == null || txtPlacar2.getText().isEmpty()){
				txtPlacar2.requestFocus();
				msgErro("Placar é Obrigatório!");
				return false;
			} else if(valorEmpatado(Integer.parseInt(txtPlacar.getText()), Integer.parseInt(txtPlacar2.getText()))){
				txtPlacar.requestFocus();
				msgErro("Placares devem serem diferentes!");
				return false;
			}
			
			MenssageConfirmacao.setMenssage("Finalizar Partida", "Confirma a Finalização da partida?",ParametroCrud.getModoCrudDeletar(), meio);
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
