package ar.uba.fi.tecnicas.tphipodromo.servicios;

import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.DetalleCaballoDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.CaballoInexistenteException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ServicioCaballos")
public interface ServicioCaballos extends RemoteService {
	
	public Collection<CaballoDTO> buscarTodos();
	
	public DetalleCaballoDTO buscarPorId(Long id) throws CaballoInexistenteException;
	
	public void guardar(DetalleCaballoDTO detalleCaballoDTO) throws CaballoInexistenteException;
	
}
