package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.client.Mensajes;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.BotonChico;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.Listado;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ParticipanteDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
/**
 * 
 * NO esta terminado, por eso no funciona
 *
 */
public class VistaParticipantes extends DialogBox {

	private Listado<ParticipanteDTO> listado;
	
	private Mensajes mensajes;
	
	public VistaParticipantes() {
		super();
		mensajes = GWT.create(Mensajes.class);
		listado = new Listado<ParticipanteDTO>() {
			public Widget[] getAtributos(ParticipanteDTO obj) {
				return new Widget[] {
						// Te comente las siguientes lineas para que no haya errores de compilacion.
						// Esto se produjo porque ya cambie el participante DTO para que tenga todos
						// los otros DTOs adentro, como me pediste
//						new Label(obj.getCaballoId().toString()),
//						new Label(obj.getJockeyId().toString()),
						new Label(String.valueOf(obj.getNroParticipante())),
						new Label(obj.getEstado()),
//						new Label(obj.getResultadoId().toString()),
						new BotonChico(mensajes.eliminar(), new EliminarPrticipanteListener(obj))
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
	}
	
	public void mostrarParticipantes(CarreraDTO carrera, Collection<ParticipanteDTO> participantes) {
		listado.update(participantes);
		
	}
	
	private class EliminarPrticipanteListener implements ClickListener {

		public EliminarPrticipanteListener(ParticipanteDTO participante) {
			
		}
		
		public void onClick(Widget sender) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
