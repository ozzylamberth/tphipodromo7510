package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class VistaABMApuestasGWT extends VistaGWT {
	
	SimplePanel panelPrincipal;

	public VistaABMApuestasGWT(HasWidgets padre) {
		super(padre);
		this.init();
	}

	private void init() {
		panelPrincipal = new SimplePanel();
		panelPrincipal.setWidth("100%");
		panelPrincipal.add(new Label("ABM de apuestas"));	
	}
	
	@Override
	public Widget toWidget() {
		return panelPrincipal;
	}
	
	@Override
	public void onMostrarABMApuestas() {
		super.onMostrarABMApuestas();
		this.mostrar();
	}
}
