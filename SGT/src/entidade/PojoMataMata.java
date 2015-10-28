package entidade;

import javax.swing.JDialog;
import javax.swing.JPanel;

public class PojoMataMata {

	public Partida partidaPai1;
	public Partida partidaPai2;
	public Partida filho;
	
	
	public JPanel panelPai1;
	public JPanel panelPai2;
	public JPanel panelFilho;
	public Partida getPartidaPai1() {
		return partidaPai1;
	}
	public void setPartidaPai1(Partida partidaPai1) {
		this.partidaPai1 = partidaPai1;
	}
	public Partida getPartidaPai2() {
		return partidaPai2;
	}
	public void setPartidaPai2(Partida partidaPai2) {
		this.partidaPai2 = partidaPai2;
	}
	public Partida getFilho() {
		return filho;
	}
	public void setFilho(Partida filho) {
		this.filho = filho;
	}
	public JPanel getPanelPai1() {
		return panelPai1;
	}
	public void setPanelPai1(JPanel panelPai1) {
		this.panelPai1 = panelPai1;
	}
	public JPanel getPanelPai2() {
		return panelPai2;
	}
	public void setPanelPai2(JPanel panelPai2) {
		this.panelPai2 = panelPai2;
	}
	public JPanel getPanelFilho() {
		return panelFilho;
	}
	public void setPanelFilho(JPanel panelFilho) {
		this.panelFilho = panelFilho;
	}
	
	
}
