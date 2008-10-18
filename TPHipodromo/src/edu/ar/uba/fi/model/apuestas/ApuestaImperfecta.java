package edu.ar.uba.fi.model.apuestas;

import java.math.BigDecimal;
import java.util.Collection;

import edu.ar.uba.fi.model.Participante;

/**
 * Representa las apuestas del tipo Imperfecta, en donde el jugador debe
 * seleccionar 2 caballos para apostar en una misma carrera, que debera llegar
 * en primero y segundo, en cualquier orden para ganar la apuesta
 */
public class ApuestaImperfecta extends Apuesta {

	public ApuestaImperfecta(Collection<Participante> participantes) {
		// TODO: validar que los participantes sean de las carreras
		// correspondientes
	}

	public int getCantidadParticipantes() {
		return 2;
	}

	public boolean isAcertada() {
		// TODO: implementar logica
		return false;
	}

	public BigDecimal getValorBase() {
		return new BigDecimal(2);
	}

}