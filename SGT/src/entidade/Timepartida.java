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
@Table(name = "timepartida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Timepartida.findAll", query = "SELECT t FROM Timepartida t"),
    @NamedQuery(name = "Timepartida.findByCodigoTimePartida", query = "SELECT t FROM Timepartida t WHERE t.codigoTimePartida = :codigoTimePartida"),
    @NamedQuery(name = "Timepartida.findByTimeVencedor", query = "SELECT t FROM Timepartida t WHERE t.timeVencedor = :timeVencedor"),
    @NamedQuery(name = "Timepartida.findByPlacarTimeVencedor", query = "SELECT t FROM Timepartida t WHERE t.placarTimeVencedor = :placarTimeVencedor"),
    @NamedQuery(name = "Timepartida.findByPlacarTimePerdedor", query = "SELECT t FROM Timepartida t WHERE t.placarTimePerdedor = :placarTimePerdedor")})
public class Timepartida implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoTimePartida")
    private Integer codigoTimePartida;
    @Column(name = "timeVencedor")
    private Integer timeVencedor;
    @Column(name = "placarTimeVencedor")
    private Integer placarTimeVencedor;
    @Column(name = "placarTimePerdedor")
    private Integer placarTimePerdedor;
    @JoinColumn(name = "codigoTime", referencedColumnName = "codigoTime")
    @ManyToOne(optional = false)
    private Time time;
    @JoinColumn(name = "codigoPartida", referencedColumnName = "codigoPartida")
    @ManyToOne(optional = false)
    private Partida partida;

    public Timepartida() {
    }

    public Timepartida(Integer codigoTimePartida) {
        this.codigoTimePartida = codigoTimePartida;
    }

    public Integer getCodigoTimePartida() {
        return codigoTimePartida;
    }

    public void setCodigoTimePartida(Integer codigoTimePartida) {
        this.codigoTimePartida = codigoTimePartida;
    }

    public Integer getTimeVencedor() {
        return timeVencedor;
    }

    public void setTimeVencedor(Integer timeVencedor) {
        this.timeVencedor = timeVencedor;
    }

    public Integer getPlacarTimeVencedor() {
        return placarTimeVencedor;
    }

    public void setPlacarTimeVencedor(Integer placarTimeVencedor) {
        this.placarTimeVencedor = placarTimeVencedor;
    }

    public Integer getPlacarTimePerdedor() {
        return placarTimePerdedor;
    }

    public void setPlacarTimePerdedor(Integer placarTimePerdedor) {
        this.placarTimePerdedor = placarTimePerdedor;
    }
    
    public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
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
        hash += (codigoTimePartida != null ? codigoTimePartida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Timepartida)) {
            return false;
        }
        Timepartida other = (Timepartida) object;
        if ((this.codigoTimePartida == null && other.codigoTimePartida != null) || (this.codigoTimePartida != null && !this.codigoTimePartida.equals(other.codigoTimePartida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "criaentidades.Timepartida[ codigoTimePartida=" + codigoTimePartida + " ]";
    }
    
}
