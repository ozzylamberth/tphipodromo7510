package edu.ar.uba.fi.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Iterator;

import edu.ar.uba.fi.exceptions.TransicionInvalidaEstadoCarreraException;

public class Carrera {
	private BigDecimal distancia;
	private Date fechaYHora;
	private String nombre;
	private int numero;
	private EstadoCarrera estadoCarrera;
	private List<Participante> participantes;

	public void cerrarApuestas() throws TransicionInvalidaEstadoCarreraException {
		this.cambiarEstado(EstadoCarrera.ABIERTA_A_APUESTAS, EstadoCarrera.CERRADA_A_APUESTAS);
	}

	public void comenzar() throws TransicionInvalidaEstadoCarreraException {
		this.cambiarEstado(EstadoCarrera.CERRADA_A_APUESTAS, EstadoCarrera.EN_CURSO);
	}

	public void finalizar(List<ResultadoCarrera> Resultado) throws TransicionInvalidaEstadoCarreraException {
		this.cambiarEstado(EstadoCarrera.A_AUDITAR, EstadoCarrera.FINALIZADA);
	}

	public void cancelar() throws TransicionInvalidaEstadoCarreraException {
		ArrayList<EstadoCarrera> estadosAnteriores = new ArrayList<EstadoCarrera>();
		estadosAnteriores.add(EstadoCarrera.ABIERTA_A_APUESTAS);
		estadosAnteriores.add(EstadoCarrera.CERRADA_A_APUESTAS);
		estadosAnteriores.add(EstadoCarrera.EN_CURSO);
		estadosAnteriores.add(EstadoCarrera.A_AUDITAR);
		this.cambiarEstado(estadosAnteriores, EstadoCarrera.CANCELADA);
	}
	
	private void cambiarEstado(EstadoCarrera estadoAnterior, EstadoCarrera nuevoEstado) throws TransicionInvalidaEstadoCarreraException {
		if (!estadoAnterior.equals(this.getEstadoCarrera())) {
			throw new TransicionInvalidaEstadoCarreraException();
		}
		this.setEstadoCarrera(nuevoEstado);
	}
	
	private void cambiarEstado(List<EstadoCarrera> estadosAnteriores, EstadoCarrera nuevoEstado) throws TransicionInvalidaEstadoCarreraException {
		Iterator<EstadoCarrera> it = estadosAnteriores.iterator();
		while (it.hasNext()) {
			EstadoCarrera estadoAnterior = (EstadoCarrera) it.next();
			if (this.getEstadoCarrera().equals(estadoAnterior)) {
				this.setEstadoCarrera(nuevoEstado);
				return;
			}
		}
		// si no esta en niguno de los estados anteriores
		throw new TransicionInvalidaEstadoCarreraException();
	}
	
	public void aprobarResultados() {
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

	public EstadoCarrera getEstadoCarrera() {
		return this.estadoCarrera;
	}

	public void setEstadoCarrera(EstadoCarrera estadoCarrera) {
		this.estadoCarrera = estadoCarrera;
	}

	public List<Participante> getParticipantes() {
		return this.participantes;
	}

	public void setParticipantes(ArrayList<Participante> participantes) {
		Iterator<Participante> it = participantes.iterator();
		while (it.hasNext()) {
			Participante participante = (Participante) it.next();
			this.addParticipante(participante);
		}
	}
	
	public void addParticipante(Participante participante) {
		participante.setCarrera(this);
		this.participantes.add(participante);
	}

}