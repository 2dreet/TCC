package entidade;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the marca database table.
 * 
 */
@Entity
@NamedQuery(name="Marca.findAll", query="SELECT m FROM Marca m")
public class Marca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigoMarca;

	private byte ativo;

	private String descricao;

	//bi-directional many-to-one association to Driver
	@OneToMany(mappedBy="marca")
	private List<Driver> drivers;

	//bi-directional many-to-one association to Marcaperiferico
	@OneToMany(mappedBy="marca")
	private List<Marcaperiferico> marcaperifericos;

	public Marca() {
	}

	public int getCodigoMarca() {
		return this.codigoMarca;
	}

	public void setCodigoMarca(int codigoMarca) {
		this.codigoMarca = codigoMarca;
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

	public List<Driver> getDrivers() {
		return this.drivers;
	}

	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}

	public Driver addDriver(Driver driver) {
		getDrivers().add(driver);
		driver.setMarca(this);

		return driver;
	}

	public Driver removeDriver(Driver driver) {
		getDrivers().remove(driver);
		driver.setMarca(null);

		return driver;
	}

	public List<Marcaperiferico> getMarcaperifericos() {
		return this.marcaperifericos;
	}

	public void setMarcaperifericos(List<Marcaperiferico> marcaperifericos) {
		this.marcaperifericos = marcaperifericos;
	}

	public Marcaperiferico addMarcaperiferico(Marcaperiferico marcaperiferico) {
		getMarcaperifericos().add(marcaperiferico);
		marcaperiferico.setMarca(this);

		return marcaperiferico;
	}

	public Marcaperiferico removeMarcaperiferico(Marcaperiferico marcaperiferico) {
		getMarcaperifericos().remove(marcaperiferico);
		marcaperiferico.setMarca(null);

		return marcaperiferico;
	}

}