package ar.uba.fi.tecnicas.tphipodromo.servicios.transformers;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.commons.collections.Transformer;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Participante;
import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.Apuesta;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ApuestaDTO;

public class ApuestaTransformerToDTO implements Transformer {

	public Object transform(Object arg0) {
		Apuesta apuesta = (Apuesta) arg0;
		ApuestaDTO apuestaDTO = new ApuestaDTO();
		apuestaDTO.setId(apuesta.getId());
		apuestaDTO.setTipoApuesta(apuesta.getTipoApuesta().getNombre());
		apuestaDTO.setEstado(apuesta.getEstadoApuesta().getNombre());
		apuestaDTO.setMontoApostado(new Double(apuesta.getMontoApostado().toString()));
		apuestaDTO.setNroTicket(apuesta.getNroTicket());
		apuestaDTO.setFechaCreacion(apuesta.getFechaCreacion());
		apuestaDTO.setParticipantesIds(this.getParticipantesIds(apuesta.getParticipantes()));
		apuestaDTO.setDiasPlazoMaxDeCobro(apuesta.getDiasPlazoMaxDeCobro());
		return apuestaDTO;
	}
	
	private Collection<Long> getParticipantesIds(Collection<Participante> participantes) {
		Collection<Long> participantesIds = new LinkedList<Long>();
		Iterator<Participante> it = participantes.iterator();
		while (it.hasNext()) {
			Participante participante = (Participante) it.next();
			participantesIds.add(participante.getId());
		}
		return participantesIds;
	}

}
