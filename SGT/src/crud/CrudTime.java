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
import dao.JogadorBanimentoDao;
import dao.JogadorDao;
import dao.PermissaoDao;
import dao.TimeDao;
import dialog.DialogLocalizarJogador;
import entidade.CampeonatoTime;
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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
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
	private JTable tabelaTitular;
	private List<Jogador> listaJogadorTitular;
	private List<Jogador> listaJogador;
	private Object[][] colunas = new Object[][] { new String[] { "Código" },
			new String[] { "Nome" }, new String[] { "Usuário" },
			new String[] { "Cpf" }, new String[] { "Telefone" },
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
		header.setLocation((getWidth() / 2) - 325, 10);
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
		meio.setLocation((getWidth() / 2) - 325, 40);
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
			btSalvar.setBounds((meio.getWidth() / 2) - 85,
					meio.getHeight() - 70, 150, 35);
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

	public void localizarLogo() {
		try {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"Imagens Png", "png");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				String extencao = chooser.getSelectedFile().getName();
				if (extencao.length() > 0
						&& UtilitarioLogo.validarLogo(extencao)) {
					ImageIcon logoImg = new ImageIcon(chooser.getSelectedFile()
							.getPath());
					if (logoImg.getIconHeight() == 50
							&& logoImg.getIconWidth() == 50) {
						logo = chooser.getSelectedFile();
						pLogo.removeAll();
						txLogo.setText(logo.getAbsolutePath());
						JLabel lbLogo = new JLabel(logoImg);
						lbLogo.setBounds(2, 2, 50, 50);
						pLogo.add(lbLogo);
						pLogo.repaint();
					} else {
						logo = null;
						Menssage.setMenssage(
								"Imagem Inválida",
								"Imagem selecionada deve ter o tamanho 50 X 50",
								ParametroCrud.getModoCrudDeletar(), meio);
						limparLogo();
					}
				} else {
					Menssage.setMenssage(
							"Imagem Inválida",
							"Arquivo selecionado deve ser uma \nImagem do tipo '.PNG, .GIF, .JPG ou .JPEG'",
							ParametroCrud.getModoCrudDeletar(), meio);
					logo = null;
					limparLogo();
				}

			}
		} catch (Exception e) {

		}
	}

	public void limparLogo() {
		pLogo.removeAll();
		pLogo.repaint();
	}

	public void setarCampos() {
		txDescricao.setText(timeSelecionado.getDescricao());
		if (timeSelecionado.getLogo() != null) {
			logo = new File("logo/" + timeSelecionado.getLogo());
			pLogo.removeAll();
			txLogo.setText(logo.getAbsolutePath());
			JLabel lbLogo = new JLabel(new ImageIcon("logo/"
					+ timeSelecionado.getLogo()));
			lbLogo.setBounds(2, 2, 50, 50);
			pLogo.add(lbLogo);
			pLogo.repaint();
		} else {
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
			if (txDescricao.getText() == null
					|| txDescricao.getText().trim().isEmpty()) {
				txDescricao.requestFocus();
				msgErro("Campo Nome é Obrigatório!");
				return false;
			} else if (TimeDao.timeCadastrado(txDescricao.getText())
					&& timeSelecionado != null
					&& !timeSelecionado.getDescricao().equals(
							txDescricao.getText())) {
				txDescricao.requestFocus();
				msgErro("Nome Já está sendo Usado\nInformar outro nome!");
				return false;
			} else if (TimeDao.timeCadastrado(txDescricao.getText())
					&& timeSelecionado == null) {
				txDescricao.requestFocus();
				msgErro("Nome Já está sendo Usado\nInformar outro nome!");
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
			btSalvar.setBounds((meio.getWidth() / 2) - 85,
					meio.getHeight() - 70, 150, 35);
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

		JLabel lbLogoV = new JLabel(new ImageIcon("logo/"
				+ timeSelecionado.getLogo()));
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

		JLabel lbCodigo = new JLabel("Código :");
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
		JLabel lbDescricao = new JLabel("Descrição :");
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

		// Titulares
		linha += 35;
		JPanel pTitulares = new JPanel();
		pTitulares.setLayout(null);
		pTitulares.setBackground(null);
		pTitulares.setBounds(2, linha, 646, 147);
		pTitulares.setBorder(null);

		JLabel lbTitulares = new JLabel("JOGADORES TITULARES");
		lbTitulares.setBounds(0, 0, 646, 20);
		lbTitulares.setFont(UtilitarioTela.getFont(12));
		lbTitulares.setForeground(UtilitarioTela.getFontColorCrud());
		lbTitulares.setHorizontalAlignment(SwingConstants.CENTER);
		pTitulares.add(lbTitulares);

		tabelaTitular = new JTable();
		tabelaTitular.setModel(UtilitarioTabela.getModelo(colunas));

		TableColumnModel tcmTitular = tabelaTitular.getColumnModel();
		TextoIconeCell rendererTitular = new TextoIconeCell();
		tcmTitular.getColumn(0).setPreferredWidth(60);
		tcmTitular.getColumn(0).setMinWidth(60);
		tcmTitular.getColumn(0).setResizable(false);
		tcmTitular.getColumn(1).setPreferredWidth(170);
		tcmTitular.getColumn(1).setMinWidth(170);
		tcmTitular.getColumn(1).setResizable(false);
		tcmTitular.getColumn(2).setPreferredWidth(100);
		tcmTitular.getColumn(2).setMinWidth(100);
		tcmTitular.getColumn(2).setResizable(false);
		tcmTitular.getColumn(3).setPreferredWidth(83);
		tcmTitular.getColumn(3).setMinWidth(83);
		tcmTitular.getColumn(3).setResizable(false);
		tcmTitular.getColumn(4).setPreferredWidth(100);
		tcmTitular.getColumn(4).setMinWidth(100);
		tcmTitular.getColumn(4).setResizable(false);
		tcmTitular.getColumn(5).setPreferredWidth(130);
		tcmTitular.getColumn(5).setMinWidth(130);
		tcmTitular.getColumn(5).setResizable(false);

		UtilitarioTabela.pintarColona(UtilitarioTabela.getFundoHeaderPadrao(),
				UtilitarioTabela.getFontColotHeaderPadrao(), tcmTitular,
				colunas);
		UtilitarioTabela.pintarLinha(new Color(255, 153, 153), Color.black,
				tabelaTitular);
		tabelaTitular.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabelaTitular.setPreferredScrollableViewportSize(tabelaTitular
				.getPreferredSize());
		tabelaTitular.getTableHeader().setReorderingAllowed(false);
		tabelaTitular.setFont(UtilitarioTela.getFont(12));
		tabelaTitular.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollTitular = new JScrollPane(tabelaTitular);
		scrollTitular.setBounds(1, 20, 646, 100);
		scrollTitular.setBackground(Color.red);
		pTitulares.add(scrollTitular);

		JButton btDelTitular = new JButton("Remover Titular");
		btDelTitular.setBounds(2, pTitulares.getHeight() - 25, 150, 25);
		btDelTitular.setFont(UtilitarioTela.getFont(12));
		btDelTitular.setFocusPainted(false);
		btDelTitular.setBackground(UtilitarioTela.getFontColorPadrao());
		btDelTitular.setIcon((new ImageIcon(CrudTime.class
				.getResource("/imagem/diverssos/del.png"))));
		btDelTitular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tabelaTitular.getRowCount() > 0) {
					if (tabelaTitular.getSelectedRow() > -1) {
						removerJogadorTitular();
					} else {
						Menssage.setMenssage("Jogador não Selecionado",
								"Deve selecionar um Jogador!",
								ParametroCrud.getModoCrudDeletar(), meio);
					}
				} else {
					Menssage.setMenssage("Jogador não Selecionado",
							"Deve selecionar um Jogador!",
							ParametroCrud.getModoCrudDeletar(), meio);
				}
			}
		});
		pTitulares.add(btDelTitular);
		meio.add(pTitulares);

		// Reservas
		linha += 167;
		JPanel pReservas = new JPanel();
		pReservas.setLayout(null);
		pReservas.setBackground(null);
		pReservas.setBounds(2, linha, 646, 167);
		pReservas.setBorder(null);

		JLabel lbReservas = new JLabel("JOGADORES RESERVAS");
		lbReservas.setBounds(0, 0, 646, 20);
		lbReservas.setFont(UtilitarioTela.getFont(12));
		lbReservas.setForeground(UtilitarioTela.getFontColorCrud());
		lbReservas.setHorizontalAlignment(SwingConstants.CENTER);
		pReservas.add(lbReservas);

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

		UtilitarioTabela.pintarColona(UtilitarioTabela.getFundoHeaderPadrao(),
				UtilitarioTabela.getFontColotHeaderPadrao(), tcm, colunas);
		UtilitarioTabela.pintarLinha(new Color(255, 153, 153), Color.black,
				tabela);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.setPreferredScrollableViewportSize(tabela.getPreferredSize());
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.setFont(UtilitarioTela.getFont(12));
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(1, 20, 646, 120);

		JButton btAddTitular = new JButton("Adicionar Titular");
		btAddTitular.setBounds(2, pReservas.getHeight() - 25, 150, 25);
		btAddTitular.setFont(UtilitarioTela.getFont(12));
		btAddTitular.setForeground(UtilitarioTela.getFontColorSelecao(false));
		btAddTitular.setFocusPainted(false);
		btAddTitular.setBackground(UtilitarioTela.getFontColorCrud());
		btAddTitular.setIcon((new ImageIcon(CrudTime.class
				.getResource("/imagem/diverssos/add.png"))));
		btAddTitular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tabela.getRowCount() > 0) {
					if (tabela.getSelectedRow() > -1) {
						adicionarJogadorTitular();
					} else {
						Menssage.setMenssage("Jogador não Selecionado",
								"Deve selecionar um Jogador!",
								ParametroCrud.getModoCrudDeletar(), meio);
					}
				} else {
					Menssage.setMenssage("Jogador não Selecionado",
							"Deve selecionar um Jogador!",
							ParametroCrud.getModoCrudDeletar(), meio);
				}
			}
		});
		pReservas.add(btAddTitular);
		pReservas.add(scroll);
		meio.add(pReservas);

		if (modoCrud == ParametroCrud.getModoVisualizar()) {
			JButton btAddJogador = new JButton("Adicionar Jogador");
			btAddJogador.setBounds(162, pReservas.getHeight() - 25, 170, 25);
			btAddJogador.setFont(UtilitarioTela.getFont(12));
			btAddJogador.setFocusPainted(false);
			btAddJogador.setBackground(UtilitarioTela
					.getColorCrud(ParametroCrud.getModoCrudNovo()));
			btAddJogador.setIcon((new ImageIcon(CrudTime.class
					.getResource("/imagem/diverssos/add2.png"))));
			pReservas.add(btAddJogador);
			btAddJogador.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					DialogLocalizarJogador.localizarJogador(meio);
					if (DialogLocalizarJogador.getJogadorSelecionado() != null) {
						Jogador jogador = DialogLocalizarJogador
								.getJogadorSelecionado();
						boolean confirmado = true;
						MenssageConfirmacao.setMenssage(
								"Adicionar Jogador no Time",
								"Deseja Adicionar Esse Jogador no Time?\nJogador: "
										+ jogador.getUsuario().getNome() + " "
										+ jogador.getUsuario().getSobreNome(),
								modoCrud, meio);
						confirmado = MenssageConfirmacao.isConfirmado();
						if (confirmado) {
							if(JogadorBanimentoDao.jogadorBanido(jogador.getCodigoJogador())){
								Menssage.setMenssage("Jogador Banido",
										"Jogador Selecionado não pode ser adicionado no time. Pois o Jogador está Banido!",
										ParametroCrud.getModoCrudDeletar(), meio);
							}else{
								EntityManagerLocal.begin();
								jogador.setTime(timeSelecionado);
								EntityManagerLocal.merge(jogador);
								EntityManagerLocal.commit();
							}
						}
						atualizarTabela();
					}
				}
			});

			JButton btRemoveJogador = new JButton("Remover Jogador");
			btRemoveJogador.setBounds(342, pReservas.getHeight() - 25, 170, 25);
			btRemoveJogador.setFont(UtilitarioTela.getFont(12));
			btRemoveJogador.setFocusPainted(false);
			btRemoveJogador.setBackground(UtilitarioTela
					.getColorCrud(ParametroCrud.getModoCrudDeletar()));
			btRemoveJogador.setIcon((new ImageIcon(CrudTime.class
					.getResource("/imagem/diverssos/del.png"))));
			pReservas.add(btRemoveJogador);
			btRemoveJogador.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (tabela.getRowCount() > 0) {
						if (tabela.getSelectedRow() > -1
								|| tabelaTitular.getSelectedRow() > -1) {
							Jogador jogadorSelecionado = JogadorDao.getJogador(Integer
									.parseInt(String.valueOf(tabela.getValueAt(
											tabela.getSelectedRow(), 0))));
							if (jogadorSelecionado == null) {
								jogadorSelecionado = JogadorDao.getJogador(Integer
										.parseInt(String.valueOf(tabelaTitular
												.getValueAt(tabelaTitular
														.getSelectedRow(), 0))));
							}
							boolean confirmado = true;
							MenssageConfirmacao.setMenssage(
									"Remover Jogador do Time",
									"Deseja Remover Esse Jogador do Time?\nJogador: "
											+ jogadorSelecionado.getUsuario()
													.getNome()
											+ " "
											+ jogadorSelecionado.getUsuario()
													.getSobreNome(), modoCrud,
									meio);
							confirmado = MenssageConfirmacao.isConfirmado();
							if (confirmado) {
								EntityManagerLocal.begin();
								jogadorSelecionado.setTime(null);
								EntityManagerLocal.merge(jogadorSelecionado);
								EntityManagerLocal.commit();
							}
						} else {
							Menssage.setMenssage("Jogador não Selecionado",
									"Deve selecionar um Jogador!",
									ParametroCrud.getModoCrudDeletar(), meio);
						}
					} else {
						Menssage.setMenssage("Jogador não Selecionado",
								"Deve selecionar um Jogador!",
								ParametroCrud.getModoCrudDeletar(), meio);
					}
					atualizarTabela();
				}
			});
		}
		atualizarTabela();
	}

	public void atualizarTabela() {
		listaJogador = JogadorDao.getListaJogadorTime(timeSelecionado
				.getCodigoTime());
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.setNumRows(0);
		if (listaJogador != null) {
			for (Jogador j : listaJogador) {
				modelo.addRow(new String[] {
						String.valueOf(j.getCodigoJogador()),
						j.getUsuario().getNome() + " "
								+ j.getUsuario().getSobreNome(),
						j.getUsuario().getUsuario(),
						j.getUsuario().getCpf(),
						MascaraCrud.mascaraTelefoneResult(j.getUsuario()
								.getTelefone()), j.getUsuario().getEmail() });

			}
		} else {
			listaJogador = new ArrayList<Jogador>();
		}
		atualizarTabelaTitular();
	}

	public void atualizarTabelaTitular() {
		listaJogadorTitular = JogadorDao.getListaJogadorTitularTime(timeSelecionado
				.getCodigoTime());
		DefaultTableModel modelo = (DefaultTableModel) tabelaTitular.getModel();
		modelo.setNumRows(0);
		if (listaJogadorTitular != null) {
			for (Jogador j : listaJogadorTitular) {
				modelo.addRow(new String[] {
						String.valueOf(j.getCodigoJogador()),
						j.getUsuario().getNome() + " "
								+ j.getUsuario().getSobreNome(),
						j.getUsuario().getUsuario(),
						j.getUsuario().getCpf(),
						MascaraCrud.mascaraTelefoneResult(j.getUsuario()
								.getTelefone()), j.getUsuario().getEmail() });

			}
		} else {
			listaJogadorTitular = new ArrayList<Jogador>();
		}
	}

	private void save(int modoCrud) {
		boolean confirmado = true;

		if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
			MenssageConfirmacao.setMenssage("Deletar Time",
					"Deseja Deletar Esse Time?", modoCrud, meio);
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
				if (logo != null) {
					try {
						logo.renameTo(new File(logo.getPath().replace(
								logo.getName(), "")
								+ txDescricao.getText() + ".png"));
						logo = new File(logo.getPath().replace(logo.getName(),
								"")
								+ txDescricao.getText() + ".png");
						MoverArquivo.copyFile(logo,
								MoverArquivo.getLocalLogo(logo));
						time.setLogo(logo.getName());
					} catch (Exception e) {
						e.printStackTrace();
						Menssage.setMenssage("Erro ao mover Logo",
								"Não foi possivel mover a logo!",
								ParametroCrud.getModoErro(), meio);
					}
				}
				time.setDescricao(txDescricao.getText());
				time.setDataCadastro(new Date());
				time.setAtivo(true);
				EntityManagerLocal.persist(time);
				timeSelecionado = time;

			} else if (modoCrud == ParametroCrud.getModoCrudAlterar()) {
				modo = "Alteração de Time";
				menssage = "Time Alterado com Sucesso!";
				if (logo != null) {
					try {
						logo.renameTo(new File(logo.getPath().replace(
								logo.getName(), "")
								+ txDescricao.getText() + ".png"));
						logo = new File(logo.getPath().replace(logo.getName(),
								"")
								+ txDescricao.getText() + ".png");
						MoverArquivo.copyFile(logo,
								MoverArquivo.getLocalLogo(logo));
						timeSelecionado.setLogo(logo.getName());
					} catch (Exception e) {
						e.printStackTrace();
						Menssage.setMenssage("Erro ao mover Logo",
								"Não foi possivel mover a logo!",
								ParametroCrud.getModoErro(), meio);
					}
				}

				timeSelecionado.setDescricao(txDescricao.getText());
				EntityManagerLocal.merge(timeSelecionado);

			} else if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
				JogadorDao.desvincularJogadorTime(timeSelecionado
						.getCodigoTime());
				modo = "Deleção de Time";
				menssage = "Time Deletado com Sucesso!";
				timeSelecionado.setAtivo(false);
				EntityManagerLocal.merge(timeSelecionado);
			}
			EntityManagerLocal.commit();
			Menssage.setMenssage(modo, menssage, modoCrud, meio);
			if (modoCrud == ParametroCrud.getModoCrudNovo()
					|| modoCrud == ParametroCrud.getModoCrudAlterar()) {
				menuPai.exibirTime(timeSelecionado);
			} else if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
				menuPai.home();
			}
		}
	}

	public void adicionarJogadorTitular() {
		Long qtd = JogadorDao.getQtdeJogadorTitular(timeSelecionado
				.getCodigoTime());
		if (qtd < 5) {
			Jogador jogadorSelecionado = JogadorDao.getJogador(Integer
					.parseInt(String.valueOf(tabela.getValueAt(
							tabela.getSelectedRow(), 0))));
			EntityManagerLocal.begin();
			jogadorSelecionado.setTitular(true);
			EntityManagerLocal.merge(jogadorSelecionado);
			EntityManagerLocal.commit();
		} else {
			Menssage.setMenssage(
					"Maxímo de Jogador",
					"Time já possui 5 Jogadores Titulares\nPara adiconar outro Jogador\nDevera remover um Titular!",
					ParametroCrud.getModoErro(), meio);
		}
		atualizarTabela();
	}

	public void removerJogadorTitular() {
		Jogador jogadorSelecionado = JogadorDao.getJogador(Integer
				.parseInt(String.valueOf(tabelaTitular.getValueAt(
						tabelaTitular.getSelectedRow(), 0))));
		EntityManagerLocal.begin();
		jogadorSelecionado.setTitular(false);
		EntityManagerLocal.merge(jogadorSelecionado);
		EntityManagerLocal.commit();
		atualizarTabela();
	}

	public JTextField getTxDescricao() {
		return txDescricao;
	}

	public void setTxDescricao(JTextField txDescricao) {
		this.txDescricao = txDescricao;
	}
}
