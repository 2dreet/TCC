package entidade;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the jogadorperiferico database table.
 * 
 */
@Entity
@NamedQuery(name="Jogadorperiferico.findAll", query="SELECT j FROM Jogadorperiferico j")
public class Jogadorperiferico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigojogaPeri;

	//bi-directional many-to-one association to Periferico
	@ManyToOne
	@JoinColumn(name="codigoPeriferico")
	private Periferico periferico;

	//bi-directional many-to-one association to Jogador
	@ManyToOne
	@JoinColumn(name="codigoJogador")
	private Jogador jogador;

	public Jogadorperiferico() {
	}

	public int getCodigojogaPeri() {
		return this.codigojogaPeri;
	}

	public void setCodigojogaPeri(int codigojogaPeri) {
		this.codigojogaPeri = codigojogaPeri;
	}

	public Periferico getPeriferico() {
		return this.periferico;
	}

	public void setPeriferico(Periferico periferico) {
		this.periferico = periferico;
	}

	public Jogador getJogador() {
		return this.jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

}