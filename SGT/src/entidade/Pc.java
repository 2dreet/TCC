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
@Table(name = "pc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pc.findAll", query = "SELECT p FROM Pc p"),
    @NamedQuery(name = "Pc.findByCodigoPC", query = "SELECT p FROM Pc p WHERE p.codigoPC = :codigoPC"),
    @NamedQuery(name = "Pc.findByDescricao", query = "SELECT p FROM Pc p WHERE p.descricao = :descricao"),
    @NamedQuery(name = "Pc.findByIp", query = "SELECT p FROM Pc p WHERE p.ip = :ip"),
    @NamedQuery(name = "Pc.findByAtivo", query = "SELECT p FROM Pc p WHERE p.ativo = :ativo")})
public class Pc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoPC")
    private Integer codigoPC;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "ip")
    private String ip;
    @Basic(optional = false)
    @Column(name = "ativo")
    private boolean ativo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pc")
    private Collection<PcPartida> pcPartidaCollection;

    public Pc() {
    }

    public Pc(Integer codigoPC) {
        this.codigoPC = codigoPC;
    }

    public Pc(Integer codigoPC, boolean ativo) {
        this.codigoPC = codigoPC;
        this.ativo = ativo;
    }

    public Integer getCodigoPC() {
        return codigoPC;
    }

    public void setCodigoPC(Integer codigoPC) {
        this.codigoPC = codigoPC;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @XmlTransient
    public Collection<PcPartida> getPcPartidaCollection() {
        return pcPartidaCollection;
    }

    public void setPcPartidaCollection(Collection<PcPartida> pcPartidaCollection) {
        this.pcPartidaCollection = pcPartidaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPC != null ? codigoPC.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pc)) {
            return false;
        }
        Pc other = (Pc) object;
        if ((this.codigoPC == null && other.codigoPC != null) || (this.codigoPC != null && !this.codigoPC.equals(other.codigoPC))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Pc[ codigoPC=" + codigoPC + " ]";
    }
    
}
