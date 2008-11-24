package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import java.util.Collection;
import java.util.LinkedList;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorABMCaballos;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.Vista;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.CampoBoolean;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.CampoInteger;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.CampoLista;
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
	
	public VistaCaballoGWT(ControladorABMCaballos ctrlABMCaballos) {
		this.ctrlABMCaballos = ctrlABMCaballos;
		
		this.formulario = new Formulario(this);
		
		this.formulario.setText("Caballo");
		
		this.campoPadre = new CampoLista();
		this.campoMadre = new CampoLista();
		
		this.formulario.add("nombre", "Nombre", new CampoString(true));
		this.formulario.add("edad", "Edad", new CampoInteger(true));
		this.formulario.add("peso", "Peso", new CampoString(true));
		this.formulario.add("caballeriza", "Caballeriza", new CampoString(true));
		this.formulario.add("criador", "Criador", new CampoString(true));
		this.formulario.add("madre", "Madre", this.campoMadre);
		this.formulario.add("padre", "Padre", this.campoPadre);
		this.formulario.add("pelaje", "Pelaje", new CampoString(true));
		this.formulario.add("puraSangre", "Pura Sangre", new CampoBoolean());
		
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
		Collection<CampoLista.Item> items = new LinkedList<CampoLista.Item>();
		
		for(final CaballoDTO caballo: lista) {
			items.add(new CampoLista.Item() {
				public String getDescripcion() {
					return caballo.getNombre();
				}
				
				public String getId() {
					return caballo.getId().toString();
				}
			});
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
		this.formulario.setString("peso", caballo.getPeso().toString());
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
		caballo.setPeso(new Double(formulario.getString("peso")));
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
