package ec.com.distrito.tesisControlGasolina.seguridad.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ec.com.distrito.tesisControlGasolina.seguridad.entity.Rol;

public interface RolService {
	@Transactional
	public List<Rol> obtener();

	@Transactional
	public Rol obtenerPorNombre(String nombre);

	@Transactional
	public Rol obtenerPorRolId(int rolId);
}