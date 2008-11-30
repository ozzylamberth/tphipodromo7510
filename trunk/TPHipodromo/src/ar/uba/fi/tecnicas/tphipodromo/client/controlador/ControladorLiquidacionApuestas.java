package ar.uba.fi.tecnicas.tphipodromo.client.controlador;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.evento.EventoFactory;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioApuestas;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioApuestasAsync;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ApuestaDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ControladorLiquidacionApuestas extends Controlador {
	
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

}
