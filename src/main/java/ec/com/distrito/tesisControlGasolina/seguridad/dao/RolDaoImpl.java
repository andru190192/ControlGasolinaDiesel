package ec.com.distrito.tesisControlGasolina.seguridad.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import ec.com.distrito.tesisControlGasolina.seguridad.entity.Rol;
import ec.com.distrito.tesisControlGasolina.utils.dao.GenericDaoImpl;

@Repository
public class RolDaoImpl extends GenericDaoImpl<Rol, Integer>implements RolDao, Serializable {

	private static final long serialVersionUID = 1L;

}
