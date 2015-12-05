package ec.com.distrito.tesisControlGasolina.control.service;

import static ec.com.distrito.tesisControlGasolina.utils.UtilsAplicacion.presentaMensaje;
import static ec.com.distrito.tesisControlGasolina.utils.UtilsAplicacion.redireccionar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ec.com.distrito.tesisControlGasolina.control.dao.ChoferDao;
import ec.com.distrito.tesisControlGasolina.control.entity.Chofer;
import ec.com.distrito.tesisControlGasolina.seguridad.entity.Rol;
import ec.com.distrito.tesisControlGasolina.seguridad.entity.RolUsuario;
import ec.com.distrito.tesisControlGasolina.seguridad.service.RolService;

@Service
public class ChoferServiceImpl implements ChoferService, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ChoferDao choferDao;

	@Autowired
	private RolService rolService;

	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = factory.getValidator();

	public boolean actualizar(Chofer chofer) {
		boolean retorno = false;

		Set<ConstraintViolation<Chofer>> violations = validator.validate(chofer);
		if (violations.size() > 0)
			for (ConstraintViolation<Chofer> cv : violations)
				presentaMensaje(FacesMessage.SEVERITY_INFO, cv.getMessage());
		else if (choferDao.comprobarIndices(Chofer.class, "cedula", chofer.getCedula(), String.valueOf(chofer.getId())))
			presentaMensaje(FacesMessage.SEVERITY_INFO, "LA CÉDULA YA EXISTE", "cerrar", false);
		else {
			chofer.setPassword(generarClave(chofer.getCedula()));
			choferDao.actualizar(chofer);
			retorno = true;
		}
		return retorno;
	}

	public void cambiarClave(String claveActual, String clave1, String clave2) {
		Chofer chofer = obtenerActivoPorCedula(SecurityContextHolder.getContext().getAuthentication().getName());
		if (claveActual.length() == 0 || clave1.length() == 0 || clave2.length() == 0) {
			presentaMensaje(FacesMessage.SEVERITY_INFO, "INGRESE TODOS LOS DATOS REQUERIDOS");
		} else if (clave1.length() < 8 || clave2.length() < 8) {
			presentaMensaje(FacesMessage.SEVERITY_INFO,
					"LA NUEVA CONTRASEÑA DEBE TENER UNA LONGITUD MINIMA DE 8 CARACTERES");
		} else if (!compararClave(chofer.getPassword(), generarClave(claveActual))) {
			presentaMensaje(FacesMessage.SEVERITY_INFO, "LA CONTRASEÑA ACTUAL ES INCORRECTA");
		} else if (!compararClave(clave1, clave2)) {
			presentaMensaje(FacesMessage.SEVERITY_INFO, "LAS CONTRASEÑAS NUEVAS NO COINCIDEN");
		} else if (compararClave(clave1, chofer.getCedula())) {
			presentaMensaje(FacesMessage.SEVERITY_INFO, "LA CONTRASEÑA NO PUEDE SER IGUAL AL USUARIO");
		} else {
			chofer.setPassword(generarClave(clave1));
			choferDao.actualizar(chofer);
			presentaMensaje(FacesMessage.SEVERITY_INFO, "CAMBIO DE PASSWORD EXITOSO");
			redireccionar("../../logout.jsf");
		}
	}

	public boolean compararClave(String clave1, String clave2) {
		return clave1.compareTo(clave2) == 0 ? true : false;
	}

	public boolean comprobarRol(String cedula, String rol) {
		List<Chofer> usuario = null;
		usuario = choferDao.obtenerPorHql("select distinct c from Chofer c " + "left join fetch c.rolUsuarios ru "
				+ "left join fetch ru.rol r " + "where c.cedula=?1 and r.nombre=?2", new Object[] { cedula, rol });

		if (usuario.size() == 0)
			return false;
		else
			return true;
	}

	public Long contar() {
		return (Long) choferDao.contar(Chofer.class);
	}

	public void eliminar(Chofer chofer) {
		chofer.setActivo(chofer.getActivo() ? false : true);
		choferDao.actualizar(chofer);

		if (chofer.getActivo())
			presentaMensaje(FacesMessage.SEVERITY_INFO,
					"SE ACTIVO AL CHOFER: " + chofer.getApellido() + " " + chofer.getNombre());
		else
			presentaMensaje(FacesMessage.SEVERITY_INFO,
					"SE DESACTIVO AL CHOFER: " + chofer.getApellido() + " " + chofer.getNombre());
	}

	public String generarClave(String clave) {
		ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder(256);
		return shaPasswordEncoder.encodePassword(clave, null);
	}

	public boolean insertar(Chofer chofer) {
		boolean retorno = false;

		Set<ConstraintViolation<Chofer>> violationsChofer = validator.validate(chofer);
		if (violationsChofer.size() > 0)
			for (ConstraintViolation<Chofer> cv : violationsChofer)
				presentaMensaje(FacesMessage.SEVERITY_INFO, cv.getMessage());

		else if (choferDao.comprobarIndices(Chofer.class, "cedula", chofer.getCedula(), String.valueOf(chofer.getId())))
			presentaMensaje(FacesMessage.SEVERITY_INFO, "LA CÉDULA YA EXISTE", "cerrar", false);
		else {
			chofer.setActivo(true);
			chofer.setPassword(generarClave(chofer.getCedula()));
			choferDao.insertar(chofer);
			retorno = true;
		}
		return retorno;
	}

	public String insertarRoles(Chofer chofer, List<String> roles) {
		if (chofer.getRolUsuarios() == null)
			chofer.setRolUsuarios(new ArrayList<RolUsuario>());

		for (String r : roles) {
			RolUsuario rolUsuario = new RolUsuario();
			Rol rol = rolService.obtenerPorNombre(r);
			rolUsuario.setRol(rol);
			rolUsuario.setActivo(true);
			chofer.addRolUsuario(rolUsuario);
		}
		choferDao.actualizar(chofer);
		// }
		return "SAVE";
	}

	public List<Chofer> obtener(Boolean activo) {
		List<Chofer> lista = choferDao.obtenerPorHql("select c from Chofer c order by c.apellido, c.nombre",
				new Object[] {});
		return lista;
	}

	public Chofer obtenerActivoPorCedula(String cedula) {
		List<Chofer> chofer = choferDao.obtenerPorHql("select c from Chofer c where c.cedula=?1 and c.activo=true",
				new Object[] { cedula });
		if (chofer != null && chofer.size() == 1)
			return chofer.get(0);
		return null;
	}

	public Chofer obtenerPorCedula(String cedula) {
		List<Chofer> chofer = choferDao.obtenerPorHql("select c from Chofer c where c.cedula=?1 and c.activo=true",
				new Object[] { cedula });
		if (chofer != null)
			if (chofer.size() != 0)
				return chofer.get(0);

		return null;
	}

	public Chofer obtenerPorChoferId(Integer choferId) {
		Chofer chofer = choferDao
				.obtenerPorHql("select c from Chofer c " + "where c.id=?1 and c.activo=true", new Object[] { choferId })
				.get(0);
		return chofer;
	}

	public List<Chofer> obtenerTodosPorBusqueda(String criterioBusquedaChofer) {
		List<Chofer> lista = null;
		criterioBusquedaChofer = criterioBusquedaChofer.toUpperCase();

		if (criterioBusquedaChofer.compareToIgnoreCase("") == 0)
			presentaMensaje(FacesMessage.SEVERITY_ERROR, "INGRESE UN CRITERIO DE BUSQUEDA");
		else {
			if (criterioBusquedaChofer.length() >= 3) {
				if (criterioBusquedaChofer.compareToIgnoreCase("") != 0)
					lista = choferDao.obtenerPorHql(
							"select distinct c from Chofer c "
									+ "where (c.cedula like ?1 or c.nombre like ?1 or c.apellido like ?1 c.licencia like ?1 ) "
									+ "order by c.apellido, c.nombre",
							new Object[] { "%" + criterioBusquedaChofer + "%" });
				if (lista.isEmpty())
					presentaMensaje(FacesMessage.SEVERITY_INFO, "NO SE ENCONTRO NINGUNA COINCIDENCIA");
			} else
				presentaMensaje(FacesMessage.SEVERITY_ERROR, "INGRESE MINIMO 3 CARACTERES");
		}
		return lista;
	}

	public List<String> obtenerListaChoferAutoComplete(String criterioChoferBusqueda) {
		List<String> list = new ArrayList<String>();
		List<Chofer> lista = obtenerChoferes(criterioChoferBusqueda);
		if (!lista.isEmpty())
			for (Chofer c : lista)
				list.add(c.getCedula() + " - " + c.getApellido() + " " + c.getNombre());
		return list;
	}

	public List<Chofer> obtenerChoferes(String criterioChoferBusqueda) {
		criterioChoferBusqueda = criterioChoferBusqueda.toUpperCase();
		List<Chofer> lista = null;
		if (criterioChoferBusqueda.length() >= 0 && criterioChoferBusqueda.length() < 3)
			presentaMensaje(FacesMessage.SEVERITY_ERROR, "INGRESE MAS DE 3 CARACTERES");
		else {
			lista = choferDao.obtenerPorHql(
					"select distinct p from Chofer p " + "where "
							+ "(p.cedula like ?1 or p.nombre like ?1 or p.apellido like ?1 ) " + "and p.activo=true",
					new Object[] { "%" + criterioChoferBusqueda + "%" });
			if (lista.isEmpty())
				presentaMensaje(FacesMessage.SEVERITY_WARN, "NO SE ENCONTRO NINGUNA COINCIDENCIA");
		}
		return lista;
	}

	public Chofer cargarChofer(String chofer) {
		return obtenerPorCedula(chofer.split(" - ")[0]);
	}

}
