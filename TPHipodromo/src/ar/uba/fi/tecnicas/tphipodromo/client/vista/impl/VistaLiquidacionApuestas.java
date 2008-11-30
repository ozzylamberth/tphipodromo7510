package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class VistaLiquidacionApuestas extends VistaDefaultGWT {
	
	private TextBox txtNumeroTicket;
	
	private Button buttonBuscar;

	public VistaLiquidacionApuestas(HasWidgets padre) {
		super(padre);
		this.init();
		
	}

	private void init() {
		this.setTitulo(mensajes.cobrarApuestas());
		HorizontalPanel panel = new HorizontalPanel();
		panel.add(new Label(mensajes.nroTicket() + ": "));
		txtNumeroTicket = new TextBox();
		panel.add(txtNumeroTicket);
		buttonBuscar = new Button(mensajes.buscar());
		buttonBuscar.addClickListener(new BuscarClickListener());
		panel.add(buttonBuscar);
		this.getCuerpo().add(panel);
	}
	
	public void onMostrarLiquidacionApuestas() {
		super.onMostrarLiquidacionApuestas();
		this.mostrar();
	}
	
	private class BuscarClickListener implements ClickListener {

		public void onClick(Widget sender) {
			System.out.println("Buscanro el nro de ticket " + txtNumeroTicket.getText());
		}
		
	}

}
