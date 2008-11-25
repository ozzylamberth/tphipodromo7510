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

	public void doGuardarDatos(JockeyDTO jockeyMostrado) {
		ServicioJockeysAsync servicio = GWT.create(ServicioJockeys.class);
		
		servicio.guardar(jockeyMostrado, new AsyncCallback<Long>() {
			public void onFailure(Throwable caught) {
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			}
			
			public void onSuccess(Long result) {
				notifyObservers(EventoFactory.getMostrarMensaje(),"Jockey guardado correctamente");
				notifyObservers(EventoFactory.getMostrarABMJockeys());
			}
		});		
	}

	public void doBorrarJockey(JockeyDTO jockey) {
		ServicioJockeysAsync servicio = GWT.create(ServicioJockeys.class);
		
		servicio.borrar(jockey.getId(), new AsyncCallback<Void>() {
			public void onFailure(Throwable caught) {
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			}
			
			public void onSuccess(Void result) {
				notifyObservers(EventoFactory.getMostrarMensaje(),"Jockey borrado correctamente", result);
				notifyObservers(EventoFactory.getMostrarABMJockeys());
			}
		});	
	}

	public void doMostrarJockey(JockeyDTO jockey) {
		this.notifyObservers(EventoFactory.getMostrarJockey(), jockey, false);
	}

	public void doEditarJockey(JockeyDTO jockey) {
		this.notifyObservers(EventoFactory.getMostrarJockey(), jockey, true);
	}

	public void doCrearJockey() {
		this.notifyObservers(EventoFactory.getMostrarJockey(), new JockeyDTO(), true);
	}


}
