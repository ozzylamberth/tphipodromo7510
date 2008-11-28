package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import ar.uba.fi.tecnicas.tphipodromo.client.Mensajes;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Formulario extends DialogBox implements ClickListener {
	
	private Mensajes mensajes = GWT.create(Mensajes.class);
	
	private FlexTable form = new FlexTable();
	
	private Button botonGuardar = new Button(mensajes.guardar());
	
	private Button botonCerrar = new Button(mensajes.cerrar());
	
	private Map<String, Campo> campos = new HashMap<String, Campo>();
	
	private FormularioListener listener;
	
	private int cantidadCampos = 0;
	
	private HorizontalPanel botonera;
	
	/**
	 * Tiene los nombres de los campos que son solo para mostrar datos y no se pueden
	 * modificar.
	 */
	private Collection<String> noEditables;
	
	public Formulario(FormularioListener l) {
		super(false);
		
		this.listener = l;
		
		this.botonGuardar.setTabIndex(98);
		this.botonCerrar.setTabIndex(99);
		
		this.botonGuardar.addClickListener(this);
		this.botonCerrar.addClickListener(this);
		
		noEditables = new ArrayList<String>();
		
		botonera = new HorizontalPanel();
		botonera.setSpacing(10);
		botonera.add(botonGuardar);
		botonera.add(botonCerrar);
		
		VerticalPanel principal = new VerticalPanel();
		principal.add(form);
		principal.add(botonera);
		principal.setCellHorizontalAlignment(form,
				VerticalPanel.ALIGN_CENTER);
		principal.setCellHorizontalAlignment(botonera,
				VerticalPanel.ALIGN_RIGHT);
		
		super.add(principal);
	}
	
	public void add(String nombre, String label, Campo campo) {
		campos.put(nombre, campo);
		
		form.setWidget(cantidadCampos, 0, new Label(label));
		form.setWidget(cantidadCampos, 1, campo.toWidget());
		
		cantidadCampos++;
	}
	
	public void setString(String nombre, String valor) {
		Campo campo = campos.get(nombre);
		campo.setValor(valor);
	}
	
	public String getString(String nombre) {
		Campo campo = campos.get(nombre);
		return campo.getValor();
	}

	public void setBoolean(String nombre, boolean valor) {
		Campo campo = campos.get(nombre);
		campo.setValor(String.valueOf(valor));
	}
	
	public boolean getBoolean(String nombre) {
		Campo campo = campos.get(nombre);
		return Boolean.parseBoolean(campo.getValor());
	}
	
	public void setInteger(String nombre, int valor) {
		Campo campo = campos.get(nombre);
		campo.setValor(String.valueOf(valor));
	}
	
	public int getInteger(String nombre) {
		Campo campo = campos.get(nombre);
		return Integer.parseInt(campo.getValor());
	}
	
	public void setLong(String nombre, long valor) {
		Campo campo = campos.get(nombre);
		campo.setValor(String.valueOf(valor));
	}
	
	public long getLong(String nombre) {
		Campo campo = campos.get(nombre);
		return Long.parseLong(campo.getValor());
	}
	
	public double getDouble(String nombre) {
		Campo campo = campos.get(nombre);
		return Double.parseDouble(campo.getValor());
	}
	
	public void setDouble(String nombre, double valor) {
		Campo campo = campos.get(nombre);
		campo.setValor(String.valueOf(valor));
	}
	/*
	public Date getFecha(String nombre) {
		Campo campo = campos.get(nombre);
		try {
			if(campo.getValor()== null) {
				return null;
			}
			return Utils.parsearFecha(campo.getValor(), Utils.FORMATO_ARG);
		} catch (ParseException e) {
			//Esta excepcion no deberia darse
			e.printStackTrace();
			return null;
		}
	}
	
	public void setFecha(String nombre, Date fecha) {
		Campo campo = campos.get(nombre);
		campo.setValor(Utils.getFechaFormateada(fecha, Utils.FORMATO_ARG));
	}
	*/
	
	private void reset() {
		for(Campo campo: campos.values()) {
			campo.reset();
		}
	}
	
	private boolean validar() {
		boolean invalido = false;
		
		for(Campo campo: campos.values()) {
			if( !campo.validar()) {
				campo.setInvalido(true);
				invalido = true;
			} else {
				campo.setInvalido(false);
			}
		}
		
		return !invalido;
	}
	
	public final void onClick(Widget sender) {
		if( sender.equals(botonGuardar)) {
			if( validar()) {
				this.hide();
				listener.onGuardar();
			}
		}
		
		if(sender.equals(botonCerrar)) {
			this.hide();
			this.reset();
		}
	}
	
	public void setEditable(boolean editable) {
		botonGuardar.setVisible(editable);
		
		for(Campo campo: campos.values()) {
			campo.setEditable(editable);
		}
	}

	public HorizontalPanel getBotonera() {
		return botonera;
	}

	public void setBotonera(HorizontalPanel botonera) {
		this.botonera = botonera;
	}
}
