package ar.com.jgrande.servidor.servicios.impl;

import java.net.URL;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public abstract class ServicioAbstracto extends RemoteServiceServlet {
	
	/** Ruta al archivo de configuración de log4j. */
	private static String LOG4J_PROPERTIES_PATH = "log4j.properties";
	
	/** Logger para esta clase. */
	protected Logger logger;
	
	/**
	 * Constructor público.
	 */
	public ServicioAbstracto() {
		URL log4jProperties = getClass().getResource(LOG4J_PROPERTIES_PATH);
		
		if (log4jProperties != null) {
			PropertyConfigurator.configure(log4jProperties);
		} else {
			BasicConfigurator.configure();
			Logger.getLogger(getClass()).warn("Imposible hallar log4j.properties, usando BasicConfigurator.");
		}
		
		logger = Logger.getLogger(getClass());
	}

}
