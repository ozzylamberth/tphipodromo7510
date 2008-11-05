package edu.ar.uba.fi.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import edu.ar.uba.fi.exceptions.CantidadParticipantesInvalidaException;
import edu.ar.uba.fi.exceptions.HipodromoComposedException;
import edu.ar.uba.fi.exceptions.HipodromoException;
import edu.ar.uba.fi.exceptions.ParticipanteNoCalificadoException;
import edu.ar.uba.fi.exceptions.ResultadosCarreraInvalidosException;
import edu.ar.uba.fi.exceptions.TransicionInvalidaEstadoCarreraException;
import edu.ar.uba.fi.exceptions.TransicionInvalidaEstadoParticipanteException;

/**
 * 
 * @versi�n 1.0 Nahuel
 * @version 1.1 Fernando E. Mansilla - 84567 La funcionalidad encargada de
 *          controlar los cambios de estados se la pas� al enumerado
 *          EstadoCarrera.
 * @version 2.0 Fernando E. Mansilla - 84567 Nueva estructura de manejo de
 *          cambio de estados.
 */
public class Carrera {
	private BigDecimal distancia;
	private Date fechaYHora;
	private String nombre;
	private int numero;
	private EstadoCarrera estadoCarrera;
	private List<Participante> participantes = new ArrayList<Participante>();
	private final ReglamentoCarrera reglamentoParticipantes;

	public Carrera(ReglamentoCarrera reglamentoParticipantes) {
		this.reglamentoParticipantes = reglamentoParticipantes;
		setEstadoCarrera(EstadoCarrera.ABIERTA_A_APUESTAS);
	}

	// Cambios de estado
	// ------------------------------------------
	/**
	 * Cierra la etapa de recepcion de apuestas para la carrera. <br>
	 * <p>
	 * <b>Se controla que:</b>
	 * <ul>
	 * <li>Se cumpla con la cantidad minima y m�xima de participantes
	 * habilitados para correr (Que est�n en estado PENDIENTE_LARGADA)</li>
	 * <li>La carrera este en un estado que permita dar Cerrar la toma de
	 * Apuestas.</li>
	 * </ul>
	 * </p>
	 * <p>
	 * <b>Post Condiciones:</b>
	 * <ul>
	 * <li> Se cambia el estado de la carrera a CERRADA_A_APUESTAS.</li>
	 * </ul>
	 * </p>
	 * 
	 * @throws HipodromoException
	 */
	public void cerrarApuestas()
			throws TransicionInvalidaEstadoCarreraException, HipodromoException {

		HipodromoComposedException exception = new HipodromoComposedException();
		Iterator<Participante> it = participantes.iterator();
		int conteoParticipantesHabilitados = 0;

		while (it.hasNext()) {
			Participante participante = it.next();
			if (participante.getEstado().equals(
					EstadoParticipante.LARGADA_PENDIENTE)) {
				conteoParticipantesHabilitados++;
			}
		}
		if (conteoParticipantesHabilitados < reglamentoParticipantes
				.getCantidadParticipantesMinima()
				|| conteoParticipantesHabilitados > reglamentoParticipantes
						.getCantidadParticipantesMaxima()) {
			exception.add(new CantidadParticipantesInvalidaException());
		}
		try {
			cambiarEstado(EstadoCarrera.CERRADA_A_APUESTAS);
		} catch (TransicionInvalidaEstadoCarreraException e) {
			exception.add(e);
		}
		if (exception.hasExceptions()) {
			throw exception;
		}
	}

	/**
	 * Da comienzo a la carrera. <br>
	 * <p>
	 * <b>Se controla que:</b>
	 * <ul>
	 * <li> Todos los participantes puedan pasar a estado EN_CURSO.</li>
	 * <li>Se cumpla con la cantidad minima y m�xima de participantes
	 * habilitados para correr (Que est�n en estado PENDIENTE_LARGADA)</li>
	 * <li>La carrera este en un estado que permita dar Comienzo a la misma.</li>
	 * </ul>
	 * </p>
	 * <p>
	 * <b>Post Condiciones:</b>
	 * <ul>
	 * <li>Se pasa el estado de todos los participantes en estado
	 * PENDIENTE_LARGADA a EN_CURSO.</li>
	 * <li> Se cambia el estado de la carrera a EN_CURSO.</li>
	 * </ul>
	 * </p>
	 * 
	 * @throws HipodromoException
	 */
	public void comenzar() throws HipodromoException {
		HipodromoComposedException exception = new HipodromoComposedException();
		Iterator<Participante> it = participantes.iterator();
		int conteoParticipantesHabilitados = 0;

		while (it.hasNext()) {
			try {

				Participante participante = it.next();
				if (!participante.getEstado().equals(
						EstadoParticipante.ABANDONO)) {
					participante.setEstado(EstadoParticipante.EN_CURSO);
					conteoParticipantesHabilitados++;
				}
			} catch (TransicionInvalidaEstadoParticipanteException e) {
				exception.add(e);
			}
		}
		if (conteoParticipantesHabilitados < reglamentoParticipantes
				.getCantidadParticipantesMinima()
				|| conteoParticipantesHabilitados > reglamentoParticipantes
						.getCantidadParticipantesMaxima()) {
			exception.add(new CantidadParticipantesInvalidaException());
		}
		try {
			cambiarEstado(EstadoCarrera.EN_CURSO);
		} catch (TransicionInvalidaEstadoCarreraException e) {
			exception.add(e);
		}
		if (exception.hasExceptions()) {
			throw exception;
		}
	}

