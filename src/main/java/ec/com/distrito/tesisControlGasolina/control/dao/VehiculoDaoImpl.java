package ec.com.distrito.tesisControlGasolina.control.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import ec.com.distrito.tesisControlGasolina.control.entity.Vehiculo;
import ec.com.distrito.tesisControlGasolina.utils.dao.GenericDaoImpl;

@Repository
public class VehiculoDaoImpl extends GenericDaoImpl<Vehiculo, Integer>implements VehiculoDao, Serializable {

	private static final long serialVersionUID = 1L;
}