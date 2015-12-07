package ec.com.distrito.tesisControlGasolina.control.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ec.com.distrito.tesisControlGasolina.control.entity.Vehiculo;

public interface VehiculoService {
	@Transactional
	public boolean actualizar(Vehiculo vehiculo);

	@Transactional
	public void eliminar(Vehiculo vehiculo);

	@Transactional
	public boolean insertar(Vehiculo vehiculo);

	@Transactional
	public Vehiculo insertarActualizar(Vehiculo vehiculo);

	@Transactional
	public List<Vehiculo> obtener(Boolean activo);

	@Transactional
	public Vehiculo obtenerPorPlaca(String placa);

	@Transactional
	public Vehiculo obtenerPorVehiculoId(Integer vehiculoId);

	@Transactional
	public List<Vehiculo> obtenerTodosPorBusqueda(String criterioBusqueda);

	@Transactional
	public List<String> obtenerListaVehiculoAutoComplete(String criterioVehiculoBusqueda);

	@Transactional
	public List<Vehiculo> obtenerVehiculos(String criterioVehiculoBusqueda);

	@Transactional
	public Vehiculo cargarVehiculo(String vehiculo);
}