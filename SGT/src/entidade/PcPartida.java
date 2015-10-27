/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jose
 */
@Entity
@Table(name = "pc_partida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PcPartida.findAll", query = "SELECT p FROM PcPartida p"),
    @NamedQuery(name = "PcPartida.findByCodigopcPartida", query = "SELECT p FROM PcPartida p WHERE p.codigopcPartida = :codigopcPartida")})
public class PcPartida implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigopc_partida")
    private Integer codigopcPartida;
    @JoinColumn(name = "codigoPc", referencedColumnName = "codigoPC")
    @ManyToOne(optional = false)
    private Pc pc;
    @JoinColumn(name = "codigoPartida", referencedColumnName = "codigoPartida")
    @ManyToOne(optional = false)
    private Partida partida;

    public PcPartida() {
    }

    public PcPartida(Integer codigopcPartida) {
        this.codigopcPartida = codigopcPartida;
    }

    public Integer getCodigopcPartida() {
        return codigopcPartida;
    }

    public void setCodigopcPartida(Integer codigopcPartida) {
        this.codigopcPartida = codigopcPartida;
    }


    public Pc getPc() {
		return pc;
	}

	public void setPc(Pc pc) {
		this.pc = pc;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (codigopcPartida != null ? codigopcPartida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PcPartida)) {
            return false;
        }
        PcPartida other = (PcPartida) object;
        if ((this.codigopcPartida == null && other.codigopcPartida != null) || (this.codigopcPartida != null && !this.codigopcPartida.equals(other.codigopcPartida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.PcPartida[ codigopcPartida=" + codigopcPartida + " ]";
    }
    
}
