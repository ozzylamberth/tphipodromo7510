package edu.ar.uba.fi.model;

/**
 * @author Fernando E. Mansilla - 84567
 */
public interface ReglamentoParticipanteCarrera {

	/**
	 * @param participante
	 * @return Devuelve true si el participante cumple con los requisitos,
	 * false en caso contrario.
	 */
	public boolean validarRequisitos(Participante participante);
}
