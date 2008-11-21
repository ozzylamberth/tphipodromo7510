package ar.uba.fi.tecnicas.tphipodromo.client.controlador;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.evento.Evento;
import ar.uba.fi.tecnicas.tphipodromo.client.controlador.evento.EventoPrincipalFactory;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.VistaPrincipal;

public class ControladorPrincipal extends Controlador<VistaPrincipal, Evento<VistaPrincipal>> {

	public ControladorPrincipal() {
		
	}
	
	public void doPrincipal() {
		this.notifyObservers(EventoPrincipalFactory.getEventoMostrar());
	}
	
	public void doMostrarApuestasABM() {
		this.notifyObservers(EventoPrincipalFactory.getEventoApuestasABM());
	}
	
	public void doMostrarCobrarApuestas() {
		this.notifyObservers(EventoPrincipalFactory.getEventoCobrarApuestas());
	}
	
	public void doMostrarCaballosABM() {
		this.notifyObservers(EventoPrincipalFactory.getEventoCaballosABM());
	}
	
}
