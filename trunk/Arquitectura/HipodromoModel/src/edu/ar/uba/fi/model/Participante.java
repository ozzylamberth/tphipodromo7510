package edu.ar.uba.fi.model;

import java.math.BigDecimal;

public class Participante {
	private int nroParticipante;
	private BigDecimal peso;
	private ResultadoCarrera resultado;
	private Caballo caballo;
	private Jinete jinete;
	private Carrera carrera;
	
	public Participante(Caballo caballo, Jinete jinete, Carrera carrera) {
		this.caballo = caballo;
		this.jinete = jinete;
		this.carrera = carrera;
	}

	public int getNroParticipante() {
		return this.nroParticipante;
	}

	public void setNroParticipante(int nroParticipante) {
		this.nroParticipante = nroParticipante;
	}

	public BigDecimal getPeso() {
		return this.peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public ResultadoCarrera getResultado() {
		return this.resultado;
	}

	public void setResultado(ResultadoCarrera resultado) {
		this.resultado = resultado;
	}

	public Caballo getCaballo() {
		return this.caballo;
	}

	public void setCaballo(Caballo caballo) {
		this.caballo = caballo;
	}

	public Jinete getJinete() {
		return this.jinete;
	}

	public void setJinete(Jinete jinete) {
		this.jinete = jinete;
	}

	public Carrera getCarrera() {
		return this.carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

}