package ec.com.mariscalSucre.tesisMatriculacion.matriculacion.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity.Persona;
import ec.com.mariscalSucre.tesisMatriculacion.utils.dao.GenericDaoImpl;

@Repository
public class PersonaDaoImpl extends GenericDaoImpl<Persona, Integer> implements
		PersonaDao, Serializable {

	private static final long serialVersionUID = 1L;
}