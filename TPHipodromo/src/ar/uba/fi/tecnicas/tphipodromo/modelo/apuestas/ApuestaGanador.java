package ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa las apuestas del tipo Ganador, en donde el jugador debe
 * seleccionar 1 caballo para apostar en una carrera, que debera llegar en
 * primer lugar para ganar la apuesta
 */
public class ApuestaGanador extends Apuesta {
	
	public static final String TIPO_APUESTA = "Apuesta Ganador"; 
	
	public ApuestaGanador() {
		super(TIPO_APUESTA);
	}

	public int getCantidadParticipantes() {
		return 1;
	}

	public BigDecimal getValorBase() {
		return new BigDecimal(1);
	}

	public List<Integer> getPosiblesOrdenesLLegada() {
		ArrayList<Integer> ordenesLlegada = new ArrayList<Integer>();
		ordenesLlegada.add(new Integer(1));
		return ordenesLlegada;
	}

}