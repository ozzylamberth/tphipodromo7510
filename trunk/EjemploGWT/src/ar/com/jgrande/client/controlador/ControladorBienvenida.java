package ar.com.jgrande.client.controlador;

import ar.com.jgrande.client.controlador.eventos.EventoBienvenida;
import ar.com.jgrande.client.vista.VistaBienvenida;

import com.google.gwt.user.client.HistoryListener;

/**
 * Controla la primera pantalla luego del login.
 * 
 * @author Juan
 *
 */
public class ControladorBienvenida extends Controlador<VistaBienvenida, EventoBienvenida> implements HistoryListener {
	
	private ControladorPrincipal ctrlPrincipal;
	
	public ControladorBienvenida(ControladorPrincipal ctrlPrincipal) {
		this.ctrlPrincipal = ctrlPrincipal;
	}
	
	/**
	 * Informa a todas las vistas que deben mostrar la pantalla de bienvenida.
	 * 
	 */
	public void doBienvenida() {
		notifyObservers(EventoBienvenida.BIENVENIDA);
	}
	
	/**
	 * A esto no le den bola! Es para manejar el historial (botones
	 * back y forward del explorador) pero todavía estoy viendo
	 * bien cómo lo incluyo.
	 */
	public void onHistoryChanged(String historyToken) {
		/*
		if(historyToken.equals("bienvenida"))
			doBienvenida();
		 */
	}
	
	public void onIniciar() {
		notifyObservers(EventoBienvenida.INICIAR);
		ctrlPrincipal.doPrincipal();
	}
}
