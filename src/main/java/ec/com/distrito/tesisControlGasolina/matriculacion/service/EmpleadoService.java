package ec.com.mariscalSucre.tesisMatriculacion.matriculacion.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity.EmpleadoCargo;
import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity.Persona;
import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entityAux.PersonaCedulaNombre;

public interface EmpleadoService {
	@Transactional
	public void actualizar(Persona persona);

	@Transactional
	public boolean autorizacion(String login, String pass);

	public List<EmpleadoCargo> cargarCargos(List<EmpleadoCargo> listaEmpleadoCargos, Persona persona);

	@Transactional
	public Long contar();

	@Transactional
	public void eliminar(Persona persona);

	@Transactional
	public void insertar(Persona persona);

	@Transactional
	public String insertarCargos(Persona persona);

	@Transactional(readOnly = true)
	public List<Persona> obtener();

	@Transactional
	public List<Persona> obtener(String nombreEmpleado);

	@Transactional(readOnly = true)
	public List<Persona> obtenerActivos();

	@Transactional
	public EmpleadoCargo obtenerCualquierEmpleadoCargoPorCedulaAndCargo(String cedula, Integer cargoId);

	@Transactional
	public EmpleadoCargo obtenerEmpleadoCargoPorCedulaAndCargo(String cedula, Integer cargoId);

	@Transactional
	public List<PersonaCedulaNombre> obtenerPorCargo(int cargo);

	@Transactional
	public Persona obtenerPorCedula(String cedula);

	@Transactional
	public EmpleadoCargo obtenerPorEmpleadoCargoId(int empladoCargoId);

	@Transactional
	public List<Persona> obtenerTodosPorBusqueda(int criterioBusquedaCargo, String criterioBusqueda,
			int criterioBusquedaCiudad);

}