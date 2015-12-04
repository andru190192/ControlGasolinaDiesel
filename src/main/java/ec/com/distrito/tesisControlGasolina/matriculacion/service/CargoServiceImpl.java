package ec.com.mariscalSucre.tesisMatriculacion.matriculacion.service;

import static ec.com.mariscalSucre.tesisMatriculacion.utils.UtilsAplicacion.presentaMensaje;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.dao.CargoDao;
import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity.Cargo;

@Service
public class CargoServiceImpl implements CargoService, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CargoDao cargoDao;

	public String actualizar(Cargo cargo) {
		if (!cargoDao.comprobarIndices(Cargo.class, "nombre", cargo.getNombre(), String.valueOf(cargo.getId()))) {
			cargoDao.actualizar(cargo);
			return "SAVE";
		} else
			return "EXISTE";
	}

	public void eliminar(Cargo cargo) {
		cargo.setActivo(cargo.getActivo() ? false : true);
		cargoDao.actualizar(cargo);

		if (cargo.getActivo())
			presentaMensaje(FacesMessage.SEVERITY_INFO, "ACTIVO CARGO: " + cargo.getNombre());
		else
			presentaMensaje(FacesMessage.SEVERITY_INFO, "DESACTIVO CARGO: " + cargo.getNombre());
	}

	public String insertar(Cargo cargo) {
		if (!cargoDao.comprobarIndices(Cargo.class, "nombre", cargo.getNombre(), String.valueOf(cargo.getId()))) {
			cargo.setActivo(true);
			cargo.setPorDefecto(false);
			cargo.setNombre(cargo.getNombre().toUpperCase());
			cargoDao.insertar(cargo);
			return "SAVE";
		} else
			return "EXISTE";
	}

	public List<Cargo> obtener(Boolean activo) {
		return cargoDao.obtener(Cargo.class, "nombre", activo);
	}

	public Cargo obtenerPorCargoId(int cargoId) {
		return cargoDao.obtenerPorId(Cargo.class, cargoId);
	}
}