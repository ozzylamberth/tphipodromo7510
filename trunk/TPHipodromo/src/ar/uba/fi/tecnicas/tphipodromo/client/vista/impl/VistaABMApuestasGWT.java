package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import ar.uba.fi.tecnicas.tphipodromo.client.vista.VistaABMApuestas;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class VistaABMApuestasGWT extends VistaGWT implements VistaABMApuestas {
	
	SimplePanel panelPrincipal;

	public VistaABMApuestasGWT() {
		super();
		this.init();
	}

	private void init() {
		panelPrincipal = new SimplePanel();
		panelPrincipal.setWidth("100%");
		panelPrincipal.add(new Label("ABM de apuestas"));	
	}
	
	@Override
	public Widget getWidgetPrincipal() {
		return panelPrincipal;
	}
}
