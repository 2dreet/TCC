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
@Table(name = "marcaperiferico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Marcaperiferico.findAll", query = "SELECT m FROM Marcaperiferico m"),
    @NamedQuery(name = "Marcaperiferico.findByCodigoMarcaPeriferico", query = "SELECT m FROM Marcaperiferico m WHERE m.codigoMarcaPeriferico = :codigoMarcaPeriferico")})
public class Marcaperiferico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigoMarcaPeriferico")
    private Integer codigoMarcaPeriferico;
    @JoinColumn(name = "codigoPeriferico", referencedColumnName = "codigoPeriferico")
    @ManyToOne(optional = false)
    private Periferico codigoPeriferico;
    @JoinColumn(name = "codigoMarca", referencedColumnName = "codigoMarca")
    @ManyToOne(optional = false)
    private Marca codigoMarca;

    public Marcaperiferico() {
    }

    public Marcaperiferico(Integer codigoMarcaPeriferico) {
        this.codigoMarcaPeriferico = codigoMarcaPeriferico;
    }

    public Integer getCodigoMarcaPeriferico() {
        return codigoMarcaPeriferico;
    }

    public void setCodigoMarcaPeriferico(Integer codigoMarcaPeriferico) {
        this.codigoMarcaPeriferico = codigoMarcaPeriferico;
    }

    public Periferico getCodigoPeriferico() {
        return codigoPeriferico;
    }

    public void setCodigoPeriferico(Periferico codigoPeriferico) {
        this.codigoPeriferico = codigoPeriferico;
    }

    public Marca getCodigoMarca() {
        return codigoMarca;
    }

    public void setCodigoMarca(Marca codigoMarca) {
        this.codigoMarca = codigoMarca;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoMarcaPeriferico != null ? codigoMarcaPeriferico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Marcaperiferico)) {
            return false;
        }
        Marcaperiferico other = (Marcaperiferico) object;
        if ((this.codigoMarcaPeriferico == null && other.codigoMarcaPeriferico != null) || (this.codigoMarcaPeriferico != null && !this.codigoMarcaPeriferico.equals(other.codigoMarcaPeriferico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.treinoweb.model.entidade.Marcaperiferico[ codigoMarcaPeriferico=" + codigoMarcaPeriferico + " ]";
    }
    
}
