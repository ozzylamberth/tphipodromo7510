package ar.com.jgrande.client.vista.impl;

import ar.com.jgrande.client.controlador.ControladorPrincipal;
import ar.com.jgrande.client.vista.PantallaPrincipal;

import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class PantallaPrincipalGWT extends VistaGWT implements PantallaPrincipal {

	private ControladorPrincipal controlador;
	private VerticalPanel panelContendor = new VerticalPanel();
	private PanelPrincipal panelPrincipal;
	private PanelApuestasABM panelApuestasABM;
	private PanelCobrarApuestas panelCobrarApuestas;
	
	public PantallaPrincipalGWT(ControladorPrincipal controlador) {
		super();
		this.controlador = controlador;
		panelContendor.add(getPanelTop());
		panelContendor.add(getPanelSecundario());
		panelContendor.add(getPanelPie());
		panelContendor.setSize("100%", "100%");
	}
	
	private Panel getPanelPie() {
		return new PanelPie();
	}

	private Panel getPanelSecundario() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSize("100%", "90%");
		panel.setBorderWidth(0);
		panel.setSpacing(0);
		panel.add(this.getPanelIzquierda());
		panel.add(this.getPanelPrincipal());
		return panel;
	}

	private Widget getPanelPrincipal() {
		panelPrincipal = new PanelPrincipal();
		DecoratorPanel decorator = new DecoratorPanel();
		decorator.setWidget(panelPrincipal);
		return decorator;
	}

	private Widget getPanelIzquierda() {
		return new PanelMenu(controlador);
	}

	private Panel getPanelTop() {
		return new PanelTop();
	}

	public void onMostrar() {
		getRoot().add(panelContendor);
	}
	
	public void onMostrarApuestasAbm() {
		if(panelApuestasABM == null) {
			panelApuestasABM = new PanelApuestasABM();
		}
		panelPrincipal.mostrarPanel(panelApuestasABM);
	}
	
	public void onMostrarCobrarApuestas() {
		if(panelCobrarApuestas == null) {
			panelCobrarApuestas = new PanelCobrarApuestas();
		}
		panelPrincipal.mostrarPanel(panelCobrarApuestas);
	}
	
}
