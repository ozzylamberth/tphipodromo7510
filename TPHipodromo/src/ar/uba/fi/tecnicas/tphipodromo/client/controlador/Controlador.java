package ar.uba.fi.tecnicas.tphipodromo.client.controlador;

import java.util.Collection;
import java.util.LinkedList;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.evento.Evento;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.Vista;

/**
 * Controlador genérico que implementa el patrón Observer.
 * 
 * @author Juan
 *
 * @param <T> Tipo de vista que responde a los eventos que dispara el controlador.
 * @param <E> Enumerado con los eventos que dispara el controlador.
 */
public class Controlador<T extends Vista> {
	
	/** Colección de vistas que observan los eventos disparados por el controlador. */
	private Collection<T> observadores;

	/**
	 * Constructor público.
	 * 
	 */
	public Controlador() {
		observadores = new LinkedList<T>();
	}
	
	/**
	 * Agrega un observador al controlador. Los observadores
	 * agregados utilizando <code>addObserver</code> serán 
	 * informados de todos los eventos que dispare el controlador.
	 * 
	 * @param obs Vista a agregar como observadora del controlador.
	 */
	public void addObserver(T obs) {
		observadores.add(obs);
	}
	
	/**
	 * Dispara un evento y notifica a todos los observadores.
	 * 
	 * @param evento Evento a disparar.
	 * @param args Argumentos que se pasarán a la vista.
	 */
	protected void notifyObservers(Evento<T> evento, Object... args) {
		for(T obs: observadores) {
			evento.resolver(obs, args);
		}
	}

}
