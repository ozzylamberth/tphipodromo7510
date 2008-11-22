package ar.uba.fi.tecnicas.tphipodromo.client.controlador;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.evento.EventoFactory;


public class ControladorPrincipal extends Controlador {
	
	public void doMostrarPrincipal() {
		notifyObservers(EventoFactory.getMostrarPrincipal());
	}
	
	public void doMostrarHome() {
		notifyObservers(EventoFactory.getMostrarHome());
	}
	
}
