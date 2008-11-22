package ar.uba.fi.tecnicas.tphipodromo.client.util;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;

public class Mensaje extends DialogBox {
	
	public Mensaje(String titulo, String mensaje) {
		super(true);
		
		setText(titulo);
		add(new Label(mensaje));
	}
	
	public void mostrar() {
		center();
		show();
	}

}
