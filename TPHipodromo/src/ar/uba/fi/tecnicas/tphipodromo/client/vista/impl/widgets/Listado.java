package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets;

import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.client.Mensajes;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class Listado<T> {
	
	private VerticalPanel panel = new VerticalPanel();
	
	private FlexTable tabla = new FlexTable();
	
	private Mensajes mensajes = GWT.create(Mensajes.class);
	
	private Label lblVacia = new Label(mensajes.listaVacia());
	
	private Label lblCargando = new Label(mensajes.cargando());
	
	public Listado() {
		super();
		tabla.setStyleName("listado");
		lblCargando.setVisible(false);
		lblVacia.setVisible(false);
		encabezado();
		pie();
	}
	
	private void encabezado() {
		int i=0;
		
		for(String s: getTitulos()) {
			if(this.getAnchos() != null) {
				tabla.getColumnFormatter().setWidth(i, String.valueOf(getAnchos()[i]));
			}
			tabla.setText(0, i, s);
			tabla.getCellFormatter().setStyleName(0, i, "listado-encabezado");
			i++;
		}
		
		tabla.getRowFormatter().setStyleName(0, "listado-encabezado");
		
		panel.add(lblCargando);
		panel.add(tabla);
	}
	
	private void pie() {
		panel.add(lblVacia);
	}
	
	public void limpiar() {
		while(tabla.getRowCount()>1)
			tabla.removeRow(1);
		
		lblVacia.setVisible(true);
	}
	
	public void update(Collection<T> lista) {
		int i=1;
		
		limpiar();
		
		lblVacia.setVisible(lista.size()==0);
		
		for(T obj: lista) {
			int j= 0;
			
			for( Widget s: getAtributos(obj)) {
				tabla.setWidget(i, j, s);
				tabla.getCellFormatter().setStyleName(i, j, "listado-cuerpo");
				j++;
			}
			
			tabla.getRowFormatter().setStyleName(i, "listado-cuerpo-" + ( i%2==0 ? "par" : "impar"));
			i++;
		}
	}
	
	public void setCargando(boolean cargando) {
		lblCargando.setVisible(cargando);
	}
	
	public Widget toWidget() {
		return panel;
	}
	
	public abstract String[] getTitulos();
	
	public abstract Widget[] getAtributos(T obj);
	
	protected String[] getAnchos() {
		return null;
	}

}
