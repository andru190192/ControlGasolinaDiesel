package ec.com.distrito.tesisControlGasolina.control.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ec.com.distrito.tesisControlGasolina.control.entity.Chofer;

public interface ChoferService {
	@Transactional
	public boolean actualizar(Chofer chofer);

	@Transactional
	public void cambiarClave(String claveActual, String clave1, String clave2);

	@Transactional
	public boolean compararClave(String clave1, String clave2);

	@Transactional
	public boolean comprobarRol(String cedula, String rol);

	@Transactional
	public Long contar();

	@Transactional
	public void eliminar(Chofer chofer);

	@Transactional
	public String generarClave(String clave);

	@Transactional
	public boolean insertar(Chofer chofer);

	@Transactional
	public String insertarRoles(Chofer chofer, List<String> roles);

	@Transactional
	public List<Chofer> obtener(Boolean activo);

	@Transactional
	public Chofer obtenerActivoPorCedula(String cedula);

	@Transactional
	public Chofer obtenerPorCedula(String cedula);

	@Transactional
	public Chofer obtenerPorChoferId(Integer choferId);

	@Transactional
	public List<Chofer> obtenerTodosPorBusqueda(String criterioBusqueda);

	@Transactional
	public List<String> obtenerListaChoferAutoComplete(String criterioChoferBusqueda);

	@Transactional
	public List<Chofer> obtenerChoferes(String criterioChoferBusqueda);

	@Transactional
	public Chofer cargarChofer(String chofer);
}