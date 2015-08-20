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
@Table(name = "pcpartida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pcpartida.findAll", query = "SELECT p FROM Pcpartida p"),
    @NamedQuery(name = "Pcpartida.findByCodigoPcPartida", query = "SELECT p FROM Pcpartida p WHERE p.codigoPcPartida = :codigoPcPartida")})
public class Pcpartida implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoPcPartida")
    private Integer codigoPcPartida;
    @JoinColumn(name = "codigoPc", referencedColumnName = "codigoPC")
    @ManyToOne(optional = false)
    private Pc pc;
    @JoinColumn(name = "codigoPartida", referencedColumnName = "codigoPartida")
    @ManyToOne(optional = false)
    private Partida partida;

    public Pcpartida() {
    }

    public Pcpartida(Integer codigoPcPartida) {
        this.codigoPcPartida = codigoPcPartida;
    }

    public Integer getCodigoPcPartida() {
        return codigoPcPartida;
    }

    public void setCodigoPcPartida(Integer codigoPcPartida) {
        this.codigoPcPartida = codigoPcPartida;
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
        hash += (codigoPcPartida != null ? codigoPcPartida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pcpartida)) {
            return false;
        }
        Pcpartida other = (Pcpartida) object;
        if ((this.codigoPcPartida == null && other.codigoPcPartida != null) || (this.codigoPcPartida != null && !this.codigoPcPartida.equals(other.codigoPcPartida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "criaentidades.Pcpartida[ codigoPcPartida=" + codigoPcPartida + " ]";
    }
    
}
