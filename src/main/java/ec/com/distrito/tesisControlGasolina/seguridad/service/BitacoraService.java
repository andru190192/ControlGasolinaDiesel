package ec.com.distrito.tesisControlGasolina.seguridad.service;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ec.com.distrito.tesisControlGasolina.seguridad.entity.Bitacora;

public interface BitacoraService {
	@Transactional
	public List<Bitacora> obtener(Integer usuarioId, Date fechaInicio);
}