package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;

public class PanelTop extends HorizontalPanel {

	public PanelTop() {
		super();
		this.init();
	}

	private void init() {
		HorizontalPanel auxPanel1 = new HorizontalPanel();
		HorizontalPanel auxPanel2 = new HorizontalPanel();
		this.setSpacing(1);
		auxPanel1.setSize("100%", "5%");
		auxPanel2.setSpacing(5);
		this.setTitle("panelTop");
		this.setSize("100%", "5%");
		this.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
		this.add(auxPanel1);
		this.add(auxPanel2);
		
		Hyperlink home = new Hyperlink("home", false, "home");
		auxPanel2.add(home);//TODO implementar history
		
		Hyperlink logOut = new Hyperlink("log Out", false, "logOut");
		auxPanel2.add(logOut);//TODO deslogear
	}
}
