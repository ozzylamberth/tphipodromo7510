package ar.uba.fi.tecnicas.tphipodromo.client.controlador;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import ar.uba.fi.tecnicas.tphipodromo.client.Mensajes;
import ar.uba.fi.tecnicas.tphipodromo.client.controlador.evento.EventoFactory;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioApuestas;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioApuestasAsync;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ApuestaDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ControladorLiquidacionApuestas extends Controlador {
	
	private Mensajes mensajes = GWT.create(Mensajes.class);
	
	private ServicioApuestasAsync servicioApuestas = GWT.create(ServicioApuestas.class);
	
	public void doBuscarApuesta(Long nroTicket) {
//		servicioApuestas.buscarPorNroTicket(nroTicket, new AsyncCallback<ApuestaDTO>() {
//			public void onFailure(Throwable caught) {
//				notifyObservers(EventoFactory.getErrorRPC(), caught);
//			}
//			public void onSuccess(ApuestaDTO result) {
//				notifyObservers(EventoFactory.getApuestaEncontrada(), result);
//			}
//			
//		});
		ApuestaDTO apuesta = new ApuestaDTO();
		apuesta.setDiasPlazoMaxDeCobro(7);
		apuesta.setEstado("Creada");
		apuesta.setFechaCreacion(new Date());
		apuesta.setMontoApostado(850.50);
		apuesta.setNroTicket(87053);
		apuesta.setTipoApuesta("Apuesta Ganador");
		List<Long> participantes = new LinkedList<Long>();
		participantes.add(new Long(1));
		apuesta.setParticipantesIds(participantes);
		notifyObservers(EventoFactory.getApuestaEncontrada(), apuesta);
	}

	public void doLiquidarApuesta(final ApuestaDTO apuesta) {
		servicioApuestas.liquidarApuesta(apuesta, new AsyncCallback<Double>() {

			public void onFailure(Throwable caught) {
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			}

			public void onSuccess(Double result) {
				notifyObservers(EventoFactory.getApuestaLiquidada(), result);
				notifyObservers(EventoFactory.getMostrarMensaje(), mensajes.apuestaLiquidadaExitosamente());
				/*
				 * Necesito recargar los datos de la apuesta
				 */
				servicioApuestas.buscarPorId(apuesta.getId(), new AsyncCallback<ApuestaDTO>() {
					public void onFailure(Throwable caught) {
						notifyObservers(EventoFactory.getErrorRPC(), caught);
					}
					public void onSuccess(ApuestaDTO result) {
						notifyObservers(EventoFactory.getApuestaEncontrada(), apuesta);
					}
					
				});
			}
			
		});

	}

}
