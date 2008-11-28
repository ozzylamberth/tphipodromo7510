package ar.uba.fi.tecnicas.tphipodromo.servicios.transformers;

import org.apache.commons.collections.Transformer;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Caballo;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;

/**
 * Convierte un Caballo en un CaballoDTO
 */
public class CaballoTrasnformerToDTO implements Transformer {

	public Object transform(Object arg0) {
		Caballo caballo = (Caballo) arg0;
		CaballoDTO detalleCaballoDTO = new CaballoDTO();
		detalleCaballoDTO.setId(caballo.getId());
		detalleCaballoDTO.setCaballeriza(caballo.getCaballeriza());
		detalleCaballoDTO.setCriador(caballo.getCriador());
		detalleCaballoDTO.setEdad(caballo.getEdad());
		detalleCaballoDTO.setNombre(caballo.getNombre());
		detalleCaballoDTO.setPelaje(caballo.getPelaje());
		detalleCaballoDTO.setPeso(new Double(caballo.getPeso().toString()));
		detalleCaballoDTO.setPuraSangre(caballo.isPuraSangre());
		if (caballo.getPadre() != null) {
			detalleCaballoDTO.setPadreId(caballo.getPadre().getId());
		}
		if (caballo.getMadre() != null) {
			detalleCaballoDTO.setMadreId(caballo.getMadre().getId());
		}
		return detalleCaballoDTO;
	}

}
