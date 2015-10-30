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

	public TelaWinnerLower(Campeonato campeonato) {
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
		scrollPane.remove(panel);
		maiorX = 0;
		maiorY = 0;
		List<PojoMataMata> pojo = new ArrayList<PojoMataMata>();
		List<TimePartida> listaTimePartida = PartidaDao
				.getPartidasParaTabelaMataMata(campeonato, 1, false);

		List<TimePartida> listaTimePartidaLower = PartidaDao
				.getPartidasParaTabelaMataMata(campeonato, 1, true);

		getContentPane().setLayout(
				new javax.swing.BoxLayout(getContentPane(),
						javax.swing.BoxLayout.LINE_AXIS));
		panel = new JPanel();
		panel.setLayout(null);
		pojo = montarArvore(campeonato, listaTimePartida, pojo);
		posicaoInicialLower = maiorX;
		montarArvoreLower(campeonato, listaTimePartidaLower, pojo);
		panel.setPreferredSize(new java.awt.Dimension(maiorX, maiorY));
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
		panel.setSize(200, 70);
		panel.setLocation(x, y);
		panel.setLayout(null);
		panel.setBackground(UtilitarioTela.getFontColorCrud());

		JPanel partidaFinal = new JPanel();
		partidaFinal.setSize(100, 30);
		partidaFinal.setLocation(x + 220, y + 20);
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

		if (panel.getX() > maiorX) {
			maiorX = panel.getX() + 220;
		}

		if (panel.getY() > maiorY) {
			maiorY = panel.getY() + 350;
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
		lb1.setLocation(5, 0);
		lb1.setForeground(UtilitarioTela.getFundoCrud());

		JPanel mei = new JPanel();
		mei.setSize(200, 2);
		mei.setLocation(0, 34);
		mei.setLayout(null);
		mei.setBackground(UtilitarioTela.getColorCrud(ParametroCrud
				.getModoCrudAlterar()));
		panel.add(mei);

		JLabel lb2 = new JLabel(time2);
		lb2.setSize(195, 34);
		lb2.setLocation(5, 36);
		lb2.setForeground(UtilitarioTela.getFundoCrud());

		boolean time1Add = false;
		boolean time2Add = false;

		if (tp.getTimeVencedor() != null
				&& tp.getTimeVencedor() == tp.getTime1()) {
			panelVencedor.setLocation(0, lb1.getY());
			lb1.setForeground(UtilitarioTela.getFontColorCrud());
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

		if (tp.getTime1() != null && isTimeBaia(tp.getTime1())) {
			lb1.setForeground(UtilitarioTela.getFontColorCrud());
			panelTimeBaia.setLocation(0, lb1.getY());
			panelTimeBaia.add(lb1);
			panel.add(panelTimeBaia);
			time1Add = true;
		} else if (tp.getTime2() != null && isTimeBaia(tp.getTime2())) {
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

	public boolean isTimeBaia(Time time) {
		List<TimePartida> baia = PartidaDao.getTimeBaia(time.getCodigoTime());
		if (baia != null && baia.size() > 0) {
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
				listaAuxPai = PartidaDao.getPartidasPai(partida
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
								.getPartidasPai(partidaPai1.getPartidaFilho()
										.getCodigoPartida());
						if (listaAuxPai2.size() > 0) {
							naoPassouAinda = true;
						}
						partidaPai1 = listaAuxPai.get(1);
						partidaPai2= listaAuxPai.get(0);
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
										.getPartidaFilho() == null) {
							naoPassouAinda = true;
						}
						if(PartidaDao.isPartidaFilho(partidaPai2.getCodigoPartida())){
							naoPassouAinda = true;
						}
					} else if (jpanelPai1 == null) {
						List<Partida> listaAuxPai2 = PartidaDao
								.getPartidasPai(partidaPai1.getPartidaFilho()
										.getCodigoPartida());
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
							k = k + 10;
							jpanelPai2 = getJPanel(10, k + (70 * (n + 1)),
									partidaPai2);
							pm.setPanelPai2(jpanelPai2);
							panel.add(jpanelPai2);
						} else if (jpanelPai2 == null) {
							if (partidasAcima.size() > 1) {
								k = k + 10;
								jpanelPai2 = getJPanel(jpanelPai1.getX(),
										jpanelPai1.getY() + (90), partidaPai2);
								pm.setPanelPai2(jpanelPai2);
								panel.add(jpanelPai2);
							} else {
								k = k + 10;
								k = k + 10;
								jpanelPai2 = getJPanel(10, k + (70 * (n + 1)),
										partidaPai2);
								pm.setPanelPai2(jpanelPai2);
								panel.add(jpanelPai2);
							}
						} else if (jpanelPai1 == null) {
							if (partidasAcima.size() > 1) {
								k = k + 10;
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
			k = k + 10;
		}
		if (continua) {
			return montarArvoreLower(campeonato, newLista, partidaFilhos);
		}
		return true;
	}

	public List<PojoMataMata> montarArvore(Campeonato campeonato,
			List<TimePartida> listaTimePartida, List<PojoMataMata> partidaFilhos) {
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
										.getPartidaFilho() == null) {
							naoPassouAinda = true;
						}
					} else if (jpanelPai1 == null) {
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
						pm.setPartidaPai2(partidaPai2);

						if (jpanelPai1 == null && jpanelPai2 == null) {
							jpanelPai1 = getJPanel(10, k + (70 * n),
									partidaPai1);
							pm.setPanelPai1(jpanelPai1);
							panel.add(jpanelPai1);
							k = k + 10;
							jpanelPai2 = getJPanel(10, k + (70 * (n + 1)),
									partidaPai2);
							pm.setPanelPai2(jpanelPai2);
							panel.add(jpanelPai2);
						} else if (jpanelPai2 == null) {
							if (partidasAcima.size() > 1) {
								k = k + 10;
								jpanelPai2 = getJPanel(jpanelPai1.getX(),
										jpanelPai1.getY() + (90), partidaPai2);
								pm.setPanelPai2(jpanelPai2);
								panel.add(jpanelPai2);
							}
						} else if (jpanelPai1 == null) {
							if (partidasAcima.size() > 1) {
								k = k + 10;
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
			k = k + 10;
		}
		if (continua) {
			return montarArvore(campeonato, newLista, partidaFilhos);
		}
		return partidaFilhos;
	}

	public void getLigacao(int xP1, int xP2, int yP1, int yP2, int xF, int yF) {
		JPanel p1 = new JPanel();
		p1.setSize(xF - xP1 - 230, 2);
		p1.setLocation(xP1 + 200, yP1 + 35);
		p1.setLayout(null);
		p1.setBackground(UtilitarioTela.getFontColorPadrao());
		panel.add(p1);

		JPanel p2 = new JPanel();
		p2.setSize(xF - xP2 - 230, 2);
		p2.setLocation(xP2 + 200, yP2 + 35);
		p2.setLayout(null);
		p2.setBackground(UtilitarioTela.getFontColorPadrao());
		panel.add(p2);

		JPanel l = new JPanel();
		l.setSize(2, p2.getY() - p1.getY() + 2);
		l.setLocation(p2.getX() + p2.getWidth(), yP1 + 35);
		l.setLayout(null);
		l.setBackground(UtilitarioTela.getFontColorPadrao());
		panel.add(l);

		JPanel p3 = new JPanel();
		p3.setSize(30, 2);
		p3.setLocation(l.getX(), yF + 35);
		p3.setLayout(null);
		p3.setBackground(UtilitarioTela.getFontColorPadrao());
		panel.add(p3);
	}

	public void getLigacaoSimples(int xP1, int yP1, int xF, int yF) {
		JPanel p1 = new JPanel();
		p1.setSize(xF - xP1 - 200, 2);
		p1.setLocation(xP1 + 200, yP1 + 35);
		p1.setLayout(null);
		p1.setBackground(UtilitarioTela.getFontColorPadrao());
		panel.add(p1);
	}

	public static void main(String[] args) {
		Campeonato campeonato = CampeonatoDao.getCampeonato(1);
		TelaWinnerLower tm = new TelaWinnerLower(campeonato);
		tm.atualizarTela(campeonato);
		tm.setVisible(true);
	}
}
