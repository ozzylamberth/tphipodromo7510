package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import ar.uba.fi.tecnicas.tphipodromo.client.vista.Vista;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.VistaPrincipal;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class VistaPrincipalGWT extends VistaGWT implements VistaPrincipal {

	private DockPanel panelContenedor;
	
	private Widget contenido;
	
	public VistaPrincipalGWT(VistaMenuGWT vistaMenu) {
		super();
		this.panelContenedor = new DockPanel();		
		this.panelContenedor.setWidth("80%");
		this.panelContenedor.setHorizontalAlignment(DockPanel.ALIGN_CENTER);
		this.panelContenedor.add(new PanelTop(), DockPanel.NORTH);
		this.panelContenedor.add(new PanelPie(), DockPanel.SOUTH);
		this.panelContenedor.add(vistaMenu.getWidgetPrincipal(), DockPanel.WEST);
		this.panelContenedor.setCellWidth(vistaMenu.getWidgetPrincipal(), "20%");
	}
	
	public Widget getWidgetPrincipal() {
		return panelContenedor;
	}
	
	public void onCambiarContenido(Vista v) {
		if(contenido != null) {
			contenido.setVisible(false);
			panelContenedor.remove(contenido);
		}
		
		contenido = ((VistaGWT)v).getWidgetPrincipal();
		panelContenedor.add(contenido, DockPanel.CENTER);
		panelContenedor.setCellWidth(((VistaGWT)v).getWidgetPrincipal(), "70%");
		contenido.setVisible(true);
	}
	
	public void mostrar() {
		RootPanel.get().add(panelContenedor);
		super.mostrar();
	}
	
}
