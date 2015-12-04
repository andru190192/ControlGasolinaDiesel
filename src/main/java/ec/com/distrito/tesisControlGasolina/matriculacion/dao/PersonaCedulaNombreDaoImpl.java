package ec.com.mariscalSucre.tesisMatriculacion.matriculacion.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entityAux.PersonaCedulaNombre;
import ec.com.mariscalSucre.tesisMatriculacion.utils.dao.GenericDaoImpl;

@Repository
public class PersonaCedulaNombreDaoImpl extends GenericDaoImpl<PersonaCedulaNombre, Integer>
		implements PersonaCedulaNombreDao, Serializable {
	private static final long serialVersionUID = 1L;

}