package entidade;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the jogador database table.
 * 
 */
@Entity
@NamedQuery(name="Jogador.findAll", query="SELECT j FROM Jogador j")
public class Jogador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigoJogador;

	@Temporal(TemporalType.DATE)
	private Date dataCadastro;

	//bi-directional many-to-one association to Time
	@ManyToOne
	@JoinColumn(name="codigoTime")
	private Time time;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="codigoUsuario")
	private Usuario usuario;

	//bi-directional many-to-one association to Jogadorbanimento
	@OneToMany(mappedBy="jogador")
	private List<Jogadorbanimento> jogadorbanimentos;

	//bi-directional many-to-one association to Jogadorperiferico
	@OneToMany(mappedBy="jogador")
	private List<Jogadorperiferico> jogadorperifericos;

	public Jogador() {
	}

	public int getCodigoJogador() {
		return this.codigoJogador;
	}

	public void setCodigoJogador(int codigoJogador) {
		this.codigoJogador = codigoJogador;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Time getTime() {
		return this.time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Jogadorbanimento> getJogadorbanimentos() {
		return this.jogadorbanimentos;
	}

	public void setJogadorbanimentos(List<Jogadorbanimento> jogadorbanimentos) {
		this.jogadorbanimentos = jogadorbanimentos;
	}

	public Jogadorbanimento addJogadorbanimento(Jogadorbanimento jogadorbanimento) {
		getJogadorbanimentos().add(jogadorbanimento);
		jogadorbanimento.setJogador(this);

		return jogadorbanimento;
	}

	public Jogadorbanimento removeJogadorbanimento(Jogadorbanimento jogadorbanimento) {
		getJogadorbanimentos().remove(jogadorbanimento);
		jogadorbanimento.setJogador(null);

		return jogadorbanimento;
	}

	public List<Jogadorperiferico> getJogadorperifericos() {
		return this.jogadorperifericos;
	}

	public void setJogadorperifericos(List<Jogadorperiferico> jogadorperifericos) {
		this.jogadorperifericos = jogadorperifericos;
	}

	public Jogadorperiferico addJogadorperiferico(Jogadorperiferico jogadorperiferico) {
		getJogadorperifericos().add(jogadorperiferico);
		jogadorperiferico.setJogador(this);

		return jogadorperiferico;
	}

	public Jogadorperiferico removeJogadorperiferico(Jogadorperiferico jogadorperiferico) {
		getJogadorperifericos().remove(jogadorperiferico);
		jogadorperiferico.setJogador(null);

		return jogadorperiferico;
	}

}