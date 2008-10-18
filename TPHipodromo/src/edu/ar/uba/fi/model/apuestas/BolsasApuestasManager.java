package edu.ar.uba.fi.model.apuestas;

import java.util.ArrayList;

import edu.ar.uba.fi.model.Carrera;

/**
 * Se encarga de manejar todas las bolsas de apuestas, organizandolas por tipo
 * de apuestas y por carreras
 */
public class BolsasApuestasManager {
	private static final BolsasApuestasManager instance = new BolsasApuestasManager();

	private ArrayList<BolsaApuestas> bolsasApuestas = new ArrayList<BolsaApuestas>();

	private BolsasApuestasManager() {
	}

	public static BolsasApuestasManager getInstance() {
		return instance;
	}

	/**
	 * Retorna la Bolsa de Apuestas correspondiente a esa carrera y a las
	 * apuestas del tipo Ganador. Si hay una bolsa abierta a apuestas, retorna
	 * esa misma, y en caso contrario crea una nueva bolsa de apuestas
	 */
	public BolsaApuestas getBolsaApuestasGanador(Carrera Carrera) {
		// TODO: implementar logica
		return null;
	}

	/**
	 * Retorna la Bolsa de Apuestas correspondiente a esa carrera y a las
	 * apuestas del tipo Segundo. Si hay una bolsa abierta a apuestas, retorna
	 * esa misma, y en caso contrario crea una nueva bolsa de apuestas
	 */
	public BolsaApuestas getBolsaApuestasSegundo(Carrera Carrera) {
		return null;
	}

	/**
	 * Retorna la Bolsa de Apuestas correspondiente a esa carrera y a las
	 * apuestas del tipo Tercero. Si hay una bolsa abierta a apuestas, retorna
	 * esa misma, y en caso contrario crea una nueva bolsa de apuestas
	 */
	public BolsaApuestas getBolsaApuestasTercero(Carrera Carrera) {
		// TODO: implementar logica
		return null;
	}

	/**
	 * Retorna la Bolsa de Apuestas correspondiente a esa carrera y a las
	 * apuestas del tipo Exacta. Si hay una bolsa abierta a apuestas, retorna
	 * esa misma, y en caso contrario crea una nueva bolsa de apuestas
	 */
	public BolsaApuestas getBolsaApuestasExacta(Carrera Carrera) {
		// TODO: implementar logica
		return null;
	}

	/**
	 * Retorna la Bolsa de Apuestas correspondiente a esa carrera y a las
	 * apuestas del tipo Imperfecta. Si hay una bolsa abierta a apuestas,
	 * retorna esa misma, y en caso contrario crea una nueva bolsa de apuestas
	 */
	public BolsaApuestas getBolsaApuestasImperfecta(Carrera Carrera) {
		// TODO: implementar logica
		return null;
	}

	/**
	 * Retorna la Bolsa de Apuestas correspondiente a esa carrera y a las
	 * apuestas del tipo Trifecta. Si hay una bolsa abierta a apuestas, retorna
	 * esa misma, y en caso contrario crea una nueva bolsa de apuestas
	 */
	public BolsaApuestas getBolsaApuestasTrifecta(Carrera Carrera) {
		// TODO: implementar logica
		return null;
	}

	/**
	 * Retorna la Bolsa de Apuestas correspondiente a esas carrera y a las
	 * apuestas del tipo Doble. Si hay una bolsa abierta a apuestas, retorna esa
	 * misma, y en caso contrario crea una nueva bolsa de apuestas
	 */
	public BolsaApuestas getBolsaApuestasDoble(Carrera[] carreras) {
		// TODO: implementar logica
		return null;
	}

	/**
	 * Retorna la Bolsa de Apuestas correspondiente a esas carrera y a las
	 * apuestas del tipo Triplo. Si hay una bolsa abierta a apuestas, retorna
	 * esa misma, y en caso contrario crea una nueva bolsa de apuestas
	 */
	public BolsaApuestas getBolsaApuestasTriplo(Carrera[] carreras) {
		// TODO: implementar logica
		return null;
	}

	/**
	 * Retorna la Bolsa de Apuestas correspondiente a esas carrera y a las
	 * apuestas del tipo Cuaterna. Si hay una bolsa abierta a apuestas, retorna
	 * esa misma, y en caso contrario crea una nueva bolsa de apuestas
	 */
	public BolsaApuestas getBolsaApuestasCuaterna(Carrera[] carreras) {
		// TODO: implementar logica
		return null;
	}

}