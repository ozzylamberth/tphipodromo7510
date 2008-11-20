package ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Carrera;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Configuracion;


/**
 * Clase encargada de administrar una bolsa por carrera y por tipo de Apuesta.
 * Conoce la correspondencia entre tipo de apuestas y tipo de bolsa. Esta clase
 * hace uso de la clase ConfiguracionManager para obtener la configuraci�n de la
 * carrera y de acuerdo con esta conoce el pozo m�nimo e incremento del Pozo que
 * va a asignar a la bolsa de apuesta reci�n creada. LA relaci�n entre tipo de
 * apuesta y tipo de bolsa que debe manejarla se implementa mediante un archivo
 * de configuraci�n que indica la correspondencia de tipos.
 * 
 * @author ncampos
 * @author jgrande
 * @author Fernando E. Mansilla - 84567
 */
public class BolsasApuestasManager {
	private static final BolsasApuestasManager instance = new BolsasApuestasManager();
	// TODO reveer si hay que hacerlo variable
	public static BigDecimal porcentajeComisionHipodromo = new BigDecimal("0.1");

	private Map<Class<? extends Apuesta>, Map<Set<Carrera>, BolsaApuestasAbstracta>> bolsasApuestas = new HashMap<Class<? extends Apuesta>, Map<Set<Carrera>, BolsaApuestasAbstracta>>();
	private Properties properties;

	/**
	 * Carga la configuraci�n de correspondencias entre tipos de apuestas y
	 * tipos de bolsas.
	 */
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
	 * @param tipoApuestas
	 *            Tipo de apuesta que se desea administrar.
	 * @param carreras
	 *            Carreras sobre las que se aplica la apuesta.
	 * @param configuraci�n
	 *            Configuraci�n asociada a la creaci�n de la bolsa, indica el
	 *            incremento y el pozo minimo. En caso de ser null, ambos
	 *            valores se consideran igual a cero.
	 * @return la Bolsa de Apuestas correspondiente a esas carreras. Si hay una
	 *         bolsa abierta, retorna esa misma, y en caso contrario crea una
	 *         nueva bolsa de apuestas para retornar
	 */
	public BolsaApuestasAbstracta getBolsaApuestas(
			Class<? extends Apuesta> tipoApuestas, Set<Carrera> carreras,
			Configuracion configuraci�n) {
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
				Constructor<?> constructor = tipoBolsaSegunTipoApuesta
						.getConstructor();
				bolsa = (BolsaApuestasAbstracta) constructor.newInstance();

				bolsa.setTipoBolsaApuestas(tipoApuestas);
				bolsa.setCarreras(carreras);
				bolsa
						.setPorcentajeComisionHipodromo(porcentajeComisionHipodromo);

				if (configuraci�n != null) {
					bolsa.setIncrementoPozo(configuraci�n.getIncrementoPozo());
					bolsa.setPozoMinimo(configuraci�n.getPozoMinimo());
				} else {
					bolsa.setIncrementoPozo(BigDecimal.ZERO);
					bolsa.setPozoMinimo(BigDecimal.ZERO);
				}

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

	/**
	 * @param tipoApuesta
	 * @return tipo de Bolsa a crear seg�n el tupo de apuesta indicado.
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public Class<? extends BolsaApuestasAbstracta> getTipoBolsaSegunTipoApuesta(
			Class<? extends Apuesta> tipoApuesta) throws ClassNotFoundException {

		String className = (String) properties.get(tipoApuesta
				.getCanonicalName());
		System.out.println("1) " + className);
		if (className == null) {
			try {
				properties.load(new FileInputStream(
						"TipoBolsaXTipoApuesta.properties"));
				className = (String) properties.get(tipoApuesta
						.getCanonicalName());
				System.out.println("2) " + className);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (className != null) {
			return (Class<? extends BolsaApuestasAbstracta>) ClassLoader
					.getSystemClassLoader().loadClass(className);
		}
		return null;

	}

}