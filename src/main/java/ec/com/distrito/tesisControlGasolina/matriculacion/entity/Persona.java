package ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import ec.com.mariscalSucre.tesisMatriculacion.seguridad.entity.Bitacora;
import ec.com.mariscalSucre.tesisMatriculacion.seguridad.entity.RolUsuario;

@Entity
@Table(name = "persona")
public class Persona implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Ciudad ciudad;
	private String cedula;
	private String apellido;
	private String nombre;
	private Date fechaNacimiento;
	private String direccion;
	private String password;
	private Boolean activo;
	private Estudiante estudiante;
	private Empleado empleado;
	private List<Bitacora> bitacoras;
	private List<RolUsuario> rolUsuarios;

	public Persona() {
	}

	public Persona(Integer id, String cedula, String apellido, String nombre, Ciudad ciudad, Date fechaNacimiento,
			String direccion, Boolean activo, Estudiante estudiante, Empleado empleado, String password,
			List<Bitacora> bitacoras, List<RolUsuario> rolUsuarios) {
		super();
		this.id = id;
		this.cedula = cedula;
		this.apellido = apellido;
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.activo = activo;
		this.estudiante = estudiante;
		this.empleado = empleado;
		this.password = password;
		this.bitacoras = bitacoras;
		this.rolUsuarios = rolUsuarios;
	}

	public Bitacora addBitacora(Bitacora bitacora) {
		getBitacoras().add(bitacora);
		bitacora.setPersona(this);

		return bitacora;
	}

	public RolUsuario addRolUsuario(RolUsuario rolUsuario) {
		getRolUsuarios().add(rolUsuario);
		rolUsuario.setPersona(this);

		return rolUsuario;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
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

	public Bitacora removeBitacora(Bitacora bitacora) {
		getBitacoras().remove(bitacora);
		bitacora.setPersona(null);

		return bitacora;
	}

	public RolUsuario removeRolUsuario(RolUsuario rolUsuario) {
		getRolUsuarios().remove(rolUsuario);
		rolUsuario.setPersona(null);

		return rolUsuario;
	}

	@Id
	@SequenceGenerator(allocationSize = 1, name = "PERSONA_PERSONAID_GENERATOR", sequenceName = "PERSONA_PERSONAID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSONA_PERSONAID_GENERATOR")
	@Column(name = "personaid", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	@ManyToOne
	@JoinColumn(name = "ciudadid", nullable = false)
	public Ciudad getCiudad() {
		return this.ciudad;
	}

	@Pattern(regexp = "[0-9]{10}+|[0-9]{13}+", message = "EL CAMPO CÉDULA ACEPTA DE 10 A 13 DÍGITOS NUMÉRICOS")
	@Column(nullable = false, length = 13)
	public String getCedula() {
		return this.cedula;
	}

	@Pattern(regexp = "[A-Za-z ñÑ]{3,500}", message = "EL CAMPO APELLIDO ACEPTA MINIMO 3 LETRAS ")
	@Column(nullable = false, length = 500)
	public String getApellido() {
		return this.apellido;
	}

	@Pattern(regexp = "[A-Za-z ñÑ]{3,500}", message = "EL CAMPO NOMBRE ACEPTA MINIMO 3 LETRAS ")
	@Column(nullable = false, length = 500)
	public String getNombre() {
		return this.nombre;
	}

	@Column(name = "fechanacimiento")
	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	@Length(min = 3, max = 5000, message = "LA DIRECCION ACEPTA MINIMO 3 LETRAS ")
	@Column(nullable = false, length = 5000)
	public String getDireccion() {
		return this.direccion;
	}

	@Column(nullable = false, length = 255)
	public String getPassword() {
		return this.password;
	}

	@Column(nullable = false)
	public Boolean getActivo() {
		return this.activo;
	}

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "persona")
	public Estudiante getEstudiante() {
		return this.estudiante;
	}

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "persona")
	public Empleado getEmpleado() {
		return this.empleado;
	}

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "persona")
	public List<Bitacora> getBitacoras() {
		return bitacoras;
	}

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "persona")
	public List<RolUsuario> getRolUsuarios() {
		return rolUsuarios;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setBitacoras(List<Bitacora> bitacoras) {
		this.bitacoras = bitacoras;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRolUsuarios(List<RolUsuario> rolUsuarios) {
		this.rolUsuarios = rolUsuarios;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

}