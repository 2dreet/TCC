package entidade;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pcpartida database table.
 * 
 */
@Entity
@NamedQuery(name="Pcpartida.findAll", query="SELECT p FROM Pcpartida p")
public class Pcpartida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigoPcPartida;

	//bi-directional many-to-one association to Partida
	@ManyToOne
	@JoinColumn(name="codigoPartida")
	private Partida partida;

	//bi-directional many-to-one association to Pc
	@ManyToOne
	@JoinColumn(name="codigoPc")
	private Pc pc;

	public Pcpartida() {
	}

	public int getCodigoPcPartida() {
		return this.codigoPcPartida;
	}

	public void setCodigoPcPartida(int codigoPcPartida) {
		this.codigoPcPartida = codigoPcPartida;
	}

	public Partida getPartida() {
		return this.partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public Pc getPc() {
		return this.pc;
	}

	public void setPc(Pc pc) {
		this.pc = pc;
	}

}