package ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones;

@SuppressWarnings("serial")
public class TipoApuestaInvalidaException extends Exception {
	
	public TipoApuestaInvalidaException() {
		super("El tipo de la apuesta es invalido");
	}
	
}
