package ar.uba.fi.tecnicas.tphipodromo.client.controlador;

import java.util.Collection;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.evento.EventoFactory;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioCarreras;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioCarrerasAsync;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;

public class ControladorInscripcion extends Controlador {
	
	private ServicioCarrerasAsync servicioCarreras =
		(ServicioCarrerasAsync) GWT.create(ServicioCarreras.class);
	
	public void doCerrarInscripcion(CarreraDTO carrera) {
		servicioCarreras.cerrarInscripcion(carrera, new AsyncCallback<Void>() {
			public void onFailure(Throwable caught) {
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			}
			
			public void onSuccess(Void result) {
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
}
