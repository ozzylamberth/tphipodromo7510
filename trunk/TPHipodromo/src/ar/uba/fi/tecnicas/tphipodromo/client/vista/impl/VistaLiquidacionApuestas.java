package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorLiquidacionApuestas;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.CampoInteger;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.LabelDoble;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ApuestaDTO;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class VistaLiquidacionApuestas extends VistaDefaultGWT {
	
	private CampoInteger txtNumeroTicket;
	
	private Button buttonBuscar;
	
	private ApuestaDTO apuestaMostrada;
	
	private VerticalPanel panelDatosApuesta;
	
	private ControladorLiquidacionApuestas controlador;
	

	public VistaLiquidacionApuestas(HasWidgets padre, ControladorLiquidacionApuestas controlador) {
		super(padre);
		this.controlador = controlador;
		this.init();
		
	}

	private void init() {
		this.setTitulo(mensajes.cobrarApuestas());
		HorizontalPanel panel = new HorizontalPanel();
		panel.add(new Label(mensajes.nroTicket() + ": "));
		txtNumeroTicket = new CampoInteger(true);
		panel.add(txtNumeroTicket.toWidget());
		buttonBuscar = new Button(mensajes.buscar());
		buttonBuscar.addClickListener(new BuscarClickListener());
		panel.add(buttonBuscar);
		this.getCuerpo().add(panel);
	}
	
	public void onMostrarApuestaBuscada(ApuestaDTO apuestaDTO) {
		super.onMostrarApuestaBuscada(apuestaDTO);
		this.apuestaMostrada = apuestaDTO;
		this.recargarDatosApuesta();
	}
	
	private void recargarDatosApuesta() {
		panelDatosApuesta.add(new LabelDoble(mensajes.nroTicket(), String.valueOf(apuestaMostrada.getNroTicket())));
	}

	public void onMostrarLiquidacionApuestas() {
		super.onMostrarLiquidacionApuestas();
		this.mostrar();
	}
	
	private class BuscarClickListener implements ClickListener {

		public void onClick(Widget sender) {
			if(txtNumeroTicket.validar()) {
				controlador.doBuscarApuesta(new Long(txtNumeroTicket.getValor()));
				txtNumeroTicket.setInvalido(false);
			}else {
				txtNumeroTicket.setInvalido(true);
			}
		}
		
	}

}
