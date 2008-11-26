package ar.uba.fi.tecnicas.tphipodromo.client.controlador;

import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.client.Mensajes;
import ar.uba.fi.tecnicas.tphipodromo.client.controlador.evento.EventoFactory;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioCarreras;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioCarrerasAsync;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioParticipantes;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioParticipantesAsync;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ParticipanteDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ControladorABMCarreras extends Controlador {

	private Mensajes mensajes = GWT.create(Mensajes.class);
	
	private ServicioCarrerasAsync servicioCaballos = GWT.create(ServicioCarreras.class);
	
	private ServicioParticipantesAsync servicioParticipantes = GWT.create(ServicioParticipantes.class);
	
	public void doActualizarListadoCarrera() {
		servicioCaballos.buscarTodos(new AsyncCallback<Collection<CarreraDTO>>() {
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
		servicioCaballos.borrar(carrera.getId(), new AsyncCallback<Void>() {
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
		servicioCaballos.guardar(carrera, new AsyncCallback<Long>() {
			public void onFailure(Throwable caught) {
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			}
			
			public void onSuccess(Long result) {
				doActualizarListadoCarrera();
				notifyObservers(EventoFactory.getMostrarMensaje(), mensajes.carreraGuardada(), result);
			}
		});
	}

	public void doMostrarParticipantes(CarreraDTO carrera) {
		notifyObservers(EventoFactory.getMostrarParticipantes(), carrera, false);
	}
	
	public void doEditarParticipantes(final CarreraDTO carrera) {
		servicioParticipantes.buscarPorCarrera(carrera, new AsyncCallback<Collection<ParticipanteDTO>>() {
			public void onFailure(Throwable caught) {
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			}
			
			public void onSuccess(Collection<ParticipanteDTO> result) {
				notifyObservers(EventoFactory.getMostrarParticipantes(), carrera, result, true);
			}
		});
	}

}
