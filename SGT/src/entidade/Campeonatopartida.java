package entidade;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the campeonatopartida database table.
 * 
 */
@Entity
@NamedQuery(name="Campeonatopartida.findAll", query="SELECT c FROM Campeonatopartida c")
public class Campeonatopartida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigoCampPartida;

	//bi-directional many-to-one association to Campeonato
	@ManyToOne
	@JoinColumn(name="codigoCampeonato")
	private Campeonato campeonato;

	//bi-directional many-to-one association to Partida
	@ManyToOne
	@JoinColumn(name="codigoPartida")
	private Partida partida;

	public Campeonatopartida() {
	}

	public int getCodigoCampPartida() {
		return this.codigoCampPartida;
	}

	public void setCodigoCampPartida(int codigoCampPartida) {
		this.codigoCampPartida = codigoCampPartida;
	}

	public Campeonato getCampeonato() {
		return this.campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public Partida getPartida() {
		return this.partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

}