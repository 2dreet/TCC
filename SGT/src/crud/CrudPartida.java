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
import tela.HomeFuncionario;
import utilitario.BordaEscura;
import utilitario.BordaSombreada;
import utilitario.Computador;
import utilitario.GerenciadorPartida;
import utilitario.MascaraCrud;
import utilitario.MoverArquivo;
import utilitario.ParametroCrud;
import utilitario.Parametros;
import utilitario.UtilitarioCrud;
import utilitario.UtilitarioImagem;
import utilitario.UtilitarioLogo;
import utilitario.UtilitarioTabela;
import utilitario.UtilitarioTela;
import utilitario.ValidadorCrud;

import javax.swing.JButton;

import dao.CampeonatoDao;
import dao.CampeonatoTimeDao;
import dao.ClassificacaoDao;
import dao.EntityManagerLocal;
import dao.JogadorBanimentoDao;
import dao.JogadorDao;
import dao.PartidaDao;
import dao.PermissaoDao;
import dao.TimeDao;
import dialog.DialogCrudPartida;
import dialog.DialogLocalizarJogador;
import entidade.Campeonato;
import entidade.CampeonatoTime;
import entidade.Classificacao;
import entidade.Jogador;
import entidade.Partida;
import entidade.PcPartida;
import entidade.Time;
import entidade.TimePartida;
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

import menu.MenuCampeonato;
import menu.MenuJogador;
import menu.MenuTime;

public class CrudPartida extends JPanel {
	private JLabel lblMsg;
	private MenuCampeonato menuPai;
	private Campeonato campeonatoSelecionado;
	private JPanel header;
	private JLabel lblHeader;
	private JPanel meio;
	private JPanel meio2;
	private Partida partida;
	private JPanel meioT1;
	private JButton btProximaPartida;
	private JTable tabela;
	private Object[][] colunas = new Object[][] { new String[] { "Placar" },
			new String[] { "Time Vencedor" }, new String[] { "Placar" },
			new String[] { "Time Perdedor" }, new String[] { "Chave" } };
	private JButton btTabelaGrupo;
	private JButton btTabelaMataMata;
	private JButton btTabelaWinnerLower;
	private List<Partida> listaPartida;
	private TelaWinnerLower telaWinnerLower;
	private TelaGrupo telaGrupo;
	
