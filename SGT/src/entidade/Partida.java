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
@Table(name = "partida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Partida.findAll", query = "SELECT p FROM Partida p"),
    @NamedQuery(name = "Partida.findByCodigoPartida", query = "SELECT p FROM Partida p WHERE p.codigoPartida = :codigoPartida"),
    @NamedQuery(name = "Partida.findByDescricao", query = "SELECT p FROM Partida p WHERE p.descricao = :descricao"),
    @NamedQuery(name = "Partida.findByCancelada", query = "SELECT p FROM Partida p WHERE p.cancelada = :cancelada"),
    @NamedQuery(name = "Partida.findByMotivo", query = "SELECT p FROM Partida p WHERE p.motivo = :motivo"),
    @NamedQuery(name = "Partida.findByHoraInicio", query = "SELECT p FROM Partida p WHERE p.horaInicio = :horaInicio"),
    @NamedQuery(name = "Partida.findByHoraFim", query = "SELECT p FROM Partida p WHERE p.horaFim = :horaFim"),
    @NamedQuery(name = "Partida.findByAtivo", query = "SELECT p FROM Partida p WHERE p.ativo = :ativo")})
public class Partida implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoPartida")
    private Integer codigoPartida;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "cancelada")
    private Boolean cancelada;
    @Column(name = "motivo")
    private String motivo;
    @Column(name = "horaInicio")
    @Temporal(TemporalType.DATE)
    private Date horaInicio;
    @Column(name = "horaFim")
    @Temporal(TemporalType.DATE)
    private Date horaFim;
    @Basic(optional = false)
    @Column(name = "ativo")
    private boolean ativo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partida")
    private Collection<Campeonatopartida> campeonatopartidaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partida")
    private Collection<Jogadorpartida> jogadorpartidaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partida")
    private Collection<Pcpartida> pcpartidaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partida")
    private Collection<Timepartida> timepartidaCollection;

    public Partida() {
    }

    public Partida(Integer codigoPartida) {
        this.codigoPartida = codigoPartida;
    }

    public Partida(Integer codigoPartida, boolean ativo) {
        this.codigoPartida = codigoPartida;
        this.ativo = ativo;
    }

    public Integer getCodigoPartida() {
        return codigoPartida;
    }

    public void setCodigoPartida(Integer codigoPartida) {
        this.codigoPartida = codigoPartida;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getCancelada() {
        return cancelada;
    }

    public void setCancelada(Boolean cancelada) {
        this.cancelada = cancelada;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Date horaFim) {
        this.horaFim = horaFim;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @XmlTransient
    public Collection<Campeonatopartida> getCampeonatopartidaCollection() {
        return campeonatopartidaCollection;
    }

    public void setCampeonatopartidaCollection(Collection<Campeonatopartida> campeonatopartidaCollection) {
        this.campeonatopartidaCollection = campeonatopartidaCollection;
    }

    @XmlTransient
    public Collection<Jogadorpartida> getJogadorpartidaCollection() {
        return jogadorpartidaCollection;
    }

    public void setJogadorpartidaCollection(Collection<Jogadorpartida> jogadorpartidaCollection) {
        this.jogadorpartidaCollection = jogadorpartidaCollection;
    }

    @XmlTransient
    public Collection<Pcpartida> getPcpartidaCollection() {
        return pcpartidaCollection;
    }

    public void setPcpartidaCollection(Collection<Pcpartida> pcpartidaCollection) {
        this.pcpartidaCollection = pcpartidaCollection;
    }

    @XmlTransient
    public Collection<Timepartida> getTimepartidaCollection() {
        return timepartidaCollection;
    }

    public void setTimepartidaCollection(Collection<Timepartida> timepartidaCollection) {
        this.timepartidaCollection = timepartidaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPartida != null ? codigoPartida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Partida)) {
            return false;
        }
        Partida other = (Partida) object;
        if ((this.codigoPartida == null && other.codigoPartida != null) || (this.codigoPartida != null && !this.codigoPartida.equals(other.codigoPartida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Partida[ codigoPartida=" + codigoPartida + " ]";
    }
    
}
