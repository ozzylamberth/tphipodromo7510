package edu.ar.uba.fi.model.apuestas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import edu.ar.uba.fi.exceptions.ApuestaPerdidaException;
import edu.ar.uba.fi.exceptions.ApuestaVencidaException;
import edu.ar.uba.fi.exceptions.CantidadParticipantesInvalidaException;
import edu.ar.uba.fi.exceptions.CarreraCerradaAApuestasException;
import edu.ar.uba.fi.exceptions.CarreraNoFinalizadaException;
import edu.ar.uba.fi.exceptions.HipodromoComposedException;
import edu.ar.uba.fi.exceptions.HipodromoException;
import edu.ar.uba.fi.exceptions.ParticipantesEnDistintasCarrerasException;
import edu.ar.uba.fi.exceptions.ResultadosCarreraInvalidosException;
import edu.ar.uba.fi.exceptions.TransicionInvalidaEstadoApuestaException;
import edu.ar.uba.fi.model.Participante;

/**
 * Representa la logica general de los distintos tipos de apuestas
 * 
 * @version 1.1 Fernando E. Mansilla - 84567 La funcionalidad encargada de
 *          controlar los cambios de estados se la pasé al enumerado
 *          EstadoApuesta.
 */
public abstract class Apuesta {
	/**
	 * Representa la cantidad de decimales con la que se estará realizando los
	 * cálculos de dinero en las apuestas.
	 */
	private final static int CANT_DECIMALES = 2;

	private BolsaApuestasAbstracta bolsaApuestas;
	private EstadoApuesta estadoApuesta;
	private BigDecimal montoApostado;
	private long nroTicket;
	private Collection<Participante> participantes = new ArrayList<Participante>();
	private Date fechaCreacion;
	private int diasPlazoMaxDeCobro;

	public Apuesta() {
		setEstadoApuesta(EstadoApuesta.CREADA);
		setFechaCreacion(new Date());
	}

	public abstract BigDecimal getValorBase();

	/**
	 * Retorna la cantidad de participantes permitidas para el tipo de apuesta
	 */
	public abstract int getCantidadParticipantes();

	/**
	 * Retorna las posiciones en las cuales deben salir los participantes para
	 * que la apuesta se considere como ganada
	 */
	public abstract List<Integer> getPosiblesOrdenesLLegada();

	/**
	 * Verifica si la apuesta fue ganada. En el caso de que asi suceda, retorna
	 * true, en caso contrario false
	 * @throws HipodromoException 
	 */
	public boolean isAcertada() throws HipodromoException {
		Iterator<Participante> it = this.getParticipantes().iterator();
		HipodromoComposedException composedException = new HipodromoComposedException();
		while (it.hasNext()) {
			Participante participante = (Participante) it.next();
			try {
				int ordenLLegada = participante.getResultado()
						.getOrdenLlegada();
//TODO comtemplar que si abandono para que no sea un error
				if (!this.getPosiblesOrdenesLLegada().contains(
						new Integer(ordenLLegada))) {
					return false;
				}
			} catch (ResultadosCarreraInvalidosException e) {
				composedException.add(e);
				//				
				// System.out.println("invalidos!!!!");
				// return false;
			}
		}

		if (composedException.hasExceptions()) {
			throw composedException;
		} else {
			return true;
		}
	}

	private BigDecimal calcularMontoAPagar() throws HipodromoException {
		BigDecimal proporcion = this.getMontoApostado().divide(
				this.getValorBase(), CANT_DECIMALES, BigDecimal.ROUND_HALF_UP);
		BigDecimal montoAPagar = proporcion.multiply(this.getBolsaApuestas()
				.getDividendo());
		// si el monto a pagar es menor al monto apostado
		if (this.getMontoApostado().compareTo(montoAPagar) > 0) {
			return this.getMontoApostado();
		} else {
			return montoAPagar;
		}
	}

	public BigDecimal liquidar() throws HipodromoException {
		this.validarEstadoLiquidacion();
		BigDecimal montoAPagar = this.calcularMontoAPagar();
		this.cambiarEstado(EstadoApuesta.LIQUIDADA);
		return montoAPagar;
	}

	public void pagar() throws TransicionInvalidaEstadoApuestaException {
		this.cambiarEstado(EstadoApuesta.PAGADA);
	}

