package ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones;

//Suppress warnings about missing serialVersionUID
@SuppressWarnings("serial")
public class ApuestaPerdidaException extends ApuestaException {
	
	public ApuestaPerdidaException() {
		super("La apuesta fue perdida");
	}
	
}