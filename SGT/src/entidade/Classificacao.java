/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jose
 */
@Entity
@Table(name = "classificacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Classificacao.findAll", query = "SELECT c FROM Classificacao c"),
    @NamedQuery(name = "Classificacao.findByCodigoClassificacao", query = "SELECT c FROM Classificacao c WHERE c.codigoClassificacao = :codigoClassificacao")})
public class Classificacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoClassificacao")
    private Integer codigoClassificacao;
    @JoinColumn(name = "timeTerceiro", referencedColumnName = "codigoTime")
    @ManyToOne(optional = false)
    private Time timeTerceiro;
    @JoinColumn(name = "timeSegundo", referencedColumnName = "codigoTime")
    @ManyToOne(optional = false)
    private Time timeSegundo;
    @JoinColumn(name = "timePrimeiro", referencedColumnName = "codigoTime")
    @ManyToOne(optional = false)
    private Time timePrimeiro;
    @JoinColumn(name = "codigoCampeonato", referencedColumnName = "codigoCampeonato")
    @ManyToOne(optional = false)
    private Campeonato campeonato;

    public Classificacao() {
    }

    public Classificacao(Integer codigoClassificacao) {
        this.codigoClassificacao = codigoClassificacao;
    }

    public Integer getCodigoClassificacao() {
        return codigoClassificacao;
    }

    public void setCodigoClassificacao(Integer codigoClassificacao) {
        this.codigoClassificacao = codigoClassificacao;
    }

    public Time getTimeTerceiro() {
        return timeTerceiro;
    }

    public void setTimeTerceiro(Time timeTerceiro) {
        this.timeTerceiro = timeTerceiro;
    }

    public Time getTimeSegundo() {
        return timeSegundo;
    }

    public void setTimeSegundo(Time timeSegundo) {
        this.timeSegundo = timeSegundo;
    }

    public Time getTimePrimeiro() {
        return timePrimeiro;
    }

    public void setTimePrimeiro(Time timePrimeiro) {
        this.timePrimeiro = timePrimeiro;
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
        hash += (codigoClassificacao != null ? codigoClassificacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Classificacao)) {
            return false;
        }
        Classificacao other = (Classificacao) object;
        if ((this.codigoClassificacao == null && other.codigoClassificacao != null) || (this.codigoClassificacao != null && !this.codigoClassificacao.equals(other.codigoClassificacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "criaentidades.Classificacao[ codigoClassificacao=" + codigoClassificacao + " ]";
    }
    
}
