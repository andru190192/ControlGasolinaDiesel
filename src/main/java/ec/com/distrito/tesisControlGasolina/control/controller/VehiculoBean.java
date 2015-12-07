package ec.com.distrito.tesisControlGasolina.control.controller;

import static ec.com.distrito.tesisControlGasolina.utils.UtilsAplicacion.presentaMensaje;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ec.com.distrito.tesisControlGasolina.control.entity.Vehiculo;
import ec.com.distrito.tesisControlGasolina.control.service.VehiculoService;

@Controller
@Scope("session")
public class VehiculoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private VehiculoService vehiculoService;

	private List<Vehiculo> listaVehiculos;
	private String criterioBusquedaVehiculo;
	private Vehiculo vehiculo;

	public VehiculoBean() {
	}

	@PostConstruct
	public void init() {
		limpiarObjetos();
		listaVehiculos = new ArrayList<>();
	}

	public void limpiarObjetos() {
		vehiculo = new Vehiculo();
	}

	public void obtenerVehiculos() {
		listaVehiculos = new ArrayList<Vehiculo>();
		listaVehiculos = vehiculoService.obtenerTodosPorBusqueda(criterioBusquedaVehiculo.toUpperCase());
	}

	public void insertar(ActionEvent actionEvent) {
		vehiculoService.insertarActualizar(vehiculo);
	}

	public void actualizar(ActionEvent actionEvent) {
		vehiculoService.actualizar(vehiculo);
		listaVehiculos = new ArrayList<Vehiculo>();
	}

	public void eliminar(ActionEvent actionEvent) {
		vehiculoService.eliminar(vehiculo);
	}

	public void cargarInsertar() {
		limpiarObjetos();
	}

	public void cargarEditar() {
	}

	public void comprobarVehiculo() {
		String placa = vehiculo.getPlaca().trim();
		vehiculo = vehiculoService.obtenerPorPlaca(placa);
		if (vehiculo != null) {
			limpiarObjetos();
			presentaMensaje(FacesMessage.SEVERITY_ERROR, "EL VEHICULO YA EXISTE");
		} else {
			limpiarObjetos();
			vehiculo.setPlaca(placa);
		}
	}

	public List<Vehiculo> getListaVehiculos() {
		return listaVehiculos;
	}

	public void setListaVehiculos(List<Vehiculo> listaVehiculos) {
		this.listaVehiculos = listaVehiculos;
	}

	public String getCriterioBusquedaVehiculo() {
		return criterioBusquedaVehiculo;
	}

	public void setCriterioBusquedaVehiculo(String criterioBusquedaVehiculo) {
		this.criterioBusquedaVehiculo = criterioBusquedaVehiculo;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

}
