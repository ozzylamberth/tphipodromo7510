package ar.uba.fi.tecnicas.tphipodromo.servicios.transformers;

import java.math.BigDecimal;

import org.apache.commons.collections.Transformer;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Jockey;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.JockeyDTO;

public class JockeyTransformerFromDTO implements Transformer {

	public Object transform(Object arg0) {
		JockeyDTO jockeyDTO = (JockeyDTO) arg0;
		Jockey jockey = new Jockey();
		jockey.setId(jockeyDTO.getId());
		jockey.setNombre(jockeyDTO.getNombre());
		jockey.setApellido(jockeyDTO.getApellido());
		jockey.setPeso(new BigDecimal(jockeyDTO.getPeso().toString()));
		return jockey;
	}

}
