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
@Table(name = "campeonato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Campeonato.findAll", query = "SELECT c FROM Campeonato c"),
    @NamedQuery(name = "Campeonato.findByCodigoCampeonato", query = "SELECT c FROM Campeonato c WHERE c.codigoCampeonato = :codigoCampeonato"),
    @NamedQuery(name = "Campeonato.findByDescricao", query = "SELECT c FROM Campeonato c WHERE c.descricao = :descricao"),
    @NamedQuery(name = "Campeonato.findByDataCadastro", query = "SELECT c FROM Campeonato c WHERE c.dataCadastro = :dataCadastro"),
    @NamedQuery(name = "Campeonato.findByDataIncio", query = "SELECT c FROM Campeonato c WHERE c.dataIncio = :dataIncio"),
    @NamedQuery(name = "Campeonato.findByDataFim", query = "SELECT c FROM Campeonato c WHERE c.dataFim = :dataFim"),
    @NamedQuery(name = "Campeonato.findByAtivo", query = "SELECT c FROM Campeonato c WHERE c.ativo = :ativo")})
public class Campeonato implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoCampeonato")
    private Integer codigoCampeonato;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "dataCadastro")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    @Column(name = "dataIncio")
    @Temporal(TemporalType.DATE)
    private Date dataIncio;
    @Column(name = "dataFim")
    @Temporal(TemporalType.DATE)
    private Date dataFim;
    @Basic(optional = false)
    @Column(name = "ativo")
    private boolean ativo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "campeonato")
    private Collection<Grupo> grupoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "campeonato")
    private Collection<Classificacao> classificacaoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "campeonato")
    private Collection<CampeonatoTime> campeonatoTimeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "campeonato")
    private Collection<FuncionarioCampeonato> funcionarioCampeonatoCollection;
    @JoinColumn(name = "codigoChave", referencedColumnName = "codigoChave")
    @ManyToOne(optional = false)
    private Chave chave;
    @JoinColumn(name = "codigoModalidade", referencedColumnName = "codigoModalidade")
    @ManyToOne(optional = false)
    private Modalidade modalidade;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "campeonato")
    private Collection<Partida> partidaCollection;

    public Campeonato() {
    }

    public Campeonato(Integer codigoCampeonato) {
        this.codigoCampeonato = codigoCampeonato;
    }

    public Campeonato(Integer codigoCampeonato, boolean ativo) {
        this.codigoCampeonato = codigoCampeonato;
        this.ativo = ativo;
    }

    public Integer getCodigoCampeonato() {
        return codigoCampeonato;
    }

    public void setCodigoCampeonato(Integer codigoCampeonato) {
        this.codigoCampeonato = codigoCampeonato;
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

    public Date getDataIncio() {
        return dataIncio;
    }

    public void setDataIncio(Date dataIncio) {
        this.dataIncio = dataIncio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @XmlTransient
    public Collection<Grupo> getGrupoCollection() {
        return grupoCollection;
    }

    public void setGrupoCollection(Collection<Grupo> grupoCollection) {
        this.grupoCollection = grupoCollection;
    }

    @XmlTransient
    public Collection<Classificacao> getClassificacaoCollection() {
        return classificacaoCollection;
    }

    public void setClassificacaoCollection(Collection<Classificacao> classificacaoCollection) {
        this.classificacaoCollection = classificacaoCollection;
    }

    @XmlTransient
    public Collection<CampeonatoTime> getCampeonatoTimeCollection() {
        return campeonatoTimeCollection;
    }

    public void setCampeonatoTimeCollection(Collection<CampeonatoTime> campeonatoTimeCollection) {
        this.campeonatoTimeCollection = campeonatoTimeCollection;
    }

    @XmlTransient
    public Collection<FuncionarioCampeonato> getFuncionarioCampeonatoCollection() {
        return funcionarioCampeonatoCollection;
    }

    public void setFuncionarioCampeonatoCollection(Collection<FuncionarioCampeonato> funcionarioCampeonatoCollection) {
        this.funcionarioCampeonatoCollection = funcionarioCampeonatoCollection;
    }
    
    public Chave getChave() {
		return chave;
	}

	public void setChave(Chave chave) {
		this.chave = chave;
	}

	public Modalidade getModalidade() {
		return modalidade;
	}

	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
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
        hash += (codigoCampeonato != null ? codigoCampeonato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Campeonato)) {
            return false;
        }
        Campeonato other = (Campeonato) object;
        if ((this.codigoCampeonato == null && other.codigoCampeonato != null) || (this.codigoCampeonato != null && !this.codigoCampeonato.equals(other.codigoCampeonato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Campeonato[ codigoCampeonato=" + codigoCampeonato + " ]";
    }
    
}
