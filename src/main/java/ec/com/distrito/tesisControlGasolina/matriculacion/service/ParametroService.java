package ec.com.mariscalSucre.tesisMatriculacion.matriculacion.service;

import org.springframework.transaction.annotation.Transactional;

import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity.Parametro;

public interface ParametroService {
	@Transactional
	public void actualizar(Parametro parametro);

	@Transactional
	public Parametro obtener();

}
