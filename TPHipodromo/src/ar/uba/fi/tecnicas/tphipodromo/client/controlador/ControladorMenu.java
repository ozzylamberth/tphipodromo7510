package ar.uba.fi.tecnicas.tphipodromo.client.controlador;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.evento.EventoFactory;

public class ControladorMenu extends Controlador {
	
	public void doABMCaballos() {
		notifyObservers(EventoFactory.getOcultarMensaje());
		notifyObservers(EventoFactory.getMostrarABMCaballos());
	}
	
	public void doABMApuestas() {
		notifyObservers(EventoFactory.getOcultarMensaje());
		notifyObservers(EventoFactory.getMostrarABMApuestas());
	}
	
	public void doMostrarNuevaApuesta() {
		notifyObservers(EventoFactory.getOcultarMensaje());
		notifyObservers(EventoFactory.getMostrarNuevaApuesta());
	}

	public void doABMJockeys() {
		notifyObservers(EventoFactory.getOcultarMensaje());
		notifyObservers(EventoFactory.getMostrarABMJockeys());
	}

	public void doABMCarreras() {
		notifyObservers(EventoFactory.getOcultarMensaje());
		notifyObservers(EventoFactory.getMostrarABMCarreras());
	}

	public void doMostrarLiquidacionApuestas() {
		notifyObservers(EventoFactory.getOcultarMensaje());
		notifyObservers(EventoFactory.getMostrarLiquidacionApuestas());
	}
	
	public void doMostrarCerrarInscripcion() {
		notifyObservers(EventoFactory.getOcultarMensaje());
		notifyObservers(EventoFactory.getMostrarCerrarInscripcion());
	}
	
	public void doABMResultados() {
		notifyObservers(EventoFactory.getOcultarMensaje());
		notifyObservers(EventoFactory.getMostrarABMResultados());
	}
}
