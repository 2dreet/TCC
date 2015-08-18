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
@Table(name = "chave")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chave.findAll", query = "SELECT c FROM Chave c"),
    @NamedQuery(name = "Chave.findByCodigoChave", query = "SELECT c FROM Chave c WHERE c.codigoChave = :codigoChave"),
    @NamedQuery(name = "Chave.findByDescricao", query = "SELECT c FROM Chave c WHERE c.descricao = :descricao"),
    @NamedQuery(name = "Chave.findByAtivo", query = "SELECT c FROM Chave c WHERE c.ativo = :ativo")})
public class Chave implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigoChave")
    private Integer codigoChave;
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "ativo")
    private boolean ativo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoChave")
    private Collection<Campeonato> campeonatoCollection;

    public Chave() {
    }

    public Chave(Integer codigoChave) {
        this.codigoChave = codigoChave;
    }

    public Chave(Integer codigoChave, boolean ativo) {
        this.codigoChave = codigoChave;
        this.ativo = ativo;
    }

    public Integer getCodigoChave() {
        return codigoChave;
    }

    public void setCodigoChave(Integer codigoChave) {
        this.codigoChave = codigoChave;
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
    public Collection<Campeonato> getCampeonatoCollection() {
        return campeonatoCollection;
    }

    public void setCampeonatoCollection(Collection<Campeonato> campeonatoCollection) {
        this.campeonatoCollection = campeonatoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoChave != null ? codigoChave.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chave)) {
            return false;
        }
        Chave other = (Chave) object;
        if ((this.codigoChave == null && other.codigoChave != null) || (this.codigoChave != null && !this.codigoChave.equals(other.codigoChave))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.treinoweb.model.entidade.Chave[ codigoChave=" + codigoChave + " ]";
    }
    
}
