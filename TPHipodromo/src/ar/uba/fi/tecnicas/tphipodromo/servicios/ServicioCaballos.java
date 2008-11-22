package ar.uba.fi.tecnicas.tphipodromo.servicios;

import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.servicios.dto.CaballoDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dto.DetalleCaballoDTO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ServicioCaballos")
public interface ServicioCaballos extends RemoteService {
	
	public Collection<CaballoDTO> buscar();
	
	public DetalleCaballoDTO buscar(String nombre);
	
}