	/**
	 * Create the panel.
	 */
	public CrudPartida(Campeonato campeonato, MenuCampeonato menuPai) {

		this.menuPai = menuPai;
		setSize(UtilitarioTela.getTamanhoMeio());
		setLayout(null);
		setBackground(null);
		listaPartida = new ArrayList<Partida>();
		campeonatoSelecionado = campeonato;
		header = new JPanel();
		header.setSize(getWidth(), 30);
		header.setLocation(0, 0);
		header.setLayout(null);
		header.setBackground(UtilitarioTela.getColorCrud(ParametroCrud
				.getModoVisualizar()));
		header.setBorder(null);
		add(header);

		telaWinnerLower = new TelaWinnerLower(campeonato);
		telaGrupo = new TelaGrupo(campeonato);
		
		String textoHeader = "Partidas";

		lblHeader = new JLabel(textoHeader);
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setBounds(0, 0, header.getWidth(), header.getHeight());
		lblHeader.setFont(UtilitarioTela.getFont(14));
		lblHeader.setForeground(UtilitarioTela.getFontColorCrud());
		header.add(lblHeader);

		meio = new JPanel();
		meio.setSize(getWidth(), getHeight() - 30);
		meio.setLocation(0, 30);
		meio.setLayout(null);
		meio.setBackground(UtilitarioTela.getFundoCrud());
		meio.setBorder(new BordaSombreada(ParametroCrud.getModoVisualizar()));
		add(meio);

		meio2 = new JPanel();
		meio2.setSize(getWidth() - 5, getHeight() - 175);
		meio2.setLocation(2, 140);
		meio2.setLayout(null);
		meio2.setBackground(UtilitarioTela.getFundoCrud());
		meio2.setBorder(new BordaSombreada(true, false, false, false));
		meio.add(meio2);

		btProximaPartida = new JButton("Iniciar Proxima Partida");
		btProximaPartida.setSize(500, 30);
		btProximaPartida.setLocation((meio.getWidth() / 2) - 250, 20);
		btProximaPartida.setBackground(UtilitarioTela.getFontColorPadrao());
		btProximaPartida.setFocusPainted(false);
		btProximaPartida
				.setBorder(new BordaSombreada(false, true, false, false));
		btProximaPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirDialog();
			}
		});
		meio.add(btProximaPartida);

		btTabelaGrupo = new JButton("Tabela de Grupo");
		btTabelaGrupo.setSize(200, 30);
		btTabelaGrupo.setLocation(10, 10);
		btTabelaGrupo.setFocusPainted(false);
		btTabelaGrupo.setBackground(UtilitarioTela.getFontColorCrud());
		btTabelaGrupo.setForeground(UtilitarioTela.getFontColorPadrao());
		btTabelaGrupo.setBorder(new BordaSombreada(false, true, false, false));
		btTabelaGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (telaGrupo.isVisible()) {
					telaGrupo.atualizarTela(campeonatoSelecionado);
				} else {
					telaGrupo.setVisible(true);
					telaGrupo.atualizarTela(campeonatoSelecionado);
				}
			}
		});
		meio.add(btTabelaGrupo);

		btTabelaMataMata = new JButton("Tabela de Mata-Mata");
		btTabelaMataMata.setSize(200, 30);
		btTabelaMataMata.setLocation(10, 50);
		btTabelaMataMata.setBackground(UtilitarioTela.getFontColorCrud());
		btTabelaMataMata.setForeground(UtilitarioTela.getFontColorPadrao());
		btTabelaMataMata.setFocusPainted(false);
		btTabelaMataMata
				.setBorder(new BordaSombreada(false, true, false, false));
		btTabelaMataMata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (telaWinnerLower.isVisible()) {
					telaWinnerLower.atualizarTela(campeonatoSelecionado);
				} else {
					telaWinnerLower.setVisible(true);
					telaWinnerLower.atualizarTela(campeonatoSelecionado);
				}
			}
		});
		meio.add(btTabelaMataMata);

		btTabelaWinnerLower = new JButton("Tabela de Winner Lower");
		btTabelaWinnerLower.setSize(200, 30);
		btTabelaWinnerLower.setLocation(10, 90);
		btTabelaWinnerLower.setBackground(UtilitarioTela.getFontColorCrud());
		btTabelaWinnerLower.setForeground(UtilitarioTela.getFontColorPadrao());
		btTabelaWinnerLower.setFocusPainted(false);
		btTabelaWinnerLower.setBorder(new BordaSombreada(false, true, false,
				false));
		btTabelaWinnerLower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (telaWinnerLower.isVisible()) {
					telaWinnerLower.atualizarTela(campeonatoSelecionado);
				} else {
					telaWinnerLower.partidaEmAndamento = partida;
					telaWinnerLower.setVisible(true);
					telaWinnerLower.atualizarTela(campeonatoSelecionado);
				}
			}
		});
		meio.add(btTabelaWinnerLower);

		meioT1 = new JPanel();
		meioT1.setSize(500, 70);
		meioT1.setLayout(null);
		meioT1.setLocation((meio.getWidth() / 2) - 250, 50);
		meioT1.setBackground(UtilitarioTela.getBtnFundo(false));
		meioT1.setBorder(new BordaSombreada(new Color(46, 49, 56), new Color(
				102, 102, 102)));
		meio.add(meioT1);

		JPanel headerPartida = new JPanel();
		headerPartida.setSize(600, 30);
		headerPartida.setLocation((meio2.getWidth() / 2) - 295, 2);
		headerPartida.setLayout(null);
		headerPartida.setBackground(UtilitarioTela.getColorCrud(ParametroCrud
				.getModoVisualizar()));
		headerPartida.setBorder(null);
		meio2.add(headerPartida);

		JLabel lblHeaderPartida = new JLabel("Partidas Realizadas");
		lblHeaderPartida.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeaderPartida.setBounds(0, 0, 600, 30);
		lblHeaderPartida.setFont(UtilitarioTela.getFont(14));
		lblHeaderPartida.setForeground(UtilitarioTela.getFontColorCrud());
		headerPartida.add(lblHeaderPartida);

		tabela = new JTable();
		tabela.setModel(UtilitarioTabela.getModelo(colunas));

		TableColumnModel tcm = tabela.getColumnModel();
		TextoIconeCell renderer = new TextoIconeCell();
		tcm.getColumn(0).setCellRenderer(renderer);
		tcm.getColumn(0).setPreferredWidth(50);
		tcm.getColumn(0).setMinWidth(50);
		tcm.getColumn(0).setResizable(false);
		tcm.getColumn(1).setPreferredWidth(200);
		tcm.getColumn(1).setMinWidth(200);
		tcm.getColumn(1).setResizable(false);
		tcm.getColumn(2).setPreferredWidth(50);
		tcm.getColumn(2).setMinWidth(50);
		tcm.getColumn(2).setResizable(false);
		tcm.getColumn(3).setCellRenderer(renderer);
		tcm.getColumn(3).setPreferredWidth(200);
		tcm.getColumn(3).setMinWidth(200);
		tcm.getColumn(3).setResizable(false);
		tcm.getColumn(4).setPreferredWidth(80);
		tcm.getColumn(4).setMinWidth(8);
		tcm.getColumn(4).setResizable(false);

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
		scroll.setBounds((meio2.getWidth() / 2) - 295, 32, 600, 350);
		meio2.add(scroll);

		mostrarProximaPartida();

	}

	public void abrirDialog() {
		Computador.stopPCconectado();
		DialogCrudPartida dcp = new DialogCrudPartida(partida, this, meio);
		mostrarProximaPartida();
	}

	public void mudarChaveHeader() {
		if (campeonatoSelecionado.getDataFim() != null) {
			meioT1.removeAll();
			Classificacao classificacao = ClassificacaoDao
					.getClassificacaoPorCampeonato(campeonatoSelecionado);
			JLabel lbLgMedalha = new JLabel(new ImageIcon(
					CrudPartida.class.getResource("/imagem/medalha.png")));
			lbLgMedalha.setBounds(10, 10, 50, 50);
			meioT1.add(lbLgMedalha);

			JLabel lbLg = new JLabel(
					UtilitarioImagem.converterImage(classificacao
							.getTimePrimeiro().getLogo()));
			lbLg.setBounds(70, 10, 50, 50);
			lbLg.setFont(UtilitarioTela.getFont(14));
			lbLg.setForeground(UtilitarioTela.getFontColorPadrao());
			meioT1.add(lbLg);

			JLabel lbNome = new JLabel(classificacao.getTimePrimeiro()
					.getDescricao());
			lbNome.setBounds(130, 10, 150, 50);
			lbNome.setFont(UtilitarioTela.getFont(14));
			lbNome.setForeground(UtilitarioTela.getFontColorPadrao());
			
			meioT1.add(lbNome);
			meioT1.repaint();
			btProximaPartida.setVisible(false);
			meio.repaint();
		}
		
	}

	public boolean proximaLower() {
		if (campeonatoSelecionado.getChave().getCodigoChave() == 2) {
			Partida partidaAux = PartidaDao
					.getProximaPartidaPartidaLower(campeonatoSelecionado);
			if (partidaAux != null) {
				partida = partidaAux;
				return true;
			}
		}
		return false;
	}

	public boolean iniciarWinnerLowerMataMata() {
		List<Partida> auxLista = PartidaDao
				.getPartidasNaoFinalizadasIniciadasGrupo(campeonatoSelecionado
						.getCodigoCampeonato());
		if (auxLista != null && auxLista.size() > 0) {
			return false;
		}
		return true;
	}

	public void mostrarVisuPartida() {
		btTabelaGrupo.setEnabled(false);
		btTabelaMataMata.setEnabled(false);
		btTabelaWinnerLower.setEnabled(false);
		if (PartidaDao.isCampeonatoChaveGrupo(campeonatoSelecionado
				.getCodigoCampeonato())) {
			btTabelaGrupo.setEnabled(true);
		}
		if (PartidaDao.isCampeonatoChaveWinnerLower(campeonatoSelecionado
				.getCodigoCampeonato())) {
			if (telaWinnerLower.isVisible()) {
				telaWinnerLower.atualizarTela(campeonatoSelecionado);
			}
			btTabelaWinnerLower.setEnabled(true);
		} else if (PartidaDao.isCampeonatoChaveMataMata(campeonatoSelecionado
				.getCodigoCampeonato())) {
			if (telaWinnerLower.isVisible()) {
				telaWinnerLower.atualizarTela(campeonatoSelecionado);
			}
			btTabelaMataMata.setEnabled(true);
		}

	}

	public boolean mostrarProximaPartida() {
		atualizarTabela();
		mostrarVisuPartida();
		if (campeonatoSelecionado.getDataFim() == null) {
			if (!proximaLower()) {
				partida = PartidaDao
						.getProximaPartidaPartida(campeonatoSelecionado);
			}
			if (partida == null) {
				btProximaPartida.setVisible(false);
				if (campeonatoSelecionado.getChave().getCodigoChave() == 3
						&& iniciarWinnerLowerMataMata()) {
					if (GerenciadorPartida.adicionarPatidasDeGrupo(
							campeonatoSelecionado, meio)) {
						Menssage.setMenssage("Partidas Geradas",
								"As Partidas foram Geradas",
								ParametroCrud.getModoCrudNovo(), meio);

						return mostrarProximaPartida();
					}
				} else {
					Classificacao classificacao = ClassificacaoDao
							.getClassificacaoPorCampeonato(campeonatoSelecionado);
					if (classificacao == null) {
						classificacao = new Classificacao();
						TimePartida timeCampeao = PartidaDao
								.getTimeCampeao(campeonatoSelecionado
										.getCodigoCampeonato());
						List<Partida> terceiro = PartidaDao
								.getPartidasPaiLower(timeCampeao.getPartida()
										.getCodigoPartida());

						classificacao.setCampeonato(campeonatoSelecionado);
						Time timePrimeiro = timeCampeao.getTimeVencedor();
						Time timeSegundo = timeCampeao.getTimePerdedor();
						Time timeTerceiro = null;
						for (Partida p : terceiro) {
							TimePartida tp = PartidaDao.getTimePartida(p
									.getCampeonato().getCodigoCampeonato(), p
									.getCodigoPartida());
							if (tp.getTime1() != timeSegundo
									&& tp.getTime1() != timePrimeiro) {
								timeTerceiro = tp.getTime1();
							} else if (tp.getTime2() != timeSegundo
									&& tp.getTime2() != timePrimeiro) {
								timeTerceiro = tp.getTime2();
							}
						}

						campeonatoSelecionado.setDataFim(new Date());
						classificacao.setTimePrimeiro(timePrimeiro);
						classificacao.setTimeSegundo(timeSegundo);
						if (timeTerceiro != null) {
							classificacao.setTimeTerceiro(timeTerceiro);
						}
						EntityManagerLocal.begin();
						EntityManagerLocal.persist(classificacao);
						EntityManagerLocal.merge(campeonatoSelecionado);
						EntityManagerLocal.commit();
						EntityManagerLocal.clear();

						campeonatoSelecionado = CampeonatoDao
								.getCampeonato(campeonatoSelecionado
										.getCodigoCampeonato());
						menuPai.abreMenuClassificacao();
					}
				}

				return false;
			}

			TimePartida timePartida = PartidaDao.getTimePartida(
					campeonatoSelecionado.getCodigoCampeonato(),
					partida.getCodigoPartida());

			boolean banido = false;
			Time timeDesqualificado = null;
			Time vencedor = null;
			if (CampeonatoTimeDao.timeDesqualificado(timePartida.getTime1()
					.getCodigoTime(), campeonatoSelecionado
					.getCodigoCampeonato())) {
				banido = true;
				timeDesqualificado = timePartida.getTime1();
				vencedor = timePartida.getTime2();
			} else if (CampeonatoTimeDao.timeDesqualificado(timePartida
					.getTime2().getCodigoTime(), campeonatoSelecionado
					.getCodigoCampeonato())) {
				banido = true;
				timeDesqualificado = timePartida.getTime2();
				vencedor = timePartida.getTime1();
			}

			if (banido) {
				timePartida.setTimeVencedor(vencedor);
				timePartida.setTimePerdedor(timeDesqualificado);
				timePartida.setPlacarTimeVencedor(1);
				timePartida.setPlacarTimePerdedor(0);
				partida.setAtivo(false);
				partida.setHoraInicio(new Date());
				partida.setHoraFim(new Date());
				if (partida.getCampeonato().getChave().getCodigoChave() == 1
						|| partida.getCampeonato().getChave().getCodigoChave() == 2) {
					if (partida.getPartidaFilho() != null) {
						TimePartida time = PartidaDao.getTimePartidaTime1Null(
								partida.getCampeonato().getCodigoCampeonato(),
								partida.getPartidaFilho().getCodigoPartida());

						if (time == null) {
							time = PartidaDao.getTimePartidaTime2Null(partida
									.getCampeonato().getCodigoCampeonato(),
									partida.getPartidaFilho()
											.getCodigoPartida());
							time.setTime2(timePartida.getTimeVencedor());
						} else {
							time.setTime1(timePartida.getTimeVencedor());
						}

						EntityManagerLocal.begin();
						EntityManagerLocal.merge(time);
						if (partida.getPartidaLower() != null) {
							TimePartida timeLower = PartidaDao
									.getTimePartidaTime1Null(partida
											.getCampeonato()
											.getCodigoCampeonato(), partida
											.getPartidaLower()
											.getCodigoPartida());

							if (timeLower == null) {
								timeLower = PartidaDao.getTimePartidaTime2Null(
										partida.getCampeonato()
												.getCodigoCampeonato(), partida
												.getPartidaLower()
												.getCodigoPartida());
								timeLower.setTime2(timePartida
										.getTimePerdedor());
							} else {
								timeLower.setTime1(timePartida
										.getTimePerdedor());
							}
							EntityManagerLocal.flush();
							EntityManagerLocal.merge(timeLower);
						}

						EntityManagerLocal.commit();
						EntityManagerLocal.clear();
					}
				}

				EntityManagerLocal.begin();
				EntityManagerLocal.merge(partida);
				EntityManagerLocal.merge(timePartida);
				EntityManagerLocal.commit();
				EntityManagerLocal.clear();

				partida = PartidaDao.getPartida(partida.getCodigoPartida());

				return mostrarProximaPartida();
			} else if (timePartida.getTime2() == null) {
				timePartida.setTimeVencedor(timePartida.getTime1());
				partida.setAtivo(false);
				partida.setHoraFim(new Date());
				if (partida.getCampeonato().getChave().getCodigoChave() == 1
						|| partida.getCampeonato().getChave().getCodigoChave() == 2) {
					if (partida.getPartidaFilho() != null) {
						TimePartida time = PartidaDao.getTimePartidaTime1Null(
								partida.getCampeonato().getCodigoCampeonato(),
								partida.getPartidaFilho().getCodigoPartida());

						if (time == null) {
							time = PartidaDao.getTimePartidaTime2Null(partida
									.getCampeonato().getCodigoCampeonato(),
									partida.getPartidaFilho()
											.getCodigoPartida());
							time.setTime2(timePartida.getTimeVencedor());
						} else {
							time.setTime1(timePartida.getTimeVencedor());
						}

						EntityManagerLocal.begin();
						EntityManagerLocal.merge(time);
						EntityManagerLocal.commit();
						EntityManagerLocal.clear();
					}
				}

				EntityManagerLocal.begin();
				EntityManagerLocal.merge(partida);
				EntityManagerLocal.merge(timePartida);
				EntityManagerLocal.commit();
				partida = PartidaDao.getPartida(partida.getCodigoPartida());
				EntityManagerLocal.clear();

				return mostrarProximaPartida();
			}

			meioT1.removeAll();
			JLabel lbLg = new JLabel(
					UtilitarioImagem.converterImage(timePartida.getTime1()
							.getLogo()));
			lbLg.setBounds(5, 10, 50, 50);
			lbLg.setFont(UtilitarioTela.getFont(14));
			lbLg.setForeground(UtilitarioTela.getFontColorPadrao());
			meioT1.add(lbLg);

			JLabel lbNome = new JLabel(timePartida.getTime1().getDescricao());
			lbNome.setBounds(65, 10, 150, 50);
			lbNome.setFont(UtilitarioTela.getFont(14));
			lbNome.setForeground(UtilitarioTela.getFontColorPadrao());
			meioT1.add(lbNome);

			JPanel meioVs = new JPanel();
			meioVs.setSize(50, 70);
			meioVs.setLayout(null);
			meioVs.setLocation(220, 0);
			meioVs.setBackground(UtilitarioTela.getFontColorPadrao());
			meioVs.setBorder(null);
			meioT1.add(meioVs);

			JLabel VS = new JLabel("VS");
			VS.setBounds(0, 10, 50, 50);
			VS.setFont(UtilitarioTela.getFont(25));
			VS.setHorizontalAlignment(SwingConstants.CENTER);
			VS.setForeground(UtilitarioTela.getFontColorCrud());
			meioVs.add(VS);

			JLabel lbLg2 = new JLabel(
					UtilitarioImagem.converterImage(timePartida.getTime2()
							.getLogo()));
			lbLg2.setBounds(275, 10, 50, 50);
			lbLg2.setFont(UtilitarioTela.getFont(14));
			lbLg2.setForeground(UtilitarioTela.getFontColorPadrao());
			meioT1.add(lbLg2);

			JLabel lbNome2 = new JLabel(timePartida.getTime2().getDescricao());
			lbNome2.setBounds(330, 10, 150, 50);
			lbNome2.setFont(UtilitarioTela.getFont(14));
			lbNome2.setForeground(UtilitarioTela.getFontColorPadrao());
			meioT1.add(lbNome2);
			
			if (partida != null) {
				String textoAux = "Iniciar Proxima Partida";
				if (partida.getGrupo() != null) {
					textoAux = "Iniciar Proxima Partida (Grupo)";
				} else if (!partida.getWinerLower()) {
					textoAux = "Iniciar Proxima Partida (Mata Mata)";
				} else if (partida.getWinerLower()) {
					textoAux = "Iniciar Proxima Partida (Winner Lower)";
				}
				
				btProximaPartida.setText(textoAux);
				btProximaPartida.repaint();
			}
			
			meioT1.repaint();
			return true;
		}
		mudarChaveHeader();
		return true;
	}

	public void atualizarTabela() {
		listaPartida = PartidaDao.getPartidasFinalizadas(campeonatoSelecionado
				.getCodigoCampeonato());
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.setNumRows(0);
		if (listaPartida != null) {
			for (Partida partida : listaPartida) {
				String chave = "";
				if (partida.getGrupo() != null) {
					chave = "Grupo";
				} else if (!partida.getWinerLower()) {
					chave = "Mata-Mata";
				} else if (partida.getWinerLower()) {
					chave = "WinLower";
				}
				TimePartida tp = PartidaDao.getTimePartida(
						campeonatoSelecionado.getCodigoCampeonato(),
						partida.getCodigoPartida());
				modelo.addRow(new Object[] { tp.getPlacarTimeVencedor(),
						tp.getTimeVencedor().getDescricao(),
						tp.getPlacarTimePerdedor(),
						tp.getTimePerdedor().getDescricao(), chave });
			}
		} else {
			listaPartida = new ArrayList<Partida>();
		}
	}

}
