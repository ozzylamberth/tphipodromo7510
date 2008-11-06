package edu.ar.uba.fi.exceptions;

/**
 * Excepcion utilizada para indicar que un participante no puede inscribirse en
 * una carrera por alguna de las siguientes causas:
 * <ul>
 * <li>No cumple con el reglamento de la carrera.</li>
 * <li>El estado del participante no es Pendiente de Largada.</li>
 * </ul>
 * 
 * @author Fernando E. Mansilla - 84567
 */
public class ParticipanteNoCalificadoException extends CarreraException {
	private static final long serialVersionUID = -8653423990281199299L;
}