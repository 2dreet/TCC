package dialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.omg.CORBA.TCKind;

import componente.ComboBox;
import componente.Menssage;
import componente.MenssageConfirmacao;
import dao.EntityManagerLocal;
import dao.MarcaDao;
import dao.PerifericoDao;
import utilitario.BordaSombreada;
import utilitario.ParametroCrud;
import utilitario.Parametros;
import utilitario.UtilitarioLogo;
import utilitario.UtilitarioTela;
import entidade.Driver;
import entidade.Marca;
import entidade.Periferico;

public class DialogCrudDriver {
	
	public JTextField txNome;
	private boolean confirmado;
	private Driver driverSelecionado;
	private ComboBox cbPeriferico;
	private ComboBox cbMarca;
	private JLabel lblMsg;
	private JPanel msg;
	private JPanel meio;
	
	public void crudDriver(Driver driverSelecionado, int modoCrud, JPanel painelPai){
		this.driverSelecionado = driverSelecionado;
		
		JDialog dialog = new JDialog(Parametros.getPai(), true);
		dialog.setUndecorated(true);
		dialog.setLayout(null);
		dialog.setSize(400, 320);
		dialog.getContentPane().setBackground(new Color(232, 234, 239));
		dialog.setLocationRelativeTo(painelPai);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BordaSombreada(new Color(46, 49, 56), new Color(102, 102, 102)));
		panel.setLayout(null);
		panel.setSize(dialog.getSize());
		panel.setBackground(null);
		panel.setLocation(0, 0);
		panel.setFocusable(true);
		panel.requestFocusInWindow();
		
		JPanel header = new JPanel();
		header.setSize(396, 30);
		header.setLayout(null);
		header.setLocation(2, 2);
		header.setBackground(UtilitarioTela.getColorCrud(modoCrud));
		header.setBorder(null);
		panel.add(header);
		
		String textoHeader = "";
		if (modoCrud == ParametroCrud.getModoCrudNovo()) {
			textoHeader = "Cadastrar Driver";
		} else if (modoCrud == ParametroCrud.getModoCrudAlterar()) {
			textoHeader = "Alterar Driver";
		} else if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
			textoHeader = "Deletar Driver";
		} 
		
		JLabel lbHeader = new JLabel(textoHeader);
		lbHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lbHeader.setBounds(0, 0, 396, 30);
		lbHeader.setFont(UtilitarioTela.getFont(14));
		lbHeader.setForeground(UtilitarioTela.getFontColorCrud());
		lbHeader.setBorder(new BordaSombreada(false, true, false, false));
		header.add(lbHeader);
		
		meio = new JPanel();
		meio.setSize(396, 220);
		meio.setLayout(null);
		meio.setLocation(2, 30);
