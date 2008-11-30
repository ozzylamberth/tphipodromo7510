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

	private ListBox listaCarreras;
	
	private Button ver;
	
	private ControladorABMResultados ctrlABMResultados;

	public VistaABMResultados(HasWidgets padre, ControladorABMResultados ctrlABMResultados) {
		super(padre);
		
		this.setTitulo(mensajes.resultados());
		
		this.ctrlABMResultados = ctrlABMResultados;
		
		listaCarreras = new ListBox();
		carrerasMostradas = new HashMap<Long, CarreraDTO>();
		
		listado = new Listado<ParticipanteDTO>() {
			public Widget[] getAtributos(ParticipanteDTO obj) {
				return new Widget[] {
						new Label(String.valueOf(obj.getNroParticipante())),
						new Label(obj.getCaballoDTO().getNombre().toString()),
						new Label(obj.getJockeyDTO().getApellido().toString()),
						new Label(obj.getEstado()),
						new Label(this.obtenerResultado(obj.getResultadoDTO())),
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

		ver = new Button(mensajes.ver());
		ver.addClickListener(new VerResultadosCarreraListener());
		
		VerticalPanel panel = new VerticalPanel();
		HorizontalPanel panelHorizontal = new HorizontalPanel();
		panelHorizontal.add(new Label(mensajes.carreras() + " "));
		panelHorizontal.add(listaCarreras);
		panelHorizontal.add(ver);
		panel.add(panelHorizontal);
		
		panel.add(listado.toWidget());
		
		getCuerpo().add(panel);
		getCuerpo().setCellHorizontalAlignment(ver,
				HasHorizontalAlignment.ALIGN_RIGHT);
	}
	
	@Override
	public void onMostrarABMResultados() {
	
		listado.update(participantes);
		this.ctrlABMResultados.doActualizarListaCarreras();
		super.onMostrarABMResultados();
		this.mostrar();
	}
	
	public void onListaCarrerasActualizada(Collection<CarreraDTO> lista) {

		carrerasMostradas.clear();
		listaCarreras.clear();
		
		Iterator<CarreraDTO> it = lista.iterator();
		while (it.hasNext()) {
			CarreraDTO carreraDTO = (CarreraDTO) it.next();
			
			listaCarreras.addItem(carreraDTO.getNombre(), carreraDTO.getId().toString());
			carrerasMostradas.put(carreraDTO.getId(), carreraDTO);
		}
		
		super.onListaCarrerasActualizada(lista);
	}
	
	private class VerResultadosCarreraListener implements ClickListener {
		public void onClick(Widget sender) {
			verSeleccion();
		}
	}
	
	private void verSeleccion() {
	
		CarreraDTO carreraDTO = carrerasMostradas.get(new Long(listaCarreras.getValue(listaCarreras.getSelectedIndex())));
		
		System.out.println(carreraDTO.getNombre());
		
		ctrlABMResultados.doMostrarParticipantes(carreraDTO);
	}
	
	public void onMostrarParticipantesCarrera(CarreraDTO carrera, 
			Collection<ParticipanteDTO> listaParticipantes) {
		
		participantes.clear();
		Iterator<ParticipanteDTO> it = listaParticipantes.iterator();
		while (it.hasNext()) {
			ParticipanteDTO participanteDTO = (ParticipanteDTO) it.next();
			
			participantes.add(participanteDTO);
		}
		
	}
	
}

