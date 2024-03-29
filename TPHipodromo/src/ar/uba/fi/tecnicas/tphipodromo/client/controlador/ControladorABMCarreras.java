package ar.uba.fi.tecnicas.tphipodromo.client.controlador;

import java.util.ArrayList;
import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.client.Mensajes;
import ar.uba.fi.tecnicas.tphipodromo.client.controlador.evento.EventoFactory;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioCaballos;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioCaballosAsync;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioCarreras;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioCarrerasAsync;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioJockeys;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioJockeysAsync;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioParticipantes;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioParticipantesAsync;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.JockeyDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ParticipanteDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ControladorABMCarreras extends Controlador {

	private Mensajes mensajes = GWT.create(Mensajes.class);
	
	private ServicioCarrerasAsync servicioCarreras = GWT.create(ServicioCarreras.class);
	
	private ServicioParticipantesAsync servicioParticipantes = GWT.create(ServicioParticipantes.class);
	
	private Collection<ParticipanteDTO> participantesCarreraEditada = new ArrayList<ParticipanteDTO>();
	
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

	public void doEditarCrrera(CarreraDTO carrera) {
		this.participantesCarreraEditada.clear();
		notifyObservers(EventoFactory.getMostrarDatosCarrera(), carrera, Boolean.TRUE);
	}

	public void doBorrarCarrera(CarreraDTO carrera) {
		servicioCarreras.borrar(carrera.getId(), new AsyncCallback<Void>() {
			public void onFailure(Throwable caught) {
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			}
			
			public void onSuccess(Void result) {
				doActualizarListadoCarrera();
				notifyObservers(EventoFactory.getCarreraBorrada());
				notifyObservers(EventoFactory.getMostrarMensaje(), mensajes.carreraBorrada());
			}
		});
	}

	public void doMostrarCarrera(CarreraDTO carrera) {
		notifyObservers(EventoFactory.getMostrarDatosCarrera(), carrera, Boolean.FALSE);
	}

	public void doCrearCarrera() {
		notifyObservers(EventoFactory.getMostrarDatosCarrera(), new CarreraDTO(), Boolean.TRUE);
	}

	public void doGuadarCarrera(final CarreraDTO carrera) {
		servicioCarreras.guardar(carrera, new AsyncCallback<Long>() {
			public void onFailure(Throwable caught) {
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			}
			
			public void onSuccess(Long result) {
				if (!participantesCarreraEditada.isEmpty()) {
					doAsignarParticipantes(carrera);
				} else {
					doActualizarListadoCarrera();
					notifyObservers(EventoFactory.getMostrarMensaje(), mensajes.carreraGuardada());
				}
			}
		});
	}
	
	public void doAsignarParticipantes(CarreraDTO carreraDTO) {
		servicioCarreras.asignarParticipantes(carreraDTO, this.participantesCarreraEditada, new AsyncCallback<Void>() {
			public void onFailure(Throwable caught) {
				participantesCarreraEditada.clear();
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			}
			
			public void onSuccess(Void v) {
				doActualizarListadoCarrera();
				notifyObservers(EventoFactory.getMostrarMensaje(), mensajes.carreraGuardada());
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
	
	public void doEditarParticipantes(final CarreraDTO carrera) {
		// si ya edite los participantes de la carrera y todavia no se guardaron los cambios
		if (!this.participantesCarreraEditada.isEmpty()) {
			notifyObservers(EventoFactory.getEditarParticipantes(), carrera, this.participantesCarreraEditada);
		} else {
			// si es la primera vez que edito los participantes de la carrera
			servicioParticipantes.buscarPorCarrera(carrera, new AsyncCallback<Collection<ParticipanteDTO>>() {
				public void onFailure(Throwable caught) {
					notifyObservers(EventoFactory.getErrorRPC(), caught);
				}
				
				public void onSuccess(Collection<ParticipanteDTO> result) {
					notifyObservers(EventoFactory.getEditarParticipantes(), carrera, result);
				}
			});
		}
	}
	
	public void doMostrarJockeysParaCarrera(CarreraDTO carrera) {
		
		servicioCarreras.buscarJockeysFueraDeCarrera(carrera, new AsyncCallback<Collection<JockeyDTO>>() {
			public void onFailure(Throwable caught) {
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			}
			public void onSuccess(Collection<JockeyDTO> result) {
				notifyObservers(EventoFactory.getMostrarJockeysParaCarrera(), result);
			}
			
		});
	}
	
	public void doMostrarCaballosParaCarrera(CarreraDTO carrera) {
		servicioCarreras.buscarCaballosFueraDeCarrera(carrera, new AsyncCallback<Collection<CaballoDTO>>() {
			public void onFailure(Throwable caught) {
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			}
			public void onSuccess(Collection<CaballoDTO> result) {
				notifyObservers(EventoFactory.getMostrarCaballosParaCarrera(), result);
			}
			
		});
	}
	
	public void doGuardarParticipantes(Collection<ParticipanteDTO> participantesCarrera) {
		this.participantesCarreraEditada.clear();
		this.participantesCarreraEditada.addAll(participantesCarrera);
	}

}
