package ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "empleadocargo")
public class EmpleadoCargo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Boolean actual;
	private Date fechaCierre;
	private Date fechaInicio;
	private Cargo cargo;
	private Empleado empleado;

	public EmpleadoCargo() {
	}

	public EmpleadoCargo(Integer id, Boolean actual, Date fechaCierre, Date fechaInicio, Cargo cargo,
			Empleado empleado) {
		this.id = id;
		this.actual = actual;
		this.fechaCierre = fechaCierre;
		this.fechaInicio = fechaInicio;
		this.cargo = cargo;
		this.empleado = empleado;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpleadoCargo other = (EmpleadoCargo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Column(nullable = false)
	public Boolean getActual() {
		return this.actual;
	}

	@ManyToOne
	@JoinColumn(name = "cargoid", nullable = false)
	public Cargo getCargo() {
		return this.cargo;
	}

	@ManyToOne
	@JoinColumn(name = "empleadoid", nullable = false)
	public Empleado getEmpleado() {
		return this.empleado;
	}

	@Column(name = "fechacierre")
	@Temporal(TemporalType.DATE)
	public Date getFechaCierre() {
		return fechaCierre;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fechainicio", nullable = false)
	public Date getFechaInicio() {
		return fechaInicio;
	}

	@Id
	@SequenceGenerator(allocationSize = 1, name = "EMPLEADOCARGO_EMPLEADOCARGOID_GENERATOR", sequenceName = "EMPLEADOCARGO_EMPLEADOCARGOID_SEQ")
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLEADOCARGO_EMPLEADOCARGOID_GENERATOR")
	@Column(name = "empleadocargoid", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public void setActual(Boolean actual) {
		this.actual = actual;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
