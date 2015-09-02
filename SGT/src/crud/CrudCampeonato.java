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

import dao.BanimentoDao;
import dao.EntityManagerLocal;
import dao.ModalidadeDao;
import dao.PermissaoDao;
import entidade.Campeonato;
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

import menu.MenuCampeonato;
import menu.MenuComputador;
import menu.MenuJogador;

public class CrudCampeonato extends JPanel {
	private JTextField txDescricao;
	private JTextField txIp;
	private JLabel lblMsg;
	private JPanel msg;
	private Campeonato campeonatoSelecionado;
	private MenuCampeonato menuPai;
	private JPanel header;
	private JLabel lblHeader;
	private JPanel meio;
	private ComboBox cbModalidade;
	/**
	 * Create the panel.
	 */
	public CrudCampeonato(Campeonato campeonato, int modoCrud, MenuCampeonato menuPai) {
		this.menuPai = menuPai;
		setSize(UtilitarioTela.getTamanhoMeio());
		setLayout(null);
		setBackground(null);

		campeonatoSelecionado = campeonato;

		header = new JPanel();
		header.setSize(500, 30);
		header.setLocation((getWidth() / 2) - 250, 10);
		header.setLayout(null);
		header.setBackground(UtilitarioTela.getColorCrud(modoCrud));
		header.setBorder(null);
		add(header);

		String textoHeader = "";
		if (modoCrud == ParametroCrud.getModoCrudNovo()) {
			textoHeader = "Cadastrar Campeonato";
		} else if (modoCrud == ParametroCrud.getModoCrudAlterar()) {
			textoHeader = "Alterar Campeonato";
		} else if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
			textoHeader = "Deletar Campeonato";
		} else if (modoCrud == ParametroCrud.getModoVisualizar()) {
			textoHeader = "Visualizar Campeonato";
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
		txDescricao.setBounds(130, 20, 280, 25);
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

		JLabel lbIp = new JLabel("Modalidade :");
		lbIp.setBounds(20, 60, 100, 20);
		lbIp.setFont(UtilitarioTela.getFont(14));
		lbIp.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbIp);

		cbModalidade = new ComboBox(new Dimension(150, 25));
		cbModalidade.setModel(new DefaultComboBoxModel(ModalidadeDao.getVetorModalidade()));
		cbModalidade.setLocation(130, 60);
		meio.add(cbModalidade);

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

		if (campeonatoSelecionado != null) {
			setarCampos();
		}
	}

	public void setarCampos() {
		txDescricao.setText(campeonatoSelecionado.getDescricao());
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
		
		JLabel lbCodigo = new JLabel("Código :");
		lbCodigo.setBounds(120, linha, 100, 20);
		lbCodigo.setFont(UtilitarioTela.getFont(14));
		lbCodigo.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbCodigo);
		JLabel lbCodigoV = new JLabel(String.valueOf(campeonatoSelecionado
				.getCodigoCampeonato()));
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
		JLabel lbNomeV = new JLabel(campeonatoSelecionado.getDescricao());
		lbNomeV.setBounds(205, linha, 300, 20);
		lbNomeV.setFont(UtilitarioTela.getFont(14));
		lbNomeV.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbNomeV);
		
		linha += 30;
		JLabel lbModalidade = new JLabel("Modalidade :");
		lbModalidade.setBounds(120, linha, 100, 20);
		lbModalidade.setFont(UtilitarioTela.getFont(14));
		lbModalidade.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbModalidade);
		JLabel lbModalidadeV = new JLabel(campeonatoSelecionado.getModalidade().getDescricao());
		lbModalidadeV.setBounds(205, linha, 300, 20);
		lbModalidadeV.setFont(UtilitarioTela.getFont(14));
		lbModalidadeV.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbModalidadeV);

	}

	private void save(int modoCrud) {
		boolean confirmado = true;

		if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
			MenssageConfirmacao.setMenssage("Deletar Campeonato",
					"Deseja Deletar Esse Campeonato?", modoCrud, meio);
			confirmado = MenssageConfirmacao.isConfirmado();
		}

		if (confirmado) {
			EntityManagerLocal.begin();
			String modo = "";
			String menssage = "";
			if (modoCrud == ParametroCrud.getModoCrudNovo()) {
				modo = "Cadastro de Campeonato";
				menssage = "Campeonato Cadastrado com Sucesso!";
				Campeonato campeonato = new Campeonato();
				campeonato.setDescricao(txDescricao.getText());
				campeonato.setDataIncio(new Date());
				campeonato.setModalidade(ModalidadeDao.getModalidadeNome(String.valueOf(cbModalidade.getSelectedItem())));
				campeonato.setAtivo(true);
				EntityManagerLocal.persist(campeonato);
				campeonatoSelecionado = campeonato;

			} else if (modoCrud == ParametroCrud.getModoCrudAlterar()) {
				modo = "Alteração de Campeonato";
				menssage = "Campeonato Alterado com Sucesso!";
				campeonatoSelecionado.setDescricao(txDescricao.getText());
				campeonatoSelecionado.setModalidade(ModalidadeDao.getModalidadeNome(String.valueOf(cbModalidade.getSelectedItem())));
				EntityManagerLocal.merge(campeonatoSelecionado);
			} else if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
				modo = "Deleção de Campeonato";
				menssage = "Campeonato Deletado com Sucesso!";
				campeonatoSelecionado.setAtivo(false);
				EntityManagerLocal.merge(campeonatoSelecionado);
			}
			EntityManagerLocal.commit();
			Menssage.setMenssage(modo, menssage, modoCrud, meio);
			if (modoCrud == ParametroCrud.getModoCrudNovo()
					|| modoCrud == ParametroCrud.getModoCrudAlterar()) {
				menuPai.exibirCampeonato(campeonatoSelecionado);
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
