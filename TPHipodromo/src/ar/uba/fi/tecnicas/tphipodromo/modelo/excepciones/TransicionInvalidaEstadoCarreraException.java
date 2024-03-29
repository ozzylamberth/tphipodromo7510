package ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones;

/**
 * Excepcion utilizada para indica que hubo un error al intentar pasar una
 * Carrera a un nuevo estado.
 * 
 * @author Fernando E. Mansilla - 84567
 */
public class TransicionInvalidaEstadoCarreraException extends CarreraException {
	private static final long serialVersionUID = -5882960841475900896L;

	public TransicionInvalidaEstadoCarreraException() {
		super("Transicion invalida de estado de carrera");
	}
}
