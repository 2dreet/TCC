package entidade;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the banimento database table.
 * 
 */
@Entity
@NamedQuery(name="Banimento.findAll", query="SELECT b FROM Banimento b")
public class Banimento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigoBanimento;

	private byte ativo;

	private String descricao;

	//bi-directional many-to-one association to Jogadorbanimento
	@OneToMany(mappedBy="banimento")
	private List<Jogadorbanimento> jogadorbanimentos;

	public Banimento() {
	}

	public int getCodigoBanimento() {
		return this.codigoBanimento;
	}

	public void setCodigoBanimento(int codigoBanimento) {
		this.codigoBanimento = codigoBanimento;
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

	public List<Jogadorbanimento> getJogadorbanimentos() {
		return this.jogadorbanimentos;
	}

	public void setJogadorbanimentos(List<Jogadorbanimento> jogadorbanimentos) {
		this.jogadorbanimentos = jogadorbanimentos;
	}

	public Jogadorbanimento addJogadorbanimento(Jogadorbanimento jogadorbanimento) {
		getJogadorbanimentos().add(jogadorbanimento);
		jogadorbanimento.setBanimento(this);

		return jogadorbanimento;
	}

	public Jogadorbanimento removeJogadorbanimento(Jogadorbanimento jogadorbanimento) {
		getJogadorbanimentos().remove(jogadorbanimento);
		jogadorbanimento.setBanimento(null);

		return jogadorbanimento;
	}

}