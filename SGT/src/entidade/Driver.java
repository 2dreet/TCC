package entidade;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the driver database table.
 * 
 */
@Entity
@NamedQuery(name="Driver.findAll", query="SELECT d FROM Driver d")
public class Driver implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigoDriver;

	private byte ativo;

	private String descricao;

	@Lob
	private String localArquivo;

	//bi-directional many-to-one association to Marca
	@ManyToOne
	@JoinColumn(name="codigoMarca")
	private Marca marca;

	public Driver() {
	}

	public int getCodigoDriver() {
		return this.codigoDriver;
	}

	public void setCodigoDriver(int codigoDriver) {
		this.codigoDriver = codigoDriver;
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

	public String getLocalArquivo() {
		return this.localArquivo;
	}

	public void setLocalArquivo(String localArquivo) {
		this.localArquivo = localArquivo;
	}

	public Marca getMarca() {
		return this.marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

}