package ar.uba.fi.tecnicas.tphipodromo.servicios.transformers;

import java.math.BigDecimal;

import org.apache.commons.collections.Transformer;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Carrera;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;

public class CarreraTransformerFromDTO implements Transformer {

	public Object transform(Object arg0) {
		CarreraDTO carreraDTO = (CarreraDTO) arg0;
		Carrera carrera = new Carrera();
		carrera.setId(carreraDTO.getId());
		carrera.setDistancia(new BigDecimal(carreraDTO.getDistancia().toString()));
		carrera.setFechaYHora(carreraDTO.getFechaYHora());
		carrera.setNombre(carreraDTO.getNombre());
		carrera.setNumero(carreraDTO.getNumero());
		return carrera;
	}

}
