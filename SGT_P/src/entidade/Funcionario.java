package entidade;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the funcionario database table.
 * 
 */
@Entity
@NamedQuery(name="Funcionario.findAll", query="SELECT f FROM Funcionario f")
public class Funcionario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigoFuncionario;

	@Temporal(TemporalType.DATE)
	private Date dataCadastro;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="codigoUsuario")
	private Usuario usuario;

	//bi-directional many-to-one association to Funcionariocampeonato
	@OneToMany(mappedBy="funcionario")
	private List<Funcionariocampeonato> funcionariocampeonatos;

	public Funcionario() {
	}

	public int getCodigoFuncionario() {
		return this.codigoFuncionario;
	}

	public void setCodigoFuncionario(int codigoFuncionario) {
		this.codigoFuncionario = codigoFuncionario;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Funcionariocampeonato> getFuncionariocampeonatos() {
		return this.funcionariocampeonatos;
	}

	public void setFuncionariocampeonatos(List<Funcionariocampeonato> funcionariocampeonatos) {
		this.funcionariocampeonatos = funcionariocampeonatos;
	}

	public Funcionariocampeonato addFuncionariocampeonato(Funcionariocampeonato funcionariocampeonato) {
		getFuncionariocampeonatos().add(funcionariocampeonato);
		funcionariocampeonato.setFuncionario(this);

		return funcionariocampeonato;
	}

	public Funcionariocampeonato removeFuncionariocampeonato(Funcionariocampeonato funcionariocampeonato) {
		getFuncionariocampeonatos().remove(funcionariocampeonato);
		funcionariocampeonato.setFuncionario(null);

		return funcionariocampeonato;
	}

}