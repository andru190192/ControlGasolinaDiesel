package ec.com.mariscalSucre.tesisMatriculacion.matriculacion.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity.Cargo;
import ec.com.mariscalSucre.tesisMatriculacion.utils.dao.GenericDaoImpl;

@Repository
public class CargoDaoImpl extends GenericDaoImpl<Cargo, Integer>implements CargoDao, Serializable {
	private static final long serialVersionUID = 1L;

}