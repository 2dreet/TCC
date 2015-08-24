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

import menu.MenuJogador;

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
	private ComboBox comboSexo;
	private Jogador jogadorSelecionado;
	private MenuJogador menuPai;
	private JPanel header;
	private JLabel lblHeader;
	private JPanel meio;

	/**
	 * Create the panel.
	 */
	public CrudJogador(Jogador jogador, int modoCrud, MenuJogador menuPai) {
		this.menuPai = menuPai;
		setSize(UtilitarioTela.getTamanhoMeio());
		setLayout(null);
		setBackground(null);

		jogadorSelecionado = jogador;

		header = new JPanel();
		header.setSize(500, 30);
		header.setLocation((getWidth() / 2) - 250, 10);
		header.setLayout(null);
		header.setBackground(UtilitarioTela.getColorCrud(modoCrud));
		header.setBorder(null);
		add(header);

		String textoHeader = "";
		if (modoCrud == ParametroCrud.getModoCrudNovo()) {
			textoHeader = "Cadastrar Jogador";
		} else if (modoCrud == ParametroCrud.getModoCrudAlterar()) {
			textoHeader = "Alterar Jogador";
		} else if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
			textoHeader = "Deletar Jogador";
		} else if (modoCrud == ParametroCrud.getModoVisualizar()) {
			textoHeader = "Visualizar Jogador";
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
		setVisible(true);

		JLabel lbRg = new JLabel("RG :");
		lbRg.setBounds(20, 90, 100, 20);
		lbRg.setFont(UtilitarioTela.getFont(14));
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

		JLabel lbUsuario = new JLabel("Usuário :");
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
		msg.setLocation(5, 300);
		msg.setLayout(null);
		msg.setBackground(null);
		meio.add(msg);

		if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
			visualizar(modoCrud);
		} else if (modoCrud == ParametroCrud.getModoVisualizar()) {
			visualizar(modoCrud);
		}

		if (jogadorSelecionado != null) {
			setarCampos();
		}
	}

	public void setarCampos() {
		txNome.setText(jogadorSelecionado.getUsuario().getNome());
		txSobreNome.setText(jogadorSelecionado.getUsuario().getSobreNome());
		txRg.setText(jogadorSelecionado.getUsuario().getRg());
		txTelefone.setText(MascaraCrud.mascaraTelefoneResult(jogadorSelecionado
				.getUsuario().getTelefone()));
		txEmail.setText(jogadorSelecionado.getUsuario().getEmail());
		txUsuario.setText(jogadorSelecionado.getUsuario().getUsuario());
		txDataNascimento.setText(MascaraCrud.macaraDataBanco(jogadorSelecionado
				.getUsuario().getDataNascimento()));
		comboSexo.setSelectedIndex(0);
		if (jogadorSelecionado.getUsuario().getSexo() == Parametros
				.getSexoFeminino()) {
			comboSexo.setSelectedIndex(1);
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

			if (txNome.getText() == null || txNome.getText().trim().isEmpty()) {
				txNome.requestFocus();
				msgErro("Campo Nome é Obrigatório!");
				return false;
			}
			if (txSobreNome.getText() == null
					|| txSobreNome.getText().trim().isEmpty()) {
				txSobreNome.requestFocus();
				msgErro("Campo Sobrenome é Obrigatório!");
				return false;
			}

			if (txRg.getText() == null || txRg.getText().trim().isEmpty()) {
				msgErro("Campo RG é Obrigatório!");
				txRg.requestFocus();
				return false;
			} else if (!ValidadorCrud.validarRg(txRg.getText())) {
				msgErro("RG é Inválido!");
				txRg.requestFocus();
				return false;
			}

			if (txDataNascimento.getText() == null
					|| txDataNascimento.getText().trim().isEmpty()) {
				txDataNascimento.requestFocus();
				msgErro("Campo Data de Nascimento é Obrigatório!");
				return false;
			} else if (!ValidadorCrud.validarData(txDataNascimento.getText())) {
				msgErro("Data de Nascimento Inválida!");
				txDataNascimento.requestFocus();
				return false;
			}

			if (txTelefone.getText() == null
					|| txTelefone.getText().trim().isEmpty()) {
				txTelefone.requestFocus();
				msgErro("Campo Telefone é Obrigatório!");
				return false;
			} else if (!ValidadorCrud.validarTelefone(txTelefone.getText())) {
				txTelefone.requestFocus();
				msgErro("Telefone é Inválido!");
				return false;
			}

			if (txEmail.getText() == null || txEmail.getText().trim().isEmpty()) {
				txEmail.requestFocus();
				msgErro("Campo Email é Obrigatório!");
				return false;
			} else if (!ValidadorCrud.validarEmail(txEmail.getText())) {
				txEmail.requestFocus();
				msgErro("Email é Inválido!");
				return false;
			}

			if (txUsuario.getText() == null
					|| txUsuario.getText().trim().isEmpty()) {
				txUsuario.requestFocus();
				msgErro("Campo Usuário é Obrigatório!");
				return false;
			} else if (!ValidadorCrud.validarUsuario(txUsuario.getText())
					&& jogadorSelecionado == null) {
				txUsuario.requestFocus();
				msgErro("Usuário Já Cadastrado!");
				return false;
			} else if (!ValidadorCrud.validarUsuario(txUsuario.getText())
					&& jogadorSelecionado != null
					&& !jogadorSelecionado.getUsuario().getUsuario()
							.equals(txUsuario.getText())) {
				txUsuario.requestFocus();
				msgErro("Usuário Já Cadastrado!");
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
		JLabel lbCodigoV = new JLabel(String.valueOf(jogadorSelecionado
				.getCodigoJogador()));
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
		JLabel lbNomeV = new JLabel(jogadorSelecionado.getUsuario().getNome());
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
		JLabel lbSobrenomeV = new JLabel(jogadorSelecionado.getUsuario()
				.getSobreNome());
		lbSobrenomeV.setBounds(155, linha, 300, 20);
		lbSobrenomeV.setFont(UtilitarioTela.getFont(14));
		lbSobrenomeV.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbSobrenomeV);

		linha += 35;
		JLabel lbRg = new JLabel("RG :");
		lbRg.setBounds(20, linha, 100, 20);
		lbRg.setFont(UtilitarioTela.getFont(14));
		lbRg.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbRg);
		JLabel lbRgV = new JLabel(jogadorSelecionado.getUsuario().getRg());
		lbRgV.setBounds(155, linha, 300, 20);
		lbRgV.setFont(UtilitarioTela.getFont(14));
		lbRgV.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbRgV);

		linha += 35;
		JLabel lbDataNascimento = new JLabel("Data Nascimento :");
		lbDataNascimento.setBounds(20, linha, 130, 20);
		lbDataNascimento.setFont(UtilitarioTela.getFont(14));
		lbDataNascimento.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbDataNascimento);
		JLabel lbDataNascimentoV = new JLabel(
				MascaraCrud.macaraDataBanco(jogadorSelecionado.getUsuario()
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
				MascaraCrud.mascaraTelefoneResult(jogadorSelecionado
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
		JLabel lbSexoV = new JLabel(Parametros.getSexo(jogadorSelecionado
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
		JLabel lbEmailV = new JLabel(jogadorSelecionado.getUsuario().getEmail());
		lbEmailV.setBounds(155, linha, 300, 20);
		lbEmailV.setFont(UtilitarioTela.getFont(14));
		lbEmailV.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbEmailV);

		linha += 35;
		JLabel lbUsuario = new JLabel("Usuário :");
		lbUsuario.setBounds(20, linha, 100, 20);
		lbUsuario.setFont(UtilitarioTela.getFont(14));
		lbUsuario.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbUsuario);
		JLabel lbUsuarioV = new JLabel(jogadorSelecionado.getUsuario()
				.getUsuario());
		lbUsuarioV.setBounds(155, linha, 300, 20);
		lbUsuarioV.setFont(UtilitarioTela.getFont(14));
		lbUsuarioV.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbUsuarioV);
	}

	private void save(int modoCrud) {
		boolean confirmado = true;

		if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
			MenssageConfirmacao.setMenssage("Deletar Jogador",
					"Deseja Deletar Esse Jogador?", modoCrud);
			confirmado = MenssageConfirmacao.isConfirmado();
		}

		if (confirmado) {
			EntityManagerLocal.begin();
			String modo = "";
			String menssage = "";
			if (modoCrud == ParametroCrud.getModoCrudNovo()) {
				modo = "Cadastro de Jogador";
				menssage = "<html>Jogador Cadastrado com Sucesso!</html>";
				Usuario usuario = new Usuario();
				usuario.setAtivo(true);
				usuario.setNome(txNome.getText());
				usuario.setSobreNome(txSobreNome.getText());
				usuario.setRg(txRg.getText());
				usuario.setDataNascimento(UtilitarioCrud
						.getData(txDataNascimento.getText()));
				usuario.setTelefone(txTelefone.getText().replace("(", "")
						.replace(")", "").replace("-", ""));
				usuario.setUsuario(txUsuario.getText());
				usuario.setEmail(txEmail.getText());
				usuario.setPermissao(PermissaoDao.getPermissao(Parametros
						.getPermissaoJogador()));
				if (comboSexo.getSelectedIndex() == 0) {
					usuario.setSexo(Parametros.getSexoMasculino());
				} else {
					usuario.setSexo(Parametros.getSexoFeminino());
				}
				EntityManagerLocal.persist(usuario);
				Jogador jogador = new Jogador();
				jogador.setUsuario(usuario);
				jogador.setDataCadastro(new Date());
				EntityManagerLocal.persist(jogador);
				jogadorSelecionado = jogador;

			} else if (modoCrud == ParametroCrud.getModoCrudAlterar()) {
				modo = "Alteração de Jogador";
				menssage = "<html>Jogador Alterado com Sucesso!</html>";
				Usuario usuario = jogadorSelecionado.getUsuario();
				usuario.setNome(txNome.getText());
				usuario.setSobreNome(txSobreNome.getText());
				usuario.setRg(txRg.getText());
				usuario.setDataNascimento(UtilitarioCrud
						.getData(txDataNascimento.getText()));
				usuario.setTelefone(txTelefone.getText());
				usuario.setUsuario(txUsuario.getText());
				if (comboSexo.getSelectedIndex() == 0) {
					usuario.setSexo(Parametros.getSexoMasculino());
				} else {
					usuario.setSexo(Parametros.getSexoFeminino());
				}
				EntityManagerLocal.merge(usuario);
				jogadorSelecionado.setUsuario(usuario);
				EntityManagerLocal.merge(jogadorSelecionado);
			} else if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
				modo = "Deleção de Jogador";
				menssage = "<html>Jogador Deletado com Sucesso!</html>";
				Usuario usuario = jogadorSelecionado.getUsuario();
				usuario.setAtivo(false);
				EntityManagerLocal.merge(usuario);
				jogadorSelecionado.setUsuario(usuario);
				EntityManagerLocal.merge(jogadorSelecionado);
			}
			EntityManagerLocal.commit();
			Menssage.setMenssage(modo, menssage, modoCrud);
			if (modoCrud == ParametroCrud.getModoCrudNovo()
					|| modoCrud == ParametroCrud.getModoCrudAlterar()) {
				menuPai.exibirJogador(jogadorSelecionado);
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
