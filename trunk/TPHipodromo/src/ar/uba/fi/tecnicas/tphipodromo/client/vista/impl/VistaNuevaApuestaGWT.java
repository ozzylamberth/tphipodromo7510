package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorABMApuestas;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.CampoDouble;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.CampoFecha;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.CampoLista;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.Formulario;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.Listado;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ApuestaDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ParticipanteDTO;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class VistaNuevaApuestaGWT extends VistaDefaultGWT implements ClickListener, ChangeListener {
	
	private ControladorABMApuestas ctrlApuestas;
	
	private Formulario formulario = new Formulario();
	
	private CampoLista tipo = new CampoLista();
	
	private CampoFecha fecha = new CampoFecha(true);
	
	private CampoDouble monto = new CampoDouble(true);
	
	private Button botonApostar = new Button(mensajes.apostar());
	
	private Listado<CarreraDTO> listado;
	
	private Collection<ListBox> participantes = new LinkedList<ListBox>();
	
	private Iterator<ListBox> itParticipantes;
	
	public VistaNuevaApuestaGWT(HasWidgets padre, ControladorABMApuestas ctrlApuestas) {
		super(padre);
		
		this.ctrlApuestas = ctrlApuestas;
		
		this.setTitulo(mensajes.apostar());
		
		this.botonApostar.addClickListener(this);
		this.fecha.toWidget().addChangeListener(this);
		
		formulario.add("tipo", mensajes.tipo(), tipo);
		formulario.add("fecha", mensajes.fecha(), fecha);
		formulario.add("monto", mensajes.monto(), monto);
		
		listado = new Listado<CarreraDTO>() {
			public String[] getTitulos() {
				return new String[] {
						mensajes.hora(),
						mensajes.numero(),
						mensajes.nombre(),
						mensajes.distancia(),
						mensajes.participante()
				};
			}
			
			public Widget[] getAtributos(CarreraDTO obj) {
				return new Widget[] {
						new Label(DateTimeFormat.getShortTimeFormat().format(obj.getFechaYHora())),
						new Label(String.valueOf(obj.getNumero())),
						new Label(obj.getNombre()),
						new Label(String.valueOf(obj.getDistancia())),
						itParticipantes.next()
				};
			};
		};
		
		getCuerpo().add(formulario.toWidget());
		getCuerpo().add(listado.toWidget());
		getCuerpo().add(botonApostar);
		getCuerpo().setCellHorizontalAlignment(formulario.toWidget(),
				HasHorizontalAlignment.ALIGN_CENTER);
		getCuerpo().setCellHorizontalAlignment(botonApostar,
				HasHorizontalAlignment.ALIGN_RIGHT);
	}
	
	@Override
	public void onMostrarNuevaApuesta() {
		super.onMostrarNuevaApuesta();
		this.reset();
		this.mostrar();
	}
	
	@Override
	public void onListaTiposApuestaActualizada(Collection<String> lista) {
		tipo.setStrings(lista);
		super.onListaTiposApuestaActualizada(lista);
	}
	
	public void onClick(Widget sender) {
		if( formulario.validar()) {
			this.botonApostar.setEnabled(false);
			
			ApuestaDTO apuesta = new ApuestaDTO();
			
			apuesta.setFechaCreacion(new Date());
			apuesta.setMontoApostado(new Double(monto.getValor()));
			apuesta.setTipoApuesta(tipo.getValor());
			apuesta.setParticipantesIds(new LinkedList<Long>());
			
			for(ListBox listBox: participantes) {
				if( listBox.getSelectedIndex()>0) {
					apuesta.getParticipantesIds().add(new Long(listBox.getValue(listBox.getSelectedIndex())));
				}
			}
			
			ctrlApuestas.doCrearApuesta(apuesta);
		}
	}
	
	@Override
	public void onApuestaCreada() {
		this.reset();
		super.onApuestaCreada();
	}
	
	@Override
	public void onErrorRPC(Throwable e) {
		this.botonApostar.setEnabled(true);		
		super.onErrorRPC(e);
	}
	
	private void actualizarParticipantes(Map<CarreraDTO, Collection<ParticipanteDTO>> mapa) {
		participantes.clear();
		
		for(CarreraDTO carrera: mapa.keySet()) {
			ListBox listBox = new ListBox();
			listBox.addItem("");
			
			for(ParticipanteDTO participante: mapa.get(carrera)) {
				listBox.addItem(participante.getCaballoDTO().getNombre()
						+ " - " + participante.getJockeyDTO().getNombre(),
						participante.getId().toString());
			}
			participantes.add(listBox);
		}
		
		itParticipantes = participantes.iterator();
	}
	
	@Override
	public void onMapaCarrerasActualizado(
			Map<CarreraDTO, Collection<ParticipanteDTO>> mapa) {
		listado.setCargando(false);
		this.actualizarParticipantes(mapa);
		listado.update(mapa.keySet());
	};
	
	public void reset() {
		this.listado.limpiar();
		this.formulario.reset();
		this.botonApostar.setEnabled(true);
		this.ctrlApuestas.doActualizarListaTiposApuesta();
	}
	
	public void onChange(Widget sender) {
		if(sender.equals(fecha.toWidget())) {
			try {
				if(fecha.getDate()==null) {
					listado.limpiar();
				} else {
					ctrlApuestas.doBuscarCarrerasPorFecha(fecha.getDate());
					listado.setCargando(true);
				}
			} catch(IllegalArgumentException ex) {
				
			}
		}
	}
}
