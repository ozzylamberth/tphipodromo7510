package edu.ar.uba.fi.model.apuestas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
	public BolsaApuestas getBolsaApuestas(TipoBolsaApuestas tipoBolsaApuestas, List<Carrera> carreras) {
		Iterator<BolsaApuestas> it = this.bolsasApuestas.iterator();
		while (it.hasNext()) {
			BolsaApuestas bolsaApuestas = (BolsaApuestas) it.next();
			if ((bolsaApuestas.getTipoBolsaApuestas().equals(tipoBolsaApuestas)) &&
					(bolsaApuestas.correspondeACarreras(carreras))) {
				return bolsaApuestas;
			}
		}
		return this.crearBolsaApuestas(tipoBolsaApuestas, carreras);
	}
	
	private BolsaApuestas crearBolsaApuestas(TipoBolsaApuestas tipoBolsaApuestas, List<Carrera> carreras) {
		BolsaApuestas bolsaApuestas = new BolsaApuestas(tipoBolsaApuestas, carreras);
		this.bolsasApuestas.add(bolsaApuestas);
		return bolsaApuestas;
	}

}