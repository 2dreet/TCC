package entidade;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the marcaperiferico database table.
 * 
 */
@Entity
@NamedQuery(name="Marcaperiferico.findAll", query="SELECT m FROM Marcaperiferico m")
public class Marcaperiferico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigoMarcaPeriferico;

	//bi-directional many-to-one association to Marca
	@ManyToOne
	@JoinColumn(name="codigoMarca")
	private Marca marca;

	//bi-directional many-to-one association to Periferico
	@ManyToOne
	@JoinColumn(name="codigoPeriferico")
	private Periferico periferico;

	public Marcaperiferico() {
	}

	public int getCodigoMarcaPeriferico() {
		return this.codigoMarcaPeriferico;
	}

	public void setCodigoMarcaPeriferico(int codigoMarcaPeriferico) {
		this.codigoMarcaPeriferico = codigoMarcaPeriferico;
	}

	public Marca getMarca() {
		return this.marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Periferico getPeriferico() {
		return this.periferico;
	}

	public void setPeriferico(Periferico periferico) {
		this.periferico = periferico;
	}

}