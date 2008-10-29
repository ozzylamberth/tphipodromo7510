package ar.com.jgrande.client.controlador.eventos;

import ar.com.jgrande.client.vista.Vista;

/**
 * Interfaz que deben implementar los enumerados que definen
 * los eventos que dispara un controlador. Cada controlador dispara
 * un conjunto de eventos que son escuchados por una o m�s vistas.
 * Los eventos que dispara un controlador est�n definidos por un
 * enumerado que implementa la interfaz <code>Evento</code>.
 * 
 * @author Juan
 *
 * @param <V> Vista que recibe los eventos.
 */
public interface Evento<V extends Vista> {
	
	/**
	 * Relaciona un evento con un m�todo en la vista.
	 * <p>
	 * El m�todo <code>resolver</code> cumple dos funciones principales:
	 * <li> Mapea un evento con un m�todo de la vista
	 * <li> Mapea y castea los argumentos en <code>args</code> a los
	 *      par�metros que recibe el m�todo callback de la vista.
	 * 
	 * @param v Vista que recibe el evento.
	 * @param args Argumentos que deben mapearse a los par�metros de la vista.
	 */
	public void resolver(V v, Object[] args);

}
