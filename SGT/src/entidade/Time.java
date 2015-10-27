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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoTime")
    private Integer codigoTime;
    @Column(name = "descricao")
    private String descricao;
    @Lob
    @Column(name = "logo")
    private byte[] logo;
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
    @OneToMany(mappedBy = "timePerdedor")
    private Collection<TimePartida> timePartidaCollection;
    @OneToMany(mappedBy = "timeVencedor")
    private Collection<TimePartida> timePartidaCollection1;
    @OneToMany(mappedBy = "time2")
    private Collection<TimePartida> timePartidaCollection2;
    @OneToMany(mappedBy = "time1")
    private Collection<TimePartida> timePartidaCollection3;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "time")
    private Collection<CampeonatoTime> campeonatoTimeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "time")
    private Collection<TimeGrupo> timeGrupoCollection;
    @OneToMany(mappedBy = "time")
    private Collection<Jogador> jogadorCollection;

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

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
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
    public Collection<TimePartida> getTimePartidaCollection() {
        return timePartidaCollection;
    }

    public void setTimePartidaCollection(Collection<TimePartida> timePartidaCollection) {
        this.timePartidaCollection = timePartidaCollection;
    }

    @XmlTransient
    public Collection<TimePartida> getTimePartidaCollection1() {
        return timePartidaCollection1;
    }

    public void setTimePartidaCollection1(Collection<TimePartida> timePartidaCollection1) {
        this.timePartidaCollection1 = timePartidaCollection1;
    }

    @XmlTransient
    public Collection<TimePartida> getTimePartidaCollection2() {
        return timePartidaCollection2;
    }

    public void setTimePartidaCollection2(Collection<TimePartida> timePartidaCollection2) {
        this.timePartidaCollection2 = timePartidaCollection2;
    }

    @XmlTransient
    public Collection<TimePartida> getTimePartidaCollection3() {
        return timePartidaCollection3;
    }

    public void setTimePartidaCollection3(Collection<TimePartida> timePartidaCollection3) {
        this.timePartidaCollection3 = timePartidaCollection3;
    }

    @XmlTransient
    public Collection<CampeonatoTime> getCampeonatoTimeCollection() {
        return campeonatoTimeCollection;
    }

    public void setCampeonatoTimeCollection(Collection<CampeonatoTime> campeonatoTimeCollection) {
        this.campeonatoTimeCollection = campeonatoTimeCollection;
    }

    @XmlTransient
    public Collection<TimeGrupo> getTimeGrupoCollection() {
        return timeGrupoCollection;
    }

    public void setTimeGrupoCollection(Collection<TimeGrupo> timeGrupoCollection) {
        this.timeGrupoCollection = timeGrupoCollection;
    }

    @XmlTransient
    public Collection<Jogador> getJogadorCollection() {
        return jogadorCollection;
    }

    public void setJogadorCollection(Collection<Jogador> jogadorCollection) {
        this.jogadorCollection = jogadorCollection;
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
        return "entidade.Time[ codigoTime=" + codigoTime + " ]";
    }
    
}
