package ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones;

import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class TipoApuestaInvalidaException extends Exception implements IsSerializable {
	
	public TipoApuestaInvalidaException() {
		super("El tipo de la apuesta es invalido");
	}
	
}
