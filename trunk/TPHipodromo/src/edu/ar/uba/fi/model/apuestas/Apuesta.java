package edu.ar.uba.fi.model.apuestas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import edu.ar.uba.fi.exceptions.ApuestaPerdidaException;
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
	private int diasPlazoMaxDeCobro;

	/**
	 * Verifica si la apuesta fue ganada. En el caso de que asi suceda, retorna
	 * true, en caso contrario false
	 */
	public abstract boolean isAcertada();

	public abstract BigDecimal getValorBase();

	private BigDecimal calcularMontoAPagar() {
		BigDecimal proporcion = this.getMontoApostado().divide(
				this.getValorBase());
		return proporcion.multiply(this.getBolsaApuestas().getDividendo());
	}

	public BigDecimal liquidar() throws ApuestaPerdidaException {
		if (!this.isAcertada()) {
			throw new ApuestaPerdidaException();
		}
		return this.calcularMontoAPagar();
	}

	public boolean isPagada() {
		return (EstadoApuesta.PAGADA.equals(this.getEstadoApuesta()));
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

	public int getDiasPlazoMaxDeCobro() {
		return this.diasPlazoMaxDeCobro;
	}

	public void setDiasPlazoMaxDeCobro(int diasPlazoMaxDeCobro) {
		this.diasPlazoMaxDeCobro = diasPlazoMaxDeCobro;
	}

}