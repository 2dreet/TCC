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
@Table(name = "jogadorbanimento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jogadorbanimento.findAll", query = "SELECT j FROM Jogadorbanimento j"),
    @NamedQuery(name = "Jogadorbanimento.findByCodigoJogBan", query = "SELECT j FROM Jogadorbanimento j WHERE j.codigoJogBan = :codigoJogBan"),
    @NamedQuery(name = "Jogadorbanimento.findByDescricao", query = "SELECT j FROM Jogadorbanimento j WHERE j.descricao = :descricao"),
    @NamedQuery(name = "Jogadorbanimento.findByAtivo", query = "SELECT j FROM Jogadorbanimento j WHERE j.ativo = :ativo")})
public class Jogadorbanimento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoJogBan")
    private Integer codigoJogBan;
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "ativo")
    private boolean ativo;
    @JoinColumn(name = "codigoJogador", referencedColumnName = "codigoJogador")
    @ManyToOne(optional = false)
    private Jogador jogador;
    @JoinColumn(name = "codigoBanimento", referencedColumnName = "codigoBanimento")
    @ManyToOne(optional = false)
    private Banimento banimento;

    public Jogadorbanimento() {
    }

    public Jogadorbanimento(Integer codigoJogBan) {
        this.codigoJogBan = codigoJogBan;
    }

    public Jogadorbanimento(Integer codigoJogBan, boolean ativo) {
        this.codigoJogBan = codigoJogBan;
        this.ativo = ativo;
    }

    public Integer getCodigoJogBan() {
        return codigoJogBan;
    }

    public void setCodigoJogBan(Integer codigoJogBan) {
        this.codigoJogBan = codigoJogBan;
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
    
    public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public Banimento getBanimento() {
		return banimento;
	}

	public void setBanimento(Banimento banimento) {
		this.banimento = banimento;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoJogBan != null ? codigoJogBan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jogadorbanimento)) {
            return false;
        }
        Jogadorbanimento other = (Jogadorbanimento) object;
        if ((this.codigoJogBan == null && other.codigoJogBan != null) || (this.codigoJogBan != null && !this.codigoJogBan.equals(other.codigoJogBan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Jogadorbanimento[ codigoJogBan=" + codigoJogBan + " ]";
    }
    
}
