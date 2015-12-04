package ec.com.mariscalSucre.tesisMatriculacion.matriculacion.dao;

import org.springframework.stereotype.Repository;

import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity.Matricula;
import ec.com.mariscalSucre.tesisMatriculacion.utils.dao.GenericDaoImpl;

@Repository
public class MatriculaDaoImpl extends GenericDaoImpl<Matricula, Integer>implements MatriculaDao {

}