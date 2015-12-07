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

import ec.com.distrito.tesisControlGasolina.control.entity.Chofer;
import ec.com.distrito.tesisControlGasolina.control.service.ChoferService;

@Controller
@Scope("session")
public class ChoferBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ChoferService choferService;

	private List<Chofer> listaChoferes;
	private String criterioBusquedaChofer;
	private Chofer chofer;

	public ChoferBean() {
	}

	@PostConstruct
	public void init() {
		limpiarObjetos();
		listaChoferes = new ArrayList<>();
	}

	public void limpiarObjetos() {
		chofer = new Chofer();
	}

	public void obtenerChoferes() {
		listaChoferes = new ArrayList<Chofer>();
		listaChoferes = choferService.obtenerTodosPorBusqueda(criterioBusquedaChofer.toUpperCase());
	}

	public void insertar(ActionEvent actionEvent) {
		choferService.insertarActualizar(chofer);
	}

	public void actualizar(ActionEvent actionEvent) {
		choferService.actualizar(chofer);
		listaChoferes = new ArrayList<Chofer>();
	}

	public void eliminar(ActionEvent actionEvent) {
		choferService.eliminar(chofer);
	}

	public void cargarInsertar() {
		limpiarObjetos();
	}

	public void cargarEditar() {
	}

	public void comprobarChofer() {
		String cedula = chofer.getCedula().trim();
		chofer = choferService.obtenerPorCedula(cedula);
		if (chofer != null) {
			limpiarObjetos();
			presentaMensaje(FacesMessage.SEVERITY_ERROR, "EL CHOFER YA EXISTE");
		} else {
			limpiarObjetos();
			chofer.setCedula(cedula);
		}
	}

	public List<Chofer> getListaChoferes() {
		return listaChoferes;
	}

	public void setListaChoferes(List<Chofer> listaChoferes) {
		this.listaChoferes = listaChoferes;
	}

	public String getCriterioBusquedaChofer() {
		return criterioBusquedaChofer;
	}

	public void setCriterioBusquedaChofer(String criterioBusquedaChofer) {
		this.criterioBusquedaChofer = criterioBusquedaChofer;
	}

	public Chofer getChofer() {
		return chofer;
	}

	public void setChofer(Chofer chofer) {
		this.chofer = chofer;
	}

}
