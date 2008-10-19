package edu.ar.uba.fi.model.apuestas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import edu.ar.uba.fi.exceptions.ApuestaPerdidaException;
import edu.ar.uba.fi.exceptions.ApuestaVencidaException;
import edu.ar.uba.fi.exceptions.CantidadParticipantesInvalidaException;
import edu.ar.uba.fi.exceptions.CarreraNoFinalizadaException;
import edu.ar.uba.fi.exceptions.TransicionInvalidaEstadoApuestaException;
import edu.ar.uba.fi.model.Participante;

/**
 * Representa la logica general de los distintos tipos de apuestas
 */
public abstract class Apuesta {
	private BolsaApuestas bolsaApuestas;
	private EstadoApuesta estadoApuesta;
	private BigDecimal montoApostado;
	private long nroTicket;
	private List<Participante> participantes = new ArrayList<Participante>();
	private Date fechaCreacion;
	private int diasPlazoMaxDeCobro;

	public Apuesta() {
		this.setEstadoApuesta(EstadoApuesta.CREADA);
		this.setFechaCreacion(new Date());
		
	}
	
	public Apuesta(List<Participante> participantes) throws CantidadParticipantesInvalidaException {
		this.validarCantidadParticipantes(participantes);
		this.setEstadoApuesta(EstadoApuesta.CREADA);
		this.setParticipantes(participantes);
	}
	
	private void validarCantidadParticipantes(List<Participante> participantes) throws CantidadParticipantesInvalidaException {
		if (participantes.size() != this.getCantidadParticipantes()) {
			throw new CantidadParticipantesInvalidaException();
		}
	}

	public abstract BigDecimal getValorBase();
	
	/**
	 * Retorna la cantidad de participantes permitidas para el tipo de apuesta
	 */
	public abstract int getCantidadParticipantes();
	
	/**
	 * Retorna las posiciones en las cuales deben salir los participantes
	 * para que la apuesta se considere como ganada
	 */
	public abstract List<Integer> getPosiblesOrdenesLLegada();
	
	/**
	 * Verifica si la apuesta fue ganada. En el caso de que asi suceda, retorna
	 * true, en caso contrario false
	 */
	public boolean isAcertada() {
		Iterator<Participante> it = this.getParticipantes().iterator();
		while (it.hasNext()) {
			Participante participante = (Participante) it.next();
			int ordenLLegada = participante.getResultado().getOrdenLlegada(); 
			if (!this.getPosiblesOrdenesLLegada().contains(new Integer(ordenLLegada))) {
				return false;
			}
		}
		return true;
	}

	private BigDecimal calcularMontoAPagar() {
		BigDecimal proporcion = this.getMontoApostado().divide(this.getValorBase());
		return proporcion.multiply(this.getBolsaApuestas().getDividendo());
	}

	public BigDecimal liquidar() throws CarreraNoFinalizadaException, ApuestaPerdidaException, TransicionInvalidaEstadoApuestaException, ApuestaVencidaException {
		this.validarEstadoLiquidacion();
		BigDecimal montoAPagar = this.calcularMontoAPagar();
		this.cambiarEstado(EstadoApuesta.CREADA, EstadoApuesta.LIQUIDADA);
		return montoAPagar;
	}
	
	public void pagar() throws TransicionInvalidaEstadoApuestaException {
		this.cambiarEstado(EstadoApuesta.LIQUIDADA, EstadoApuesta.PAGADA);
	}
	
	/**
	 * Valida si esta en el estado correcto para liquidar
	 */
	private void validarEstadoLiquidacion() throws CarreraNoFinalizadaException, ApuestaPerdidaException, ApuestaVencidaException {
		this.validarApuestaGanada();
		this.validarCarrerasFinalizadas();
		this.validarFechaVencimiento();
	}
	
	private void validarApuestaGanada() throws ApuestaPerdidaException {
		if (!this.isAcertada()) {
			throw new ApuestaPerdidaException();
		}
	}
	
	/**
	 * Valida que todas las carreras correspondientes a los participantes
	 * se encuentren finalizadas
	 */
	private void validarCarrerasFinalizadas() throws CarreraNoFinalizadaException {
		Iterator<Participante> it = this.getParticipantes().iterator();
		while (it.hasNext()) {
			Participante participante = (Participante) it.next();
			if (!participante.getCarrera().isFinalizada()) {
				throw new CarreraNoFinalizadaException();
			}
		}
	}
	
	private void validarFechaVencimiento() throws ApuestaVencidaException {
		long milisegundos = new Date().getTime() - this.getFechaCreacion().getTime();
		long dias = milisegundos / (1000 * 60 * 60 * 24);
		if (dias > this.getDiasPlazoMaxDeCobro()) {
			throw new ApuestaVencidaException();
		}
	}

	public boolean isPagada() {
		return (EstadoApuesta.PAGADA.equals(this.getEstadoApuesta()));
	}
	
	private void cambiarEstado(EstadoApuesta estadoAnterior, EstadoApuesta nuevoEstado) throws TransicionInvalidaEstadoApuestaException {
		if (!estadoAnterior.equals(this.getEstadoApuesta())) {
			throw new TransicionInvalidaEstadoApuestaException();
		}
		this.setEstadoApuesta(nuevoEstado);
	}

	public BolsaApuestas getBolsaApuestas() {
		return this.bolsaApuestas;
	}

	public void setBolsaApuestas(BolsaApuestas bolsaApuestas) {
		this.bolsaApuestas = bolsaApuestas;
	}

	public EstadoApuesta getEstadoApuesta() {
		return this.estadoApuesta;
	}

	public void setEstadoApuesta(EstadoApuesta estado) {
		this.estadoApuesta = estado;
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

	public List<Participante> getParticipantes() {
		return this.participantes;
	}

	public void setParticipantes(List<Participante> participantes) {
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