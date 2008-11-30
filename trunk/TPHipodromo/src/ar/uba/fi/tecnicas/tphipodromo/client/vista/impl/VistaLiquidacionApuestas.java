package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.ControladorLiquidacionApuestas;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.Utils;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.CampoInteger;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets.LabelDoble;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ApuestaDTO;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class VistaLiquidacionApuestas extends VistaDefaultGWT {
	
	private CampoInteger txtNumeroTicket;
	
	private Button buttonBuscar;
	
	private ApuestaDTO apuestaMostrada;
	
	private VerticalPanel panelDatosApuesta;
	
	private ControladorLiquidacionApuestas controlador;
	
	private Button buttonLiquidar;
	
	private Label montoPagar;
	
	

	public VistaLiquidacionApuestas(HasWidgets padre, ControladorLiquidacionApuestas controlador) {
		super(padre);
		this.controlador = controlador;
		this.init();
		
	}

	private void init() {
		this.setTitulo(mensajes.cobrarApuestas());
		HorizontalPanel panel = new HorizontalPanel();
		panel.add(new Label(mensajes.nroTicket() + ":\t"));
		txtNumeroTicket = new CampoInteger(true);
		panel.add(txtNumeroTicket.toWidget());
		buttonBuscar = new Button(mensajes.buscar());
		buttonBuscar.addClickListener(new BuscarListener());
		buttonBuscar.addKeyboardListener(new BuscarListener());
		panel.add(buttonBuscar);
		panelDatosApuesta = new VerticalPanel();
		this.getCuerpo().add(panel);
		DecoratorPanel decorator = new DecoratorPanel();
		decorator.add(panelDatosApuesta);
		this.getCuerpo().add(decorator);
		buttonLiquidar = new Button(mensajes.liquidar());
		buttonLiquidar.addClickListener(new LiquidarListener());
		buttonLiquidar.setVisible(false);
		this.getCuerpo().add(buttonLiquidar);
		montoPagar = new Label();
		this.getCuerpo().add(montoPagar);
		montoPagar.setVisible(false);
	}
	
	@Override
	public void onApuestaLiquidada(Double monto) {
		super.onApuestaLiquidada(monto);
		montoPagar.setText("$\t" +monto.toString());
		montoPagar.setVisible(true);
		buttonLiquidar.setVisible(false);
	}
	
	public void onMostrarApuestaBuscada(ApuestaDTO apuestaDTO) {
		super.onMostrarApuestaBuscada(apuestaDTO);
		this.apuestaMostrada = apuestaDTO;
		this.recargarDatosApuesta();
		buttonLiquidar.setVisible(true);
	}
	
	private void recargarDatosApuesta() {
		panelDatosApuesta.clear();
		panelDatosApuesta.add(new LabelDoble(mensajes.nroTicket(), String.valueOf(apuestaMostrada.getNroTicket())));
		panelDatosApuesta.add(new LabelDoble(mensajes.tipo(), apuestaMostrada.getTipoApuesta()));
		panelDatosApuesta.add(new LabelDoble(mensajes.fecha(), Utils.getFechaFormateada(apuestaMostrada.getFechaCreacion(), Utils.FORMATO_ARG_HORA)));
		panelDatosApuesta.add(new LabelDoble(mensajes.dia(), String.valueOf(apuestaMostrada.getDiasPlazoMaxDeCobro())));
		panelDatosApuesta.add(new LabelDoble(mensajes.estado(), apuestaMostrada.getEstado()));
		panelDatosApuesta.add(new LabelDoble(mensajes.monto(), "$\t " + apuestaMostrada.getMontoApostado()));
	}

	public void onMostrarLiquidacionApuestas() {
		super.onMostrarLiquidacionApuestas();
		this.mostrar();
	}
	
	private class LiquidarListener implements ClickListener, KeyboardListener {

		private void doLiquidar() {
			controlador.doLiquidarApuesta(apuestaMostrada);
		}
		
		public void onClick(Widget sender) {
			doLiquidar();
		}

		public void onKeyDown(Widget sender, char keyCode, int modifiers) {
			doLiquidar();
		}

		public void onKeyPress(Widget sender, char keyCode, int modifiers) {}

		public void onKeyUp(Widget sender, char keyCode, int modifiers) {		}
		
	}
	
	private class BuscarListener implements ClickListener, KeyboardListener {

		private void doBuscar() {
			if(txtNumeroTicket.validar()) {
				controlador.doBuscarApuesta(new Long(txtNumeroTicket.getValor()));
				txtNumeroTicket.setInvalido(false);
			}else {
				txtNumeroTicket.setInvalido(true);
			}
		}
		
		public void onClick(Widget sender) {
			doBuscar();
		}

		public void onKeyDown(Widget sender, char keyCode, int modifiers) {
			doBuscar();
		}

		public void onKeyPress(Widget sender, char keyCode, int modifiers) {
		}

		public void onKeyUp(Widget sender, char keyCode, int modifiers) {
		}
		
	}

}
