package edu.ar.uba.fi.model.apuestas;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import edu.ar.uba.fi.model.Carrera;

/**
 * Se encarga de manejar todas las bolsas de apuestas, organizandolas por tipo
 * de apuestas y por carreras
 * 
 * @author ncampos
 * @author jgrande
 * 
 */
public class BolsasApuestasManager {
	private static final BolsasApuestasManager instance = new BolsasApuestasManager();
	// reveer si hay que hacerlo variable
	public static BigDecimal porcentajeComisionHipodromo = new BigDecimal("0.1");

	private Map<Class<? extends Apuesta>, Map<Set<Carrera>, BolsaApuestas>> bolsasApuestas = 
		new HashMap<Class<? extends Apuesta>, Map<Set<Carrera>,BolsaApuestas>>();

	private BolsasApuestasManager() {
	}

	public static BolsasApuestasManager getInstance() {
		return instance;
	}
	
	/**
	 * Retorna la Bolsa de Apuestas correspondiente a esas carreras.
	 * Si hay una bolsa abierta, retorna esa misma, y en caso contrario
	 * crea una nueva bolsa de apuestas para retornar
	 */
	public BolsaApuestas getBolsaApuestas(Class<? extends Apuesta> tipoBolsaApuestas, Set<Carrera> carreras) {
		Map<Set<Carrera>, BolsaApuestas> bolsasPorCarrera = bolsasApuestas.get(tipoBolsaApuestas);
		
		if( bolsasPorCarrera==null) {
			bolsasPorCarrera = new HashMap<Set<Carrera>, BolsaApuestas>();
			bolsasApuestas.put(tipoBolsaApuestas, bolsasPorCarrera);
		}
		
		BolsaApuestas bolsa = bolsasPorCarrera.get(carreras);
		
		if( bolsa==null) {
			bolsa = new BolsaApuestas(tipoBolsaApuestas, carreras);
			bolsa.setPorcentajeComisionHipodromo(porcentajeComisionHipodromo);
			bolsasPorCarrera.put(carreras, bolsa);
		}
		
		return bolsa;
	}

}