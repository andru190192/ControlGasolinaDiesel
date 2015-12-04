package ec.com.mariscalSucre.tesisMatriculacion.matriculacion.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity.Estudiante;
import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity.Persona;

public interface EstudianteService {
	@Transactional
	public void actualizar(Persona persona);

	@Transactional
	public Long contar();

	@Transactional
	public void eliminar(Persona persona);

	@Transactional
	public Persona insertar(Persona persona);

	@Transactional(readOnly = true)
	public List<Persona> obtener();

	@Transactional
	public List<Persona> obtener(String criterioEstudianteBusqueda, Integer estudianteId);

	@Transactional(readOnly = true)
	public List<Persona> obtenerActivos();

	@Transactional
	public List<Persona> obtenerTodosPorBusqueda(String criterioEstudianteBusqueda);

	@Transactional
	public Estudiante obtenerEstudiantePorCedula(String cedula);

	@Transactional
	public Persona obtenerPorCedula(String cedula);

	@Transactional
	public Persona obtenerPorEstudianteId(Integer estudianteId);

	@Transactional
	public Persona obtenerPorPersonaId(Integer personaId);

	@Transactional
	public List<String> obtenerListaEstudiantesAutoComplete(String criterioEstudianteBusqueda);

	@Transactional
	public Estudiante cargarEstudiante(String estudiante);

}