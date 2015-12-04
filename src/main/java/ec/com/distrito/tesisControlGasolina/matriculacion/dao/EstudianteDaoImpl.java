package ec.com.mariscalSucre.tesisMatriculacion.matriculacion.dao;

import org.springframework.stereotype.Repository;

import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity.Estudiante;
import ec.com.mariscalSucre.tesisMatriculacion.utils.dao.GenericDaoImpl;

@Repository
public class EstudianteDaoImpl extends GenericDaoImpl<Estudiante, String>implements EstudianteDao {

}