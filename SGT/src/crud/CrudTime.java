package crud;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JPanel;

import componente.Menssage;
import componente.MenssageConfirmacao;
import utilitario.BordaEscura;
import utilitario.BordaSombreada;
import utilitario.MascaraCrud;
import utilitario.MoverArquivo;
import utilitario.ParametroCrud;
import utilitario.Parametros;
import utilitario.UtilitarioCrud;
import utilitario.UtilitarioLogo;
import utilitario.UtilitarioTela;
import utilitario.ValidadorCrud;

import javax.swing.JButton;

import dao.EntityManagerLocal;
import dao.PermissaoDao;
import entidade.Jogador;
import entidade.Time;
import entidade.Usuario;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;

import componente.ComboBox;

import java.awt.Color;
import java.io.File;
import java.util.Date;

import javax.swing.JTextArea;

import menu.MenuJogador;
import menu.MenuTime;

public class CrudTime extends JPanel {
	private JTextField txDescricao;
	private JTextField txLogo;
	private JLabel lblMsg;
	private JPanel msg;
	private Time timeSelecionado;
	private MenuTime menuPai;
	private JPanel header;
	private JLabel lblHeader;
	private JPanel meio;
	private JPanel pLogo;
	private File logo;

	/**
	 * Create the panel.
	 */
	public CrudTime(Time time, int modoCrud, MenuTime menuPai) {
		this.menuPai = menuPai;
		logo = null;
		setSize(UtilitarioTela.getTamanhoMeio());
		setLayout(null);
		setBackground(null);

		timeSelecionado = time;

		header = new JPanel();
		header.setSize(500, 30);
		header.setLocation((getWidth() / 2) - 250, 10);
		header.setLayout(null);
		header.setBackground(UtilitarioTela.getColorCrud(modoCrud));
		header.setBorder(null);
		add(header);

		String textoHeader = "";
		if (modoCrud == ParametroCrud.getModoCrudNovo()) {
			textoHeader = "Cadastrar Time";
		} else if (modoCrud == ParametroCrud.getModoCrudAlterar()) {
			textoHeader = "Alterar Time";
		} else if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
			textoHeader = "Deletar Time";
		} else if (modoCrud == ParametroCrud.getModoVisualizar()) {
			textoHeader = "Visualizar Time";
		}

		lblHeader = new JLabel(textoHeader);
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
		meio.setBorder(new BordaSombreada(modoCrud));
		add(meio);

