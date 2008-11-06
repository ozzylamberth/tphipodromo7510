package edu.ar.uba.fi.model;

/**
 * Reglamento que habilita a cualquier tipo de participante a correr en
 * cualquier carrera. El mínimo y máximo de participantes son muy flexibles.
 * 
 * @author Fernando E. Mansilla - 84567
 */
public class ReglamentoValeTodo implements ReglamentoCarrera {

	@Override
	public boolean validarRequisitos(Participante participante) {
		return true;
	}

	@Override
	public int getCantidadParticipantesMaxima() {
		return 1000;
	}

	@Override
	public int getCantidadParticipantesMinima() {
		return 0;
	}

}
