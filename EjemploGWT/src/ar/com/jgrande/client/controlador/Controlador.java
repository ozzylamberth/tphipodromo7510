package ar.com.jgrande.client.controlador;

import java.util.Collection;
import java.util.LinkedList;

import ar.com.jgrande.client.controlador.eventos.Evento;
import ar.com.jgrande.client.vista.Vista;

/**
 * Controlador gen�rico que implementa el patr�n Observer.
 * 
 * @author Juan
 *
 * @param <T> Tipo de vista que responde a los eventos que dispara el controlador.
 * @param <E> Enumerado con los eventos que dispara el controlador.
 */
public class Controlador<T extends Vista, E extends Evento<T>> {
	
	/** Colecci�n de vistas que observan los eventos disparados por el controlador. */
	private Collection<T> observadores;

	/**
	 * Constructor p�blico.
	 * 
	 */
	public Controlador() {
		observadores = new LinkedList<T>();
	}
	
	/**
	 * Agrega un observador al controlador. Los observadores
	 * agregados utilizando <code>addObserver</code> ser�n 
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
	 * @param args Argumentos que se pasar�n a la vista.
	 */
	protected void notifyObservers(E evento, Object... args) {
		for(T obs: observadores) {
			evento.resolver(obs, args);
		}
	}

}
