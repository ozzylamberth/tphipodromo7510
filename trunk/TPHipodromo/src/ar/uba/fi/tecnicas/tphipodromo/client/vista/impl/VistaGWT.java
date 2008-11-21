package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;


import ar.uba.fi.tecnicas.tphipodromo.client.Mensajes;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.Vista;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;

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
	protected static Mensajes mensajes;
	
	/**
	 * Constructor p�blico.
	 */
	public VistaGWT() {
		mensajes = GWT.create(Mensajes.class);
	}
	
	/**
	 * Devuelve el panel ra�z del browser.
	 * 
	 * @return Panel ra�z del browser.
	 */
	public Panel getRoot() {
		return RootPanel.get();
	}

}
