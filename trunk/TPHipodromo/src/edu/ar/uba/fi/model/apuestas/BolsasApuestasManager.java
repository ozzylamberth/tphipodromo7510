package edu.ar.uba.fi.model.apuestas;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
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

	private Map<Class<? extends Apuesta>, Map<Set<Carrera>, BolsaApuestasAbstracta>> bolsasApuestas = new HashMap<Class<? extends Apuesta>, Map<Set<Carrera>, BolsaApuestasAbstracta>>();
	private Properties properties;

	private BolsasApuestasManager() {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(
					"TipoBolsaXTipoApuesta.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static BolsasApuestasManager getInstance() {
		return instance;
	}

	/**
	 * Retorna la Bolsa de Apuestas correspondiente a esas carreras. Si hay una
	 * bolsa abierta, retorna esa misma, y en caso contrario crea una nueva
	 * bolsa de apuestas para retornar
	 */
	public BolsaApuestasAbstracta getBolsaApuestas(
			Class<? extends Apuesta> tipoApuestas, Set<Carrera> carreras) {
		Map<Set<Carrera>, BolsaApuestasAbstracta> bolsasPorCarrera = bolsasApuestas
				.get(tipoApuestas);

		if (bolsasPorCarrera == null) {
			bolsasPorCarrera = new HashMap<Set<Carrera>, BolsaApuestasAbstracta>();
			bolsasApuestas.put(tipoApuestas, bolsasPorCarrera);
		}

		BolsaApuestasAbstracta bolsa = bolsasPorCarrera.get(carreras);

		if (bolsa == null) {
			
			
			try {
				Class<? extends BolsaApuestasAbstracta> tipoBolsaSegunTipoApuesta = getTipoBolsaSegunTipoApuesta(tipoApuestas);
				Constructor<?> constructor = tipoBolsaSegunTipoApuesta.getConstructor();
				bolsa = (BolsaApuestasAbstracta) constructor.newInstance();

				bolsa.setTipoBolsaApuestas(tipoApuestas);
				bolsa.setCarreras(carreras);
				bolsa.setPorcentajeComisionHipodromo(porcentajeComisionHipodromo);

				bolsasPorCarrera.put(carreras, bolsa);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return bolsa;
	}

	@SuppressWarnings("unchecked")
	public Class<? extends BolsaApuestasAbstracta> getTipoBolsaSegunTipoApuesta(
			Class<? extends Apuesta> tipoApuesta) throws ClassNotFoundException {
		
		String className = (String) properties.get(tipoApuesta.getCanonicalName());
		System.out.println("1) "+className);
		if (className == null) {
			try {
				properties.load(new FileInputStream(
						"TipoBolsaXTipoApuesta.properties"));
				className = (String) properties
						.get(tipoApuesta.getCanonicalName());
				System.out.println("2) "+className);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (className != null) {
			return  (Class<? extends BolsaApuestasAbstracta>) ClassLoader.getSystemClassLoader().loadClass(className);
		}
		return null;

	}

}