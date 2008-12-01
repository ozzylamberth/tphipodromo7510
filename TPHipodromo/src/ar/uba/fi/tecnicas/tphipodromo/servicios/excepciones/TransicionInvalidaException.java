package ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones;

import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class TransicionInvalidaException extends Exception implements IsSerializable {
	
	public TransicionInvalidaException() {
		super("La transicion que se quiere realizar es invalida");
	}
}
