package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;


import ar.uba.fi.tecnicas.tphipodromo.client.Mensajes;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.Vista;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Provee funcionalidad com�n a todas las vistas implementadas en GWT.
 * Se recomienda que todas las vistas implementadas en GWT hereden de 
 * esta clase abstracta.
 * 
 * @author Juan
 *
 */
public abstract class VistaGWT implements Vista {
	
	/** Objeto con las constantes definidas para internacionalizaci�n. */
	protected Mensajes mensajes;
	
	/**
	 * Constructor p�blico.
	 * 
	 * @author Juan
	 */
	public VistaGWT() {
		this.mensajes = GWT.create(Mensajes.class);
	}
	
	/**
	 * Devuelve el panel ra�z del browser.
	 * 
	 * @return Panel ra�z del browser.
	 * @author Juan
	 */
	public Panel getRoot() {
		return RootPanel.get();
	}
	
	/**
	 * Muestra el widget principal de la vista.
	 * 
	 * @author Juan
	 */
	public void mostrar() {
		getWidgetPrincipal().setVisible(true);
	}
	
	/**
	 * Oculta el widget principal de la vista.
	 * 
	 * @author Juan
	 */
	public void ocultar() {
		getWidgetPrincipal().setVisible(false);
	}
	
	/**
	 * Devuelve el widget principal de la vista. 
	 * Generalmente es un panel.
	 * 
	 * @author Juan
	 */
	public abstract Widget getWidgetPrincipal();
	
}
