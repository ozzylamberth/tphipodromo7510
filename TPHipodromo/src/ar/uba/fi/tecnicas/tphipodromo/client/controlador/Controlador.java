package ar.uba.fi.tecnicas.tphipodromo.client.controlador;

import java.util.Collection;
import java.util.LinkedList;

import ar.uba.fi.tecnicas.tphipodromo.client.controlador.evento.Evento;
import ar.uba.fi.tecnicas.tphipodromo.client.vista.Vista;

/**
 * Controlador gen�rico que implementa el patr�n Observer.
 * 
 * @author Juan
 *
 * @param <T> Tipo de vista que responde a los eventos que dispara el controlador.
 * @param <E> Enumerado con los eventos que dispara el controlador.
 */
public class Controlador {
	
	/** Colecci�n de vistas que observan los eventos disparados por el controlador. */
	private Collection<Vista> observadores;

	/**
	 * Constructor p�blico.
	 * 
	 */
	public Controlador() {
		observadores = new LinkedList<Vista>();
	}
	
	/**
	 * Agrega un observador al controlador. Los observadores
	 * agregados utilizando <code>addObserver</code> ser�n 
	 * informados de todos los eventos que dispare el controlador.
	 * 
	 * @param obs Vista a agregar como observadora del controlador.
	 */
	public void addObserver(Vista obs) {
		observadores.add(obs);
	}
	
	/**
	 * Dispara un evento y notifica a todos los observadores.
	 * 
	 * @param evento Evento a disparar.
	 * @param args Argumentos que se pasar�n a la vista.
	 */
	protected void notifyObservers(Evento evento, Object... args) {
		for(Vista obs: observadores) {
			evento.resolver(obs, args);
		}
	}

}
