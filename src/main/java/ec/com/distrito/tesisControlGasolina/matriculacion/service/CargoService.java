package ec.com.mariscalSucre.tesisMatriculacion.matriculacion.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity.Cargo;

public interface CargoService {
	@Transactional
	public String actualizar(Cargo cargo);

	@Transactional
	public void eliminar(Cargo cargo);

	@Transactional
	public String insertar(Cargo cargo);

	@Transactional
	public List<Cargo> obtener(Boolean activo);

	@Transactional
	public Cargo obtenerPorCargoId(int cargoId);
}