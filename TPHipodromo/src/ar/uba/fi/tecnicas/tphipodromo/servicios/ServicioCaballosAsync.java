package ar.uba.fi.tecnicas.tphipodromo.servicios;

import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.DetalleCaballoDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ServicioCaballosAsync {

	public void buscarTodos(AsyncCallback<Collection<CaballoDTO>> callback);
	
	public void buscarPorId(Long id, AsyncCallback<DetalleCaballoDTO> callback);
	
	public void guardar(DetalleCaballoDTO detalleCaballoDTO, AsyncCallback<Void> callback);
	
}
