package ar.uba.fi.tecnicas.tphipodromo.servicios.transformers;

import org.apache.commons.collections.Transformer;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Participante;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.ResultadosCarreraInvalidosException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ParticipanteDTO;

public class ParticipanteTransformerToDTO implements Transformer {

	public Object transform(Object arg0) {
		Participante participante = (Participante) arg0;
		ParticipanteDTO participanteDTO = new ParticipanteDTO();
		participanteDTO.setId(participante.getId());
		participanteDTO.setNroParticipante(participante.getNroParticipante());
		try {
			participanteDTO.setResultadoId(participante.getResultado().getId());
		} catch (ResultadosCarreraInvalidosException e) {
			participanteDTO.setId(null);
		}
		participanteDTO.setCaballoId(participante.getCaballo().getId());
		participanteDTO.setJockeyId(participante.getJockey().getId());
		participanteDTO.setCarreraId(participante.getCarrera().getId());
		participanteDTO.setEstado(participante.getEstado().getNombre());
		return participanteDTO;
	}

}
