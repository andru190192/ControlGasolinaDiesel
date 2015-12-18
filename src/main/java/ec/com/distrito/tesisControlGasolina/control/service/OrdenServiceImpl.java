package ec.com.distrito.tesisControlGasolina.control.service;

import static ec.com.distrito.tesisControlGasolina.utils.UtilsAplicacion.presentaMensaje;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.distrito.tesisControlGasolina.control.dao.ControlGastoDao;
import ec.com.distrito.tesisControlGasolina.control.entity.ControlGasto;

@Service
public class OrdenServiceImpl implements OrdenService, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ControlGastoDao controlGastoDao;

	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = factory.getValidator();

	public List<ControlGasto> obtener() {
		List<ControlGasto> lista = controlGastoDao.obtenerPorHql("select cg from ControlGasto cg", new Object[] {});
		return lista;
	}

	public ControlGasto insertarActualizar(ControlGasto controlGasto) {
		Set<ConstraintViolation<ControlGasto>> violationsOrden = validator.validate(controlGasto);
		if (violationsOrden.size() > 0)
			for (ConstraintViolation<ControlGasto> cv : violationsOrden)
				presentaMensaje(FacesMessage.SEVERITY_INFO, cv.getMessage());
		else {
			boolean retorno = false;
			if (controlGasto.getId() == null)
				retorno = insertar(controlGasto);
			else
				retorno = actualizar(controlGasto);

			if (retorno) {
				presentaMensaje(FacesMessage.SEVERITY_INFO, "ORDEN DE CONTROL DE GASOLINA INSERTADA DE MANERA CORRECTA",
						"cerrar", true);
			}
		}
		return controlGasto;

	}

	public boolean insertar(ControlGasto controlGasto) {
		boolean retorno = false;
		if (controlGasto.getChofer().getId() == null || controlGasto.getChofer().getId() == 0)
			presentaMensaje(FacesMessage.SEVERITY_INFO, "ESCOJA UN CHOFER");
		if (controlGasto.getVehiculo().getId() == null || controlGasto.getVehiculo().getId() == 0)
			presentaMensaje(FacesMessage.SEVERITY_INFO, "ESCOJA UN VEHICULO");

		controlGasto.setActivo(true);
		controlGasto.setFecha(new Timestamp(new Date().getTime()));
		controlGastoDao.insertar(controlGasto);
		retorno = true;

		return retorno;
	}

	public boolean actualizar(ControlGasto controlGasto) {
		boolean retorno = false;

		if (controlGasto.getChofer().getId() == null || controlGasto.getChofer().getId() == 0)
			presentaMensaje(FacesMessage.SEVERITY_INFO, "ESCOJA UN CHOFER");
		if (controlGasto.getVehiculo().getId() == null || controlGasto.getVehiculo().getId() == 0)
			presentaMensaje(FacesMessage.SEVERITY_INFO, "ESCOJA UN VEHICULO");
		else {
			controlGasto.setActivo(true);
			controlGastoDao.actualizar(controlGasto);
			retorno = true;
		}
		return retorno;
	}

	public List<ControlGasto> obtenerPorBusqueda(int choferid, int vehiculoid) {
		List<ControlGasto> lista = null;
		if (choferid == 0 && vehiculoid == 0)
			presentaMensaje(FacesMessage.SEVERITY_ERROR, "INGRESE UN CRITERIO DE BUSQUEDA");
		else {
			if (vehiculoid != 0 && choferid != 0)
				lista = controlGastoDao.obtenerPorHql(
						"select distinct cg from ControlGasto cg " + "inner join cg.vehiculo ve "
								+ "inner join cg.chofer ch " + "where ve.id=?1 and ch.id=?2 " + "order by cg.fecha",
						new Object[] { vehiculoid, choferid });
			if (choferid != 0)
				lista = controlGastoDao.obtenerPorHql("select distinct cg from ControlGasto cg "
						+ "inner join cg.chofer ch " + "where (ch.id=?1 ) " + "order by cg.fecha",
						new Object[] { choferid });
			if (vehiculoid != 0)
				lista = controlGastoDao.obtenerPorHql("select distinct cg from ControlGasto cg "
						+ "inner join cg.vehiculo ve " + "where (ve.id=?1 ) " + "order by cg.fecha",
						new Object[] { vehiculoid });
			if (lista.isEmpty())
				presentaMensaje(FacesMessage.SEVERITY_INFO, "NO SE ENCONTRO NINGUNA COINCIDENCIA");
		}
		return lista;
	}

	public List<ControlGasto> obtenerPorBusquedaDeFechas(Date fechaInicio, Date fechaFin) {
		List<ControlGasto> lista = null;
		if (fechaInicio == null && fechaFin == null)
			presentaMensaje(FacesMessage.SEVERITY_ERROR, "INGRESE UN RANGO DE FECHAS");
		else {
			if (fechaInicio != null && fechaFin != null)
				lista = controlGastoDao.obtenerPorHql(
						"select distinct cg from ControlGasto cg " + "inner join cg.vehiculo ve "
								+ "inner join cg.chofer ch " + "where cg.fecha>=?1 and cg.fecha<=?2 "
								+ "order by cg.fecha",
						new Object[] { new Timestamp(fechaInicio.getTime()),
								new Timestamp(fechaFin.getTime() + 86399999) });
			if (lista.isEmpty())
				presentaMensaje(FacesMessage.SEVERITY_INFO, "NO SE ENCONTRO NINGUNA COINCIDENCIA");
		}
		return lista;
	}

}