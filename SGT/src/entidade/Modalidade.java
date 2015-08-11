package entidade;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the modalidade database table.
 * 
 */
@Entity
@NamedQuery(name="Modalidade.findAll", query="SELECT m FROM Modalidade m")
public class Modalidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigoModalidade;

	private byte ativo;

	private String descricao;

	//bi-directional many-to-one association to Campeonato
	@OneToMany(mappedBy="modalidade")
	private List<Campeonato> campeonatos;

	public Modalidade() {
	}

	public int getCodigoModalidade() {
		return this.codigoModalidade;
	}

	public void setCodigoModalidade(int codigoModalidade) {
		this.codigoModalidade = codigoModalidade;
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
		campeonato.setModalidade(this);

		return campeonato;
	}

	public Campeonato removeCampeonato(Campeonato campeonato) {
		getCampeonatos().remove(campeonato);
		campeonato.setModalidade(null);

		return campeonato;
	}

}