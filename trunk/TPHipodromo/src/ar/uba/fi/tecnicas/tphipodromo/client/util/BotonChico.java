package ar.uba.fi.tecnicas.tphipodromo.client.util;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;

public class BotonChico extends Button {
	
	public BotonChico(String texto, ClickListener listener) {
		super(texto, listener);
		this.setStyleName("boton-chico");
	}

}
