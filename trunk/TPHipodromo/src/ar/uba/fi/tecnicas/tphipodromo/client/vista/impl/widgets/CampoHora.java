package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets;

import java.util.Date;

import ar.uba.fi.tecnicas.tphipodromo.client.vista.Utils;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class CampoHora extends Campo {
	
	public final static DateTimeFormat formato = DateTimeFormat.getFormat(Utils.FORMATO_HORA_24);
	private final static int horaDefault = 12;
	private final static int minutoDefault = 00;
	private CampoInteger campoHora;
	
	private CampoInteger campoMinutos;
	
	private HorizontalPanel panel;
	
	
	public CampoHora(boolean obligatorio) {
		super(obligatorio);
		campoHora = new CampoInteger(obligatorio);
		campoMinutos = new CampoInteger(obligatorio);
		campoHora.setValor(String.valueOf(horaDefault));
		campoHora.toWidget().setSize("35px", "22px");
		campoMinutos.setValor(String.valueOf(minutoDefault));
		campoMinutos.toWidget().setSize("35px", "22px");
		panel = new HorizontalPanel();
		panel.add(campoHora.toWidget());
		panel.add(new Label(":"));
		panel.add(campoMinutos.toWidget());
	}
	
	@SuppressWarnings("deprecation")
	public Date getDate() {
		return new Date(0, 0, 0, Integer.valueOf(campoHora.getValor()), Integer.valueOf(campoMinutos.getValor()));
	}


	@Override
	public String getValor() {
		return formato.format(getDate());
	}

	@Override
	public void setEditable(boolean editable) {
		campoHora.setEditable(editable);
		campoMinutos.setEditable(editable);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void setValor(String obj) {
		Date d = formato.parseStrict(obj);
		campoHora.setValor(String.valueOf(d.getHours()));
		campoMinutos.setValor(String.valueOf(d.getMinutes()));
	}

	@Override
	public Widget toWidget() {
		return panel;
	}

	@Override
	public boolean validar() {
		if(!campoHora.validar()) {
			return false;
		}
		if(!campoMinutos.validar()) {
			return false;
		}
		if(Integer.valueOf(campoHora.getValor()).compareTo(0) < 0
				|| Integer.valueOf(campoHora.getValor()).compareTo(23) > 0) {
			return false;
		}
		if(Integer.valueOf(campoMinutos.getValor()).compareTo(0) < 0
				|| Integer.valueOf(campoMinutos.getValor()).compareTo(59) > 0) {
			return false;
		}
		return true;
	}
	
	@Override
	public void setInvalido(boolean invalido) {
		campoHora.setInvalido(invalido);
		campoMinutos.setInvalido(invalido);
	}

}
