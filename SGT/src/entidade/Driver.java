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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "driver")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Driver.findAll", query = "SELECT d FROM Driver d"),
    @NamedQuery(name = "Driver.findByCodigoDriver", query = "SELECT d FROM Driver d WHERE d.codigoDriver = :codigoDriver"),
    @NamedQuery(name = "Driver.findByDescricao", query = "SELECT d FROM Driver d WHERE d.descricao = :descricao"),
    @NamedQuery(name = "Driver.findByAtivo", query = "SELECT d FROM Driver d WHERE d.ativo = :ativo")})
public class Driver implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoDriver")
    private Integer codigoDriver;
    @Column(name = "descricao")
    private String descricao;
    @Lob
    @Column(name = "localArquivo")
    private String localArquivo;
    @Basic(optional = false)
    @Column(name = "ativo")
    private boolean ativo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "driver")
    private Collection<Jogadordriver> jogadordriverCollection;
    @JoinColumn(name = "codigoPeriferico", referencedColumnName = "codigoPeriferico")
    @ManyToOne(optional = false)
    private Periferico periferico;
    @JoinColumn(name = "codigoMarca", referencedColumnName = "codigoMarca")
    @ManyToOne(optional = false)
    private Marca marca;

    public Driver() {
    }

    public Driver(Integer codigoDriver) {
        this.codigoDriver = codigoDriver;
    }

    public Driver(Integer codigoDriver, boolean ativo) {
        this.codigoDriver = codigoDriver;
        this.ativo = ativo;
    }

    public Integer getCodigoDriver() {
        return codigoDriver;
    }

    public void setCodigoDriver(Integer codigoDriver) {
        this.codigoDriver = codigoDriver;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocalArquivo() {
        return localArquivo;
    }

    public void setLocalArquivo(String localArquivo) {
        this.localArquivo = localArquivo;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @XmlTransient
    public Collection<Jogadordriver> getJogadordriverCollection() {
        return jogadordriverCollection;
    }

    public void setJogadordriverCollection(Collection<Jogadordriver> jogadordriverCollection) {
        this.jogadordriverCollection = jogadordriverCollection;
    }
    
    public Periferico getPeriferico() {
		return periferico;
	}

	public void setPeriferico(Periferico periferico) {
		this.periferico = periferico;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoDriver != null ? codigoDriver.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Driver)) {
            return false;
        }
        Driver other = (Driver) object;
        if ((this.codigoDriver == null && other.codigoDriver != null) || (this.codigoDriver != null && !this.codigoDriver.equals(other.codigoDriver))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Driver[ codigoDriver=" + codigoDriver + " ]";
    }
    
}
