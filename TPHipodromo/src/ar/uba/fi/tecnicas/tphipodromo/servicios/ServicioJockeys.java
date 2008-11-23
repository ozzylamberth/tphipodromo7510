package ar.uba.fi.tecnicas.tphipodromo.servicios;

import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.JockeyDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.EntidadInexistenteException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ServicioJockeys")
public interface ServicioJockeys extends RemoteService {
	
	public Collection<JockeyDTO> buscarTodos();
	
	public JockeyDTO buscarPorId(Long id) throws EntidadInexistenteException;
	
	public Long guardar(JockeyDTO jockeyDTO);
	
	public void borrar(Long id) throws EntidadInexistenteException;
	
}
