package ar.com.jgrande.client.vista.impl;

import ar.com.jgrande.client.controlador.ControladorPrincipal;
import ar.com.jgrande.client.vista.impl.listener.AApuestasABMListener;
import ar.com.jgrande.client.vista.impl.listener.ACobrarApuestasListener;

import com.google.gwt.user.client.ui.DecoratedStackPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PanelMenu extends DecoratedStackPanel {
	
	private ControladorPrincipal controlador;

	public PanelMenu(ControladorPrincipal controlador) {
		super();
		this.controlador = controlador;
		this.init();
	}

	private void init() {
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setSpacing(3);
		verticalPanel.add(crearLinkApostar());
		verticalPanel.add(crearLinkCobrar());
	    this.setSize("30%", "100%");
	    this.add(verticalPanel, "Apuestas");
	    VerticalPanel verticalPanelAdmin = new VerticalPanel();
	    verticalPanelAdmin.setSpacing(3);
	    verticalPanelAdmin.add(new Label("Administrar Carreras"));
	    verticalPanelAdmin.add(new Label("Administrar Caballos"));
	    verticalPanelAdmin.add(new Label("Administrar Apuestas"));
	    this.add(verticalPanelAdmin, "Administracion");
		
	}
	
	private Hyperlink crearLinkCobrar() {
		Hyperlink link = new Hyperlink("Cobrar Apuesta", "cobrar");
		link.addClickListener(new ACobrarApuestasListener(controlador));
		return link;
	}

	private Hyperlink crearLinkApostar() {
		Hyperlink link = new Hyperlink("Apostar", "apostar");
		link.addClickListener(new AApuestasABMListener(controlador));
		return link;
	}
}
