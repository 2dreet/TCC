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
import utilitario.Computador;
import utilitario.GerenciadorGupos;
import utilitario.MascaraCrud;
import utilitario.ParametroCrud;
import utilitario.Parametros;
import utilitario.UtilitarioCrud;
import utilitario.UtilitarioImagem;
import utilitario.UtilitarioTabela;
import utilitario.UtilitarioTela;
import utilitario.ValidadorCrud;

import javax.swing.JButton;

import dao.BanimentoDao;
import dao.CampeonatoTimeDao;
import dao.ChaveDao;
import dao.EntityManagerLocal;
import dao.GrupoDao;
import dao.JogadorDao;
import dao.ModalidadeDao;
import dao.PermissaoDao;
import dao.TimeDao;
import dialog.DialogLocalizarJogador;
import dialog.DialogLocalizarTime;
import entidade.Campeonato;
import entidade.CampeonatoTime;
import entidade.Grupo;
import entidade.Jogador;
import entidade.Pc;
import entidade.Time;
import entidade.Usuario;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import componente.ComboBox;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	private ComboBox cbChave;
	private List<CampeonatoTime> listaTime;
	private Time timeSelecionado;
	private JTable tabela;
	private Object[][] colunas = new Object[][] { new String[] { "Logo" },
			new String[] { "Código" }, new String[] { "Nome" } };

	/**
	 * Create the panel.
	 */
	public CrudCampeonato(Campeonato campeonato, int modoCrud,
			MenuCampeonato menuPai) {
		this.menuPai = menuPai;
		listaTime = new ArrayList<CampeonatoTime>();
		timeSelecionado = null;
		setSize(UtilitarioTela.getTamanhoMeio());
		setLayout(null);
		setBackground(null);

		campeonatoSelecionado = campeonato;

		header = new JPanel();
		header.setSize(650, 30);
		header.setLocation((getWidth() / 2) - 325, 10);
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
		meio.setSize(650, getHeight() - 50);
		meio.setLocation((getWidth() / 2) - 325, 40);
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

		JLabel lbModalidade = new JLabel("Modalidade :");
		lbModalidade.setBounds(20, 60, 100, 20);
		lbModalidade.setFont(UtilitarioTela.getFont(14));
		lbModalidade.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbModalidade);

		cbModalidade = new ComboBox(new Dimension(150, 25));
		cbModalidade.setModel(new DefaultComboBoxModel(ModalidadeDao
				.getVetorModalidade()));
		cbModalidade.setLocation(130, 60);
		meio.add(cbModalidade);

		JLabel lbChave = new JLabel("Chave :");
		lbChave.setBounds(20, 100, 100, 20);
		lbChave.setFont(UtilitarioTela.getFont(14));
		lbChave.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbChave);

		cbChave = new ComboBox(new Dimension(150, 25));
		cbChave.setModel(new DefaultComboBoxModel(ChaveDao.getVetorChave()));
		cbChave.setLocation(130, 100);
		meio.add(cbChave);

		String texto = "";
		if (modoCrud == ParametroCrud.getModoCrudAlterar()) {
			texto = "Alterar";
		} else if (modoCrud == ParametroCrud.getModoCrudNovo()) {
			texto = "Cadastrar";
		}

		if (modoCrud != ParametroCrud.getModoVisualizar()) {
			JButton btSalvar = new JButton(texto);
			btSalvar.setBounds(175, meio.getHeight() - 75, 150, 35);
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
		msg.setSize(630, 35);
		msg.setLocation(5, 180);
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
		lblMsg.setBounds(0, 0, 630, 35);
		lblMsg.setFont(UtilitarioTela.getFont(14));
		lblMsg.setForeground(UtilitarioTela.getFontColorCrud());
		msg.add(lblMsg);
		msg.setBackground(UtilitarioTela.getColorErro());
	}

	public void limpaErro() {
		msg.removeAll();
		lblMsg = new JLabel("");
		lblMsg.setHorizontalAlignment(SwingConstants.CENTER);
		lblMsg.setBounds(0, 0, 630, 35);
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
				msgErro("Campo Descrição é Obrigatório!");
				return false;
			}

			String modalidade = String.valueOf(cbModalidade.getSelectedItem());
			
			
			if (String.valueOf(cbModalidade.getSelectedItem()) == null
					|| String.valueOf(cbModalidade.getSelectedItem()).equals("null") 
					|| String.valueOf(cbModalidade.getSelectedItem()).trim()
							.isEmpty()) {
				msgErro("Casdatrar Modalidade em Casdatro de modalidade");
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
		} else {
			if (campeonatoSelecionado.getDataIncio() == null) {
				JButton btIniciar = new JButton("Iniciar Campeonato");
				btIniciar.setBounds(175, meio.getHeight() - 70, 150, 35);
				btIniciar.setFont(UtilitarioTela.getFont(14));
				btIniciar.setFocusPainted(false);
				btIniciar.setBackground(UtilitarioTela.getColorCrud(modoCrud));
				btIniciar.setIcon(UtilitarioCrud.getIconeCrud(modoCrud));
				meio.add(btIniciar);
				btIniciar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (campeonatoSelecionado.getChave().getCodigoChave() == 3) {
							// Grupo
							if (listaTime.size() >= 12) {
								if (GerenciadorGupos.gerarGrupos(campeonatoSelecionado)) {
									List<Grupo> listaGrupo = GrupoDao.getListaGrupo(campeonatoSelecionado.getCodigoCampeonato());
									
								}
							} else {
								Menssage.setMenssage("Times insuficientes",
										"Deve adicionar Mais Times para iniciar o campeonato!",
										ParametroCrud.getModoCrudDeletar(),
										meio);
							}
						}
					}
				});
			} else {
				JButton btCancelar = new JButton("Cancelar Campeonato");
				btCancelar.setBounds(175, meio.getHeight() - 70, 150, 35);
				btCancelar.setFont(UtilitarioTela.getFont(14));
				btCancelar.setFocusPainted(false);
				btCancelar.setBackground(UtilitarioTela.getColorCrud(modoCrud));
				btCancelar.setIcon(UtilitarioCrud.getIconeCrud(modoCrud));
				meio.add(btCancelar);
				btCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

					}
				});
			}
		}

		int linha = 20;
		JLabel lbCodigo = new JLabel("Código :");
		lbCodigo.setBounds(20, linha, 100, 20);
		lbCodigo.setFont(UtilitarioTela.getFont(14));
		lbCodigo.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbCodigo);
		JLabel lbCodigoV = new JLabel(String.valueOf(campeonatoSelecionado
				.getCodigoCampeonato()));
		lbCodigoV.setBounds(130, linha, 300, 20);
		lbCodigoV.setFont(UtilitarioTela.getFont(14));
		lbCodigoV.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbCodigoV);

		linha += 30;
		JLabel lbNome = new JLabel("Descrição :");
		lbNome.setBounds(20, linha, 100, 20);
		lbNome.setFont(UtilitarioTela.getFont(14));
		lbNome.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbNome);
		JLabel lbNomeV = new JLabel(campeonatoSelecionado.getDescricao());
		lbNomeV.setBounds(130, linha, 300, 20);
		lbNomeV.setFont(UtilitarioTela.getFont(14));
		lbNomeV.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbNomeV);

		linha += 30;
		JLabel lbModalidade = new JLabel("Modalidade :");
		lbModalidade.setBounds(20, linha, 100, 20);
		lbModalidade.setFont(UtilitarioTela.getFont(14));
		lbModalidade.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbModalidade);
		JLabel lbModalidadeV = new JLabel(campeonatoSelecionado.getModalidade()
				.getDescricao());
		lbModalidadeV.setBounds(130, linha, 300, 20);
		lbModalidadeV.setFont(UtilitarioTela.getFont(14));
		lbModalidadeV.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbModalidadeV);

		linha += 70;
		tabela = new JTable();
		tabela.setModel(UtilitarioTabela.getModelo(colunas));

		TableColumnModel tcm = tabela.getColumnModel();
		TextoIconeCell renderer = new TextoIconeCell();
		tcm.getColumn(0).setCellRenderer(renderer);
		tcm.getColumn(0).setPreferredWidth(50);
		tcm.getColumn(0).setMinWidth(50);
		tcm.getColumn(0).setResizable(false);
		tcm.getColumn(1).setPreferredWidth(120);
		tcm.getColumn(1).setMinWidth(120);
		tcm.getColumn(1).setResizable(false);
		tcm.getColumn(2).setPreferredWidth(465);
		tcm.getColumn(2).setMinWidth(465);
		tcm.getColumn(2).setResizable(false);

		UtilitarioTabela.pintarColona(UtilitarioTabela.getFundoHeaderPadrao(),
				UtilitarioTabela.getFontColotHeaderPadrao(), tcm, colunas);
		UtilitarioTabela.pintarLinha(new Color(255, 153, 153), Color.black,
				tabela);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.setPreferredScrollableViewportSize(tabela.getPreferredSize());
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.setRowHeight(50);
		tabela.setFont(UtilitarioTela.getFont(14));
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(5, linha, 640, 250);
		scroll.setBackground(Color.red);
		meio.add(scroll);

		if (modoCrud == ParametroCrud.getModoVisualizar()) {
			JButton btAddTime = new JButton("Adicionar Time");
			btAddTime.setBounds(80, meio.getHeight() - 70, 220, 35);
			btAddTime.setFont(UtilitarioTela.getFont(14));
			btAddTime.setFocusPainted(false);
			btAddTime.setBackground(UtilitarioTela.getColorCrud(ParametroCrud
					.getModoCrudNovo()));
			btAddTime.setIcon((new ImageIcon(CrudTime.class
					.getResource("/imagem/crud/addJogSelect.png"))));
			meio.add(btAddTime);
			btAddTime.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					DialogLocalizarTime.localizarTime(meio,
							campeonatoSelecionado);
					if (DialogLocalizarTime.getTimeSelecionado() != null) {
						Time time = DialogLocalizarTime.getTimeSelecionado();
						boolean confirmado = true;
						MenssageConfirmacao.setMenssage(
								"Adicionar Time no Campeonato",
								"Deseja Adicionar Esse Time no Campeonato?\nTime: "
										+ time.getDescricao(), modoCrud, meio);
						confirmado = MenssageConfirmacao.isConfirmado();
						if (confirmado) {
							EntityManagerLocal.begin();
							CampeonatoTime campTime = new CampeonatoTime();
							campTime.setTime(time);
							campTime.setCampeonato(campeonatoSelecionado);
							campTime.setDataInscricao(new Date());
							EntityManagerLocal.merge(campTime);
							EntityManagerLocal.commit();
						}
						atualizarTabela();
					}
				}
			});

			JButton btRemoverTime = new JButton("Remover Time");
			btRemoverTime.setBounds(360, meio.getHeight() - 70, 220, 35);
			btRemoverTime.setFont(UtilitarioTela.getFont(14));
			btRemoverTime.setFocusPainted(false);
			btRemoverTime.setBackground(UtilitarioTela
					.getColorCrud(ParametroCrud.getModoCrudDeletar()));
			btRemoverTime.setIcon((new ImageIcon(CrudTime.class
					.getResource("/imagem/crud/delJogSelect.png"))));
			meio.add(btRemoverTime);
			btRemoverTime.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (tabela.getRowCount() > 0) {
						if (tabela.getSelectedRow() > -1) {
							CampeonatoTime campTime = CampeonatoTimeDao.getCampeonatoTime(Integer
									.parseInt(String.valueOf(tabela.getValueAt(
											tabela.getSelectedRow(), 0))));
							boolean confirmado = true;
							MenssageConfirmacao
									.setMenssage("Remover Time do Campeonato",
											"Deseja Remover Esse Time do Campeonato?\nTime: "
													+ campTime.getTime()
															.getDescricao(),
											modoCrud, meio);
							confirmado = MenssageConfirmacao.isConfirmado();
							if (confirmado) {
								EntityManagerLocal.begin();
								EntityManagerLocal.delete(campTime);
								EntityManagerLocal.commit();
							}
						} else {
							Menssage.setMenssage("Time não Selecionado",
									"Deve selecionar um Time!",
									ParametroCrud.getModoCrudDeletar(), meio);
						}
					} else {
						Menssage.setMenssage("Time não Selecionado",
								"Deve selecionar um Time!",
								ParametroCrud.getModoCrudDeletar(), meio);
					}
					atualizarTabela();
				}
			});
		}
		atualizarTabela();
	}

	public void atualizarTabela() {
		listaTime = CampeonatoTimeDao
				.getListaCampeonatoTimeCamp(campeonatoSelecionado
						.getCodigoCampeonato());
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.setNumRows(0);
		if (listaTime != null) {
			for (CampeonatoTime t : listaTime) {
				modelo.addRow(new Object[] {
						new DadoComIcone("", UtilitarioImagem.converterImage(t
								.getTime().getLogo())),
						String.valueOf(t.getTime().getCodigoTime()),
						t.getTime().getDescricao() });

			}
		} else {
			listaTime = new ArrayList<CampeonatoTime>();
		}
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
				campeonato.setDataCadastro(new Date());
				campeonato.setModalidade(ModalidadeDao.getModalidadeNome(String
						.valueOf(cbModalidade.getSelectedItem())));
				campeonato.setChave(ChaveDao.getChaveNome(String
						.valueOf(cbChave.getSelectedItem())));
				campeonato.setAtivo(true);
				EntityManagerLocal.persist(campeonato);
				campeonatoSelecionado = campeonato;

			} else if (modoCrud == ParametroCrud.getModoCrudAlterar()) {
				modo = "Alteração de Campeonato";
				menssage = "Campeonato Alterado com Sucesso!";
				campeonatoSelecionado.setDescricao(txDescricao.getText());
				campeonatoSelecionado.setModalidade(ModalidadeDao
						.getModalidadeNome(String.valueOf(cbModalidade
								.getSelectedItem())));
				campeonatoSelecionado.setChave(ChaveDao.getChaveNome(String
						.valueOf(cbChave.getSelectedItem())));
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
