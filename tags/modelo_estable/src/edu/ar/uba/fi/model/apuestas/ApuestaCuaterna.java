package edu.ar.uba.fi.model.apuestas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa las apuestas del tipo Cuaterna, en donde el jugador debe
 * seleccionar 4 caballos para apostar, que deberan llegar primero en 4 carreras
 * consecutivas, señaladas en el programa oficial, para ganar la apuesta
 */
public class ApuestaCuaterna extends Apuesta {
	
	public int getCantidadParticipantes() {
		return 4;
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