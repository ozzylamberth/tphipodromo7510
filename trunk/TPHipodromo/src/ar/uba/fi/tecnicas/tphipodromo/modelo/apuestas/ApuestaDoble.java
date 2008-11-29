package ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa las apuestas del tipo Doble, en donde el jugador debe seleccionar
 * 2 caballos para apostar, que deberan llegar primero en 2 carreras
 * consecutivas para ganar la apuesta
 */
public class ApuestaDoble extends Apuesta { 
	
	public ApuestaDoble() {
		super(TipoApuesta.DOBLE);
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
		return ordenesLlegada;
	}

}