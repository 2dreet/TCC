package dialog;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import localizar.LocalizarPc;

import org.omg.CORBA.TCKind;

import componente.ComboBox;
import componente.Menssage;
import componente.MenssageConfirmacao;
import dao.CampeonatoDao;
import dao.EntityManagerLocal;
import dao.JogadorDao;
import dao.MarcaDao;
import dao.PartidaDao;
import dao.PcDao;
import dao.PerifericoDao;
import tela.HomeFuncionario;
import utilitario.BordaSombreada;
import utilitario.Computador;
import utilitario.GerenciadorPartida;
import utilitario.ParametroCrud;
import utilitario.Parametros;
import utilitario.UtilitarioImagem;
import utilitario.UtilitarioLogo;
import utilitario.UtilitarioTela;
import utilitario.ValidadorCrud;
import entidade.Driver;
import entidade.Jogador;
import entidade.JogadorPartida;
import entidade.Marca;
import entidade.Partida;
import entidade.PcPartida;
import entidade.Periferico;
import entidade.Time;
import entidade.TimePartida;

public class DialogCrudPartida {

	public JTextField txtPlacar;
	public JTextField txtPlacar2;
	private boolean confirmado;
	private boolean partidaIniciada;
	private Partida partidaSelecionado;
	private JLabel lblMsg;
	private JPanel msg;
	private JPanel meio;
	private JPanel meioTime;
	private JFrame pai;

	private TimePartida timePartida;
	private List<Container> pcs;

	private List<Jogador> jogadores;

	private JPanel jpPc0;
	private JPanel jpPc1;
	private JPanel jpPc2;
	private JPanel jpPc3;
	private JPanel jpPc4;
	private JPanel jpPc5;
	private JPanel jpPc6;
	private JPanel jpPc7;
	private JPanel jpPc8;
	private JPanel jpPc9;
	private JPanel header;

	private JButton confirmar;
	private JButton btPcPartida;

	public DialogCrudPartida(Partida partida) {
		JFrame jf = new JFrame();
		jf.setSize(455, 555);
		Parametros.setPai(jf);
		crudPartida(partida, jf);

	}

