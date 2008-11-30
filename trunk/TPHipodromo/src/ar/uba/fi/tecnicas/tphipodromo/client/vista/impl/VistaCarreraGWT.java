package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import ar.uba.fi.tecnicas.tphipodromo.client.Mensajes;
import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorABMCarreras;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.Vista;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.CampoDouble;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.CampoFecha;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.CampoInteger;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.CampoListaItem;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.CampoNoEditableDecorator;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.CampoString;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.FormularioPopup;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.FormularioPopupListener;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.JockeyDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ParticipanteDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

public class VistaCarreraGWT extends Vista implements FormularioPopupListener {

	private ControladorABMCarreras ctrlABMCarreras;
	
	private FormularioPopup formulario;
	
	private CarreraDTO carrera;
	
	private Mensajes mensajes = GWT.create(Mensajes.class);
	
	private Button botonParticipantes;
	
	private VistaParticipantes vistaParticipantes;
	
	public VistaCarreraGWT(ControladorABMCarreras ctrlABMCarreras) {
		this.ctrlABMCarreras = ctrlABMCarreras;
		
		this.formulario = new FormularioPopup(this);
		
		this.formulario.setTitulo(mensajes.carrera());

		this.formulario.add("numero", mensajes.numero(), new CampoInteger(true));
		this.formulario.add("nombre", mensajes.nombre(), new CampoString(false));
		this.formulario.add("fechaYHora", mensajes.fechaYHora(), new CampoFecha(true));
		this.formulario.add("distancia", mensajes.distancia(), new CampoDouble(true));
		this.formulario.add("estado", mensajes.estado(), new CampoNoEditableDecorator( new CampoString(false)));
		
		this.botonParticipantes = new Button(mensajes.participantes());
		this.botonParticipantes.addClickListener(new ParticipantesClickListener());
		this.botonParticipantes.setVisible(false);
		this.formulario.getBotonera().add(this.botonParticipantes);
		this.vistaParticipantes = new VistaParticipantes(ctrlABMCarreras);
	}
	
	@Override
	public void mostrar() {
		super.mostrar();
		this.formulario.mostrar();
	}
	
	@Override
	public void ocultar() {
		super.ocultar();
		this.formulario.ocultar();
	}
	
	@Override
	public void onListaCarreraActualizada(Collection<CarreraDTO> lista) {
		Collection<CampoListaItem> items = new LinkedList<CampoListaItem>();
		
		items.add(new CampoListaItem(mensajes.idNulo(), ""));
		
		for(final CarreraDTO carrera: lista) {
			items.add(new CampoListaItem(carrera.getId().toString(),
					carrera.getNombre()));
		}
		
	}

	@Override
	public void onMostrarDatosCarrera(CarreraDTO carrera, Boolean editable) {
		super.onMostrarDatosCarrera(carrera, editable);
		
		this.carrera = carrera;
		
		this.formulario.setEditable(editable);
		
		this.formulario.setInteger("numero", carrera.getNumero());
		this.formulario.setString("nombre", carrera.getNombre());
		this.formulario.setString("fechaYHora", DateTimeFormat.getShortDateFormat().format(carrera.getFechaYHora()));
		this.formulario.setDouble("distancia", carrera.getDistancia());
		this.formulario.setString("estado", carrera.getEstado());
		if(carrera.getId() == null || carrera.getId().equals(new Long(0))) {
			botonParticipantes.setVisible(false);
		}else{
			botonParticipantes.setVisible(true);
		}
		
		mostrar();
	}
	
	public void onGuardar() {
		carrera.setNombre(formulario.getString("nombre"));
		carrera.setNumero(formulario.getInteger("numero"));
		carrera.setFechaYHora(formulario.getFecha("fechaYHora"));
		carrera.setDistancia(formulario.getDouble("distancia"));
		carrera.setEstado(formulario.getString("estado"));
		ctrlABMCarreras.doGuadarCarrera(carrera);
		botonParticipantes.setVisible(true);
	}
	
	private class ParticipantesClickListener implements ClickListener {
		public void onClick(Widget sender) {
			if(formulario.isEditable()) {
				ctrlABMCarreras.doEditarParticipantes(carrera);
				ctrlABMCarreras.doMostrarCaballosParaCarrera(carrera);
				ctrlABMCarreras.doMostrarJockeysParaCarrera(carrera);
			}else {
				ctrlABMCarreras.doMostrarParticipantes(carrera);
			}
				
		}
		
	}

	@Override
	public void onMostrarParticipantesCarrera(CarreraDTO carreraDTO,
			Collection<ParticipanteDTO> participantes) {
		vistaParticipantes.mostrarParticipantes(carrera, participantes);
	}
	
	@Override
	public void onEditarParticipantesCarrera(CarreraDTO carreraDTO,
			Collection<ParticipanteDTO> participantes) {
		vistaParticipantes.editarParticipantes(carrera, participantes);
	}
	
	@Override
	public void onMostrarCaballosParaCarrera(Collection<CaballoDTO> collection) {
		vistaParticipantes.actualizarCaballos(collection);
	}
	
	@Override
	public void onMostrarJockeysParaCarrera(Collection<JockeyDTO> collection) {
		vistaParticipantes.actualizarJockeys(collection);
	}
}
