package ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones;

import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class ApuestaInvalidaException extends Exception implements IsSerializable {
	
	public ApuestaInvalidaException(String message) {
		super(message);
	}
	
	public ApuestaInvalidaException() {
		super("La apuesta tiene datos invalidos");
	}
}
