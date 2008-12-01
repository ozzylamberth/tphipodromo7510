package ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones;


/**
 * Esta clase generaliza todas las excepciones que se pueden 
 * dar con una Apuesta 
 */
//Suppress warnings about missing serialVersionUID
@SuppressWarnings("serial")
public abstract class ApuestaException extends HipodromoException {
	
	public ApuestaException(String msg) {
		super(msg);
	}
}