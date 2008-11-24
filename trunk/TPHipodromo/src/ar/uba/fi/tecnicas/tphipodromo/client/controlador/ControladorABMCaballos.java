package ar.uba.fi.tecnicas.tphipodromo.client.controlador;

import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.evento.EventoFactory;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioCaballos;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioCaballosAsync;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;


public class ControladorABMCaballos extends Controlador {
	
	private ServicioCaballosAsync servicioCaballos = GWT.create(ServicioCaballos.class);
	
	public void doActualizarListadoCaballos() {
		servicioCaballos.buscarTodos(new AsyncCallback<Collection<CaballoDTO>>() {
			public void onFailure(Throwable caught) {
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			}
			
			public void onSuccess(Collection<CaballoDTO> result) {
				notifyObservers(EventoFactory.getListaCaballosActualizada(), result);
			}
		});
	}
	
	public void doCrearCaballo() {
		notifyObservers(EventoFactory.getMostrarDatosCaballo(), new CaballoDTO(), Boolean.TRUE);
	}
	
	public void doEditarCaballo(CaballoDTO caballo) {
		notifyObservers(EventoFactory.getMostrarDatosCaballo(), caballo, Boolean.TRUE);
	}
	
	public void doMostrarCaballo(CaballoDTO caballo) {
		notifyObservers(EventoFactory.getMostrarDatosCaballo(), caballo, Boolean.FALSE);
	}
	
	public void doBorrarCaballo(final CaballoDTO caballo) {
		servicioCaballos.borrar(caballo.getId(), new AsyncCallback<Void>() {
			public void onFailure(Throwable caught) {
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			}
			
			public void onSuccess(Void result) {
				doActualizarListadoCaballos();
				notifyObservers(EventoFactory.getCaballoBorrado());
				notifyObservers(EventoFactory.getMostrarMensaje(),"Caballo borrado correctamente");
			}
		});
	}
	
	public void doGuadarCaballo(CaballoDTO caballo) {
		servicioCaballos.guardar(caballo, new AsyncCallback<Long>() {
			public void onFailure(Throwable caught) {
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			}
			
			public void onSuccess(Long result) {
				doActualizarListadoCaballos();
				notifyObservers(EventoFactory.getMostrarMensaje(),"Caballo guardado correctamente", result);
			}
		});
	}
	
}
