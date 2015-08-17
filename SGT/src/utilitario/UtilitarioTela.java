package utilitario;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import tela.HomeFuncionario;

public class UtilitarioTela {

	public static Dimension getTamanhoMunitor(){
		Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
		Double height = dm.getHeight() - (dm.getHeight() * 0.1) ;
		Double width = dm.getWidth() - (dm.getWidth() * 0.1);
		dm.setSize(width, height);
		return dm;
	}
	
	public static Dimension getTamanhoMunitorJpanel(){
		Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
		Double height = dm.getHeight() - (dm.getHeight() * 0.1) - (10) ;
		Double width = dm.getWidth() - (dm.getWidth() * 0.1) - (10);
		dm.setSize(width, height);
		return dm;
	}
	
	public static Dimension getTamanhoMenuLateral(){
		Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
		Double height = (dm.getHeight() - (dm.getHeight() * 0.1) - (10)) - 160;
		Double width = 240.0;
		dm.setSize(width, height);
		return dm;
	}
	
	public static Dimension getTamanhoMeio(){
		Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
		Double height = (dm.getHeight() - (dm.getHeight() * 0.1) - (10)) - 160 ;
		Double width = (dm.getWidth() - (dm.getWidth() * 0.1) - (10)) - 250;
		dm.setSize(width, height);
		return dm;
	}

	public static Dimension getTamanhoMenuBaixo(){
		Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
		Double height = (dm.getHeight() - (dm.getHeight() * 0.1) - (10)) - 160 ;
		Double width = (dm.getWidth() - (dm.getWidth() * 0.1) - (10));
		dm.setSize(width, height);
		return dm;
	}
	
		public static LineBorder jTextFieldComFocus(){
		return new LineBorder(new Color( 255, 119, 0), 1);
	}
	
	public static LineBorder jTextFieldNormal(){
		return new LineBorder(new Color( 160, 160, 160), 1);
	}
	
	
	
}
