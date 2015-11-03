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
import utilitario.GerenciadorPartida;
import utilitario.Login;
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
import dao.JogadorBanimentoDao;
import dao.JogadorDao;
import dao.ModalidadeDao;
import dao.PermissaoDao;
import dao.TimeDao;
import dialog.DialogDesqualificarTime;
import dialog.DialogLocalizarJogador;
import dialog.DialogLocalizarTime;
import dialog.DialogTimesDesqualificados;
import entidade.Campeonato;
import entidade.CampeonatoTime;
import entidade.Chave;
import entidade.Grupo;
import entidade.Jogador;
import entidade.Modalidade;
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
import javax.swing.ComboBoxModel;
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
	private List<Time> timeSelecionado;
	private JTable tabela;
	private JPanel lbQtdTimes;
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
		DefaultComboBoxModel comboModel = (DefaultComboBoxModel) cbModalidade.getModel();
		comboModel.removeAllElements();
		List<Modalidade>list = ModalidadeDao.getListaModalidade();
		for (Modalidade m : list){
			comboModel.addElement(m);
		}

		cbModalidade.setLocation(130, 60);
		meio.add(cbModalidade);

		JLabel lbChave = new JLabel("Chave :");
		lbChave.setBounds(20, 100, 100, 20);
		lbChave.setFont(UtilitarioTela.getFont(14));
		lbChave.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbChave);

		cbChave = new ComboBox(new Dimension(150, 25));
		DefaultComboBoxModel comboChave = (DefaultComboBoxModel) cbChave.getModel();
		List<Chave>listChave = ChaveDao.getListaChave();
		comboChave.removeAllElements();
		for (Chave c : listChave){
			comboChave.addElement(c);
		}
		
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
		cbChave.setSelectedItem(campeonatoSelecionado.getChave());
		cbModalidade.setSelectedItem(campeonatoSelecionado.getModalidade());
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
					|| String.valueOf(cbModalidade.getSelectedItem()).equals(
							"null")
					|| String.valueOf(cbModalidade.getSelectedItem()).trim()
							.isEmpty()) {
				msgErro("Casdatrar Modalidade em Casdatro de modalidade");
				return false;
			}

			if (campeonatoSelecionado != null
					&& campeonatoSelecionado.getDataInicio() != null
					&& campeonatoSelecionado.getDataFim() == null) {
				Menssage.setMenssage("Campeonato Iniciado",
						"Campeonato Iniciado não pode ser Deletado",
						ParametroCrud.getModoCrudDeletar(), meio);
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
			if (campeonatoSelecionado != null
					&& campeonatoSelecionado.getDataInicio() == null) {
				JButton btIniciar = new JButton("Iniciar Campeonato");
				btIniciar.setBounds(meio.getWidth() - 215, 5, 210, 35);
				btIniciar.setFont(UtilitarioTela.getFont(14));
				btIniciar.setFocusPainted(false);
				btIniciar.setBackground(UtilitarioTela
						.getColorCrud(ParametroCrud.getModoCrudAlterar()));
				meio.add(btIniciar);
				btIniciar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (campeonatoSelecionado.getChave().getCodigoChave() == 3) {
							// Grupo
							if (listaTime.size() >= 12) {
								if (GerenciadorGupos
										.gerarGrupos(campeonatoSelecionado)) {
									Menssage.setMenssage(
											"Partidas Geradas",
											"Partidas Geradas\nClicar em partidas para ver as partidas!",
											ParametroCrud.getModoCrudDeletar(),
											meio);
									menuPai.btPartida.setEnabled(true);
									iniciarCampeonato();
								}
							} else {
								Menssage.setMenssage(
										"Times insuficientes",
										"Deve adicionar no Minimo 12 Times para iniciar o campeonato!",
										ParametroCrud.getModoCrudDeletar(),
										meio);
							}
						} else if (campeonatoSelecionado.getChave()
								.getCodigoChave() == 2
								|| campeonatoSelecionado.getChave()
										.getCodigoChave() == 1) {
							// Winner Lower MATA MATA
							if (listaTime.size() >= 2) {
								if (GerenciadorPartida
										.adicionarPatidas(campeonatoSelecionado)) {
									Menssage.setMenssage(
											"Partidas Geradas",
											"Partidas Geradas\nClicar em partidas para ver as partidas!",
											ParametroCrud.getModoCrudDeletar(),
											meio);
									menuPai.btPartida.setEnabled(true);
									iniciarCampeonato();
								} else {
									Menssage.setMenssage("Erro",
											"Erro ao gerrar partidas!",
											ParametroCrud.getModoCrudDeletar(),
											meio);
								}
							} else {
								Menssage.setMenssage(
										"Times insuficientes",
										"Deve adicionar no Minimo 2 Times para iniciar o campeonato!",
										ParametroCrud.getModoCrudDeletar(),
										meio);
							}
						}
					}
				});
			} else if (campeonatoSelecionado != null
					&& campeonatoSelecionado.getDataInicio() != null
					&& campeonatoSelecionado.getDataFim() == null) {
				JButton btDesqualificar = new JButton("Desqualificar Time");
				btDesqualificar.setBounds(meio.getWidth() - 215, 5, 210, 35);
				btDesqualificar.setFont(UtilitarioTela.getFont(14));
				btDesqualificar.setFocusPainted(false);
				btDesqualificar.setBackground(UtilitarioTela
						.getColorCrud(ParametroCrud.getModoCrudAlterar()));
				meio.add(btDesqualificar);
				btDesqualificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (tabela.getSelectedRowCount() == 1) {
							String codigoTime = (String) tabela.getValueAt(
									tabela.getSelectedRow(), 1);
							Time timeSelecionado = TimeDao.getTime(Integer
									.parseInt(codigoTime));
							CampeonatoTime ct = CampeonatoTimeDao
									.getCampeonatoTime(timeSelecionado
											.getCodigoTime(),
											campeonatoSelecionado
													.getCodigoCampeonato());

							if (ct.isDesqualificado()) {
								Menssage.setMenssage("Time",
										"O Time já Está desqualificado!",
										ParametroCrud.getModoCrudDeletar(),
										meio);
							} else {

								DialogDesqualificarTime dd = new DialogDesqualificarTime();

								dd.desqualificar(timeSelecionado,
										campeonatoSelecionado,
										CrudCampeonato.this);
								if (dd.getConfirmado()) {
									Menssage.setMenssage("Time",
											"O Time Foi Desqualificado!",
											ParametroCrud.getModoCrudAlterar(),
											meio);
								}
							}
						} else {
							Menssage.setMenssage("Time",
									"Deve Selecionar apenas 1 Time por vez!",
									ParametroCrud.getModoCrudDeletar(), meio);
						}
					}
				});

				JButton btTimesDesqualificados = new JButton(
						"Times Desqualificados");
				btTimesDesqualificados.setBounds(meio.getWidth() - 215, 55,
						210, 35);
				btTimesDesqualificados.setFont(UtilitarioTela.getFont(14));
				btTimesDesqualificados.setFocusPainted(false);
				btTimesDesqualificados.setBackground(UtilitarioTela
						.getColorCrud(ParametroCrud.getModoCrudDeletar()));
				meio.add(btTimesDesqualificados);
				btTimesDesqualificados.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						DialogTimesDesqualificados dt = new DialogTimesDesqualificados();
						dt.localizarTime(meio, campeonatoSelecionado);
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

		linha += 30;
		JLabel lbChave = new JLabel("Chave :");
		lbChave.setBounds(20, linha, 100, 20);
		lbChave.setFont(UtilitarioTela.getFont(14));
		lbChave.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbChave);
		JLabel lbChaveV = new JLabel(campeonatoSelecionado.getChave()
				.getDescricao());
		lbChaveV.setBounds(130, linha, 160, 20);
		lbChaveV.setFont(UtilitarioTela.getFont(14));
		lbChaveV.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbChaveV);

		lbQtdTimes = new JPanel();
		lbQtdTimes.setBounds(330, linha, 100, 20);
		lbQtdTimes.setLayout(null);
		lbQtdTimes.setBackground(null);
		meio.add(lbQtdTimes);

		linha += 30;
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
		tcm.getColumn(2).setPreferredWidth(450);
		tcm.getColumn(2).setMinWidth(450);
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
		tabela.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(5, linha, 640, 300);
		meio.add(scroll);

		if (modoCrud == ParametroCrud.getModoVisualizar()
				&& campeonatoSelecionado != null
				&& campeonatoSelecionado.getDataInicio() == null) {
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
						List<Time> time = DialogLocalizarTime
								.getTimeSelecionado();
						boolean confirmado = true;

						MenssageConfirmacao.setMenssage(
								"Adicionar Time no Campeonato",
								"Deseja Adicionar o(os) Time no Campeonato? ",
								modoCrud, meio);
						confirmado = MenssageConfirmacao.isConfirmado();
						if (confirmado) {
							EntityManagerLocal.begin();
							for (Time t : time) {
								CampeonatoTime campTime = new CampeonatoTime();
								campTime.setTime(t);
								campTime.setCampeonato(campeonatoSelecionado);
								campTime.setDataInscricao(new Date());
								EntityManagerLocal.merge(campTime);
							}
							EntityManagerLocal.commit();
						}
						atualizarTabela();
						atualizarQtdTimes();
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
							int[] selecao = tabela.getSelectedRows();

							boolean confirmado = true;
							MenssageConfirmacao.setMenssage(
									"Remover Time do Campeonato",
									"Deseja Remover o(os) Time do Campeonato?",
									modoCrud, meio);
							confirmado = MenssageConfirmacao.isConfirmado();
							if (confirmado) {
								EntityManagerLocal.begin();
								for (int i = 0; i < selecao.length; i++) {
									CampeonatoTime campTime = CampeonatoTimeDao.getCampeonatoTime(
											Integer.parseInt(String
													.valueOf(tabela.getValueAt(
															selecao[i], 1))),
											campeonatoSelecionado
													.getCodigoCampeonato());
									EntityManagerLocal.delete(campTime);
								}
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
					atualizarQtdTimes();
				}
			});
		}
		atualizarTabela();
		atualizarQtdTimes();
	}

	public void atualizarQtdTimes() {
		lbQtdTimes.removeAll();
		JLabel lblqtdTimes = new JLabel("Times : " + listaTime.size());
		lblqtdTimes.setBounds(0, 0, 100, 20);
		lblqtdTimes.setFont(UtilitarioTela.getFont(14));
		lblqtdTimes.setForeground(UtilitarioTela.getFontColorCrud());
		lbQtdTimes.add(lblqtdTimes);
		lbQtdTimes.repaint();
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
				campeonato.setModalidade((Modalidade) cbModalidade.getSelectedItem());
				campeonato.setChave((Chave)cbChave.getSelectedItem());
				campeonato.setAtivo(true);
				campeonato.setFuncionario(Login.usuario);
				EntityManagerLocal.persist(campeonato);
				campeonatoSelecionado = campeonato;

			} else if (modoCrud == ParametroCrud.getModoCrudAlterar()) {
				modo = "Alteração de Campeonato";
				menssage = "Campeonato Alterado com Sucesso!";
				campeonatoSelecionado.setDescricao(txDescricao.getText());
				campeonatoSelecionado.setModalidade((Modalidade) cbModalidade.getSelectedItem());
				campeonatoSelecionado.setChave((Chave) cbChave.getSelectedItem());
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

	public void iniciarCampeonato() {
		EntityManagerLocal.begin();
		campeonatoSelecionado.setDataInicio(new Date());
		EntityManagerLocal.merge(campeonatoSelecionado);
		EntityManagerLocal.commit();
		menuPai.liberarCrud();
		menuPai.abreMenuPartida(campeonatoSelecionado);
	}

	public boolean validarCampeonato() {
		if (listaTime == null || listaTime.size() < 12) {
			Menssage.setMenssage("valor de times invalido",
					"Adicionar No minimo 12 times no campeonato",
					ParametroCrud.getModoCrudDeletar(), meio);
			return false;
		}

		for (CampeonatoTime ct : listaTime) {
			List<Jogador> listaJogador = JogadorDao
					.getListaJogadorTitularTime(ct.getTime().getCodigoTime());
			if (listaJogador == null || listaJogador.size() < 5) {
				Menssage.setMenssage("valor de Jogadores invalido",
						"Adicionar 5 Jogadores Titulares para o Time\n"
								+ ct.getTime().getDescricao(),
						ParametroCrud.getModoCrudDeletar(), meio);
				return false;
			}
			for (Jogador jogador : listaJogador) {
				if (JogadorBanimentoDao.jogadorBanido(jogador
						.getCodigoJogador())) {
					Menssage.setMenssage("Jogador Banido ", "Jogador "
							+ jogador.getUsuario().getNome() + " do Time "
							+ ct.getTime().getDescricao()
							+ "\nEstá Banido deve ser subistituido!",
							ParametroCrud.getModoCrudDeletar(), meio);
					return false;
				}
			}

		}

		return true;
	}

	public void setTxDescricao(JTextField txDescricao) {
		this.txDescricao = txDescricao;
	}
}
