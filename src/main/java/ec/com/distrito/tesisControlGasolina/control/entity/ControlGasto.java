package ec.com.distrito.tesisControlGasolina.control.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "controlgasto")
public class ControlGasto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Chofer chofer;
	private Vehiculo vehiculo;
	private String recorrido;
	private BigDecimal valor;
	private Timestamp fecha;
	private Boolean activo;

	public ControlGasto() {
	}

	public ControlGasto(Integer id, Chofer chofer, Vehiculo vehiculo, String recorrido, BigDecimal valor,
			Timestamp fecha, Boolean activo) {
		this.id = id;
		this.chofer = chofer;
		this.vehiculo = vehiculo;
		this.recorrido = recorrido;
		this.valor = valor;
		this.fecha = fecha;
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
		ControlGasto other = (ControlGasto) obj;
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
	@SequenceGenerator(allocationSize = 1, name = "CONTROLGASTO_CONTROLGASTOID_GENERATOR", sequenceName = "CONTROLGASTO_CONTROLGASTOID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTROLGASTO_CONTROLGASTOID_GENERATOR")
	@Column(name = "controlgastoid", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	@OneToOne
	@JoinColumn(name = "choferidid", nullable = false)
	public Chofer getChofer() {
		return this.chofer;
	}

	@OneToOne
	@JoinColumn(name = "vehiculoid", nullable = false)
	public Vehiculo getVehiculo() {
		return this.vehiculo;
	}

	@Pattern(regexp = "[A-Za-z ñÑ]{3,500P}", message = "EL CAMPO RECORRIDO ACEPTA MINIMO 3 LETRAS ")
	@Column(nullable = false, length = 5000)
	public String getRecorrido() {
		return this.recorrido;
	}

	@Column(nullable = false, precision = 12, scale = 6)
	public BigDecimal getValor() {
		return this.valor;
	}

	@Column(nullable = false)
	public Timestamp getFecha() {
		return fecha;
	}

	@Column(nullable = false)
	public Boolean getActivo() {
		return this.activo;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setChofer(Chofer chofer) {
		this.chofer = chofer;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public void setRecorrido(String recorrido) {
		this.recorrido = recorrido;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

}