package ec.com.distrito.tesisControlGasolina.control.entity;

import java.io.Serializable;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import ec.com.distrito.tesisControlGasolina.seguridad.entity.Bitacora;
import ec.com.distrito.tesisControlGasolina.seguridad.entity.RolUsuario;

@Entity
@Table(name = "chofer")
public class Chofer implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String cedula;
	private String apellido;
	private String nombre;
	private String direccion;
	private String licencia;
	private String tipoSangre;
	private String telefono;
	private String email;
	private String password;
	private Boolean activo;
	private ControlGasto controlGasto;
	private List<Bitacora> bitacoras;
	private List<RolUsuario> rolUsuarios;

	public Chofer() {
	}

	public Chofer(Integer id, String cedula, String apellido, String nombre, String direccion, String licencia,
			String tipoSangre, String telefono, String email, String password, Boolean activo,
			ControlGasto controlGasto, List<Bitacora> bitacoras, List<RolUsuario> rolUsuarios) {
		this.id = id;
		this.cedula = cedula;
		this.apellido = apellido;
		this.nombre = nombre;
		this.direccion = direccion;
		this.licencia = licencia;
		this.tipoSangre = tipoSangre;
		this.telefono = telefono;
		this.email = email;
		this.password = password;
		this.activo = activo;
		this.controlGasto = controlGasto;
		this.bitacoras = bitacoras;
		this.rolUsuarios = rolUsuarios;
	}

	public Bitacora addBitacora(Bitacora bitacora) {
		getBitacoras().add(bitacora);
		bitacora.setChofer(this);

		return bitacora;
	}

	public RolUsuario addRolUsuario(RolUsuario rolUsuario) {
		getRolUsuarios().add(rolUsuario);
		rolUsuario.setChofer(this);

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
		Chofer other = (Chofer) obj;
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
		bitacora.setChofer(null);

		return bitacora;
	}

	public RolUsuario removeRolUsuario(RolUsuario rolUsuario) {
		getRolUsuarios().remove(rolUsuario);
		rolUsuario.setChofer(null);

		return rolUsuario;
	}

	@Id
	@SequenceGenerator(allocationSize = 1, name = "CHOFER_CHOFERID_GENERATOR", sequenceName = "CHOFER_CHOFERID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHOFER_CHOFERID_GENERATOR")
	@Column(name = "choferid", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	@ManyToOne
	@JoinColumn(name = "controlgastoid", nullable = false)
	public ControlGasto getControlGasto() {
		return this.controlGasto;
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

	@Length(min = 3, max = 5000, message = "LA DIRECCION ACEPTA MINIMO 3 LETRAS ")
	@Column(nullable = false, length = 5000)
	public String getDireccion() {
		return this.direccion;
	}

	public String getLicencia() {
		return licencia;
	}

	@Column(name = "tiposangre")
	public String getTipoSangre() {
		return tipoSangre;
	}

	public String getTelefono() {
		return telefono;
	}

	@Email(message = "INGRESE UN EMAIL VALIDO")
	public String getEmail() {
		return email;
	}

	@Column(nullable = false, length = 255)
	public String getPassword() {
		return this.password;
	}

	@Column(nullable = false)
	public Boolean getActivo() {
		return this.activo;
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

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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

	public void setControlGasto(ControlGasto controlGasto) {
		this.controlGasto = controlGasto;
	}

	public void setLicencia(String licencia) {
		this.licencia = licencia;
	}

	public void setTipoSangre(String tipoSangre) {
		this.tipoSangre = tipoSangre;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}