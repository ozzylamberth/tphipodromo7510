package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets;

import com.google.gwt.user.client.ui.Widget;

public abstract class Campo {
	
	private boolean obligatorio;
	
	public Campo(boolean obligatorio) {
		this.obligatorio = obligatorio;
	}
	
	public boolean isObligatorio() {
		return obligatorio;
	}
	
	public void setInvalido(boolean invalido) {
		if(invalido) {
			toWidget().addStyleName("campo-invalido");
		} else {
			toWidget().removeStyleName("campo-invalido");
		}
	}
	
	public void reset() {
		setInvalido(false);
	}
	
	public abstract Widget toWidget();

	public abstract boolean validar();
	
	public abstract String getValor();
	
	public abstract void setValor(String obj);
	
	public abstract void setEditable(boolean editable);

}
