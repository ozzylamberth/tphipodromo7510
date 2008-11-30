package ar.uba.fi.tecnicas.tphipodromo.client.controlador;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import ar.uba.fi.tecnicas.tphipodromo.client.Mensajes;
import ar.uba.fi.tecnicas.tphipodromo.client.controlador.evento.EventoFactory;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioApuestas;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioApuestasAsync;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioCarreras;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioCarrerasAsync;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioParticipantes;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioParticipantesAsync;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ApuestaDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ParticipanteDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;


public class ControladorABMApuestas extends Controlador {
	
	private Mensajes mensajes = GWT.create(Mensajes.class);
	
	private ServicioApuestasAsync servicioApuestas = 
		(ServicioApuestasAsync) GWT.create(ServicioApuestas.class);
	
	private ServicioCarrerasAsync servicioCarreras = 
		(ServicioCarrerasAsync) GWT.create(ServicioCarreras.class);
	
	private ServicioParticipantesAsync servicioParticipantes =
		(ServicioParticipantesAsync) GWT.create(ServicioParticipantes.class);
	
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
	
	private void doBuscarParticipantesCarreras(final Map<CarreraDTO, Collection<ParticipanteDTO>> mapa, final Iterator<CarreraDTO> it) {
		if( !it.hasNext()) {
			notifyObservers(EventoFactory.getMapaCarrerasActualizado(), mapa);
			return;
		}
		
		final CarreraDTO carrera = it.next();
		servicioParticipantes.buscarPorCarrera(carrera, 
				new AsyncCallback<Collection<ParticipanteDTO>>() {
					public void onFailure(Throwable caught) {
						notifyObservers(EventoFactory.getErrorRPC(),
								caught);
					}
			
					public void onSuccess(Collection<ParticipanteDTO> result) {
						mapa.put(carrera, result);
						doBuscarParticipantesCarreras(mapa, it);
					}
		});
		
	}
	
	public void doBuscarCarrerasPorFecha(Date fecha) {
		servicioCarreras.buscarPorFecha(fecha, new AsyncCallback<Collection<CarreraDTO>>() {
			public void onFailure(Throwable caught) {
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			};
			
			public void onSuccess(Collection<CarreraDTO> result) {
				doBuscarParticipantesCarreras(new HashMap<CarreraDTO, Collection<ParticipanteDTO>>(),
						result.iterator());
			};
		});
	}

	public void doCrearApuesta(ApuestaDTO apuesta) {
		servicioApuestas.crearApuesta(apuesta, new AsyncCallback<Long>() {
			public void onFailure(Throwable caught) {
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			}
			
			public void onSuccess(Long nroTicket) {
				notifyObservers(EventoFactory.getMostrarMensaje(),
						mensajes.apuestaCreada(nroTicket.toString()));
				
				notifyObservers(EventoFactory.getApuestaCreada());
			}
		});
	}
}
