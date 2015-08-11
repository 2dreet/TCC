package entidade;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigoUsuario;

	private byte ativo;

	private String cpf;

	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	private String email;

	private String nome;

	private String rg;

	private String senha;

	private String sobreNome;

	private String telefone;

	private String usuario;

	//bi-directional many-to-one association to Funcionario
	@OneToMany(mappedBy="usuario")
	private List<Funcionario> funcionarios;

	//bi-directional many-to-one association to Jogador
	@OneToMany(mappedBy="usuario")
	private List<Jogador> jogadors;

	//bi-directional many-to-one association to Permissao
	@ManyToOne
	@JoinColumn(name="codigoPermissao")
	private Permissao permissao;

	public Usuario() {
	}

	public int getCodigoUsuario() {
		return this.codigoUsuario;
	}

	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public byte getAtivo() {
		return this.ativo;
	}

	public void setAtivo(byte ativo) {
		this.ativo = ativo;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return this.rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSobreNome() {
		return this.sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<Funcionario> getFuncionarios() {
		return this.funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Funcionario addFuncionario(Funcionario funcionario) {
		getFuncionarios().add(funcionario);
		funcionario.setUsuario(this);

		return funcionario;
	}

	public Funcionario removeFuncionario(Funcionario funcionario) {
		getFuncionarios().remove(funcionario);
		funcionario.setUsuario(null);

		return funcionario;
	}

	public List<Jogador> getJogadors() {
		return this.jogadors;
	}

	public void setJogadors(List<Jogador> jogadors) {
		this.jogadors = jogadors;
	}

	public Jogador addJogador(Jogador jogador) {
		getJogadors().add(jogador);
		jogador.setUsuario(this);

		return jogador;
	}

	public Jogador removeJogador(Jogador jogador) {
		getJogadors().remove(jogador);
		jogador.setUsuario(null);

		return jogador;
	}

	public Permissao getPermissao() {
		return this.permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

}