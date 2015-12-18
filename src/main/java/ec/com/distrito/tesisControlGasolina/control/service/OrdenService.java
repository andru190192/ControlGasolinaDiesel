package ec.com.distrito.tesisControlGasolina.control.service;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ec.com.distrito.tesisControlGasolina.control.entity.ControlGasto;

public interface OrdenService {

	@Transactional()
	public List<ControlGasto> obtener();

	@Transactional
	public ControlGasto insertarActualizar(ControlGasto controlGasto);

	@Transactional
	public boolean insertar(ControlGasto controlGasto);

	@Transactional
	public boolean actualizar(ControlGasto controlGasto);

	@Transactional
	public List<ControlGasto> obtenerPorBusqueda(int choferid, int vehiculoid);

	@Transactional
	public List<ControlGasto> obtenerPorBusquedaDeFechas(Date fechaDesde, Date fechaFin);

}