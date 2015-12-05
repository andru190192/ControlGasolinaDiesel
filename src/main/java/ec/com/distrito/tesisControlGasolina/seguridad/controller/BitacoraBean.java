package ec.com.distrito.tesisControlGasolina.seguridad.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ec.com.distrito.tesisControlGasolina.control.entity.Chofer;
import ec.com.distrito.tesisControlGasolina.control.service.ChoferService;
import ec.com.distrito.tesisControlGasolina.seguridad.entity.Bitacora;
import ec.com.distrito.tesisControlGasolina.seguridad.service.BitacoraService;

@Controller
@Scope("session")
public class BitacoraBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	BitacoraService bitacoraService;

	@Autowired
	ChoferService choferService;

	private Chofer persona;
	private String criterio;
	private Date fechaInicio;

	List<Bitacora> listaBitacora;
	List<Chofer> listaPersonas;

	public BitacoraBean() {
	}

	public void buscarUsuario() {
		listaPersonas = choferService.obtenerTodosPorBusqueda(criterio);
	}

	public void cargarUsuario(SelectEvent event) {
		persona = choferService.obtenerPorChoferId(persona.getId());
	}

	public void consultar() {
		listaBitacora = new ArrayList<Bitacora>();
		listaBitacora = bitacoraService.obtener(persona.getId(), fechaInicio);
	}

	public String getCriterio() {
		return criterio;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public List<Bitacora> getListaBitacora() {
		return listaBitacora;
	}

	public List<Chofer> getListaPersonas() {
		return listaPersonas;
	}

	public Chofer getPersona() {
		return persona;
	}

	@PostConstruct
	public void init() {
		persona = new Chofer();
		fechaInicio = new Date();
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public void setListaBitacora(List<Bitacora> listaBitacora) {
		this.listaBitacora = listaBitacora;
	}

	public void setListaPersonas(List<Chofer> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}

	public void setPersona(Chofer persona) {
		this.persona = persona;
	}

}