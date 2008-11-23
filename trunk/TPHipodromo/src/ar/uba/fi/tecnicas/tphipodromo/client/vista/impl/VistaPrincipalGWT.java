package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class VistaPrincipalGWT extends VistaGWT {

	private DockPanel panelContenedor;
	
	private SimplePanel panelIzquierda;
	
	private SimplePanel panelCentro;
	
	private PanelPie panelPie;
	
	public VistaPrincipalGWT() {
		super(null);
		this.panelContenedor = new DockPanel();
		this.panelIzquierda = new SimplePanel();
		this.panelCentro = new SimplePanel();
		
		this.panelContenedor.setWidth("80%");
		this.panelContenedor.setHorizontalAlignment(DockPanel.ALIGN_CENTER);
		
		this.panelContenedor.add(new PanelTop(), DockPanel.NORTH);
		panelPie = new PanelPie();
		this.panelContenedor.add(panelPie, DockPanel.SOUTH);
		this.panelContenedor.add(panelIzquierda, DockPanel.WEST);
		this.panelContenedor.setCellWidth(panelIzquierda, "20%");
		this.panelContenedor.add(panelCentro, DockPanel.CENTER);
		this.panelContenedor.setCellWidth(panelCentro, "80%");
	}
	
	public Widget toWidget() {
		return panelContenedor;
	}
	
	public HasWidgets getPanelIzquierda() {
		return panelIzquierda;
	}
	
	public HasWidgets getPanelCentro() {
		return panelCentro;
	}
	
	@Override
	public void onMostrarPrincipal() {
		super.onMostrarPrincipal();
		this.mostrar();
	}
	
	@Override
	public void onErrorRPC(Throwable e) {
		DialogBox dialog = new DialogBox(true);
		
		dialog.setText("Error!");
		dialog.add(new Label(e.getLocalizedMessage()));
		dialog.center();
		dialog.show();
		
		super.onErrorRPC(e);
	}
	
	@Override
	public void onMostrarMensajePie(String mensaje) {
		panelPie.mostrarMensaje(mensaje);
	};
}
