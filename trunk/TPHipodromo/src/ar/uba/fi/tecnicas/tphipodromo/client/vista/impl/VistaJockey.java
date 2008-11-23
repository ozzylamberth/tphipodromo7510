package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorABMJockey;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.Vista;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.PopupListener;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;

public class VistaJockey extends Vista implements PopupListener {

	private ControladorABMJockey ctrlABMJockey;
	
	private boolean editable;
	
	private DialogBox dialogo;
	
	private Grid form;
	
	private TextBox txtNombre;
	
	private TextBox txtApellido;
	
	private TextBox txtPeso;
	
	public VistaJockey(ControladorABMJockey ctrlABMJockey) {
		this.ctrlABMJockey = ctrlABMJockey;
		
		dialogo = new DialogBox(true);
		
		dialogo.setText("Jockey");
		
		form = new Grid(3, 2);
		
		form.setText(0, 0, "Nombre");
		form.setText(1, 0, "Apellido");
		form.setText(2, 0, "Peso");
		
		txtNombre = new TextBox();
		txtPeso = new TextBox();
		txtApellido = new TextBox();
		
		form.setWidget(0, 1, txtNombre);
		form.setWidget(1, 1, txtApellido);
		form.setWidget(2, 1, txtPeso);
		
		dialogo.add(form);
		dialogo.addPopupListener(this);
	}
	
	@Override
	public void mostrar() {
		super.mostrar();
		dialogo.center();
		dialogo.show();
	}
	
	@Override
	public void ocultar() {
		super.ocultar();
		dialogo.hide();
	}

	public void onPopupClosed(PopupPanel sender, boolean autoClosed) {
		this.ocultar();
		if(editable && autoClosed) {
			System.out.println("Guardar datos Jockey");
		}
	}

}
