package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import ar.uba.fi.tecnicas.tphipodromo.client.vista.Vista;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.TextBox;

public class VistaCaballo extends Vista {
	
	private DialogBox dialogo;
	
	private Grid form;
	
	private TextBox txtNombre;
	
	private TextBox txtEdad;
	
	private TextBox txtPeso;
	
	public VistaCaballo() {
		dialogo = new DialogBox(true);
		
		dialogo.setText("Caballo");
		
		form = new Grid(3, 2);
		
		form.setText(0, 0, "Nombre");
		form.setText(1, 0, "Edad");
		form.setText(2, 0, "Peso");
		
		txtNombre = new TextBox();
		txtEdad = new TextBox();
		txtPeso = new TextBox();
		
		form.setWidget(0, 1, txtNombre);
		form.setWidget(1, 1, txtEdad);
		form.setWidget(2, 1, txtPeso);
		
		dialogo.add(form);
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

	@Override
	public void onMostrarDatosCaballo(CaballoDTO caballo, Boolean editable) {
		super.onMostrarDatosCaballo(caballo, editable);
		
		txtNombre.setReadOnly(!editable);
		txtEdad.setReadOnly(!editable);
		txtPeso.setReadOnly(!editable);
		
		txtNombre.setText(caballo.getNombre());
		txtEdad.setText(caballo.getEdad().toString());
		txtPeso.setText(caballo.getPeso().toString());
		
		mostrar();
	}

}
