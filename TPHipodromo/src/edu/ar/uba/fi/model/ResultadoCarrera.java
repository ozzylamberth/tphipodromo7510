package edu.ar.uba.fi.model;

import edu.ar.uba.fi.exceptions.ResultadosCarreraInvalidosExeption;
import edu.ar.uba.fi.exceptions.TransicionInvalidaEstadoResultadoCarreraException;

/**
 * Representa el Resultado de un Participante dentro de una carrera
 * 
 * @version 1.1 Fernando E. Mansilla - 84567 Se introdujo un enum de estados.
 */
public class ResultadoCarrera {
	private int ordenLlegada;
	private int tiempo;
	private EstadoResultadoCarrera estado;
	private Participante participante;

	public ResultadoCarrera(Participante participante) {
		setParticipante(participante);
		setEstado(EstadoResultadoCarrera.EN_CURSO);
	}

	public ResultadoCarrera(Participante participante, Integer ordenLlegada) {
		this(participante);
		this.setOrdenLlegada(ordenLlegada);
	}

	public int getOrdenLlegada() throws ResultadosCarreraInvalidosExeption {
		if (estado.equals(EstadoResultadoCarrera.FINALIZADO)) {
			return this.ordenLlegada;
		} else {
			throw new ResultadosCarreraInvalidosExeption();
		}
	}

	private void setEstado(EstadoResultadoCarrera estado) {
		this.estado = estado;
	}

	private void cambiarEstado(EstadoResultadoCarrera nuevoEstado)
			throws TransicionInvalidaEstadoResultadoCarreraException {
		if (estado.esSiguienteEstadoValido(nuevoEstado)) {
			setEstado(nuevoEstado);
		} else {
			throw new TransicionInvalidaEstadoResultadoCarreraException(estado,
					nuevoEstado);
		}
	}

	public void setDescalificado()
			throws TransicionInvalidaEstadoResultadoCarreraException {
		cambiarEstado(EstadoResultadoCarrera.DESCALIFICADO);
	}

	public void setAbandono()
			throws TransicionInvalidaEstadoResultadoCarreraException {
		cambiarEstado(EstadoResultadoCarrera.ABANDONO);
	}

	public void setOrdenLlegada(int ordenLlegada) {
		if (estado.equals(EstadoResultadoCarrera.FINALIZADO)
				|| estado
						.esSiguienteEstadoValido(EstadoResultadoCarrera.FINALIZADO)) {
			this.ordenLlegada = ordenLlegada;
			setEstado(EstadoResultadoCarrera.FINALIZADO);
		}
	}

	public int getTiempo() {
		return this.tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public EstadoResultadoCarrera getEstado() {
		return this.estado;
	}

	public Participante getParticipante() {
		return this.participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

}