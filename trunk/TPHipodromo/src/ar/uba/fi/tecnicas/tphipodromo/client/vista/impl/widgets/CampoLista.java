package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets;

import java.util.Collection;

import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class CampoLista extends Campo {
	
	private ListBox listBox;
	
	public CampoLista() {
		super(true);
		
		listBox = new ListBox();
	}
	
	public void setItems(Collection<CampoListaItem> items) {
		listBox.clear();
		
		for(CampoListaItem item: items) {
			listBox.addItem(item.getDescripcion(), item.getId());
		}
	}

	@Override
	public String getValor() {
		return listBox.getValue(listBox.getSelectedIndex());
	}

	@Override
	public void setEditable(boolean editable) {
		listBox.setEnabled(editable);

	}

	@Override
	public void setValor(String obj) {
		for(int i=0; i<listBox.getItemCount(); i++) {
			if( listBox.getValue(i).equals(obj)) {
				listBox.setSelectedIndex(i);
				return;
			}
		}
	}

	@Override
	public Widget toWidget() {
		return listBox;
	}

	@Override
	public boolean validar() {
		return true;
	}

}
