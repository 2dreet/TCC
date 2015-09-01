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
import utilitario.Computador;
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
import entidade.Pc;
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

import menu.MenuComputador;
import menu.MenuJogador;

public class CrudComputador extends JPanel {
	private JTextField txDescricao;
	private JTextField txIp;
	private JLabel lblMsg;
	private JPanel msg;
	private Pc pcSelecionado;
	private MenuComputador menuPai;
	private JPanel header;
	private JLabel lblHeader;
	private JPanel meio;
	/**
	 * Create the panel.
	 */
	public CrudComputador(Pc pc, int modoCrud, MenuComputador menuPai) {
		this.menuPai = menuPai;
		setSize(UtilitarioTela.getTamanhoMeio());
		setLayout(null);
		setBackground(null);

		pcSelecionado = pc;

		header = new JPanel();
		header.setSize(500, 30);
		header.setLocation((getWidth() / 2) - 250, 10);
		header.setLayout(null);
		header.setBackground(UtilitarioTela.getColorCrud(modoCrud));
		header.setBorder(null);
		add(header);

		String textoHeader = "";
		if (modoCrud == ParametroCrud.getModoCrudNovo()) {
			textoHeader = "Cadastrar Computador";
		} else if (modoCrud == ParametroCrud.getModoCrudAlterar()) {
			textoHeader = "Alterar Computador";
		} else if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
			textoHeader = "Deletar Computador";
		} else if (modoCrud == ParametroCrud.getModoVisualizar()) {
			textoHeader = "Visualizar Computador";
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

		JLabel lbDescricao = new JLabel("Descrição :");
		lbDescricao.setBounds(20, 20, 100, 20);
		lbDescricao.setFont(UtilitarioTela.getFont(14));
		lbDescricao.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbDescricao);

		txDescricao = new JTextField();
		txDescricao.setColumns(100);
		txDescricao.setBounds(120, 20, 280, 25);
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

