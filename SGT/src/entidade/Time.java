/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jose
 */
@Entity
@Table(name = "time")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Time.findAll", query = "SELECT t FROM Time t"),
    @NamedQuery(name = "Time.findByCodigoTime", query = "SELECT t FROM Time t WHERE t.codigoTime = :codigoTime"),
    @NamedQuery(name = "Time.findByDescricao", query = "SELECT t FROM Time t WHERE t.descricao = :descricao"),
    @NamedQuery(name = "Time.findByDataCadastro", query = "SELECT t FROM Time t WHERE t.dataCadastro = :dataCadastro"),
    @NamedQuery(name = "Time.findByAtivo", query = "SELECT t FROM Time t WHERE t.ativo = :ativo")})
public class Time implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigoTime")
    private Integer codigoTime;
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "dataCadastro")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    @Basic(optional = false)
    @Column(name = "ativo")
    private boolean ativo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "timeTerceiro")
    private Collection<Classificacao> classificacaoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "timeSegundo")
    private Collection<Classificacao> classificacaoCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "timePrimeiro")
    private Collection<Classificacao> classificacaoCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTime")
    private Collection<Jogador> jogadorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTime")
    private Collection<Timepartida> timepartidaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTime")
    private Collection<Campeonatotime> campeonatotimeCollection;

    public Time() {
    }

    public Time(Integer codigoTime) {
        this.codigoTime = codigoTime;
    }

    public Time(Integer codigoTime, Date dataCadastro, boolean ativo) {
        this.codigoTime = codigoTime;
        this.dataCadastro = dataCadastro;
        this.ativo = ativo;
    }

    public Integer getCodigoTime() {
        return codigoTime;
    }

    public void setCodigoTime(Integer codigoTime) {
        this.codigoTime = codigoTime;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @XmlTransient
    public Collection<Classificacao> getClassificacaoCollection() {
        return classificacaoCollection;
    }

    public void setClassificacaoCollection(Collection<Classificacao> classificacaoCollection) {
        this.classificacaoCollection = classificacaoCollection;
    }

    @XmlTransient
    public Collection<Classificacao> getClassificacaoCollection1() {
        return classificacaoCollection1;
    }

    public void setClassificacaoCollection1(Collection<Classificacao> classificacaoCollection1) {
        this.classificacaoCollection1 = classificacaoCollection1;
    }

    @XmlTransient
    public Collection<Classificacao> getClassificacaoCollection2() {
        return classificacaoCollection2;
    }

    public void setClassificacaoCollection2(Collection<Classificacao> classificacaoCollection2) {
        this.classificacaoCollection2 = classificacaoCollection2;
    }

    @XmlTransient
    public Collection<Jogador> getJogadorCollection() {
        return jogadorCollection;
    }

    public void setJogadorCollection(Collection<Jogador> jogadorCollection) {
        this.jogadorCollection = jogadorCollection;
    }

    @XmlTransient
    public Collection<Timepartida> getTimepartidaCollection() {
        return timepartidaCollection;
    }

    public void setTimepartidaCollection(Collection<Timepartida> timepartidaCollection) {
        this.timepartidaCollection = timepartidaCollection;
    }

    @XmlTransient
    public Collection<Campeonatotime> getCampeonatotimeCollection() {
        return campeonatotimeCollection;
    }

    public void setCampeonatotimeCollection(Collection<Campeonatotime> campeonatotimeCollection) {
        this.campeonatotimeCollection = campeonatotimeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoTime != null ? codigoTime.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Time)) {
            return false;
        }
        Time other = (Time) object;
        if ((this.codigoTime == null && other.codigoTime != null) || (this.codigoTime != null && !this.codigoTime.equals(other.codigoTime))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.treinoweb.model.entidade.Time[ codigoTime=" + codigoTime + " ]";
    }
    
}
