package ec.com.distrito.tesisControlGasolina.seguridad.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import ec.com.distrito.tesisControlGasolina.seguridad.entity.Menu;
import ec.com.distrito.tesisControlGasolina.utils.dao.GenericDaoImpl;

@Repository
public class MenuDaoImpl extends GenericDaoImpl<Menu, Integer> implements
		MenuDao, Serializable {

	private static final long serialVersionUID = 1L;

}
