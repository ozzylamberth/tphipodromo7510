package ar.uba.fi.tecnicas.tphipodromo.servicios.transformers;

import org.apache.commons.collections.Transformer;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Caballo;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;

/**
 * Convierte un Caballo en un CaballoDTO 
 */
public class CaballoDTOTransformer implements Transformer {

	public Object transform(Object arg0) {
		Caballo caballo = (Caballo) arg0;
		CaballoDTO caballoDTO = new CaballoDTO();
		caballoDTO.setId(caballo.getId());
		caballoDTO.setNombre(caballo.getNombre());
		caballoDTO.setEdad(caballo.getEdad());
		caballoDTO.setPeso(new Double(caballo.getPeso().toString()));
		return caballoDTO;
	}

}
