package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorABMCaballos;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.BotonChico;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.Listado;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class VistaABMCaballosGWT extends VistaDefaultGWT {

	private Listado<CaballoDTO> listado;
	
	private ControladorABMCaballos ctrlABMCaballos;

	public VistaABMCaballosGWT(HasWidgets padre, ControladorABMCaballos ctrlABMCaballos) {
		super(padre);
		
		this.setTitulo(mensajes.caballos());
		
		this.ctrlABMCaballos = ctrlABMCaballos;
		
		listado = new Listado<CaballoDTO>() {
			public Widget[] getAtributos(CaballoDTO obj) {
				return new Widget[] {
						new Label(obj.getNombre()),
						new Label(obj.getEdad().toString()),
						new Label(obj.getPeso().toString()),
						new BotonChico(mensajes.ver(), new VerCaballoListener(obj)),
						new BotonChico(mensajes.editar(), new EditarCaballoListener(obj)),
						new BotonChico(mensajes.borrar(), new BorrarCaballoListener(obj))
				};
			}

			public String[] getTitulos() {
				return new String[] {mensajes.nombre(),
						mensajes.edad(), 
						mensajes.peso(), 
						"", 
						"", 
						""};
			}
		};
		
		Button botonInsertar = new Button(mensajes.nuevo(), new InsertarCaballoListener());
		
		getCuerpo().add(listado);
		getCuerpo().add(botonInsertar);
		getCuerpo().setCellHorizontalAlignment(botonInsertar,
				HasHorizontalAlignment.ALIGN_RIGHT);
	}
	
	@Override
	public void onMostrarABMCaballos() {
		this.listado.limpiar();
		ctrlABMCaballos.doActualizarListadoCaballos();
		super.onMostrarABMCaballos();
		this.mostrar();
	}
	
	@Override
	public void onListaCaballosActualizada(Collection<CaballoDTO> lista) {
		super.onListaCaballosActualizada(lista);
		this.listado.update(lista);
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
			if( Window.confirm(mensajes.confirmacionBorrarCaballo())) {
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
	
	private class InsertarCaballoListener implements ClickListener {
		public void onClick(Widget sender) {
			ctrlABMCaballos.doCrearCaballo();
		}
	}
}
