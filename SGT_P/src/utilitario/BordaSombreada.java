package utilitario;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

public class BordaSombreada implements Border {

	private int modoCrud = 0;
	private Color linhaDireita;
	private Color linhaEsquerda;
	private boolean topo;
	private boolean baixo;
	private boolean direita;
	private boolean esquerda;
	
	public BordaSombreada(int modoCrud) {
		this.modoCrud = modoCrud;
		this.linhaEsquerda = UtilitarioTela.getColorCrud(modoCrud);
		this.linhaDireita = new Color(53, 56, 64);
		this.topo = true;
		this.baixo = true;
		this.direita = true;
		this.esquerda = true;
	}

	public BordaSombreada(int modoCrud, boolean topo, boolean baixo, boolean direita, boolean esquerda) {
		this.modoCrud = modoCrud;
		this.linhaEsquerda = UtilitarioTela.getColorCrud(modoCrud);
		this.linhaDireita = new Color(53, 56, 64);
		this.topo = topo;
		this.baixo = baixo;
		this.direita = direita;
		this.esquerda = esquerda;
	}

	
	public BordaSombreada( boolean topo, boolean baixo, boolean direita, boolean esquerda) {
		modoCrud = 0;
		this.linhaEsquerda = (Color.white);
		this.linhaDireita = new Color(53, 56, 64);
		this.topo = topo;
		this.baixo = baixo;
		this.direita = direita;
		this.esquerda = esquerda;
	}
	
	public BordaSombreada() {
		modoCrud = 0;
		this.linhaEsquerda = (Color.white);
		this.linhaDireita = new Color(53, 56, 64);
		this.topo = true;
		this.baixo = true;
		this.direita = true;
		this.esquerda = true;
	}
	
	public BordaSombreada(Color linhaDireita, Color linhaEsquerda){
		this.linhaDireita = linhaDireita;
		this.linhaEsquerda = linhaEsquerda;
		this.topo = true;
		this.baixo = true;
		this.direita = true;
		this.esquerda = true;
	}
	
	public BordaSombreada(Color linhaDireita, Color linhaEsquerda, boolean topo, boolean baixo, boolean direita, boolean esquerda){
		this.linhaDireita = linhaDireita;
		this.linhaEsquerda = linhaEsquerda;
		this.topo = topo;
		this.baixo = baixo;
		this.direita = direita;
		this.esquerda = esquerda;
	}

	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

		g.setColor(linhaEsquerda);
		
		if(topo && baixo && direita && esquerda){
			g.drawRect(x , y, width - 1, height - 1);
		} else if(topo && baixo && esquerda){
			g.drawRect(x , y, width  , height - 1);
		} else if(topo && baixo && direita){
			g.drawRect(x - 1, y, width , height - 1);
		} else if(topo && baixo){
			g.drawRect(x - 1 , y, width + 2, height - 1);
		} else if(topo && direita && esquerda){
			g.drawRect(x , y, width - 1, height);
		} else if(topo && direita ){
			g.drawRect(x -1 , y, width , height);
		} else if(topo &&  esquerda){
			g.drawRect(x , y, width  , height );
		} else if(topo){
			g.drawRect(x - 1 , y , width + 2, height );
		} else if(baixo && direita && esquerda){
			g.drawRect(x, y - 2, width - 1, height + 1);
		} else if(baixo && esquerda){
			g.drawRect(x, y - 2, width , height + 1);
		} else if(baixo && direita){
			g.drawRect(x - 1, y - 2, width , height + 1);
		} else if(baixo){
			g.drawRect(x -1, y - 2, width +2 , height + 1);
		} else if(direita && esquerda){
			g.drawRect(x, y - 2 , width - 1, height +2 );
		} else if(direita){
			g.drawRect(x - 1, y - 2 , width , height +2 );
		} else if(esquerda){
			g.drawRect(x, y - 2 , width , height +2 );
		}
		

		g.setColor(linhaDireita);
		
		if(topo && baixo && direita && esquerda){
			g.fillRect(x + 1, y + 1, 1, height - 2);
			g.fillRect(x + width - 2, y + 1, 1, height - 2);
			g.fillRect(x + 1, y + 1, width - 2, 1);
			g.fillRect(x + 1, y + height - 2, width - 2, 1);
		} else if(topo && baixo && esquerda){
			g.fillRect(x + 1, y + 1, 1, height - 2);
			g.fillRect(x + 1, y + 1, width - 1 , 1);
			g.fillRect(x + 1, y + height - 2, width - 1, 1);
		} else if(topo && baixo && direita){
			g.fillRect(x + width - 2, y + 1, 1, height - 2);
			g.fillRect(x - 1, y + 1, width - 1, 1);
			g.fillRect(x - 1, y + height - 2, width - 1, 1);
		} else if(topo && baixo){
			g.fillRect(x , y + 1, width + 2 , 1);
			g.fillRect(x , y + height - 2, width + 2, 1);
		} else if(topo && direita && esquerda){
			g.fillRect(x + 1, y + 1, 1, height - 1);
			g.fillRect(x + width - 2, y + 1, 1, height - 1);
			g.fillRect(x + 1, y + 1, width - 2, 1);
		} else if(topo && direita){
			g.fillRect(x + width - 2, y + 1, 1, height - 1);
			g.fillRect(x - 1, y + 1, width - 1, 1);
		} else if(topo && esquerda){
			g.fillRect(x + 1, y + 1, 1, height - 1);
			g.fillRect(x + 1, y + 1, width - 1, 1);
		} else if(topo){
			g.fillRect(x , y +1 , width + 2 , 1);
		} else if(baixo && direita && esquerda){
			g.fillRect(x + 1, y , 1, height - 1);
			g.fillRect(x + width - 2, y , 1, height - 1);
			g.fillRect(x + 1, y + height -2 , width - 2, 1);
		} else if(baixo && esquerda){
			g.fillRect(x + 1, y , 1, height - 1);
			g.fillRect(x + 1, y + height -2 , width - 1, 1);
		} else if(baixo && direita){
			g.fillRect(x + width - 2, y , 1, height - 1);
			g.fillRect(x , y + height -2 , width - 2, 1);
		} else if(baixo){
			g.fillRect(x , y + height -2 , width , 1);
		} else if(direita && esquerda){
			g.fillRect(x + 1, y , 1, height);
			g.fillRect(x + width - 2, y , 1, height);
		} else if(direita){
			g.fillRect(x + width - 2, y , 1, height);
		} else if(esquerda){
			g.fillRect(x + 1, y , 1, height);
		}
		
			
		
	}

	public Insets getBorderInsets(Component c) {
		return new Insets(3, 3, 3, 3);
	}

	public boolean isBorderOpaque() {
		return true;
	}
}
