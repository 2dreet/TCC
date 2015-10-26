package crud;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import utilitario.ParametroCrud;
import utilitario.Parametros;
import utilitario.UtilitarioTela;
import entidade.Campeonato;

public class TelaGrupos extends JPanel {

	private static Campeonato campeonatoSelecionado;
	
	/**
	 * @wbp.nonvisual location=220,49
	 */
	
	public TelaGrupos(Campeonato campeonato, JPanel pai) {
		campeonatoSelecionado = campeonato;

		JPanel header = new JPanel();
		header.setSize(pai.getWidth(), 30);
		header.setLocation(0, 0);
		header.setLayout(null);
		header.setBackground(UtilitarioTela.getColorCrud(ParametroCrud
				.getModoCrudAlterar()));
		header.setBorder(null);
		pai.add(header);

		JLabel lblHeader = new JLabel("Grupos");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setBounds(0, 0, header.getWidth(), header.getHeight());
		lblHeader.setFont(UtilitarioTela.getFont(14));
		lblHeader.setForeground(UtilitarioTela.getFontColorCrud());
		header.add(lblHeader);
		
		JPanel gui = new JPanel(new BorderLayout(3,3));
        final JPanel panel = new JPanel(new GridLayout(0,1));
        JScrollPane scroll = new JScrollPane(panel);
        scroll.setPreferredSize(new Dimension(80,100));
        gui.add(scroll, BorderLayout.CENTER);
        for(int i = 0; i < 40; i++){
        		panel.add(new JLabel("Label " + ++i));
                panel.revalidate();
                int height = (int)panel.getPreferredSize().getHeight();
                Rectangle rect = new Rectangle(0,height,10,10);
                panel.scrollRectToVisible(rect);
        }
        gui.setLocation(5, 35);
        gui.setSize(200, 200);
        pai.add(gui);
	}
}

