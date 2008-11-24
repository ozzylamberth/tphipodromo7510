package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import java.util.Collection;
import java.util.LinkedList;

import com.google.gwt.core.client.GWT;

import ar.uba.fi.tecnicas.tphipodromo.client.Mensajes;
import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorABMCaballos;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.Vista;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.CampoBoolean;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.CampoDouble;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.CampoInteger;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.CampoLista;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.CampoListaItem;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.CampoString;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.Formulario;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.FormularioListener;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;

public class VistaCaballoGWT extends Vista implements FormularioListener {
	
	private ControladorABMCaballos ctrlABMCaballos;
	
	private Formulario formulario;
	
	private CaballoDTO caballo;
	
	private CampoLista campoPadre;
	
	private CampoLista campoMadre;
	
	private Mensajes mensajes = GWT.create(Mensajes.class);
	
	public VistaCaballoGWT(ControladorABMCaballos ctrlABMCaballos) {
		this.ctrlABMCaballos = ctrlABMCaballos;
		
		this.formulario = new Formulario(this);
		
		this.formulario.setText(mensajes.caballo());
		
		this.campoPadre = new CampoLista();
		this.campoMadre = new CampoLista();
		
		this.formulario.add("nombre", mensajes.nombre(), new CampoString(true));
		this.formulario.add("edad", mensajes.edad(), new CampoInteger(true));
		this.formulario.add("peso", mensajes.peso(), new CampoDouble(true));
		this.formulario.add("caballeriza", mensajes.caballeriza(), new CampoString(true));
		this.formulario.add("criador", mensajes.criador(), new CampoString(true));
		this.formulario.add("madre", mensajes.madre(), this.campoMadre);
		this.formulario.add("padre", mensajes.padre(), this.campoPadre);
		this.formulario.add("pelaje", mensajes.pelaje(), new CampoString(true));
		this.formulario.add("puraSangre", mensajes.esPuraSange(), new CampoBoolean());
		
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
	public void onListaCaballosActualizada(Collection<CaballoDTO> lista) {
		Collection<CampoListaItem> items = new LinkedList<CampoListaItem>();
		
		items.add(new CampoListaItem(mensajes.idNulo(), ""));
		
		for(final CaballoDTO caballo: lista) {
			items.add(new CampoListaItem(caballo.getId().toString(),
					caballo.getNombre()));
		}
		
		campoPadre.setItems(items);
		campoMadre.setItems(items);
	}

	@Override
	public void onMostrarDatosCaballo(CaballoDTO caballo, Boolean editable) {
		super.onMostrarDatosCaballo(caballo, editable);
		
		this.caballo = caballo;
		
		this.formulario.setEditable(editable);
		
		this.formulario.setString("nombre", caballo.getNombre());
		this.formulario.setInteger("edad", caballo.getEdad());
		this.formulario.setDouble("peso", caballo.getPeso());
		this.formulario.setString("caballeriza", caballo.getCaballeriza());
		this.formulario.setLong("madre", caballo.getMadreId());
		this.formulario.setLong("padre", caballo.getPadreId());
		this.formulario.setString("criador", caballo.getCriador());
		this.formulario.setString("pelaje", caballo.getPelaje());
		this.formulario.setBoolean("puraSangre", caballo.isPuraSangre());
		
		mostrar();
	}
	
	public void onGuardar() {
		caballo.setNombre(formulario.getString("nombre"));
		caballo.setPeso(formulario.getDouble("peso"));
		caballo.setEdad(formulario.getInteger("edad"));
		caballo.setCaballeriza(formulario.getString("caballeriza"));
		caballo.setMadreId(formulario.getLong("madre"));
		caballo.setPadreId(formulario.getLong("padre"));
		caballo.setPelaje(formulario.getString("pelaje"));
		caballo.setCriador(formulario.getString("criador"));
		caballo.setPuraSangre(formulario.getBoolean("puraSangre"));
		
		ctrlABMCaballos.doGuadarCaballo(caballo);
	}

}
