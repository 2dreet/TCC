package entidade;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the periferico database table.
 * 
 */
@Entity
@NamedQuery(name="Periferico.findAll", query="SELECT p FROM Periferico p")
public class Periferico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigoPeriferico;

	private byte ativo;

	private String descricao;

	//bi-directional many-to-one association to Jogadorperiferico
	@OneToMany(mappedBy="periferico")
	private List<Jogadorperiferico> jogadorperifericos;

	//bi-directional many-to-one association to Marcaperiferico
	@OneToMany(mappedBy="periferico")
	private List<Marcaperiferico> marcaperifericos;

	public Periferico() {
	}

	public int getCodigoPeriferico() {
		return this.codigoPeriferico;
	}

	public void setCodigoPeriferico(int codigoPeriferico) {
		this.codigoPeriferico = codigoPeriferico;
	}

	public byte getAtivo() {
		return this.ativo;
	}

	public void setAtivo(byte ativo) {
		this.ativo = ativo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Jogadorperiferico> getJogadorperifericos() {
		return this.jogadorperifericos;
	}

	public void setJogadorperifericos(List<Jogadorperiferico> jogadorperifericos) {
		this.jogadorperifericos = jogadorperifericos;
	}

	public Jogadorperiferico addJogadorperiferico(Jogadorperiferico jogadorperiferico) {
		getJogadorperifericos().add(jogadorperiferico);
		jogadorperiferico.setPeriferico(this);

		return jogadorperiferico;
	}

	public Jogadorperiferico removeJogadorperiferico(Jogadorperiferico jogadorperiferico) {
		getJogadorperifericos().remove(jogadorperiferico);
		jogadorperiferico.setPeriferico(null);

		return jogadorperiferico;
	}

	public List<Marcaperiferico> getMarcaperifericos() {
		return this.marcaperifericos;
	}

	public void setMarcaperifericos(List<Marcaperiferico> marcaperifericos) {
		this.marcaperifericos = marcaperifericos;
	}

	public Marcaperiferico addMarcaperiferico(Marcaperiferico marcaperiferico) {
		getMarcaperifericos().add(marcaperiferico);
		marcaperiferico.setPeriferico(this);

		return marcaperiferico;
	}

	public Marcaperiferico removeMarcaperiferico(Marcaperiferico marcaperiferico) {
		getMarcaperifericos().remove(marcaperiferico);
		marcaperiferico.setPeriferico(null);

		return marcaperiferico;
	}

}