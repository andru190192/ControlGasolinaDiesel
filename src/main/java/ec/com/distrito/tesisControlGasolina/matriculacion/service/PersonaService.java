package ec.com.mariscalSucre.tesisMatriculacion.matriculacion.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity.Persona;
import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entityAux.PersonaCedulaNombre;

public interface PersonaService {
	@Transactional
	public boolean actualizar(Persona persona);

	@Transactional
	public void cambiarClave(String claveActual, String clave1, String clave2);

	@Transactional
	public boolean compararClave(String clave1, String clave2);

	@Transactional
	public boolean comprobarRol(String cedula, String rol);

	@Transactional
	public Long contar();

	@Transactional
	public void eliminar(Persona persona);

	@Transactional
	public String generarClave(String clave);

	@Transactional
	public boolean insertar(Persona persona);

	public void insertarFilaAdicional(Persona persona);

	@Transactional
	public String insertarRoles(Persona persona, List<String> roles);

	@Transactional
	public List<Persona> obtener(Boolean activo);

	@Transactional
	public Persona obtenerActivoPorCedula(String cedula);

	@Transactional
	public PersonaCedulaNombre obtenerPersonaCedulaNombre(String cedula);

	@Transactional
	public Persona obtenerPorCedula(String cedula);

	@Transactional
	public Persona obtenerPorPersonaId(Integer personaId);

	@Transactional
	public List<Persona> obtenerTodosPorBusqueda(String criterioBusqueda, int criterioBusquedaCiudad);

	@Transactional
	public List<String> obtenerListaPersonaAutoComplete(String criterioPersonaBusqueda);

	@Transactional
	public List<Persona> obtenerPersonas(String criterioPersonaBusqueda);

	@Transactional
	public Persona cargarPersona(String persona);
}