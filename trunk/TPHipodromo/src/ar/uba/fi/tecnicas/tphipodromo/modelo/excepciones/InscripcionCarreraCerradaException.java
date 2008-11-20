package ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones;

/**
 * Excepcion utilizada para indicar que no se pueden realizar más incripciones
 * en una carrera por alguna de las siguientes causas:
 * <ul>
 * <li> Se llegó al límite máximo de participantes.</li>
 * <li>La carrera no esta más abierta a apuestas.</li>
 * </ul>
 * 
 * @author Fernando E. Mansilla - 84567
 */
public class InscripcionCarreraCerradaException extends HipodromoException {
	private static final long serialVersionUID = 1160250031825759800L;

}
