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
@Table(name = "campeonatopartida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Campeonatopartida.findAll", query = "SELECT c FROM Campeonatopartida c"),
    @NamedQuery(name = "Campeonatopartida.findByCodigoCampPartida", query = "SELECT c FROM Campeonatopartida c WHERE c.codigoCampPartida = :codigoCampPartida")})
public class Campeonatopartida implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigoCampPartida")
    private Integer codigoCampPartida;
    @JoinColumn(name = "codigoPartida", referencedColumnName = "codigoPartida")
    @ManyToOne(optional = false)
    private Partida codigoPartida;
    @JoinColumn(name = "codigoCampeonato", referencedColumnName = "codigoCampeonato")
    @ManyToOne(optional = false)
    private Campeonato codigoCampeonato;

    public Campeonatopartida() {
    }

    public Campeonatopartida(Integer codigoCampPartida) {
        this.codigoCampPartida = codigoCampPartida;
    }

    public Integer getCodigoCampPartida() {
        return codigoCampPartida;
    }

    public void setCodigoCampPartida(Integer codigoCampPartida) {
        this.codigoCampPartida = codigoCampPartida;
    }

    public Partida getCodigoPartida() {
        return codigoPartida;
    }

    public void setCodigoPartida(Partida codigoPartida) {
        this.codigoPartida = codigoPartida;
    }

    public Campeonato getCodigoCampeonato() {
        return codigoCampeonato;
    }

    public void setCodigoCampeonato(Campeonato codigoCampeonato) {
        this.codigoCampeonato = codigoCampeonato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoCampPartida != null ? codigoCampPartida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Campeonatopartida)) {
            return false;
        }
        Campeonatopartida other = (Campeonatopartida) object;
        if ((this.codigoCampPartida == null && other.codigoCampPartida != null) || (this.codigoCampPartida != null && !this.codigoCampPartida.equals(other.codigoCampPartida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.treinoweb.model.entidade.Campeonatopartida[ codigoCampPartida=" + codigoCampPartida + " ]";
    }
    
}
