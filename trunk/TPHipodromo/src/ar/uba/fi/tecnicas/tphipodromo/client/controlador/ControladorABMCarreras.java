package ar.uba.fi.tecnicas.tphipodromo.client.controlador;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import ar.uba.fi.tecnicas.tphipodromo.client.Mensajes;
import ar.uba.fi.tecnicas.tphipodromo.client.controlador.evento.EventoFactory;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioCarreras;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioCarrerasAsync;
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

	public void doGuadarCarrera(CarreraDTO carrera) {
		servicioCarreras.guardar(carrera, new AsyncCallback<Long>() {
			public void onFailure(Throwable caught) {
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			}
			
			public void onSuccess(Long result) {
				doActualizarListadoCarrera();
				notifyObservers(EventoFactory.getMostrarMensaje(), mensajes.carreraGuardada(), result);
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
		/*
		List<ParticipanteDTO> participantes = new LinkedList<ParticipanteDTO>(); 
		notifyObservers(EventoFactory.getMostrarParticipantes(), carrera, participantes);
		*/
	}
	
	public void doEditarParticipantes(final CarreraDTO carrera) {
		servicioParticipantes.buscarPorCarrera(carrera, new AsyncCallback<Collection<ParticipanteDTO>>() {
			public void onFailure(Throwable caught) {
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			}
			
			public void onSuccess(Collection<ParticipanteDTO> result) {
				notifyObservers(EventoFactory.getEditarParticipantes(), carrera, result, new LinkedList<JockeyDTO>(), new LinkedList<CaballoDTO>());
			}
		});
		/*
		List<JockeyDTO> jockeys = new LinkedList<JockeyDTO>();
		JockeyDTO jockey = new JockeyDTO();
		
		for(int i = 0; i< 10; i++) {
			jockey = new JockeyDTO();
			jockey.setNombre("Juan " + i);
			jockey.setPeso(new Double(40 + i));
			jockey.setId(new Long(i));
			jockeys.add(jockey);
		}
		
		List<CaballoDTO> caballos = new LinkedList<CaballoDTO>();
		for(int i = 0; i< 10; i++) {
			CaballoDTO caballo = new CaballoDTO();
			caballo.setNombre("Caballo " + i);
			caballo.setId(new Long(i));
			caballos.add(caballo);
		}
		
		
		List<ParticipanteDTO> participantes = new LinkedList<ParticipanteDTO>();
		ParticipanteDTO participante = new ParticipanteDTO();
		
		for(int i = 0; i< 5; i++) {
			participante = new ParticipanteDTO();
			participante.setCaballoDTO(caballos.get(i));
			participante.setJockeyDTO(jockeys.get(i));
			participante.setNroParticipante(i);
			participante.setEstado("Corriendo");
			participantes.add(participante);
		}
		
		
		notifyObservers(EventoFactory.getEditarParticipantes(), carrera, participantes, jockeys, caballos);
		*/
	}

}
