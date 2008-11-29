package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.client.Mensajes;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.BotonChico;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.Listado;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.JockeyDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ParticipanteDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
/**
 * 
 * NO esta terminado, por eso no funciona
 *
 */
public class VistaParticipantes extends DialogBox {
	
	private Label labelNumeroCarrera;
	
	private Label labelNombreCarrera;
	
	private ListBox listaJockeys;
	
	private ListBox listaCaballos;

	private Listado<ParticipanteDTO> listado;
	
	private Mensajes mensajes;
	
	private Button agregar;
	
	private Collection<ParticipanteDTO> participantes;
	
	private Collection<JockeyDTO> jockeyMostrados;
	
	private Collection<CaballoDTO> caballosMostrados;
	
	private CarreraDTO carreraMostrada;
	
	public VistaParticipantes() {
		super(true);
		mensajes = GWT.create(Mensajes.class);
		
		this.setText(mensajes.participantes());
		
		labelNombreCarrera = new Label();
		labelNumeroCarrera = new Label();
		
		listaJockeys = new ListBox();
		
		listaCaballos = new ListBox();
		
		listado = new Listado<ParticipanteDTO>() {
			public Widget[] getAtributos(ParticipanteDTO obj) {
				return new Widget[] {
						new Label(obj.getCaballoDTO().getNombre().toString()),
						new Label(obj.getJockeyDTO().getApellido().toString()),
						new Label(String.valueOf(obj.getNroParticipante())),
						new Label(obj.getEstado()),
//						new Label(String.valueOf(obj.getResultadoDTO().getOrdenLlegada())),
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
		
		agregar = new Button(mensajes.agregar());
		agregar.addClickListener(new AgregarParticipanteListener());
		
		VerticalPanel panel = new VerticalPanel();
		HorizontalPanel panelHorizontal = new HorizontalPanel();
		panelHorizontal.add(new Label(mensajes.nombre() + ":  "));
		panelHorizontal.add(labelNombreCarrera);
		panel.add(panelHorizontal);
		
		panelHorizontal = new HorizontalPanel();
		panelHorizontal.add(new Label(mensajes.numero() + ":  "));
		panelHorizontal.add(labelNumeroCarrera);
		panel.add(panelHorizontal);
		
		panelHorizontal = new HorizontalPanel();
		panelHorizontal.add(new Label(mensajes.jockeys() + " "));
		panelHorizontal.add(listaJockeys);
		panelHorizontal.add(new Label(mensajes.caballos() + " "));
		panelHorizontal.add(listaCaballos);
		panelHorizontal.add(agregar);
		panel.add(panelHorizontal);
		
		panel.add(listado);
		this.add(panel);
	}
	
	private void actualizar(boolean editable) {
		listado.update(participantes);
		labelNombreCarrera.setText(carreraMostrada.getNombre());
		labelNumeroCarrera.setText(String.valueOf(carreraMostrada.getNumero()));
		this.setEditable(editable);
		if(editable) {
			popularComboCaballos(caballosMostrados);
			popularComboJockeys(jockeyMostrados);
		}
	}
	
	private void setEditable(boolean editable) {
		listaCaballos.setVisible(editable);
		listaJockeys.setVisible(editable);
		agregar.setVisible(editable);
	}

	public void mostrarParticipantes(CarreraDTO carrera, Collection<ParticipanteDTO> participantes) {
		this.participantes = participantes;
		this.carreraMostrada = carrera;
		this.actualizar(false);
		this.mostrar(this.obtenerTtulo(carrera));
		
	}
	
	private String obtenerTtulo(CarreraDTO carrera) {
		if(carrera.getNombre() != null && !carrera.getNombre().equals("")) {
			return mensajes.participantesDe(carrera.getNombre());
		}
		return mensajes.participantes();
	}
	
	private void mostrar(String titulo) {
		this.setText(titulo);
		this.center();
		this.show();
	}

	public void editarParticipantes(CarreraDTO carrera, Collection<ParticipanteDTO> participantes, 
			Collection<CaballoDTO> caballos, Collection<JockeyDTO> jockeys) {
		this.participantes = participantes;
		this.carreraMostrada = carrera;
		this.caballosMostrados = caballos;
		this.jockeyMostrados = jockeys;
		this.actualizar(true);
		this.mostrar(this.obtenerTtulo(carrera));
	}
	
	private void popularComboCaballos(Collection<CaballoDTO> caballos) {
		listaCaballos.clear();
		for(CaballoDTO caballo:caballos) {
			listaCaballos.addItem(caballo.getNombre(), caballo.getId().toString());
		}
	}
	
	private void popularComboJockeys(Collection<JockeyDTO> jockeys) {
		listaJockeys.clear();
		for(JockeyDTO jockey:jockeys) {
			listaJockeys.addItem(jockey.getNombre(), jockey.getId().toString());
		}
	}
	
	private void agregarSeleccion() {
		System.out.println("Agrego el jockey " + listaJockeys.getValue(listaJockeys.getSelectedIndex()));
		ParticipanteDTO participante = new ParticipanteDTO();
//		participante.setCaballoId(new Long(listaCaballos.getValue(listaCaballos.getSelectedIndex())));
//		participante.setJockeyId(new Long(listaJockeys.getValue(listaJockeys.getSelectedIndex())));
//		participante.setCarreraId(carreraMostrada.getId());
//		TODO: 
//		participante.setNroParticipante(nroParticipante)
		participante.setEstado("Creado");
//		TODO sacar el caballo y el jockey de los mostrados
		participantes.add(participante);
		actualizar(agregar.isVisible());
	}
	
	private class AgregarParticipanteListener implements ClickListener {

		public void onClick(Widget sender) {
			agregarSeleccion();
		}
		
	}

	private class EliminarPrticipanteListener implements ClickListener {

		public EliminarPrticipanteListener(ParticipanteDTO participante) {
			
		}
		
		public void onClick(Widget sender) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
