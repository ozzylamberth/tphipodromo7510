package edu.ar.uba.fi.model.apuestas;

import java.math.BigDecimal;
import java.util.ArrayList;

import edu.ar.uba.fi.model.Participante;

/**
 * Representa las apuestas del tipo Ganador, en donde el jugador debe
 * seleccionar 1 caballo para apostar en una carrera, que debera llegar en
 * primer lugar para ganar la apuesta
 */
public class ApuestaGanador extends Apuesta {
	
	public ApuestaGanador() {
		super();
	}

	public ApuestaGanador(Participante participante) {
		super();
		ArrayList<Participante> participantes = new ArrayList<Participante>();
		participantes.add(participante);
		this.setParticipantes(participantes);
	}

	public int getCantidadParticipantes() {
		return 1;
	}

	public boolean isAcertada() {
		return (this.getParticipante().getResultado().getOrdenLlegada() == 1);
	}
	
	private Participante getParticipante() {
		return this.getParticipantes().get(0);
	}

	public BigDecimal getValorBase() {
		return new BigDecimal(1);
	}

}