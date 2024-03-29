package ar.com.jgrande.client.controlador;

import ar.com.jgrande.client.controlador.eventos.EventoPrincipal;
import ar.com.jgrande.client.vista.PantallaPrincipal;

public class ControladorPrincipal extends Controlador<PantallaPrincipal, EventoPrincipal> {

	public ControladorPrincipal() {
		
	}
	
	public void doPrincipal() {
		this.notifyObservers(EventoPrincipal.MOSTRAR);
	}
	
	public void doMostrarApuestasABM() {
		this.notifyObservers(EventoPrincipal.APUESTAS_ABM);
	}
	
	public void doMostrarCobrarApuestas() {
		this.notifyObservers(EventoPrincipal.COBRAR_APUESTAS);
	}
	
}