	/**
	 * Finaliza la carrera y la deja a la espera de que se auditen los
	 * resultados. <br>
	 * <p>
	 * <b>Se controla que:</b>
	 * <ul>
	 * <li> Todos los participantes esten en estado ABANDONADO o bien EN_CURSO.</li>
	 * <li>La carrera este en un estado que permita finalizar la Misma.</li>
	 * </ul>
	 * </p>
	 * <p>
	 * <b>Post Condiciones:</b>
	 * <ul>
	 * <li>Se pasa el estado de todos los participantes en estado EN_CURSO a
	 * A_AUDITAR.</li>
	 * <li> Se cambia el estado de la carrera a A_AUDITAR.</li>
	 * </ul>
	 * </p>
	 * 
	 * @throws HipodromoException
	 */
	public void terminar() throws HipodromoException {

		Iterator<Participante> it = participantes.iterator();
		HipodromoComposedException exception = new HipodromoComposedException();

		while (it.hasNext()) {
			try {
				Participante participante = it.next();
				if (!participante.getEstado().equals(
						EstadoParticipante.ABANDONO)) {
					participante.setEstado(EstadoParticipante.A_AUDITAR);
				}
			} catch (TransicionInvalidaEstadoParticipanteException e) {
				exception.add(e);
			}
		}

		try {
			cambiarEstado(EstadoCarrera.A_AUDITAR);
		} catch (TransicionInvalidaEstadoCarreraException e) {
			exception.add(e);
		}

		if (exception.hasExceptions()) {
			throw exception;
		}
	}

	/**
	 * Finaliza completamente la carrera, asegurando que los resultados son
	 * correctos. <br>
	 * <p>
	 * <b>Se controla que:</b>
	 * <ul>
	 * <li> Todos los participantes esten en estado ABANDONADO, FINALIZADO o
	 * bien DESCALIFICADO.</li>
	 * <li>La carrera este en un estado que permita aprobar los resultados.</li>
	 * </ul>
	 * </p>
	 * <p>
	 * <b>Post Condiciones:</b>
	 * <ul>
	 * <li> Se cambia el estado de la carrera a FINALIZADA.</li>
	 * </ul>
	 * </p>
	 * 
	 * @throws HipodromoException
	 */
	public void aprobarResultados() throws HipodromoException {

		HipodromoComposedException exception = new HipodromoComposedException();
		Iterator<Participante> it = participantes.iterator();

		while (it.hasNext()) {
			Participante participante = it.next();
			if (!participante.getEstado().equals(EstadoParticipante.FINALIZADO)
					&& !participante.getEstado().equals(
							EstadoParticipante.ABANDONO)
					&& !participante.getEstado().equals(
							EstadoParticipante.DESCALIFICADO)) {
				exception.add(new ResultadosCarreraInvalidosException());
			}
		}

		try {
			cambiarEstado(EstadoCarrera.FINALIZADA);
		} catch (TransicionInvalidaEstadoCarreraException e) {
			exception.add(e);
		}
		if (exception.hasExceptions()) {
			throw exception;
		}
	}

	public void cancelar() throws HipodromoException {
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

	// private void setResultadosPendienteAprobacion(
	// List<ResultadoCarrera> resultados)
	// throws ResultadosCarreraInvalidosException {
	// if (resultados.size() != this.getParticipantes().size()) {
	// throw new ResultadosCarreraInvalidosException();
	// }
	// this.validarMismosParticipantes(resultados);
	// this.resultadosPendienteAprobacion = resultados;
	// }
	//
	// private void validarMismosParticipantes(List<ResultadoCarrera>
	// resultados)
	// throws ResultadosCarreraInvalidosException {
	// Iterator<ResultadoCarrera> it = resultados.iterator();
	// while (it.hasNext()) {
	// ResultadoCarrera resultadoCarrera = (ResultadoCarrera) it.next();
	// if (!this.getParticipantes().contains(
	// resultadoCarrera.getParticipante())) {
	// throw new ResultadosCarreraInvalidosException();
	// }
	// }
	// }

	// private void asignarResultadosAParticipantes() {
	// Iterator<ResultadoCarrera> it = this.resultadosPendienteAprobacion
	// .iterator();
	// while (it.hasNext()) {
	// ResultadoCarrera resultadoCarrera = (ResultadoCarrera) it.next();
	// resultadoCarrera.getParticipante().setResultado(resultadoCarrera);
	// }
	// }

	public void setEstadoCarrera(EstadoCarrera estadoCarrera) {
		this.estadoCarrera = estadoCarrera;
	}

}