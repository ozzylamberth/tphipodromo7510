package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorInscripcion;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.BotonChico;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.Listado;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class VistaCerrarInscripcion extends VistaDefaultGWT {
	
	private Listado<CarreraDTO> listado;
	
	private ControladorInscripcion ctrlInscripcion;
	
	public VistaCerrarInscripcion(HasWidgets padre, ControladorInscripcion ctrlInscripcion) {
		super(padre);
		
		this.ctrlInscripcion = ctrlInscripcion;
		
		setTitulo(mensajes.cerrarInscripcion());
		
		listado = new Listado<CarreraDTO>() {
			@Override
			public Widget[] getAtributos(CarreraDTO obj) {
				return new Widget [] {
					new Label(String.valueOf(obj.getNumero())),
					new Label(obj.getNombre()),
					new BotonChico(mensajes.cerrarInscripcion(), new CerrarInscripcionListener(obj))
				};
			}
			
			@Override
			public String[] getTitulos() {
				return new String[] {
						mensajes.numero(),
						mensajes.nombre(),
						mensajes.cerrarInscripcion()
				};
			}
		};
		
		getCuerpo().add(listado.toWidget());
	}
	
	@Override
	public void onMostrarCerrarInscripcion() {
		this.listado.limpiar();
		this.listado.setCargando(true);
		ctrlInscripcion.doActualizarCarrerasEnInscripcion();
		this.mostrar();
		super.onMostrarCerrarInscripcion();
	}
	
	public void onCarrerasEnInscripcionActualizadas(Collection<CarreraDTO> lista) {
		listado.update(lista);
		listado.setCargando(false);
		super.onCarrerasEnInscripcionActualizadas(lista);
	}
	
	private class CerrarInscripcionListener implements ClickListener {
		CarreraDTO carrera;
		
		public CerrarInscripcionListener(CarreraDTO carrera) {
			this.carrera = carrera;
		}
		
		public void onClick(Widget sender) {
			ctrlInscripcion.doCerrarInscripcion(carrera);
		}
	}

}
