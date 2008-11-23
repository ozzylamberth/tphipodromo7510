package ar.uba.fi.tecnicas.tphipodromo.servicios.dtos;

import java.util.Collection;
import java.util.Date;

public class ApuestaDTO {
	
	private Long id = new Long(0);
	private String tipoApuesta = "";
	private String estado = "";
	private Double montoApostado = new Double(0);
	private long nroTicket = 0;
	private Date fechaCreacion = new Date();
	private Collection<Long> participantesIds;
	private int diasPlazoMaxDeCobro = 0;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoApuesta() {
		return this.tipoApuesta;
	}

	public void setTipoApuesta(String tipo) {
		this.tipoApuesta = tipo;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Double getMontoApostado() {
		return this.montoApostado;
	}

	public void setMontoApostado(Double montoApostado) {
		this.montoApostado = montoApostado;
	}

	public long getNroTicket() {
		return this.nroTicket;
	}

	public void setNroTicket(long nroTicket) {
		this.nroTicket = nroTicket;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Collection<Long> getParticipantesIds() {
		return this.participantesIds;
	}

	public void setParticipantesIds(Collection<Long> participanteIds) {
		this.participantesIds = participanteIds;
	}

	public int getDiasPlazoMaxDeCobro() {
		return this.diasPlazoMaxDeCobro;
	}

	public void setDiasPlazoMaxDeCobro(int diasPlazoMaxDeCobro) {
		this.diasPlazoMaxDeCobro = diasPlazoMaxDeCobro;
	}
	
}
