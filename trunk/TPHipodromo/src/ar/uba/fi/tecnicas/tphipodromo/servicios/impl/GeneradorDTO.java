package ar.uba.fi.tecnicas.tphipodromo.servicios.impl;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Caballo;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.DetalleCaballoDTO;

public class GeneradorDTO {
	
	private static GeneradorDTO instance = new GeneradorDTO();
	
	private GeneradorDTO() {
	}
	
	public static GeneradorDTO getInstance() {
		return instance;
	}
	
	public CaballoDTO convertToCaballoDTO(Caballo caballo) {
		CaballoDTO caballoDTO = new CaballoDTO();
		caballoDTO.setId(caballo.getId());
		caballoDTO.setNombre(caballo.getNombre());
		caballoDTO.setEdad(caballo.getEdad());
		caballoDTO.setPeso(new Double(caballo.getPeso().toString()));
		return caballoDTO;
	}
	
	public DetalleCaballoDTO convertToDetalleCaballoDTO(Caballo caballo) {
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
		}
		if (caballo.getMadre() != null) {
			detalleCaballoDTO.setMadre(caballo.getMadre().getNombre());
		}
		return detalleCaballoDTO;
	}
	
}
