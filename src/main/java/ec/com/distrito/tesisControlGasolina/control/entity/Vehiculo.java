package ec.com.distrito.tesisControlGasolina.control.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "vehiculo")
public class Vehiculo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String placa;
	private String color;
	private String marca;
	private String modelo;
	private String tipo;
	private Boolean activo;

	public Vehiculo() {
	}

	public Vehiculo(Integer id, String placa, String color, String marca, String modelo, String tipo, Boolean activo) {
		this.id = id;
		this.placa = placa;
		this.color = color;
		this.marca = marca;
		this.modelo = modelo;
		this.tipo = tipo;
		this.activo = activo;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehiculo other = (Vehiculo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Id
	@SequenceGenerator(allocationSize = 1, name = "VEHICULO_VEHICULOID_GENERATOR", sequenceName = "VEHICULO_VEHICULOID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VEHICULO_VEHICULOID_GENERATOR")
	@Column(name = "vehiculoid", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	@Pattern(regexp = "[A-Za-z ñÑ]{3,5000}", message = "EL CAMPO PLACA ACEPTA MINIMO 3 LETRAS ")
	@Column(nullable = false, length = 5000)
	public String getPlaca() {
		return this.placa;
	}

	@Pattern(regexp = "[A-Za-z ñÑ]{3,5000}", message = "EL CAMPO COLOR ACEPTA MINIMO 3 LETRAS ")
	@Column(nullable = false, length = 5000)
	public String getColor() {
		return this.color;
	}

	@Pattern(regexp = "[A-Za-z ñÑ]{3,5000}", message = "EL CAMPO MARCA ACEPTA MINIMO 3 LETRAS ")
	@Column(nullable = false, length = 5000)
	public String getMarca() {
		return this.marca;
	}

	@Pattern(regexp = "[A-Za-z ñÑ]{3,5000}", message = "EL CAMPO MODELO ACEPTA MINIMO 3 LETRAS ")
	@Column(nullable = false, length = 5000)
	public String getModelo() {
		return this.modelo;
	}

	@Pattern(regexp = "[A-Za-z ñÑ]{3,500}", message = "EL CAMPO TIPO ACEPTA MINIMO 3 LETRAS ")
	@Column(nullable = false, length = 5000)
	public String getTipo() {
		return this.tipo;
	}

	@Column(nullable = false)
	public Boolean getActivo() {
		return this.activo;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

}