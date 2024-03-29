package edu.ar.uba.fi.model.apuestas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import edu.ar.uba.fi.exceptions.CantidadParticipantesInvalidaException;
import edu.ar.uba.fi.exceptions.CarreraCerradaAApuestasException;
import edu.ar.uba.fi.exceptions.ParticipantesEnDistintasCarrerasException;
import edu.ar.uba.fi.model.Participante;

/**
 * Representa las apuestas del tipo Imperfecta, en donde el jugador debe
 * seleccionar 2 caballos para apostar en una misma carrera, que debera llegar
 * en primero y segundo, en cualquier orden para ganar la apuesta
 */
public class ApuestaImperfecta extends Apuesta {
	
	public ApuestaImperfecta() {
		super();
	}

	public ApuestaImperfecta(List<Participante> participantes) throws CantidadParticipantesInvalidaException, ParticipantesEnDistintasCarrerasException, CarreraCerradaAApuestasException {
		super(participantes);
		this.validarMismaCarrera(participantes);
	}
	
	private void validarMismaCarrera(List<Participante> participantes) throws ParticipantesEnDistintasCarrerasException {
		if (!participantes.get(0).getCarrera().equals(participantes.get(1).getCarrera())) {
			throw new ParticipantesEnDistintasCarrerasException();
		}
	}

	public int getCantidadParticipantes() {
		return 2;
	}

	public BigDecimal getValorBase() {
		return new BigDecimal(2);
	}

	public List<Integer> getPosiblesOrdenesLLegada() {
		ArrayList<Integer> ordenesLlegada = new ArrayList<Integer>();
		ordenesLlegada.add(new Integer(1));
		ordenesLlegada.add(new Integer(2));
		return ordenesLlegada;
	}

}