package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.uba.fi.tecnicas.tphipodromo.client.Mensajes;
import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorABMCarreras;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.BotonChico;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.Listado;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.JockeyDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ParticipanteDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ResultadoDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class VistaParticipantes extends DialogBox  {
	
	private Label labelNumeroCarrera;
	
	private Label labelNombreCarrera;
	
	private ListBox listaJockeys;
	
	private ListBox listaCaballos;

	private Listado<ParticipanteDTO> listado;
	
	private Mensajes mensajes;
	
	private Button agregar;
	
	private List<ParticipanteDTO> participantes;
	
	private Map<Long, JockeyDTO> jockeysMostrados;
	
	private Map<Long, CaballoDTO> caballosMostrados;
	
	private CarreraDTO carreraMostrada;
	
	private Button botonGuardar;
	
	private Button botonCerrar;
	
	private boolean editable;
	
	private ControladorABMCarreras controladorCarreras;
	
	public VistaParticipantes(ControladorABMCarreras controladorCarreras) {
		super(false);
		mensajes = GWT.create(Mensajes.class);
		this.controladorCarreras = controladorCarreras;
		participantes = new ArrayList<ParticipanteDTO>();
		
		this.setText(mensajes.participantes());
		this.setSize("20%", "30%");
		
		labelNombreCarrera = new Label();
		labelNumeroCarrera = new Label();
		
		listaJockeys = new ListBox();
		
		listaCaballos = new ListBox();
		jockeysMostrados = new HashMap<Long, JockeyDTO>();
		caballosMostrados = new HashMap<Long, CaballoDTO>();
		botonGuardar = new Button(mensajes.guardar());
		botonCerrar = new Button(mensajes.cerrar());
		this.botonGuardar.addClickListener(new GuardarParticipantesListener());
		this.botonCerrar.addClickListener(new CerrarListener());
		
		listado = new Listado<ParticipanteDTO>() {
			public Widget[] getAtributos(ParticipanteDTO obj) {
				return new Widget[] {
						new Label(String.valueOf(obj.getNroParticipante())),
						new Label(obj.getCaballoDTO().getNombre().toString()),
						new Label(obj.getJockeyDTO().getApellido().toString()),
						new Label(obj.getEstado()),
						new Label(this.obtenerResultado(obj.getResultadoDTO())),
						new BotonChico(mensajes.eliminar(), new EliminarPrticipanteListener(obj))
				};
			}

			private String obtenerResultado(ResultadoDTO resultado) {
				if(resultado == null) {
					return "-";
				}
				return String.valueOf(resultado.getOrdenLlegada());
			}

			public String[] getTitulos() {
				return new String[] {
						mensajes.nroParticipante(), 
						mensajes.caballo(),
						mensajes.jockey(), 
						mensajes.estado(), 
						mensajes.resultado(), 
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
		
		panel.add(listado.toWidget());
		
		HorizontalPanel botonera = new HorizontalPanel();
		botonera.setSpacing(10);
		botonera.add(botonGuardar);
		botonera.add(botonCerrar);
		panel.add(botonera);
		this.add(panel);
	}
	
	private void actualizar() {
		listado.update(participantes);
		labelNombreCarrera.setText(carreraMostrada.getNombre());
		labelNumeroCarrera.setText(String.valueOf(carreraMostrada.getNumero()));
	}
	
	private void setEditable(boolean editable) {
		this.editable = editable;
		listaCaballos.setVisible(editable);
		listaJockeys.setVisible(editable);
		agregar.setVisible(editable);
		botonGuardar.setVisible(editable);
	}

	public void mostrarParticipantes(CarreraDTO carrera, Collection<ParticipanteDTO> participantes) {
		this.participantes.addAll(participantes);
		this.carreraMostrada = carrera;
		this.setEditable(false);
		this.actualizar();
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

	public void editarParticipantes(CarreraDTO carrera, Collection<ParticipanteDTO> participantes) {
		this.participantes.addAll(participantes);
		this.carreraMostrada = carrera;
		this.setEditable(true);
		this.actualizar();
		this.mostrar(this.obtenerTtulo(carrera));
	}
	
	public void actualizarCaballos(Collection<CaballoDTO> collection) {
		popularComboCaballos(collection);
	}
	
	public void actualizarJockeys(Collection<JockeyDTO> collection) {
		popularComboJockeys(collection);
	}
	
	private void popularComboCaballos(Collection<CaballoDTO> caballos) {
		caballosMostrados.clear();
		listaCaballos.clear();
		for(CaballoDTO caballo:caballos) {
			listaCaballos.addItem(caballo.getNombre(), caballo.getId().toString());
			caballosMostrados.put(caballo.getId(), caballo);
			
		}
	}
	
	private void popularComboJockeys(Collection<JockeyDTO> jockeys) {
		listaJockeys.clear();
		jockeysMostrados.clear();
		for(JockeyDTO jockey:jockeys) {
			listaJockeys.addItem(jockey.getNombre(), jockey.getId().toString());
			jockeysMostrados.put(jockey.getId(), jockey);
		}
	}
	
	private void ocultar() {
		this.hide();
	}
	
	private void agregarSeleccion() {
		ParticipanteDTO participante = new ParticipanteDTO();
		
		participante.setCaballoDTO(caballosMostrados.get(new Long(listaCaballos.getValue(listaCaballos.getSelectedIndex()))));
		listaCaballos.removeItem(listaCaballos.getSelectedIndex());
		
		participante.setJockeyDTO(jockeysMostrados.get(new Long(listaJockeys.getValue(listaJockeys.getSelectedIndex()))));
		listaJockeys.removeItem(listaJockeys.getSelectedIndex());
		
		participante.setCarreraDTO(carreraMostrada);
		participante.setNroParticipante(participantes.size() + 1);
		participante.setEstado("Creado");
		participantes.add(participante);
		actualizar();
	}
	
	private class AgregarParticipanteListener implements ClickListener {
		public void onClick(Widget sender) {
			if(listaCaballos.getSelectedIndex() != -1 && listaJockeys.getSelectedIndex() != -1)
				agregarSeleccion();
		}
		
	}

	private class EliminarPrticipanteListener implements ClickListener {
		ParticipanteDTO participante;
		
		public EliminarPrticipanteListener(ParticipanteDTO participante) {
			this.participante = participante;
		}
		
		public void onClick(Widget sender) {
			if(editable) {
				participante.setCarreraDTO(null);
				participantes.remove(participante);
				listaCaballos.addItem(participante.getCaballoDTO().getNombre(), participante.getCaballoDTO().getId().toString());
				listaJockeys.addItem(participante.getJockeyDTO().getNombre(), participante.getJockeyDTO().getId().toString());
				
				int i = 1;
				for(ParticipanteDTO participanteActual:participantes) {
					participanteActual.setNroParticipante(i);
					i++;
				}
				
				actualizar();
			}
		}
		
	}
	
	private class CerrarListener implements ClickListener {
		public void onClick(Widget sender) {
			ocultar();
		}	
	}
	
	private class GuardarParticipantesListener implements ClickListener {
		public void onClick(Widget sender) {
			controladorCarreras.doGuardarParticipantes(participantes);
			ocultar();
		}
		
	}
}
