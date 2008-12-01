package ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones;

//Suppress warnings about missing serialVersionUID
@SuppressWarnings("serial")
public class ResultadosCarreraInvalidosException extends CarreraException {
	
	public ResultadosCarreraInvalidosException() {
		super("Resultados carrera invalidos");
	}
}
