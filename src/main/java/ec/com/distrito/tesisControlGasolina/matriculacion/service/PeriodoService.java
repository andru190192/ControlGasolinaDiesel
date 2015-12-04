package ec.com.mariscalSucre.tesisMatriculacion.matriculacion.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity.Periodo;

public interface PeriodoService {

	@Transactional
	public List<Periodo> obtenerPorId(Integer id);

	@Transactional
	public List<Periodo> obtenerTodos();

}
