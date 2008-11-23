package ar.uba.fi.tecnicas.tphipodromo.client.controlador;

import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.evento.EventoFactory;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioJockeys;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioJockeysAsync;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.JockeyDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ControladorABMJockey extends Controlador {

	public void doBuscarTodos() {
		ServicioJockeysAsync servicio = GWT.create(ServicioJockeys.class);
		
		servicio.buscarTodos(new AsyncCallback<Collection<JockeyDTO>>() {
			public void onFailure(Throwable caught) {
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			}
			
			public void onSuccess(Collection<JockeyDTO> result) {
				notifyObservers(EventoFactory.getListarJockeys(), result);
			}
		});		
	}

}
