package ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa las apuestas del tipo Triplo, en donde el jugador debe seleccionar
 * 3 caballos para apostar, que deberan llegar primero en 3 carreras señaladas
 * en el programa oficial para ganar la apuesta
 */
public class ApuestaTriplo extends Apuesta {
	
	public static final String TIPO_APUESTA = "Apuesta Triplo"; 
	
	public ApuestaTriplo() {
		super(TIPO_APUESTA);
	}
	
	public int getCantidadParticipantes() {
		return 3;
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