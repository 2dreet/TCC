package componente;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import utilitario.BordaSombreada;
import utilitario.ParametroCrud;
import utilitario.Parametros;
import utilitario.UtilitarioTela;

public class MenssageConfirmacao {
	
	public static boolean confirmado;
	
	public static void setMenssage( String titulo, String menssage, int modoCrud, JPanel painelPai) {
		confirmado = false;
		JDialog dialog = new JDialog(Parametros.getPai(), true);
		dialog.setUndecorated(true);
		dialog.setLayout(null);
		dialog.setSize(400, 300);
		dialog.setLocationRelativeTo(painelPai);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new BordaSombreada(new Color(46, 49, 56), new Color(102, 102, 102)));
		panel.setLayout(null);
		panel.setSize(dialog.getSize());
		panel.setBackground(null);
		panel.setLocation(0, 0);
		panel.setFocusable(true);
		panel.requestFocusInWindow();
		
		JPanel panelMeio = new JPanel();
		panelMeio.setSize(396, 296);
		panelMeio.setLayout(null);
		panelMeio.setLocation(2, 2);
		panelMeio.setBackground(new Color(46, 49, 56));
		panel.add(panelMeio);

		JLabel lbTitulo = new JLabel(titulo);
		lbTitulo.setForeground(new Color(46, 49, 56));
		lbTitulo.setFont(UtilitarioTela.getFont(14));
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setSize(380,30);

		JPanel pTitulo = new JPanel();
		pTitulo.setLayout(null);
		pTitulo.setBounds(0, 0, 396, 30);
		pTitulo.setBackground(UtilitarioTela.getColorCrud(modoCrud));
		pTitulo.add(lbTitulo);
		pTitulo.setBorder(new BordaSombreada(new Color(46, 49, 56), new Color(102, 102, 102), false, true, false , false));
		panelMeio.add(pTitulo);
		
		JLabel lbMenssage = new JLabel("<html>"+menssage.replace("\n", "<BR>")+"</html>");
		lbMenssage.setForeground(UtilitarioTela.getFontColorPadrao());
		lbMenssage.setFont(UtilitarioTela.getFont(14));
		lbMenssage.setSize(380, 150);
		lbMenssage.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel pMenssage = new JPanel();
		pMenssage.setLayout(null);
		pMenssage.setBounds(8, 70, 380, 150);
		pMenssage.setBackground(null);
		pMenssage.add(lbMenssage);
		panelMeio.add(pMenssage);
		
		JLabel label = new JLabel(titulo);
		label.setForeground(new Color(46, 49, 56));
		label.setFont(UtilitarioTela.getFont(14));
		
		JButton confirmar = new JButton("Confirmar");
		confirmar.setSize(150,30);
		confirmar.setLocation(20, 250);
		confirmar.setBackground(UtilitarioTela.getColorCrud(ParametroCrud.getModoCrudNovo()));
		confirmar.setFocusPainted(false);
		confirmar.setIcon(new ImageIcon(Menssage.class.getResource("/imagem/ok.png")));
		confirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				confirmar();
				dialog.setVisible(false);
			}
		});
		
		JButton cancelar = new JButton("Cancelar");
		cancelar.setSize(150,30);
		cancelar.setLocation(223, 250);
		cancelar.setBackground(UtilitarioTela.getFundoLocalizar());
		cancelar.setFocusPainted(false);
		cancelar.setIcon(new ImageIcon(Menssage.class.getResource("/imagem/cancelBlack.png")));
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelar();
				dialog.setVisible(false);
			}
		});
		
		panelMeio.add(confirmar);
		panelMeio.add(cancelar);
		
		dialog.getContentPane().add(panel);
		dialog.setVisible(true);
	}
	
	public static void confirmar(){
		confirmado = true;
	}
	
	public static void cancelar(){
		confirmado = false;
	}
	
	public static boolean isConfirmado(){
		return confirmado;
	}
}
