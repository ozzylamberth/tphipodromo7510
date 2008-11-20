package ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones;

/**
 * Excepcion utilizada para indicar que no se pueden realizar m�s incripciones
 * en una carrera por alguna de las siguientes causas:
 * <ul>
 * <li> Se lleg� al l�mite m�ximo de participantes.</li>
 * <li>La carrera no esta m�s abierta a apuestas.</li>
 * </ul>
 * 
 * @author Fernando E. Mansilla - 84567
 */
public class InscripcionCarreraCerradaException extends HipodromoException {
	private static final long serialVersionUID = 1160250031825759800L;

}
