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
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import dao.PartidaDao;
import utilitario.BordaSombreada;
import utilitario.ParametroCrud;
import utilitario.Parametros;
import utilitario.UtilitarioImagem;
import utilitario.UtilitarioTela;
import entidade.Campeonato;
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

		Integer[] indices = PartidaDao
				.getIndicesPartidaParaGerarLowers(campeonato
						.getCodigoCampeonato());
		
		int x = 0;
		for (int i : indices) {
			int y = 35 * i;
			List<TimePartida>listaTimePartida = PartidaDao.getPartidasPorIndice(campeonato, i, false);
			for(TimePartida tp : listaTimePartida){
				JPanel partida = new JPanel();
				partida.setSize(100, 50);
				partida.setLocation(x , y);
				partida.setLayout(null);
				partida.setBackground(null);

				JLabel lbNome = new JLabel(tp.getTime1().getDescricao());
				lbNome.setBounds(0, 10, 100, 10);
				lbNome.setFont(UtilitarioTela.getFont(10));
				lbNome.setForeground(getColorTime(tp, tp.getTime1()));
				partida.add(lbNome);
				
				JLabel lbNome2 = new JLabel(tp.getTime2().getDescricao());
				lbNome2.setBounds(0, 30, 100, 10);
				lbNome2.setFont(UtilitarioTela.getFont(10));
				lbNome2.setForeground(getColorTime(tp, tp.getTime2()));
				partida.add(lbNome2);
				pai.add(partida);
				
				y = y + (55) * i;
			}
			x = x + 110;
		}
		
	}

	
	
	public Color getColorTime(TimePartida tp, Time t){
		if(tp.getTimePerdedor() == t){
			return Color.red;
		}
		return Color.BLUE;
	}
	
	private class DrawCanvas extends JPanel {

		private int x = 0;
		private int y = 0;
		private int wx = 0;
		private int hy = 0;

		public DrawCanvas(int x, int y, int wx, int hy) {
			this.x = x;
			this.y = y;
			this.wx = wx;
			this.hy = hy;
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.BLACK);
			g.drawLine(x, y, wx, hy);
		}
	}
}
