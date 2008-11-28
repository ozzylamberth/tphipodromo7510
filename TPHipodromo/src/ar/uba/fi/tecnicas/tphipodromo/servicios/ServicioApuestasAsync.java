package ar.uba.fi.tecnicas.tphipodromo.servicios;

import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ApuestaDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ServicioApuestasAsync {

	public void buscarTodos(AsyncCallback<Collection<ApuestaDTO>> callback);
	
	public void buscarPorId(Long id, AsyncCallback<ApuestaDTO> callback);
	
	public void crearApuesta(ApuestaDTO apuestaDTO, AsyncCallback<Void> callback);
	
	public void obtenerTiposApuesta(AsyncCallback<Collection<String>> callback);
	
	public void liquidarApuesta(ApuestaDTO apuestaDTO, AsyncCallback<Double> callback);
	
	public void pagarApuesta(ApuestaDTO apuestaDTO, AsyncCallback<Void> callback);
	
}
