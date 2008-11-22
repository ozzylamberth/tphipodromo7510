package ar.uba.fi.tecnicas.tphipodromo.servicios;

import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ServicioCaballosAsync {

	public void buscarTodos(AsyncCallback<Collection<CaballoDTO>> callback);
	
	public void buscarPorId(Long id, AsyncCallback<CaballoDTO> callback);
	
	public void guardar(CaballoDTO detalleCaballoDTO, AsyncCallback<Void> callback);
	
	public void borrar(Long id, AsyncCallback<Void> callback);
	
}
