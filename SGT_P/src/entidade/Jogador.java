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
    @NamedQuery(name = "Jogador.findByDataCadastro", query = "SELECT j FROM Jogador j WHERE j.dataCadastro = :dataCadastro")})
public class Jogador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigoJogador")
    private Integer codigoJogador;
    @Column(name = "dataCadastro")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoJogador")
    private Collection<Jogadorbanimento> jogadorbanimentoCollection;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne(optional = false)
    private Usuario codigoUsuario;
    @JoinColumn(name = "codigoTime", referencedColumnName = "codigoTime")
    @ManyToOne(optional = false)
    private Time codigoTime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoJogador")
    private Collection<Jogadorperiferico> jogadorperifericoCollection;

    public Jogador() {
    }

    public Jogador(Integer codigoJogador) {
        this.codigoJogador = codigoJogador;
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

    @XmlTransient
    public Collection<Jogadorbanimento> getJogadorbanimentoCollection() {
        return jogadorbanimentoCollection;
    }

    public void setJogadorbanimentoCollection(Collection<Jogadorbanimento> jogadorbanimentoCollection) {
        this.jogadorbanimentoCollection = jogadorbanimentoCollection;
    }

    public Usuario getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Usuario codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Time getCodigoTime() {
        return codigoTime;
    }

    public void setCodigoTime(Time codigoTime) {
        this.codigoTime = codigoTime;
    }

    @XmlTransient
    public Collection<Jogadorperiferico> getJogadorperifericoCollection() {
        return jogadorperifericoCollection;
    }

    public void setJogadorperifericoCollection(Collection<Jogadorperiferico> jogadorperifericoCollection) {
        this.jogadorperifericoCollection = jogadorperifericoCollection;
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
        return "br.com.treinoweb.model.entidade.Jogador[ codigoJogador=" + codigoJogador + " ]";
    }
    
}
