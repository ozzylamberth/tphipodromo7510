package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.listener;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorPrincipal;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

public class ACaballosABMListener implements ClickListener {

	private ControladorPrincipal controlador;
	
	public ACaballosABMListener(ControladorPrincipal controlador) {
		this.controlador = controlador;
	}
	
	public void onClick(Widget sender) {
		controlador.doMostrarCaballosABM();
	}

}
