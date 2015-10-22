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
@Table(name = "banimento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Banimento.findAll", query = "SELECT b FROM Banimento b"),
    @NamedQuery(name = "Banimento.findByCodigoBanimento", query = "SELECT b FROM Banimento b WHERE b.codigoBanimento = :codigoBanimento"),
    @NamedQuery(name = "Banimento.findByDescricao", query = "SELECT b FROM Banimento b WHERE b.descricao = :descricao"),
    @NamedQuery(name = "Banimento.findByAtivo", query = "SELECT b FROM Banimento b WHERE b.ativo = :ativo")})
public class Banimento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoBanimento")
    private Integer codigoBanimento;
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "ativo")
    private boolean ativo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "banimento")
    private Collection<JogadorBanimento> jogadorBanimentoCollection;

    public Banimento() {
    }

    public Banimento(Integer codigoBanimento) {
        this.codigoBanimento = codigoBanimento;
    }

    public Banimento(Integer codigoBanimento, boolean ativo) {
        this.codigoBanimento = codigoBanimento;
        this.ativo = ativo;
    }

    public Integer getCodigoBanimento() {
        return codigoBanimento;
    }

    public void setCodigoBanimento(Integer codigoBanimento) {
        this.codigoBanimento = codigoBanimento;
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
    public Collection<JogadorBanimento> getJogadorBanimentoCollection() {
        return jogadorBanimentoCollection;
    }

    public void setJogadorBanimentoCollection(Collection<JogadorBanimento> jogadorBanimentoCollection) {
        this.jogadorBanimentoCollection = jogadorBanimentoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoBanimento != null ? codigoBanimento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Banimento)) {
            return false;
        }
        Banimento other = (Banimento) object;
        if ((this.codigoBanimento == null && other.codigoBanimento != null) || (this.codigoBanimento != null && !this.codigoBanimento.equals(other.codigoBanimento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Banimento[ codigoBanimento=" + codigoBanimento + " ]";
    }
    
}
