package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorABMJockey;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.Vista;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.CampoDouble;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.CampoString;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.Formulario;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.FormularioListener;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.JockeyDTO;

public class VistaJockeyGWT extends Vista implements FormularioListener {

	private ControladorABMJockey ctrlABMJockey;
	
	private JockeyDTO jockeyMostrado;
	
	private Formulario formulario;
	
	public VistaJockeyGWT(ControladorABMJockey ctrlABMJockey) {
		this.ctrlABMJockey = ctrlABMJockey;
		
		this.formulario = new Formulario(this);
		
		this.formulario.setText("Jockey");
		
		this.formulario.add("nombre", "Nombre", new CampoString(true));
		this.formulario.add("apellido", "Apellido", new CampoString(true));
		this.formulario.add("peso", "Peso", new CampoDouble(true));
		
	}
	
	@Override
	public void mostrar() {
		super.mostrar();
		formulario.center();
		formulario.show();
	}

	@Override
	public void ocultar() {
		super.ocultar();
		formulario.hide();
	}

	public void onMostrarJockey(JockeyDTO jockeyDTO, Boolean editable) {
		super.onMostrarJockey(jockeyDTO, editable);
		this.jockeyMostrado = jockeyDTO;
		if(jockeyMostrado == null) {
			jockeyMostrado = new JockeyDTO();
		}
		this.formulario.setEditable(editable);
		this.formulario.setString("nombre", jockeyMostrado.getNombre());
		this.formulario.setString("apellido", jockeyMostrado.getApellido());
		this.formulario.setDouble("peso", jockeyMostrado.getPeso());
		
		this.mostrar();
		
	}

	public void onGuardar() {
		jockeyMostrado.setNombre(formulario.getString("nombre"));
		jockeyMostrado.setPeso(formulario.getDouble("peso"));
		jockeyMostrado.setApellido(formulario.getString("apellido"));
		
		ctrlABMJockey.doGuardarDatos(jockeyMostrado);
	}

}
