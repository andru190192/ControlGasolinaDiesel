package ec.com.mariscalSucre.tesisMatriculacion.matriculacion.controller;

import static ec.com.mariscalSucre.tesisMatriculacion.utils.UtilsAplicacion.presentaMensaje;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity.Ciudad;
import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity.Estudiante;
import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity.Persona;
import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity.Provincia;
import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.service.CiudadService;
import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.service.EstudianteService;
import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.service.PersonaService;

@Controller
@Scope("session")
public class EstudianteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private EstudianteService estudianteService;

	@Autowired
	private PersonaService personaService;

	@Autowired
	private CiudadService ciudadService;

	private List<Persona> listaPersonasEstudiantes;
	private String criterioBusquedaEstudiante;
	private Persona persona;
	private List<Ciudad> listaCiudades;

	public EstudianteBean() {
	}

	@PostConstruct
	public void init() {
		limpiarObjetos();
		listaPersonasEstudiantes = new ArrayList<>();
	}

	public void limpiarObjetos() {
		persona = new Persona();
		persona.setCiudad(new Ciudad());
		persona.setEstudiante(new Estudiante());
		persona.getEstudiante().setFolio(String.valueOf(estudianteService.contar() + 1));
	}

	public void cargarCiudades() {
		listaCiudades = new ArrayList<>();
		listaCiudades = ciudadService.obtenerPorProvincia(persona.getCiudad().getProvincia());
	}

	public void obtenerEstudiantes() {
		listaPersonasEstudiantes = new ArrayList<Persona>();
		listaPersonasEstudiantes = estudianteService.obtenerTodosPorBusqueda(criterioBusquedaEstudiante.toUpperCase());
	}

	public void insertar(ActionEvent actionEvent) {
		estudianteService.insertar(persona);
	}

	public void actualizar(ActionEvent actionEvent) {
		estudianteService.actualizar(persona);
		listaPersonasEstudiantes = new ArrayList<Persona>();
	}

	public void eliminar(ActionEvent actionEvent) {
		estudianteService.eliminar(persona);
	}

	public void cargarInsertar() {
		limpiarObjetos();
	}

	public void cargarEditar() {
		cargarCiudades();
	}

	public void comprobarPersona() {
		String cedula = persona.getCedula().trim();
		persona = personaService.obtenerPorCedula(cedula);
		if (persona != null && persona.getEstudiante() != null) {
			limpiarObjetos();
			presentaMensaje(FacesMessage.SEVERITY_ERROR, "EL ESTUDIANTE YA EXISTE");
		} else if (persona != null) {
			persona.setEstudiante(new Estudiante());
			persona.getEstudiante().setFolio(String.valueOf(estudianteService.contar() + 1));
			cargarCiudades();
		} else {
			limpiarObjetos();
			persona.setCedula(cedula);
		}
	}

	public List<Persona> getListaPersonasEstudiantes() {
		return listaPersonasEstudiantes;
	}

	public void setListaPersonasEstudiantes(List<Persona> listaPersonasEstudiantes) {
		this.listaPersonasEstudiantes = listaPersonasEstudiantes;
	}

	public String getCriterioBusquedaEstudiante() {
		return criterioBusquedaEstudiante;
	}

	public void setCriterioBusquedaEstudiante(String criterioBusquedaEstudiante) {
		this.criterioBusquedaEstudiante = criterioBusquedaEstudiante;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Ciudad> getListaCiudades() {
		return listaCiudades;
	}

	public void setListaCiudades(List<Ciudad> listaCiudades) {
		this.listaCiudades = listaCiudades;
	}

	public Provincia[] getListaProvincias() {
		return Provincia.values();
	}

}
