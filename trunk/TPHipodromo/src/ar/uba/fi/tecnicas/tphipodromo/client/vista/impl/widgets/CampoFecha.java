package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;


public class CampoFecha extends Campo {
	
	private DateBox fecha = new DateBox();
	
	public CampoFecha(boolean obligatorio) {
		super(obligatorio);
	}
	
	@Override
	public String getValor() {
		return DateTimeFormat.getShortDateFormat().format(fecha.getDate());
	}
	
	public Date getDate() {
		return fecha.getDate();
	}

	@Override
	public void setEditable(boolean editable) {
		this.fecha.setEnabled(editable);
	}

	@Override
	public void setValor(String obj) {
		this.fecha.setDate(obj);
	}

	@Override
	public DateBox toWidget() {
		return fecha;
	}

	@Override
	public boolean validar() {
		if(isObligatorio() && fecha.getDate()==null)
			return false;
		
		return true;
	}
	
	@Override
	public void reset() {
		this.fecha.setDate((Date)null);
		super.reset();
	}
	
}
