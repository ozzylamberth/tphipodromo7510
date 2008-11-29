package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorABMJockey;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.BotonChico;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.Listado;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.JockeyDTO;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class VistaABMJockeysGWT extends VistaDefaultGWT {
	
	private ControladorABMJockey ctrlABMJockey;
	private Listado<JockeyDTO> listado;

	public VistaABMJockeysGWT(HasWidgets padre, ControladorABMJockey ctrlABMJockey) {
		super(padre);
		
		this.setTitulo(mensajes.jockeys());
		
		this.ctrlABMJockey = ctrlABMJockey;
		
		listado = new Listado<JockeyDTO>() {
			public Widget[] getAtributos(JockeyDTO obj) {
				return new Widget[] {
						new Label(obj.getNombre()),
						new Label(obj.getApellido().toString()),
						new Label(obj.getPeso().toString()),
						new BotonChico("Ver", new VerJockeyListener(obj)),
						new BotonChico("Editar", new EditarJockeyListener(obj)),
						new BotonChico("Borrar", new BorrarJockeyListener(obj))
				};
			}

			public String[] getTitulos() {
				return new String[] {"Nombre", "Apellido", "Peso", "", "", ""};
			}
		};
		getCuerpo().add(listado.toWidget());
		Button botonInsertar = new Button("Insertar", new InsertarJockeyListener());
		getCuerpo().add(botonInsertar);
		getCuerpo().setCellHorizontalAlignment(botonInsertar,
				HasHorizontalAlignment.ALIGN_RIGHT);
	}
	
	@Override
	public void onMostrarABMJockeys() {
		this.listado.limpiar();
		ctrlABMJockey.doBuscarTodos();
		super.onMostrarABMJockeys();
		this.mostrar();
	}
	
	@Override
	public void onListarJockeys(Collection<JockeyDTO> lista) {
		super.onListarJockeys(lista);
		this.listado.update(lista);
	}
	
	private class EditarJockeyListener implements ClickListener {
		private JockeyDTO jockey;
		
		public EditarJockeyListener(JockeyDTO jockey) {
			this.jockey = jockey;
		}
		
		public void onClick(Widget sender) {
			ctrlABMJockey.doEditarJockey(jockey);
		}
	}
	
	private class BorrarJockeyListener implements ClickListener {
		private JockeyDTO jockey;
		
		public BorrarJockeyListener(JockeyDTO jockey) {
			this.jockey = jockey;
		}
		
		public void onClick(Widget sender) {
			if( Window.confirm("¿Está seguro que desea borrar el jockey?")) {
				ctrlABMJockey.doBorrarJockey(jockey);
			}
		}
	}
	
	private class VerJockeyListener implements ClickListener {
		private JockeyDTO jockey;
		
		public VerJockeyListener(JockeyDTO jockey) {
			this.jockey = jockey;
		}
		
		public void onClick(Widget sender) {
			ctrlABMJockey.doMostrarJockey(jockey);
		}
	}
	
	private class InsertarJockeyListener implements ClickListener {
		public void onClick(Widget sender) {
			ctrlABMJockey.doCrearJockey();
		}
	}

}
