package ar.uba.fi.tecnicas.tphipodromo.client.controlador;

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
		servicioApuestas.buscarPorNroTicket(nroTicket, new AsyncCallback<ApuestaDTO>() {
			public void onFailure(Throwable caught) {
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			}
			public void onSuccess(ApuestaDTO result) {
				notifyObservers(EventoFactory.getApuestaEncontrada(), result);
			}
			
		});
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

	public void doPagarApuesta(ApuestaDTO apuesta) {
		servicioApuestas.pagarApuesta(apuesta, new AsyncCallback<Void>() {
			public void onFailure(Throwable caught) {
				notifyObservers(EventoFactory.getErrorRPC(), caught);
			}
			public void onSuccess(Void result) {
				notifyObservers(EventoFactory.getMostrarMensaje(), mensajes.apuestaPagadaExitosamente());
				notifyObservers(EventoFactory.getApuestaPagada());
			}
			
		});
	}

}
