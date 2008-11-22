package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import ar.uba.fi.tecnicas.tphipodromo.client.vista.VistaHome;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class VistaHomeGWT extends VistaGWT implements VistaHome {
	
	private Label titulo = new Label("Bienvenido");

	public Widget getWidgetPrincipal() {
		return titulo;
	}

}
