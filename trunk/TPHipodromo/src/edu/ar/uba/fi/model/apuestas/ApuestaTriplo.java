package edu.ar.uba.fi.model.apuestas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import edu.ar.uba.fi.exceptions.CantidadParticipantesInvalidaException;
import edu.ar.uba.fi.exceptions.CarreraCerradaAApuestasException;
import edu.ar.uba.fi.model.Participante;

/**
 * Representa las apuestas del tipo Triplo, en donde el jugador debe seleccionar
 * 3 caballos para apostar, que deberan llegar primero en 3 carreras señaladas
 * en el programa oficial para ganar la apuesta
 */
public class ApuestaTriplo extends Apuesta {
	
	public ApuestaTriplo() {
		super();
	}

	public ApuestaTriplo(List<Participante> participantes) throws CantidadParticipantesInvalidaException, CarreraCerradaAApuestasException {
		super(participantes);
	}

	public int getCantidadParticipantes() {
		return 3;
	}

	public BigDecimal getValorBase() {
		return new BigDecimal(2);
	}

	public List<Integer> getPosiblesOrdenesLLegada() {
		ArrayList<Integer> ordenesLlegada = new ArrayList<Integer>();
		ordenesLlegada.add(new Integer(1));
		return ordenesLlegada;
	}

}