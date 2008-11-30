package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.listener;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorMenu;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

public class AResultadosABMListener implements ClickListener {
	
	private ControladorMenu controlador;

	public AResultadosABMListener(ControladorMenu controlador) {
		this.controlador = controlador;
	}
	
	public void onClick(Widget sender) {
		controlador.doABMResultados();
	}

}
