package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorABMCarreras;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.BotonChico;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.Listado;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class VistaABMCarreras extends VistaDefaultGWT {

	private Listado<CarreraDTO> listado;
	
	private ControladorABMCarreras ctrlABMCarreras;

	public VistaABMCarreras(HasWidgets padre, ControladorABMCarreras ctrlABMCarreras) {
		super(padre);
		
		this.setTitulo(mensajes.carreras());
		
		this.ctrlABMCarreras = ctrlABMCarreras;
		
		listado = new Listado<CarreraDTO>() {
			public Widget[] getAtributos(CarreraDTO obj) {
				return new Widget[] {
						new Label(String.valueOf(obj.getNumero())),
						new Label(obj.getNombre()),
						new Label(obj.getFechaYHora().toString()),
						new Label(obj.getDistancia().toString()),
						new Label(obj.getEstado().toString()),
						new BotonChico(mensajes.ver(), new VerCarreraListener(obj)),
						new BotonChico(mensajes.editar(), new EditarCarreraListener(obj)),
						new BotonChico(mensajes.borrar(), new BorrarCarreraListener(obj))
				};
			}

			public String[] getTitulos() {
				return new String[] {
						mensajes.numero(),
						mensajes.nombre(),
						mensajes.fechaYHora(), 
						mensajes.distancia(),
						mensajes.estado(),
						"", 
						"", 
						""};
			}
		};
		
		Button botonInsertar = new Button(mensajes.nuevo(), new InsertarCarreraListener());
		
		getCuerpo().add(listado.toWidget());
		getCuerpo().add(botonInsertar);
		getCuerpo().setCellHorizontalAlignment(botonInsertar,
				HasHorizontalAlignment.ALIGN_RIGHT);
	}
	
	@Override
	public void onMostrarABMCarrera() {
		this.listado.limpiar();
		ctrlABMCarreras.doActualizarListadoCarrera();
		super.onMostrarABMCarrera();
		this.mostrar();
	}
	
	@Override
	public void onListaCarreraActualizada(Collection<CarreraDTO> lista) {
		super.onListaCarreraActualizada(lista);
		this.listado.update(lista);
	}
	
	private class EditarCarreraListener implements ClickListener {
		private CarreraDTO carrera;
		
		public EditarCarreraListener(CarreraDTO carrera) {
			this.carrera = carrera;
		}
		
		public void onClick(Widget sender) {
			ctrlABMCarreras.doEditarCrrera(carrera);
		}
	}
	
	private class BorrarCarreraListener implements ClickListener {
		private CarreraDTO carrera;
		
		public BorrarCarreraListener(CarreraDTO carrera) {
			this.carrera = carrera;
		}
		
		public void onClick(Widget sender) {
			if( Window.confirm(mensajes.confirmacionBorrarCarrera())) {
				ctrlABMCarreras.doBorrarCarrera(carrera);
			}
		}
	}
	
	private class VerCarreraListener implements ClickListener {
		private CarreraDTO carrera;
		
		public VerCarreraListener(CarreraDTO carrera) {
			this.carrera = carrera;
		}
		
		public void onClick(Widget sender) {
			ctrlABMCarreras.doMostrarCarrera(carrera);
		}
	}
	
	private class InsertarCarreraListener implements ClickListener {
		public void onClick(Widget sender) {
			ctrlABMCarreras.doCrearCarrera();
		}
	}
}
