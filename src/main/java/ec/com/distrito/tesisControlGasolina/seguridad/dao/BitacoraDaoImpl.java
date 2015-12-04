package ec.com.mariscalSucre.tesisMatriculacion.seguridad.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import ec.com.mariscalSucre.tesisMatriculacion.seguridad.entity.Bitacora;
import ec.com.mariscalSucre.tesisMatriculacion.utils.dao.GenericDaoImpl;

@Repository
public class BitacoraDaoImpl extends GenericDaoImpl<Bitacora, Integer>
		implements BitacoraDao, Serializable {

	private static final long serialVersionUID = 1L;

}
