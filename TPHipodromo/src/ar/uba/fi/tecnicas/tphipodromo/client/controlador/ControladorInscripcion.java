package ar.uba.fi.tecnicas.tphipodromo.client.controlador;

import java.util.Collection;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import ar.uba.fi.tecnicas.tphipodromo.client.Mensajes;
import ar.uba.fi.tecnicas.tphipodromo.client.controlador.evento.EventoFactory;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioCarreras;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioCarrerasAsync;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;

public class ControladorInscripcion extends Controlador {
	
	private Mensajes mensajes = GWT.create(Mensajes.class);
	
	private ServicioCarrerasAsync servicioCarreras =
		(ServicioCarrerasAsync) GWT.create(ServicioCarreras.class);
	
	public void doCerrarInscripcion(final CarreraDTO carrera) {
		servicioCarreras.cerrarInscripcion(carrera, new AsyncCallback<Void>() {
			public void onFailure(Throwable caught) {
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			}
			
			public void onSuccess(Void result) {
				notifyObservers(EventoFactory.getMostrarMensaje(),
						mensajes.inscripcionesCerradas(String.valueOf(carrera.getNumero())));
				doActualizarCarrerasEnInscripcion();
			}
		});
	}

	public void doActualizarCarrerasEnInscripcion() {
		servicioCarreras.buscarCarrerasEnInscripcion(
			new AsyncCallback<Collection<CarreraDTO>>() {
			public void onFailure(Throwable caught) {
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			};
			
			public void onSuccess(Collection<CarreraDTO> result) {
				notifyObservers(EventoFactory.getCarrerasEnInscripcionActualizadas(),
						result);
			};
		});
	}
	
	public void doCerrarApuestas(final CarreraDTO carrera) {
		servicioCarreras.cerrarApuestas(carrera, new AsyncCallback<Void>() {
			public void onFailure(Throwable caught) {
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			}
			
			public void onSuccess(Void result) {
				notifyObservers(EventoFactory.getMostrarMensaje(),
						mensajes.apuestasCerradas(String.valueOf(carrera.getNumero())));
				doActualizarCarrerasApostables();
			}
		});
	}

	public void doActualizarCarrerasApostables() {
		servicioCarreras.buscarCarrerasApostables(
			new AsyncCallback<Collection<CarreraDTO>>() {
			public void onFailure(Throwable caught) {
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			};
			
			public void onSuccess(Collection<CarreraDTO> result) {
				notifyObservers(EventoFactory.getCarrerasApostablesActualizadas(),
						result);
			};
		});
	}
}
