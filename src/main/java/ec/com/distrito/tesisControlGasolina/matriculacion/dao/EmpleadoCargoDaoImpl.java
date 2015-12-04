package ec.com.mariscalSucre.tesisMatriculacion.matriculacion.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity.EmpleadoCargo;
import ec.com.mariscalSucre.tesisMatriculacion.utils.dao.GenericDaoImpl;

@Repository
public class EmpleadoCargoDaoImpl extends GenericDaoImpl<EmpleadoCargo, Integer>
		implements EmpleadoCargoDao, Serializable {

	private static final long serialVersionUID = 1L;

}