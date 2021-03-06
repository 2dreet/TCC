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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "Partida.findByIndice", query = "SELECT p FROM Partida p WHERE p.indice = :indice"),
    @NamedQuery(name = "Partida.findByDescricao", query = "SELECT p FROM Partida p WHERE p.descricao = :descricao"),
    @NamedQuery(name = "Partida.findByHoraInicio", query = "SELECT p FROM Partida p WHERE p.horaInicio = :horaInicio"),
    @NamedQuery(name = "Partida.findByHoraFim", query = "SELECT p FROM Partida p WHERE p.horaFim = :horaFim"),
    @NamedQuery(name = "Partida.findByWinerLower", query = "SELECT p FROM Partida p WHERE p.winerLower = :winerLower"),
    @NamedQuery(name = "Partida.findByAtivo", query = "SELECT p FROM Partida p WHERE p.ativo = :ativo")})
public class Partida implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoPartida")
    private Integer codigoPartida;
    @Column(name = "indice")
    private Integer indice;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "horaInicio")
    @Temporal(TemporalType.DATE)
    private Date horaInicio;
    @Column(name = "horaFim")
    @Temporal(TemporalType.DATE)
    private Date horaFim;
    @Basic(optional = false)
    @Column(name = "winerLower")
    private boolean winerLower;
    @Basic(optional = false)
    @Column(name = "ativo")
    private boolean ativo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partida")
    private Collection<PcPartida> pcPartidaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partida")
    private Collection<TimePartida> timePartidaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partida")
    private Collection<JogadorPartida> jogadorPartidaCollection;
    @JoinColumn(name = "codigoGrupo", referencedColumnName = "codigoGrupo")
    @ManyToOne
    private Grupo grupo;
    @OneToMany(mappedBy = "partidaFilho")
    private Collection<Partida> partidaCollection;
    @JoinColumn(name = "codigoPartidaFilho", referencedColumnName = "codigoPartida")
    @ManyToOne
    private Partida partidaFilho;
    @JoinColumn(name = "codigoPartidaLower", referencedColumnName = "codigoPartida")
    @ManyToOne
    private Partida partidaLower;
    @JoinColumn(name = "codigoCampeonato", referencedColumnName = "codigoCampeonato")
    @ManyToOne(optional = false)
    private Campeonato campeonato;

    public Partida() {
    }

    public Partida(Integer codigoPartida) {
        this.codigoPartida = codigoPartida;
    }

    public Partida(Integer codigoPartida, boolean winerLower, boolean ativo) {
        this.codigoPartida = codigoPartida;
        this.winerLower = winerLower;
        this.ativo = ativo;
    }

    public Integer getCodigoPartida() {
        return codigoPartida;
    }

    public void setCodigoPartida(Integer codigoPartida) {
        this.codigoPartida = codigoPartida;
    }

    public Integer getIndice() {
        return indice;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public boolean getWinerLower() {
        return winerLower;
    }

    public void setWinerLower(boolean winerLower) {
        this.winerLower = winerLower;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Partida getPartidaLower() {
		return partidaLower;
	}

	public void setPartidaLower(Partida partidaLower) {
		this.partidaLower = partidaLower;
	}

	@XmlTransient
    public Collection<PcPartida> getPcPartidaCollection() {
        return pcPartidaCollection;
    }

    public void setPcPartidaCollection(Collection<PcPartida> pcPartidaCollection) {
        this.pcPartidaCollection = pcPartidaCollection;
    }

    @XmlTransient
    public Collection<TimePartida> getTimePartidaCollection() {
        return timePartidaCollection;
    }

    public void setTimePartidaCollection(Collection<TimePartida> timePartidaCollection) {
        this.timePartidaCollection = timePartidaCollection;
    }

    @XmlTransient
    public Collection<JogadorPartida> getJogadorPartidaCollection() {
        return jogadorPartidaCollection;
    }

    public void setJogadorPartidaCollection(Collection<JogadorPartida> jogadorPartidaCollection) {
        this.jogadorPartidaCollection = jogadorPartidaCollection;
    }

    
    @XmlTransient
    public Collection<Partida> getPartidaCollection() {
        return partidaCollection;
    }

    public void setPartidaCollection(Collection<Partida> partidaCollection) {
        this.partidaCollection = partidaCollection;
    }

    
    public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Partida getPartidaFilho() {
		return partidaFilho;
	}

	public void setPartidaFilho(Partida partidaFilho) {
		this.partidaFilho = partidaFilho;
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
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
