package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class VistaDefaultGWT extends VistaGWT {
	
	private VerticalPanel cuerpo;
	
	private Label titulo;

	public VistaDefaultGWT(HasWidgets padre) {
		super(padre);
		
		cuerpo = new VerticalPanel();
		cuerpo.setSpacing(20);
		
		titulo = new Label();
		titulo.setStyleName("titulo");
		cuerpo.add(titulo);
		cuerpo.setCellHorizontalAlignment(titulo, VerticalPanel.ALIGN_CENTER);
	}
	
	protected VerticalPanel getCuerpo() {
		return cuerpo;
	}
	
	public void setTitulo(String s) {
		this.titulo.setText(s);
	}
	
	public final Widget toWidget() {
		return cuerpo;
	}

}
