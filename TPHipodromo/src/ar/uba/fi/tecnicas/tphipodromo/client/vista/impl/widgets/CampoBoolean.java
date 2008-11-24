package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets;

import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class CampoBoolean extends Campo {
	
	private ListBox listBox;
	
	public CampoBoolean() {
		super(true);
		
		this.listBox = new ListBox();
		
		listBox.addItem(mensajes.si());
		listBox.addItem(mensajes.no());
	}
	
	@Override
	public Widget toWidget() {
		return listBox;
	}
	
	@Override
	public String getValor() {
		return listBox.getSelectedIndex()==0 ? "true" : "false";
	}
	
	@Override
	public void setValor(String arg0) {
		if(new Boolean(arg0).equals(Boolean.TRUE)) {
			listBox.setSelectedIndex(0);
		} else {
			listBox.setSelectedIndex(1);
		}
	}
	
	@Override
	public void setEditable(boolean editable) {
		listBox.setEnabled(editable);
	}

	@Override
	public boolean validar() {
		return true;
	}
	
	@Override
	public void reset() {
		this.listBox.setSelectedIndex(0);
		super.reset();
	}

}
