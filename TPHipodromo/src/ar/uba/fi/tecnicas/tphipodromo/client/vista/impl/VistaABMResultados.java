package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorABMResultados;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.Listado;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ParticipanteDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ResultadoDTO;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class VistaABMResultados extends VistaDefaultGWT {

	private Listado<ParticipanteDTO> listado;
	
	private Map<Long, CarreraDTO> carrerasMostradas;
	
	private Collection<ParticipanteDTO> participantes = new ArrayList<ParticipanteDTO>();

	private ListBox listaCarreras, listaEstadosValidos;
	
	private Map<Long, ListBox> resultadosMostrados;
	
	private Button ver, editar;
	
	private Label labelEstadoActual;
	
	private ControladorABMResultados ctrlABMResultados;

	public VistaABMResultados(HasWidgets padre, ControladorABMResultados ctrlABMResultados) {
		super(padre);
		
		this.setTitulo(mensajes.resultados());
		
		this.ctrlABMResultados = ctrlABMResultados;
		
		listaCarreras = new ListBox();
		listaEstadosValidos = new ListBox();
		carrerasMostradas = new HashMap<Long, CarreraDTO>();
		labelEstadoActual = new Label();
		
		resultadosMostrados = new HashMap<Long, ListBox>();
		
		listado = new Listado<ParticipanteDTO>() {
			public Widget[] getAtributos(ParticipanteDTO obj) {
				return new Widget[] {
						new Label(String.valueOf(obj.getNroParticipante())),
						new Label(obj.getCaballoDTO().getNombre().toString()),
						new Label(obj.getJockeyDTO().getApellido().toString()),
						new Label(obj.getEstado()),
						resultadosMostrados.get(obj.getId()),
				};
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

		ver = new Button(mensajes.ver());
		ver.addClickListener(new VerResultadosCarreraListener());
		
		editar = new Button(mensajes.modificar());
		editar.addClickListener(new CambiarEstadosCarreraListener());
		
		VerticalPanel panel = new VerticalPanel();
		HorizontalPanel panelHorizontal = new HorizontalPanel();
		panelHorizontal.add(new Label(mensajes.carrera() + "   "));
		panelHorizontal.add(listaCarreras);
		panelHorizontal.add(ver);
		panel.add(panelHorizontal);
		
		panelHorizontal = new HorizontalPanel();
		panelHorizontal.add(new Label(mensajes.estado() + ":   "));
		//panelHorizontal.setSpacing(10);
		panelHorizontal.add(labelEstadoActual);
		panel.add(panelHorizontal);

		panelHorizontal = new HorizontalPanel();
		panelHorizontal.add(new Label("Siguientes estados validos" + ":   "));
		panelHorizontal.add(listaEstadosValidos);
		panelHorizontal.add(editar);
		panel.add(panelHorizontal);
		
		panelHorizontal = new HorizontalPanel();
		panelHorizontal.add(new Label(mensajes.participantes()));
		panel.add(panelHorizontal);
		
		panel.add(listado.toWidget());
		
		Button botonGuardar = new Button(mensajes.guardar(), new GuardarResultadosListener());
		panel.add(botonGuardar);
		
		panel.setCellHorizontalAlignment(ver,
				HasHorizontalAlignment.ALIGN_RIGHT);
		panel.setCellHorizontalAlignment(botonGuardar,
				HasHorizontalAlignment.ALIGN_RIGHT);
		
		getCuerpo().add(panel);
		
	}
	
	@Override
	public void onMostrarABMResultados() {
	
		limpiar();
		ctrlABMResultados.doActualizarListadoCarrera();
		super.onMostrarABMResultados();
		this.mostrar();
	}
	
	private void limpiar() {
	
		listado.limpiar();
		labelEstadoActual.setText("");
		listaEstadosValidos.clear();
	}
	
	public void onListaCarreraActualizada(Collection<CarreraDTO> lista) {

		super.onListaCarreraActualizada(lista);
		
		carrerasMostradas.clear();
		listaCarreras.clear();
		
		Iterator<CarreraDTO> it = lista.iterator();
		while (it.hasNext()) {
			CarreraDTO carreraDTO = (CarreraDTO) it.next();
			
			listaCarreras.addItem(carreraDTO.getNombre(), carreraDTO.getId().toString());
			carrerasMostradas.put(carreraDTO.getId(), carreraDTO);
		}

	}
	
	private class VerResultadosCarreraListener implements ClickListener {
		public void onClick(Widget sender) {
			verSeleccion();
		}
	}
	
	private void verSeleccion() {
	
		CarreraDTO carreraDTO = carrerasMostradas.get(new Long(listaCarreras.getValue(listaCarreras.getSelectedIndex())));
		
		ctrlABMResultados.doObtenerSiguientesEstadosValidos(carreraDTO);
		ctrlABMResultados.doMostrarParticipantes(carreraDTO);
		
		labelEstadoActual.setText(carreraDTO.getEstado());
	}
	
	public void onGetSiguientesEstadosValidos(Collection<String> lista) {
		
		listaEstadosValidos.clear();
		for (String estadoValido: lista) {
			listaEstadosValidos.addItem(estadoValido);
		}
	}
	
	public void onMostrarParticipantesCarrera(CarreraDTO carrera, 
			Collection<ParticipanteDTO> listaParticipantes) {
		
		participantes.clear();
		resultadosMostrados.clear();
		
		Iterator<ParticipanteDTO> it = listaParticipantes.iterator();
		while (it.hasNext())
		{
			ListBox resultados = new ListBox();
			for (int i = 1; i <= listaParticipantes.size(); i++)
				resultados.addItem(String.valueOf(i));
			
			ParticipanteDTO participanteDTO = (ParticipanteDTO)it.next();
			participantes.add(participanteDTO);
			
			resultadosMostrados.put(participanteDTO.getId(), resultados);
		}
				
		listado.update(participantes);
	}
	
	private class CambiarEstadosCarreraListener implements ClickListener {
		public void onClick(Widget sender) {
			
			CarreraDTO carreraDTO = carrerasMostradas.get(new Long(listaCarreras.getValue(listaCarreras.getSelectedIndex())));
			String estadoValido = listaEstadosValidos.getValue(listaEstadosValidos.getSelectedIndex());
			
			ctrlABMResultados.doCambiarEstadoCarrera(carreraDTO, estadoValido);
		}
	}
	
	
	public void onCambiarEstadoCarrera(CarreraDTO carrera, String estado) {
	
		ctrlABMResultados.doActualizarListadoCarrera();
		
		int index = 0;
		for (CarreraDTO carr: carrerasMostradas.values()) {
			if (carr.getId().equals(carrera.getId()))
			{
				listaCarreras.setSelectedIndex(index);
				break;
			}
			index++;
		}
		
		verSeleccion();
	}
	
	private class GuardarResultadosListener implements ClickListener {
		public void onClick(Widget sender) {
			
			CarreraDTO carreraDTO = carrerasMostradas.get(new Long(listaCarreras.getValue(listaCarreras.getSelectedIndex())));
			
			Collection<ParticipanteDTO> listaParticipantes = new ArrayList<ParticipanteDTO>();
			for (ParticipanteDTO part : participantes) {
			
				ListBox listResultado = resultadosMostrados.get(part.getId());
				ResultadoDTO resultadoDTO = new ResultadoDTO();
				
				Integer ordenLlegada = new Integer(listResultado.getValue(listResultado.getSelectedIndex())).intValue();
				resultadoDTO.setOrdenLlegada(ordenLlegada.intValue());
				
				//System.out.println(resultadoDTO.getOrdenLlegada());
				part.setResultadoDTO(resultadoDTO);
				listaParticipantes.add(part);
			}
			
			participantes.clear();
			participantes.addAll(listaParticipantes);
			
			ctrlABMResultados.doAsignarParticipantes(carreraDTO, participantes);
		}
	}
	
	/*
	public void onAsignarParticipantes(CarreraDTO carreraDTO, Collection<ParticipanteDTO> lista) {
	
	}
	*/
	
}

