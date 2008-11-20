package edu.ar.uba.fi.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import edu.ar.uba.fi.model.apuestas.Apuesta;

/**
 * Esta clase se encarga de administrar las configuraciones para las carreras y
 * tipos de apuesta. Implementa el patrón Singleton. Si una carrera no tiene
 * ninguna configuración asociada se asume por default que todo tipo de apuesta
 * es válido en la misma. En caso contrario, lo serán solo aquellas que tengan
 * una configuración asociada. Este control de configuraciones se hizo necesario
 * por la ocurrencia en casos reales de que en ciertas carreras se encontraban
 * algunos tipos de apuestas restringidos.
 * 
 * @author Fernando E. Mansilla - 84567
 */
public class ConfiguracionManager {

	private static ConfiguracionManager instancia = new ConfiguracionManager();

	Map<Class<? extends Apuesta>, Map<Carrera, Configuracion>> configuraciones;

	private ConfiguracionManager() {
		configuraciones = new HashMap<Class<? extends Apuesta>, Map<Carrera, Configuracion>>();
	}

	/**
	 * 
	 * @param carreras
	 * @param tipoApuesta
	 * @param configuracion
	 */
	public void addConfiguracion(Configuracion configuracion) {
		Map<Carrera, Configuracion> mapTipoApuesta = configuraciones
				.get(configuracion.getTipoApuesta());
		if (mapTipoApuesta == null) {
			mapTipoApuesta = new HashMap<Carrera, Configuracion>();
		}

		mapTipoApuesta.put(configuracion.getCarrera(), configuracion);
	}

	/**
	 * @param carrera
	 * @return true si existe alguna configuración para algun tipo de apuesta
	 *         para la carrera.
	 */
	public boolean existenConfiguraciones(Carrera carrera) {
		Iterator<Map<Carrera, Configuracion>> it = configuraciones.values()
				.iterator();
		while (it.hasNext()) {
			if (it.next().containsKey(carrera)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param carreras
	 * @param tipoApuesta
	 * @return Configuración asociada a la carrera y tipo de Apuesta.
	 */
	public Configuracion getConfiguracion(Carrera carrera,
			Class<? extends Apuesta> tipoApuesta) {

		Map<Carrera, Configuracion> map = configuraciones.get(tipoApuesta);

		if (map == null) {
			return null;
		} else {
			return map.get(carrera);
		}
	}

	/**
	 * @return Única instancia de la clase.
	 */
	public static ConfiguracionManager getInstancia() {
		return instancia;
	}

}