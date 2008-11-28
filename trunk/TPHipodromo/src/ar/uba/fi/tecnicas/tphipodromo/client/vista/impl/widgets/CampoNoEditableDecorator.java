package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets;

import com.google.gwt.user.client.ui.Widget;

public class CampoNoEditableDecorator extends Campo {
	
	private Campo decorado;
	
	public CampoNoEditableDecorator(Campo decorado) {
		super(decorado.isObligatorio());
		this.decorado = decorado;
		this.decorado.setEditable(false);
	}
	
	@Override
	public String getValor() {
		return this.decorado.getValor();
	}

	@Override
	public void setEditable(boolean editable) {
		//No hace nada, la idea es que nunca es editable este campo.
	}

	@Override
	public void setValor(String obj) {
		this.decorado.setValor(obj);
	}

	@Override
	public Widget toWidget() {
		return decorado.toWidget();
	}

	@Override
	public boolean validar() {
		return decorado.validar();
	}

}
