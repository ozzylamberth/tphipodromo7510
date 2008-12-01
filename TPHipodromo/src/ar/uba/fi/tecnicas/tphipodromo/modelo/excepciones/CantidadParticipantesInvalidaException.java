package ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones;

//Suppress warnings about missing serialVersionUID
@SuppressWarnings("serial")
public class CantidadParticipantesInvalidaException extends ApuestaException {
	
	public CantidadParticipantesInvalidaException() {
		super("Cantidad de participantes invalida");
	}
}
