package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorABMCaballos;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.Vista;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.PopupListener;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;

public class VistaCaballo extends Vista implements PopupListener {
	
	private ControladorABMCaballos ctrlABMCaballos;
	
	private CaballoDTO caballoMostrado;
	
	private boolean editable;
	
	private DialogBox dialogo;
	
	private Grid form;
	
	private TextBox txtNombre;
	
	private TextBox txtEdad;
	
	private TextBox txtPeso;
	
	private TextBox txtCaballeriza;
	
	private TextBox txtCriador;
	
	private TextBox txtPelaje;
	
	private TextBox txtMadre;
	
	private TextBox txtPadre;
	
	
	public VistaCaballo(ControladorABMCaballos ctrlABMCaballos) {
		this.ctrlABMCaballos = ctrlABMCaballos;
		
		dialogo = new DialogBox(true);
		
		dialogo.setText("Caballo");
		
		form = new Grid(8, 2);
		
		form.setText(0, 0, "Nombre");
		form.setText(1, 0, "Edad");
		form.setText(2, 0, "Peso");
		form.setText(3, 0, "Caballeriza");
		form.setText(4, 0, "Criador");
		form.setText(5, 0, "Madre");
		form.setText(6, 0, "Padre");
		form.setText(7, 0, "Pelaje");
		
		txtNombre = new TextBox();
		txtEdad = new TextBox();
		txtPeso = new TextBox();
		txtCaballeriza = new TextBox();
		txtCriador = new TextBox();
		txtMadre = new TextBox();
		txtPadre = new TextBox();
		txtPelaje = new TextBox();
		
		form.setWidget(0, 1, txtNombre);
		form.setWidget(1, 1, txtEdad);
		form.setWidget(2, 1, txtPeso);
		form.setWidget(3, 1, txtCaballeriza);
		form.setWidget(4, 1, txtCriador);
		form.setWidget(5, 1, txtMadre);
		form.setWidget(6, 1, txtPadre);
		form.setWidget(7, 1, txtPelaje);
		
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

	/**
	 * Carga los datos de la vista en el DTO
	 */
	private void recargarDatosCaballo() {
		caballoMostrado.setNombre(txtNombre.getText());
		caballoMostrado.setPeso(new Double(txtPeso.getText()));
		caballoMostrado.setEdad(new Integer(txtEdad.getText()));
		caballoMostrado.setCaballeriza(txtCaballeriza.getText());
		caballoMostrado.setMadre(txtMadre.getText());
		caballoMostrado.setPadre(txtPadre.getText());
		caballoMostrado.setPelaje(txtPelaje.getText());
		caballoMostrado.setCriador(txtCriador.getText());
	}

	@Override
	public void onMostrarDatosCaballo(CaballoDTO caballo, Boolean editable) {
		super.onMostrarDatosCaballo(caballo, editable);
		
		this.caballoMostrado = caballo;
		this.editable = editable;
		
		txtNombre.setReadOnly(!editable);
		txtEdad.setReadOnly(!editable);
		txtPeso.setReadOnly(!editable);
		txtCaballeriza.setReadOnly(!editable);
		txtMadre.setReadOnly(!editable);
		txtPadre.setReadOnly(!editable);
		txtCriador.setReadOnly(!editable);
		txtPelaje.setReadOnly(!editable);
		
		txtNombre.setText(caballo.getNombre());
		txtEdad.setText(caballo.getEdad().toString());
		txtPeso.setText(caballo.getPeso().toString());
		txtCaballeriza.setText(caballo.getCaballeriza());
		txtMadre.setText(caballo.getMadre());
		txtPadre.setText(caballo.getPadre());
		txtCriador.setText(caballo.getCriador());
		txtPelaje.setText(caballo.getPelaje());
		
		mostrar();
	}

	public void onPopupClosed(PopupPanel sender, boolean autoClosed) {
		this.ocultar();
		if(editable && autoClosed) {
			this.recargarDatosCaballo();
			ctrlABMCaballos.doGuadarCaballo(caballoMostrado);
			ctrlABMCaballos.doBuscarTodos();
		}
	}
	
	public void onCrearCaballo() {
		super.onCrearCaballo();
		caballoMostrado = new CaballoDTO();
		editable = true;
		mostrar();
	};

}
