package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class VistaPrincipalGWT extends VistaGWT {

	private DockPanel panelContenedor;
	
	private SimplePanel panelIzquierda;
	
	private PanelPie panelPie;
	
	private SimplePanel panelContenido;
	
	private DecoratorPanel mensajeDecorado;
	
	private Label mensaje;
	
	public VistaPrincipalGWT() {
		super(null);
		
		this.panelContenedor = new DockPanel();
		this.panelIzquierda = new SimplePanel();
		this.panelContenido = new SimplePanel();
		this.mensaje = new Label();
		this.mensaje.setWidth("100%");
		this.mensaje.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		this.mensajeDecorado = new DecoratorPanel();
		this.mensajeDecorado.add(this.mensaje);
		this.mensajeDecorado.setVisible(false);
		this.mensajeDecorado.setWidth("100%");
		
		VerticalPanel panelCentro = new VerticalPanel();
		panelCentro.add(this.mensajeDecorado);
		panelCentro.add(this.panelContenido);
		
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
		return panelContenido;
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
		HTML html = new HTML();
		html.setHTML(e.getLocalizedMessage());
		dialog.add(html);
		dialog.center();
		dialog.show();
		
		super.onErrorRPC(e);
	}
	
	@Override
	public void onMostrarMensajePie(String s) {
		this.mensaje.setText(s);
		this.mensajeDecorado.setVisible(true);
		Timer t = new Timer() {
	        public void run() {
	        	onOcultarMensajePie();
	        }
	      };
	     t.schedule(5000);//TODO tomas: Parametrizar
	};
	
	@Override
	public void onOcultarMensajePie() {
		this.mensajeDecorado.setVisible(false);
	}
}
