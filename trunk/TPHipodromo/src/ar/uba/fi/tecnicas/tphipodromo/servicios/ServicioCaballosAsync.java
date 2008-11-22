package ar.uba.fi.tecnicas.tphipodromo.servicios;

import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.servicios.dto.CaballoDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dto.DetalleCaballoDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ServicioCaballosAsync {

	public void buscar(AsyncCallback<Collection<CaballoDTO>> callback);
	
	public void buscar(String nombre, AsyncCallback<DetalleCaballoDTO> callback);
	
}
