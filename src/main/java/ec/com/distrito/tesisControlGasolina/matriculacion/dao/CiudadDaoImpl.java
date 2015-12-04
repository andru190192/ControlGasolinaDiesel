package ec.com.mariscalSucre.tesisMatriculacion.matriculacion.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity.Ciudad;
import ec.com.mariscalSucre.tesisMatriculacion.utils.dao.GenericDaoImpl;

@Repository
public class CiudadDaoImpl extends GenericDaoImpl<Ciudad, Integer>implements CiudadDao, Serializable {
	private static final long serialVersionUID = 1L;

}