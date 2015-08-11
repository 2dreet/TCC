package entidade;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the time database table.
 * 
 */
@Entity
@NamedQuery(name="Time.findAll", query="SELECT t FROM Time t")
public class Time implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigoTime;

	private byte ativo;

	@Temporal(TemporalType.DATE)
	private Date dataCadastro;

	private String descricao;

	//bi-directional many-to-one association to Campeonatotime
	@OneToMany(mappedBy="time")
	private List<Campeonatotime> campeonatotimes;

	//bi-directional many-to-one association to Classificacao
	@OneToMany(mappedBy="time1")
	private List<Classificacao> classificacaos1;

	//bi-directional many-to-one association to Classificacao
	@OneToMany(mappedBy="time2")
	private List<Classificacao> classificacaos2;

	//bi-directional many-to-one association to Classificacao
	@OneToMany(mappedBy="time3")
	private List<Classificacao> classificacaos3;

	//bi-directional many-to-one association to Jogador
	@OneToMany(mappedBy="time")
	private List<Jogador> jogadors;

	//bi-directional many-to-one association to Timepartida
	@OneToMany(mappedBy="time")
	private List<Timepartida> timepartidas;

	public Time() {
	}

	public int getCodigoTime() {
		return this.codigoTime;
	}

	public void setCodigoTime(int codigoTime) {
		this.codigoTime = codigoTime;
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

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Campeonatotime> getCampeonatotimes() {
		return this.campeonatotimes;
	}

	public void setCampeonatotimes(List<Campeonatotime> campeonatotimes) {
		this.campeonatotimes = campeonatotimes;
	}

	public Campeonatotime addCampeonatotime(Campeonatotime campeonatotime) {
		getCampeonatotimes().add(campeonatotime);
		campeonatotime.setTime(this);

		return campeonatotime;
	}

	public Campeonatotime removeCampeonatotime(Campeonatotime campeonatotime) {
		getCampeonatotimes().remove(campeonatotime);
		campeonatotime.setTime(null);

		return campeonatotime;
	}

	public List<Classificacao> getClassificacaos1() {
		return this.classificacaos1;
	}

	public void setClassificacaos1(List<Classificacao> classificacaos1) {
		this.classificacaos1 = classificacaos1;
	}

	public Classificacao addClassificacaos1(Classificacao classificacaos1) {
		getClassificacaos1().add(classificacaos1);
		classificacaos1.setTime1(this);

		return classificacaos1;
	}

	public Classificacao removeClassificacaos1(Classificacao classificacaos1) {
		getClassificacaos1().remove(classificacaos1);
		classificacaos1.setTime1(null);

		return classificacaos1;
	}

	public List<Classificacao> getClassificacaos2() {
		return this.classificacaos2;
	}

	public void setClassificacaos2(List<Classificacao> classificacaos2) {
		this.classificacaos2 = classificacaos2;
	}

	public Classificacao addClassificacaos2(Classificacao classificacaos2) {
		getClassificacaos2().add(classificacaos2);
		classificacaos2.setTime2(this);

		return classificacaos2;
	}

	public Classificacao removeClassificacaos2(Classificacao classificacaos2) {
		getClassificacaos2().remove(classificacaos2);
		classificacaos2.setTime2(null);

		return classificacaos2;
	}

	public List<Classificacao> getClassificacaos3() {
		return this.classificacaos3;
	}

	public void setClassificacaos3(List<Classificacao> classificacaos3) {
		this.classificacaos3 = classificacaos3;
	}

	public Classificacao addClassificacaos3(Classificacao classificacaos3) {
		getClassificacaos3().add(classificacaos3);
		classificacaos3.setTime3(this);

		return classificacaos3;
	}

	public Classificacao removeClassificacaos3(Classificacao classificacaos3) {
		getClassificacaos3().remove(classificacaos3);
		classificacaos3.setTime3(null);

		return classificacaos3;
	}

	public List<Jogador> getJogadors() {
		return this.jogadors;
	}

	public void setJogadors(List<Jogador> jogadors) {
		this.jogadors = jogadors;
	}

	public Jogador addJogador(Jogador jogador) {
		getJogadors().add(jogador);
		jogador.setTime(this);

		return jogador;
	}

	public Jogador removeJogador(Jogador jogador) {
		getJogadors().remove(jogador);
		jogador.setTime(null);

		return jogador;
	}

	public List<Timepartida> getTimepartidas() {
		return this.timepartidas;
	}

	public void setTimepartidas(List<Timepartida> timepartidas) {
		this.timepartidas = timepartidas;
	}

	public Timepartida addTimepartida(Timepartida timepartida) {
		getTimepartidas().add(timepartida);
		timepartida.setTime(this);

		return timepartida;
	}

	public Timepartida removeTimepartida(Timepartida timepartida) {
		getTimepartidas().remove(timepartida);
		timepartida.setTime(null);

		return timepartida;
	}

}