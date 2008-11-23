package ar.uba.fi.tecnicas.tphipodromo.servicios.dtos;

public class ParticipanteDTO {
	
	private Long id = new Long(0);
	private int nroParticipante = 0;
	private Long resultadoId = new Long(0);
	private Long caballoId = new Long(0);
	private Long jockeyId = new Long(0);
	private Long carreraId = new Long(0);
	private String estado = "";

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNroParticipante() {
		return this.nroParticipante;
	}

	public void setNroParticipante(int nroParticipante) {
		this.nroParticipante = nroParticipante;
	}

	public Long getResultadoId() {
		return this.resultadoId;
	}

	public void setResultadoId(Long idResultado) {
		this.resultadoId = idResultado;
	}

	public Long getCaballoId() {
		return this.caballoId;
	}

	public void setCaballoId(Long idCaballo) {
		this.caballoId = idCaballo;
	}

	public Long getJockeyId() {
		return this.jockeyId;
	}

	public void setJockeyId(Long jockeyId) {
		this.jockeyId = jockeyId;
	}

	public Long getCarreraId() {
		return this.carreraId;
	}

	public void setCarreraId(Long idCarrera) {
		this.carreraId = idCarrera;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