	/**
	 * Valida si esta en el estado correcto para liquidar
	 * @throws HipodromoException 
	 */
	private void validarEstadoLiquidacion()
			throws HipodromoException {
		this.validarCarrerasFinalizadas();
		this.validarApuestaGanada();
		this.validarFechaVencimiento();
	}

	private void validarApuestaGanada() throws HipodromoException {
		if (!this.isAcertada()) {
			throw new ApuestaPerdidaException();
		}
	}

	/**
	 * Valida que todas las carreras correspondientes a los participantes se
	 * encuentren finalizadas
	 */
	private void validarCarrerasFinalizadas()
			throws CarreraNoFinalizadaException {
		Iterator<Participante> it = this.getParticipantes().iterator();
		while (it.hasNext()) {
			Participante participante = (Participante) it.next();
			if (!participante.getCarrera().isFinalizada()) {
				throw new CarreraNoFinalizadaException();
			}
		}
	}

	private void validarFechaVencimiento() throws ApuestaVencidaException,
			TransicionInvalidaEstadoApuestaException {
		long milisegundos = new Date().getTime()
				- this.getFechaCreacion().getTime();
		long dias = milisegundos / (1000 * 60 * 60 * 24);
		if (dias > this.getDiasPlazoMaxDeCobro()) {
			this.cambiarEstado(EstadoApuesta.VENCIDA);
			throw new ApuestaVencidaException();
		}
	}

	public boolean isPagada() {
		return (EstadoApuesta.PAGADA.equals(this.getEstadoApuesta()));
	}

	/**
	 * @param nuevoEstado
	 * @throws TransicionInvalidaEstadoApuestaException
	 */
	private void cambiarEstado(EstadoApuesta nuevoEstado)
			throws TransicionInvalidaEstadoApuestaException {
		if (this.estadoApuesta.esSiguienteEstadoValido(nuevoEstado)) {
			setEstadoApuesta(nuevoEstado);
		} else {
			throw new TransicionInvalidaEstadoApuestaException();
		}
	}

	public BolsaApuestasAbstracta getBolsaApuestas() {
		return this.bolsaApuestas;
	}

	public void setBolsaApuestas(BolsaApuestasAbstracta bolsaApuestas) {
		this.bolsaApuestas = bolsaApuestas;
	}

	public EstadoApuesta getEstadoApuesta() {
		return this.estadoApuesta;
	}

	public void setEstadoApuesta(EstadoApuesta estadoApuesta) {
		this.estadoApuesta = estadoApuesta;
	}

	public BigDecimal getMontoApostado() {
		return this.montoApostado;
	}

	public void setMontoApostado(BigDecimal montoApostado) {
		this.montoApostado = montoApostado;
	}

	public long getNroTicket() {
		return this.nroTicket;
	}

	public void setNroTicket(long nroTicket) {
		this.nroTicket = nroTicket;
	}

	public Collection<Participante> getParticipantes() {
		return this.participantes;
	}

	private void validarCantidadParticipantes(
			Collection<Participante> participantes)
			throws CantidadParticipantesInvalidaException {
		if (participantes.size() != this.getCantidadParticipantes()) {
			throw new CantidadParticipantesInvalidaException();
		}
	}

	public void validarCarreraCerradaAApuestas(
			Collection<Participante> participantes)
			throws CarreraCerradaAApuestasException {
		for (Participante participante : participantes) {
			if (participante.getCarrera().isCerradaAApuestas()) {
				throw new CarreraCerradaAApuestasException();
			}
		}
	}

	public void setParticipantes(Collection<Participante> participantes)
			throws CantidadParticipantesInvalidaException,
			CarreraCerradaAApuestasException,
			ParticipantesEnDistintasCarrerasException {
		this.validarCarreraCerradaAApuestas(participantes);
		this.validarCantidadParticipantes(participantes);
		this.participantes = participantes;
	}

	public void addParticipante(Participante participante) {
		this.participantes.add(participante);
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public int getDiasPlazoMaxDeCobro() {
		return this.diasPlazoMaxDeCobro;
	}

	public void setDiasPlazoMaxDeCobro(int diasPlazoMaxDeCobro) {
		this.diasPlazoMaxDeCobro = diasPlazoMaxDeCobro;
	}

}