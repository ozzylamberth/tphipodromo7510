package ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones;

/**
 * Excepcion utilizada para indica que hubo un error al intentar pasar una
 * Apuesta a un nuevo estado.
 * 
 * @author Fernando E. Mansilla - 84567
 */
public class TransicionInvalidaEstadoApuestaException extends ApuestaException {

	private static final long serialVersionUID = 7334088373021352341L;
	
	public TransicionInvalidaEstadoApuestaException() {
		super("Transicion invalida de estado de apuesta");
	}
	
}
