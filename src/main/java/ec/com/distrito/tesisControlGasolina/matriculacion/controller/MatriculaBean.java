package ec.com.mariscalSucre.tesisMatriculacion.matriculacion.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity.Estudiante;
import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity.Matricula;
import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity.Periodo;
import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity.Persona;
import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entityAux.Grado;
import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.service.EstudianteService;
import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.service.MatriculaService;
import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.service.PeriodoService;
import ec.com.mariscalSucre.tesisMatriculacion.utils.service.ReporteService;

@Controller
@Scope("session")
public class MatriculaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private MatriculaService matriculaService;

	@Autowired
	private EstudianteService estudianteService;

	@Autowired
	private PeriodoService periodoService;

	@Autowired
	private ReporteService reporteService;

	private List<Matricula> listaMatriculas;

	private Matricula matricula;

	private String estudianteMatricula;

	private String estudiante;

	private List<Periodo> listaPeriodos;

	public MatriculaBean() {
	}

	@PostConstruct
	public void init() {
		limpiarObjetos();
		listaMatriculas = new ArrayList<>();
		listaMatriculas = matriculaService.obtener();
	}

	public void insertar(ActionEvent actionEvent) {
		matriculaService.insertarActualizar(matricula);
		listaMatriculas = matriculaService.obtener();
		estudianteMatricula = "";
	}

	public void cargarInsertar() {
		limpiarObjetos();
		listaPeriodos = new ArrayList<>();
		listaPeriodos = periodoService.obtenerTodos();
	}

	public void cargarEditar() {
		cargarPeriodos();
	}

	public void cargarPeriodos() {
		listaPeriodos = new ArrayList<>();
		listaPeriodos = periodoService.obtenerPorId(matricula.getPeriodo().getId());
	}

	public void limpiarObjetos() {
		matricula = new Matricula();
		matricula.setEstudiante(new Estudiante());
		matricula.setPeriodo(new Periodo());
	}

	public List<String> obtenerEstudianteMatriculaPorBusqueda(String criterioEstudianteBusqueda) {
		List<String> lista = estudianteService.obtenerListaEstudiantesAutoComplete(criterioEstudianteBusqueda);
		if (lista.size() == 1) {
			estudianteMatricula = (lista.get(0));
			cargarEstudianteMatricula();
		}
		return lista;
	}

	public void cargarEstudianteMatricula() {
		matricula.setEstudiante(estudianteService.cargarEstudiante(estudianteMatricula));
	}

	public void cargarEstudiante(Persona persona) {
		Persona p = estudianteService.obtenerPorPersonaId(persona.getId());
		matricula.setEstudiante(p.getEstudiante());

		estudiante = p.getEstudiante().getId().toString().concat("-").concat(p.getCedula()).concat("-")
				.concat(p.getApellido()).concat(" ").concat(p.getNombre());
	}

	public void imprimir() {
		List<Matricula> listaReporte = new ArrayList<Matricula>();
		listaReporte.add(matricula);
		reporteService.generarReportePDF(listaReporte, new HashMap<String, Object>(), "Matricula",
				"carnet" + matricula.getEstudiante().getPersona().getCedula());
	}

	public List<Matricula> getListaMatriculas() {
		return listaMatriculas;
	}

	public void setListaMatriculas(List<Matricula> listaMatriculas) {
		this.listaMatriculas = listaMatriculas;
	}

	public String getEstudianteMatricula() {
		return estudianteMatricula;
	}

	public void setEstudianteMatricula(String estudianteMatricula) {
		this.estudianteMatricula = estudianteMatricula;
	}

	public String getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(String estudiante) {
		this.estudiante = estudiante;
	}

	public Grado[] getListaGrados() {
		return Grado.values();
	}

	public List<Periodo> getListaPeriodos() {
		return listaPeriodos;
	}

	public void setListaPeriodos(List<Periodo> listaPeriodos) {
		this.listaPeriodos = listaPeriodos;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

}
