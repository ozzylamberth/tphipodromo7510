package ar.uba.fi.tecnicas.tphipodromo.servicios.transformers;

import org.apache.commons.collections.Transformer;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Carrera;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;

public class CarreraTransformerToDTO implements Transformer {

	public Object transform(Object arg0) {
		Carrera carrera = (Carrera) arg0;
		CarreraDTO carreraDTO = new CarreraDTO();
		carreraDTO.setId(carrera.getId());
		carreraDTO.setDistancia(new Double(carrera.getDistancia().toString()));
		carreraDTO.setFechaYHora(carrera.getFechaYHora());
		carreraDTO.setNombre(carrera.getNombre());
		carreraDTO.setNumero(carrera.getNumero());
		carreraDTO.setEstado(carrera.getEstadoCarrera().getNombre());
		return carreraDTO;
	}

}