		JLabel lbNome = new JLabel("Nome :");
		lbNome.setBounds(20, 20, 50, 20);
		lbNome.setFont(UtilitarioTela.getFont(14));
		lbNome.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbNome);

		txDescricao = new JTextField();
		txDescricao.setColumns(100);
		txDescricao.setBounds(89, 20, 335, 25);
		txDescricao.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txDescricao.setBorder(UtilitarioTela.jTextFieldComFocus());
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				txDescricao.setBorder(UtilitarioTela.jTextFieldNormal());
				limpaErro();
			}
		});
		txDescricao.setLayout(null);
		txDescricao.setBorder(UtilitarioTela.jTextFieldNormal());
		meio.add(txDescricao);

		JLabel lbLogol = new JLabel("Logo : ");
		lbLogol.setBounds(20, 82, 80, 25);
		lbLogol.setFont(UtilitarioTela.getFont(14));
		lbLogol.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbLogol);
		
		pLogo = new JPanel();
		pLogo.setSize(54, 54);
		pLogo.setLocation(89, 55);
		pLogo.setLayout(null);
		pLogo.setBackground(null);
		pLogo.setBorder(new BordaSombreada());
		meio.add(pLogo);
		
		JLabel lbLogo = new JLabel("");
		lbLogo.setBounds(0, 0, 50, 50);
		pLogo.add(lbLogo);
		
		txLogo = new JTextField();
		txLogo.setText("");
		txLogo.setEditable(false);
		txLogo.setColumns(70);
		txLogo.setBounds(153, 82, 150, 25);
		txLogo.setLayout(null);
		txLogo.setBorder(UtilitarioTela.jTextFieldNormal());
		meio.add(txLogo);
		
		JButton btLocalizar = new JButton("Localizar");
		btLocalizar.setBounds(315, 82, 110, 25);
		btLocalizar.setFont(UtilitarioTela.getFont(12));
		btLocalizar.setFocusPainted(false);
		btLocalizar.setBackground(UtilitarioTela.getColorCrud(modoCrud));
		btLocalizar.setIcon(UtilitarioCrud.getIconeCrud(modoCrud));
		meio.add(btLocalizar);
		btLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				localizarLogo();
			}
		});
		
		
		String texto = "";
		if (modoCrud == ParametroCrud.getModoCrudAlterar()) {
			texto = "Alterar";
		} else if (modoCrud == ParametroCrud.getModoCrudNovo()) {
			texto = "Cadastrar";
		}

		if (modoCrud != ParametroCrud.getModoVisualizar()) {
			JButton btSalvar = new JButton(texto);
			btSalvar.setBounds(175, meio.getHeight() - 70, 150, 35);
			btSalvar.setFont(UtilitarioTela.getFont(14));
			btSalvar.setFocusPainted(false);
			btSalvar.setBackground(UtilitarioTela.getColorCrud(modoCrud));
			btSalvar.setIcon(UtilitarioCrud.getIconeCrud(modoCrud));
			meio.add(btSalvar);
			btSalvar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (modoCrud != ParametroCrud.getModoCrudDeletar()) {
						if (validarCrud()) {
							save(modoCrud);
						}
					}
				}
			});
		}

		msg = new JPanel();
		msg.setSize(490, 35);
		msg.setLocation(5, 120);
		msg.setLayout(null);
		msg.setBackground(null);
		meio.add(msg);

		if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
			visualizar(modoCrud);
		} else if (modoCrud == ParametroCrud.getModoVisualizar()) {
			visualizar(modoCrud);
		}

		if (timeSelecionado != null) {
			setarCampos();
		}
	}

	public void localizarLogo(){
		try{
			JFileChooser chooser = new JFileChooser();    
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagens Png", "png");   
		    chooser.setFileFilter(filter);    
		    int returnVal = chooser.showOpenDialog(this);   
		    if(returnVal == JFileChooser.APPROVE_OPTION) {    
		    	String extencao = chooser.getSelectedFile().getName();
		    	if(extencao.length() > 0 && UtilitarioLogo.validarLogo(extencao)){
		    		ImageIcon logoImg = new ImageIcon(chooser.getSelectedFile().getPath());
		    		if(logoImg.getIconHeight() == 50 && logoImg.getIconWidth() == 50){
			    		logo = chooser.getSelectedFile();
			    		pLogo.removeAll();
			    		txLogo.setText(logo.getAbsolutePath());
			    		JLabel lbLogo = new JLabel(logoImg);
			    		lbLogo.setBounds(2, 2, 50, 50);
			    		pLogo.add(lbLogo);
			    		pLogo.repaint();
			    		//MoverArquivo.copyFile(chooser.getSelectedFile(), MoverArquivo.getLocalLogo(chooser.getSelectedFile()));
			    	}else{
			    		Menssage.setMenssage("Imagem Inválida", "Imagem selecionada deve ter o tamanho 50 X 50", ParametroCrud.getModoCrudDeletar());
			    		limparLogo();
			    	}
		    	}else{
		    		Menssage.setMenssage("Imagem Inválida", "Arquivo selecionado deve ser uma \nImagem do tipo '.PNG, .GIF, .JPG ou .JPEG'", ParametroCrud.getModoCrudDeletar());
		    		logo = null;
		    		limparLogo();
		    	}
		           
		    }  
		}catch(Exception e){
			
		}
	}
	
	
	
	public void limparLogo(){
		pLogo.removeAll();
		pLogo.repaint();
	}
	
	public void setarCampos() {
		
	}

	public void msgErro(String erro) {
		msg.removeAll();
		lblMsg = new JLabel(erro);
		lblMsg.setHorizontalAlignment(SwingConstants.CENTER);
		lblMsg.setBounds(0, 0, 490, 35);
		lblMsg.setFont(UtilitarioTela.getFont(14));
		lblMsg.setForeground(UtilitarioTela.getFontColorCrud());
		msg.add(lblMsg);
		msg.setBackground(UtilitarioTela.getColorErro());
	}

	public void limpaErro() {
		msg.removeAll();
		lblMsg = new JLabel("");
		lblMsg.setHorizontalAlignment(SwingConstants.CENTER);
		lblMsg.setBounds(0, 0, 490, 35);
		lblMsg.setFont(UtilitarioTela.getFont(12));
		lblMsg.setForeground(UtilitarioTela.getFontColorCrud());
		msg.add(lblMsg);
		msg.setBackground(null);
	}

	private boolean validarCrud() {
		try {

			if (txDescricao.getText() == null || txDescricao.getText().trim().isEmpty()) {
				txDescricao.requestFocus();
				msgErro("Campo Nome é Obrigatório!");
				return false;
			}
			

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void visualizar(int modoCrud) {
		meio.removeAll();

		if (modoCrud != ParametroCrud.getModoVisualizar()) {
			JButton btSalvar = new JButton("Deletar");
			btSalvar.setBounds(175, meio.getHeight() - 70, 150, 35);
			btSalvar.setFont(UtilitarioTela.getFont(14));
			btSalvar.setFocusPainted(false);
			btSalvar.setBackground(UtilitarioTela.getColorCrud(modoCrud));
			btSalvar.setIcon(UtilitarioCrud.getIconeCrud(modoCrud));
			meio.add(btSalvar);
			btSalvar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (validarCrud()) {
						save(modoCrud);
					}
				}
			});
		}

		int linha = 20;

		JLabel lbCodigo = new JLabel("Código :");
		lbCodigo.setBounds(20, linha, 100, 20);
		lbCodigo.setFont(UtilitarioTela.getFont(14));
		lbCodigo.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbCodigo);
		JLabel lbCodigoV = new JLabel(String.valueOf(timeSelecionado
				.getCodigoTime()));
		lbCodigoV.setBounds(155, linha, 300, 20);
		lbCodigoV.setFont(UtilitarioTela.getFont(14));
		lbCodigoV.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbCodigoV);

		linha += 35;
		JLabel lbDescricao = new JLabel("Descrição :");
		lbDescricao.setBounds(20, linha, 50, 20);
		lbDescricao.setFont(UtilitarioTela.getFont(14));
		lbDescricao.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbDescricao);
		JLabel lbDescricaoV = new JLabel(timeSelecionado.getDescricao());
		lbDescricaoV.setBounds(155, linha, 300, 20);
		lbDescricaoV.setFont(UtilitarioTela.getFont(14));
		lbDescricaoV.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbDescricaoV);

		
	}

	private void save(int modoCrud) {
		boolean confirmado = true;

		if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
			MenssageConfirmacao.setMenssage("Deletar Time",
					"Deseja Deletar Esse Time?", modoCrud);
			confirmado = MenssageConfirmacao.isConfirmado();
		}

		if (confirmado) {
			EntityManagerLocal.begin();
			String modo = "";
			String menssage = "";
			if (modoCrud == ParametroCrud.getModoCrudNovo()) {
				modo = "Cadastro de Time";
				menssage = "<html>Time Cadastrado com Sucesso!</html>";
				

			} else if (modoCrud == ParametroCrud.getModoCrudAlterar()) {
				modo = "Alteração de Time";
				menssage = "<html>Time Alterado com Sucesso!</html>";
				
			} else if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
				modo = "Deleção de Time";
				menssage = "<html>Time Deletado com Sucesso!</html>";
				
			}
			EntityManagerLocal.commit();
			Menssage.setMenssage(modo, menssage, modoCrud);
			if (modoCrud == ParametroCrud.getModoCrudNovo()
					|| modoCrud == ParametroCrud.getModoCrudAlterar()) {
				menuPai.exibirTime(timeSelecionado);
			} else if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
				menuPai.home();
			}
		}
	}
	
	public JTextField getTxDescricao() {
		return txDescricao;
	}

	public void setTxDescricao(JTextField txDescricao) {
		this.txDescricao = txDescricao;
	}
}
