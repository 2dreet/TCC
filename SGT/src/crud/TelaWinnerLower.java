package crud;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import dao.CampeonatoDao;
import dao.PartidaDao;
import tela.HomeFuncionario;
import utilitario.BordaSombreada;
import utilitario.Computador;
import utilitario.Conectar;
import utilitario.ParametroCrud;
import utilitario.Parametros;
import utilitario.UtilitarioImagem;
import utilitario.UtilitarioTela;
import entidade.Campeonato;
import entidade.Partida;
import entidade.PojoMataMata;
import entidade.Time;
import entidade.TimePartida;

public class TelaWinnerLower extends JFrame {

	private static Campeonato campeonatoSelecionado;

	private int xPai;
	private int yPai;
	private int xFilho;
	private int yFilho;

	private int maiorX;
	private int maiorY;
	private JScrollPane scrollPane;
	private JPanel panel;

	private int posicaoInicialLower;

	public Partida partidaEmAndamento;

	public TelaWinnerLower(Campeonato campeonato) {
		campeonatoSelecionado = campeonato;
		if (panel != null) {
			panel.removeAll();
		}

		maiorX = 0;
		maiorY = 0;
		posicaoInicialLower = 0;
		getContentPane().setLayout(
				new javax.swing.BoxLayout(getContentPane(),
						javax.swing.BoxLayout.LINE_AXIS));

		panel = new JPanel();
		panel.setLayout(null);
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(panel);
		getContentPane().add(scrollPane);
		setSize(UtilitarioTela.getTamanhoMunitor());
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false);
		setLocationRelativeTo(null);
		repaint();
	}

	public void atualizarTela(Campeonato campeonato) {
		campeonatoSelecionado = campeonato;
		scrollPane.remove(panel);
		maiorX = 0;
		maiorY = 0;
		List<PojoMataMata> pojo = new ArrayList<PojoMataMata>();
		List<TimePartida> listaTimePartida = PartidaDao
				.getPartidasParaTabelaMataMata(campeonato, 1, false);

		List<TimePartida> listaTimePartidaLower = PartidaDao
				.getPartidasParaTabelaMataMata(campeonato, 1, true);

		List<TimePartida> listaTimeBaiaWinner = PartidaDao
				.getPartidasParaTabelaMataMataSozinho(campeonato, 1, true);

		List<TimePartida> listaTimeBaia = PartidaDao
				.getPartidasParaTabelaMataMataSozinho(campeonato, 1, true);

		if (listaTimeBaiaWinner != null && listaTimeBaiaWinner.size() > 0) {
			for (TimePartida tp : listaTimeBaiaWinner) {
				listaTimePartida.add(PartidaDao.getTimePartida(
						campeonato.getCodigoCampeonato(), tp.getPartida()
								.getPartidaFilho().getCodigoPartida()));
			}
		}

		if (listaTimeBaia != null && listaTimeBaia.size() > 0) {
			for (TimePartida tp : listaTimeBaia) {
				listaTimePartidaLower.add(PartidaDao.getTimePartida(
						campeonato.getCodigoCampeonato(), tp.getPartida()
								.getPartidaFilho().getCodigoPartida()));
			}
		}

		getContentPane().setLayout(
				new javax.swing.BoxLayout(getContentPane(),
						javax.swing.BoxLayout.LINE_AXIS));
		panel = new JPanel();
		panel.setLayout(null);
		pojo = montarArvore(campeonato, listaTimePartida, pojo, 1);
		posicaoInicialLower = maiorX + 130;
		if (campeonato.getChave().getCodigoChave() == 2) {
			montarArvoreLower(campeonato, listaTimePartidaLower, pojo);
		}
		panel.setPreferredSize(new java.awt.Dimension(maiorX + 300,
				maiorY + 300));
		scrollPane.setViewportView(panel);
		repaint();
	}

	public Color getColorTime(TimePartida tp, Time t) {
		if (tp.getTimePerdedor() == t) {
			return Color.red;
		}
		return Color.BLUE;
	}

	public JPanel getJPanel(int x, int y, Partida partida) {

		TimePartida tp = PartidaDao.getTimePartida(partida.getCampeonato()
				.getCodigoCampeonato(), partida.getCodigoPartida());

		JPanel panel = new JPanel();
		panel.setSize(200, 90);
		panel.setLocation(x, y);
		panel.setLayout(null);
		panel.setBackground(UtilitarioTela.getFontColorCrud());

		if (partida.getIndice() == 1) {
			maiorX = maiorX + x + 120;
		}

		JPanel descricao = new JPanel();
		descricao.setSize(200, 20);
		descricao.setLocation(0, 0);
		descricao.setLayout(null);
		if (partidaEmAndamento == partida) {
			descricao.setBackground(UtilitarioTela.getColorCrud(ParametroCrud
					.getModoCrudNovo()));
		} else {
			if (isPartidaBaia(partida)) {
				descricao.setBackground(UtilitarioTela.getFontColorPadrao());
			} else if (partida.getHoraFim() != null) {
				descricao.setBackground(UtilitarioTela
						.getColorCrud(ParametroCrud.getModoCrudAlterar()));
			} else {
				descricao.setBackground(new Color(102, 178, 255));
			}
		}
		panel.add(descricao);

		String descricaoTxt = "";

		if (!partida.getWinerLower()
				&& !PartidaDao.isPartidaFilho(partida.getCodigoPartida())) {
			descricaoTxt = partida.getDescricao();
		} else if (!partida.getWinerLower()
				&& PartidaDao.isPartidaFilho(partida.getCodigoPartida())) {
			List<Partida> pais = PartidaDao.getPartidasPai(partida
					.getCodigoPartida());
			if (pais.size() == 2) {
				descricaoTxt = partida.getDescricao() + " x ( "
						+ pais.get(0).getDescricao() + " x "
						+ pais.get(1).getDescricao() + " )";
			} else if (pais.size() == 1) {
				descricaoTxt = partida.getDescricao() + " x ( "
						+ pais.get(0).getDescricao() + ")";
			}
		} else if (partida.getWinerLower()
				&& !PartidaDao.isPartidaFilho(partida.getCodigoPartida())) {
			List<Partida> pais = PartidaDao.getPartidasWinner(partida
					.getCodigoPartida());
			if (pais.size() == 2) {
				descricaoTxt = partida.getDescricao() + " x ( W: "
						+ pais.get(0).getDescricao() + " x W: "
						+ pais.get(1).getDescricao() + " )";
			} else if (pais.size() == 1) {
				descricaoTxt = partida.getDescricao() + " x ( W: "
						+ pais.get(0).getDescricao() + " )";
			}
		} else if (partida.getWinerLower()
				&& PartidaDao.isPartidaFilho(partida.getCodigoPartida())) {
			List<Partida> pais = PartidaDao.getPartidasWinner(partida
					.getCodigoPartida());
			List<Partida> paisNormal = PartidaDao.getPartidasPai(partida
					.getCodigoPartida());
			if (pais.size() == 1 && paisNormal.size() == 2) {
				descricaoTxt = partida.getDescricao() + " x ( W: "
						+ pais.get(0).getDescricao() + " x ("
						+ paisNormal.get(0).getDescricao() + "x"
						+ paisNormal.get(1).getDescricao() + ") )";
			} else if (pais.size() == 1 && paisNormal.size() == 1) {
				descricaoTxt = partida.getDescricao() + " x ( W: "
						+ pais.get(0).getDescricao() + " x ("
						+ paisNormal.get(0).getDescricao() + ") )";
			} else if (pais.size() == 2 && paisNormal.size() == 0) {
				descricaoTxt = partida.getDescricao() + " x ( W: "
						+ pais.get(0).getDescricao() + " x W:"
						+ pais.get(1).getDescricao() + " )";
			} else {
				pais = PartidaDao.getPartidasPai(partida.getCodigoPartida());
				if (pais.size() == 2) {
					descricaoTxt = partida.getDescricao() + " x ( "
							+ pais.get(0).getDescricao() + " x "
							+ pais.get(1).getDescricao() + " )";
				} else {
					descricaoTxt = partida.getDescricao() + " x ( "
							+ paisNormal.get(0).getDescricao() + " x  ( W: "
							+ pais.get(0).getDescricao() + " ))";
				}
			}
		}

		JLabel lblDescricao = new JLabel(descricaoTxt);
		lblDescricao.setSize(200, 20);
		lblDescricao.setLocation(0, 0);
		lblDescricao.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescricao.setForeground(UtilitarioTela.getFontColorCrud());
		descricao.add(lblDescricao);

		JPanel partidaFinal = new JPanel();
		partidaFinal.setSize(100, 30);
		partidaFinal.setLocation(x + 220, y + 40);
		partidaFinal.setLayout(null);
		partidaFinal.setBackground(UtilitarioTela.getColorCrud(ParametroCrud
				.getModoCrudAlterar()));

		JLabel lbFinal = new JLabel("Final");
		lbFinal.setSize(100, 30);
		lbFinal.setLocation(0, 0);
		lbFinal.setHorizontalAlignment(SwingConstants.CENTER);
		lbFinal.setForeground(UtilitarioTela.getFontColorCrud());
		partidaFinal.add(lbFinal);

		if (PartidaDao.isPartidaFinal(partida.getCodigoPartida())) {
			this.panel.add(partidaFinal);
		}

		JPanel panelVencedor = new JPanel();
		panelVencedor.setSize(200, 34);
		panelVencedor.setLayout(null);
		panelVencedor.setBackground(UtilitarioTela.getColorCrud(ParametroCrud
				.getModoCrudNovo()));

		JPanel panelTimeBaia = new JPanel();
		panelTimeBaia.setSize(200, 34);
		panelTimeBaia.setLayout(null);
		panelTimeBaia.setBackground(UtilitarioTela.getFontColorPadrao());

		if (panel.getY() > maiorY) {
			maiorY = panel.getY();
		}

		String time1 = "";
		if (tp.getTime1() != null) {
			time1 = tp.getTime1().getDescricao();
		}

		String time2 = "";
		if (tp.getTime2() != null) {
			time2 = tp.getTime2().getDescricao();
		}

		JLabel lb1 = new JLabel(time1);
		lb1.setSize(195, 34);
		lb1.setLocation(5, 20);
		lb1.setForeground(UtilitarioTela.getFundoCrud());

		JPanel mei = new JPanel();
		mei.setSize(200, 2);
		mei.setLocation(0, 55);
		mei.setLayout(null);
		mei.setBackground(UtilitarioTela.getColorCrud(ParametroCrud
				.getModoCrudAlterar()));
		panel.add(mei);

		JLabel lb2 = new JLabel(time2);
		lb2.setSize(195, 34);
		lb2.setLocation(5, 57);
		lb2.setForeground(UtilitarioTela.getFundoCrud());

		boolean time1Add = false;
		boolean time2Add = false;

		if (tp.getTimeVencedor() != null
				&& tp.getTimeVencedor() == tp.getTime1()) {
			panelVencedor.setLocation(0, lb1.getY());
			lb1.setForeground(UtilitarioTela.getFontColorCrud());
			lb1.setLocation(5, 0);
			panelVencedor.add(lb1);
			panel.add(panelVencedor);
			time1Add = true;
		} else if (tp.getTimeVencedor() != null
				&& tp.getTimeVencedor() == tp.getTime2()) {
			panelVencedor.setLocation(0, lb2.getY());
			lb2.setForeground(UtilitarioTela.getFontColorCrud());
			lb2.setLocation(5, 0);
			panelVencedor.add(lb2);
			panel.add(panelVencedor);
			time2Add = true;
		}

		if (tp.getTime1() != null && isTimeBaia(tp.getTime1(), tp.getPartida())) {
			lb1.setForeground(UtilitarioTela.getFontColorCrud());
			panelTimeBaia.setLocation(0, lb1.getY());
			panelTimeBaia.add(lb1);
			lb1.setLocation(5, 0);
			panel.add(panelTimeBaia);
			time1Add = true;
		} else if (tp.getTime2() != null
				&& isTimeBaia(tp.getTime2(), tp.getPartida())) {
			lb2.setForeground(UtilitarioTela.getFontColorCrud());
			panelTimeBaia.setLocation(0, lb2.getY());
			lb2.setLocation(5, 0);
			panelTimeBaia.add(lb2);
			panel.add(panelTimeBaia);
			time2Add = true;
		}

		if (!time1Add) {
			panel.add(lb1);
		}

		if (!time2Add) {
			panel.add(lb2);
		}

		return panel;
	}

	public boolean isTimeBaia(Time time, Partida partida) {
		List<TimePartida> baia = PartidaDao.getTimeBaia(time.getCodigoTime(),
				partida.getWinerLower(), partida.getCampeonato()
						.getCodigoCampeonato());
		if (baia != null && baia.size() > 0) {
			return true;
		}
		return false;
	}

	public boolean isPartidaBaia(Partida partida) {

		if (!PartidaDao.isPartidaFilho(partida.getCodigoPartida())
				&& partida.getIndice() > 1) {
			return true;
		}
		return false;
	}

	public JPanel getJLinhaPanel(int x, int y) {

		JPanel panel = new JPanel();
		panel.setSize(200, 70);
		panel.setLocation(x, y);
		panel.setLayout(null);
		panel.setBackground(null);

		JPanel mei = new JPanel();
		mei.setSize(200, 2);
		mei.setLocation(0, 35);
		mei.setLayout(null);
		mei.setBackground(UtilitarioTela.getFontColorPadrao());
		panel.add(mei);

		return panel;
	}

	public boolean montarArvoreLower(Campeonato campeonato,
			List<TimePartida> listaTimePartida, List<PojoMataMata> partidaFilhos) {
		int tamanhoPai = listaTimePartida.size();
		Partida partida;
		Partida partidaPai1;
		Partida partidaPai2;
		List<PojoMataMata> partidasAcima = new ArrayList<PojoMataMata>();
		List<TimePartida> newLista = new ArrayList<TimePartida>();
		PojoMataMata pm;
		boolean continua = false;
		int k = posicaoInicialLower;
		List<Partida> listaAuxPai = new ArrayList<Partida>();
		for (int n = 0; n < listaTimePartida.size(); n++) {
			pm = new PojoMataMata();
			partida = listaTimePartida.get(n).getPartida();
			if (partida.getPartidaFilho() != null) {
				listaAuxPai = PartidaDao.getPartidasPaiLower(partida
						.getPartidaFilho().getCodigoPartida());
				if (listaAuxPai.size() == 2) {
					boolean naoPassouAinda = false;
					partidaPai1 = listaAuxPai.get(0);
					partidaPai2 = listaAuxPai.get(1);
					JPanel jpanelPai1 = null;
					JPanel jpanelPai2 = null;
					for (PojoMataMata pmAux : partidaFilhos) {
						if (pmAux.getFilho() == partidaPai1) {
							jpanelPai1 = pmAux.getPanelFilho();
						}
						if (pmAux.getFilho() == partidaPai2) {
							jpanelPai2 = pmAux.getPanelFilho();
						}
					}

					if (jpanelPai1 != null && jpanelPai2 != null
							&& jpanelPai1.getY() > jpanelPai2.getY()) {
						partidaPai1 = listaAuxPai.get(1);
						partidaPai2 = listaAuxPai.get(0);
						jpanelPai1 = null;
						jpanelPai2 = null;
						for (PojoMataMata pmAux : partidaFilhos) {
							if (pmAux.getFilho() == partidaPai1) {
								jpanelPai1 = pmAux.getPanelFilho();
							}
							if (pmAux.getFilho() == partidaPai2) {
								jpanelPai2 = pmAux.getPanelFilho();
							}
						}
					}

					if (jpanelPai1 == null && jpanelPai2 != null) {
						List<Partida> listaAuxPai2 = PartidaDao
								.getPartidasPaiLower(partidaPai1
										.getPartidaFilho().getCodigoPartida());
						if (listaAuxPai2.size() > 0) {
							naoPassouAinda = true;
						}
						partidaPai1 = listaAuxPai.get(1);
						partidaPai2 = listaAuxPai.get(0);
						jpanelPai1 = null;
						jpanelPai2 = null;
						for (PojoMataMata pmAux : partidaFilhos) {
							if (pmAux.getFilho() == partidaPai1) {
								jpanelPai1 = pmAux.getPanelFilho();
							}
							if (pmAux.getFilho() == partidaPai2) {
								jpanelPai2 = pmAux.getPanelFilho();
							}
						}
					}

					if (jpanelPai2 == null) {
						List<Partida> listaAuxPai2 = PartidaDao
								.getPartidasPaiLower(partidaPai2
										.getPartidaFilho().getCodigoPartida());
						if (PartidaDao.isPartidaFilho(partidaPai2
								.getCodigoPartida())) {
							naoPassouAinda = true;
						}
					} else if (jpanelPai1 == null) {
						List<Partida> listaAuxPai2 = PartidaDao
								.getPartidasPaiLower(partidaPai1
										.getPartidaFilho().getCodigoPartida());
						if (listaAuxPai2.size() > 0) {
							naoPassouAinda = true;
						}
					}

					if (!naoPassouAinda) {
						pm.setFilho(partidaPai1.getPartidaFilho());
						pm.setPartidaPai1(partidaPai1);
						pm.setPartidaPai2(partidaPai2);

						if (jpanelPai1 == null && jpanelPai2 == null) {
							jpanelPai1 = getJPanel(10, k + (70 * n),
									partidaPai1);
							pm.setPanelPai1(jpanelPai1);
							panel.add(jpanelPai1);
							k = k + 50;
							jpanelPai2 = getJPanel(10, k + (70 * (n + 1)),
									partidaPai2);
							pm.setPanelPai2(jpanelPai2);
							panel.add(jpanelPai2);
						} else if (jpanelPai2 == null) {
							if (partidasAcima.size() > 1) {
								k = k + 50;
								jpanelPai2 = getJPanel(jpanelPai1.getX(),
										jpanelPai1.getY() + (90), partidaPai2);
								pm.setPanelPai2(jpanelPai2);
								panel.add(jpanelPai2);
							} else {
								k = k + 50;
								jpanelPai2 = getJPanel(10, k + (70 * (n + 1)),
										partidaPai2);
								pm.setPanelPai2(jpanelPai2);
								panel.add(jpanelPai2);
							}
						} else if (jpanelPai1 == null) {
							if (partidasAcima.size() > 1) {
								k = k + 50;
								jpanelPai1 = getJPanel(jpanelPai2.getX(),
										jpanelPai2.getY() + +(90), partidaPai1);
								pm.setPanelPai1(jpanelPai1);
								panel.add(jpanelPai1);
							}
						}

						int x = jpanelPai1.getX() + jpanelPai1.getWidth() + 50;
						if (jpanelPai2.getX() > jpanelPai1.getX()) {
							x = jpanelPai2.getX() + jpanelPai2.getWidth() + 50;
						}
						int y = jpanelPai2.getLocation().y
								- ((jpanelPai2.getLocation().y - jpanelPai1
										.getLocation().y) / 2);

						if (jpanelPai2.getX() > jpanelPai1.getX()) {

						}

						JPanel filho = getJPanel(x, y,
								partidaPai1.getPartidaFilho());
						pm.setPanelPai1(jpanelPai1);
						pm.setPanelPai2(jpanelPai2);
						pm.setPanelFilho(filho);
						panel.add(filho);
						getLigacao(jpanelPai1.getX(), jpanelPai2.getX(),
								jpanelPai1.getY(), jpanelPai2.getY(),
								filho.getX(), filho.getY());

						partidaFilhos.add(pm);
						partidasAcima.add(pm);
						newLista.add(PartidaDao.getTimePartida(campeonato
								.getCodigoCampeonato(), partidaPai1
								.getPartidaFilho().getCodigoPartida()));
						continua = true;

						// System.out.println("Pai 1 : "
						// + pm.getPartidaPai1().getCodigoPartida()
						// + " y " + pm.getPanelPai1().getY() + " h "
						// + pm.getPanelPai1().getHeight() + " W "
						// + pm.getPanelPai1().getWidth());
						// System.out.println("Pai 2 : "
						// + pm.getPartidaPai2().getCodigoPartida()
						// + " y " + pm.getPanelPai2().getY() + " h "
						// + pm.getPanelPai2().getHeight());
						// System.out.println("Filho : "
						// + pm.getFilho().getCodigoPartida() + " y "
						// + pm.getPanelFilho().getY());
						// System.out
						// .println("--------------------------------------------------");
						n++;
					}

				} else if (listaAuxPai.size() > 0) {
					boolean naoPassouAinda = false;
					partidaPai1 = listaAuxPai.get(0);
					JPanel jpanelPai1 = null;
					for (PojoMataMata pmAux : partidaFilhos) {
						if (pmAux.getFilho() == partidaPai1) {
							jpanelPai1 = pmAux.getPanelFilho();
						}
					}

					if (jpanelPai1 == null) {
						List<Partida> listaAuxPai2 = PartidaDao
								.getPartidasPaiLower(partidaPai1
										.getPartidaFilho().getCodigoPartida());
						if (listaAuxPai2.size() > 0
								&& listaAuxPai2.get(0).getPartidaFilho()
										.getPartidaFilho() == null) {
							naoPassouAinda = true;
						}
					}

					if (!naoPassouAinda) {
						pm.setFilho(partidaPai1.getPartidaFilho());
						pm.setPartidaPai1(partidaPai1);

						if (jpanelPai1 == null) {
							jpanelPai1 = getJPanel(10, k + (70 * n),
									partidaPai1);
							pm.setPanelPai1(jpanelPai1);
							panel.add(jpanelPai1);
							k = k + 10;
						}

						int x = jpanelPai1.getX() + jpanelPai1.getWidth() + 50;
						int y = jpanelPai1.getY();
						JPanel filho = getJPanel(x, y,
								partidaPai1.getPartidaFilho());
						pm.setPanelPai1(jpanelPai1);
						pm.setPanelFilho(filho);
						getLigacaoSimples(jpanelPai1.getX(), jpanelPai1.getY(),
								filho.getX(), filho.getY());
						panel.add(filho);

						partidaFilhos.add(pm);
						partidasAcima.add(pm);
						newLista.add(PartidaDao.getTimePartida(campeonato
								.getCodigoCampeonato(), partidaPai1
								.getPartidaFilho().getCodigoPartida()));
						continua = true;

						// System.out.println("Pai 1 : "
						// + pm.getPartidaPai1().getCodigoPartida()
						// + " y " + pm.getPanelPai1().getY() + " h "
						// + pm.getPanelPai1().getHeight() + " W "
						// + pm.getPanelPai1().getWidth());
						//
						// System.out.println("Filho : "
						// + pm.getFilho().getCodigoPartida() + " y "
						// + pm.getPanelFilho().getY());
						// System.out
						// .println("--------------------------------------------------");
					}
				}
			}
			k = k + 50;
		}
		if (continua) {
			return montarArvoreLower(campeonato, newLista, partidaFilhos);
		}
		return true;
	}

	public List<PojoMataMata> montarArvore(Campeonato campeonato,
			List<TimePartida> listaTimePartida, List<PojoMataMata> partidaFilhos, int coluna) {
		int tamanhoPai = listaTimePartida.size();
		Partida partida;
		Partida partidaPai1;
		Partida partidaPai2;
		List<PojoMataMata> partidasAcima = new ArrayList<PojoMataMata>();
		List<TimePartida> newLista = new ArrayList<TimePartida>();
		PojoMataMata pm;
		boolean continua = false;
		int k = 10;
		List<Partida> listaAuxPai = new ArrayList<Partida>();
		for (int n = 0; n < listaTimePartida.size(); n++) {
			pm = new PojoMataMata();
			partida = listaTimePartida.get(n).getPartida();
			if (partida.getPartidaFilho() != null) {
				listaAuxPai = PartidaDao.getPartidasPai(partida
						.getPartidaFilho().getCodigoPartida());
				if (listaAuxPai.size() == 2) {
					boolean naoPassouAinda = false;
					partidaPai1 = listaAuxPai.get(0);
					partidaPai2 = listaAuxPai.get(1);
					if((!partidaPai1.getDescricao().contains("A") && coluna == 1) || (!partidaPai2.getDescricao().contains("A") && coluna == 1)){
						naoPassouAinda = true;
					}
					JPanel jpanelPai1 = null;
					JPanel jpanelPai2 = null;
					for (PojoMataMata pmAux : partidaFilhos) {
						if (pmAux.getFilho() == partidaPai1) {
							jpanelPai1 = pmAux.getPanelFilho();
						}
						if (pmAux.getFilho() == partidaPai2) {
							jpanelPai2 = pmAux.getPanelFilho();
						}
					}

					if (jpanelPai1 != null && jpanelPai2 != null
							&& jpanelPai1.getY() > jpanelPai2.getY()) {
						partidaPai1 = listaAuxPai.get(1);
						partidaPai2 = listaAuxPai.get(0);
						jpanelPai1 = null;
						jpanelPai2 = null;
						for (PojoMataMata pmAux : partidaFilhos) {
							if (pmAux.getFilho() == partidaPai1) {
								jpanelPai1 = pmAux.getPanelFilho();
							}
							if (pmAux.getFilho() == partidaPai2) {
								jpanelPai2 = pmAux.getPanelFilho();
							}
						}
					}

					if (jpanelPai1 == null
							&& jpanelPai2 != null
							&& PartidaDao.isPartidaFilho(partidaPai1
									.getCodigoPartida())) {
						List<Partida> listaAuxPai2 = PartidaDao
								.getPartidasPai(partidaPai1.getPartidaFilho()
										.getCodigoPartida());
						if (listaAuxPai2.size() > 0) {
							naoPassouAinda = true;
						}
						partidaPai1 = listaAuxPai.get(1);
						partidaPai2 = listaAuxPai.get(0);
						jpanelPai1 = null;
						jpanelPai2 = null;
						for (PojoMataMata pmAux : partidaFilhos) {
							if (pmAux.getFilho() == partidaPai1) {
								jpanelPai1 = pmAux.getPanelFilho();
							}
							if (pmAux.getFilho() == partidaPai2) {
								jpanelPai2 = pmAux.getPanelFilho();
							}
						}
					}

					if (jpanelPai2 == null) {
						List<Partida> listaAuxPai2 = PartidaDao
								.getPartidasPai(partidaPai2.getPartidaFilho()
										.getCodigoPartida());
						if (listaAuxPai2.size() > 0
								&& listaAuxPai2.get(0).getPartidaFilho()
										.getPartidaFilho() == null
								&& !listaAuxPai2.get(0).getDescricao()
										.contains("A") && coluna > 1) {
							naoPassouAinda = true;
						}
					} else if (jpanelPai1 == null) {
						List<Partida> listaAuxPai2 = PartidaDao
								.getPartidasPai(partidaPai1.getPartidaFilho()
										.getCodigoPartida());
						if (listaAuxPai2.size() > 0
								&& listaAuxPai2.get(0).getPartidaFilho()
										.getPartidaFilho() == null
								&& !listaAuxPai2.get(0).getDescricao()
										.contains("A") && coluna > 1) {
							naoPassouAinda = true;
						}
					}

					if (!naoPassouAinda) {
						pm.setFilho(partidaPai1.getPartidaFilho());
						pm.setPartidaPai1(partidaPai1);
						pm.setPartidaPai2(partidaPai2);

						if (jpanelPai2 != null && jpanelPai1 == null
								&& partidaPai1.getDescricao().contains("A") && coluna > 1) {
							jpanelPai1 = jpanelPai2;
							Partida partidaold = partidaPai1;
							partidaPai1 = partidaPai2;
							partidaPai2 = partidaold;
							jpanelPai2 = null;
						}

						if (jpanelPai1 == null && jpanelPai2 == null) {
							jpanelPai1 = getJPanel(10, k + (70 * n),
									partidaPai1);
							pm.setPanelPai1(jpanelPai1);
							panel.add(jpanelPai1);
							k = k + 50;
							jpanelPai2 = getJPanel(10, k + (70 * (n + 1)),
									partidaPai2);
							pm.setPanelPai2(jpanelPai2);
							panel.add(jpanelPai2);
						} else if (jpanelPai2 == null) {
							
								k = k + 50;
								jpanelPai2 = getJPanel(jpanelPai1.getX(),
										jpanelPai1.getY() + (110), partidaPai2);
								pm.setPanelPai2(jpanelPai2);
								panel.add(jpanelPai2);
							 
						} else if (jpanelPai1 == null) {
							if (partidasAcima.size() >= 1) {
								k = k + 50;
								jpanelPai1 = getJPanel(jpanelPai2.getX(),
										jpanelPai2.getY() + (110), partidaPai1);
								pm.setPanelPai1(jpanelPai1);
								panel.add(jpanelPai1);
							}
						}

						int x = jpanelPai1.getX() + jpanelPai1.getWidth() + 50;
						if (jpanelPai2.getX() > jpanelPai1.getX()) {
							x = jpanelPai2.getX() + jpanelPai2.getWidth() + 50;
						}
						int y = jpanelPai2.getLocation().y
								- ((jpanelPai2.getLocation().y - jpanelPai1
										.getLocation().y) / 2);

						if (jpanelPai2.getX() > jpanelPai1.getX()) {

						}

						JPanel filho = getJPanel(x, y,
								partidaPai1.getPartidaFilho());
						pm.setPanelPai1(jpanelPai1);
						pm.setPanelPai2(jpanelPai2);
						pm.setPanelFilho(filho);
						panel.add(filho);
						getLigacao(jpanelPai1.getX(), jpanelPai2.getX(),
								jpanelPai1.getY(), jpanelPai2.getY(),
								filho.getX(), filho.getY());

						partidaFilhos.add(pm);
						partidasAcima.add(pm);
						newLista.add(PartidaDao.getTimePartida(campeonato
								.getCodigoCampeonato(), partidaPai1
								.getPartidaFilho().getCodigoPartida()));
						continua = true;

						// System.out.println("Pai 1 : "
						// + pm.getPartidaPai1().getCodigoPartida()
						// + " y " + pm.getPanelPai1().getY() + " h "
						// + pm.getPanelPai1().getHeight() + " W "
						// + pm.getPanelPai1().getWidth());
						// System.out.println("Pai 2 : "
						// + pm.getPartidaPai2().getCodigoPartida()
						// + " y " + pm.getPanelPai2().getY() + " h "
						// + pm.getPanelPai2().getHeight());
						// System.out.println("Filho : "
						// + pm.getFilho().getCodigoPartida() + " y "
						// + pm.getPanelFilho().getY());
						// System.out
						// .println("--------------------------------------------------");
					}
					n++;

				} else {
					boolean naoPassouAinda = false;
					partidaPai1 = listaAuxPai.get(0);
					JPanel jpanelPai1 = null;
					for (PojoMataMata pmAux : partidaFilhos) {
						if (pmAux.getFilho() == partidaPai1) {
							jpanelPai1 = pmAux.getPanelFilho();
						}
					}

					if (jpanelPai1 == null) {
						List<Partida> listaAuxPai2 = PartidaDao
								.getPartidasPai(partidaPai1.getPartidaFilho()
										.getCodigoPartida());
						if (listaAuxPai2.size() > 0
								&& listaAuxPai2.get(0).getPartidaFilho()
										.getPartidaFilho() == null) {
							naoPassouAinda = true;
						}
					}

					if (!naoPassouAinda) {
						pm.setFilho(partidaPai1.getPartidaFilho());
						pm.setPartidaPai1(partidaPai1);

						if (jpanelPai1 == null) {
							jpanelPai1 = getJPanel(10, k + (70 * n),
									partidaPai1);
							pm.setPanelPai1(jpanelPai1);
							panel.add(jpanelPai1);
						}

						int x = jpanelPai1.getX() + jpanelPai1.getWidth() + 50;
						int y = jpanelPai1.getY();
						JPanel filho = getJPanel(x, y,
								partidaPai1.getPartidaFilho());
						pm.setPanelPai1(jpanelPai1);
						pm.setPanelFilho(filho);
						getLigacaoSimples(jpanelPai1.getX(), jpanelPai1.getY(),
								filho.getX(), filho.getY());
						panel.add(filho);

						partidaFilhos.add(pm);
						partidasAcima.add(pm);
						newLista.add(PartidaDao.getTimePartida(campeonato
								.getCodigoCampeonato(), partidaPai1
								.getPartidaFilho().getCodigoPartida()));
						continua = true;

						// System.out.println("Pai 1 : "
						// + pm.getPartidaPai1().getCodigoPartida()
						// + " y " + pm.getPanelPai1().getY() + " h "
						// + pm.getPanelPai1().getHeight() + " W "
						// + pm.getPanelPai1().getWidth());
						//
						// System.out.println("Filho : "
						// + pm.getFilho().getCodigoPartida() + " y "
						// + pm.getPanelFilho().getY());
						// System.out
						// .println("--------------------------------------------------");
					}
					n++;
				}
			} 
			k = k + 50;
		}
		if (continua) {
			coluna++;
			return montarArvore(campeonato, newLista, partidaFilhos, coluna);
		}
		return partidaFilhos;
	}

	public void getLigacao(int xP1, int xP2, int yP1, int yP2, int xF, int yF) {
		JPanel p1 = new JPanel();
		p1.setSize(xF - xP1 - 230, 2);
		p1.setLocation(xP1 + 200, yP1 + 55);
		p1.setLayout(null);
		p1.setBackground(UtilitarioTela.getFontColorPadrao());
		panel.add(p1);

		JPanel p2 = new JPanel();
		p2.setSize(xF - xP2 - 230, 2);
		p2.setLocation(xP2 + 200, yP2 + 55);
		p2.setLayout(null);
		p2.setBackground(UtilitarioTela.getFontColorPadrao());
		panel.add(p2);

		JPanel l = new JPanel();
		if (p2.getY() < p1.getY()) {
			l.setSize(2, p1.getY() - p2.getY() + 2);
			l.setLocation(p1.getX() + p1.getWidth(), yP2 + 55);
			l.setLayout(null);
			l.setBackground(UtilitarioTela.getFontColorPadrao());
			panel.add(l);
		} else {
			l.setSize(2, p2.getY() - p1.getY() + 2);
			l.setLocation(p2.getX() + p2.getWidth(), yP1 + 55);
			l.setLayout(null);
			l.setBackground(UtilitarioTela.getFontColorPadrao());
			panel.add(l);
		}
		JPanel p3 = new JPanel();
		p3.setSize(30, 2);
		p3.setLocation(l.getX(), yF + 55);
		p3.setLayout(null);
		p3.setBackground(UtilitarioTela.getFontColorPadrao());
		panel.add(p3);
	}

	public void getLigacaoSimples(int xP1, int yP1, int xF, int yF) {
		JPanel p1 = new JPanel();
		p1.setSize(xF - xP1 - 200, 2);
		p1.setLocation(xP1 + 200, yP1 + 55);
		p1.setLayout(null);
		p1.setBackground(UtilitarioTela.getFontColorPadrao());
		panel.add(p1);
	}

	public static void main(String[] args) {
		Conectar.conectar();
		Campeonato campeonato = CampeonatoDao.getCampeonato(1);
		TelaWinnerLower tm = new TelaWinnerLower(campeonato);
		tm.atualizarTela(campeonato);
		tm.setVisible(true);
	}
}
