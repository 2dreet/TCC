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
import utilitario.BordaEscura;
import utilitario.BordaSombreada;
import utilitario.MascaraCrud;
import utilitario.MoverArquivo;
import utilitario.ParametroCrud;
import utilitario.Parametros;
import utilitario.UtilitarioCrud;
import utilitario.UtilitarioLogo;
import utilitario.UtilitarioTabela;
import utilitario.UtilitarioTela;
import utilitario.ValidadorCrud;

import javax.swing.JButton;

import dao.EntityManagerLocal;
import dao.JogadorDao;
import dao.PermissaoDao;
import dao.TimeDao;
import entidade.Jogador;
import entidade.Time;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
	private JTable tabela;
	private List<Jogador> listaJogador;
	private Object[][] colunas = new Object[][] { new String[] { "C�digo" },
			new String[] { "Nome" }, new String[] { "Usu�rio" },
			new String[] { "RG" }, new String[] { "Telefone" },
			new String[] { "Email" } };
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
		header.setSize(650, 30);
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
		meio.setSize(650, getHeight() - 50);
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
			    	}else{
			    		logo = null;
			    		Menssage.setMenssage("Imagem Inv�lida", "Imagem selecionada deve ter o tamanho 50 X 50", ParametroCrud.getModoCrudDeletar());
			    		limparLogo();
			    	}
		    	}else{
		    		Menssage.setMenssage("Imagem Inv�lida", "Arquivo selecionado deve ser uma \nImagem do tipo '.PNG, .GIF, .JPG ou .JPEG'", ParametroCrud.getModoCrudDeletar());
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
		txDescricao.setText(timeSelecionado.getDescricao());
		if(timeSelecionado.getLogo() !=null ){
			logo = new File("logo/" + timeSelecionado.getLogo());
			pLogo.removeAll();
			txLogo.setText(logo.getAbsolutePath());
			JLabel lbLogo = new JLabel(new ImageIcon("logo/"+timeSelecionado.getLogo()));
			lbLogo.setBounds(2, 2, 50, 50);
			pLogo.add(lbLogo);
			pLogo.repaint();
		}else{
			logo = null;
			limparLogo();
		}
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
				msgErro("Campo Nome � Obrigat�rio!");
				return false;
			} else if(TimeDao.timeCadastrado(txDescricao.getText()) && !timeSelecionado.getDescricao().equals(txDescricao.getText())){
				txDescricao.requestFocus();
				msgErro("Nome J� est� sendo Usado\nInformar outro nome!");
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

		JLabel lbLogoV = new JLabel(new ImageIcon("logo/" + timeSelecionado.getLogo()));
		lbLogoV.setFont(UtilitarioTela.getFont(14));
		lbLogoV.setForeground(UtilitarioTela.getFontColorCrud());
		lbLogoV.setBounds(2, 2, 50, 50);	
		JPanel pnLogo = new JPanel();
		pnLogo.setBounds(20, linha, 54, 54);
		pnLogo.setLayout(null);
		pnLogo.setBackground(null);
		pnLogo.setBorder(new BordaSombreada());
		pnLogo.add(lbLogoV);
		meio.add(pnLogo);

		JLabel lbCodigo = new JLabel("C�digo :");
		lbCodigo.setBounds(90, linha, 80, 20);
		lbCodigo.setFont(UtilitarioTela.getFont(14));
		lbCodigo.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbCodigo);
		JLabel lbCodigoV = new JLabel(String.valueOf(timeSelecionado
				.getCodigoTime()));
		lbCodigoV.setBounds(180, linha, 200, 20);
		lbCodigoV.setFont(UtilitarioTela.getFont(14));
		lbCodigoV.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbCodigoV);
		
		linha += 35;
		JLabel lbDescricao = new JLabel("Descri��o :");
		lbDescricao.setBounds(90, linha, 80, 20);
		lbDescricao.setFont(UtilitarioTela.getFont(14));
		lbDescricao.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbDescricao);
		JLabel lbDescricaoV = new JLabel(timeSelecionado.getDescricao());
		lbDescricaoV.setBounds(180, linha, 200, 20);
		lbDescricaoV.setFont(UtilitarioTela.getFont(14));
		lbDescricaoV.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbDescricaoV);

		linha += 35;
		linha += 35;
		tabela = new JTable();
		tabela.setModel(UtilitarioTabela.getModelo(colunas));
		TableColumnModel tcm = tabela.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(60);
		tcm.getColumn(0).setMinWidth(60);
		tcm.getColumn(0).setResizable(false);
		tcm.getColumn(1).setPreferredWidth(170);
		tcm.getColumn(1).setMinWidth(170);
		tcm.getColumn(1).setResizable(false);
		tcm.getColumn(2).setPreferredWidth(100);
		tcm.getColumn(2).setMinWidth(100);
		tcm.getColumn(2).setResizable(false);
		tcm.getColumn(3).setPreferredWidth(83);
		tcm.getColumn(3).setMinWidth(83);
		tcm.getColumn(3).setResizable(false);
		tcm.getColumn(4).setPreferredWidth(100);
		tcm.getColumn(4).setMinWidth(100);
		tcm.getColumn(4).setResizable(false);
		tcm.getColumn(5).setPreferredWidth(130);
		tcm.getColumn(5).setMinWidth(130);
		tcm.getColumn(5).setResizable(false);
		
		UtilitarioTabela.pintarColona(UtilitarioTabela.getFundoHeaderPadrao() ,UtilitarioTabela.getFontColotHeaderPadrao()
				, tcm, colunas);
		UtilitarioTabela.pintarLinha( new Color(255, 153, 153), Color.black, tabela);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.setPreferredScrollableViewportSize(tabela.getPreferredSize());
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.setRowHeight(50);
		tabela.setFont(UtilitarioTela.getFont(14));
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(2, linha, 646, 250);
		meio.add(scroll);
		
	}

	public void atualizarTabela(){
		listaJogador = JogadorDao.getListaJogadorTime(timeSelecionado.getCodigoTime());
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.setNumRows(0);
		if (listaJogador != null) {
			for (Jogador j : listaJogador) {
				modelo.addRow(new String[] {
						String.valueOf(j.getCodigoJogador()),
						j.getUsuario().getNome() + " "
								+ j.getUsuario().getSobreNome(),
						j.getUsuario().getUsuario(),
						j.getUsuario().getRg(),
						MascaraCrud.mascaraTelefoneResult(j.getUsuario()
								.getTelefone()), j.getUsuario().getEmail() });

			}
		} else {
			listaJogador = new ArrayList<Jogador>();
		}
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
				menssage = "Time Cadastrado com Sucesso!";
				Time time = new Time();
				if(logo != null){
					try{
						MoverArquivo.copyFile( logo, MoverArquivo.getLocalLogo(logo));
						logo = new File("logo/" + logo.getName());
						logo.renameTo(new File("logo/" + txDescricao.getText()));
						time.setLogo(logo.getName());
					}catch(Exception e){
						e.printStackTrace();
						Menssage.setMenssage("Erro ao mover Logo", "N�o foi possivel mover a logo!", ParametroCrud.getModoErro());
					}
				}
				time.setDescricao(txDescricao.getText());
				time.setDataCadastro(new Date());
				time.setAtivo(true);
				EntityManagerLocal.persist(time);
				timeSelecionado = time;
				
			} else if (modoCrud == ParametroCrud.getModoCrudAlterar()) {
				modo = "Altera��o de Time";
				menssage = "Time Alterado com Sucesso!";
				if(logo != null){
					try{
						logo.renameTo(new File(logo.getPath().replace(logo.getName(), "")+txDescricao.getText()+".png"));
						logo = new File(logo.getPath().replace(logo.getName(), "")+txDescricao.getText()+".png");
						MoverArquivo.copyFile( logo, MoverArquivo.getLocalLogo(logo));
						timeSelecionado.setLogo(logo.getName());
					}catch(Exception e){
						e.printStackTrace();
						Menssage.setMenssage("Erro ao mover Logo", "N�o foi possivel mover a logo!", ParametroCrud.getModoErro());
					}
				}

				timeSelecionado.setDescricao(txDescricao.getText());
				EntityManagerLocal.merge(timeSelecionado);
				
			} else if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
				modo = "Dele��o de Time";
				menssage = "Time Deletado com Sucesso!";
				timeSelecionado.setAtivo(false);
				EntityManagerLocal.merge(timeSelecionado);
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
