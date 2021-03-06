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
import utilitario.ParametroCrud;
import utilitario.Parametros;
import utilitario.UtilitarioCrud;
import utilitario.UtilitarioTela;
import utilitario.ValidadorCrud;

import javax.swing.JButton;

import dao.EntityManagerLocal;
import dao.PermissaoDao;
import dialog.DialogRedefinirSenha;
import entidade.Funcionario;
import entidade.Jogador;
import entidade.Usuario;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import componente.ComboBox;

import java.awt.Color;
import java.util.Date;

import javax.swing.JTextArea;

import menu.MenuFuncionario;
import menu.MenuJogador;

public class CrudFuncionario extends JPanel {
	private JTextField txNome;
	private JTextField txSobreNome;
	private JTextField txCpf;
	private JTextField txDataNascimento;
	private JTextField txTelefone;
	private JTextField txEmail;
	private JTextField txUsuario;
	private JLabel lblMsg;
	private JPanel msg;
	private ComboBox comboSexo;
	private ComboBox comboPermissao;
	private Funcionario funcionarioSelecionado;
	private MenuFuncionario menuPai;
	private JPanel header;
	private JLabel lblHeader;
	private JPanel meio;
	/**
	 * Create the panel.
	 */
	public CrudFuncionario(Funcionario funcionario, int modoCrud, MenuFuncionario menuPai) {
		this.menuPai = menuPai;
		setSize(UtilitarioTela.getTamanhoMeio());
		setLayout(null);
		setBackground(null);
		
		funcionarioSelecionado = funcionario;

		header = new JPanel();
		header.setSize(500, 30);
		header.setLocation((getWidth() / 2) - 250, 10);
		header.setLayout(null);
		header.setBackground(UtilitarioTela.getColorCrud(modoCrud));
		header.setBorder(null);
		add(header);

		String textoHeader = "";
		if (modoCrud == ParametroCrud.getModoCrudNovo()) {
			textoHeader = "Cadastrar Funcion�rio";
		} else if (modoCrud == ParametroCrud.getModoCrudAlterar()) {
			textoHeader = "Alterar Funcion�rio";
		} else if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
			textoHeader = "Deletar Funcion�rio";
		} else if (modoCrud == ParametroCrud.getModoVisualizar()) {
			textoHeader = "Visualizar Funcion�rio";
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
		lbSobrenome.setFont(UtilitarioTela.getFont(14));
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
		
		JLabel lbCpf = new JLabel("CPF :");
		lbCpf.setBounds(20, 90, 100, 20);
		lbCpf.setFont(UtilitarioTela.getFont(14));
		lbCpf.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbCpf);