	public void crudPartida(Partida partidaSelecionado, JFrame jf) {
		this.partidaSelecionado = partidaSelecionado;
		pcs = new ArrayList<Container>();
		jogadores = new ArrayList<Jogador>();
		pai = jf;
		timePartida = PartidaDao.getTimePartida(partidaSelecionado
				.getCampeonato().getCodigoCampeonato(), partidaSelecionado
				.getCodigoPartida());

		jf.setUndecorated(true);
		jf.getContentPane().setLayout(null);
		jf.setSize(Parametros.getPai().getSize());
		jf.getContentPane().setBackground(new Color(232, 234, 239));
		jf.setLocationRelativeTo(Parametros.getPai());

		JPanel panel = new JPanel();
		panel.setBorder(new BordaSombreada(new Color(46, 49, 56), new Color(
				102, 102, 102)));
		panel.setLayout(null);
		panel.setSize(jf.getSize());
		panel.setBackground(null);
		panel.setLocation(0, 0);
		panel.setFocusable(true);
		panel.requestFocusInWindow();

		header = new JPanel();
		header.setSize(jf.getWidth() - 4, 30);
		header.setLayout(null);
		header.setLocation(2, 2);
		header.setBackground(UtilitarioTela.getColorCrud(ParametroCrud
				.getModoVisualizar()));
		header.setBorder(null);
		panel.add(header);

		if (partidaSelecionado.getHoraInicio() == null) {
			partidaIniciada = false;
		} else {
			partidaIniciada = true;
		}

		String textoHeader = "";
		if (partidaIniciada) {
			textoHeader = "Partida Iniciada!";
		} else {
			textoHeader = "Partida não Iniciada!";
		}

		JLabel lbHeader = new JLabel(textoHeader);
		lbHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lbHeader.setSize(header.getSize().width - 30, 30);
		lbHeader.setFont(UtilitarioTela.getFont(14));
		lbHeader.setForeground(UtilitarioTela.getFontColorCrud());
		lbHeader.setBorder(new BordaSombreada(false, true, false, false));
		header.add(lbHeader);

		JButton btFechar = new JButton("");
		btFechar.setIcon(new ImageIcon(HomeFuncionario.class
				.getResource("/imagem/cancelBlack.png")));
		btFechar.setSize(30, 30);
		btFechar.setLocation(header.getSize().width - 30, 0);
		btFechar.setBackground(UtilitarioTela.getFontColorPadrao());
		btFechar.setFocusPainted(false);
		btFechar.setBorder(new BordaSombreada(false, true, false, false));
		btFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jf.dispose();
			}
		});
		header.add(btFechar);

		meio = new JPanel();
		meio.setSize(panel.getSize().width - 4, panel.getSize().height - 32);
		meio.setLayout(null);
		meio.setLocation(2, 30);
		meio.setBackground(new Color(232, 234, 239));
		panel.add(meio);

		meioTime = new JPanel();
		meioTime.setSize(430, 300);
		meioTime.setLayout(null);
		meioTime.setLocation(10, 210);
		meioTime.setBackground(UtilitarioTela.getBtnFundo(false));
		meioTime.setBorder(new BordaSombreada(new Color(46, 49, 56), new Color(
				102, 102, 102)));
		meio.add(meioTime);

		JPanel meioT1 = new JPanel();
		meioT1.setSize(430, 70);
		meioT1.setLayout(null);
		meioT1.setLocation(10, 50);
		meioT1.setBackground(UtilitarioTela.getBtnFundo(false));
		meioT1.setBorder(new BordaSombreada(new Color(46, 49, 56), new Color(
				102, 102, 102)));
		meio.add(meioT1);

		JLabel lbLg = new JLabel(UtilitarioImagem.converterImage(timePartida
				.getTime1().getLogo()));
		lbLg.setBounds(70, 10, 50, 50);
		lbLg.setFont(UtilitarioTela.getFont(14));
		lbLg.setForeground(UtilitarioTela.getFontColorPadrao());
		meioT1.add(lbLg);

		JLabel lbNome = new JLabel(timePartida.getTime1().getDescricao());
		lbNome.setBounds(130, 10, 250, 50);
		lbNome.setFont(UtilitarioTela.getFont(14));
		lbNome.setForeground(UtilitarioTela.getFontColorPadrao());
		meioT1.add(lbNome);

		txtPlacar = new JTextField();
		txtPlacar.setColumns(100);
		txtPlacar.setBounds(10, 10, 50, 50);
		txtPlacar.setFont(UtilitarioTela.getFont(30));
		txtPlacar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				ValidadorCrud.campoInt(arg0, txtPlacar2.getText());
			}

		});
		txtPlacar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtPlacar.setBorder(UtilitarioTela.jTextFieldComFocus());
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				txtPlacar.setBorder(UtilitarioTela.jTextFieldNormal());
			}
		});
		txtPlacar.setLayout(null);
		txtPlacar.setBorder(UtilitarioTela.jTextFieldNormal());
		meioT1.add(txtPlacar);

		JPanel meioT2 = new JPanel();
		meioT2.setSize(430, 70);
		meioT2.setLayout(null);
		meioT2.setLocation(10, 130);
		meioT2.setBackground(UtilitarioTela.getBtnFundo(false));
		meioT2.setBorder(new BordaSombreada(new Color(46, 49, 56), new Color(
				102, 102, 102)));
		meio.add(meioT2);

		JLabel lbLg2 = new JLabel(UtilitarioImagem.converterImage(timePartida
				.getTime2().getLogo()));
		lbLg2.setBounds(70, 10, 50, 50);
		lbLg2.setFont(UtilitarioTela.getFont(14));
		lbLg2.setForeground(UtilitarioTela.getFontColorPadrao());
		meioT2.add(lbLg2);

		JLabel lbNome2 = new JLabel(timePartida.getTime2().getDescricao());
		lbNome2.setBounds(130, 10, 300, 50);
		lbNome2.setFont(UtilitarioTela.getFont(14));
		lbNome2.setForeground(UtilitarioTela.getFontColorPadrao());
		meioT2.add(lbNome2);

		txtPlacar2 = new JTextField();
		txtPlacar2.setColumns(100);
		txtPlacar2.setBounds(10, 10, 50, 50);
		txtPlacar2.setFont(UtilitarioTela.getFont(30));
		txtPlacar2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				ValidadorCrud.campoInt(arg0, txtPlacar2.getText());
			}

		});
		txtPlacar2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtPlacar2.setBorder(UtilitarioTela.jTextFieldComFocus());
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				txtPlacar2.setBorder(UtilitarioTela.jTextFieldNormal());
			}
		});
		txtPlacar2.setLayout(null);
		txtPlacar2.setBorder(UtilitarioTela.jTextFieldNormal());
		meioT2.add(txtPlacar2);

		msg = new JPanel();
		msg.setSize(396, 35);
		msg.setLocation(0, 70);
		msg.setLayout(null);
		msg.setBackground(null);
		meio.add(msg);

		String texto = "";

		if (partidaIniciada) {
			texto = "Terminar";
		} else {
			texto = "Iniciar";
		}

		confirmar = new JButton(texto);
		confirmar.setSize(150, 30);
		confirmar.setLocation(10, 10);
		confirmar.setBackground(UtilitarioTela.getColorCrud(ParametroCrud
				.getModoCrudNovo()));
		confirmar.setFocusPainted(false);
		confirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (confirmar()) {
					jf.setVisible(false);
				}
			}
		});

		texto = "";
		if (partidaIniciada) {
			texto = "Cancelar Partida";
		} else {
			texto = "PC Partida";
		}

		btPcPartida = new JButton(texto);
		btPcPartida.setSize(150, 30);
		btPcPartida.setLocation(290, 10);
		btPcPartida.setBackground(UtilitarioTela.getFundoLocalizar());
		btPcPartida.setFocusPainted(false);
		btPcPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (partidaIniciada) {

				} else {
					DialogPcPartida lp = new DialogPcPartida();
					lp.localizarPc(jf.getContentPane(), partidaSelecionado);
				}
			}
		});

		meio.add(confirmar);
		meio.add(btPcPartida);

		atualiarPlacar();

		atualizarPc();

		jf.getContentPane().add(panel);
		jf.setVisible(true);
		if (PcDao.getListaPcPartida(partidaSelecionado.getCodigoPartida()) == null
				|| PcDao.getListaPcPartida(
						partidaSelecionado.getCodigoPartida()).size() < 10) {
			Menssage.setMenssage("Numero Pc's",
					"Deve Adicionar Mais PC's Para Iniciar Partida!",
					ParametroCrud.getModoCrudDeletar(), meio);
			DialogPcPartida lp = new DialogPcPartida();
			lp.localizarPc(jf.getContentPane(), partidaSelecionado);
		}
	}

	public void atualiarPlacar() {
		if (partidaIniciada) {
			txtPlacar.setEditable(true);
			txtPlacar2.setEditable(true);
		} else {
			txtPlacar.setEditable(false);
			txtPlacar2.setEditable(false);
		}
	}

	public void limparPc() {
		pcs = new ArrayList<Container>();

		jpPc0 = new JPanel();
		jpPc0.setSize(200, 50);
		jpPc0.setLayout(null);
		jpPc0.setLocation(10, 10);
		jpPc0.setBackground(UtilitarioTela.getBtnFundo(false));
		jpPc0.setBorder(null);
		meioTime.add(jpPc0);
		pcs.add(jpPc0);

		jpPc1 = new JPanel();
		jpPc1.setSize(200, 50);
		jpPc1.setLayout(null);
		jpPc1.setLocation(10, 70);
		jpPc1.setBackground(UtilitarioTela.getBtnFundo(false));
		jpPc1.setBorder(null);
		meioTime.add(jpPc1);
		pcs.add(jpPc1);

		jpPc2 = new JPanel();
		jpPc2.setSize(200, 50);
		jpPc2.setLayout(null);
		jpPc2.setLocation(10, 130);
		jpPc2.setBackground(UtilitarioTela.getBtnFundo(false));
		jpPc2.setBorder(null);
		meioTime.add(jpPc2);
		pcs.add(jpPc2);

		jpPc3 = new JPanel();
		jpPc3.setSize(200, 50);
		jpPc3.setLayout(null);
		jpPc3.setLocation(10, 190);
		jpPc3.setBackground(UtilitarioTela.getBtnFundo(false));
		jpPc3.setBorder(null);
		meioTime.add(jpPc3);
		pcs.add(jpPc3);

		jpPc4 = new JPanel();
		jpPc4.setSize(200, 50);
		jpPc4.setLayout(null);
		jpPc4.setLocation(10, 250);
		jpPc4.setBackground(UtilitarioTela.getBtnFundo(false));
		jpPc4.setBorder(null);
		meioTime.add(jpPc4);
		pcs.add(jpPc4);

		jpPc5 = new JPanel();
		jpPc5.setSize(200, 50);
		jpPc5.setLayout(null);
		jpPc5.setLocation(220, 10);
		jpPc5.setBackground(UtilitarioTela.getBtnFundo(false));
		jpPc5.setBorder(null);
		meioTime.add(jpPc5);
		pcs.add(jpPc5);

		jpPc6 = new JPanel();
		jpPc6.setSize(200, 50);
		jpPc6.setLayout(null);
		jpPc6.setLocation(220, 70);
		jpPc6.setBackground(UtilitarioTela.getBtnFundo(false));
		jpPc6.setBorder(null);
		meioTime.add(jpPc6);
		pcs.add(jpPc6);

		jpPc7 = new JPanel();
		jpPc7.setSize(200, 50);
		jpPc7.setLayout(null);
		jpPc7.setLocation(220, 130);
		jpPc7.setBackground(UtilitarioTela.getBtnFundo(false));
		jpPc7.setBorder(null);
		meioTime.add(jpPc7);
		pcs.add(jpPc7);

		jpPc8 = new JPanel();
		jpPc8.setSize(200, 50);
		jpPc8.setLayout(null);
		jpPc8.setLocation(220, 190);
		jpPc8.setBackground(UtilitarioTela.getBtnFundo(false));
		jpPc8.setBorder(null);
		meioTime.add(jpPc8);
		pcs.add(jpPc8);

		jpPc9 = new JPanel();
		jpPc9.setSize(200, 50);
		jpPc9.setLayout(null);
		jpPc9.setLocation(220, 250);
		jpPc9.setBackground(UtilitarioTela.getBtnFundo(false));
		jpPc9.setBorder(null);
		meioTime.add(jpPc9);
		pcs.add(jpPc9);

	}

	public void cancelar() {
		this.confirmado = false;
	}

	public static boolean iniciarPartida;

	public void atualizarPc() {
		limparPc();
		Computador.t2 = new Thread() {
			@Override
			public void run() {
				try {
					while (true) {

						meio.repaint();
						List<PcPartida> listaPc = PcDao
								.getListaPcPartida(partidaSelecionado
										.getCodigoPartida());
						for (int i = 0; i < listaPc.size(); i++) {
							Jogador jogador = Computador
									.verificaJogadorLogado(listaPc.get(i)
											.getPc());
							Container parent = pcs.get(i);
							parent.removeAll();
							JLabel lbPc = null;
							JLabel lbcJogador;
							JLabel lbcIp;
							if (jogador == null) {
								lbPc = new JLabel(
										new ImageIcon(
												Computador.class
														.getResource("/imagem/pcoff.png")));

								lbcJogador = new JLabel("");
								lbcJogador.setFont(UtilitarioTela.getFont(12));
								lbcJogador.setForeground(UtilitarioTela
										.getFontColorPadrao());
								lbcJogador.setBounds(40, 0, 160, 15);

								lbcIp = new JLabel("");
								lbcIp.setFont(UtilitarioTela.getFont(12));
								lbcIp.setForeground(UtilitarioTela
										.getFontColorPadrao());
								lbcIp.setBounds(40, 16, 160, 15);
								jogadores = new ArrayList<Jogador>();
							} else {
								if (jogadores.size() < 10) {
									jogadores.add(jogador);
								}
								lbPc = new JLabel(
										new ImageIcon(
												Computador.class
														.getResource("/imagem/pcon.png")));

								lbcJogador = new JLabel(""
										+ jogador.getUsuario()
												.getCodigoUsuario() + " - "
										+ jogador.getUsuario().getNome());
								lbcJogador.setFont(UtilitarioTela.getFont(12));
								lbcJogador.setForeground(UtilitarioTela
										.getFontColorPadrao());
								lbcJogador.setBounds(40, 0, 160, 15);

								lbcIp = new JLabel(""
										+ listaPc.get(i).getPc().getIp());
								lbcIp.setFont(UtilitarioTela.getFont(12));
								lbcIp.setForeground(UtilitarioTela
										.getFontColorPadrao());
								lbcIp.setBounds(40, 16, 160, 15);

							}
							lbPc.setBounds(0, 0, 35, 35);
							parent.add(lbPc);
							parent.add(lbcJogador);
							parent.add(lbcIp);
						}
						meio.repaint();
						sleep(5000);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		Computador.t2.start();
	}

	public void atualizarJogadorLogado() {

	}

	public boolean confirmar() {
		boolean confirmado = false;
		if (partidaIniciada) {
			if (txtPlacar.getText() == null || txtPlacar.getText().isEmpty()) {
				Menssage.setMenssage("Erro", "Placar é Obrigatório!",
						ParametroCrud.getModoCrudDeletar(), meio);
				txtPlacar.requestFocus();
				return false;
			} else if (txtPlacar2.getText() == null
					|| txtPlacar2.getText().isEmpty()) {
				Menssage.setMenssage("Erro", "Placar é Obrigatório!",
						ParametroCrud.getModoCrudDeletar(), meio);
				txtPlacar2.requestFocus();
				return false;
			} else if (valorEmpatado(Integer.parseInt(txtPlacar.getText()),
					Integer.parseInt(txtPlacar2.getText()))) {
				Menssage.setMenssage("Erro",
						"Placares devem serem diferentes!",
						ParametroCrud.getModoCrudDeletar(), meio);
				txtPlacar.requestFocus();
				return false;
			}

			MenssageConfirmacao.setMenssage("Finalizar Partida",
					"Confirma a Finalização da partida?",
					ParametroCrud.getModoCrudDeletar(), meio);
			confirmado = MenssageConfirmacao.isConfirmado();

			if (confirmado) {
				getTimeVencedor(Integer.parseInt(txtPlacar.getText()),
						Integer.parseInt(txtPlacar2.getText()));
				partidaSelecionado.setAtivo(false);
				partidaSelecionado.setHoraFim(new Date());
				if (partidaSelecionado.getCampeonato().getChave()
						.getCodigoChave() == 1) {
					if (partidaSelecionado.getPartidaFilho() != null) {
						TimePartida time = PartidaDao.getTimePartidaTime1Null(
								partidaSelecionado.getCampeonato()
										.getCodigoCampeonato(),
								partidaSelecionado.getPartidaFilho().getCodigoPartida());

						if (time == null) {
							time = PartidaDao.getTimePartidaTime2Null(
									partidaSelecionado.getCampeonato()
											.getCodigoCampeonato(),
											partidaSelecionado.getPartidaFilho().getCodigoPartida());
							time.setTime2(timePartida.getTimeVencedor());
						} else {
							time.setTime1(timePartida.getTimeVencedor());
						}

						EntityManagerLocal.begin();
						EntityManagerLocal.merge(time);
						EntityManagerLocal.commit();
						EntityManagerLocal.clear();
					}
				} else if (partidaSelecionado.getCampeonato().getChave()
						.getCodigoChave() == 2) {
					if (partidaSelecionado.getPartidaFilho() != null) {
						TimePartida time = PartidaDao.getTimePartidaTime1Null(
								partidaSelecionado.getCampeonato()
										.getCodigoCampeonato(),
										partidaSelecionado.getPartidaFilho().getCodigoPartida());

						if (time == null) {
							time = PartidaDao.getTimePartidaTime2Null(
									partidaSelecionado.getCampeonato()
											.getCodigoCampeonato(),
											partidaSelecionado.getPartidaFilho().getCodigoPartida());
							time.setTime2(timePartida.getTimeVencedor());
						} else {
							time.setTime1(timePartida.getTimeVencedor());
						}

						EntityManagerLocal.begin();
						EntityManagerLocal.merge(time);
						EntityManagerLocal.commit();
						EntityManagerLocal.clear();
					}
					if (iniciarWinerLower()) {
						
					}
				}

				EntityManagerLocal.begin();
				EntityManagerLocal.merge(partidaSelecionado);
				EntityManagerLocal.merge(timePartida);
				EntityManagerLocal.commit();
				EntityManagerLocal.clear();
			}
		} else {
			if (jogadores.size() == 10) {
				for (Jogador jogador : jogadores) {
					JogadorPartida jp = new JogadorPartida();
					jp.setJogador(jogador);
					jp.setPartida(partidaSelecionado);

					partidaSelecionado.setHoraInicio(new Date());
					EntityManagerLocal.begin();
					EntityManagerLocal.merge(jp);
					EntityManagerLocal.merge(partidaSelecionado);
					EntityManagerLocal.commit();
					EntityManagerLocal.clear();
					atualizarTela();
				}
			} else {
				Menssage.setMenssage(
						"Não Poder Iniciar",
						"Não pode Inicar Partida Pois\nPois falta Jogadores logado!\nJogadores Logados "
								+ jogadores.size(),
						ParametroCrud.getModoCrudDeletar(), meio);
			}

		}
		return confirmado;
	}

	public boolean iniciarWinerLower() {
		List<Partida> auxLista = PartidaDao
				.getPartidasNaoFinalizadasIniciadasMataMata(partidaSelecionado
						.getCampeonato().getCodigoCampeonato());
		if (auxLista != null && auxLista.size() > 0) {
			return false;
		}
		return true;
	}

	public void atualizarTela() {
		atualiarPlacar();
		partidaIniciada = true;
		header.removeAll();
		JLabel lbHeader = new JLabel("Partida Iniciada");
		lbHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lbHeader.setSize(header.getSize().width - 30, 30);
		lbHeader.setFont(UtilitarioTela.getFont(14));
		lbHeader.setForeground(UtilitarioTela.getFontColorCrud());
		lbHeader.setBorder(new BordaSombreada(false, true, false, false));
		header.add(lbHeader);

		JButton btFechar = new JButton("");
		btFechar.setIcon(new ImageIcon(HomeFuncionario.class
				.getResource("/imagem/cancelBlack.png")));
		btFechar.setSize(30, 30);
		btFechar.setLocation(header.getSize().width - 30, 0);
		btFechar.setBackground(UtilitarioTela.getFontColorPadrao());
		btFechar.setFocusPainted(false);
		btFechar.setBorder(new BordaSombreada(false, true, false, false));
		btFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pai.dispose();
			}
		});
		header.add(btFechar);

		header.repaint();
		btPcPartida.setText("Cancelar Partida");
		confirmar.setText("Terminar");
	}

	public boolean getConfirmado() {
		return this.confirmado;
	}

	public boolean valorEmpatado(int valor1, int valor2) {
		if (valor1 == valor2) {
			return true;
		}
		return false;
	}

	public void getTimeVencedor(int valor1, int valor2) {

		if (valor1 > valor2) {
			timePartida.setPlacarTimeVencedor(valor1);
			timePartida.setPlacarTimePerdedor(valor2);
			timePartida.setTimeVencedor(timePartida.getTime1());
			timePartida.setTimePerdedor(timePartida.getTime2());
		} else {
			timePartida.setPlacarTimeVencedor(valor2);
			timePartida.setPlacarTimePerdedor(valor1);
			timePartida.setTimeVencedor(timePartida.getTime2());
			timePartida.setTimePerdedor(timePartida.getTime1());
		}
	}

	public void pcOff(Container parent) {
		JLabel lbPc;
		lbPc = new JLabel(new ImageIcon(
				Computador.class.getResource("/imagem/pcoff.png")));
		lbPc.setBounds(10, 0, 35, 35);

		JLabel lbcJogador;
		lbcJogador = new JLabel("Jogador: ");
		lbcJogador.setFont(UtilitarioTela.getFont(12));
		lbcJogador.setForeground(UtilitarioTela.getFontColorPadrao());
		lbcJogador.setBounds(100, 0, 300, 15);

		JLabel lbcIp;
		lbcIp = new JLabel("Pc IP: ");
		lbcIp.setFont(UtilitarioTela.getFont(12));
		lbcIp.setForeground(UtilitarioTela.getFontColorPadrao());
		lbcIp.setBounds(100, 16, 200, 15);

		parent.add(lbPc);
		parent.add(lbcJogador);
		parent.add(lbcIp);
	}

	public static void main(String[] args) {
		//GerenciadorPartida.gerarLowers(CampeonatoDao.getCampeonato(1));
		
	}
}
