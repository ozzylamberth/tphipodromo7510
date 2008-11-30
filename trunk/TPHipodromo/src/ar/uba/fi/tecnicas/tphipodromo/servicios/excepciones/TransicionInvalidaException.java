package ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones;

@SuppressWarnings("serial")
public class TransicionInvalidaException extends Exception {
	
	public TransicionInvalidaException() {
		super("La transicion que se quiere realizar es invalida");
	}
}
