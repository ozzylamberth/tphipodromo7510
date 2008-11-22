package ar.uba.fi.tecnicas.tphipodromo.client.util;

import java.util.Collection;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

public abstract class Listado<T> extends FlexTable {
	
	public Listado() {
		super();
		
		this.setStyleName("listado");
		
		encabezado();
	}
	
	private void encabezado() {
		int i=0;
		
		for(String s: getTitulos()) {
			this.setText(0, i, s);
			this.getCellFormatter().setStyleName(0, i, "listado-encabezado");
			i++;
		}
		
		this.getRowFormatter().setStyleName(0, "listado-encabezado");
	}
	
	public void limpiar() {
		for(int i=1; i<this.getRowCount(); i++) {
			this.removeRow(i);
		}
	}
	
	public void update(Collection<T> lista) {
		int i=1;
		
		limpiar();
		
		for(T obj: lista) {
			int j= 0;
			
			for( Widget s: getAtributos(obj)) {
				this.setWidget(i, j, s);
				this.getCellFormatter().setStyleName(i, j, "listado-cuerpo");
				j++;
			}
			
			this.getRowFormatter().setStyleName(i, "listado-cuerpo-" + ( i%2==0 ? "par" : "impar"));
			i++;
		}
	}
	
	public abstract String[] getTitulos();
	
	public abstract Widget[] getAtributos(T obj);

}
