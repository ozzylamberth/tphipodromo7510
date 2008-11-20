package ar.uba.fi.tecnicas.tphipodromo.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.CantidadParticipantesInvalidaException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.HipodromoComposedException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.HipodromoException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.InscripcionCarreraCerradaException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.ParticipanteNoCalificadoException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.ParticipantesEnDistintasCarrerasException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.ResultadosCarreraInvalidosException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.TransicionInvalidaEstadoCarreraException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.TransicionInvalidaEstadoParticipanteException;


/**
 * Clase que encapsula la información de la carrera y contiene la lógica que
 * rige los cambios de estado.
 * 
 * @versión 1.0 Nahuel
 * @version 1.1 Fernando E. Mansilla - 84567 La funcionalidad encargada de
 *          controlar los cambios de estados se la pasé al enumerado
 *          EstadoCarrera.
 * @version 2.0 Fernando E. Mansilla - 84567 Nueva estructura de manejo de
 *          cambio de estados.
 */
public class Carrera implements Comparable<Carrera>, InterfaceAuditor,
		InterfaceDetectorLlegada {
	private BigDecimal distancia;
	private Date fechaYHora;
	private String nombre;
	private int numero;
	private EstadoCarrera estadoCarrera;
	private List<Participante> participantes = new ArrayList<Participante>();
	private final ReglamentoCarrera reglamentoParticipantes;

	public Carrera(ReglamentoCarrera reglamentoParticipantes) {
		this.reglamentoParticipantes = reglamentoParticipantes;
		setEstadoCarrera(EstadoCarrera.INSCRIPCION_PARTICIPANTES);
	}

	// Cambios de estado
	// ------------------------------------------
	/**
	 * Cierra la etapa de inscripción de participantes y comienza la toma de
	 * apuestas.<br>
	 * <p>
	 * <b>Se controla que:</b>
	 * <ul>
	 * <li>Se cumpla con la cantidad minima y máxima de participantes
	 * habilitados para correr (Que están en estado PENDIENTE_LARGADA)</li>
	 * <li>La carrera este en un estado que permita Iniciar la toma de
	 * Apuestas.</li>
	 * </ul>
	 * </p>
	 * <p>
	 * <b>Post Condiciones:</b>
	 * <ul>
	 * <li> Se cambia el estado de la carrera a ABIERTA_A_APUESTAS.</li>
	 * </ul>
	 * </p>
	 * 
	 * @throws HipodromoException
	 */
	public void abrirApuestas() throws HipodromoException {

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
			cambiarEstado(EstadoCarrera.ABIERTA_A_APUESTAS);
		} catch (TransicionInvalidaEstadoCarreraException e) {
			exception.add(e);
		}
		if (exception.hasExceptions()) {
			throw exception;
		}
	}

	/**
	 * Cierra la etapa de recepcion de apuestas para la carrera. <br>
	 * <p>
	 * <b>Se controla que:</b>
	 * <ul>
	 * <li>Se cumpla con la cantidad minima y máxima de participantes
	 * habilitados para correr (Que están en estado PENDIENTE_LARGADA)</li>
	 * <li>La carrera este en un estado que permita Cerrar la toma de Apuestas.</li>
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
	public void cerrarApuestas() throws HipodromoException {

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
	 * <li>Se cumpla con la cantidad minima y máxima de participantes
	 * habilitados para correr (Que están en estado PENDIENTE_LARGADA)</li>
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

	/**
	 * Cancela la carrera, dejandola en tal estado definitivamente.
	 * 
	 * @throws HipodromoException
	 */
	public void cancelar() throws HipodromoException {
		cambiarEstado(this.estadoCarrera.cancelar());
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

	public void setEstadoCarrera(EstadoCarrera estadoCarrera) {
		this.estadoCarrera = estadoCarrera;
	}

	public EstadoCarrera getEstadoCarrera() {
		return this.estadoCarrera;
	}

	public List<Participante> getParticipantes() {
		return this.participantes;
	}

	/**
	 * Setea el conjunto de participantes a la carrera
	 * 
	 * @param participantes
	 *            Participantes a vincular a la carrera.
	 * @throws HipodromoException
	 *             Excepcion compuesta por todos las excepciones ocurridas en el
	 *             proceso.
	 */
	public void setParticipantes(ArrayList<Participante> participantes)
			throws HipodromoException {

		HipodromoComposedException exception = new HipodromoComposedException();
		Iterator<Participante> it = participantes.iterator();
		while (it.hasNext()) {
			Participante participante = (Participante) it.next();

			try {
				this.addParticipante(participante);
			} catch (ParticipanteNoCalificadoException e) {
				exception.add(e);
			}
		}

		if (exception.hasExceptions()) {
			throw exception;
		}
	}

	/**
	 * @param participante
	 * @throws ParticipanteNoCalificadoException
	 *             Si no cumple con el reglamento o bien su estado no es
	 *             Pendiente de Largada.
	 * @throws ParticipantesEnDistintasCarrerasException
	 *             Si ya tiene asignada una Carrera distinta a la actual.
	 * @throws InscripcionCarreraCerradaException
	 *             Si la carrera ya no puede aceptar nuevos participantes, ya
	 *             sea por su estado o porque se alcanzo el máximo.
	 */
	public void addParticipante(Participante participante)
			throws ParticipanteNoCalificadoException,
			ParticipantesEnDistintasCarrerasException,
			InscripcionCarreraCerradaException {

		if (!participante.getEstado().equals(
				EstadoParticipante.LARGADA_PENDIENTE)) {
			throw new ParticipanteNoCalificadoException();
		}

		if (!this.estadoCarrera.equals(EstadoCarrera.INSCRIPCION_PARTICIPANTES)
				|| participantes.size() > reglamentoParticipantes
						.getCantidadParticipantesMaxima()) {
			throw new InscripcionCarreraCerradaException();
		}

		if (!this.equals(participante.getCarrera())) {
			throw new ParticipantesEnDistintasCarrerasException();
		}

		if (reglamentoParticipantes.validarRequisitos(participante)) {
			participante.setCarrera(this);
			this.participantes.add(participante);
		} else {
			throw new ParticipanteNoCalificadoException();
		}
	}

	@Override
	public int compareTo(Carrera o) {
		return this.getNumero() - o.getNumero();
	}

}