package ar.uba.fi.tecnicas.tphipodromo.client;

import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioCaballos;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioCaballosAsync;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dto.CaballoDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dto.DetalleCaballoDTO;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TPHipodromo implements EntryPoint {
	
	private Mensajes mensajes = GWT.create(Mensajes.class);

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
	  Label lblTitulo = new Label(mensajes.caballos());
	  lblTitulo.setStyleName("lbl-large");
	  
	  final FlexTable tabla = new FlexTable();

	  tabla.setStyleName("listado");
	  tabla.getCellFormatter().setStyleName(0, 0, "listado-encabezado");
	  tabla.getCellFormatter().setStyleName(0, 1, "listado-encabezado");
	  tabla.getCellFormatter().setStyleName(0, 2, "listado-encabezado");
	  tabla.setText(0, 0, mensajes.nombre());
	  tabla.setText(0, 1, mensajes.edad());
	  tabla.setText(0, 2, mensajes.peso());
	  
	  final ServicioCaballosAsync servicioCaballos = GWT.create(ServicioCaballos.class);
	  servicioCaballos.buscar(new AsyncCallback<Collection<CaballoDTO>>() {
		 public void onFailure(Throwable caught) {
			 final DialogBox dialog = new DialogBox();
			 Button btnCerrar = new Button(mensajes.cerrar());
			 dialog.setText(mensajes.errorRCP());
			 dialog.add(btnCerrar);
			 btnCerrar.addClickListener(new ClickListener() {
				public void onClick(Widget sender) {
					 dialog.hide();
				}
			 });
			 dialog.center();
			 dialog.show();
		 };
		 
		 public void onSuccess(Collection<CaballoDTO> result) {
			 int i=1;
			 for(final CaballoDTO caballo: result) {
				 Hyperlink link = new Hyperlink();
				 
				 link.addClickListener(new ClickListener() {

					public void onClick(Widget sender) {
						final DialogBox dialog = new DialogBox();
						
						final Grid form = new Grid(9, 2);
						for(int i=0;i<9;i++) form.getCellFormatter().setStyleName(i, 0, "detalle-clave");
						form.setText(0, 0, mensajes.nombre());
						form.setText(1, 0, mensajes.caballeriza());
						form.setText(2, 0, mensajes.edad());
						form.setText(3, 0, mensajes.criador());
						form.setText(4, 0, mensajes.pelaje());
						form.setText(5, 0, mensajes.peso());
						form.setText(6, 0, mensajes.esPuraSange());
						form.setText(7, 0, mensajes.padre());
						form.setText(8, 0, mensajes.madre());
						
						Button cerrar = new Button(mensajes.cerrar());
						cerrar.addClickListener(new ClickListener() {
							public void onClick(Widget sender) {
								dialog.hide();
							}
						});
						
						servicioCaballos.buscar("", new AsyncCallback<DetalleCaballoDTO>() {
							public void onFailure(Throwable caught) {};
							
							public void onSuccess(DetalleCaballoDTO result) {
								form.setText(0, 1, result.getNombre());
								form.setText(1, 1, result.getCaballeriza());
								form.setText(2, 1, String.valueOf(result.getEdad()));
								form.setText(3, 1, result.getCriador());
								form.setText(4, 1, result.getPelaje());
								form.setText(5, 1, String.valueOf(result.getPeso()));
								form.setText(6, 1, result.isPuraSangre() ? mensajes.si() : mensajes.no());
								form.setText(7, 1, result.getPadre());
								form.setText(8, 1, result.getMadre());
							};
						});
						
						VerticalPanel panel = new VerticalPanel();
						panel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
						panel.setSpacing(10);
						panel.add(form);
						panel.add(cerrar);

						dialog.setText(caballo.getNombre());
						dialog.add(panel);
						dialog.center();
						dialog.show();
					}
				 });
				 
				 link.setText(caballo.getNombre());
				 
				 tabla.setWidget(i, 0, link);
				 tabla.setText(i, 1, caballo.getEdad().toString());
				 tabla.setText(i, 2, caballo.getPeso().toString());
				 
				 tabla.getCellFormatter().setStyleName(i, 0, "listado-cuerpo");
				 tabla.getCellFormatter().setStyleName(i, 1, "listado-cuerpo");
				 tabla.getCellFormatter().setStyleName(i, 2, "listado-cuerpo");
				 
				 tabla.getCellFormatter().setHorizontalAlignment(i, 0, HasHorizontalAlignment.ALIGN_CENTER);
				 tabla.getCellFormatter().setHorizontalAlignment(i, 1, HasHorizontalAlignment.ALIGN_CENTER);
				 tabla.getCellFormatter().setHorizontalAlignment(i, 2, HasHorizontalAlignment.ALIGN_CENTER);
				 
				 i++;
			 }
		 };
	  });
	  
	  VerticalPanel panel = new VerticalPanel();
	  panel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
	  panel.setWidth("100%");
	  panel.setSpacing(20);
	  panel.add(lblTitulo);
	  panel.add(tabla);
	  
	  RootPanel.get().add(panel);
  }
}
