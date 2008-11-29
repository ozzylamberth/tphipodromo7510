package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets;

import ar.uba.fi.tecnicas.tphipodromo.client.Mensajes;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class FormularioPopup extends Formulario implements ClickListener {
	
	private Mensajes mensajes = GWT.create(Mensajes.class);
	
	private DialogBox dialogo = new DialogBox(false);
	
	private Button botonGuardar = new Button(mensajes.guardar());
	
	private Button botonCerrar = new Button(mensajes.cerrar());
	
	private FormularioPopupListener listener;
	
	private HorizontalPanel botonera;
	
	public FormularioPopup(FormularioPopupListener l) {
		this.listener = l;
		
		this.botonGuardar.setTabIndex(98);
		this.botonCerrar.setTabIndex(99);
		
		this.botonGuardar.addClickListener(this);
		this.botonCerrar.addClickListener(this);
		
		botonera = new HorizontalPanel();
		botonera.setSpacing(10);
		botonera.add(botonGuardar);
		botonera.add(botonCerrar);
		
		VerticalPanel principal = new VerticalPanel();
		principal.add(super.toWidget());
		principal.add(botonera);
		principal.setCellHorizontalAlignment(super.toWidget(),
				VerticalPanel.ALIGN_CENTER);
		principal.setCellHorizontalAlignment(botonera,
				VerticalPanel.ALIGN_RIGHT);
		
		dialogo.add(principal);
	}
	
	public final void onClick(Widget sender) {
		if( sender.equals(botonGuardar)) {
			if( validar()) {
				this.dialogo.hide();
				listener.onGuardar();
			}
		}
		
		if(sender.equals(botonCerrar)) {
			this.dialogo.hide();
			this.reset();
		}
	}
	
	@Override
	public void setEditable(boolean editable) {
		botonGuardar.setVisible(editable);
		super.setEditable(editable);
	}

	public HorizontalPanel getBotonera() {
		return botonera;
	}

	public void setBotonera(HorizontalPanel botonera) {
		this.botonera = botonera;
	}
	
	public void setTitulo(String s) {
		this.dialogo.setText(s);
	}
	
	public void mostrar() {
		this.dialogo.center();
		this.dialogo.show();
	}
	
	public void ocultar() {
		this.dialogo.hide();
	}
	
	@Override
	public Widget toWidget() {
		return dialogo;
	}
}
