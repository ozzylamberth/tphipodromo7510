package ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones;

//Suppress warnings about missing serialVersionUID
@SuppressWarnings("serial")
public class CarreraCerradaAApuestasException extends CarreraException {

	public CarreraCerradaAApuestasException() {
		super("Carrera cerrada a apuestas");
	}
}