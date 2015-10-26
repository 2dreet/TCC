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
	private Partida partidaSelecionada;
	private MenuCampeonato menuPai;
	private Campeonato campeonatoSelecionado;
	private JPanel header;
	private JLabel lblHeader;
	private JPanel meio;
	private JPanel proximaPartida;
	private Partida partida;
	private JPanel meioT1;
	
	/**
	 * Create the panel.
	 */
	public CrudPartida(Campeonato campeonato, MenuCampeonato menuPai) {
		this.menuPai = menuPai;
		setSize(UtilitarioTela.getTamanhoMeio());
		setLayout(null);
		setBackground(null);
		campeonatoSelecionado = campeonato;
		header = new JPanel();
		header.setSize(getWidth(), 30);
		header.setLocation(0, 0);
		header.setLayout(null);
		header.setBackground(UtilitarioTela.getColorCrud(ParametroCrud.getModoVisualizar()));
		header.setBorder(null);
		add(header);

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
		
		partida = PartidaDao.getProximaPartidaPartida(campeonatoSelecionado);
		JButton btProximaPartida = new JButton("Iniciar Proxima Partida");
		btProximaPartida.setSize(500, 30);
		btProximaPartida.setLocation((meio.getWidth()/2)-250, 20);
		btProximaPartida.setBackground(UtilitarioTela.getFontColorPadrao());
		btProximaPartida.setFocusPainted(false);
		btProximaPartida.setBorder(new BordaSombreada(false, true, false, false));
		btProximaPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirDialog();
			}
		});
		meio.add(btProximaPartida);
		
		meioT1 = new JPanel();
		meioT1.setSize(500, 70);
		meioT1.setLayout(null);
		meioT1.setLocation((meio.getWidth()/2)-250, 50);
		meioT1.setBackground(UtilitarioTela.getBtnFundo(false));
		meioT1.setBorder(new BordaSombreada(new Color(46, 49, 56), new Color(
				102, 102, 102)));
		meio.add(meioT1);
		
		mostrarProximaPartida();
		
	}
	public void abrirDialog(){
		Computador.stopPCconectado();
		partida = PartidaDao.getProximaPartidaPartida(campeonatoSelecionado);
		DialogCrudPartida dcp = new DialogCrudPartida(partida, this);
		mostrarProximaPartida();
	}
	
	public boolean iniciarWinerLower() {
		List<Partida> auxLista = PartidaDao
				.getPartidasNaoFinalizadasIniciadasWinnerLower(campeonatoSelecionado.getCodigoCampeonato());
		if (auxLista != null && auxLista.size() > 0) {
			return false;
		}
		return true;
	}
	
	public boolean iniciarWinnerLowerMataMata(){
		List<Partida> auxLista = PartidaDao
				.getPartidasNaoFinalizadasIniciadasGrupo(campeonatoSelecionado.getCodigoCampeonato());
		if (auxLista != null && auxLista.size() > 0) {
			return false;
		}
		return true;
	}
	
	public boolean mostrarProximaPartida(){
		partida = PartidaDao.getProximaPartidaPartida(campeonatoSelecionado);
		if(partida == null){
			if (campeonatoSelecionado.getChave()
					.getCodigoChave() == 2
					&& iniciarWinerLower()) {
				if(GerenciadorPartida.gerarLowers(campeonatoSelecionado)){
					Menssage.setMenssage(
							"Partidas Geradas",
							"As Partidas Winner Lower foram Geradas",
							ParametroCrud.getModoCrudNovo(), meio);
					mostrarProximaPartida();
				}
			}
			
			if (campeonatoSelecionado.getChave()
					.getCodigoChave() == 3
					&& iniciarWinnerLowerMataMata()) {
				if(GerenciadorPartida.adicionarPatidasDeGrupo(campeonatoSelecionado)){
					Menssage.setMenssage(
							"Partidas Geradas",
							"As Partidas Mata Mata foram Geradas",
							ParametroCrud.getModoCrudNovo(), meio);
				}
			}
			return false;
		}
		TimePartida timePartida = PartidaDao.getTimePartida(campeonatoSelecionado.getCodigoCampeonato(), partida.getCodigoPartida());
		
		if(timePartida.getTime1() == null || timePartida.getTime2() == null){
			timePartida.setTimeVencedor(timePartida.getTime1());
			partida.setAtivo(false);
			partida.setHoraFim(new Date());
			if (partida.getCampeonato().getChave()
					.getCodigoChave() == 1
					|| partida.getCampeonato().getChave()
							.getCodigoChave() == 2) {
				if (partida.getPartidaFilho() != null) {
					TimePartida time = PartidaDao.getTimePartidaTime1Null(
							partida.getCampeonato()
									.getCodigoCampeonato(),
									partida.getPartidaFilho()
									.getCodigoPartida());

					if (time == null) {
						time = PartidaDao.getTimePartidaTime2Null(
								partida.getCampeonato()
										.getCodigoCampeonato(),
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
			
			return mostrarProximaPartida();
		}
		
		meioT1.removeAll();
		JLabel lbLg = new JLabel(UtilitarioImagem.converterImage(timePartida
				.getTime1().getLogo()));
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
		
		JLabel lbLg2 = new JLabel(UtilitarioImagem.converterImage(timePartida
				.getTime2().getLogo()));
		lbLg2.setBounds(275, 10, 50, 50);
		lbLg2.setFont(UtilitarioTela.getFont(14));
		lbLg2.setForeground(UtilitarioTela.getFontColorPadrao());
		meioT1.add(lbLg2);

		JLabel lbNome2 = new JLabel(timePartida.getTime2().getDescricao());
		lbNome2.setBounds(330, 10, 150, 50);
		lbNome2.setFont(UtilitarioTela.getFont(14));
		lbNome2.setForeground(UtilitarioTela.getFontColorPadrao());
		meioT1.add(lbNome2);
		meioT1.repaint();
		return true;
	}
	
}
