package ec.com.mariscalSucre.tesisMatriculacion.seguridad.aspect;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.service.PersonaService;
import ec.com.mariscalSucre.tesisMatriculacion.seguridad.dao.BitacoraDao;
import ec.com.mariscalSucre.tesisMatriculacion.seguridad.entity.Bitacora;

@Component
@Aspect
public class BitacoraAspect implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	public BitacoraDao bitacoraDao;

	@Autowired
	public PersonaService personaService;

	@After("execution(public * ec.com.mariscalSucre.tesisMatriculacion.matriculacion.service..*.eliminar(..)) "
			+ "|| execution(public * ec.com.mariscalSucre.tesisMatriculacion.matriculacion.service..*.eliminar(..)) ")
	public void auditarEliminar(JoinPoint joinPoint) {
		Object obj = (joinPoint.getArgs())[0];
		String mensaje = "";
		try {
			String nombreObjeto = obj.getClass().getSimpleName();
			Method metodo = obj.getClass().getMethod("get" + nombreObjeto + "id", new Class[] {});
			Method metodoEliminar = obj.getClass().getMethod("getActivo", new Class[] {});

			if ((Boolean) metodoEliminar.invoke(obj, new Object[] {}))
				mensaje = "Se Activó el objeto " + nombreObjeto + " con la llave: # "
						+ metodo.invoke(obj, new Object[] {});
			else
				mensaje = "Se Desactivó el objeto " + nombreObjeto + " con la llave: # "
						+ metodo.invoke(obj, new Object[] {});

		} catch (Exception e) {
			mensaje = "error al leer método";
		}
		String cedula = SecurityContextHolder.getContext().getAuthentication().getName();
		if (cedula.compareTo("0123456789") != 0)
			bitacoraDao.insertar(new Bitacora(new Timestamp((new Date()).getTime()), mensaje,
					personaService.obtenerPorCedula(cedula)));
	}

	@After("execution(public * ec.com.mariscalSucre.tesisMatriculacion.matriculacion.service..*.insertar(..)) "
			+ "|| execution(public * ec.com.mariscalSucre.tesisMatriculacion.matriculacion.service..*.actualizar(..)) "
			+ "|| execution(public * ec.com.mariscalSucre.tesisMatriculacion.rrhh.service..*.insertar(..)) "
			+ "|| execution(public * ec.com.mariscalSucre.tesisMatriculacion.rrhh.service..*.actualizar(..)) ")
	public void auditar(JoinPoint joinPoint) {
		Object obj = (joinPoint.getArgs())[0];
		String mensaje = "";
		try {
			String nombreObjeto = obj.getClass().getSimpleName();
			Method metodo = obj.getClass().getMethod("get" + nombreObjeto + "id", new Class[] {});
			mensaje = "Se acaba de " + joinPoint.getSignature().getName() + " el objeto " + nombreObjeto
					+ " con la llave: # " + metodo.invoke(obj, new Object[] {});
		} catch (Exception e) {
			mensaje = "error al leer método";
		}
		String cedula = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(cedula);
		if (cedula.compareTo("0123456789") != 0)
			bitacoraDao.insertar(new Bitacora(new Timestamp((new Date()).getTime()), mensaje,
					personaService.obtenerPorCedula(cedula)));
	}

	@After("execution(public * ec.com.mariscalSucre.tesisMatriculacion.seguridad.service.MenuService.obtenerPorUsuario(..)) ")
	public void ingreso(JoinPoint joinPoint) {
		String cedula = SecurityContextHolder.getContext().getAuthentication().getName();
		if (cedula.compareTo("0123456789") != 0)
			bitacoraDao.insertar(new Bitacora(new Timestamp((new Date()).getTime()), "Ingresó al Sistema",
					personaService.obtenerActivoPorCedula(cedula)));
	}
}