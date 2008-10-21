package edu.ar.uba.fi.model;

/**
 * Representa el Resultado de un Participante dentro de una carrera
 */
public class ResultadoCarrera {
	private int ordenLlegada;
	private int tiempo;
	private String estado;
	private Participante participante;
	
	public ResultadoCarrera(Participante participante) {
		this.setParticipante(participante);
	}
	
	public ResultadoCarrera(Participante participante, Integer ordenLlegada) {
		this(participante);
		this.setOrdenLlegada(ordenLlegada);
	}

	public int getOrdenLlegada() {
		return this.ordenLlegada;
	}

	public void setOrdenLlegada(int ordenLlegada) {
		this.ordenLlegada = ordenLlegada;
	}

	public int getTiempo() {
		return this.tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Participante getParticipante() {
		return this.participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

}