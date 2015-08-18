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
@Table(name = "marca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Marca.findAll", query = "SELECT m FROM Marca m"),
    @NamedQuery(name = "Marca.findByCodigoMarca", query = "SELECT m FROM Marca m WHERE m.codigoMarca = :codigoMarca"),
    @NamedQuery(name = "Marca.findByDescricao", query = "SELECT m FROM Marca m WHERE m.descricao = :descricao"),
    @NamedQuery(name = "Marca.findByAtivo", query = "SELECT m FROM Marca m WHERE m.ativo = :ativo")})
public class Marca implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigoMarca")
    private Integer codigoMarca;
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "ativo")
    private boolean ativo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoMarca")
    private Collection<Marcaperiferico> marcaperifericoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoMarca")
    private Collection<Driver> driverCollection;

    public Marca() {
    }

    public Marca(Integer codigoMarca) {
        this.codigoMarca = codigoMarca;
    }

    public Marca(Integer codigoMarca, boolean ativo) {
        this.codigoMarca = codigoMarca;
        this.ativo = ativo;
    }

    public Integer getCodigoMarca() {
        return codigoMarca;
    }

    public void setCodigoMarca(Integer codigoMarca) {
        this.codigoMarca = codigoMarca;
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

    @XmlTransient
    public Collection<Marcaperiferico> getMarcaperifericoCollection() {
        return marcaperifericoCollection;
    }

    public void setMarcaperifericoCollection(Collection<Marcaperiferico> marcaperifericoCollection) {
        this.marcaperifericoCollection = marcaperifericoCollection;
    }

    @XmlTransient
    public Collection<Driver> getDriverCollection() {
        return driverCollection;
    }

    public void setDriverCollection(Collection<Driver> driverCollection) {
        this.driverCollection = driverCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoMarca != null ? codigoMarca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Marca)) {
            return false;
        }
        Marca other = (Marca) object;
        if ((this.codigoMarca == null && other.codigoMarca != null) || (this.codigoMarca != null && !this.codigoMarca.equals(other.codigoMarca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.treinoweb.model.entidade.Marca[ codigoMarca=" + codigoMarca + " ]";
    }
    
}
