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
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class VistaLiquidacionApuestas extends VistaDefaultGWT {
	
	private CampoInteger txtNumeroTicket;
	
	private Button buttonBuscar;
	
	private ApuestaDTO apuestaMostrada;
	
	private VerticalPanel panelDatosApuesta;
	
	private DecoratorPanel decorator;
	
	private ControladorLiquidacionApuestas controlador;
	
	private Button buttonLiquidar;
	
	private SimplePanel panelMonto;
	
	private Button buttonPagar;
	
	

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
		decorator = new DecoratorPanel();
		decorator.add(panelDatosApuesta);
		decorator.setVisible(false);
		this.getCuerpo().add(decorator);
		buttonLiquidar = new Button(mensajes.liquidar());
		buttonLiquidar.addClickListener(new LiquidarListener());
		buttonLiquidar.addKeyboardListener(new LiquidarListener());
		buttonLiquidar.setVisible(false);
		panelMonto = new SimplePanel();
		this.getCuerpo().add(panelMonto);
		buttonPagar = new Button(mensajes.pagada());
		buttonPagar.setVisible(false);
		buttonPagar.addClickListener(new PagarListener());
		buttonPagar.addKeyboardListener(new PagarListener());
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.add(buttonLiquidar);
		horizontalPanel.add(new Label("\t"));
		horizontalPanel.add(buttonPagar);
		this.getCuerpo().add(horizontalPanel);
	}
	
	@Override
	public void onApuestaLiquidada(Double monto) {
		super.onApuestaLiquidada(monto);
//		montoPagar = new LabelDoble(mensajes.montoAPagar(), "$\t" +monto.toString());
		panelMonto.clear();
		panelMonto.add(new LabelDoble(mensajes.montoAPagar(), "$\t" +monto.toString()));
		buttonPagar.setVisible(true);
	}
	
	public void onMostrarApuestaBuscada(ApuestaDTO apuestaDTO) {
		super.onMostrarApuestaBuscada(apuestaDTO);
		this.apuestaMostrada = apuestaDTO;
		this.recargarDatosApuesta();
		decorator.setVisible(true);
		buttonLiquidar.setVisible(true);
	}
	
	private void recargarDatosApuesta() {
		panelDatosApuesta.clear();
		panelDatosApuesta.add(new LabelDoble(mensajes.nroTicket(), String.valueOf(apuestaMostrada.getNroTicket())));
		panelDatosApuesta.add(new LabelDoble(mensajes.tipo(), apuestaMostrada.getTipoApuesta()));
		panelDatosApuesta.add(new LabelDoble(mensajes.fecha(), Utils.getFechaFormateada(apuestaMostrada.getFechaCreacion(), Utils.FORMATO_ARG_HORA)));
		panelDatosApuesta.add(new LabelDoble(mensajes.diasCobro(), String.valueOf(apuestaMostrada.getDiasPlazoMaxDeCobro())));
		panelDatosApuesta.add(new LabelDoble(mensajes.estado(), apuestaMostrada.getEstado()));
		panelDatosApuesta.add(new LabelDoble(mensajes.monto(), "$\t " + apuestaMostrada.getMontoApostado()));
	}

	public void onMostrarLiquidacionApuestas() {
		super.onMostrarLiquidacionApuestas();
		this.mostrar();
	}
	
	public void onApuestaPagada() {
		super.onApuestaPagada();
		this.resetear();
	}
	
	private void resetear() {
		decorator.setVisible(false);
		buttonLiquidar.setVisible(false);
		buttonPagar.setVisible(false);
		txtNumeroTicket.setValor("");
		panelMonto.clear();
	}

	private class PagarListener implements ClickListener, KeyboardListener  {

		private void doPagar() {
			controlador.doPagarApuesta(apuestaMostrada);
		}
		
		public void onClick(Widget sender) {
			doPagar();
		}

		public void onKeyDown(Widget sender, char keyCode, int modifiers) {
			if(keyCode == KeyboardListener.KEY_ENTER)
				doPagar();
		}

		public void onKeyPress(Widget sender, char keyCode, int modifiers) {}

		public void onKeyUp(Widget sender, char keyCode, int modifiers) {}
		
	}
	
	private class LiquidarListener implements ClickListener, KeyboardListener {

		private void doLiquidar() {
			controlador.doLiquidarApuesta(apuestaMostrada);
		}
		
		public void onClick(Widget sender) {
			doLiquidar();
		}

		public void onKeyDown(Widget sender, char keyCode, int modifiers) {
			if(keyCode == KeyboardListener.KEY_ENTER)
			doLiquidar();
		}

		public void onKeyPress(Widget sender, char keyCode, int modifiers) {}

		public void onKeyUp(Widget sender, char keyCode, int modifiers) {		}
		
	}
	
	private class BuscarListener implements ClickListener, KeyboardListener {

		private void doBuscar() {
			if(txtNumeroTicket.validar()) {
				buttonPagar.setVisible(false);
				buttonLiquidar.setVisible(false);
				panelMonto.clear();
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
			if(keyCode == KeyboardListener.KEY_ENTER)
			doBuscar();
		}

		public void onKeyPress(Widget sender, char keyCode, int modifiers) {
		}

		public void onKeyUp(Widget sender, char keyCode, int modifiers) {
		}
		
	}

}
