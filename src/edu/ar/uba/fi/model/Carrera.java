package edu.ar.uba.fi.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import edu.ar.uba.fi.exceptions.ParticipanteNoCalificadoException;
import edu.ar.uba.fi.exceptions.ResultadosCarreraInvalidosException;
import edu.ar.uba.fi.exceptions.TransicionInvalidaEstadoCarreraException;

/**
 * 
 * @versión 1.0 Nahuel
 * @version 1.1 Fernando E. Mansilla - 84567 La funcionalidad encargada de
 *          controlar los cambios de estados se la pasé al enumerado
 *          EstadoCarrera.
 */
public class Carrera {
	private BigDecimal distancia;
	private Date fechaYHora;
	private String nombre;
	private int numero;
	private EstadoCarrera estadoCarrera;
	private List<Participante> participantes = new ArrayList<Participante>();
	private List<ResultadoCarrera> resultadosPendienteAprobacion;
	private final ReglamentoParticipanteCarrera reglamentoParticipantes;

	public Carrera(ReglamentoParticipanteCarrera reglamentoParticipantes) {
		this.reglamentoParticipantes = reglamentoParticipantes;
		setEstadoCarrera(EstadoCarrera.ABIERTA_A_APUESTAS);
	}

	// Cambios de estado
	// ------------------------------------------
	public void cerrarApuestas()
			throws TransicionInvalidaEstadoCarreraException {
		cambiarEstado(EstadoCarrera.CERRADA_A_APUESTAS);
	}

	public void comenzar() throws TransicionInvalidaEstadoCarreraException {
		cambiarEstado(EstadoCarrera.EN_CURSO);
	}

	public void terminar(List<ResultadoCarrera> resultados)
			throws ResultadosCarreraInvalidosException,
			TransicionInvalidaEstadoCarreraException {
		this.setResultadosPendienteAprobacion(resultados);
		cambiarEstado(EstadoCarrera.A_AUDITAR);
	}

	public void aprobarResultados()
			throws TransicionInvalidaEstadoCarreraException {
		cambiarEstado(EstadoCarrera.FINALIZADA);
		this.asignarResultadosAParticipantes();
	}

	public void cancelar() throws TransicionInvalidaEstadoCarreraException {
		setEstadoCarrera(this.estadoCarrera.cancelar());
	}

	private void cambiarEstado(EstadoCarrera nuevoEstado)
			throws TransicionInvalidaEstadoCarreraException {
		if (this.estadoCarrera.esSiguienteEstadoValido(nuevoEstado)) {
			setEstadoCarrera(nuevoEstado);
		} else {
			throw new TransicionInvalidaEstadoCarreraException();
		}
	}

	// ------------------------------------------

	public boolean isCerradaAApuestas() {
		return (EstadoCarrera.CERRADA_A_APUESTAS
				.equals(this.getEstadoCarrera()));
	}

	public boolean isFinalizada() {
		return (EstadoCarrera.FINALIZADA.equals(this.getEstadoCarrera()));
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

	public List<Participante> getParticipantes() {
		return this.participantes;
	}

	public void setParticipantes(ArrayList<Participante> participantes)
			throws ParticipanteNoCalificadoException {
		Iterator<Participante> it = participantes.iterator();
		while (it.hasNext()) {
			Participante participante = (Participante) it.next();
			this.addParticipante(participante);
		}
	}

	public void addParticipante(Participante participante)
			throws ParticipanteNoCalificadoException {
		if (reglamentoParticipantes.validarRequisitos(participante)) {
			participante.setCarrera(this);
			this.participantes.add(participante);
		} else {
			throw new ParticipanteNoCalificadoException();
		}
	}

	public void setResultadosPendienteAprobacion(
			List<ResultadoCarrera> resultados)
			throws ResultadosCarreraInvalidosException {
		if (resultados.size() != this.getParticipantes().size()) {
			throw new ResultadosCarreraInvalidosException();
		}
		this.validarMismosParticipantes(resultados);
		this.resultadosPendienteAprobacion = resultados;
	}

	private void validarMismosParticipantes(List<ResultadoCarrera> resultados)
			throws ResultadosCarreraInvalidosException {
		Iterator<ResultadoCarrera> it = resultados.iterator();
		while (it.hasNext()) {
			ResultadoCarrera resultadoCarrera = (ResultadoCarrera) it.next();
			if (!this.getParticipantes().contains(
					resultadoCarrera.getParticipante())) {
				throw new ResultadosCarreraInvalidosException();
			}
		}
	}

	private void asignarResultadosAParticipantes() {
		Iterator<ResultadoCarrera> it = this.resultadosPendienteAprobacion
				.iterator();
		while (it.hasNext()) {
			ResultadoCarrera resultadoCarrera = (ResultadoCarrera) it.next();
			resultadoCarrera.getParticipante().setResultado(resultadoCarrera);
		}
	}

	public List<ResultadoCarrera> getResultadosPendienteAprobacion() {
		return this.resultadosPendienteAprobacion;
	}

	public void setEstadoCarrera(EstadoCarrera estadoCarrera) {
		this.estadoCarrera = estadoCarrera;
	}

}