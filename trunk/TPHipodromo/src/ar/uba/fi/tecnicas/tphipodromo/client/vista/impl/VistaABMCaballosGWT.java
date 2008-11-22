package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorABMCaballos;
import ar.uba.fi.tecnicas.tphipodromo.client.util.BotonChico;
import ar.uba.fi.tecnicas.tphipodromo.client.util.Listado;
import ar.uba.fi.tecnicas.tphipodromo.client.util.Mensaje;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class VistaABMCaballosGWT extends VistaDefaultGWT {

	private Listado<CaballoDTO> listado;
	
	private ControladorABMCaballos ctrlABMCaballos;

	public VistaABMCaballosGWT(HasWidgets padre, ControladorABMCaballos ctrlABMCaballos) {
		super(padre, "Caballos");
		
		this.ctrlABMCaballos = ctrlABMCaballos;
		
		listado = new Listado<CaballoDTO>() {
			public Widget[] getAtributos(CaballoDTO obj) {
				return new Widget[] {
						new Label(obj.getNombre()),
						new Label(obj.getEdad().toString()),
						new Label(obj.getPeso().toString()),
						new BotonChico("Ver", new VerCaballoListener(obj)),
						new BotonChico("Editar", new EditarCaballoListener(obj)),
						new BotonChico("Borrar", new BorrarCaballoListener(obj))
				};
			}

			public String[] getTitulos() {
				return new String[] {"Nombre", "Edad", "Peso", "", "", ""};
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
	
	@Override
	public void onCaballoBorrado() {
		super.onCaballoBorrado();
		new Mensaje("Caballo", "Caballo borrado exitosamente.").mostrar();
	}
	
	private class EditarCaballoListener implements ClickListener {
		private CaballoDTO caballo;
		
		public EditarCaballoListener(CaballoDTO caballo) {
			this.caballo = caballo;
		}
		
		public void onClick(Widget sender) {
			ctrlABMCaballos.doEditarCaballo(caballo);
		}
	}
	
	private class BorrarCaballoListener implements ClickListener {
		private CaballoDTO caballo;
		
		public BorrarCaballoListener(CaballoDTO caballo) {
			this.caballo = caballo;
		}
		
		public void onClick(Widget sender) {
			if( Window.confirm("¿Está seguro que desea borrar el caballo?")) {
				ctrlABMCaballos.doBorrarCaballo(caballo);
			}
		}
	}
	
	private class VerCaballoListener implements ClickListener {
		private CaballoDTO caballo;
		
		public VerCaballoListener(CaballoDTO caballo) {
			this.caballo = caballo;
		}
		
		public void onClick(Widget sender) {
			ctrlABMCaballos.doMostrarCaballo(caballo);
		}
	}
}
