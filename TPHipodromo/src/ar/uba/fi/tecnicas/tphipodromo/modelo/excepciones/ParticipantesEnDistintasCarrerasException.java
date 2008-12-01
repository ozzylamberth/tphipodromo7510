package ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones;

//Suppress warnings about missing serialVersionUID
@SuppressWarnings("serial")
public class ParticipantesEnDistintasCarrerasException extends ApuestaException {

	public ParticipantesEnDistintasCarrerasException() {
		super("Participantes en distintas carreras");
	}
}
