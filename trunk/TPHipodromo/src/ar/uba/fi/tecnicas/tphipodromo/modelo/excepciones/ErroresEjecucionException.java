package ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones;

//Suppress warnings about missing serialVersionUID
@SuppressWarnings("serial")
public class ErroresEjecucionException extends HipodromoException {
	
	public ErroresEjecucionException() {
		super("Errores de ejecucion");
	}
}