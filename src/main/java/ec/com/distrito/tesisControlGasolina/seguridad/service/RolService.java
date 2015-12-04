package ec.com.mariscalSucre.tesisMatriculacion.seguridad.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ec.com.mariscalSucre.tesisMatriculacion.seguridad.entity.Rol;

public interface RolService {
	@Transactional
	public List<Rol> obtener();

	@Transactional
	public Rol obtenerPorNombre(String nombre);

	@Transactional
	public Rol obtenerPorRolId(int rolId);
}