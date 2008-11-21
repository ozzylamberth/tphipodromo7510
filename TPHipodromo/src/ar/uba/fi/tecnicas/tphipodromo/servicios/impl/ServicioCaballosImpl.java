package ar.uba.fi.tecnicas.tphipodromo.servicios.impl;
import java.util.Collection;
import java.util.LinkedList;

import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioCaballos;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dto.CaballoDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dto.DetalleCaballoDTO;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;


public class ServicioCaballosImpl extends RemoteServiceServlet implements ServicioCaballos {

	public Collection<CaballoDTO> buscar() {
		Collection<CaballoDTO> caballos = new LinkedList<CaballoDTO>();
		
		caballos.add(new CaballoDTO("Morocho Aldao", 3, 120));
		caballos.add(new CaballoDTO("Ride Li", 4, 135));
		caballos.add(new CaballoDTO("La Chalada", 6, 147));
		
		return caballos;
	}
	
	@Override
	public DetalleCaballoDTO buscar(String nombre) {
		DetalleCaballoDTO caballo = new DetalleCaballoDTO();
		
		caballo.setCaballeriza("La caballeriza");
		caballo.setCriador("Haras Santa Inés");
		caballo.setEdad(3);
		caballo.setMadre("Rushi");
		caballo.setNombre("Morocho Aldao");
		caballo.setPadre("Linyera Real");
		caballo.setPelaje("Marrón");
		caballo.setPeso(120);
		caballo.setPuraSangre(Boolean.TRUE);
		
		return caballo;
	}

}
