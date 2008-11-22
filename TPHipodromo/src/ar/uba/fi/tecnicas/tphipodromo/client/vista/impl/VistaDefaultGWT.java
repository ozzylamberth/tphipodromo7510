package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class VistaDefaultGWT extends VistaGWT {
	
	private VerticalPanel cuerpo;

	public VistaDefaultGWT(HasWidgets padre, String titulo) {
		super(padre);
		
		cuerpo = new VerticalPanel();
		cuerpo.setSpacing(20);
		
		Label lblTitulo = new Label("Caballos");
		lblTitulo.setStyleName("titulo");
		cuerpo.add(lblTitulo);
		cuerpo.setCellHorizontalAlignment(lblTitulo, VerticalPanel.ALIGN_CENTER);
	}
	
	protected VerticalPanel getCuerpo() {
		return cuerpo;
	}
	
	public final Widget toWidget() {
		return cuerpo;
	}

}
