package crud;

import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JPanel;

import utilitario.BordaEscura;
import utilitario.BordaSombreada;
import utilitario.MascaraCrud;
import utilitario.ParametroCrud;
import utilitario.UtilitarioCrud;
import utilitario.UtilitarioTela;
import utilitario.ValidadorCrud;

import javax.swing.JButton;

import entidade.Jogador;
import entidade.Usuario;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

public class CrudJogador extends JPanel {
	private JTextField txNome;
	private JTextField txSobreNome;
	private JTextField txRg;
	private JTextField txDataNascimento;
	private JTextField txTelefone;
	private JTextField txEmail;
	private JTextField txUsuario;
	private JLabel lblMsg;
	private JPanel msg;

	/**
	 * Create the panel.
	 */
	public CrudJogador(Jogador jogador, int modoCrud) {
		setSize(UtilitarioTela.getTamanhoMeio());
		setLayout(null);
		setBackground(null);
		
		JPanel header = new JPanel();
		header.setSize(500, 30);
		header.setLocation((getWidth()/2)-250, 10);
		header.setLayout(null);
		header.setBackground(UtilitarioTela.getColorCrud(modoCrud));
		header.setBorder(null);
		add(header);
		
		String textoHeader = "";
		if(modoCrud == ParametroCrud.getModoCrudNovo()){
			textoHeader= "Cadastrar Jogador";
		} else if(modoCrud == ParametroCrud.getModoCrudAlterar()){
			textoHeader= "Alterar Jogador";
		} else if(modoCrud == ParametroCrud.getModoCrudDeletar()){
			textoHeader= "Deletar Jogador";
		}
		
		JLabel lblHeader = new JLabel(textoHeader);
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setBounds(0, 0, header.getWidth(), header.getHeight());
		lblHeader.setFont(UtilitarioTela.getFontCrud());
		lblHeader.setForeground(UtilitarioTela.getFontColorCrud());
		header.add(lblHeader);
		
		JPanel meio = new JPanel();
		meio.setSize(500, getHeight()-50);
		meio.setLocation((getWidth()/2)-250, 40);
		meio.setLayout(null);
		meio.setBackground(UtilitarioTela.getFundoCrud());
		meio.setBorder(new BordaSombreada(modoCrud));
		add(meio);
		
		JLabel lbNome = new JLabel("Nome :");
		lbNome.setBounds(20, 20, 50, 20);
		lbNome.setFont(UtilitarioTela.getFontCrud());
		lbNome.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbNome);
		
