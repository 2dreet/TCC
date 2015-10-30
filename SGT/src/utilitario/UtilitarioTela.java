package utilitario;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

import net.miginfocom.layout.LayoutCallback;
import tela.HomeFuncionario;

public class UtilitarioTela {


	public static Dimension getTamanhoMunitorReal() {
		Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
		Double height = dm.getHeight();
		Double width = dm.getWidth();
		dm.setSize(width, height);
		return dm;
	}
	
	public static Dimension getTamanhoMunitor() {
		Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
		Double height = dm.getHeight() - (dm.getHeight() * 0.05);
		Double width = dm.getWidth();
		dm.setSize(width, height);
		return dm;
	}

	public static Dimension getTamanhoMunitorJpanel() {
		Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
		Double height = dm.getHeight() - (dm.getHeight() * 0.1) - (10);
		Double width = dm.getWidth() - 10;

		dm.setSize(width, height);
		return dm;
	}

	public static Dimension getTamanhoMenuLateral() {
		Dimension dm = getTamanhoMunitor();
		Double height = (dm.getHeight() - (dm.getHeight() * 0.1) - (10)) - 80;
		Double width = 240.0;
		
		dm.setSize(width, height);
		return dm;
	}

	public static Dimension getTamanhoMeio() {
		Dimension dm = getTamanhoMunitor();
		Double height = (dm.getHeight() - (dm.getHeight() * 0.1) - (10)) - 80;
		Double width = (dm.getWidth() - (10)) - 250;
		
		dm.setSize(width, height);
		return dm;
	}

	public static Dimension getTamanhoMenuBaixo() {
		Dimension dm = getTamanhoMunitor();
		Double height = (dm.getHeight() - (dm.getHeight() * 0.1) - (10)) - 80;
		Double width = (dm.getWidth() - (10));
		
		dm.setSize(width, height);
		return dm;
	}

	public static LineBorder jTextFieldComFocus() {
		return new LineBorder(new Color(255, 119, 0), 1);
	}

	public static LineBorder jTextFieldNormal() {
		return new LineBorder(new Color(160, 160, 160), 1);
	}

	public static Color getBtnFundo(boolean selecionado) {
		int r = 0, g = 0, b = 0;
		if (selecionado) {
			r = 252;
			g = 79;
			b = 63;
		} else {
			r = 46;
			g = 49;
			b = 56;
		}
		return new Color(r, g, b);
	}

	public static Color getFontColorSelecao(boolean selecionado) {
		int r = 0, g = 0, b = 0;

		if (!selecionado) {
			r = 252;
			g = 79;
			b = 63;
		} else {
			r = 46;
			g = 49;
			b = 56;
		}
		return new Color(r, g, b);
	}

	public static Color getFontColorCrud() {
		return new Color(46, 49, 56);
	}

	public static Color getFundoCrud() {
		return new Color(232,234,239);
	}
	
	public static Font getFont(int tamanho) {
		return new Font("SansSerif", Font.BOLD, tamanho);
	}
	
	public static Color getColorCrud(int modoCrud) {
		int r = 0, g = 0, b = 0;
		if (modoCrud == ParametroCrud.getModoCrudNovo()) {
			r = 153;
			g = 255;
			b = 153;
		} else if (modoCrud == ParametroCrud.getModoCrudAlterar()) {
			r = 255;
			g = 178;
			b = 102;
		} else if (modoCrud == ParametroCrud.getModoCrudDeletar()){
			r = 255;
			g = 153;
			b = 153;
		} else if (modoCrud == ParametroCrud.getModoVisualizar()){
			r = 255;
			g = 255;
			b = 255;
		}
		return new Color(r, g, b);
	}
	
	public static Color getFontColorPadrao(){
		return new Color(252, 79, 63);
	}
	
	public static ImageIcon getIconeLocalizar(){
		return new ImageIcon(UtilitarioTela.class.getResource("/imagem/localizar.png"));
	}
	
	public static Color getColorErro(){
		return new Color(252, 79, 63);
	}
	
	public static Dimension getTamanhoPorPorcional(double e, double f){
		int tamanhoIdealLargura = 1600;
		int tamanhoIdealAltura = 900;
		Dimension d = getTamanhoMunitor();
		
		double porcentagemLargura = ((e * 100)/tamanhoIdealLargura);
		double porcentagemAltura = ((f * 100)/tamanhoIdealAltura);
		
		d.setSize(d.getWidth()*(porcentagemLargura/100), d.getHeight()*(porcentagemAltura/100));
		return d;
	}
	
	public static double getLargura(int w){
		int tamanhoIdealLargura = 1600;
		Dimension d = getTamanhoMunitor();
		double porcentagemLargura = ((w * 100)/tamanhoIdealLargura);
		return  (d.getWidth()*(porcentagemLargura/100));
	}
	
	public static double getAltura(int h){
		int tamanhoIdealAltura = 900;
		Dimension d = getTamanhoMunitor();
		double porcentagemAltura = ((h * 100)/tamanhoIdealAltura);
		return  (d.getHeight()*(porcentagemAltura/100));
	}
	
	public static int getX(int x){
		int tamanhoIdealLargura = 1600;
		Dimension d = getTamanhoMunitor();
		double porcentagemAltura = ((x * 100)/tamanhoIdealLargura);
		return  (int)(d.getWidth()*(porcentagemAltura/100));
	}
	
	public static int getY(int y){
		int tamanhoIdealAltura = 900;
		Dimension d = getTamanhoMunitor();
		double porcentagemAltura = ((y * 100)/tamanhoIdealAltura);
		return (int) (d.getHeight()*(porcentagemAltura/100));
	}
	
	public static Color getFundoLocalizar(){
		return new Color(252, 79, 63);
	}
}
