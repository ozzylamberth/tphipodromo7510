package edu.ar.uba.fi.model;


/**
 * Representa el Resultado de un Participante dentro de una carrera
 * 
 * @version 1.1 Fernando E. Mansilla - 84567 Se introdujo un enum de estados.
 */
public class Resultado {
	private int ordenLlegada;
	private long tiempo;

	public Resultado() {
		ordenLlegada = 0;
		tiempo = 0;
	}

	public Resultado(Integer ordenLlegada, long tiempoMilis) {
		setOrdenLlegada(ordenLlegada);
		setTiempo(tiempoMilis);
	}

	public int getOrdenLlegada() {
		return this.ordenLlegada;
	}

	public void setOrdenLlegada(int ordenLlegada) {
		this.ordenLlegada = ordenLlegada;
	}

	public long getTiempo() {
		return this.tiempo;
	}

	public void setTiempo(long tiempoMilis) {
		this.tiempo = tiempoMilis;
	}


}