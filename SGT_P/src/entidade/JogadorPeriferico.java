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
@Table(name = "jogador_periferico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JogadorPeriferico.findAll", query = "SELECT j FROM JogadorPeriferico j"),
    @NamedQuery(name = "JogadorPeriferico.findByCodigoJD", query = "SELECT j FROM JogadorPeriferico j WHERE j.codigoJD = :codigoJD")})
public class JogadorPeriferico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoJD")
    private Integer codigoJD;
    @JoinColumn(name = "codigoJogador", referencedColumnName = "codigoJogador")
    @ManyToOne(optional = false)
    private Jogador jogador;
    @JoinColumn(name = "codigoPeriferico", referencedColumnName = "codigoPeriferico")
    @ManyToOne(optional = false)
    private Periferico periferico;
    @JoinColumn(name = "codigoMarca", referencedColumnName = "codigoMarca")
    @ManyToOne(optional = false)
    private Marca marca;
    

    public JogadorPeriferico() {
    }

    public JogadorPeriferico(Integer codigoJD) {
        this.codigoJD = codigoJD;
    }

    public Integer getCodigoJD() {
        return codigoJD;
    }

    public void setCodigoJD(Integer codigoJD) {
        this.codigoJD = codigoJD;
    }

    public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
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
        hash += (codigoJD != null ? codigoJD.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JogadorPeriferico)) {
            return false;
        }
        JogadorPeriferico other = (JogadorPeriferico) object;
        if ((this.codigoJD == null && other.codigoJD != null) || (this.codigoJD != null && !this.codigoJD.equals(other.codigoJD))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.JogadorPeriferico[ codigoJD=" + codigoJD + " ]";
    }
    
}
