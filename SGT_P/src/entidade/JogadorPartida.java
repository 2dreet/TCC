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
@Table(name = "jogador_partida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JogadorPartida.findAll", query = "SELECT j FROM JogadorPartida j"),
    @NamedQuery(name = "JogadorPartida.findByCodigoJP", query = "SELECT j FROM JogadorPartida j WHERE j.codigoJP = :codigoJP")})
public class JogadorPartida implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoJP")
    private Integer codigoJP;
    @JoinColumn(name = "codigoJogador", referencedColumnName = "codigoJogador")
    @ManyToOne(optional = false)
    private Jogador jogador;
    @JoinColumn(name = "codigoPartida", referencedColumnName = "codigoPartida")
    @ManyToOne(optional = false)
    private Partida partida;

    public JogadorPartida() {
    }

    public JogadorPartida(Integer codigoJP) {
        this.codigoJP = codigoJP;
    }

    public Integer getCodigoJP() {
        return codigoJP;
    }

    public void setCodigoJP(Integer codigoJP) {
        this.codigoJP = codigoJP;
    }
    
    public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoJP != null ? codigoJP.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JogadorPartida)) {
            return false;
        }
        JogadorPartida other = (JogadorPartida) object;
        if ((this.codigoJP == null && other.codigoJP != null) || (this.codigoJP != null && !this.codigoJP.equals(other.codigoJP))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.JogadorPartida[ codigoJP=" + codigoJP + " ]";
    }
    
}
