package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets;


public class CampoFecha{
	/*
	private com.thapar.gwt.user.ui.client.widget.simpledatepicker.DatePicker datePicker;
	
	public CampoFecha(boolean obligatorio) {
		super(obligatorio);
		datePicker = new com.thapar.gwt.user.ui.client.widget.simpledatepicker.SimpleDatePicker();
		datePicker.setWeekendSelectable(true);
		datePicker.setDateFormat(com.thapar.gwt.user.ui.client.util.DateFormat.DATE_FORMAT_DDMMYYYY);
		
	}
	
	@Override
	public String getValor() {
		if(datePicker.getSelectedDate()== null) {
			return null;
		}
		return Utils.getFechaFormateada(datePicker.getSelectedDate(), Utils.FORMATO_ARG);
	}

	@Override
	public void setEditable(boolean editable) {
		datePicker.setEnabled(editable);
	}

	@Override
	public void setValor(String obj) {
		try {
			datePicker.setSelectedDate(Utils.parsearFecha(obj, Utils.FORMATO_ARG));
		} catch (java.text.ParseException e) {
			this.setInvalido(true);
		}
		
	}

	@Override
	public Widget toWidget() {
		return datePicker;
	}

	@Override
	public boolean validar() {
		try {
			Utils.parsearFecha(getValor(), Utils.FORMATO_ARG);
		} catch (java.text.ParseException e) {
			return false;
		}
		return true;
	}
*/
}
