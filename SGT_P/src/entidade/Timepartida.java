package entidade;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the timepartida database table.
 * 
 */
@Entity
@NamedQuery(name="Timepartida.findAll", query="SELECT t FROM Timepartida t")
public class Timepartida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigoTimePartida;

	private int placarTimePerdedor;

	private int placarTimeVencedor;

	private int timeVencedor;

	//bi-directional many-to-one association to Partida
	@ManyToOne
	@JoinColumn(name="codigoPartida")
	private Partida partida;

	//bi-directional many-to-one association to Time
	@ManyToOne
	@JoinColumn(name="codigoTime")
	private Time time;

	public Timepartida() {
	}

	public int getCodigoTimePartida() {
		return this.codigoTimePartida;
	}

	public void setCodigoTimePartida(int codigoTimePartida) {
		this.codigoTimePartida = codigoTimePartida;
	}

	public int getPlacarTimePerdedor() {
		return this.placarTimePerdedor;
	}

	public void setPlacarTimePerdedor(int placarTimePerdedor) {
		this.placarTimePerdedor = placarTimePerdedor;
	}

	public int getPlacarTimeVencedor() {
		return this.placarTimeVencedor;
	}

	public void setPlacarTimeVencedor(int placarTimeVencedor) {
		this.placarTimeVencedor = placarTimeVencedor;
	}

	public int getTimeVencedor() {
		return this.timeVencedor;
	}

	public void setTimeVencedor(int timeVencedor) {
		this.timeVencedor = timeVencedor;
	}

	public Partida getPartida() {
		return this.partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public Time getTime() {
		return this.time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

}