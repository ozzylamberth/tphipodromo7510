package ar.uba.fi.tecnicas.tphipodromo.servicios.transformers;

import org.apache.commons.collections.Transformer;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Caballo;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.DetalleCaballoDTO;

/**
 * Convierte un Caballo en un DetalleCaballoDTO
 */
public class DetalleCaballoDTOTrasnformer implements Transformer {

	public Object transform(Object arg0) {
		Caballo caballo = (Caballo) arg0;
		DetalleCaballoDTO detalleCaballoDTO = new DetalleCaballoDTO();
		detalleCaballoDTO.setId(caballo.getId());
		detalleCaballoDTO.setCaballeriza(caballo.getCaballeriza());
		detalleCaballoDTO.setCriador(caballo.getCriador());
		detalleCaballoDTO.setEdad(caballo.getEdad());
		detalleCaballoDTO.setNombre(caballo.getNombre());
		detalleCaballoDTO.setPelaje(caballo.getPelaje());
		detalleCaballoDTO.setPeso(new Double(caballo.getPeso().toString()));
		detalleCaballoDTO.setPuraSangre(caballo.isPuraSangre());
		if (caballo.getPadre() != null) {
			detalleCaballoDTO.setPadre(caballo.getPadre().getNombre());
			detalleCaballoDTO.setPadreId(caballo.getPadre().getId());
		}
		if (caballo.getMadre() != null) {
			detalleCaballoDTO.setMadre(caballo.getMadre().getNombre());
			detalleCaballoDTO.setMadreId(caballo.getMadre().getId());
		}
		return detalleCaballoDTO;
	}

}