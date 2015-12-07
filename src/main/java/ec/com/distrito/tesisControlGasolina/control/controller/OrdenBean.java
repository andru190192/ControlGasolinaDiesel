package ec.com.distrito.tesisControlGasolina.control.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ec.com.distrito.tesisControlGasolina.control.entity.Chofer;
import ec.com.distrito.tesisControlGasolina.control.entity.ControlGasto;
import ec.com.distrito.tesisControlGasolina.control.entity.Vehiculo;
import ec.com.distrito.tesisControlGasolina.control.service.ChoferService;
import ec.com.distrito.tesisControlGasolina.control.service.OrdenService;
import ec.com.distrito.tesisControlGasolina.control.service.VehiculoService;
import ec.com.distrito.tesisControlGasolina.utils.service.ReporteService;

@Controller
@Scope("session")
public class OrdenBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private OrdenService ordenService;

	@Autowired
	private ChoferService choferService;

	@Autowired
	private VehiculoService vehiculoService;

	@Autowired
	private ReporteService reporteService;

	private List<ControlGasto> listaOrdenes;
	private List<Chofer> choferes;
	private List<Vehiculo> vehiculos;

	private ControlGasto orden;
	private Chofer chofer;
	private Vehiculo vehiculo;

	private String choferOrden;
	private String vehiculoOrden;

	private String choferString;
	private String vehiculoString;

	public OrdenBean() {
	}

	@PostConstruct
	public void init() {
		limpiarObjetos();
		listaOrdenes = new ArrayList<>();
		listaOrdenes = ordenService.obtener();
		choferes = new ArrayList<>();
		choferes = choferService.obtener(true);
		vehiculos = new ArrayList<>();
		vehiculos = vehiculoService.obtener(true);
	}

	public void obtenerOrdenes() {
		listaOrdenes = new ArrayList<ControlGasto>();
		chofer.setId(chofer.getId() == null ? 0 : chofer.getId());
		vehiculo.setId(vehiculo.getId() == null ? 0 : vehiculo.getId());
		listaOrdenes = ordenService.obtenerPorBusqueda(chofer.getId(), vehiculo.getId());
	}

	public void insertar(ActionEvent actionEvent) {
		ordenService.insertarActualizar(orden);
		listaOrdenes = ordenService.obtener();
		choferOrden = "";
		vehiculoOrden = "";
	}

	public void cargarInsertar() {
		limpiarObjetos();
	}

	public void cargarEditar() {
	}

	public void limpiarObjetos() {
		orden = new ControlGasto();
		orden.setChofer(new Chofer());
		orden.setVehiculo(new Vehiculo());
		orden.setRecorrido("");
		orden.setValor(new BigDecimal("0.00"));
		chofer = new Chofer();
		vehiculo = new Vehiculo();
	}

	public List<String> obtenerChoferOrdenPorBusqueda(String criterioChoferBusqueda) {
		List<String> lista = choferService.obtenerListaChoferAutoComplete(criterioChoferBusqueda);
		if (lista.size() == 1) {
			choferOrden = (lista.get(0));
			cargarChoferOrden();
		}
		return lista;
	}

	public List<String> obtenerVehiculoOrdenPorBusqueda(String criterioVehiculoBusqueda) {
		List<String> lista = vehiculoService.obtenerListaVehiculoAutoComplete(criterioVehiculoBusqueda);
		if (lista.size() == 1) {
			vehiculoOrden = (lista.get(0));
			cargarVehiculoOrden();
		}
		return lista;
	}

	public void cargarChoferOrden() {
		orden.setChofer(choferService.cargarChofer(choferOrden));
	}

	public void cargarVehiculoOrden() {
		orden.setVehiculo(vehiculoService.cargarVehiculo(vehiculoOrden));
		System.out.println(orden.getVehiculo().getPlaca());
	}

	public void cargarChofer(Chofer chofer) {
		Chofer c = choferService.obtenerPorChoferId(chofer.getId());
		orden.setChofer(c);

		this.choferString = c.getId().toString().concat("-").concat(c.getCedula()).concat("-").concat(c.getApellido())
				.concat(" ").concat(c.getNombre());
	}

	public void cargarVehiculo(Vehiculo vehiculo) {
		Vehiculo v = vehiculoService.obtenerPorVehiculoId(vehiculo.getId());
		orden.setVehiculo(v);

		this.vehiculoString = v.getId().toString().concat("-").concat(v.getPlaca()).concat("-").concat(v.getMarca())
				.concat(" ").concat(v.getModelo());
	}

	public void imprimir() {
		List<ControlGasto> listaReporte = new ArrayList<ControlGasto>();
		listaReporte.add(orden);
		reporteService.generarReportePDF(listaReporte, new HashMap<String, Object>(), "Orden", "orden"
				+ orden.getChofer().getCedula() + orden.getChofer().getApellido() + orden.getChofer().getNombre());
	}

	public OrdenService getOrdenService() {
		return ordenService;
	}

	public void setOrdenService(OrdenService ordenService) {
		this.ordenService = ordenService;
	}

	public ChoferService getChoferService() {
		return choferService;
	}

	public void setChoferService(ChoferService choferService) {
		this.choferService = choferService;
	}

	public VehiculoService getVehiculoService() {
		return vehiculoService;
	}

	public void setVehiculoService(VehiculoService vehiculoService) {
		this.vehiculoService = vehiculoService;
	}

	public ReporteService getReporteService() {
		return reporteService;
	}

	public void setReporteService(ReporteService reporteService) {
		this.reporteService = reporteService;
	}

	public List<ControlGasto> getListaOrdenes() {
		return listaOrdenes;
	}

	public void setListaOrdenes(List<ControlGasto> listaOrdenes) {
		this.listaOrdenes = listaOrdenes;
	}

	public ControlGasto getOrden() {
		return orden;
	}

	public void setOrden(ControlGasto orden) {
		this.orden = orden;
	}

	public String getChoferOrden() {
		return choferOrden;
	}

	public void setChoferOrden(String choferOrden) {
		this.choferOrden = choferOrden;
	}

	public String getVehiculoOrden() {
		return vehiculoOrden;
	}

	public void setVehiculoOrden(String vehiculoOrden) {
		this.vehiculoOrden = vehiculoOrden;
	}

	public String getChoferString() {
		return choferString;
	}

	public void setChoferString(String choferString) {
		this.choferString = choferString;
	}

	public String getVehiculoString() {
		return vehiculoString;
	}

	public void setVehiculoString(String vehiculoString) {
		this.vehiculoString = vehiculoString;
	}

	public List<Chofer> getChoferes() {
		return choferes;
	}

	public void setChoferes(List<Chofer> choferes) {
		this.choferes = choferes;
	}

	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public Chofer getChofer() {
		return chofer;
	}

	public void setChofer(Chofer chofer) {
		this.chofer = chofer;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

}
