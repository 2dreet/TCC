package entidade;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the jogadorbanimento database table.
 * 
 */
@Entity
@NamedQuery(name="Jogadorbanimento.findAll", query="SELECT j FROM Jogadorbanimento j")
public class Jogadorbanimento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigoJogBan;

	private byte ativo;

	private String descricao;

	//bi-directional many-to-one association to Banimento
	@ManyToOne
	@JoinColumn(name="codigoBanimento")
	private Banimento banimento;

	//bi-directional many-to-one association to Jogador
	@ManyToOne
	@JoinColumn(name="codigoJogador")
	private Jogador jogador;

	public Jogadorbanimento() {
	}

	public int getCodigoJogBan() {
		return this.codigoJogBan;
	}

	public void setCodigoJogBan(int codigoJogBan) {
		this.codigoJogBan = codigoJogBan;
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

	public Banimento getBanimento() {
		return this.banimento;
	}

	public void setBanimento(Banimento banimento) {
		this.banimento = banimento;
	}

	public Jogador getJogador() {
		return this.jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

}