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
@Table(name = "funcionario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Funcionario.findAll", query = "SELECT f FROM Funcionario f"),
    @NamedQuery(name = "Funcionario.findByCodigoFuncionario", query = "SELECT f FROM Funcionario f WHERE f.codigoFuncionario = :codigoFuncionario"),
    @NamedQuery(name = "Funcionario.findByDataCadastro", query = "SELECT f FROM Funcionario f WHERE f.dataCadastro = :dataCadastro")})
public class Funcionario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigoFuncionario")
    private Integer codigoFuncionario;
    @Column(name = "dataCadastro")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne(optional = false)
    private Usuario codigoUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoFuncionario")
    private Collection<Funcionariocampeonato> funcionariocampeonatoCollection;

    public Funcionario() {
    }

    public Funcionario(Integer codigoFuncionario) {
        this.codigoFuncionario = codigoFuncionario;
    }

    public Integer getCodigoFuncionario() {
        return codigoFuncionario;
    }

    public void setCodigoFuncionario(Integer codigoFuncionario) {
        this.codigoFuncionario = codigoFuncionario;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Usuario getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Usuario codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    @XmlTransient
    public Collection<Funcionariocampeonato> getFuncionariocampeonatoCollection() {
        return funcionariocampeonatoCollection;
    }

    public void setFuncionariocampeonatoCollection(Collection<Funcionariocampeonato> funcionariocampeonatoCollection) {
        this.funcionariocampeonatoCollection = funcionariocampeonatoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoFuncionario != null ? codigoFuncionario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcionario)) {
            return false;
        }
        Funcionario other = (Funcionario) object;
        if ((this.codigoFuncionario == null && other.codigoFuncionario != null) || (this.codigoFuncionario != null && !this.codigoFuncionario.equals(other.codigoFuncionario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.treinoweb.model.entidade.Funcionario[ codigoFuncionario=" + codigoFuncionario + " ]";
    }
    
}
