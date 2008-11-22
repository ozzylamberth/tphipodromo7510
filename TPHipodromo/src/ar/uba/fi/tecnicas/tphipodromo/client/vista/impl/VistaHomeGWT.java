package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class VistaHomeGWT extends VistaGWT {
	
	private Label titulo = new Label("Bienvenido");
	
	public VistaHomeGWT(HasWidgets padre) {
		super(padre);
	}

	public Widget toWidget() {
		return titulo;
	}
	
	@Override
	public void onMostrarHome() {
		super.onMostrarHome();
		this.mostrar();
	}
	
	@Override
	public void onMostrarPrincipal() {
		super.onMostrarPrincipal();
		this.mostrar();
	}

}
