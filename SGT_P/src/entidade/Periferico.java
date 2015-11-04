/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jose
 */
@Entity
@Table(name = "periferico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Periferico.findAll", query = "SELECT p FROM Periferico p"),
    @NamedQuery(name = "Periferico.findByCodigoPeriferico", query = "SELECT p FROM Periferico p WHERE p.codigoPeriferico = :codigoPeriferico"),
    @NamedQuery(name = "Periferico.findByDescricao", query = "SELECT p FROM Periferico p WHERE p.descricao = :descricao"),
    @NamedQuery(name = "Periferico.findByAtivo", query = "SELECT p FROM Periferico p WHERE p.ativo = :ativo")})
public class Periferico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoPeriferico")
    private Integer codigoPeriferico;
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "ativo")
    private boolean ativo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "periferico")
    private Collection<JogadorPeriferico> jogadorPerifericoCollection;

    public Periferico() {
    }

    public Periferico(Integer codigoPeriferico) {
        this.codigoPeriferico = codigoPeriferico;
    }

    public Periferico(Integer codigoPeriferico, boolean ativo) {
        this.codigoPeriferico = codigoPeriferico;
        this.ativo = ativo;
    }

    public Integer getCodigoPeriferico() {
        return codigoPeriferico;
    }

    public void setCodigoPeriferico(Integer codigoPeriferico) {
        this.codigoPeriferico = codigoPeriferico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @XmlTransient
    public Collection<JogadorPeriferico> getJogadorPerifericoCollection() {
        return jogadorPerifericoCollection;
    }

    public void setJogadorPerifericoCollection(Collection<JogadorPeriferico> jogadorPerifericoCollection) {
        this.jogadorPerifericoCollection = jogadorPerifericoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPeriferico != null ? codigoPeriferico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Periferico)) {
            return false;
        }
        Periferico other = (Periferico) object;
        if ((this.codigoPeriferico == null && other.codigoPeriferico != null) || (this.codigoPeriferico != null && !this.codigoPeriferico.equals(other.codigoPeriferico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descricao;
    }
    
}
