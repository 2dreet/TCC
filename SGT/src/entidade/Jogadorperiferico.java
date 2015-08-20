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
@Table(name = "jogadorperiferico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jogadorperiferico.findAll", query = "SELECT j FROM Jogadorperiferico j"),
    @NamedQuery(name = "Jogadorperiferico.findByCodigojogaPeri", query = "SELECT j FROM Jogadorperiferico j WHERE j.codigojogaPeri = :codigojogaPeri")})
public class Jogadorperiferico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigojogaPeri")
    private Integer codigojogaPeri;
    @JoinColumn(name = "codigoJogador", referencedColumnName = "codigoJogador")
    @ManyToOne(optional = false)
    private Jogador jogador;
    @JoinColumn(name = "codigoPeriferico", referencedColumnName = "codigoPeriferico")
    @ManyToOne(optional = false)
    private Periferico periferico;

    public Jogadorperiferico() {
    }

    public Jogadorperiferico(Integer codigojogaPeri) {
        this.codigojogaPeri = codigojogaPeri;
    }

    public Integer getCodigojogaPeri() {
        return codigojogaPeri;
    }

    public void setCodigojogaPeri(Integer codigojogaPeri) {
        this.codigojogaPeri = codigojogaPeri;
    }
    
    public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public Periferico getPeriferico() {
		return periferico;
	}

	public void setPeriferico(Periferico periferico) {
		this.periferico = periferico;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (codigojogaPeri != null ? codigojogaPeri.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jogadorperiferico)) {
            return false;
        }
        Jogadorperiferico other = (Jogadorperiferico) object;
        if ((this.codigojogaPeri == null && other.codigojogaPeri != null) || (this.codigojogaPeri != null && !this.codigojogaPeri.equals(other.codigojogaPeri))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "criaentidades.Jogadorperiferico[ codigojogaPeri=" + codigojogaPeri + " ]";
    }
    
}
