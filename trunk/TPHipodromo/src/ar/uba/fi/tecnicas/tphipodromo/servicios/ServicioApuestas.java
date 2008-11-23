package ar.uba.fi.tecnicas.tphipodromo.servicios;

import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ApuestaDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.ApuestaInvalidaException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.EntidadInexistenteException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.TipoApuestaInvalidaException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ServicioApuestas")
public interface ServicioApuestas extends RemoteService {
	
	public Collection<ApuestaDTO> buscarTodos();
	
	public ApuestaDTO buscarPorId(Long id) throws EntidadInexistenteException;
	
	public void crearApuesta(ApuestaDTO apuestaDTO) throws ApuestaInvalidaException, TipoApuestaInvalidaException;
	
	public Collection<String> obtenerTiposApuesta();

}
