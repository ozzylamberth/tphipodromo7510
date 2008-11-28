package ar.uba.fi.tecnicas.tphipodromo.servicios.dtos;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ParticipanteDTO  implements IsSerializable {
	
	private Long id = new Long(0);
	private int nroParticipante = 0;
	private ResultadoDTO resultadoDTO;
	private CaballoDTO caballoDTO;
	private JockeyDTO jockeyDTO;
	private CarreraDTO carreraDTO;
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

	public ResultadoDTO getResultadoDTO() {
		return this.resultadoDTO;
	}

	public void setResultadoDTO(ResultadoDTO resultadoDTO) {
		this.resultadoDTO = resultadoDTO;
	}

	public CaballoDTO getCaballoDTO() {
		return this.caballoDTO;
	}

	public void setCaballoDTO(CaballoDTO caballoDTO) {
		this.caballoDTO = caballoDTO;
	}

	public JockeyDTO getJockeyDTO() {
		return this.jockeyDTO;
	}

	public void setJockeyDTO(JockeyDTO jockeyDTO) {
		this.jockeyDTO = jockeyDTO;
	}

	public CarreraDTO getCarreraDTO() {
		return this.carreraDTO;
	}

	public void setCarreraDTO(CarreraDTO carreraDTO) {
		this.carreraDTO = carreraDTO;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
