package ar.com.jgrande.client.vista.impl;

import ar.com.jgrande.client.controlador.ControladorBienvenida;
import ar.com.jgrande.client.vista.VistaBienvenida;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

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
	
	private ToggleButton buttonIngresar = new ToggleButton("Ingresar");
	
	/**
	 * Constructor público. Crea los controles y paneles.
	 */
	public VistaBienvenidaGWT(final ControladorBienvenida controladorBienvenida) {
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
	    
	    pnlRaiz.add(buttonIngresar);
	    buttonIngresar.addClickListener(new ClickListener() {
		    	public void onClick(Widget sender) {
		    		controladorBienvenida.onIniciar();
		    	}
		    }
	    );
	}
	
	public void onBienvenida() {
		// History.newItem("bienvenida");
		// getRoot().clear();
		getRoot().add(pnlRaiz);
	}

	public void onIniciar() {
		getRoot().remove(pnlRaiz);
	}

}
