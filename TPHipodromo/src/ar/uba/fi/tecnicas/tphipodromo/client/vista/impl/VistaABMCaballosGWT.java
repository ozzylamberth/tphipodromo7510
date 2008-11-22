package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorABMCaballos;
import ar.uba.fi.tecnicas.tphipodromo.client.util.Listado;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;

import com.google.gwt.user.client.ui.HasWidgets;

public class VistaABMCaballosGWT extends VistaDefaultGWT {

	private Listado<CaballoDTO> listado;
	
	private ControladorABMCaballos ctrlABMCaballos;

	public VistaABMCaballosGWT(HasWidgets padre, ControladorABMCaballos ctrlABMCaballos) {
		super(padre, "Caballos");
		
		this.ctrlABMCaballos = ctrlABMCaballos;
		
		listado = new Listado<CaballoDTO>() {
			public String[] getAtributos(CaballoDTO obj) {
				return new String[] {
						obj.getNombre(),
						obj.getEdad().toString(),
						obj.getPeso().toString()
				};
			}

			public String[] getTitulos() {
				return new String[] {"Nombre", "Edad", "Peso"};
			}
		};
		getCuerpo().add(listado);
	}
	
	@Override
	public void onMostrarABMCaballos() {
		this.listado.limpiar();
		ctrlABMCaballos.doBuscarTodos();
		super.onMostrarABMCaballos();
		this.mostrar();
	}
	
	@Override
	public void onListarCaballos(Collection<CaballoDTO> lista) {
		super.onListarCaballos(lista);
		this.listado.update(lista);
	}
}
