package entidade;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the classificacao database table.
 * 
 */
@Entity
@NamedQuery(name="Classificacao.findAll", query="SELECT c FROM Classificacao c")
public class Classificacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigoClassificacao;

	//bi-directional many-to-one association to Campeonato
	@ManyToOne
	@JoinColumn(name="codigoCampeonato")
	private Campeonato campeonato;

	//bi-directional many-to-one association to Time
	@ManyToOne
	@JoinColumn(name="timePrimeiro")
	private Time time1;

	//bi-directional many-to-one association to Time
	@ManyToOne
	@JoinColumn(name="timeSegundo")
	private Time time2;

	//bi-directional many-to-one association to Time
	@ManyToOne
	@JoinColumn(name="timeTerceiro")
	private Time time3;

	public Classificacao() {
	}

	public int getCodigoClassificacao() {
		return this.codigoClassificacao;
	}

	public void setCodigoClassificacao(int codigoClassificacao) {
		this.codigoClassificacao = codigoClassificacao;
	}

	public Campeonato getCampeonato() {
		return this.campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public Time getTime1() {
		return this.time1;
	}

	public void setTime1(Time time1) {
		this.time1 = time1;
	}

	public Time getTime2() {
		return this.time2;
	}

	public void setTime2(Time time2) {
		this.time2 = time2;
	}

	public Time getTime3() {
		return this.time3;
	}

	public void setTime3(Time time3) {
		this.time3 = time3;
	}

}