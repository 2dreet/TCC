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
@Table(name = "funcionariocampeonato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Funcionariocampeonato.findAll", query = "SELECT f FROM Funcionariocampeonato f"),
    @NamedQuery(name = "Funcionariocampeonato.findByCodigoFuncCamp", query = "SELECT f FROM Funcionariocampeonato f WHERE f.codigoFuncCamp = :codigoFuncCamp")})
public class Funcionariocampeonato implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoFuncCamp")
    private Integer codigoFuncCamp;
    @JoinColumn(name = "codigoFuncionario", referencedColumnName = "codigoFuncionario")
    @ManyToOne(optional = false)
    private Funcionario funcionario;
    @JoinColumn(name = "codigoCampeonato", referencedColumnName = "codigoCampeonato")
    @ManyToOne(optional = false)
    private Campeonato campeonato;

    public Funcionariocampeonato() {
    }

    public Funcionariocampeonato(Integer codigoFuncCamp) {
        this.codigoFuncCamp = codigoFuncCamp;
    }

    public Integer getCodigoFuncCamp() {
        return codigoFuncCamp;
    }

    public void setCodigoFuncCamp(Integer codigoFuncCamp) {
        this.codigoFuncCamp = codigoFuncCamp;
    }
    
    public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
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
        hash += (codigoFuncCamp != null ? codigoFuncCamp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcionariocampeonato)) {
            return false;
        }
        Funcionariocampeonato other = (Funcionariocampeonato) object;
        if ((this.codigoFuncCamp == null && other.codigoFuncCamp != null) || (this.codigoFuncCamp != null && !this.codigoFuncCamp.equals(other.codigoFuncCamp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "criaentidades.Funcionariocampeonato[ codigoFuncCamp=" + codigoFuncCamp + " ]";
    }
    
}
