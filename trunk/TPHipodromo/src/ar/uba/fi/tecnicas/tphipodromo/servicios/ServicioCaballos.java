package ar.uba.fi.tecnicas.tphipodromo.servicios;

import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.EntidadInexistenteException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ServicioCaballos")
public interface ServicioCaballos extends RemoteService {
	
	public Collection<CaballoDTO> buscarTodos();
	
	public CaballoDTO buscarPorId(Long id) throws EntidadInexistenteException;
	
	public Long guardar(CaballoDTO caballoDTO);
	
	public void borrar(Long id) throws EntidadInexistenteException;
	
}
