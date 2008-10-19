package edu.ar.uba.fi.model.apuestas;

import java.math.BigDecimal;
import java.util.Collection;

import edu.ar.uba.fi.model.Participante;

/**
 * Representa las apuestas del tipo Doble, en donde el jugador debe seleccionar
 * 2 caballos para apostar, que deberan llegar primero en 2 carreras
 * consecutivas para ganar la apuesta
 */
public class ApuestaDoble extends Apuesta {
	
	public ApuestaDoble() {
		super();
	}

	public ApuestaDoble(Collection<Participante> participantes) {
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