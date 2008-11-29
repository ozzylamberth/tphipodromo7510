package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets;

import java.util.Date;

import ar.uba.fi.tecnicas.tphipodromo.client.Mensajes;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ChangeListenerCollection;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SourcesChangeEvents;
import com.google.gwt.user.client.ui.Widget;

public class DateBox extends Composite implements SourcesChangeEvents, ChangeListener, ClickListener {
	
	private ChangeListenerCollection changeListeners = new ChangeListenerCollection();
	
	private static int MIN_ANIO = 2008;
	
	private static int MAX_ANIO = 2015;
	
	private Mensajes mensajes = GWT.create(Mensajes.class);
	
	private HorizontalPanel panel = new HorizontalPanel();
	
	private ListBox anio = new ListBox();
	
	private ListBox mes = new ListBox();
	
	private ListBox dia = new ListBox();
	
	private Button botonHoy = new Button(mensajes.hoy());
	
	public DateBox() {
		initAnios();
		deshabilitarDia();
		deshabilitarMes();
		
		anio.addChangeListener(this);
		mes.addChangeListener(this);
		dia.addChangeListener(this);
		botonHoy.addClickListener(this);
		
		panel.add(anio);
		panel.add(mes);
		panel.add(dia);
		panel.add(botonHoy);
		
		initWidget(panel);
	}
	
	@SuppressWarnings("deprecation")
	public Date getDate() {
		Date d = new Date(anio.getSelectedIndex()+MIN_ANIO-1901,
				mes.getSelectedIndex()-1, 
				dia.getSelectedIndex());
		
		if( anio.getSelectedIndex()==0 || mes.getSelectedIndex()==0 || dia.getSelectedIndex()==0) {
			return null;
		}
		
		return d;
	}
	
	public void setDate(String s) {
		Date d = DateTimeFormat.getShortDateFormat().parseStrict(s);
		
		this.setDate(d);
	}
	
	@SuppressWarnings("deprecation")
	public void setDate(Date d) {
		if(d==null) {
			deshabilitarDia();
			deshabilitarMes();
			anio.setSelectedIndex(0);
			return;
		}
		
		if(d.getYear()+1900>MAX_ANIO || d.getYear()+1900<MIN_ANIO)
			throw new IllegalArgumentException();
		
		anio.setSelectedIndex(d.getYear()+1901-MIN_ANIO);
		habilitarMes();
		mes.setSelectedIndex(d.getMonth()+1);
		habilitarDia();
		dia.setSelectedIndex(d.getDate());
		
		this.onChange(null);
	}
	
	public void initAnios() {
		anio.addItem(mensajes.anio());
		for(int a=MIN_ANIO; a<=MAX_ANIO; a++) {
			anio.addItem(String.valueOf(a));
		}
	}
	
	public void habilitarMes() {
		mes.clear();
		mes.addItem(mensajes.mes());
		
		for(int m=1;m<=12;m++) {
			mes.addItem(String.valueOf(m));	
		}
		
		mes.setSelectedIndex(0);
	}
	
	public void habilitarDia() {
		int[] diasPorMes = 
			new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		
		int anio = this.anio.getSelectedIndex()+MIN_ANIO-1;
		int mes = this.mes.getSelectedIndex();
		
		dia.clear();
		dia.addItem(mensajes.dia());
		
		for(int d=1; d<=diasPorMes[mes-1]; d++) {
			dia.addItem(String.valueOf(d));
		}
		
		if( mes==2 && ((anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0))) {
			dia.addItem("29");
		}
		
		dia.setSelectedIndex(0);
	}
	
	public void deshabilitarMes() {
		mes.clear();
		mes.addItem(mensajes.mes());
	}
	
	public void deshabilitarDia() {
		dia.clear();
		dia.addItem(mensajes.dia());
	}
	
	public void setEnabled(boolean enabled) {
		anio.setEnabled(enabled);
		mes.setEnabled(enabled);
		dia.setEnabled(enabled);
		botonHoy.setEnabled(enabled);
	}
	
	public void addChangeListener(ChangeListener listener) {
		changeListeners.add(listener);
	}
	
	public void onChange(Widget sender) {		
		if( anio.equals(sender)) {
			if( anio.getSelectedIndex()==0) {
				deshabilitarDia();
				deshabilitarMes();
			} else {
				habilitarMes();
			}
		} else if( mes.equals(sender)) {
			if( mes.getSelectedIndex()==0) {
				deshabilitarDia();
			} else {
				habilitarDia();
			}
		}
		
		for(ChangeListener listener: changeListeners) {
			listener.onChange(this);
		}
	}
	
	public void onClick(Widget sender) {
		this.setDate(new Date());
	}
	
	public void removeChangeListener(ChangeListener listener) {
		changeListeners.remove(listener);
	}
	
	@Override
	public void addStyleName(String style) {
		dia.addStyleName(style);
		mes.addStyleName(style);
		anio.addStyleName(style);
	}
	
	@Override
	public void removeStyleName(String style) {
		dia.removeStyleName(style);
		mes.removeStyleName(style);
		anio.removeStyleName(style);
	}
	
}
