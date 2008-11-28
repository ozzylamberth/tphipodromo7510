package ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones;

@SuppressWarnings("serial")
public class ApuestaInvalidaException extends Exception {
	
	public ApuestaInvalidaException() {
		super("La apuesta tiene datos invalidos");
	}
}
