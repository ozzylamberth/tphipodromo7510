package edu.ar.uba.fi.model;


/**
 * Reglamento que habilita a cualquier tipo de participante a correr
 * en cualquier carrera.
 * @author Fernando E. Mansilla - 84567
 */
public class ReglamentoValeTodo implements ReglamentoParticipanteCarrera {

	@Override
	public boolean validarRequisitos(Participante participante) {
		return true;
	}

}
