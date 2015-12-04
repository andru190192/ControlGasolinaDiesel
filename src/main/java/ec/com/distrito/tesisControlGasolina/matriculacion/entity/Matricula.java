package ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entityAux.Grado;

@Entity
@Table(name = "matricula")
public class Matricula implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(allocationSize = 1, name = "MATRICULA_MATRICULAID_GENERATOR", sequenceName = "MATRICULA_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MATRICULA_MATRICULAID_GENERATOR")
	private Integer id;

	@Column(name = "matriculaid", nullable = false)
	private Integer matricula;

	@OneToOne
	@JoinColumn(name = "periodoid", nullable = false)
	private Periodo periodo;

	@OneToOne
	@JoinColumn(name = "estudianteid", nullable = false)
	private Estudiante estudiante;

	@Enumerated(EnumType.STRING)
	@Column(name = "gradoid", nullable = false)
	private Grado grado;

	private Timestamp fecha;

	private Boolean activo;

	public Matricula(Integer id, Integer matricula, Periodo periodo, Estudiante estudiante, Grado grado,
			Timestamp fecha, Boolean activo) {
		this.id = id;
		this.matricula = matricula;
		this.periodo = periodo;
		this.estudiante = estudiante;
		this.grado = grado;
		this.fecha = fecha;
		this.setActivo(activo);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Grado getGrado() {
		return grado;
	}

	public void setGrado(Grado grado) {
		this.grado = grado;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public Matricula() {
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

}