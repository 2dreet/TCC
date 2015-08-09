package entidade;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the campeonato database table.
 * 
 */
@Entity
@NamedQuery(name="Campeonato.findAll", query="SELECT c FROM Campeonato c")
public class Campeonato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigoCampeonato;

	private byte ativo;

	@Temporal(TemporalType.DATE)
	private Date dataCadastro;

	@Temporal(TemporalType.DATE)
	private Date dataFim;

	@Temporal(TemporalType.DATE)
	private Date dataIncio;

	private String descricao;

	//bi-directional many-to-one association to Chave
	@ManyToOne
	@JoinColumn(name="codigoChave")
	private Chave chave;

	//bi-directional many-to-one association to Modalidade
	@ManyToOne
	@JoinColumn(name="codigoModalidade")
	private Modalidade modalidade;

	//bi-directional many-to-one association to Campeonatopartida
	@OneToMany(mappedBy="campeonato")
	private List<Campeonatopartida> campeonatopartidas;

	//bi-directional many-to-one association to Campeonatotime
	@OneToMany(mappedBy="campeonato")
	private List<Campeonatotime> campeonatotimes;

	//bi-directional many-to-one association to Classificacao
	@OneToMany(mappedBy="campeonato")
	private List<Classificacao> classificacaos;

	//bi-directional many-to-one association to Funcionariocampeonato
	@OneToMany(mappedBy="campeonato")
	private List<Funcionariocampeonato> funcionariocampeonatos;

	public Campeonato() {
	}

	public int getCodigoCampeonato() {
		return this.codigoCampeonato;
	}

	public void setCodigoCampeonato(int codigoCampeonato) {
		this.codigoCampeonato = codigoCampeonato;
	}

	public byte getAtivo() {
		return this.ativo;
	}

	public void setAtivo(byte ativo) {
		this.ativo = ativo;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataFim() {
		return this.dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getDataIncio() {
		return this.dataIncio;
	}

	public void setDataIncio(Date dataIncio) {
		this.dataIncio = dataIncio;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Chave getChave() {
		return this.chave;
	}

	public void setChave(Chave chave) {
		this.chave = chave;
	}

	public Modalidade getModalidade() {
		return this.modalidade;
	}

	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}

	public List<Campeonatopartida> getCampeonatopartidas() {
		return this.campeonatopartidas;
	}

	public void setCampeonatopartidas(List<Campeonatopartida> campeonatopartidas) {
		this.campeonatopartidas = campeonatopartidas;
	}

	public Campeonatopartida addCampeonatopartida(Campeonatopartida campeonatopartida) {
		getCampeonatopartidas().add(campeonatopartida);
		campeonatopartida.setCampeonato(this);

		return campeonatopartida;
	}

	public Campeonatopartida removeCampeonatopartida(Campeonatopartida campeonatopartida) {
		getCampeonatopartidas().remove(campeonatopartida);
		campeonatopartida.setCampeonato(null);

		return campeonatopartida;
	}

	public List<Campeonatotime> getCampeonatotimes() {
		return this.campeonatotimes;
	}

	public void setCampeonatotimes(List<Campeonatotime> campeonatotimes) {
		this.campeonatotimes = campeonatotimes;
	}

	public Campeonatotime addCampeonatotime(Campeonatotime campeonatotime) {
		getCampeonatotimes().add(campeonatotime);
		campeonatotime.setCampeonato(this);

		return campeonatotime;
	}

	public Campeonatotime removeCampeonatotime(Campeonatotime campeonatotime) {
		getCampeonatotimes().remove(campeonatotime);
		campeonatotime.setCampeonato(null);

		return campeonatotime;
	}

	public List<Classificacao> getClassificacaos() {
		return this.classificacaos;
	}

	public void setClassificacaos(List<Classificacao> classificacaos) {
		this.classificacaos = classificacaos;
	}

	public Classificacao addClassificacao(Classificacao classificacao) {
		getClassificacaos().add(classificacao);
		classificacao.setCampeonato(this);

		return classificacao;
	}

	public Classificacao removeClassificacao(Classificacao classificacao) {
		getClassificacaos().remove(classificacao);
		classificacao.setCampeonato(null);

		return classificacao;
	}

	public List<Funcionariocampeonato> getFuncionariocampeonatos() {
		return this.funcionariocampeonatos;
	}

	public void setFuncionariocampeonatos(List<Funcionariocampeonato> funcionariocampeonatos) {
		this.funcionariocampeonatos = funcionariocampeonatos;
	}

	public Funcionariocampeonato addFuncionariocampeonato(Funcionariocampeonato funcionariocampeonato) {
		getFuncionariocampeonatos().add(funcionariocampeonato);
		funcionariocampeonato.setCampeonato(this);

		return funcionariocampeonato;
	}

	public Funcionariocampeonato removeFuncionariocampeonato(Funcionariocampeonato funcionariocampeonato) {
		getFuncionariocampeonatos().remove(funcionariocampeonato);
		funcionariocampeonato.setCampeonato(null);

		return funcionariocampeonato;
	}

}