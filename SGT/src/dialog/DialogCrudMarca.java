package dialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.omg.CORBA.TCKind;

import componente.Menssage;
import componente.MenssageConfirmacao;
import dao.EntityManagerLocal;
import dao.MarcaDao;
import utilitario.BordaSombreada;
import utilitario.ParametroCrud;
import utilitario.Parametros;
import utilitario.UtilitarioTela;
import entidade.Marca;

public class DialogCrudMarca {
	
	public JTextField txNome;
	private boolean confirmado;
	private Marca marcaSelecionada;
	private JLabel lblMsg;
	private JPanel msg;
	private JPanel meio;
	
	public void crudMarca(Marca marcaSelecionada, int modoCrud, JPanel painelPai){
		this.marcaSelecionada = marcaSelecionada;
		
		JDialog dialog = new JDialog(Parametros.getPai(), true);
		dialog.setUndecorated(true);
		dialog.setLayout(null);
		dialog.setSize(400, 220);
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
			textoHeader = "Cadastrar Marca";
		} else if (modoCrud == ParametroCrud.getModoCrudAlterar()) {
			textoHeader = "Alterar Marca";
		} else if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
			textoHeader = "Deletar Marca";
		} 
		
		JLabel lbHeader = new JLabel(textoHeader);
		lbHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lbHeader.setBounds(0, 0, 396, 30);
		lbHeader.setFont(UtilitarioTela.getFont(14));
		lbHeader.setForeground(UtilitarioTela.getFontColorCrud());
		lbHeader.setBorder(new BordaSombreada(false, true, false, false));
		header.add(lbHeader);
		
		meio = new JPanel();
		meio.setSize(396, 188);
		meio.setLayout(null);
		meio.setLocation(2, 30);
		meio.setBackground(new Color(232, 234, 239));
		panel.add(meio);
		
		JLabel lbNome = new JLabel("Nome :");
		lbNome.setBounds(20, 30, 50, 20);
		lbNome.setFont(UtilitarioTela.getFont(14));
		lbNome.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbNome);
		
		txNome = new JTextField();
		txNome.setColumns(100);
		txNome.setBounds(80, 30, 270, 25);
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
		confirmar.setLocation(20, 130);
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
		cancelar.setLocation(223, 130);
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
		
		if(marcaSelecionada != null){
			txNome.setText(this.marcaSelecionada.getDescricao());
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
		if(txNome.getText() == null || txNome.getText().isEmpty()){
			txNome.requestFocus();
			msgErro("Campo Nome � Obrigat�rio!");
			return false;
		} else if(MarcaDao.verificaMarca(txNome.getText())){
			txNome.requestFocus();
			msgErro("Marca J� Cadastrada!");
			return false;
		}
		
		if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
			MenssageConfirmacao.setMenssage("Deletar Marca", "Confirma a delecao da marca?\nTodos os vinculos iram ser perdidos\nMarca: "+this.marcaSelecionada.getDescricao(),ParametroCrud.getModoCrudDeletar(), meio);
			confirmado = MenssageConfirmacao.isConfirmado();
		}
		
		this.confirmado = confirmado;
		if(confirmado){
			EntityManagerLocal.begin();
			if (modoCrud == ParametroCrud.getModoCrudNovo()) {
				Marca marca= new Marca();
				marca.setDescricao(txNome.getText());
				marca.setAtivo(true);
				EntityManagerLocal.persist(marca);
			} else if(modoCrud == ParametroCrud.getModoCrudAlterar()){
				marcaSelecionada.setDescricao(txNome.getText());
				EntityManagerLocal.merge(marcaSelecionada);
			} else if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
				marcaSelecionada.setAtivo(true);
				MarcaDao.desativarPerifericos(marcaSelecionada.getCodigoMarca());
				EntityManagerLocal.merge(marcaSelecionada);
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
	
	
	
}
