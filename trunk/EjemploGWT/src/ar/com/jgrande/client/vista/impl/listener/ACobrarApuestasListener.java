package ar.com.jgrande.client.vista.impl.listener;

import ar.com.jgrande.client.controlador.ControladorPrincipal;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

public class ACobrarApuestasListener implements ClickListener {

	private ControladorPrincipal controlador;
	
	public ACobrarApuestasListener(ControladorPrincipal controlador) {
		this.controlador = controlador;
	}
	
	public void onClick(Widget sender) {
		controlador.doMostrarCobrarApuestas();
	}

}
