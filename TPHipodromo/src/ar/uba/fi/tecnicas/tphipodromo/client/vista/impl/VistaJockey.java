package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorABMJockey;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.Vista;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.JockeyDTO;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.PopupListener;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;

public class VistaJockey extends Vista implements PopupListener {

	private ControladorABMJockey ctrlABMJockey;
	
	private boolean editable;
	
	private JockeyDTO jockeyMostrado;
	
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
	
	private void bloquearComponentes(boolean editable) {
		txtApellido.setReadOnly(!editable);
		txtNombre.setReadOnly(!editable);
		txtPeso.setReadOnly(!editable);
		
	}

	private void cargarDatosJockey() {
		this.txtApellido.setText(jockeyMostrado.getApellido());
		this.txtNombre.setText(jockeyMostrado.getNombre());
		this.txtPeso.setText(jockeyMostrado.getPeso().toString());
	}

	@Override
	public void ocultar() {
		super.ocultar();
		dialogo.hide();
	}

	public void onPopupClosed(PopupPanel sender, boolean autoClosed) {
		this.ocultar();
		if(editable && autoClosed) {
			recargarDatosJockey();
			ctrlABMJockey.doGuardarDatos(jockeyMostrado);
			ctrlABMJockey.doBuscarTodos();
		}
	}
	
	private void recargarDatosJockey() {
		jockeyMostrado.setNombre(txtNombre.getText());
		jockeyMostrado.setApellido(txtApellido.getText());
		jockeyMostrado.setPeso(new Double(txtPeso.getText()));
	}

	public void onMostrarJockey(JockeyDTO jockeyDTO, Boolean editable) {
		super.onMostrarJockey(jockeyDTO, editable);
		this.jockeyMostrado = jockeyDTO;
		if(jockeyMostrado == null) {
			jockeyMostrado = new JockeyDTO();
		}
		this.editable = editable;
		this.cargarDatosJockey();
		this.bloquearComponentes(editable);
		this.mostrar();
		
	}

}
