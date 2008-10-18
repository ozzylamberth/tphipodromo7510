package edu.ar.uba.fi.model.apuestas;

import java.math.BigDecimal;
import java.util.Collection;

import edu.ar.uba.fi.model.Participante;

/**
 * Representa las apuestas del tipo Trifecta, en donde el jugador debe
 * seleccionar 3 caballos para apostar en una misma carrera, que debera llegar
 * primero, segundo y tercero en orden exacto para ganar la apuesta
 */
public class ApuestaTrifecta extends Apuesta {

	public ApuestaTrifecta(Collection<Participante> participantes) {
		// TODO: validar que los participantes sean de las carreras
		// correspondientes
	}

	public int getCantidadParticipantes() {
		return 3;
	}

	public boolean isAcertada() {
		// TODO: implementar logica
		return false;
	}

	public BigDecimal getValorBase() {
		return new BigDecimal(1);
	}

}