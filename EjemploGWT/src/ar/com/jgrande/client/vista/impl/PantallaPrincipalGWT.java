package ar.com.jgrande.client.vista.impl;

import ar.com.jgrande.client.controlador.ControladorPrincipal;
import ar.com.jgrande.client.vista.PantallaPrincipal;

import com.google.gwt.user.client.ui.DecoratedStackPanel;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class PantallaPrincipalGWT extends VistaGWT implements PantallaPrincipal {

	private VerticalPanel panelContendor = new VerticalPanel();
	
	public PantallaPrincipalGWT(ControladorPrincipal controlador) {
		panelContendor.add(getPanelTop());
		panelContendor.add(getPanelSecundario());
		panelContendor.add(getPanelPie());
		panelContendor.setSize("100%", "100%");
	}
	
	private Panel getPanelPie() {
		Panel panel =new SimplePanel();
		panel.setSize("100%", "5%");
		return panel;
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
		Panel panel =new SimplePanel();
		panel.setSize("100%", "100%");
		DecoratorPanel decorator = new DecoratorPanel();
		decorator.setWidget(panel);
		panel.add(new Label("Esta es la pantalla principal"));
		return decorator;
	}

	private Widget getPanelIzquierda() {
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setSpacing(3);
		verticalPanel.add(new Label("Apostar"));
		verticalPanel.add(new Label("Cobrar Apuesta"));
		DecoratedStackPanel panel = new DecoratedStackPanel();
	    panel.setSize("30%", "100%");
	    panel.add(verticalPanel, "Apuestas");
	    VerticalPanel verticalPanelAdmin = new VerticalPanel();
	    verticalPanelAdmin.setSpacing(3);
	    verticalPanelAdmin.add(new Label("Administrar Carreras"));
	    verticalPanelAdmin.add(new Label("Administrar Caballos"));
	    verticalPanelAdmin.add(new Label("Administrar Apuestas"));
	    panel.add(verticalPanelAdmin, "Administracion");
		
		return panel;
	}

	private Panel getPanelTop() {
		HorizontalPanel panelTop = new HorizontalPanel();
		HorizontalPanel auxPanel1 = new HorizontalPanel();
		HorizontalPanel auxPanel2 = new HorizontalPanel();
		panelTop.setSpacing(1);
		auxPanel1.setSize("100%", "5%");
		auxPanel2.setSpacing(5);
		panelTop.setTitle("panelTop");
		panelTop.setSize("100%", "5%");
		panelTop.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
		panelTop.add(auxPanel1);
		panelTop.add(auxPanel2);
		
		Hyperlink home = new Hyperlink("home", false, "home");
		auxPanel2.add(home);
		
		Hyperlink logOut = new Hyperlink("log Out", false, "logOut");
		auxPanel2.add(logOut);//TODO deslogear
		
		return panelTop;
	}

	public void onMostrar() {
		getRoot().add(panelContendor);
	}
}
