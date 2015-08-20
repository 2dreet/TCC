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
@Table(name = "marcaperiferico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Marcaperiferico.findAll", query = "SELECT m FROM Marcaperiferico m"),
    @NamedQuery(name = "Marcaperiferico.findByCodigoMarcaPeriferico", query = "SELECT m FROM Marcaperiferico m WHERE m.codigoMarcaPeriferico = :codigoMarcaPeriferico")})
public class Marcaperiferico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoMarcaPeriferico")
    private Integer codigoMarcaPeriferico;
    @JoinColumn(name = "codigoPeriferico", referencedColumnName = "codigoPeriferico")
    @ManyToOne(optional = false)
    private Periferico periferico;
    @JoinColumn(name = "codigoMarca", referencedColumnName = "codigoMarca")
    @ManyToOne(optional = false)
    private Marca marca;

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

    public Periferico getPeriferico() {
		return periferico;
	}

	public void setPeriferico(Periferico periferico) {
		this.periferico = periferico;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
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
        return "criaentidades.Marcaperiferico[ codigoMarcaPeriferico=" + codigoMarcaPeriferico + " ]";
    }
    
}
