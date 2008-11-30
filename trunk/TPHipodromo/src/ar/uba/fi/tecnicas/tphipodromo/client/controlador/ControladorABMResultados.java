package ar.uba.fi.tecnicas.tphipodromo.client.controlador;

import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.evento.EventoFactory;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioApuestas;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioApuestasAsync;
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
	
	public void doActualizarListaCarreras() {
		servicioCarreras.buscarTodos(new AsyncCallback<Collection<CarreraDTO>>() {
			public void onFailure(Throwable caught) {
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			}
			
			public void onSuccess(Collection<CarreraDTO> result) {
				notifyObservers(EventoFactory.getListaCarrerasActualizada(), result);
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

}
