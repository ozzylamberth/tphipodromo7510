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
 *          controlar los cambios de estados se la pas� al enumerado
 *          EstadoApuesta.
 */
public abstract class Apuesta {
	/**
	 * Representa la cantidad de decimales con la que se estar� realizando los
	 * c�lculos de dinero en las apuestas.
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
	 * @return Cantidad de participantes permitidas para el tipo de Apuesta.
	 */
	public abstract int getCantidadParticipantes();

	/**
	 * @return Posiciones en las cuales deben salir los participantes para que
	 *         la apuesta se considere como ganada.
	 */
	public abstract List<Integer> getPosiblesOrdenesLLegada();

	/**
	 * Verifica si la apuesta fue ganada.
	 * 
	 * @return true si la Apuesta esta acertada, false en caso contrario.
	 */
	public boolean isAcertada() {
		Iterator<Participante> it = this.getParticipantes().iterator();
		while (it.hasNext()) {
			Participante participante = (Participante) it.next();
			try {

				int ordenLLegada = participante.getResultado()
						.getOrdenLlegada();
				if (!this.getPosiblesOrdenesLLegada().contains(
						new Integer(ordenLLegada))) {
					return false;
				}
			} catch (ResultadosCarreraInvalidosException e) {
				System.out.println("invalidos!!!!");
				return false;
			}
		}

		return true;
	}

	/**
	 * @return Monto a pagar por la apuesta Ganada.
	 * @throws HipodromoException
	 *             Lanzada en caso de que no se pueda realizar la liquidaci�n o
	 *             bien no se pueda realizar el cambio de estado de la apuesta.
	 */
	public BigDecimal liquidar() throws HipodromoException {
		this.validarEstadoLiquidacion();
		BigDecimal montoAPagar = this.calcularMontoAPagar();
		this.cambiarEstado(EstadoApuesta.LIQUIDADA);
		return montoAPagar;
	}

	/**
	 * Cambia el estado de la apuesta a pagada.
	 * 
	 * @throws TransicionInvalidaEstadoApuestaException
	 */
	public void pagar() throws TransicionInvalidaEstadoApuestaException {
		this.cambiarEstado(EstadoApuesta.PAGADA);
	}

	/**
	 * @return true si la Apuesta ya fue pagada, false en caso contrario.
	 */
	public boolean isPagada() {
		return (EstadoApuesta.PAGADA.equals(this.getEstadoApuesta()));
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

	public int getDiasPlazoMaxDeCobro() {
		return this.diasPlazoMaxDeCobro;
	}

	public void setDiasPlazoMaxDeCobro(int diasPlazoMaxDeCobro) {
		this.diasPlazoMaxDeCobro = diasPlazoMaxDeCobro;
	}

	protected void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	// -----------------------------------------------------------------------------
	/**
	 * @return Monto a pagar por la Apuesta, suponiendo que es Ganadora. Si
	 *         valor calculado es menor al apostado, se paga el �ltimo.
	 * @throws HipodromoException
	 */
	private BigDecimal calcularMontoAPagar() throws HipodromoException {
		BigDecimal proporcion = this.getMontoApostado().divide(
				this.getValorBase(), CANT_DECIMALES, BigDecimal.ROUND_HALF_UP);
		BigDecimal montoAPagar = proporcion.multiply(this.getBolsaApuestas()
				.getDividendo());

		if (this.getMontoApostado().compareTo(montoAPagar) > 0) {
			return this.getMontoApostado();
		} else {
			return montoAPagar;
		}
	}

	/**
	 * Valida si esta en el estado correcto para liquidar
	 * 
	 * @throws HipodromoException
	 *             Errores que impiden la liquidaci�n.
	 */
	private void validarEstadoLiquidacion() throws HipodromoException {

		HipodromoComposedException composedException = new HipodromoComposedException();
		try {
			this.validarCarrerasFinalizadas();
		} catch (HipodromoException ex) {
			composedException.add(ex);
		}
		try {
			this.validarApuestaGanada();
		} catch (HipodromoException ex) {
			composedException.add(ex);
		}
		try {
			this.validarFechaVencimiento();
		} catch (HipodromoException ex) {
			composedException.add(ex);
		}

		if (composedException.hasExceptions()) {
			throw composedException;
		}
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

	private void validarCantidadParticipantes(
			Collection<Participante> participantes)
			throws CantidadParticipantesInvalidaException {
		if (participantes.size() != this.getCantidadParticipantes()) {
			throw new CantidadParticipantesInvalidaException();
		}
	}

}