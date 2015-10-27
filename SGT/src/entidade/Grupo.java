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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "grupo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupo.findAll", query = "SELECT g FROM Grupo g"),
    @NamedQuery(name = "Grupo.findByCodigoGrupo", query = "SELECT g FROM Grupo g WHERE g.codigoGrupo = :codigoGrupo"),
    @NamedQuery(name = "Grupo.findByDescricao", query = "SELECT g FROM Grupo g WHERE g.descricao = :descricao"),
    @NamedQuery(name = "Grupo.findByAtivo", query = "SELECT g FROM Grupo g WHERE g.ativo = :ativo")})
public class Grupo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoGrupo")
    private Integer codigoGrupo;
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "ativo")
    private boolean ativo;
    @JoinColumn(name = "codigoCampeonato", referencedColumnName = "codigoCampeonato")
    @ManyToOne(optional = false)
    private Campeonato campeonato;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupo")
    private Collection<TimeGrupo> timeGrupoCollection;
    @OneToMany(mappedBy = "grupo")
    private Collection<Partida> partidaCollection;

    public Grupo() {
    }

    public Grupo(Integer codigoGrupo) {
        this.codigoGrupo = codigoGrupo;
    }

    public Grupo(Integer codigoGrupo, boolean ativo) {
        this.codigoGrupo = codigoGrupo;
        this.ativo = ativo;
    }

    public Integer getCodigoGrupo() {
        return codigoGrupo;
    }

    public void setCodigoGrupo(Integer codigoGrupo) {
        this.codigoGrupo = codigoGrupo;
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

   

    public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	@XmlTransient
    public Collection<TimeGrupo> getTimeGrupoCollection() {
        return timeGrupoCollection;
    }

    public void setTimeGrupoCollection(Collection<TimeGrupo> timeGrupoCollection) {
        this.timeGrupoCollection = timeGrupoCollection;
    }

    @XmlTransient
    public Collection<Partida> getPartidaCollection() {
        return partidaCollection;
    }

    public void setPartidaCollection(Collection<Partida> partidaCollection) {
        this.partidaCollection = partidaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoGrupo != null ? codigoGrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupo)) {
            return false;
        }
        Grupo other = (Grupo) object;
        if ((this.codigoGrupo == null && other.codigoGrupo != null) || (this.codigoGrupo != null && !this.codigoGrupo.equals(other.codigoGrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Grupo[ codigoGrupo=" + codigoGrupo + " ]";
    }
    
}
