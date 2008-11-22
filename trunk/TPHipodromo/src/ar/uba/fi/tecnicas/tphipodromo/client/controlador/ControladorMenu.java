package ar.uba.fi.tecnicas.tphipodromo.client.controlador;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.evento.EventoFactory;

public class ControladorMenu extends Controlador {
	
	public void doABMCaballos() {
		notifyObservers(EventoFactory.getMostrarABMCaballos());
	}
	
	public void doABMApuestas() {
		notifyObservers(EventoFactory.getMostrarABMApuestas());
	}	
}
