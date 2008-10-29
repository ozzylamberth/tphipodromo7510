package ar.com.jgrande.client;

import ar.com.jgrande.modelo.servicios.ServicioUsuarios;
import ar.com.jgrande.modelo.servicios.ServicioUsuariosAsync;

import com.google.gwt.core.client.GWT;

/**
 * Encapsula la forma en que GWT obtiene las instancias de los servicios.
 * 
 * @author Juan
 *
 */
public class FacadeServicios {
	
	/** Única instancia del Facade. */
	private static FacadeServicios instance;
	
	/** Instancia de ServicioUsuarios. */
	private ServicioUsuariosAsync servicioUsuarios;
	
	/**
	 * Constructor privado.
	 */
	private FacadeServicios() {
		servicioUsuarios = GWT.create(ServicioUsuarios.class); 
	}

	/**
	 * @return única instancia de ServicioUsuarios.
	 */
	public ServicioUsuariosAsync getServicioUsuarios() {
		return servicioUsuarios;
	}
	
	/**
	 * @return única instancia de FacadeServicios
	 */
	public static FacadeServicios get() {
		if (instance == null) {
			instance = new FacadeServicios();
		}
		
		return instance;
	}

}
