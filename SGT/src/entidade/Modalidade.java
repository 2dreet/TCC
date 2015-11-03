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
@Table(name = "modalidade")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modalidade.findAll", query = "SELECT m FROM Modalidade m"),
    @NamedQuery(name = "Modalidade.findByCodigoModalidade", query = "SELECT m FROM Modalidade m WHERE m.codigoModalidade = :codigoModalidade"),
    @NamedQuery(name = "Modalidade.findByDescricao", query = "SELECT m FROM Modalidade m WHERE m.descricao = :descricao"),
    @NamedQuery(name = "Modalidade.findByQuantidadeJogadores", query = "SELECT m FROM Modalidade m WHERE m.quantidadeJogadores = :quantidadeJogadores"),
    @NamedQuery(name = "Modalidade.findByAtivo", query = "SELECT m FROM Modalidade m WHERE m.ativo = :ativo")})
public class Modalidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoModalidade")
    private Integer codigoModalidade;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "quantidadeJogadores")
    private Integer quantidadeJogadores;
    @Basic(optional = false)
    @Column(name = "ativo")
    private boolean ativo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modalidade")
    private Collection<Campeonato> campeonatoCollection;

    public Modalidade() {
    }

    public Modalidade(Integer codigoModalidade) {
        this.codigoModalidade = codigoModalidade;
    }

    public Modalidade(Integer codigoModalidade, boolean ativo) {
        this.codigoModalidade = codigoModalidade;
        this.ativo = ativo;
    }

    public Integer getCodigoModalidade() {
        return codigoModalidade;
    }

    public void setCodigoModalidade(Integer codigoModalidade) {
        this.codigoModalidade = codigoModalidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidadeJogadores() {
        return quantidadeJogadores;
    }
    
   
    public void setQuantidadeJogadores(Integer quantidadeJogadores) {
        this.quantidadeJogadores = quantidadeJogadores;
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
        hash += (codigoModalidade != null ? codigoModalidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modalidade)) {
            return false;
        }
        Modalidade other = (Modalidade) object;
        if ((this.codigoModalidade == null && other.codigoModalidade != null) || (this.codigoModalidade != null && !this.codigoModalidade.equals(other.codigoModalidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descricao;
    }
    
}
