package menu;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import utilitario.BordaEscura;
import utilitario.UtilitarioTela;

public class MenuDriver extends JPanel {

	/**
	 * Create the panel.
	 */
	public MenuDriver() {
		setSize(UtilitarioTela.getTamanhoMenuBaixo());
		setBackground(null);
		setLayout(null);
		setVisible(true);

		
		JPanel menuLateral = new JPanel();
		menuLateral.setSize(UtilitarioTela.getTamanhoMenuLateral());
		menuLateral.setBorder(new BordaEscura());
		menuLateral.setBackground(new Color(252, 79, 63));
		menuLateral.setLocation(0, 0);
		add(menuLateral);
		menuLateral.setLayout(null);
		
		JPanel menuLateralTopo = new JPanel();
		menuLateralTopo.setBounds(0, 0, 240, 30);
		menuLateralTopo.setBorder(new BordaEscura());
		menuLateralTopo.setBackground(new Color(252, 79, 63));
		menuLateral.add(menuLateralTopo);
		menuLateralTopo.setLayout(null);

		JLabel tituloMenu = new JLabel("Driver");
		tituloMenu.setHorizontalAlignment(SwingConstants.CENTER);
		tituloMenu.setBounds(0, 0, 240, 25);
		menuLateralTopo.add(tituloMenu);

		JPanel menuMeio = new JPanel();
		menuMeio.setSize(UtilitarioTela.getTamanhoMeio());
		menuMeio.setLocation(250, 0);
		menuMeio.setBackground(new Color(46, 49, 56));
		add(menuMeio);
		
		JPanel menuLateralBaixo = new JPanel();
		menuLateralBaixo.setBounds(0, 30, 240,
				menuLateral.getHeight() - 30);
		menuLateralBaixo.setBackground(new Color(46, 49, 56));
		menuLateral.add(menuLateralBaixo);

		JPanel jp1 = new JPanel();
		jp1.setBounds(0, 0, getWidth(), 40);
		jp1.setBackground(null);
		jp1.setLayout(null);
		jp1.setBorder(new BordaEscura());
		menuLateral.add(jp1);
		
		JPanel jp2 = new JPanel();
		jp2.setBounds(0, 40, getWidth(), 40);
		jp2.setBackground(null);
		jp2.setLayout(null);
		jp2.setBorder(new BordaEscura());
		menuLateral.add(jp2);
		
		JPanel jp3 = new JPanel();
		jp3.setBounds(0, 80, getWidth(), 40);
		jp3.setBackground(null);
		jp3.setLayout(null);
		jp3.setBorder(new BordaEscura());
		menuLateral.add(jp3);
		
		JPanel jp4 = new JPanel();
		jp4.setBounds(0, 120, getWidth(), 40);
		jp4.setBackground(null);
		jp4.setLayout(null);
		jp4.setBorder(new BordaEscura());
		menuLateral.add(jp4);
		
		JPanel jp5 = new JPanel();
		jp5.setBounds(0, 160, getWidth(), 40);
		jp5.setBackground(null);
		jp5.setLayout(null);
		jp5.setBorder(new BordaEscura());
		menuLateral.add(jp5);
		
		JPanel jp6 = new JPanel();
		jp6.setBounds(0, 200, getWidth(), 40);
		jp6.setBackground(null);
		jp6.setLayout(null);
		jp6.setBorder(new BordaEscura());
		menuLateral.add(jp6);
		
		JPanel jp7 = new JPanel();
		jp7.setBounds(0, 240, getWidth(), 40);
		jp7.setBackground(null);
		jp7.setLayout(null);
		jp7.setBorder(new BordaEscura());
		menuLateral.add(jp7);
		
	}

}
