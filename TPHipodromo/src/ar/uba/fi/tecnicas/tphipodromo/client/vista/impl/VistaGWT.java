package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;


import ar.uba.fi.tecnicas.tphipodromo.client.Mensajes;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.Vista;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Provee funcionalidad común a todas las vistas implementadas en GWT.
 * Se recomienda que todas las vistas implementadas en GWT hereden de 
 * esta clase abstracta.
 * 
 * @author Juan
 *
 */
public abstract class VistaGWT implements Vista {
	
	/** Objeto con las constantes definidas para internacionalización. */
	protected static Mensajes mensajes;
	
	/**
	 * Constructor público.
	 */
	public VistaGWT() {
		mensajes = GWT.create(Mensajes.class);
	}
	
	/**
	 * Devuelve el panel raíz del browser.
	 * 
	 * @return Panel raíz del browser.
	 */
	public Panel getRoot() {
		return RootPanel.get();
	}

}
