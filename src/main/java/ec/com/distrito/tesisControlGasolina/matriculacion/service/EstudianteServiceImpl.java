package ec.com.mariscalSucre.tesisMatriculacion.matriculacion.service;

import static ec.com.mariscalSucre.tesisMatriculacion.utils.UtilsAplicacion.presentaMensaje;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.dao.EstudianteDao;
import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.dao.PersonaDao;
import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity.Estudiante;
import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity.Persona;
import ec.com.mariscalSucre.tesisMatriculacion.seguridad.entity.RolUsuario;

@Service
public class EstudianteServiceImpl implements EstudianteService, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private PersonaService personaService;

	@Autowired
	private PersonaDao personaDao;

	@Autowired
	private EstudianteDao estudianteDao;

	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = factory.getValidator();

	public void actualizar(Persona persona) {
		Set<ConstraintViolation<Estudiante>> violationsEstudiante = validator.validate(persona.getEstudiante());
		if (violationsEstudiante.size() > 0)
			for (ConstraintViolation<Estudiante> cv : violationsEstudiante)
				presentaMensaje(FacesMessage.SEVERITY_INFO, cv.getMessage());
		else if (personaService.actualizar(persona))
			presentaMensaje(FacesMessage.SEVERITY_INFO, "ESTUDIANTE ACTUALIZADO", "cerrar", true);
	}

	public Long contar() {
		return (Long) estudianteDao.contar(Estudiante.class);
	}

	public void eliminar(Persona persona) {
		persona.getEstudiante().setActivo(persona.getEstudiante().getActivo() ? false : true);

		for (RolUsuario ru : persona.getRolUsuarios())
			if (ru.getRol().getNombre().compareTo("CLIE") == 0) {
				ru.setActivo(persona.getEstudiante().getActivo());
				break;
			}

		personaDao.actualizar(persona);
		if (persona.getEstudiante().getActivo())
			presentaMensaje(FacesMessage.SEVERITY_INFO,
					"SE ACTIVÓ AL ESTUDIANTE: " + persona.getApellido() + " " + persona.getNombre());
		else
			presentaMensaje(FacesMessage.SEVERITY_INFO,
					"SE DESACTIVÓ AL ESTUDIANTE: " + persona.getApellido() + " " + persona.getNombre());
	}

	public Persona insertar(Persona persona) {
		persona.getEstudiante().setActivo(true);
		persona.getEstudiante().setPersona(persona);
		persona.getEstudiante().setFolio(String.valueOf(contar() + 1));
		persona.getEstudiante().setFechaRegistro(new Timestamp(new Date().getTime()));
		Set<ConstraintViolation<Estudiante>> violationsEstudiante = validator.validate(persona.getEstudiante());
		if (violationsEstudiante.size() > 0)
			for (ConstraintViolation<Estudiante> cv : violationsEstudiante)
				presentaMensaje(FacesMessage.SEVERITY_INFO, cv.getMessage());
		else {
			boolean retorno = false;
			if (persona.getId() == null)
				retorno = personaService.insertar(persona);
			else
				retorno = personaService.actualizar(persona);

			if (retorno) {
				List<String> roles = new ArrayList<String>();
				roles.add("ESTUDIANTE");
				personaService.insertarRoles(persona, roles);
				presentaMensaje(FacesMessage.SEVERITY_INFO, "ESTUDIANTE INSERTADO", "cerrar", true);
			}
		}
		return persona;
	}

	public List<Persona> obtener() {
		List<Persona> lista = personaDao.obtenerPorHql(
				"select p from Persona p " + "inner join p.estudiante e " + "order by p.apellido, p.nombre",
				new Object[] {});
		return lista;
	}

	public List<Persona> obtener(String criterioEstudiante, Integer estudianteId) {
		List<Persona> lista = new ArrayList<Persona>();

		if ((criterioEstudiante == null || criterioEstudiante.compareToIgnoreCase("") == 0)
				&& (estudianteId == null || estudianteId == 0))
			presentaMensaje(FacesMessage.SEVERITY_ERROR, "INGRESE UN CRITERIO DE BÚSQUEDA VALIDO");
		else if (criterioEstudiante != null && criterioEstudiante.length() >= 3)
			lista = personaDao.obtenerPorHql(
					"select distinct p from Persona p inner join p.estudiante e "
							+ "where (p.cedula like ?1 or p.nombre like ?1 or p.apellido like ?1) and p.activo=true and e.activo=true",
					new Object[] { "%" + criterioEstudiante.toUpperCase() + "%" });
		else if (estudianteId != 0)
			lista = personaDao.obtenerPorHql("select distinct p from Persona p inner join p.estudiante e "
					+ "where p.activo=true and e.activo=true and e.id=?1", new Object[] { estudianteId });

		if (lista.isEmpty())
			presentaMensaje(FacesMessage.SEVERITY_WARN, "NO SE ENCONTRO NINGUNA COINCIDENCIA");

		return lista;
	}

	public List<Persona> obtenerActivos() {
		List<Persona> lista = personaDao.obtenerPorHql(
				"select p from Persona p inner join p.estudiante e where where p.activo=true order by p.apellido, p.nombre",
				new Object[] {});
		return lista;
	}

	public Estudiante obtenerEstudiantePorCedula(String cedula) {
		List<Persona> lista = personaDao.obtenerPorHql(
				"select p from Persona p inner join p.estudiante e where p.cedula=?1 and p.activo=true and e.activo=true",
				new Object[] { cedula });
		if (!lista.isEmpty())
			return lista.get(0).getEstudiante();
		return null;
	}

	public Persona obtenerPorCedula(String cedula) {
		List<Persona> lista = personaDao.obtenerPorHql(
				"select distinct p from Persona p inner join p.estudiante e " + "where p.cedula=?1 and p.activo=true",
				new Object[] { cedula });
		if (!lista.isEmpty())
			return lista.get(0);
		return null;
	}

	public Persona obtenerPorEstudianteId(Integer estudianteId) {
		List<Persona> lista = personaDao.obtenerPorHql(
				"select distinct p from Persona p inner join p.estudiante e " + "where e.id=?1",
				new Object[] { estudianteId });
		if (!lista.isEmpty())
			return lista.get(0);
		return null;
	}

	public Persona obtenerPorPersonaId(Integer personaId) {
		return personaService.obtenerPorPersonaId(personaId);
	}

	public List<Persona> obtenerTodosPorBusqueda(String criterioBusquedaEstudiante) {
		criterioBusquedaEstudiante = criterioBusquedaEstudiante.trim();
		List<Persona> lista = new ArrayList<Persona>();

		if (criterioBusquedaEstudiante.compareToIgnoreCase("") == 0)
			presentaMensaje(FacesMessage.SEVERITY_WARN, "INGRESE UN CRITERIO DE BUSQUEDA");
		else {
			if (criterioBusquedaEstudiante.length() >= 3) {
				if (criterioBusquedaEstudiante.compareToIgnoreCase("") != 0)
					lista = personaDao.obtenerPorHql(
							"select distinct p from Persona p " + "inner join fetch p.estudiante e "
									+ "left join fetch p.rolUsuarios "
									+ "where (p.cedula like ?1 or p.nombre like ?1 or p.apellido like ?1 or e.folio like ?1) "
									+ "order by e.id, p.apellido, p.nombre",
							new Object[] { "%" + criterioBusquedaEstudiante + "%" });
			} else
				presentaMensaje(FacesMessage.SEVERITY_WARN, "INGRESE MINIMO 3 CARACTERES");
		}
		if (lista.isEmpty())
			presentaMensaje(FacesMessage.SEVERITY_WARN, "NO SE ENCONTRO NINGUNA COINCIDENCIA");
		return lista;
	}

	public List<String> obtenerListaEstudiantesAutoComplete(String criterioEstudianteBusqueda) {
		List<String> list = new ArrayList<String>();
		List<Persona> lista = obtener(criterioEstudianteBusqueda, 0);
		if (!lista.isEmpty())
			for (Persona p : lista)
				list.add(p.getCiudad().getNombre() + " - " + p.getCedula() + " - " + p.getApellido() + " "
						+ p.getNombre());
		return list;
	}

	public Estudiante cargarEstudiante(String estudiante) {
		return obtenerEstudiantePorCedula(estudiante.split(" - ")[1]);
	}

}