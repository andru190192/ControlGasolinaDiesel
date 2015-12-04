package ec.com.mariscalSucre.tesisMatriculacion.matriculacion.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity.Empleado;
import ec.com.mariscalSucre.tesisMatriculacion.utils.dao.GenericDaoImpl;

@Repository
public class EmpleadoDaoImpl extends GenericDaoImpl<Empleado, Integer>implements EmpleadoDao, Serializable {

	private static final long serialVersionUID = 1L;
}