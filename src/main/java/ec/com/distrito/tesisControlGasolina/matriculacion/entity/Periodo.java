package ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "periodo")
public class Periodo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(allocationSize = 1, name = "PERIODO_PERIODOID_GENERATOR", sequenceName = "PERIODO_PERIODOID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERIODO_PERIODOID_GENERATOR")
	@Column(name = "periodoid", unique = true, nullable = false)
	private Integer id;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private Boolean activo;

	public Periodo() {
	}

	public Periodo(Integer id, String nombre, Boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.activo = activo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

}