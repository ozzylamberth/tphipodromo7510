package edu.ar.uba.fi.model;


/**
 * Reglamento que habilita a cualquier tipo de participante a correr
 * en cualquier carrera.
 * @author Fernando E. Mansilla - 84567
 */
public class ReglamentoValeTodo implements ReglamentoCarrera {

	@Override
	public boolean validarRequisitos(Participante participante) {
		return true;
	}

	@Override
	public int getCantidadParticipantesMaxima() {
		return 9;
	}

	@Override
	public int getCantidadParticipantesMinima() {
		// TODO Auto-generated method stub
		return 0;
	}

}
