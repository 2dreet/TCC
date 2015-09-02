/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jose
 */
@Entity
@Table(name = "campeonato_time")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CampeonatoTime.findAll", query = "SELECT c FROM CampeonatoTime c"),
    @NamedQuery(name = "CampeonatoTime.findByCodigoCampTime", query = "SELECT c FROM CampeonatoTime c WHERE c.codigoCampTime = :codigoCampTime"),
    @NamedQuery(name = "CampeonatoTime.findByDataInscricao", query = "SELECT c FROM CampeonatoTime c WHERE c.dataInscricao = :dataInscricao")})
public class CampeonatoTime implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoCampTime")
    private Integer codigoCampTime;
    @Column(name = "dataInscricao")
    @Temporal(TemporalType.DATE)
    private Date dataInscricao;
    @JoinColumn(name = "codigoTime", referencedColumnName = "codigoTime")
    @ManyToOne(optional = false)
    private Time time;
    @JoinColumn(name = "codigoCampeonato", referencedColumnName = "codigoCampeonato")
    @ManyToOne(optional = false)
    private Campeonato campeonato;

    public CampeonatoTime() {
    }

    public CampeonatoTime(Integer codigoCampTime) {
        this.codigoCampTime = codigoCampTime;
    }

    public Integer getCodigoCampTime() {
        return codigoCampTime;
    }

    public void setCodigoCampTime(Integer codigoCampTime) {
        this.codigoCampTime = codigoCampTime;
    }

    public Date getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(Date dataInscricao) {
        this.dataInscricao = dataInscricao;
    }
    
    public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
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
        hash += (codigoCampTime != null ? codigoCampTime.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CampeonatoTime)) {
            return false;
        }
        CampeonatoTime other = (CampeonatoTime) object;
        if ((this.codigoCampTime == null && other.codigoCampTime != null) || (this.codigoCampTime != null && !this.codigoCampTime.equals(other.codigoCampTime))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.CampeonatoTime[ codigoCampTime=" + codigoCampTime + " ]";
    }
    
}
