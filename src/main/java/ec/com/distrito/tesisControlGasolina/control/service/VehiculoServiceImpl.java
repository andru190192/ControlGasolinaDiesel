package ec.com.distrito.tesisControlGasolina.control.service;

import static ec.com.distrito.tesisControlGasolina.utils.UtilsAplicacion.presentaMensaje;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.distrito.tesisControlGasolina.control.dao.VehiculoDao;
import ec.com.distrito.tesisControlGasolina.control.entity.Vehiculo;

@Service
public class VehiculoServiceImpl implements VehiculoService, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private VehiculoDao vehiculoDao;

	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = factory.getValidator();

	public boolean actualizar(Vehiculo vehiculo) {
		boolean retorno = false;
		Set<ConstraintViolation<Vehiculo>> violationsVehiculo = validator.validate(vehiculo);
		if (violationsVehiculo.size() > 0)
			for (ConstraintViolation<Vehiculo> cv : violationsVehiculo)
				presentaMensaje(FacesMessage.SEVERITY_INFO, cv.getMessage());
		else if (vehiculoDao.comprobarIndices(Vehiculo.class, "placa", vehiculo.getPlaca(),
				String.valueOf(vehiculo.getId())))
			presentaMensaje(FacesMessage.SEVERITY_INFO, "LA PLACA YA EXISTE", "cerrar", false);
		else {
			vehiculoDao.actualizar(vehiculo);
			presentaMensaje(FacesMessage.SEVERITY_INFO, "VEHICULO ACTUALIZADO DE MANERA CORRECTA", "cerrar", true);
			retorno = true;
		}
		return retorno;
	}

	public void eliminar(Vehiculo vehiculo) {
		vehiculo.setActivo(vehiculo.getActivo() ? false : true);
		vehiculoDao.actualizar(vehiculo);

		if (vehiculo.getActivo())
			presentaMensaje(FacesMessage.SEVERITY_INFO,
					"SE ACTIVO AL VEHICULO: " + vehiculo.getPlaca() + " " + vehiculo.getMarca());
		else
			presentaMensaje(FacesMessage.SEVERITY_INFO,
					"SE DESACTIVO AL VEHICULO: " + vehiculo.getPlaca() + " " + vehiculo.getMarca());
	}

	public Vehiculo insertarActualizar(Vehiculo vehiculo) {
		Set<ConstraintViolation<Vehiculo>> violationsVehiculo = validator.validate(vehiculo);
		if (violationsVehiculo.size() > 0)
			for (ConstraintViolation<Vehiculo> cv : violationsVehiculo)
				presentaMensaje(FacesMessage.SEVERITY_INFO, cv.getMessage());
		else {
			boolean retorno = false;
			if (vehiculo.getId() == null)
				retorno = insertar(vehiculo);
			else
				retorno = actualizar(vehiculo);

			if (retorno) {
				presentaMensaje(FacesMessage.SEVERITY_INFO, "VEHICULO INSERTADO CORRECTAMENTE", "cerrar", true);
			}
		}
		return vehiculo;

	}

	public boolean insertar(Vehiculo vehiculo) {
		boolean retorno = false;
		if (vehiculoDao.comprobarIndices(Vehiculo.class, "placa", vehiculo.getPlaca(),
				String.valueOf(vehiculo.getId())))
			presentaMensaje(FacesMessage.SEVERITY_INFO, "LA PLACA YA EXISTE", "cerrar", false);
		else {
			vehiculo.setActivo(true);
			vehiculoDao.insertar(vehiculo);
			retorno = true;
		}
		return retorno;
	}

	public List<Vehiculo> obtener(Boolean activo) {
		List<Vehiculo> lista = vehiculoDao.obtenerPorHql("select v from Vehiculo v order by v.placa, v.marca",
				new Object[] {});
		return lista;
	}

	public Vehiculo obtenerActivoPorPlaca(String placa) {
		List<Vehiculo> vehiculo = vehiculoDao
				.obtenerPorHql("select v from Vehiculo v where v.placa=?1 and v.activo=true", new Object[] { placa });
		if (vehiculo != null && vehiculo.size() == 1)
			return vehiculo.get(0);
		return null;
	}

	public Vehiculo obtenerPorPlaca(String placa) {
		List<Vehiculo> vehiculo = vehiculoDao
				.obtenerPorHql("select v from Vehiculo v where v.placa=?1 and v.activo=true", new Object[] { placa });
		if (vehiculo != null)
			if (vehiculo.size() != 0)
				return vehiculo.get(0);

		return null;
	}

	public Vehiculo obtenerPorVehiculoId(Integer vehiculoId) {
		Vehiculo vehiculo = vehiculoDao.obtenerPorHql("select v from Vehiculo v " + "where v.id=?1 and v.activo=true",
				new Object[] { vehiculoId }).get(0);
		return vehiculo;
	}

	public List<Vehiculo> obtenerTodosPorBusqueda(String criterioBusquedaVehiculo) {
		List<Vehiculo> lista = null;
		criterioBusquedaVehiculo = criterioBusquedaVehiculo.toUpperCase();

		if (criterioBusquedaVehiculo.compareToIgnoreCase("") == 0)
			presentaMensaje(FacesMessage.SEVERITY_ERROR, "INGRESE UN CRITERIO DE BUSQUEDA");
		else {
			if (criterioBusquedaVehiculo.length() >= 3) {
				if (criterioBusquedaVehiculo.compareToIgnoreCase("") != 0)
					lista = vehiculoDao.obtenerPorHql(
							"select distinct v from Vehiculo v "
									+ "where (v.placa like ?1 or v.marca like ?1 or v.modelo like ?1 or v.tipo like ?1 ) "
									+ "order by v.placa, v.marca",
							new Object[] { "%" + criterioBusquedaVehiculo + "%" });
				if (lista.isEmpty())
					presentaMensaje(FacesMessage.SEVERITY_INFO, "NO SE ENCONTRO NINGUNA COINCIDENCIA");
			} else
				presentaMensaje(FacesMessage.SEVERITY_ERROR, "INGRESE MINIMO 3 CARACTERES");
		}
		return lista;
	}

	public List<String> obtenerListaVehiculoAutoComplete(String criterioVehiculoBusqueda) {
		List<String> list = new ArrayList<String>();
		List<Vehiculo> lista = obtenerVehiculos(criterioVehiculoBusqueda);
		if (!lista.isEmpty())
			for (Vehiculo v : lista)
				list.add(v.getPlaca() + " - " + v.getMarca() + " " + v.getModelo());
		return list;
	}

	public List<Vehiculo> obtenerVehiculos(String criterioVehiculoBusqueda) {
		criterioVehiculoBusqueda = criterioVehiculoBusqueda.toUpperCase();
		List<Vehiculo> lista = null;
		if (criterioVehiculoBusqueda.length() >= 0 && criterioVehiculoBusqueda.length() < 3)
			presentaMensaje(FacesMessage.SEVERITY_ERROR, "INGRESE MAS DE 3 CARACTERES");
		else {
			lista = vehiculoDao.obtenerPorHql(
					"select distinct v from Vehiculo v " + "where "
							+ "(v.placa like ?1 or v.marca like ?1 or v.modelo like ?1 ) " + "and v.activo=true",
					new Object[] { "%" + criterioVehiculoBusqueda + "%" });
			if (lista.isEmpty())
				presentaMensaje(FacesMessage.SEVERITY_WARN, "NO SE ENCONTRO NINGUNA COINCIDENCIA");
		}
		return lista;
	}

	public Vehiculo cargarVehiculo(String vehiculo) {
		return obtenerPorPlaca(vehiculo.split(" - ")[0]);
	}

}
