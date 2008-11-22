package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorMenu;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.listener.AApuestasABMListener;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.listener.ACaballosABMListener;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.listener.ACobrarApuestasListener;

import com.google.gwt.user.client.ui.DecoratedStackPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class VistaMenuGWT extends VistaGWT {
	
	private ControladorMenu controlador;
	
	private DecoratedStackPanel panelMenu;

	public VistaMenuGWT(HasWidgets padre, ControladorMenu controlador) {
		super(padre);
		this.controlador = controlador;
		this.init();
	}

	private void init() {
		panelMenu = new DecoratedStackPanel();
		panelMenu.setWidth("100%");
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setSpacing(3);
		verticalPanel.add(crearLinkApostar());
		verticalPanel.add(crearLinkCobrar());
	    panelMenu.add(verticalPanel, "Apuestas");
	    
	    VerticalPanel verticalPanelAdmin = new VerticalPanel();
	    verticalPanelAdmin.setSpacing(3);
	    verticalPanelAdmin.add(new Label("Administrar Carreras"));
	    verticalPanelAdmin.add(crearLinkCaballosABM());
	    verticalPanelAdmin.add(new Label("Administrar Apuestas"));
	    panelMenu.add(verticalPanelAdmin, "Administracion");
		
	}
	
	public Widget toWidget() {
		return panelMenu;
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
	
	private Hyperlink crearLinkCaballosABM() {
		Hyperlink link = new Hyperlink("Administrar Caballos", "caballos");
		link.addClickListener(new ACaballosABMListener(controlador));
		return link;
	}
	
	@Override
	public void onMostrarPrincipal() {
		super.onMostrarPrincipal();
		this.mostrar();
	}
}
