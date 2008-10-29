package ar.com.jgrande.client.vista.impl;

import ar.com.jgrande.client.vista.VistaBienvenida;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Implementación en GWT de <code>VistaBienvenida</code>
 * 
 * @see VistaBienvenida
 * @author Juan
 *
 */
public class VistaBienvenidaGWT extends VistaGWT implements
		VistaBienvenida {
	
	/** Panel principal de la vista. */
	private VerticalPanel pnlRaiz = new VerticalPanel();

	/**
	 * Constructor público. Crea los controles y paneles.
	 */
	public VistaBienvenidaGWT() {
	    Label lblTitulo = new Label(ctes.bienvenido());
	    lblTitulo.addStyleName("lbl-large");
	    
	    HorizontalPanel pnlTitulo = new HorizontalPanel();
	    pnlTitulo.setVerticalAlignment(HorizontalPanel.ALIGN_BOTTOM);
	    pnlTitulo.setSpacing(10);
	    pnlTitulo.add(lblTitulo);
	    
	    pnlRaiz.setWidth("100%");
	    pnlRaiz.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
	    pnlRaiz.setSpacing(20);
	    pnlRaiz.add(pnlTitulo);
	}
	
	public void onBienvenida() {
		// History.newItem("bienvenida");
		// getRoot().clear();
		getRoot().add(pnlRaiz);
	}
	
}
