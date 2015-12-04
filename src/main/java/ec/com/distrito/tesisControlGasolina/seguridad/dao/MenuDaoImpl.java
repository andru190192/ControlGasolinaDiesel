package ec.com.mariscalSucre.tesisMatriculacion.seguridad.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import ec.com.mariscalSucre.tesisMatriculacion.seguridad.entity.Menu;
import ec.com.mariscalSucre.tesisMatriculacion.utils.dao.GenericDaoImpl;

@Repository
public class MenuDaoImpl extends GenericDaoImpl<Menu, Integer> implements
		MenuDao, Serializable {

	private static final long serialVersionUID = 1L;

}
