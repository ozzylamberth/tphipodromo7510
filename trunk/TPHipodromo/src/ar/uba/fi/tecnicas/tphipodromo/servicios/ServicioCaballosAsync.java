package ar.uba.fi.tecnicas.tphipodromo.servicios;

import java.util.Collection;

import com.google.gwt.user.client.rpc.AsyncCallback;

import ar.uba.fi.tecnicas.tphipodromo.servicios.dto.CaballoDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dto.DetalleCaballoDTO;

public interface ServicioCaballosAsync {

	public void buscar(AsyncCallback<Collection<CaballoDTO>> callback);
	
	public void buscar(String nombre, AsyncCallback<DetalleCaballoDTO> callback);
	
}
