package edu.ar.uba.fi.model.apuestas;

import java.math.BigDecimal;
import java.util.Collection;

import edu.ar.uba.fi.model.Participante;

/**
 * Representa las apuestas del tipo Triplo, en donde el jugador debe seleccionar
 * 3 caballos para apostar, que deberan llegar primero en 3 carreras señaladas
 * en el programa oficial para ganar la apuesta
 */
public class Triplo extends Apuesta {

	public Triplo(Collection<Participante> participantes) {
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
		return new BigDecimal(2);
	}

}