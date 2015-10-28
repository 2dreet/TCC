package utilitario;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Teste extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Teste frame = new Teste();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Teste() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLayout(null);		
		getLigacao(10, 0, 80, 0, 200, 50);
		
		
	}
	
	public void getLigacao(int xP1, int xP2, int yP1, int yP2, int xF, int yF){
		JPanel p1 = new JPanel();
		p1.setSize(xF - ((xF - xP1)/2), 2);
		p1.setLocation(10,10);
		p1.setBackground(UtilitarioTela.getFontColorCrud());
		add(p1);
	}
	
	class MyCanvas extends Canvas {
		public void paint(Graphics graphics) {
			Graphics2D g = (Graphics2D) graphics;
			g.draw(new Line2D.Double(0.0, 0.0, 100.0, 100.0));
		}
	}
}
