package ec.com.distrito.tesisControlGasolina.control.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import ec.com.distrito.tesisControlGasolina.control.entity.Chofer;
import ec.com.distrito.tesisControlGasolina.utils.dao.GenericDaoImpl;

@Repository
public class ChoferDaoImpl extends GenericDaoImpl<Chofer, Integer>implements ChoferDao, Serializable {

	private static final long serialVersionUID = 1L;
}