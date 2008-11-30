package ar.uba.fi.tecnicas.tphipodromo.servicios.transformers;

import org.apache.commons.collections.Transformer;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Participante;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.ResultadosCarreraInvalidosException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.JockeyDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ParticipanteDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ResultadoDTO;

public class ParticipanteTransformerToDTO implements Transformer {

	public Object transform(Object arg0) {
		Participante participante = (Participante) arg0;
		ParticipanteDTO participanteDTO = new ParticipanteDTO();
		participanteDTO.setId(participante.getId());
		participanteDTO.setNroParticipante(participante.getNroParticipante());
		participanteDTO.setResultadoDTO(this.getResultadoDTO(participante));
		participanteDTO.setCaballoDTO(this.getCaballoDTO(participante));
		participanteDTO.setJockeyDTO(this.getJockeyDTO(participante));
		participanteDTO.setCarreraDTO(this.getCarreraDTO(participante));
		participanteDTO.setEstado(participante.getEstado().getNombre());
		return participanteDTO;
	}
	
	private ResultadoDTO getResultadoDTO(Participante participante) {
		try {
			return (ResultadoDTO) (new ResultadoTransformerToDTO().transform(participante.getResultado()));
		} catch (ResultadosCarreraInvalidosException e) {
			return null;
		}
	}
	
	private CaballoDTO getCaballoDTO(Participante participante) {
		return (CaballoDTO) (new CaballoTransformerToDTO().transform(participante.getCaballo()));
	}
	
	private JockeyDTO getJockeyDTO(Participante participante) {
		return (JockeyDTO) (new JockeyTransformerToDTO().transform(participante.getJockey()));
	}
	
	private CarreraDTO getCarreraDTO(Participante participante) {
		return (CarreraDTO) (new CarreraTransformerToDTO().transform(participante.getCarrera()));
	}

}
