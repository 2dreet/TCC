package entidade;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the pc database table.
 * 
 */
@Entity
@NamedQuery(name="Pc.findAll", query="SELECT p FROM Pc p")
public class Pc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigoPC;

	private byte ativo;

	private String descricao;

	private String mac;

	//bi-directional many-to-one association to Pcpartida
	@OneToMany(mappedBy="pc")
	private List<Pcpartida> pcpartidas;

	public Pc() {
	}

	public int getCodigoPC() {
		return this.codigoPC;
	}

	public void setCodigoPC(int codigoPC) {
		this.codigoPC = codigoPC;
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

	public String getMac() {
		return this.mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public List<Pcpartida> getPcpartidas() {
		return this.pcpartidas;
	}

	public void setPcpartidas(List<Pcpartida> pcpartidas) {
		this.pcpartidas = pcpartidas;
	}

	public Pcpartida addPcpartida(Pcpartida pcpartida) {
		getPcpartidas().add(pcpartida);
		pcpartida.setPc(this);

		return pcpartida;
	}

	public Pcpartida removePcpartida(Pcpartida pcpartida) {
		getPcpartidas().remove(pcpartida);
		pcpartida.setPc(null);

		return pcpartida;
	}

}