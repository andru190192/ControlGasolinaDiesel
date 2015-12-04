package ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "parametro")
public class Parametro implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String ruc;
	private String razonSocial;
	private String nombreComercial;
	private String direccion;
	private Integer tipoRedondeo;
	private Integer precisionDecimal;
	private String email;
	private TipoServidor tipoServidor;
	private String passEmail;

	public Parametro() {
	}

	public Parametro(Integer id, String ruc, String razonSocial, String nombreComercial, String direccion,
			Integer tipoRedondeo, Integer precisionDecimal, String email, TipoServidor tipoServidor, String passEmail) {
		this.id = id;
		this.ruc = ruc;
		this.razonSocial = razonSocial;
		this.nombreComercial = nombreComercial;
		this.direccion = direccion;
		this.tipoRedondeo = tipoRedondeo;
		this.precisionDecimal = precisionDecimal;
		this.email = email;
		this.tipoServidor = tipoServidor;
		this.passEmail = passEmail;
	}

	@Id
	@SequenceGenerator(allocationSize = 1, name = "PARAMETRO_PARAMETERID_GENERATOR", sequenceName = "PARAMETRO_PARAMETERID_SEQ")
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARAMETRO_PARAMETERID_GENERATOR")
	@Column(name = "parameterid", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	@Column(nullable = false, length = 13)
	public String getRuc() {
		return this.ruc;
	}

	@Column(name = "razonsocial", nullable = false, length = 50)
	public String getRazonSocial() {
		return razonSocial;
	}

	@Column(name = "nombrecomercial", nullable = false, length = 50)
	public String getNombreComercial() {
		return nombreComercial;
	}

	@Column(nullable = false, length = 100)
	public String getDireccion() {
		return this.direccion;
	}

	@Column(name = "tiporedondeo", nullable = false)
	public Integer getTipoRedondeo() {
		return tipoRedondeo;
	}

	@Column(name = "precisiondecimal", nullable = false)
	public Integer getPrecisionDecimal() {
		return precisionDecimal;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {
		return email;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "tiposervidoremail")
	public TipoServidor getTipoServidor() {
		return tipoServidor;
	}

	@Column(name = "passemail", length = 15)
	public String getPassEmail() {
		return passEmail;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public void setPassEmail(String passEmail) {
		this.passEmail = passEmail;
	}

	public void setPrecisionDecimal(Integer precisionDecimal) {
		this.precisionDecimal = precisionDecimal;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public void setTipoRedondeo(Integer tipoRedondeo) {
		this.tipoRedondeo = tipoRedondeo;
	}

	public void setTipoServidor(TipoServidor tipoServidor) {
		this.tipoServidor = tipoServidor;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parametro other = (Parametro) obj;
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

}