package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

public class PanelPie extends SimplePanel {
	
	private Label labelMensaje;

	public PanelPie() {
		super();
		init();
	}

	private void init() {
		this.setSize("100%", "5%");
		this.
		//labelMensaje = new Label("@2008 - Realizado por el Grupo 7");
		labelMensaje = new Label();
		this.add(labelMensaje);
	}

	public void mostrarMensaje(String mensaje) {
		ocultarMensaje();
		labelMensaje = new Label(mensaje);
		this.add(labelMensaje);
	    Timer t = new Timer() {
	        public void run() {
	        	ocultarMensaje();
	        }
	      };
	     t.schedule(5000);//TODO tomas: Parametrizar

	}

	public void ocultarMensaje() {
		this.remove(labelMensaje);
	}
}
