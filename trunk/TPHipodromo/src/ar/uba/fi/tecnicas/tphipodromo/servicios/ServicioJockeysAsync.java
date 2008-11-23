package ar.uba.fi.tecnicas.tphipodromo.servicios;

import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.JockeyDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ServicioJockeysAsync {
	
	public void buscarTodos(AsyncCallback<Collection<JockeyDTO>> callback);
	
	public void buscarPorId(Long id, AsyncCallback<JockeyDTO> callback);
	
	public void guardar(JockeyDTO jockeyDTO, AsyncCallback<Long> callback);
	
	public void borrar(Long id, AsyncCallback<Void> callback);
	
}
