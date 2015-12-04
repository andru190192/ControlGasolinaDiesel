package ec.com.mariscalSucre.tesisMatriculacion.matriculacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.dao.ParametroDao;
import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity.Parametro;

@Service
public class ParametroServiceImpl implements ParametroService {

	@Autowired
	private ParametroDao parametroDao;

	public void actualizar(Parametro parametro) {
		parametroDao.actualizar(parametro);
	}

	public Parametro obtener() {
		return parametroDao.obtenerPorId(Parametro.class, 1);
	}

}
