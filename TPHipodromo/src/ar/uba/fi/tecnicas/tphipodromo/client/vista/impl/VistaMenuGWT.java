package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorMenu;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.listener.ACerrarApuestasListener;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.listener.ACerrarInscripcionListener;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.listener.ANuevaApuestaListener;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.listener.ACaballosABMListener;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.listener.ACarrerasABMListener;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.listener.ACobrarApuestasListener;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.listener.AJockeysABMListener;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.listener.AResultadosABMListener;

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
	    panelMenu.add(verticalPanel, mensajes.apuestas());
	    
	    VerticalPanel verticalPanelAdmin = new VerticalPanel();
	    verticalPanelAdmin.setSpacing(3);
	    verticalPanelAdmin.add(crearLinkCaballosABM());
	    verticalPanelAdmin.add(crearLinkJockeyABM());
	    verticalPanelAdmin.add(crearLinkCarrerasABM());
	    verticalPanelAdmin.add(crearLinkCerrarInscripcion());
	    verticalPanelAdmin.add(crearLinkCerrarApuestas());
	    verticalPanelAdmin.add(crearLinkResultadosABM());
	    panelMenu.add(verticalPanelAdmin, mensajes.administracion());
		
	}
	
	private Hyperlink crearLinkJockeyABM() {
		Hyperlink link = new Hyperlink(mensajes.administrarJockeys(), "jockey");
		link.addClickListener(new AJockeysABMListener(controlador));
		return link;
	}

	public Widget toWidget() {
		return panelMenu;
	}
	
	private Hyperlink crearLinkCobrar() {
		Hyperlink link = new Hyperlink(mensajes.liquidar(), "cobrar");
		link.addClickListener(new ACobrarApuestasListener(controlador));
		return link;
	}

	private Hyperlink crearLinkApostar() {
		Hyperlink link = new Hyperlink(mensajes.apostar(), "apostar");
		link.addClickListener(new ANuevaApuestaListener(controlador));
		return link;
	}
	
	private Hyperlink crearLinkCaballosABM() {
		Hyperlink link = new Hyperlink(mensajes.administrarCaballos(), "caballos");
		link.addClickListener(new ACaballosABMListener(controlador));
		return link;
	}
	
	private Hyperlink crearLinkCarrerasABM() {
		Hyperlink link = new Hyperlink(mensajes.administrarCarreras(), "carreras");
		link.addClickListener(new ACarrerasABMListener(controlador));
		return link;
	}
	
	private Hyperlink crearLinkResultadosABM() {
		Hyperlink link = new Hyperlink(mensajes.cargarResultados(), "resultados");
		link.addClickListener(new AResultadosABMListener(controlador));
		return link;
	}
	
	private Hyperlink crearLinkCerrarInscripcion() {
		Hyperlink link = new Hyperlink(mensajes.cerrarInscripcion(), "cerrarInscripcion");
		link.addClickListener(new ACerrarInscripcionListener(controlador));
		return link;
	}
	
	private Hyperlink crearLinkCerrarApuestas() {
		Hyperlink link = new Hyperlink(mensajes.cerrarApuestas(), "cerrarApuestas");
		link.addClickListener(new ACerrarApuestasListener(controlador));
		return link;
	}
	
	@Override
	public void onMostrarPrincipal() {
		super.onMostrarPrincipal();
		this.mostrar();
	}
}
