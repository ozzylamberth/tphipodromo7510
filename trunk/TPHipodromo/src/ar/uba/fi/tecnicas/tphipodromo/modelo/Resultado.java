package ar.uba.fi.tecnicas.tphipodromo.modelo;

/**
 * Representa el Resultado de un Participante dentro de una carrera
 * 
 * @author Fernando E. Mansilla - 84567
 * 
 * @version 1.1 Fernando E. Mansilla - 84567 Se introdujo un enum de estados.
 */
public class Resultado implements Identificable {
	
	private Long id;
	private int ordenLlegada;
	private long tiempo;

	public Resultado() {
		this.ordenLlegada = 0;
		this.tiempo = 0;
	}

	public Resultado(Integer ordenLlegada, long tiempoMilis) {
		setOrdenLlegada(ordenLlegada);
		setTiempo(tiempoMilis);
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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