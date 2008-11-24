package ar.uba.fi.tecnicas.tphipodromo.modelo;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que maneja las estadísticas y permite calcular el Handicap asociado a
 * las mismas. Por el momento solo los caballos son factibles de tener
 * estadísticas. Se registran las cantidades de veces que se obtuvo un cierto
 * resultado. Esto además de permitir calcular el Handicap permite evaluar
 * reglas de inscripción complejas encontradas en casos reales. Como por ejemplo
 * que solo puedan inscribirse a una carrera todos los participantes con
 * caballos de edad mayos a 4 años y que hayan ganado al menos 1 vez.
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

	@SuppressWarnings("all")
	private Map<Integer, Integer> getResultadosPorPosicion() {
		return resultadosPorPosicion;
	}

	@SuppressWarnings("all")
	private void setResultadosPorPosicion(
			Map<Integer, Integer> resultadosPorPosicion) {
		this.resultadosPorPosicion = resultadosPorPosicion;
	}
}
