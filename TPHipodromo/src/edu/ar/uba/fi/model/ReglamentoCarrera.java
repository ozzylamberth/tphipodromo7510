package edu.ar.uba.fi.model;

/**
 * @author Fernando E. Mansilla - 84567
 */
public interface ReglamentoCarrera {

	/**
	 * @param participante
	 * @return Devuelve true si el participante cumple con los requisitos,
	 * false en caso contrario.
	 */
	public boolean validarRequisitos(Participante participante);
	
	public int getCantidadParticipantesMinima();
	
	public int getCantidadParticipantesMaxima();
}
