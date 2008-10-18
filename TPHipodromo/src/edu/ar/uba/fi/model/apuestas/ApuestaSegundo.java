package edu.ar.uba.fi.model.apuestas;

import java.math.BigDecimal;

import edu.ar.uba.fi.model.Participante;

/**
 * Representa las apuestas del tipo Segundo, en donde el jugador debe
 * seleccionar 1 caballo para apostar en una carrera, que debera llegar en
 * primer o segundo lugar para ganar la apuesta
 */
public class ApuestaSegundo extends Apuesta {

	public ApuestaSegundo(Participante participante) {
		// TODO: validar que los participantes sean de las carreras
		// correspondientes
	}

	public int getCantidadParticipantes() {
		return 1;
	}

	public boolean isAcertada() {
		// TODO: implementar logica
		return false;
	}

	public BigDecimal getValorBase() {
		return new BigDecimal(1);
	}

}