package edu.ar.uba.fi.model.apuestas;

import java.math.BigDecimal;
import java.util.Collection;

import edu.ar.uba.fi.model.Participante;

/**
 * Representa las apuestas del tipo Cuaterna, en donde el jugador debe
 * seleccionar 4 caballos para apostar, que deberan llegar primero en 4 carreras
 * consecutivas, señaladas en el programa oficial, para ganar la apuesta
 */
public class ApuestaCuaterna extends Apuesta {
	
	public ApuestaCuaterna() {
		super();
	}

	public ApuestaCuaterna(Collection<Participante> participantes) {
		// TODO: validar que los participantes sean de las carreras
		// correspondientes
	}

	public int getCantidadParticipantes() {
		return 4;
	}

	public boolean isAcertada() {
		// TODO: implementar logica
		return false;
	}

	public BigDecimal getValorBase() {
		return new BigDecimal(2);
	}

}