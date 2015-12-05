package ec.com.distrito.tesisControlGasolina.seguridad.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import ec.com.distrito.tesisControlGasolina.seguridad.entity.Bitacora;
import ec.com.distrito.tesisControlGasolina.utils.dao.GenericDaoImpl;

@Repository
public class BitacoraDaoImpl extends GenericDaoImpl<Bitacora, Integer>implements BitacoraDao, Serializable {

	private static final long serialVersionUID = 1L;

}
