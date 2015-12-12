package ec.com.distrito.tesisControlGasolina.seguridad.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.distrito.tesisControlGasolina.seguridad.dao.BitacoraDao;
import ec.com.distrito.tesisControlGasolina.seguridad.entity.Bitacora;

@Service
public class BitacoraServiceImpl implements BitacoraService, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private BitacoraDao bitacoraDao;

	public List<Bitacora> obtener(Integer choferId, Date fechaInicio) {
		List<Bitacora> lista = null;

		if (choferId == null || choferId == 0)
			lista = bitacoraDao.obtenerPorHql(
					"select b from Bitacora b " + "inner join b.chofer c " + "where b.fecha>=?1 and b.fecha<=?2 "
							+ "order by b.fecha desc",
					new Object[] { fechaInicio, new Date(fechaInicio.getTime() + 86399999) });
		else
			lista = bitacoraDao.obtenerPorHql(
					"select b from Bitacora b " + "inner join b.chofer c "
							+ "where c.id=?1 and b.fecha>=?2 and b.fecha<=?3 " + "order by b.fecha desc",
					new Object[] { choferId, fechaInicio, new Date(fechaInicio.getTime() + 86399999) });

		return lista;
	}
}