		JLabel lbIp = new JLabel("IP :");
		lbIp.setBounds(20, 55, 100, 20);
		lbIp.setFont(UtilitarioTela.getFont(14));
		lbIp.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbIp);

		txIp = new JTextField();
		txIp.setColumns(24);
		txIp.setBounds(120, 55, 100, 25);
		txIp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				ValidadorCrud.campoIp(arg0, txIp.getText());
			}

		});
		txIp.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txIp.setBorder(UtilitarioTela.jTextFieldComFocus());
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				txIp.setBorder(UtilitarioTela.jTextFieldNormal());
				limpaErro();
			}
		});
		txIp.setLayout(null);
		txIp.setBorder(UtilitarioTela.jTextFieldNormal());
		meio.add(txIp);
		setVisible(true);

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
		msg.setLocation(5, 100);
		msg.setLayout(null);
		msg.setBackground(null);
		meio.add(msg);

		if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
			visualizar(modoCrud);
		} else if (modoCrud == ParametroCrud.getModoVisualizar()) {
			visualizar(modoCrud);
		}

		if (pcSelecionado != null) {
			setarCampos();
		}
	}

	public void setarCampos() {
		txDescricao.setText(pcSelecionado.getDescricao());
		txIp.setText(pcSelecionado.getIp());
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
				msgErro("Campo Descrição é Obrigatório!");
				return false;
			}
			if (txIp.getText() == null || txIp.getText().trim().isEmpty()) {
				msgErro("Campo IP é Obrigatório!");
				txIp.requestFocus();
				return false;
			} else if (!ValidadorCrud.validarIP(txIp.getText())) {
				msgErro("IP é Inválido!");
				txIp.requestFocus();
				return false;
			} else if (!ValidadorCrud.validarIpCadastrado(txIp.getText())
					&& pcSelecionado ==null) {
				msgErro("IP já Cadastrado!");
				txIp.requestFocus();
				return false;
			} else if (!ValidadorCrud.validarIpCadastrado(txIp.getText())
					&& pcSelecionado !=null && !pcSelecionado.equals(txIp.getText())) {
				msgErro("IP já Cadastrado!");
				txIp.requestFocus();
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

		JPanel jpLabel = new JPanel();
		jpLabel.setSize(80, 80);
		jpLabel.setLocation(20, linha);
		jpLabel.setLayout(null);
		jpLabel.setBackground(UtilitarioTela.getFundoCrud());
		jpLabel.setBorder(new BordaSombreada(modoCrud));
		meio.add(jpLabel);
		
		Computador.getPcConectado(pcSelecionado, jpLabel);
		
		
		JLabel lbCodigo = new JLabel("Código :");
		lbCodigo.setBounds(120, linha, 100, 20);
		lbCodigo.setFont(UtilitarioTela.getFont(14));
		lbCodigo.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbCodigo);
		JLabel lbCodigoV = new JLabel(String.valueOf(pcSelecionado
				.getCodigoPC()));
		lbCodigoV.setBounds(205, linha, 300, 20);
		lbCodigoV.setFont(UtilitarioTela.getFont(14));
		lbCodigoV.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbCodigoV);

		linha += 30;
		JLabel lbNome = new JLabel("Descrição :");
		lbNome.setBounds(120, linha, 100, 20);
		lbNome.setFont(UtilitarioTela.getFont(14));
		lbNome.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbNome);
		JLabel lbNomeV = new JLabel(pcSelecionado.getDescricao());
		lbNomeV.setBounds(205, linha, 300, 20);
		lbNomeV.setFont(UtilitarioTela.getFont(14));
		lbNomeV.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbNomeV);

		linha += 30;
		JLabel lbSobrenome = new JLabel("IP :");
		lbSobrenome.setBounds(120, linha, 100, 20);
		lbSobrenome.setFont(UtilitarioTela.getFont(14));
		lbSobrenome.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbSobrenome);
		JLabel lbSobrenomeV = new JLabel(pcSelecionado.getIp());
		lbSobrenomeV.setBounds(205, linha, 300, 20);
		lbSobrenomeV.setFont(UtilitarioTela.getFont(14));
		lbSobrenomeV.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbSobrenomeV);
		
	}

	private void save(int modoCrud) {
		boolean confirmado = true;

		if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
			MenssageConfirmacao.setMenssage("Deletar Computador",
					"Deseja Deletar Esse Computador?", modoCrud, meio);
			confirmado = MenssageConfirmacao.isConfirmado();
		}

		if (confirmado) {
			EntityManagerLocal.begin();
			String modo = "";
			String menssage = "";
			if (modoCrud == ParametroCrud.getModoCrudNovo()) {
				modo = "Cadastro de Computador";
				menssage = "Computador Cadastrado com Sucesso!";
				Pc pc = new Pc();
				pc.setDescricao(txDescricao.getText());
				pc.setIp(txIp.getText());
				pc.setAtivo(true);
				EntityManagerLocal.persist(pc);
				pcSelecionado = pc;

			} else if (modoCrud == ParametroCrud.getModoCrudAlterar()) {
				modo = "Alteração de Computador";
				menssage = "Computador Alterado com Sucesso!";
				pcSelecionado.setDescricao(txDescricao.getText());
				pcSelecionado.setIp(txIp.getText());
				EntityManagerLocal.merge(pcSelecionado);
			} else if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
				modo = "Deleção de Computador";
				menssage = "Computador Deletado com Sucesso!";
				pcSelecionado.setAtivo(false);
				EntityManagerLocal.merge(pcSelecionado);
			}
			EntityManagerLocal.commit();
			Menssage.setMenssage(modo, menssage, modoCrud, meio);
			if (modoCrud == ParametroCrud.getModoCrudNovo()
					|| modoCrud == ParametroCrud.getModoCrudAlterar()) {
				menuPai.exibirPc(pcSelecionado);
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
