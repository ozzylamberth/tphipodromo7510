package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorABMApuestas;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.CampoDouble;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.CampoLista;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.CampoString;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.Formulario;

import com.google.gwt.user.client.ui.HasWidgets;

public class VistaNuevaApuestaGWT extends VistaDefaultGWT {
	
	private ControladorABMApuestas ctrlApuestas;
	
	private Formulario formulario = new Formulario();
	
	private CampoLista tipo = new CampoLista();
	
	private CampoString fecha = new CampoString(true);
	
	private CampoDouble monto = new CampoDouble(true);
	
	public VistaNuevaApuestaGWT(HasWidgets padre, ControladorABMApuestas ctrlApuestas) {
		super(padre);
		
		this.ctrlApuestas = ctrlApuestas;
		
		this.setTitulo(mensajes.apostar());
		
		formulario.add("tipo", mensajes.tipo(), tipo);
		formulario.add("fecha", mensajes.fecha(), fecha);
		formulario.add("monto", mensajes.monto(), monto);
		
		getCuerpo().add(formulario.toWidget());
	}
	
	@Override
	public void onMostrarNuevaApuesta() {
		super.onMostrarNuevaApuesta();
		this.formulario.reset();
		this.ctrlApuestas.doActualizarListaTiposApuesta();
		this.mostrar();
	}
	
	@Override
	public void onListaTiposApuestaActualizada(Collection<String> lista) {
		tipo.setStrings(lista);
		super.onListaTiposApuestaActualizada(lista);
	}

}
