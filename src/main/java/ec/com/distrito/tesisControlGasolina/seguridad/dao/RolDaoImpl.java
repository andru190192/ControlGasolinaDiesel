package ec.com.mariscalSucre.tesisMatriculacion.seguridad.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import ec.com.mariscalSucre.tesisMatriculacion.seguridad.entity.Rol;
import ec.com.mariscalSucre.tesisMatriculacion.utils.dao.GenericDaoImpl;

@Repository
public class RolDaoImpl extends GenericDaoImpl<Rol, Integer> implements RolDao,
		Serializable {

	private static final long serialVersionUID = 1L;

}
