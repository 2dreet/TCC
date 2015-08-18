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
    @Basic(optional = false)
    @Column(name = "codigoPcPartida")
    private Integer codigoPcPartida;
    @JoinColumn(name = "codigoPc", referencedColumnName = "codigoPC")
    @ManyToOne(optional = false)
    private Pc codigoPc;
    @JoinColumn(name = "codigoPartida", referencedColumnName = "codigoPartida")
    @ManyToOne(optional = false)
    private Partida codigoPartida;

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

    public Pc getCodigoPc() {
        return codigoPc;
    }

    public void setCodigoPc(Pc codigoPc) {
        this.codigoPc = codigoPc;
    }

    public Partida getCodigoPartida() {
        return codigoPartida;
    }

    public void setCodigoPartida(Partida codigoPartida) {
        this.codigoPartida = codigoPartida;
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
        return "br.com.treinoweb.model.entidade.Pcpartida[ codigoPcPartida=" + codigoPcPartida + " ]";
    }
    
}
