package edu.ar.uba.fi.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que maneja las estaditicas y permite calcular el handicap asociado a
 * las mismas.
 * 
 * @author Fernando E. Mansilla - 84567
 */
public class Estadisticas {

	private Map<Integer, Integer> resultadosPorPosicion = new HashMap<Integer, Integer>();

	/**
	 * @param posicion
	 *            Posición en la que se registro un nuevo resultado.
	 */
	public void agregarResultado(int posicion) {
		Integer valorActual = resultadosPorPosicion.get(posicion);
		if (valorActual == null) {
			valorActual = 0;
		}
		valorActual++;
		resultadosPorPosicion.put(posicion, valorActual);
	}

	/**
	 * @return Valor calculado del Handicap.
	 */
	public int getHandicap() {
		// TODO Calculo de Handicap de prueba. Buscar un algoritmo real.
		return getCantidadDeResultadosEnPosicion(1) * 10
				+ getCantidadDeResultadosEnPosicion(2) * 5
				+ getCantidadDeResultadosEnPosicion(2) * 1;
	}

	/**
	 * 
	 * @param posicion
	 *            Posición de la que se desea averiguar la cantidad de
	 *            resultados agregados.
	 * @return Cantidad de veces que se registro un resultado en la posición
	 *         indicada.
	 */
	public int getCantidadDeResultadosEnPosicion(int posicion) {
		Integer valorActual = resultadosPorPosicion.get(posicion);
		if (valorActual == null) {
			return 0;
		} else {
			return valorActual;
		}
	}
}
