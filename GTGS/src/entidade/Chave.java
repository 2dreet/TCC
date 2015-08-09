package entidade;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the chave database table.
 * 
 */
@Entity
@NamedQuery(name="Chave.findAll", query="SELECT c FROM Chave c")
public class Chave implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigoChave;

	private byte ativo;

	private String descricao;

	//bi-directional many-to-one association to Campeonato
	@OneToMany(mappedBy="chave")
	private List<Campeonato> campeonatos;

	public Chave() {
	}

	public int getCodigoChave() {
		return this.codigoChave;
	}

	public void setCodigoChave(int codigoChave) {
		this.codigoChave = codigoChave;
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

	public List<Campeonato> getCampeonatos() {
		return this.campeonatos;
	}

	public void setCampeonatos(List<Campeonato> campeonatos) {
		this.campeonatos = campeonatos;
	}

	public Campeonato addCampeonato(Campeonato campeonato) {
		getCampeonatos().add(campeonato);
		campeonato.setChave(this);

		return campeonato;
	}

	public Campeonato removeCampeonato(Campeonato campeonato) {
		getCampeonatos().remove(campeonato);
		campeonato.setChave(null);

		return campeonato;
	}

}