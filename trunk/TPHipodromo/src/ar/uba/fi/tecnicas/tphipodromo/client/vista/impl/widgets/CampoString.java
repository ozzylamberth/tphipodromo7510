package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets;

import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class CampoString extends Campo {
	
	private TextBox textBox;
	
	public CampoString(boolean obligatorio) {
		super(obligatorio);
		
		textBox = new TextBox();
	}

	@Override
	public String getValor() {
		return textBox.getText();
	}
	
	@Override
	public void setValor(String arg0) {
		textBox.setText(arg0);
	}
	
	@Override
	public void setEditable(boolean editable) {
		textBox.setReadOnly(!editable);
	}

	@Override
	public Widget toWidget() {
		return textBox;
	}

	@Override
	public boolean validar() {
		if(isObligatorio() && textBox.getText().length()==0) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public void reset() {
		textBox.setText("");
		super.reset();
	}
	
	public void addChangeListener(ChangeListener l) {
		textBox.addChangeListener(l);
	}

}
