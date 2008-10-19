package edu.ar.uba.fi.model.apuestas;

import java.util.ArrayList;
import java.util.Collection;

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
	 * Retorna la Bola de Apuestas correspondientes a esas carreras.
	 * Si hay una bolsa abierta, retorna esa misma, y en caso contrario
	 * crea una nueva bolsa de apuestas para retornar
	 */
	public BolsaApuestas getBolsaApuestas(TipoBolsaApuestas tipoBolsaApuestas, Collection<Carrera> carreras) {
		// TODO: implementar
		return null;
	}

}