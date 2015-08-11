package entidade;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the funcionariocampeonato database table.
 * 
 */
@Entity
@NamedQuery(name="Funcionariocampeonato.findAll", query="SELECT f FROM Funcionariocampeonato f")
public class Funcionariocampeonato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigoFuncCamp;

	//bi-directional many-to-one association to Campeonato
	@ManyToOne
	@JoinColumn(name="codigoCampeonato")
	private Campeonato campeonato;

	//bi-directional many-to-one association to Funcionario
	@ManyToOne
	@JoinColumn(name="codigoFuncionario")
	private Funcionario funcionario;

	public Funcionariocampeonato() {
	}

	public int getCodigoFuncCamp() {
		return this.codigoFuncCamp;
	}

	public void setCodigoFuncCamp(int codigoFuncCamp) {
		this.codigoFuncCamp = codigoFuncCamp;
	}

	public Campeonato getCampeonato() {
		return this.campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public Funcionario getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}