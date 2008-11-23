package ar.uba.fi.tecnicas.tphipodromo.servicios.transformers;

import org.apache.commons.collections.Transformer;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Jockey;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.JockeyDTO;

public class JockeyTransformerToDTO implements Transformer {

	public Object transform(Object arg0) {
		Jockey jockey = (Jockey) arg0;
		JockeyDTO jockeyDTO = new JockeyDTO();
		jockeyDTO.setId(jockey.getId());
		jockeyDTO.setNombre(jockey.getNombre());
		jockeyDTO.setApellido(jockey.getApellido());
		jockeyDTO.setPeso(new Double(jockey.getPeso().toString()));
		return jockeyDTO;
	}

}
