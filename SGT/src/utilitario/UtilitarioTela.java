package utilitario;

import java.awt.Dimension;
import java.awt.Toolkit;

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
}
