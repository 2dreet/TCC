package crud;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JPanel;

import utilitario.BordaEscura;
import utilitario.BordaSombreada;
import utilitario.MascaraCrud;
import utilitario.ParametroCrud;
import utilitario.UtilitarioTela;
import utilitario.ValidadorCrud;

import javax.swing.JButton;

import entidade.Jogador;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CrudJogador extends JPanel {
	private JTextField txNome;
	private JTextField txSobreNome;
	private JTextField txRg;
	private JTextField txDataNascimento;
	private JTextField txTelefone;
	private JTextField txEmail;
	private JTextField txUsuario;

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
		lblHeader.setBounds((header.getWidth()/2) - 50, 0, 200, 30);
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
				ValidadorCrud.campoInt(arg0, txRg.getText());
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
			}
		});
		txTelefone.setLayout(null);
		txTelefone.setBorder(UtilitarioTela.jTextFieldNormal());
		meio.add(txTelefone);
		setVisible(true);
		
		JLabel lbEmail = new JLabel("Email :");
		lbEmail.setBounds(20, 195, 100, 20);
		lbEmail.setFont(UtilitarioTela.getFontCrud());
		lbEmail.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbEmail);
		
		txEmail = new JTextField();
		txEmail.setColumns(100);
		txEmail.setBounds(155, 195, 320, 25);
		txEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txEmail.setBorder(UtilitarioTela.jTextFieldComFocus());
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				txEmail.setBorder(UtilitarioTela.jTextFieldNormal());
			}
		});
		txEmail.setLayout(null);
		txEmail.setBorder(UtilitarioTela.jTextFieldNormal());
		meio.add(txEmail);
		setVisible(true);
		
		JLabel lbUsuario = new JLabel("Usuário :");
		lbUsuario.setBounds(20, 230, 100, 20);
		lbUsuario.setFont(UtilitarioTela.getFontCrud());
		lbUsuario.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbUsuario);
		
		txUsuario = new JTextField();
		txUsuario.setColumns(100);
		txUsuario.setBounds(155, 230, 320, 25);
		txUsuario.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txUsuario.setBorder(UtilitarioTela.jTextFieldComFocus());
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				txUsuario.setBorder(UtilitarioTela.jTextFieldNormal());
			}
		});
		txUsuario.setLayout(null);
		txUsuario.setBorder(UtilitarioTela.jTextFieldNormal());
		meio.add(txUsuario);
		
		String texto = "";

		if(modoCrud == ParametroCrud.getModoCrudDeletar()){
			texto = "Confirmar";
		} else {
			texto = "Salvar";
		}
		
		JButton btSalvar = new JButton(texto);
		btSalvar.setBounds(50, meio.getHeight()-70, 150, 35);
		meio.add(btSalvar);
		
		JButton btCancelar = new JButton("Cancelar");
		btCancelar.setBounds(300, meio.getHeight()-70, 150, 35);
		meio.add(btCancelar);
		
		if(modoCrud == ParametroCrud.getModoCrudDeletar()){
			desabilitarTodos();
		}
		
	}

	private boolean validarCrud(){
		try{

			if(txNome.getText() == null || txNome.getText().trim().isEmpty()){
				txNome.requestFocus();
				return false;
			}
			if(txSobreNome.getText() == null || txSobreNome.getText().trim().isEmpty()){
				txSobreNome.requestFocus();
				return false;
			}
			if(txUsuario.getText() == null || txUsuario.getText().trim().isEmpty()){
				txUsuario.requestFocus();
				return false;
			}
			
			
			
			return true;	
		}catch(Exception e){
			return false;
		}
	}
	
	private void save(int modoCrud){
		if(modoCrud == ParametroCrud.getModoCrudNovo()){
			
			
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
