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
@Table(name = "permissao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permissao.findAll", query = "SELECT p FROM Permissao p"),
    @NamedQuery(name = "Permissao.findByCodigoPermissao", query = "SELECT p FROM Permissao p WHERE p.codigoPermissao = :codigoPermissao"),
    @NamedQuery(name = "Permissao.findByDescricao", query = "SELECT p FROM Permissao p WHERE p.descricao = :descricao"),
    @NamedQuery(name = "Permissao.findByAtivo", query = "SELECT p FROM Permissao p WHERE p.ativo = :ativo")})
public class Permissao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoPermissao")
    private Integer codigoPermissao;
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "ativo")
    private boolean ativo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "permissao")
    private Collection<Usuario> usuarioCollection;

    public Permissao() {
    }

    public Permissao(Integer codigoPermissao) {
        this.codigoPermissao = codigoPermissao;
    }

    public Permissao(Integer codigoPermissao, boolean ativo) {
        this.codigoPermissao = codigoPermissao;
        this.ativo = ativo;
    }

    public Integer getCodigoPermissao() {
        return codigoPermissao;
    }

    public void setCodigoPermissao(Integer codigoPermissao) {
        this.codigoPermissao = codigoPermissao;
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
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPermissao != null ? codigoPermissao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permissao)) {
            return false;
        }
        Permissao other = (Permissao) object;
        if ((this.codigoPermissao == null && other.codigoPermissao != null) || (this.codigoPermissao != null && !this.codigoPermissao.equals(other.codigoPermissao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Permissao[ codigoPermissao=" + codigoPermissao + " ]";
    }
    
}
