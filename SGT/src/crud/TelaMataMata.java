package crud;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
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

public class TelaMataMata extends JPanel {

	private static Campeonato campeonatoSelecionado;

	/**
	 * @wbp.nonvisual location=220,49
	 */

	public TelaMataMata(Campeonato campeonato, JPanel pai) {
		campeonatoSelecionado = campeonato;

		JPanel header = new JPanel();
		header.setSize(pai.getWidth(), 30);
		header.setLocation(0, 0);
		header.setLayout(null);
		header.setBackground(UtilitarioTela.getColorCrud(ParametroCrud
				.getModoCrudAlterar()));
		header.setBorder(null);
		pai.add(header);

		JLabel lblHeader = new JLabel("Mata Matas");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setBounds(0, 0, header.getWidth(), header.getHeight());
		lblHeader.setFont(UtilitarioTela.getFont(14));
		lblHeader.setForeground(UtilitarioTela.getFontColorCrud());
		header.add(lblHeader);

		pai.add(new DrawCanvas(campeonatoSelecionado));

	}

	public Color getColorTime(TimePartida tp, Time t) {
		if (tp.getTimePerdedor() == t) {
			return Color.red;
		}
		return Color.BLUE;
	}
	
	public static void main(String [] args){
		Campeonato campeonato = CampeonatoDao.getCampeonato(1);
		Integer[] indices = PartidaDao
				.getIndicesPartidaParaGerarLowers(campeonato
						.getCodigoCampeonato());
		List<PojoMataMata> pojo = new ArrayList<PojoMataMata>();
		List<TimePartida> listaTimePartida = PartidaDao
				.getPartidasPorIndice(campeonato, 1, false);
		boolean continua = true;
		int tamanhoPai = listaTimePartida.size();
		Partida partidaPai1;
		Partida partidaPai2;
		List<PojoMataMata> partidaFilhos = new ArrayList<PojoMataMata>();
		PojoMataMata pm;
		for (int n = 0; n < tamanhoPai; n++) {
			pm = new PojoMataMata();
			if (tamanhoPai > n + 1) {
				partidaPai1 = listaTimePartida.get(n).getPartida();
				partidaPai2 = listaTimePartida.get(n + 1).getPartida();
				if (partidaPai1.getPartidaFilho() != null && partidaPai1.getPartidaFilho() == partidaPai2.getPartidaFilho()) {
					//Pai 1 tem o mesmo filho que Pai 2
					pm.setPartidaPai1(partidaPai1);
					pm.setPartidaPai2(partidaPai2);
					pm.setFilho(partidaPai1.getPartidaFilho());
					partidaFilhos.add(pm);
					System.out.println("Pai 1 : " + pm.getPartidaPai1().getCodigoPartida());
					System.out.println("Pai 2 : " + pm.getPartidaPai2().getCodigoPartida());
					System.out.println("Filho : " + pm.getFilho().getCodigoPartida());
					System.out.println("--------------------------------------------------");
					n++;
				} else {
					
				}
			}
		}
	}

	private class DrawCanvas extends JPanel {

		private Campeonato campeonato;

		public DrawCanvas(Campeonato campeonato) {
			this.campeonato = campeonato;
			
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.BLACK);
			// g.drawLine(partida.getX(), partida.getY()+25, partida.getWidth()
			// + 50, hy);
		}
	}
	
}
