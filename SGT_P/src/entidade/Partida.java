package entidade;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the partida database table.
 * 
 */
@Entity
@NamedQuery(name="Partida.findAll", query="SELECT p FROM Partida p")
public class Partida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigoPartida;

	private byte ativo;

	private byte cancelada;

	private String descricao;

	@Temporal(TemporalType.DATE)
	private Date horaFim;

	@Temporal(TemporalType.DATE)
	private Date horaInicio;

	private String motivo;

	//bi-directional many-to-one association to Campeonatopartida
	@OneToMany(mappedBy="partida")
	private List<Campeonatopartida> campeonatopartidas;

	//bi-directional many-to-one association to Pcpartida
	@OneToMany(mappedBy="partida")
	private List<Pcpartida> pcpartidas;

	//bi-directional many-to-one association to Timepartida
	@OneToMany(mappedBy="partida")
	private List<Timepartida> timepartidas;

	public Partida() {
	}

	public int getCodigoPartida() {
		return this.codigoPartida;
	}

	public void setCodigoPartida(int codigoPartida) {
		this.codigoPartida = codigoPartida;
	}

	public byte getAtivo() {
		return this.ativo;
	}

	public void setAtivo(byte ativo) {
		this.ativo = ativo;
	}

	public byte getCancelada() {
		return this.cancelada;
	}

	public void setCancelada(byte cancelada) {
		this.cancelada = cancelada;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getHoraFim() {
		return this.horaFim;
	}

	public void setHoraFim(Date horaFim) {
		this.horaFim = horaFim;
	}

	public Date getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getMotivo() {
		return this.motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public List<Campeonatopartida> getCampeonatopartidas() {
		return this.campeonatopartidas;
	}

	public void setCampeonatopartidas(List<Campeonatopartida> campeonatopartidas) {
		this.campeonatopartidas = campeonatopartidas;
	}

	public Campeonatopartida addCampeonatopartida(Campeonatopartida campeonatopartida) {
		getCampeonatopartidas().add(campeonatopartida);
		campeonatopartida.setPartida(this);

		return campeonatopartida;
	}

	public Campeonatopartida removeCampeonatopartida(Campeonatopartida campeonatopartida) {
		getCampeonatopartidas().remove(campeonatopartida);
		campeonatopartida.setPartida(null);

		return campeonatopartida;
	}

	public List<Pcpartida> getPcpartidas() {
		return this.pcpartidas;
	}

	public void setPcpartidas(List<Pcpartida> pcpartidas) {
		this.pcpartidas = pcpartidas;
	}

	public Pcpartida addPcpartida(Pcpartida pcpartida) {
		getPcpartidas().add(pcpartida);
		pcpartida.setPartida(this);

		return pcpartida;
	}

	public Pcpartida removePcpartida(Pcpartida pcpartida) {
		getPcpartidas().remove(pcpartida);
		pcpartida.setPartida(null);

		return pcpartida;
	}

	public List<Timepartida> getTimepartidas() {
		return this.timepartidas;
	}

	public void setTimepartidas(List<Timepartida> timepartidas) {
		this.timepartidas = timepartidas;
	}

	public Timepartida addTimepartida(Timepartida timepartida) {
		getTimepartidas().add(timepartida);
		timepartida.setPartida(this);

		return timepartida;
	}

	public Timepartida removeTimepartida(Timepartida timepartida) {
		getTimepartidas().remove(timepartida);
		timepartida.setPartida(null);

		return timepartida;
	}

}