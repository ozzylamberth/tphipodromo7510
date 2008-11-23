package ar.uba.fi.tecnicas.tphipodromo.client.controlador;

import java.util.Collection;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.evento.EventoFactory;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioCaballos;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioCaballosAsync;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;


public class ControladorABMCaballos extends Controlador {
	
	public void doBuscarTodos() {
		ServicioCaballosAsync servicioCaballos = GWT.create(ServicioCaballos.class);
		
		servicioCaballos.buscarTodos(new AsyncCallback<Collection<CaballoDTO>>() {
			public void onFailure(Throwable caught) {
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			}
			
			public void onSuccess(Collection<CaballoDTO> result) {
				notifyObservers(EventoFactory.getListarCaballos(), result);
			}
		});
	}
	
	public void doEditarCaballo(CaballoDTO caballo) {
		notifyObservers(EventoFactory.getMostrarDatosCaballo(), caballo, Boolean.TRUE);
	}
	
	public void doMostrarCaballo(CaballoDTO caballo) {
		notifyObservers(EventoFactory.getMostrarDatosCaballo(), caballo, Boolean.FALSE);
	}
	
	public void doBorrarCaballo(CaballoDTO caballo) {
		notifyObservers(EventoFactory.getCaballoBorrado());
	}

}