package ar.uba.fi.tecnicas.tphipodromo.servicios;

import java.util.Collection;

import com.google.gwt.user.client.rpc.AsyncCallback;

import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.DetalleCaballoDTO;

public interface ServicioCaballosAsync {

	public void buscarTodos(AsyncCallback<Collection<CaballoDTO>> callback);
	
	public void buscarCaballoPorNombre(String nombre, AsyncCallback<DetalleCaballoDTO> callback);
	
}
