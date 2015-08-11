package entidade;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the permissao database table.
 * 
 */
@Entity
@NamedQuery(name="Permissao.findAll", query="SELECT p FROM Permissao p")
public class Permissao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigoPermissao;

	private byte ativo;

	private String descricao;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="permissao")
	private List<Usuario> usuarios;

	public Permissao() {
	}

	public int getCodigoPermissao() {
		return this.codigoPermissao;
	}

	public void setCodigoPermissao(int codigoPermissao) {
		this.codigoPermissao = codigoPermissao;
	}

	public byte getAtivo() {
		return this.ativo;
	}

	public void setAtivo(byte ativo) {
		this.ativo = ativo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setPermissao(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setPermissao(null);

		return usuario;
	}

}