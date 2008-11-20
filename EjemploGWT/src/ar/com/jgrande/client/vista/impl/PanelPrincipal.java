package ar.com.jgrande.client.vista.impl;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;

public class PanelPrincipal extends SimplePanel {
	
	public PanelPrincipal() {
		super();
		this.init();
	}

	private void init() {
		this.setSize("100%", "100%");
		this.add(new Label("Vista Default"));
	}
	
	public void mostrarPanel(Panel nuevoPanel) {
		this.clear();
		this.add(nuevoPanel);
	}
}