//		meio.setBackground(new Color(232, 234, 239));
		meio.setBackground(Color.red);
		panel.add(meio);
		
		JLabel lbNome = new JLabel("Nome :");
		lbNome.setBounds(20, 10, 50, 20);
		lbNome.setFont(UtilitarioTela.getFont(14));
		lbNome.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbNome);
		
		txNome = new JTextField();
		txNome.setColumns(100);
		txNome.setBounds(110, 10, 270, 25);
		txNome.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txNome.setBorder(UtilitarioTela.jTextFieldComFocus());
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				txNome.setBorder(UtilitarioTela.jTextFieldNormal());
			}
		});
		txNome.setLayout(null);
		txNome.setBorder(UtilitarioTela.jTextFieldNormal());
		meio.add(txNome);
		
		JLabel lbPeriferico = new JLabel("Periférico :");
		lbPeriferico.setBounds(20, 45, 90, 20);
		lbPeriferico.setFont(UtilitarioTela.getFont(14));
		lbPeriferico.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbPeriferico);
		
		cbPeriferico = new ComboBox(new Dimension(150, 25));
		cbPeriferico.setModel(new DefaultComboBoxModel(PerifericoDao.getVetorPeriferico()));
		cbPeriferico.setLocation(110, 45);
		meio.add(cbPeriferico);
		
		JLabel lbMarca = new JLabel("Marca :");
		lbMarca.setBounds(20, 80, 80, 20);
		lbMarca.setFont(UtilitarioTela.getFont(14));
		lbMarca.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbMarca);
		
		cbMarca = new ComboBox(new Dimension(150, 25));
		cbMarca.setModel(new DefaultComboBoxModel(MarcaDao.getVetorMarca()));
		cbMarca.setLocation(110, 80);
		meio.add(cbMarca);
		
		msg = new JPanel();
		msg.setSize(396, 35);
		msg.setLocation(0, 70);
		msg.setLayout(null);
		msg.setBackground(null);
		meio.add(msg);
		
		String texto = "";
		 if (modoCrud != ParametroCrud.getModoCrudDeletar()) {
			texto = "Salvar";
		}else{
			texto = "Deletar";
		} 
		
		
		JButton confirmar = new JButton(texto);
		confirmar.setSize(150,30);
		confirmar.setLocation(20, 160);
		confirmar.setBackground(UtilitarioTela.getColorCrud(ParametroCrud.getModoCrudNovo()));
		confirmar.setFocusPainted(false);
		 if (modoCrud != ParametroCrud.getModoCrudDeletar()) {
			 confirmar.setIcon(new ImageIcon(Menssage.class.getResource("/imagem/salvar.png")));
		 }else{
			 confirmar.setIcon(new ImageIcon(Menssage.class.getResource("/imagem/delete.png")));
		 }
		confirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(confirmar(modoCrud)){
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
		
		
		if(modoCrud == ParametroCrud.getModoCrudDeletar()){
			txNome.setEditable(false);
		}
		
		if(driverSelecionado != null){
			txNome.setText(this.driverSelecionado.getDescricao());
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
	}
	
	public boolean confirmar(int modoCrud){
		boolean confirmado = true;
		if (modoCrud != ParametroCrud.getModoCrudDeletar()) {
			if(txNome.getText() == null || txNome.getText().isEmpty()){
				txNome.requestFocus();
				msgErro("Campo Nome é Obrigatório!");
				return false;
			} else if(MarcaDao.verificaMarca(txNome.getText())){
				txNome.requestFocus();
				msgErro("Marca Já Cadastrada!");
				return false;
			} 
			
		}
		if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
			MenssageConfirmacao.setMenssage("Deletar Driver", "Confirma a delecao do Driver?\nDriver: "+this.driverSelecionado.getDescricao(),ParametroCrud.getModoCrudDeletar(), meio);
			confirmado = MenssageConfirmacao.isConfirmado();
		}
		
		this.confirmado = confirmado;
		if(confirmado){
			EntityManagerLocal.begin();
			if (modoCrud == ParametroCrud.getModoCrudNovo()) {
				Driver driver= new Driver();
				driver.setDescricao(txNome.getText());
				driver.setAtivo(true);
				EntityManagerLocal.persist(driver);
			} else if(modoCrud == ParametroCrud.getModoCrudAlterar()){
				driverSelecionado.setDescricao(txNome.getText());
				EntityManagerLocal.merge(driverSelecionado);
			} else if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
				driverSelecionado.setAtivo(false);
				EntityManagerLocal.merge(driverSelecionado);
			}
			EntityManagerLocal.commit();
		}
		return confirmado;
	}
	
	public boolean getConfirmado(){
		return this.confirmado;
	}
	
	public JTextField getTxNome() {
		return txNome;
	}
	
	public void localizarLogo(){
		try{
			JFileChooser chooser = new JFileChooser();    
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagens Png", "png");   
		    chooser.setFileFilter(filter);    
		    int returnVal = chooser.showOpenDialog(meio);   
		    if(returnVal == JFileChooser.APPROVE_OPTION) {    
		    	String extencao = chooser.getSelectedFile().getName();
		    	if(extencao.length() > 0 && UtilitarioLogo.validarLogo(extencao)){
		    		
		    	}else{
		    		Menssage.setMenssage("Imagem Inválida", "Arquivo selecionado deve ser uma \nImagem do tipo '.PNG, .GIF, .JPG ou .JPEG'", ParametroCrud.getModoCrudDeletar(), meio);
		    		
		    	}
		           
		    }  
		}catch(Exception e){
			
		}
	}
	
}
