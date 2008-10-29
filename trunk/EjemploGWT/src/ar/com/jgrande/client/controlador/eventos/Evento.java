package ar.com.jgrande.client.controlador.eventos;

import ar.com.jgrande.client.vista.Vista;

/**
 * Interfaz que deben implementar los enumerados que definen
 * los eventos que dispara un controlador. Cada controlador dispara
 * un conjunto de eventos que son escuchados por una o más vistas.
 * Los eventos que dispara un controlador están definidos por un
 * enumerado que implementa la interfaz <code>Evento</code>.
 * 
 * @author Juan
 *
 * @param <V> Vista que recibe los eventos.
 */
public interface Evento<V extends Vista> {
	
	/**
	 * Relaciona un evento con un método en la vista.
	 * <p>
	 * El método <code>resolver</code> cumple dos funciones principales:
	 * <li> Mapea un evento con un método de la vista
	 * <li> Mapea y castea los argumentos en <code>args</code> a los
	 *      parámetros que recibe el método callback de la vista.
	 * 
	 * @param v Vista que recibe el evento.
	 * @param args Argumentos que deben mapearse a los parámetros de la vista.
	 */
	public void resolver(V v, Object[] args);

}
