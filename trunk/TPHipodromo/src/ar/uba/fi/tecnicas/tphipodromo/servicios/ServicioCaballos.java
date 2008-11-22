package ar.uba.fi.tecnicas.tphipodromo.servicios;

import java.util.Collection;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.DetalleCaballoDTO;

@RemoteServiceRelativePath("ServicioCaballos")
public interface ServicioCaballos extends RemoteService {
	
	public Collection<CaballoDTO> buscarTodos();
	
	public DetalleCaballoDTO buscarCaballoPorNombre(String nombre);
	
}
