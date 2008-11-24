package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import com.google.gwt.core.client.GWT;

import ar.uba.fi.tecnicas.tphipodromo.client.Mensajes;
import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorABMCarreras;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.Vista;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.CampoBoolean;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.CampoDouble;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.CampoInteger;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.CampoLista;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.CampoListaItem;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.CampoString;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.Formulario;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.FormularioListener;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;

public class VistaCarreraGWT extends Vista implements FormularioListener {

	private ControladorABMCarreras ctrlABMCarreras;
	
	private Formulario formulario;
	
	private CarreraDTO carrera;
	
	private Mensajes mensajes = GWT.create(Mensajes.class);
	
	public VistaCarreraGWT(ControladorABMCarreras ctrlABMCarreras) {
		this.ctrlABMCarreras = ctrlABMCarreras;
		
		this.formulario = new Formulario(this);
		
		this.formulario.setText(mensajes.carrera());

		this.formulario.add("numero", mensajes.numero(), new CampoInteger(true));
		this.formulario.add("nombre", mensajes.nombre(), new CampoString(true));
		this.formulario.add("fechaYHora", mensajes.fechaYHora(), new CampoString(true));
		this.formulario.add("distancia", mensajes.distancia(), new CampoDouble(true));
		this.formulario.add("estado", mensajes.estado(), new CampoString(true));
	}
	
	@Override
	public void mostrar() {
		super.mostrar();
		this.formulario.center();
		this.formulario.show();
	}
	
	@Override
	public void ocultar() {
		super.ocultar();
		this.formulario.hide();
	}
	
	@Override
	public void onListaCarreraActualizada(Collection<CarreraDTO> lista) {
		Collection<CampoListaItem> items = new LinkedList<CampoListaItem>();
		
		items.add(new CampoListaItem(mensajes.idNulo(), ""));
		
		for(final CarreraDTO carrera: lista) {
			items.add(new CampoListaItem(carrera.getId().toString(),
					carrera.getNombre()));
		}
		
	}

	@Override
	public void onMostrarDatosCarrera(CarreraDTO carrera, Boolean editable) {
		super.onMostrarDatosCarrera(carrera, editable);
		
		this.carrera = carrera;
		
		this.formulario.setEditable(editable);
		
		this.formulario.setInteger("numero", carrera.getNumero());
		this.formulario.setString("nombre", carrera.getNombre());
		this.formulario.setString("fechaYHora", carrera.getFechaYHora().toString());
		this.formulario.setDouble("distancia", carrera.getDistancia());
		this.formulario.setString("estado", carrera.getEstado());
		
		mostrar();
	}
	
	public void onGuardar() {
		carrera.setNombre(formulario.getString("nombre"));
		carrera.setNumero(formulario.getInteger("numero"));
//		carrera.setFechaYHora(formulario.getInteger("edad"));
		carrera.setDistancia(formulario.getDouble("distancia"));
		carrera.setEstado(formulario.getString("estado"));
		ctrlABMCarreras.doGuadarCarrera(carrera);
	}


}
