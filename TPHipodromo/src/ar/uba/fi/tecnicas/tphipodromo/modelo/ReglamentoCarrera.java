package ar.uba.fi.tecnicas.tphipodromo.modelo;

/**
 * Interfaz básica para un reglamento de Carrera.
 * 
 * @author Fernando E. Mansilla - 84567
 */
public interface ReglamentoCarrera {

	/**
	 * @param participante
	 * @return Devuelve true si el participante cumple con los requisitos, false
	 *         en caso contrario.
	 */
	public boolean validarRequisitos(Participante participante);

	public int getCantidadParticipantesMinima();

	public int getCantidadParticipantesMaxima();
}
