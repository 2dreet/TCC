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
@Table(name = "jogador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jogador.findAll", query = "SELECT j FROM Jogador j"),
    @NamedQuery(name = "Jogador.findByCodigoJogador", query = "SELECT j FROM Jogador j WHERE j.codigoJogador = :codigoJogador"),
    @NamedQuery(name = "Jogador.findByDataCadastro", query = "SELECT j FROM Jogador j WHERE j.dataCadastro = :dataCadastro"),
    @NamedQuery(name = "Jogador.findByTitular", query = "SELECT j FROM Jogador j WHERE j.titular = :titular")})
public class Jogador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoJogador")
    private Integer codigoJogador;
    @Column(name = "dataCadastro")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    @Basic(optional = false)
    @Column(name = "titular")
    private boolean titular;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jogador")
    private Collection<JogadorBanimento> jogadorBanimentoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jogador")
    private Collection<JogadorPartida> jogadorPartidaCollection;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "codigoTime", referencedColumnName = "codigoTime")
    @ManyToOne
    private Time time;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jogador")
    private Collection<JogadorPeriferico> jogadorPerifericoCollection;

    public Jogador() {
    }

    public Jogador(Integer codigoJogador) {
        this.codigoJogador = codigoJogador;
    }

    public Jogador(Integer codigoJogador, boolean titular) {
        this.codigoJogador = codigoJogador;
        this.titular = titular;
    }

    public Integer getCodigoJogador() {
        return codigoJogador;
    }

    public void setCodigoJogador(Integer codigoJogador) {
        this.codigoJogador = codigoJogador;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public boolean getTitular() {
        return titular;
    }

    public void setTitular(boolean titular) {
        this.titular = titular;
    }

    @XmlTransient
    public Collection<JogadorBanimento> getJogadorBanimentoCollection() {
        return jogadorBanimentoCollection;
    }

    public void setJogadorBanimentoCollection(Collection<JogadorBanimento> jogadorBanimentoCollection) {
        this.jogadorBanimentoCollection = jogadorBanimentoCollection;
    }

    @XmlTransient
    public Collection<JogadorPartida> getJogadorPartidaCollection() {
        return jogadorPartidaCollection;
    }

    public void setJogadorPartidaCollection(Collection<JogadorPartida> jogadorPartidaCollection) {
        this.jogadorPartidaCollection = jogadorPartidaCollection;
    }

    public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
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
        hash += (codigoJogador != null ? codigoJogador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jogador)) {
            return false;
        }
        Jogador other = (Jogador) object;
        if ((this.codigoJogador == null && other.codigoJogador != null) || (this.codigoJogador != null && !this.codigoJogador.equals(other.codigoJogador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Jogador[ codigoJogador=" + codigoJogador + " ]";
    }
    
}
