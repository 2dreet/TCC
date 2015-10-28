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
import utilitario.BordaSombreada;
import utilitario.ParametroCrud;
import utilitario.Parametros;
import utilitario.UtilitarioImagem;
import utilitario.UtilitarioTela;
import entidade.Campeonato;
import entidade.Partida;
import entidade.PojoMataMata;
import entidade.Time;
import entidade.TimePartida;

public class TelaMataMata extends JFrame {

	private static Campeonato campeonatoSelecionado;

	private int xPai;
	private int yPai;
	private int xFilho;
	private int yFilho;
	
	private int maiorX;
	private int maiorY;
	
	private JPanel panel;
	public TelaMataMata(Campeonato campeonato, JPanel pai) {
		maiorX = 0;
		maiorY = 0;
		Integer[] indices = PartidaDao
				.getIndicesPartidaParaGerarLowers(campeonato
						.getCodigoCampeonato());
		List<PojoMataMata> pojo = new ArrayList<PojoMataMata>();
		List<TimePartida> listaTimePartida = PartidaDao
				.getPartidasParaTabelaMataMata(campeonato, 1, false);

		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
		//frame.setBounds(10, 10, 600, 400);
		//frame.setLayout((new BoxLayout(getContentPane(), BoxLayout.X_AXIS)));
		// montarArvore(campeonato, listaTimePartida, pojo);
		panel = new JPanel();
		panel.setPreferredSize(new java.awt.Dimension(2000, 2000));
		panel.setLayout(null);
		montarArvore2(campeonato, listaTimePartida, pojo);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(panel);
        //scrollPane.setSize(UtilitarioTela.getTamanhoMunitor());
		getContentPane().add(scrollPane);
		setBounds(10, 10, 600, 400);
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame.setResizable(false);
//        frame.setLocationRelativeTo(null);
        setVisible(true);
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

		String time1 = "" + partida.getCodigoPartida();
		if (tp.getTime1() != null) {
			// time1 = tp.getTime1().getDescricao();
		}

		String time2 = "";
		if (tp.getTime2() != null) {
			time2 = tp.getTime2().getDescricao();
		}

		JLabel lb1 = new JLabel(time1);
		lb1.setSize(195, 34);
		lb1.setLocation(5, 0);
		lb1.setForeground(Color.blue);
		panel.add(lb1);

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
		lb2.setForeground(Color.blue);
		panel.add(lb2);

		return panel;
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

	public boolean montarArvore(Campeonato campeonato,
			List<TimePartida> listaTimePartida, List<PojoMataMata> partidaFilhos) {
		int tamanhoPai = listaTimePartida.size();
		Partida partidaPai1;
		Partida partidaPai2;
		List<TimePartida> newLista = new ArrayList<TimePartida>();
		PojoMataMata pm;
		boolean continua = false;
		int k = 10;
		List<Partida> listaAuxPai = new ArrayList<Partida>();
		for (int n = 0; n < tamanhoPai; n++) {
			pm = new PojoMataMata();
			if (tamanhoPai > n + 1) {
				partidaPai1 = listaTimePartida.get(n).getPartida();
				partidaPai2 = listaTimePartida.get(n + 1).getPartida();
				if (partidaPai1.getPartidaFilho() != null
						&& partidaPai1.getPartidaFilho() == partidaPai2
								.getPartidaFilho()) {
					JPanel jpanelPai1 = null;
					JPanel jpanelPai2 = null;
					boolean isFilho = false;
					for (PojoMataMata pmAux : partidaFilhos) {
						if (pmAux.getFilho() == partidaPai1) {
							jpanelPai1 = pmAux.getPanelFilho();
							isFilho = true;
						}
						if (pmAux.getFilho() == partidaPai2) {
							jpanelPai2 = pmAux.getPanelFilho();
							isFilho = true;
						}
					}
					// Pai 1 tem o mesmo filho que Pai 2
					pm.setFilho(partidaPai1.getPartidaFilho());
					pm.setPartidaPai1(partidaPai1);
					pm.setPartidaPai2(partidaPai2);

					if (!isFilho) {
						jpanelPai1 = getJPanel(10, k + (70 * n), partidaPai1);
						k = k + 10;
						jpanelPai2 = getJPanel(10, k + (70 * (n + 1)),
								partidaPai2);
						int x = jpanelPai1.getX() + jpanelPai1.getWidth() + 50;
						int y = jpanelPai2.getLocation().y
								- ((jpanelPai2.getLocation().y - jpanelPai1
										.getLocation().y) / 2);
						JPanel filho = getJPanel(x, y,
								partidaPai1.getPartidaFilho());
						pm.setPanelPai1(jpanelPai1);
						pm.setPanelPai2(jpanelPai2);
						pm.setPanelFilho(filho);
						add(jpanelPai1);
						add(jpanelPai2);
						add(filho);
						getLigacao(jpanelPai1.getX(), jpanelPai2.getX(),
								jpanelPai1.getY(), jpanelPai2.getY(),
								filho.getX(), filho.getY());

					} else {
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
						add(filho);
						getLigacao(jpanelPai1.getX(), jpanelPai2.getX(),
								jpanelPai1.getY(), jpanelPai2.getY(),
								filho.getX(), filho.getY());
					}

					partidaFilhos.add(pm);
					newLista.add(PartidaDao.getTimePartida(campeonato
							.getCodigoCampeonato(), partidaPai1
							.getPartidaFilho().getCodigoPartida()));
					continua = true;

					System.out.println("Pai 1 : "
							+ pm.getPartidaPai1().getCodigoPartida() + " y "
							+ pm.getPanelPai1().getY() + " h "
							+ pm.getPanelPai1().getHeight() + " W "
							+ pm.getPanelPai1().getWidth());
					System.out.println("Pai 2 : "
							+ pm.getPartidaPai2().getCodigoPartida() + " y "
							+ pm.getPanelPai2().getY() + " h "
							+ pm.getPanelPai2().getHeight());
					System.out.println("Filho : "
							+ pm.getFilho().getCodigoPartida() + " y "
							+ pm.getPanelFilho().getY());
					System.out
							.println("--------------------------------------------------");

					n++;
				}
			} else if (listaTimePartida.get(n).getPartida().getPartidaFilho() != null) {
				listaAuxPai = PartidaDao.getPartidasPai(listaTimePartida.get(n)
						.getPartida().getPartidaFilho().getCodigoPartida());
				if (listaAuxPai.size() == 2) {
					partidaPai1 = listaTimePartida.get(n).getPartida();
					partidaPai2 = listaAuxPai.get(0);
					JPanel jpanelPai2 = null;
					JPanel jpanelPai1 = null;
					for (PojoMataMata pmAux : partidaFilhos) {
						if (pmAux.getFilho() == partidaPai1) {
							jpanelPai1 = pmAux.getPanelFilho();
						} else if (pmAux.getFilho() == partidaPai2) {
							jpanelPai2 = pmAux.getPanelFilho();
						}
					}
					if (jpanelPai1 != null && jpanelPai2 != null) {
						pm.setFilho(partidaPai1.getPartidaFilho());
						pm.setPartidaPai1(partidaPai1);
						pm.setPartidaPai2(partidaPai2);

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
						add(filho);
						getLigacao(jpanelPai1.getX(), jpanelPai2.getX(),
								jpanelPai1.getY(), jpanelPai2.getY(),
								filho.getX(), filho.getY());

						partidaFilhos.add(pm);
						newLista.add(PartidaDao.getTimePartida(campeonato
								.getCodigoCampeonato(), partidaPai1
								.getPartidaFilho().getCodigoPartida()));
						continua = true;
						System.out.println("Pai 1 : "
								+ pm.getPartidaPai1().getCodigoPartida());
						System.out.println("Pai 2 : "
								+ pm.getPartidaPai2().getCodigoPartida());
						System.out.println("Filho : "
								+ pm.getFilho().getCodigoPartida());
						System.out
								.println("--------------------------------------------------");

						n++;

					}
				} else {
					partidaPai1 = listaTimePartida.get(n).getPartida();
					// Pai 1 tem o mesmo filho que Pai 2
					pm.setPartidaPai1(partidaPai1);
					pm.setFilho(partidaPai1.getPartidaFilho());
					JPanel jpanelPai1 = null;
					boolean isFilho = false;

					for (PojoMataMata pmAux : partidaFilhos) {
						if (pmAux.getFilho() == partidaPai1) {
							jpanelPai1 = pmAux.getPanelFilho();
							isFilho = true;
						}
					}

					if (!isFilho) {
						jpanelPai1 = getJPanel(10, k + (70 * n),
								partidaPai1.getPartidaFilho());
						int x = jpanelPai1.getX() + jpanelPai1.getWidth() + 50;
						int y = (jpanelPai1.getY() + jpanelPai1.getHeight() / 2) - 15;
						JPanel filho = getJPanel(x, y,
								partidaPai1.getPartidaFilho());
						pm.setPanelPai1(jpanelPai1);
						pm.setPanelFilho(filho);
						add(jpanelPai1);
						add(filho);
						getLigacaoSimples(jpanelPai1.getX(), jpanelPai1.getY(),
								filho.getX(), filho.getY());
					} else {
						int x = jpanelPai1.getX() + jpanelPai1.getWidth() + 50;
						int y = (jpanelPai1.getY() + jpanelPai1.getHeight() / 2) - 35;
						JPanel filho = getJPanel(x, y,
								partidaPai1.getPartidaFilho());
						pm.setPanelPai1(jpanelPai1);
						pm.setPanelFilho(filho);
						add(filho);
						getLigacaoSimples(jpanelPai1.getX(), jpanelPai1.getY(),
								filho.getX(), filho.getY());
					}

					partidaFilhos.add(pm);

					newLista.add(PartidaDao.getTimePartida(campeonato
							.getCodigoCampeonato(), partidaPai1
							.getPartidaFilho().getCodigoPartida()));
					continua = true;

					System.out.println("Pai 1 : "
							+ pm.getPartidaPai1().getCodigoPartida());
					System.out.println("Filho : "
							+ pm.getFilho().getCodigoPartida());
					System.out
							.println("--------------------------------------------------");
				}
				n++;
			} else if (tamanhoPai == n + 1 && listaTimePartida.size() > 1) {
				// Ultima Partida
				partidaPai1 = listaTimePartida.get(0).getPartida();
				partidaPai2 = listaTimePartida.get(1).getPartida();
				JPanel jpanelPai1 = null;
				JPanel jpanelPai2 = null;
				boolean isFilho = false;
				for (PojoMataMata pmAux : partidaFilhos) {
					if (pmAux.getFilho() == partidaPai1) {
						jpanelPai1 = pmAux.getPanelFilho();
						isFilho = true;
					} else if (pmAux.getFilho() == partidaPai2) {
						jpanelPai2 = pmAux.getPanelFilho();
						isFilho = true;
					}
				}

				pm.setFilho(partidaPai1.getPartidaFilho());
				pm.setPartidaPai1(partidaPai1);
				pm.setPartidaPai2(partidaPai2);

				int x = jpanelPai1.getX() + jpanelPai1.getWidth() + 50;
				if (jpanelPai2.getX() > jpanelPai1.getX()) {
					x = jpanelPai2.getX() + jpanelPai2.getWidth() + 50;
				}
				int y = jpanelPai2.getLocation().y
						- ((jpanelPai2.getLocation().y - jpanelPai1
								.getLocation().y) / 2);

				if (jpanelPai2.getX() > jpanelPai1.getX()) {

				}

				JPanel filho = getJPanel(x, y, partidaPai1.getPartidaFilho());
				pm.setPanelPai1(jpanelPai1);
				pm.setPanelPai2(jpanelPai2);
				pm.setPanelFilho(filho);
				add(filho);
				getLigacao(jpanelPai1.getX(), jpanelPai2.getX(),
						jpanelPai1.getY(), jpanelPai2.getY(), filho.getX(),
						filho.getY());

				partidaFilhos.add(pm);
				newLista.add(PartidaDao.getTimePartida(campeonato
						.getCodigoCampeonato(), partidaPai1.getPartidaFilho()
						.getCodigoPartida()));
				continua = true;

				System.out.println("Pai 1 : "
						+ pm.getPartidaPai1().getCodigoPartida() + " y "
						+ pm.getPanelPai1().getY() + " h "
						+ pm.getPanelPai1().getHeight() + " W "
						+ pm.getPanelPai1().getWidth());
				System.out.println("Pai 2 : "
						+ pm.getPartidaPai2().getCodigoPartida() + " y "
						+ pm.getPanelPai2().getY() + " h "
						+ pm.getPanelPai2().getHeight());
				System.out.println("Filho : "
						+ pm.getFilho().getCodigoPartida() + " y "
						+ pm.getPanelFilho().getY());
				System.out
						.println("--------------------------------------------------");

				n++;
			}
			k = k + 10;
		}
		if (continua) {
			return montarArvore(campeonato, newLista, partidaFilhos);
		}
		return true;
	}

	public boolean montarArvore2(Campeonato campeonato,
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
						if (listaAuxPai2.size() > 0 && listaAuxPai2.get(0).getPartidaFilho().getPartidaFilho() == null) {
							naoPassouAinda = true;
						}
					} else if(jpanelPai1 == null){
						List<Partida> listaAuxPai2 = PartidaDao
								.getPartidasPai(partidaPai1.getPartidaFilho()
										.getCodigoPartida());
						if (listaAuxPai2.size() > 0 && listaAuxPai2.get(0).getPartidaFilho().getPartidaFilho() == null) {
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

						System.out.println("Pai 1 : "
								+ pm.getPartidaPai1().getCodigoPartida()
								+ " y " + pm.getPanelPai1().getY() + " h "
								+ pm.getPanelPai1().getHeight() + " W "
								+ pm.getPanelPai1().getWidth());
						System.out.println("Pai 2 : "
								+ pm.getPartidaPai2().getCodigoPartida()
								+ " y " + pm.getPanelPai2().getY() + " h "
								+ pm.getPanelPai2().getHeight());
						System.out.println("Filho : "
								+ pm.getFilho().getCodigoPartida() + " y "
								+ pm.getPanelFilho().getY());
						System.out
								.println("--------------------------------------------------");
					}
					n++;

				}
			}
			k = k + 10;
		}
		if (continua) {
			return montarArvore2(campeonato, newLista, partidaFilhos);
		}
		return true;
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
		p1.setSize(50, 2);
		p1.setLocation(xP1 + 200, yP1 + 35);
		p1.setLayout(null);
		p1.setBackground(UtilitarioTela.getFontColorPadrao());
		panel.add(p1);
	}

	public static void main(String[] args) {
		Campeonato campeonato = CampeonatoDao.getCampeonato(1);
		TelaMataMata tm = new TelaMataMata(campeonato, null);
	}
}
