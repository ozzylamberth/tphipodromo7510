package ar.uba.fi.tecnicas.tphipodromo.servicios.transformers;

import java.math.BigDecimal;

import org.apache.commons.collections.Transformer;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Carrera;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.CarreraDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.impl.ServicioSpring;

public class CarreraTransformerFromDTO implements Transformer {
	
	private CarreraDao carreraDao;
	
	public CarreraTransformerFromDTO() {
		this.carreraDao = (CarreraDao) ServicioSpring.getInstance().getBean("carreraDao");
	}

	public Object transform(Object arg0) {
		CarreraDTO carreraDTO = (CarreraDTO) arg0;
		Carrera carrera = this.getCarrera(carreraDTO);
		carrera.setId(carreraDTO.getId());
		carrera.setDistancia(new BigDecimal(carreraDTO.getDistancia().toString()));
		carrera.setFechaYHora(carreraDTO.getFechaYHora());
		carrera.setNombre(carreraDTO.getNombre());
		carrera.setNumero(carreraDTO.getNumero());
		return carrera;
	}
	
	private Carrera getCarrera(CarreraDTO carreraDTO) {
		try {
			return this.carreraDao.buscarPorId(carreraDTO.getId());
		} catch (ObjetoInexistenteException e) {
			return new Carrera();
		}
	}

}
