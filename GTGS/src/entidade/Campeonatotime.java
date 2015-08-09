package entidade;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the campeonatotime database table.
 * 
 */
@Entity
@NamedQuery(name="Campeonatotime.findAll", query="SELECT c FROM Campeonatotime c")
public class Campeonatotime implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigoCampTime;

	@Temporal(TemporalType.DATE)
	private Date dataInscricao;

	//bi-directional many-to-one association to Campeonato
	@ManyToOne
	@JoinColumn(name="codigoCampeonato")
	private Campeonato campeonato;

	//bi-directional many-to-one association to Time
	@ManyToOne
	@JoinColumn(name="codigoTime")
	private Time time;

	public Campeonatotime() {
	}

	public int getCodigoCampTime() {
		return this.codigoCampTime;
	}

	public void setCodigoCampTime(int codigoCampTime) {
		this.codigoCampTime = codigoCampTime;
	}

	public Date getDataInscricao() {
		return this.dataInscricao;
	}

	public void setDataInscricao(Date dataInscricao) {
		this.dataInscricao = dataInscricao;
	}

	public Campeonato getCampeonato() {
		return this.campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public Time getTime() {
		return this.time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

}