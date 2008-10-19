package edu.ar.uba.fi.model.apuestas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import edu.ar.uba.fi.exceptions.CantidadParticipantesInvalidaException;
import edu.ar.uba.fi.exceptions.ParticipantesEnDistintasCarrerasException;
import edu.ar.uba.fi.model.Participante;

/**
 * Representa las apuestas del tipo Trifecta, en donde el jugador debe
 * seleccionar 3 caballos para apostar en una misma carrera, que debera llegar
 * primero, segundo y tercero en orden exacto para ganar la apuesta
 */
public class ApuestaTrifecta extends ApuestaPorPosicionesOrdenadas {
	
	public ApuestaTrifecta() {
		super();
	}

	public ApuestaTrifecta(List<Participante> participantes) throws CantidadParticipantesInvalidaException, ParticipantesEnDistintasCarrerasException {
		super(participantes);
		this.validarMismaCarrera(participantes);
	}
	
	private void validarMismaCarrera(List<Participante> participantes) throws ParticipantesEnDistintasCarrerasException {
		if (!participantes.get(0).getCarrera().equals(participantes.get(1).getCarrera())) {
			throw new ParticipantesEnDistintasCarrerasException();
		} else if (!participantes.get(0).getCarrera().equals(participantes.get(2).getCarrera())) {
			throw new ParticipantesEnDistintasCarrerasException();
		}
	}

	public int getCantidadParticipantes() {
		return 3;
	}

	public BigDecimal getValorBase() {
		return new BigDecimal(1);
	}

	public List<Integer> getPosiblesOrdenesLLegada() {
		ArrayList<Integer> ordenesLlegada = new ArrayList<Integer>();
		ordenesLlegada.add(new Integer(1));
		ordenesLlegada.add(new Integer(2));
		ordenesLlegada.add(new Integer(3));
		return ordenesLlegada;
	}

}