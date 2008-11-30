package ar.uba.fi.tecnicas.tphipodromo.client.controlador;

import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.evento.EventoFactory;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioCarreras;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioCarrerasAsync;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioParticipantes;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioParticipantesAsync;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ParticipanteDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ControladorABMResultados extends Controlador {
	
	private ServicioCarrerasAsync servicioCarreras = GWT.create(ServicioCarreras.class);
	
	private ServicioParticipantesAsync servicioParticipantes = GWT.create(ServicioParticipantes.class);
	
	public void doActualizarListadoCarrera() {
		servicioCarreras.buscarTodos(new AsyncCallback<Collection<CarreraDTO>>() {
			public void onFailure(Throwable caught) {
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			}
			
			public void onSuccess(Collection<CarreraDTO> result) {
				notifyObservers(EventoFactory.getListaCarreraActualizada(), result);
			}
		});
	}
	
	public void doMostrarParticipantes(final CarreraDTO carrera) {
		servicioParticipantes.buscarPorCarrera(carrera, new AsyncCallback<Collection<ParticipanteDTO>>() {
			public void onFailure(Throwable caught) {
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			}
			
			public void onSuccess(Collection<ParticipanteDTO> result) {
				notifyObservers(EventoFactory.getMostrarParticipantes(), carrera, result);
			}
		});
	}
	
	public void doObtenerSiguientesEstadosValidos(final CarreraDTO carrera) {
		servicioCarreras.obtenerSiguientesEstadosPosibles(carrera, new AsyncCallback<Collection<String>>() {
			public void onFailure(Throwable caught) {
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			}
			
			public void onSuccess(Collection<String> result) {
				notifyObservers(EventoFactory.getSiguientesEstadosValidos(), result);
			}
		});
	}
	
	public void doCambiarEstadoCarrera(final CarreraDTO carrera, final String estado) {
		servicioCarreras.cambiarEstadoCarrera(carrera, estado, new AsyncCallback<Void>() {
			public void onFailure(Throwable caught) {
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			}

			public void onSuccess(Void result) {
				notifyObservers(EventoFactory.getCambiarEstadoCarrera(), carrera, estado);
			}
		});
	}
	
	public void doAsignarParticipantes(final CarreraDTO carrera, final Collection<ParticipanteDTO> participantes) {
		servicioCarreras.asignarParticipantes(carrera, participantes, new AsyncCallback<Void>() {
			public void onFailure(Throwable caught) {
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			}

			public void onSuccess(Void result) {
				notifyObservers(EventoFactory.getAsignarParticipantes(), carrera, participantes);
			}
		});
	}
}
