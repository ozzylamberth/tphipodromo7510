package edu.ar.uba.fi.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class Carrera {
	private BigDecimal distancia;
	private Date fechaYHora;
	private String nombre;
	private int numero;
	private String estado;
	private ArrayList<Participante> participantes;

	public ResultadoCarrera getResultado(Participante Participante) {
		// TODO: implementar logica
		return null;
	}

	public void cerrarApuestas() {
		// TODO: implementar logica
	}

	public void comenzar() {
		// TODO: implementar logica
	}

	public void aprobarResultados() {
		// TODO: implementar logica
	}

	public void finalizar(ResultadoCarrera[] Resultado) {
		// TODO: implementar logica
	}

	public void cancelar() {
		// TODO: implementar logica
	}

	public BigDecimal getDistancia() {
		return this.distancia;
	}

	public void setDistancia(BigDecimal distancia) {
		this.distancia = distancia;
	}

	public Date getFechaYHora() {
		return this.fechaYHora;
	}

	public void setFechaYHora(Date fechaYHora) {
		this.fechaYHora = fechaYHora;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public ArrayList<Participante> getParticipantes() {
		return this.participantes;
	}

	public void setParticipantes(ArrayList<Participante> participantes) {
		this.participantes = participantes;
	}

}