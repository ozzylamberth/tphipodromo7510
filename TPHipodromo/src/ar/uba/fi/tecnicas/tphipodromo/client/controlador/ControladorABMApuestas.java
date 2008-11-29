package ar.uba.fi.tecnicas.tphipodromo.client.controlador;

import java.util.Collection;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.evento.EventoFactory;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioApuestas;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioApuestasAsync;


public class ControladorABMApuestas extends Controlador {
	
	private ServicioApuestasAsync servicioApuestas = 
		(ServicioApuestasAsync) GWT.create(ServicioApuestas.class);
	
	public void doActualizarListaTiposApuesta() {
		servicioApuestas.obtenerTiposApuesta(new AsyncCallback<Collection<String>>() {
			public void onFailure(Throwable caught) {
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			};
			
			public void onSuccess(Collection<String> result) {
				notifyObservers(EventoFactory.getListaTiposApuestaActualizada(),
						result);
			};
		});
	}

}
