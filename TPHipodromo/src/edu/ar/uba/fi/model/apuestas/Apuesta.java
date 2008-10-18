package edu.ar.uba.fi.model.apuestas;

import java.math.BigDecimal;
import java.util.ArrayList;

import edu.ar.uba.fi.model.Participante;

/**
 * Representa la logica general de los distintos tipos de apuestas
 */
public abstract class Apuesta {
	private BolsaApuestas bolsaApuestas;
	private String estado;
	private BigDecimal montoApostado;
	private long nroTicket;
	private ArrayList<Participante> participantes = new ArrayList<Participante>();
	private int diasPlazoMaxDeCobro;

	/**
	 * Este metodo debe verificar si la apuesta fue ganada. En el caso de que
	 * asi suceda, debe retornar true, en el caso contrario false
	 */
	public abstract boolean isAcertada();

	private BigDecimal calcularMontoAPagar() {
		// TODO: implementar logica
		return null;
	}

	public BigDecimal liquidar() {
		// TODO: implementar logica
		return null;
	}

	public boolean isPagada() {
		// TODO: implementar logica
		return false;
	}

	public BolsaApuestas getBolsaApuestas() {
		return this.bolsaApuestas;
	}

	public void setBolsaApuestas(BolsaApuestas bolsaApuestas) {
		this.bolsaApuestas = bolsaApuestas;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public ArrayList<Participante> getParticipantes() {
		return this.participantes;
	}

	public void setParticipantes(ArrayList<Participante> participantes) {
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