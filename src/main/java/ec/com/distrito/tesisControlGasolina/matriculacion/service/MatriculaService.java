package ec.com.mariscalSucre.tesisMatriculacion.matriculacion.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ec.com.mariscalSucre.tesisMatriculacion.matriculacion.entity.Matricula;

public interface MatriculaService {

	@Transactional()
	public List<Matricula> obtener();

	@Transactional
	public Matricula insertarActualizar(Matricula matricula);

	@Transactional
	public boolean insertar(Matricula matricula);

	@Transactional
	public boolean actualizar(Matricula matricula);

	@Transactional
	public boolean comprobarIndices(Integer periodoid, Integer estudianteid);

	@Transactional
	public Integer obternerNumeroMatricula(Integer periodo);

}