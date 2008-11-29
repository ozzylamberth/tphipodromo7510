package ar.uba.fi.tecnicas.tphipodromo.client.vista.impl.widgets;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class Formulario {
	
	private FlexTable form = new FlexTable();
	
	private Map<String, Campo> campos = new HashMap<String, Campo>();
	
	private int cantidadCampos = 0;
	
	private boolean editable = false;

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
	public void reset() {
		for(Campo campo: campos.values()) {
			campo.reset();
		}
	}
	
	public boolean validar() {
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
	
	public void setEditable(boolean editable) {
		this.editable = editable;
		
		for(Campo campo: campos.values()) {
			campo.setEditable(editable);
		}
	}
	
	public boolean isEditable() {
		return editable;
	}

	public Widget toWidget() {
		return form;
	}
}
