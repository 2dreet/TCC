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
@Table(name = "time_grupo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TimeGrupo.findAll", query = "SELECT t FROM TimeGrupo t"),
    @NamedQuery(name = "TimeGrupo.findByCodigoTimeGrupo", query = "SELECT t FROM TimeGrupo t WHERE t.codigoTimeGrupo = :codigoTimeGrupo"),
    @NamedQuery(name = "TimeGrupo.findByPontuacao", query = "SELECT t FROM TimeGrupo t WHERE t.pontuacao = :pontuacao"),
    @NamedQuery(name = "TimeGrupo.findByClasse", query = "SELECT t FROM TimeGrupo t WHERE t.classe = :classe"),
    @NamedQuery(name = "TimeGrupo.findByAtivo", query = "SELECT t FROM TimeGrupo t WHERE t.ativo = :ativo")})
public class TimeGrupo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoTimeGrupo")
    private Integer codigoTimeGrupo;
    @Column(name = "pontuacao")
    private Integer pontuacao;
    @Column(name = "classe")
    private Integer classe;
    @Column(name = "ativo")
    private Integer ativo;
    @JoinColumn(name = "codigoTime", referencedColumnName = "codigoTime")
    @ManyToOne(optional = false)
    private Time time;
    @JoinColumn(name = "codigoGrupo", referencedColumnName = "codigoGrupo")
    @ManyToOne(optional = false)
    private Grupo grupo;

    public TimeGrupo() {
    }

    public TimeGrupo(Integer codigoTimeGrupo) {
        this.codigoTimeGrupo = codigoTimeGrupo;
    }

    public Integer getCodigoTimeGrupo() {
        return codigoTimeGrupo;
    }

    public void setCodigoTimeGrupo(Integer codigoTimeGrupo) {
        this.codigoTimeGrupo = codigoTimeGrupo;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Integer getClasse() {
        return classe;
    }

    public void setClasse(Integer classe) {
        this.classe = classe;
    }

    public Integer getAtivo() {
        return ativo;
    }

    public void setAtivo(Integer ativo) {
        this.ativo = ativo;
    }

    public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoTimeGrupo != null ? codigoTimeGrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TimeGrupo)) {
            return false;
        }
        TimeGrupo other = (TimeGrupo) object;
        if ((this.codigoTimeGrupo == null && other.codigoTimeGrupo != null) || (this.codigoTimeGrupo != null && !this.codigoTimeGrupo.equals(other.codigoTimeGrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.TimeGrupo[ codigoTimeGrupo=" + codigoTimeGrupo + " ]";
    }
    
}