		txNome = new JTextField();
		txNome.setColumns(100);
		txNome.setBounds(155, 20, 320, 25);
		txNome.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txNome.setBorder(UtilitarioTela.jTextFieldComFocus());
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				txNome.setBorder(UtilitarioTela.jTextFieldNormal());
				limpaErro();
			}
		});
		txNome.setLayout(null);
		txNome.setBorder(UtilitarioTela.jTextFieldNormal());
		meio.add(txNome);
		
		JLabel lbSobrenome = new JLabel("Sobrenome :");
		lbSobrenome.setBounds(20, 55, 100, 20);
		lbSobrenome.setFont(UtilitarioTela.getFontCrud());
		lbSobrenome.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbSobrenome);
		
		txSobreNome = new JTextField();
		txSobreNome.setColumns(100);
		txSobreNome.setBounds(155, 55, 320, 25);
		txSobreNome.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txSobreNome.setBorder(UtilitarioTela.jTextFieldComFocus());
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				txSobreNome.setBorder(UtilitarioTela.jTextFieldNormal());
				limpaErro();
			}
		});
		txSobreNome.setLayout(null);
		txSobreNome.setBorder(UtilitarioTela.jTextFieldNormal());
		meio.add(txSobreNome);
		setVisible(true);
		
		JLabel lbRg = new JLabel("RG :");
		lbRg.setBounds(20, 90, 100, 20);
		lbRg.setFont(UtilitarioTela.getFontCrud());
		lbRg.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbRg);
		
		txRg = new JTextField();
		txRg.setColumns(50);
		txRg.setBounds(155, 90, 100, 25);
		txRg.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				ValidadorCrud.campoRG(arg0, txRg.getText());
			}
			
		});
		txRg.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txRg.setBorder(UtilitarioTela.jTextFieldComFocus());
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				txRg.setBorder(UtilitarioTela.jTextFieldNormal());
				limpaErro();
			}
		});
		txRg.setLayout(null);
		txRg.setBorder(UtilitarioTela.jTextFieldNormal());
		meio.add(txRg);
		setVisible(true);
		
		JLabel lbDataNascimento = new JLabel("Data Nascimento :");
		lbDataNascimento.setBounds(20, 125, 130, 20);
		lbDataNascimento.setFont(UtilitarioTela.getFontCrud());
		lbDataNascimento.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbDataNascimento);
		
		txDataNascimento = new JTextField();
		txDataNascimento.setColumns(50);
		txDataNascimento.setBounds(155, 125, 100, 25);
		txDataNascimento.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txDataNascimento.setBorder(UtilitarioTela.jTextFieldComFocus());
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				txDataNascimento.setBorder(UtilitarioTela.jTextFieldNormal());
				limpaErro();
			}
		});
		txDataNascimento.setLayout(null);
		txDataNascimento.setBorder(UtilitarioTela.jTextFieldNormal());
		txDataNascimento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				ValidadorCrud.campoData(arg0, txDataNascimento.getText());
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				txDataNascimento.setText(MascaraCrud.mascaraData(txDataNascimento.getText()));
			}
		});
		
		meio.add(txDataNascimento);
		setVisible(true);
		
		JLabel lbTelefone = new JLabel("Telefone :");
		lbTelefone.setBounds(20, 160, 100, 20);
		lbTelefone.setFont(UtilitarioTela.getFontCrud());
		lbTelefone.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbTelefone);
		
		txTelefone = new JTextField();
		txTelefone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				ValidadorCrud.campoTelefone(arg0, txTelefone.getText());
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				txTelefone.setText(MascaraCrud.mascaraTelefone(txTelefone.getText()));
			}
		});
		txTelefone.setColumns(50);
		txTelefone.setBounds(155, 160, 100, 25);
		txTelefone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txTelefone.setBorder(UtilitarioTela.jTextFieldComFocus());
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				txTelefone.setBorder(UtilitarioTela.jTextFieldNormal());
				limpaErro();
			}
		});
		txTelefone.setLayout(null);
		txTelefone.setBorder(UtilitarioTela.jTextFieldNormal());
		meio.add(txTelefone);
		setVisible(true);
		
		JLabel lbEmail = new JLabel("Email :");
		lbEmail.setBounds(20, 230, 100, 20);
		lbEmail.setFont(UtilitarioTela.getFontCrud());
		lbEmail.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbEmail);
		
		txEmail = new JTextField();
		txEmail.setColumns(100);
		txEmail.setBounds(155, 230, 320, 25);
		txEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txEmail.setBorder(UtilitarioTela.jTextFieldComFocus());
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				txEmail.setBorder(UtilitarioTela.jTextFieldNormal());
				limpaErro();
			}
		});
		txEmail.setLayout(null);
		txEmail.setBorder(UtilitarioTela.jTextFieldNormal());
		meio.add(txEmail);
		setVisible(true);
		
		JLabel lbUsuario = new JLabel("Usuário :");
		lbUsuario.setBounds(20, 265, 100, 20);
		lbUsuario.setFont(UtilitarioTela.getFontCrud());
		lbUsuario.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbUsuario);
		
		txUsuario = new JTextField();
		txUsuario.setColumns(100);
		txUsuario.setBounds(155, 265, 320, 25);
		txUsuario.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txUsuario.setBorder(UtilitarioTela.jTextFieldComFocus());
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				txUsuario.setBorder(UtilitarioTela.jTextFieldNormal());
				limpaErro();
			}
		});
		txUsuario.setLayout(null);
		txUsuario.setBorder(UtilitarioTela.jTextFieldNormal());
		meio.add(txUsuario);
		
		String texto = "";

		if(modoCrud == ParametroCrud.getModoCrudDeletar()){
			texto = "Deletar";
		} else if(modoCrud == ParametroCrud.getModoCrudAlterar()) {
			texto = "Alterar";
		} else if(modoCrud == ParametroCrud.getModoCrudNovo()) {
			texto = "Cadastrar";
		}
		
		
		JButton btSalvar = new JButton(texto);
		btSalvar.setBounds(175, meio.getHeight()-70, 150, 35);
		btSalvar.setFont(UtilitarioTela.getFontCrud());
		btSalvar.setFocusPainted(false);
		btSalvar.setBackground(UtilitarioTela.getColorCrud(modoCrud));
		btSalvar.setIcon(UtilitarioCrud.getIconeCrud(modoCrud));
		meio.add(btSalvar);
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(modoCrud != ParametroCrud.getModoCrudDeletar()){
					validarCrud();
				}
			}
		});
		
		
		msg = new JPanel();
		msg.setSize(490, 35);
		msg.setLocation(5, 300);
		msg.setLayout(null);
		msg.setBackground(null);
		meio.add(msg);
		
		JRadioButton radioButton = new JRadioButton("New radio button");
		radioButton.setSelected(true);
		radioButton.setBounds(155, 195, 109, 23);
		radioButton.setBackground(null);
		meio.add(radioButton);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		rdbtnNewRadioButton.setBounds(282, 195, 109, 23);
		meio.add(rdbtnNewRadioButton);
		
		
		if(modoCrud == ParametroCrud.getModoCrudDeletar()){
			desabilitarTodos();
			msgDelecao("Deseja Deletar Esse Jogador?");
		}
		
	}

	public void msgDelecao(String erro){
		msg.removeAll();
		lblMsg = new JLabel(erro);
		lblMsg.setHorizontalAlignment(SwingConstants.CENTER);
		lblMsg.setBounds(0, 0 , 490, 35);
		lblMsg.setFont(UtilitarioTela.getFontCrud());
		lblMsg.setForeground(UtilitarioTela.getColorErro());
		msg.add(lblMsg);
		msg.setBackground(null);
	}
	
	
	public void msgErro(String erro){
		msg.removeAll();
		lblMsg = new JLabel(erro);
		lblMsg.setHorizontalAlignment(SwingConstants.CENTER);
		lblMsg.setBounds(0, 0 , 490, 35);
		lblMsg.setFont(UtilitarioTela.getFontCrud());
		lblMsg.setForeground(UtilitarioTela.getFontColorCrud());
		msg.add(lblMsg);
		msg.setBackground(UtilitarioTela.getColorErro());
	}
	
	public void limpaErro(){
		msg.removeAll();
		lblMsg = new JLabel("");
		lblMsg.setHorizontalAlignment(SwingConstants.CENTER);
		lblMsg.setBounds(0, 0 , 490, 35);
		lblMsg.setFont(UtilitarioTela.getFontCrud());
		lblMsg.setForeground(UtilitarioTela.getFontColorCrud());
		msg.add(lblMsg);
		msg.setBackground(null);
	}
	
	private boolean validarCrud(){
		try{

			if(txNome.getText() == null || txNome.getText().trim().isEmpty()){
				txNome.requestFocus();
				msgErro("Campo Nome é Obrigatório!");
				return false;
			} 
			if(txSobreNome.getText() == null || txSobreNome.getText().trim().isEmpty()){
				txSobreNome.requestFocus();
				msgErro("Campo Sobrenome é Obrigatório!");
				return false;
			}
			
			if(txRg.getText() == null || txRg.getText().trim().isEmpty()){
				msgErro("Campo RG é Obrigatório!");
				txRg.requestFocus();
				return false;
			} else if(!ValidadorCrud.validarRg(txRg.getText())){
				msgErro("RG é Inválido!");
				txRg.requestFocus();
				return false;
			}
			
			if(txDataNascimento.getText() == null || txDataNascimento.getText().trim().isEmpty()){
				txDataNascimento.requestFocus();
				msgErro("Campo Data de Nascimento é Obrigatório!");
				return false;
			}else if(!ValidadorCrud.validarData(txDataNascimento.getText())){
				msgErro("Data de Nascimento Inválida!");
				txDataNascimento.requestFocus();
				return false;
			}
			
			if(txTelefone.getText() == null || txTelefone.getText().trim().isEmpty()){
				txTelefone.requestFocus();
				msgErro("Campo Telefone é Obrigatório!");
				return false;
			}else if(!ValidadorCrud.validarTelefone(txTelefone.getText())){
				txTelefone.requestFocus();
				msgErro("Telefone é Inválido!");
				return false;
			}
			
			if(txEmail.getText() == null || txEmail.getText().trim().isEmpty()){
				txEmail.requestFocus();
				msgErro("Campo Email é Obrigatório!");
				return false;
			} else if(!ValidadorCrud.validarEmail(txEmail.getText())){
				txEmail.requestFocus();
				msgErro("Email é Inválido!");
				return false;
			}
			
			if(txUsuario.getText() == null || txUsuario.getText().trim().isEmpty()){
				txUsuario.requestFocus();
				msgErro("Campo Usuário é Obrigatório!");
				return false;
			}else if(!ValidadorCrud.validarUsuario(txUsuario.getText())){
				txUsuario.requestFocus();
				msgErro("Usuário Já Cadastrado!");
				return false;
			}
			
			
			return true;	
		}catch(Exception e){
			return false;
		}
	}
	
	private void save(int modoCrud){
		if(modoCrud == ParametroCrud.getModoCrudNovo()){
			Usuario usuario = new Usuario();
			
		}else if(modoCrud == ParametroCrud.getModoCrudAlterar()){
			
			
		}else if(modoCrud == ParametroCrud.getModoCrudDeletar()){
			
			
		}
	}
	
	private void desabilitarTodos(){
		txNome.setEditable(false);
		txSobreNome.setEditable(false);
		txRg.setEditable(false);
		txDataNascimento.setEditable(false);
		txTelefone.setEditable(false);
		txEmail.setEditable(false);
		txUsuario.setEditable(false);
	}
	
	public JTextField getTxNome() {
		return txNome;
	}

	public void setTxNome(JTextField txNome) {
		this.txNome = txNome;
	}
}
