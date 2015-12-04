package ec.com.mariscalSucre.tesisMatriculacion.matriculacion.service;

import static ec.com.mariscalSucre.tesisMatriculacion.utils.UtilsAplicacion.presentaMensaje;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.dao.MatriculaDao;
import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity.Matricula;

@Service
public class MatriculaServiceImpl implements MatriculaService, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private MatriculaDao matriculaDao;

	public List<Matricula> obtener() {
		List<Matricula> lista = matriculaDao.obtenerPorHql("select m from Matricula m", new Object[] {});
		return lista;
	}

	public Matricula insertarActualizar(Matricula matricula) {
		System.out.println(obternerNumeroMatricula(matricula.getPeriodo().getId()));
		matricula.setMatricula(obternerNumeroMatricula(matricula.getPeriodo().getId()) + 1);
		boolean retorno = false;
		// if (matricula.getId().getMatricula() == null) {
		retorno = insertar(matricula);
		// System.out.println("insertar");
		// } else {
		// retorno = actualizar(matricula);
		// System.out.println("actualizar");
		// }
		if (retorno)
			presentaMensaje(FacesMessage.SEVERITY_INFO, "MATRICULA INSERTADA", "cerrar", true);
		return matricula;
	}

	public boolean comprobarIndices(Integer periodoid, Integer estudianteid) {
		List<Matricula> lista = matriculaDao.obtenerPorHql(
				"select m from Matricula m where m.id.periodo.id=?1 and m.estudiante.id=?2",
				new Object[] { periodoid, estudianteid });
		if (lista.isEmpty())
			return false;
		else
			return true;
	}

	public boolean insertar(Matricula matricula) {
		boolean retorno = false;

		if (matricula.getPeriodo().getId() == null || matricula.getPeriodo().getId() == 0)
			presentaMensaje(FacesMessage.SEVERITY_INFO, "ESCOJA UN PERIODO");

		// else if (comprobarIndices(matricula.getId().getPeriodo().getId(),
		// matricula.getEstudiante().getId()))
		// presentaMensaje(FacesMessage.SEVERITY_INFO,
		// "EL ESTUDIANTE YA ESTA MATRICULADO EN EL PERIODO LECTIVO ESCOGIDO",
		// "cerrar", false);
		// else {
		matricula.setActivo(true);
		matricula.setFecha(new Timestamp(new Date().getTime()));
		System.out.println("entre");
		matriculaDao.insertar(matricula);
		retorno = true;

		return retorno;
	}

	public boolean actualizar(Matricula matricula) {
		boolean retorno = false;

		if (matricula.getPeriodo().getId() == null || matricula.getId() == 0)
			presentaMensaje(FacesMessage.SEVERITY_INFO, "ESCOJA UNA PERIODO");
		else if (comprobarIndices(matricula.getId(), matricula.getEstudiante().getId()))
			presentaMensaje(FacesMessage.SEVERITY_INFO,
					"EL ESTUDIANTE YA ESTA MATRICULADO EN EL PERIODO LECTIVO ESCOGIDO", "cerrar", false);
		else {
			matricula.setActivo(true);
			matriculaDao.actualizar(matricula);
			retorno = true;
		}
		return retorno;
	}

	public Integer obternerNumeroMatricula(Integer periodo) {
		List<Matricula> lista = matriculaDao.obtenerPorHql(
				"select m from Matricula m inner join m.periodo p where p.id =?1 order by m.matricula desc",
				new Object[] { periodo });
		if (lista.isEmpty())
			return 0;
		else
			return lista.get(0).getMatricula();
	}

}