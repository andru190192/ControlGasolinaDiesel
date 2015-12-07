package ec.com.distrito.tesisControlGasolina.control.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import ec.com.distrito.tesisControlGasolina.control.entity.ControlGasto;
import ec.com.distrito.tesisControlGasolina.utils.dao.GenericDaoImpl;

@Repository
public class ControlGastoDaoImpl extends GenericDaoImpl<ControlGasto, Integer>implements ControlGastoDao, Serializable {

	private static final long serialVersionUID = 1L;
}