		txCpf = new JTextField();
		txCpf.setColumns(50);
		txCpf.setBounds(155, 90, 100, 25);
		txCpf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				ValidadorCrud.campoCPF(arg0, txCpf.getText());
			}

		});
		txCpf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txCpf.setBorder(UtilitarioTela.jTextFieldComFocus());
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				txCpf.setBorder(UtilitarioTela.jTextFieldNormal());
				limpaErro();
			}
		});
		txCpf.setLayout(null);
		txCpf.setBorder(UtilitarioTela.jTextFieldNormal());
		meio.add(txCpf);
		setVisible(true);

		JLabel lbDataNascimento = new JLabel("Data Nascimento :");
		lbDataNascimento.setBounds(20, 125, 130, 20);
		lbDataNascimento.setFont(UtilitarioTela.getFont(14));
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
				txDataNascimento.setText(MascaraCrud
						.mascaraData(txDataNascimento.getText()));
			}
		});

		meio.add(txDataNascimento);
		setVisible(true);

		JLabel lbTelefone = new JLabel("Telefone :");
		lbTelefone.setBounds(20, 160, 100, 20);
		lbTelefone.setFont(UtilitarioTela.getFont(14));
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
				txTelefone.setText(MascaraCrud.mascaraTelefone(txTelefone
						.getText()));
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

		JLabel lbSexo = new JLabel("Sexo :");
		lbSexo.setBounds(20, 195, 100, 20);
		lbSexo.setFont(UtilitarioTela.getFont(14));
		lbSexo.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbSexo);

		comboSexo = new ComboBox(new Dimension(150, 25));
		comboSexo.setModel(new DefaultComboBoxModel(new String[] { "Masculino",
				"Feminino" }));
		comboSexo.setLocation(155, 195);
		meio.add(comboSexo);

		JLabel lbEmail = new JLabel("Email :");
		lbEmail.setBounds(20, 230, 100, 20);
		lbEmail.setFont(UtilitarioTela.getFont(14));
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

		JLabel lbUsuario = new JLabel("Usu�rio :");
		lbUsuario.setBounds(20, 265, 100, 20);
		lbUsuario.setFont(UtilitarioTela.getFont(14));
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
		
		JLabel lbPermissao = new JLabel("Permiss�o :");
		lbPermissao.setBounds(20, 300, 100, 20);
		lbPermissao.setFont(UtilitarioTela.getFont(14));
		lbPermissao.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbPermissao);

		comboPermissao = new ComboBox(new Dimension(150, 25));
		comboPermissao.setModel(new DefaultComboBoxModel(new String[] { "Funcion�rio",
				"Administrador" }));
		comboPermissao.setLocation(155, 300);
		meio.add(comboPermissao);

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
		msg.setLocation(5, 350);
		msg.setLayout(null);
		msg.setBackground(null);
		meio.add(msg);

		if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
			visualizar(modoCrud);
		} else if (modoCrud == ParametroCrud.getModoVisualizar()) {
			visualizar(modoCrud);
		}

		if (funcionarioSelecionado != null) {
			setarCampos();
		}
	}

	public void setarCampos() {
		txNome.setText(funcionarioSelecionado.getUsuario().getNome());
		txSobreNome.setText(funcionarioSelecionado.getUsuario().getSobreNome());
		txCpf.setText(funcionarioSelecionado.getUsuario().getCpf());
		txTelefone.setText(MascaraCrud.mascaraTelefoneResult(funcionarioSelecionado
				.getUsuario().getTelefone()));
		txEmail.setText(funcionarioSelecionado.getUsuario().getEmail());
		txUsuario.setText(funcionarioSelecionado.getUsuario().getUsuario());
		txDataNascimento.setText(MascaraCrud.macaraDataBanco(funcionarioSelecionado
				.getUsuario().getDataNascimento()));
		comboSexo.setSelectedIndex(0);
		if (funcionarioSelecionado.getUsuario().getSexo() == Parametros
				.getSexoFeminino()) {
			comboSexo.setSelectedIndex(1);
		}
		
		if (funcionarioSelecionado.getUsuario().getPermissao().getCodigoPermissao() == Parametros
				.getPermissaoFuncionario()) {
			comboPermissao.setSelectedIndex(0);
		}else{
			comboPermissao.setSelectedIndex(1);
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
			if(funcionarioSelecionado != null && funcionarioSelecionado.getCodigoFuncionario() == 1){
				msgErro("Usu�rio Admin n�o pode ser alterado!");
				return false;
			}
			if (txNome.getText() == null || txNome.getText().trim().isEmpty()) {
				txNome.requestFocus();
				msgErro("Campo Nome � Obrigat�rio!");
				return false;
			}
			if (txSobreNome.getText() == null
					|| txSobreNome.getText().trim().isEmpty()) {
				txSobreNome.requestFocus();
				msgErro("Campo Sobrenome � Obrigat�rio!");
				return false;
			}

			if (txCpf.getText() == null || txCpf.getText().trim().isEmpty()) {
				msgErro("Campo CPF � Obrigat�rio!");
				txCpf.requestFocus();
				return false;
			} else if (!ValidadorCrud.isCpf(txCpf.getText())) {
				msgErro("CPF � Inv�lido!");
				txCpf.requestFocus();
				return false;
			} else if (ValidadorCrud.cpfJaCadastrado(txCpf.getText()) && funcionarioSelecionado == null) {
				msgErro("CPF j� Cadastrado!");
				txCpf.requestFocus();
				return false;
			} else if (ValidadorCrud.cpfJaCadastrado(txCpf.getText()) && funcionarioSelecionado != null
					&& !txCpf.getText().equals(funcionarioSelecionado.getUsuario().getCpf())) {
				msgErro("CPF j� Cadastrado!");
				txCpf.requestFocus();
				return false;
			}

			if (txDataNascimento.getText() == null
					|| txDataNascimento.getText().trim().isEmpty()) {
				txDataNascimento.requestFocus();
				msgErro("Campo Data de Nascimento � Obrigat�rio!");
				return false;
			} else if (!ValidadorCrud.validarData(txDataNascimento.getText())) {
				msgErro("Data de Nascimento Inv�lida!");
				txDataNascimento.requestFocus();
				return false;
			}

			if (txTelefone.getText() == null
					|| txTelefone.getText().trim().isEmpty()) {
				txTelefone.requestFocus();
				msgErro("Campo Telefone � Obrigat�rio!");
				return false;
			} else if (!ValidadorCrud.validarTelefone(txTelefone.getText())) {
				txTelefone.requestFocus();
				msgErro("Telefone � Inv�lido!");
				return false;
			}

			if (txEmail.getText() == null || txEmail.getText().trim().isEmpty()) {
				txEmail.requestFocus();
				msgErro("Campo Email � Obrigat�rio!");
				return false;
			} else if (!ValidadorCrud.validarEmail(txEmail.getText())) {
				txEmail.requestFocus();
				msgErro("Email � Inv�lido!");
				return false;
			}

			if (txUsuario.getText() == null
					|| txUsuario.getText().trim().isEmpty()) {
				txUsuario.requestFocus();
				msgErro("Campo Usu�rio � Obrigat�rio!");
				return false;
			} else if (!ValidadorCrud.validarUsuario(txUsuario.getText())
					&& funcionarioSelecionado == null) {
				txUsuario.requestFocus();
				msgErro("Usu�rio J� Cadastrado!");
				return false;
			} else if (!ValidadorCrud.validarUsuario(txUsuario.getText())
					&& funcionarioSelecionado != null
					&& !funcionarioSelecionado.getUsuario().getUsuario()
							.equals(txUsuario.getText())) {
				txUsuario.requestFocus();
				msgErro("Usu�rio J� Cadastrado!");
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
		} else if(modoCrud == ParametroCrud.getModoVisualizar()) {
			JButton btRedefinir = new JButton("Redefinir Senha");
			btRedefinir.setBounds(100, meio.getHeight() - 70, 300, 35);
			btRedefinir.setFont(UtilitarioTela.getFont(14));
			btRedefinir.setFocusPainted(false);
			btRedefinir.setBackground(UtilitarioTela.getColorCrud(modoCrud));
			btRedefinir.setIcon(UtilitarioCrud.getIconeCrud(modoCrud));
			meio.add(btRedefinir);
			btRedefinir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					DialogRedefinirSenha rds = new DialogRedefinirSenha();
					rds.redefinir(funcionarioSelecionado.getUsuario(), meio);
				}
			});
		}

		int linha = 20;

		JLabel lbCodigo = new JLabel("C�digo :");
		lbCodigo.setBounds(20, linha, 100, 20);
		lbCodigo.setFont(UtilitarioTela.getFont(14));
		lbCodigo.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbCodigo);
		JLabel lbCodigoV = new JLabel(String.valueOf(funcionarioSelecionado
				.getCodigoFuncionario()));
		lbCodigoV.setBounds(155, linha, 300, 20);
		lbCodigoV.setFont(UtilitarioTela.getFont(14));
		lbCodigoV.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbCodigoV);

		linha += 35;
		JLabel lbNome = new JLabel("Nome :");
		lbNome.setBounds(20, linha, 50, 20);
		lbNome.setFont(UtilitarioTela.getFont(14));
		lbNome.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbNome);
		JLabel lbNomeV = new JLabel(funcionarioSelecionado.getUsuario().getNome());
		lbNomeV.setBounds(155, linha, 300, 20);
		lbNomeV.setFont(UtilitarioTela.getFont(14));
		lbNomeV.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbNomeV);

		linha += 35;
		JLabel lbSobrenome = new JLabel("Sobrenome :");
		lbSobrenome.setBounds(20, linha, 100, 20);
		lbSobrenome.setFont(UtilitarioTela.getFont(14));
		lbSobrenome.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbSobrenome);
		JLabel lbSobrenomeV = new JLabel(funcionarioSelecionado.getUsuario()
				.getSobreNome());
		lbSobrenomeV.setBounds(155, linha, 300, 20);
		lbSobrenomeV.setFont(UtilitarioTela.getFont(14));
		lbSobrenomeV.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbSobrenomeV);

		linha += 35;
		JLabel lbCpf = new JLabel("CPF :");
		lbCpf.setBounds(20, linha, 100, 20);
		lbCpf.setFont(UtilitarioTela.getFont(14));
		lbCpf.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbCpf);
		JLabel lbCpfV = new JLabel(funcionarioSelecionado.getUsuario().getCpf());
		lbCpfV.setBounds(155, linha, 300, 20);
		lbCpfV.setFont(UtilitarioTela.getFont(14));
		lbCpfV.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbCpfV);

		linha += 35;
		JLabel lbDataNascimento = new JLabel("Data Nascimento :");
		lbDataNascimento.setBounds(20, linha, 130, 20);
		lbDataNascimento.setFont(UtilitarioTela.getFont(14));
		lbDataNascimento.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbDataNascimento);
		JLabel lbDataNascimentoV = new JLabel(
				MascaraCrud.macaraDataBanco(funcionarioSelecionado.getUsuario()
						.getDataNascimento()));
		lbDataNascimentoV.setBounds(155, linha, 300, 20);
		lbDataNascimentoV.setFont(UtilitarioTela.getFont(14));
		lbDataNascimentoV.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbDataNascimentoV);

		linha += 35;
		JLabel lbTelefone = new JLabel("Telefone :");
		lbTelefone.setBounds(20, linha, 100, 20);
		lbTelefone.setFont(UtilitarioTela.getFont(14));
		lbTelefone.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbTelefone);
		JLabel lbTelefoneV = new JLabel(
				MascaraCrud.mascaraTelefoneResult(funcionarioSelecionado
						.getUsuario().getTelefone()));
		lbTelefoneV.setBounds(155, linha, 300, 20);
		lbTelefoneV.setFont(UtilitarioTela.getFont(14));
		lbTelefoneV.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbTelefoneV);

		linha += 35;
		JLabel lbSexo = new JLabel("Sexo :");
		lbSexo.setBounds(20, linha, 100, 20);
		lbSexo.setFont(UtilitarioTela.getFont(14));
		lbSexo.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbSexo);
		JLabel lbSexoV = new JLabel(Parametros.getSexo(funcionarioSelecionado
				.getUsuario().getSexo()));
		lbSexoV.setBounds(155, linha, 300, 20);
		lbSexoV.setFont(UtilitarioTela.getFont(14));
		lbSexoV.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbSexoV);

		linha += 35;
		JLabel lbEmail = new JLabel("Email :");
		lbEmail.setBounds(20, linha, 100, 20);
		lbEmail.setFont(UtilitarioTela.getFont(14));
		lbEmail.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbEmail);
		JLabel lbEmailV = new JLabel(funcionarioSelecionado.getUsuario().getEmail());
		lbEmailV.setBounds(155, linha, 300, 20);
		lbEmailV.setFont(UtilitarioTela.getFont(14));
		lbEmailV.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbEmailV);

		linha += 35;
		JLabel lbUsuario = new JLabel("Usu�rio :");
		lbUsuario.setBounds(20, linha, 100, 20);
		lbUsuario.setFont(UtilitarioTela.getFont(14));
		lbUsuario.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbUsuario);
		JLabel lbUsuarioV = new JLabel(funcionarioSelecionado.getUsuario()
				.getUsuario());
		lbUsuarioV.setBounds(155, linha, 300, 20);
		lbUsuarioV.setFont(UtilitarioTela.getFont(14));
		lbUsuarioV.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbUsuarioV);
		
		linha += 35;
		JLabel lbPermissao = new JLabel("Permiss�o :");
		lbPermissao.setBounds(20, linha, 100, 20);
		lbPermissao.setFont(UtilitarioTela.getFont(14));
		lbPermissao.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbPermissao);
		
		String permissao = "Funcion�rio";
		if(funcionarioSelecionado.getUsuario().getPermissao().getCodigoPermissao() == Parametros.getPermissaoAdministrador()){
			permissao = "Administrador";
		}
		
		JLabel lblPermissao = new JLabel(permissao);
		lblPermissao.setBounds(155, linha, 300, 20);
		lblPermissao.setFont(UtilitarioTela.getFont(14));
		lblPermissao.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lblPermissao);
	}

	private void save(int modoCrud) {
		boolean confirmado = true;

		if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
			MenssageConfirmacao.setMenssage("Deletar Funcion�rio",
					"Deseja Deletar Esse Funcion�rio?", modoCrud, meio);
			confirmado = MenssageConfirmacao.isConfirmado();
		}

		if (confirmado) {
			EntityManagerLocal.begin();
			String modo = "";
			String menssage = "";
			if (modoCrud == ParametroCrud.getModoCrudNovo()) {
				modo = "Cadastro de Funcion�rio";
				menssage = "Funcion�rio Cadastrado com Sucesso!";
				Usuario usuario = new Usuario();
				usuario.setAtivo(true);
				usuario.setNome(txNome.getText());
				usuario.setSobreNome(txSobreNome.getText());
				usuario.setCpf(txCpf.getText());
				usuario.setDataNascimento(UtilitarioCrud
						.getData(txDataNascimento.getText()));
				usuario.setTelefone(txTelefone.getText().replace("(", "")
						.replace(")", "").replace("-", ""));
				usuario.setUsuario(txUsuario.getText());
				usuario.setEmail(txEmail.getText());
				if(comboPermissao.getSelectedItem().equals("Administrador")){
					usuario.setPermissao(PermissaoDao.getPermissao(Parametros
							.getPermissaoAdministrador()));
				}else{
					usuario.setPermissao(PermissaoDao.getPermissao(Parametros
						.getPermissaoFuncionario()));
				}
				if (comboSexo.getSelectedIndex() == 0) {
					usuario.setSexo(Parametros.getSexoMasculino());
				} else {
					usuario.setSexo(Parametros.getSexoFeminino());
				}
				EntityManagerLocal.persist(usuario);
				Funcionario funcionario = new Funcionario();
				funcionario.setUsuario(usuario);
				funcionario.setDataCadastro(new Date());
				EntityManagerLocal.persist(funcionario);
				funcionarioSelecionado = funcionario;

			} else if (modoCrud == ParametroCrud.getModoCrudAlterar()) {
				modo = "Altera��o de Funcion�rio";
				menssage = "Funcion�rio Alterado com Sucesso!";
				Usuario usuario = funcionarioSelecionado.getUsuario();
				usuario.setNome(txNome.getText());
				usuario.setSobreNome(txSobreNome.getText());
				usuario.setCpf(txCpf.getText());
				usuario.setDataNascimento(UtilitarioCrud
						.getData(txDataNascimento.getText()));
				usuario.setTelefone(txTelefone.getText().replace("(", "")
						.replace(")", "").replace("-", ""));
				usuario.setUsuario(txUsuario.getText());
				if (comboSexo.getSelectedIndex() == 0) {
					usuario.setSexo(Parametros.getSexoMasculino());
				} else {
					usuario.setSexo(Parametros.getSexoFeminino());
				}
				if(comboPermissao.getSelectedItem().equals("Administrador")){
					usuario.setPermissao(PermissaoDao.getPermissao(Parametros
							.getPermissaoAdministrador()));
				}else{
					usuario.setPermissao(PermissaoDao.getPermissao(Parametros
						.getPermissaoFuncionario()));
				}
				EntityManagerLocal.merge(usuario);
				funcionarioSelecionado.setUsuario(usuario);
				EntityManagerLocal.merge(funcionarioSelecionado);
			} else if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
				modo = "Dele��o de Funcion�rio";
				menssage = "Funcion�rio Deletado com Sucesso!";
				Usuario usuario = funcionarioSelecionado.getUsuario();
				usuario.setAtivo(false);
				EntityManagerLocal.merge(usuario);
				funcionarioSelecionado.setUsuario(usuario);
				EntityManagerLocal.merge(funcionarioSelecionado);
			}
			EntityManagerLocal.commit();
			Menssage.setMenssage(modo, menssage, modoCrud, meio);
			if (modoCrud == ParametroCrud.getModoCrudNovo()
					|| modoCrud == ParametroCrud.getModoCrudAlterar()) {
				menuPai.exibirFuncionario(funcionarioSelecionado);
			} else if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
				menuPai.home();
			}
		}
	}

	public JTextField getTxNome() {
		return txNome;
	}

	public void setTxNome(JTextField txNome) {
		this.txNome = txNome;
	}
}
