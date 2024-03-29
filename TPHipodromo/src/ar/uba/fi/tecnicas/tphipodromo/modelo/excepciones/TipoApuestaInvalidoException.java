package ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones;


/**
 * Lanzada cuando se intenta ingresar un tipo de apuesta cuyo tipo no ncorresponde
 * para la bolsa de apuestas.
 * @author Fernando E. Mansilla - 84567
 *
 */
public class TipoApuestaInvalidoException extends ApuestaException {
	private static final long serialVersionUID = -6205635509178866861L;

	public TipoApuestaInvalidoException() {
		super("Tipo de apuesta invalido");
	}
	
}
