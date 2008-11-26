package ar.uba.fi.tecnicas.tphipodromo.servicios.impl;

import java.util.Collection;

import org.apache.commons.collections.Transformer;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Participante;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.DAOGenerico;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.ParticipanteDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.mock.impl.ParticipanteDaoMockImpl;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioParticipantes;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ParticipanteDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.transformers.ParticipanteTransformerFromDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.transformers.ParticipanteTransformerToDTO;

@SuppressWarnings("serial")
public class ServicioParticipantesImpl extends ServicioIdentificableImpl<Participante, ParticipanteDTO> implements ServicioParticipantes {
	
	private ParticipanteDao participanteDao = new ParticipanteDaoMockImpl();
	private ParticipanteTransformerFromDTO participanteTransformerFromDTO = new ParticipanteTransformerFromDTO();
	private ParticipanteTransformerToDTO participanteTransformerToDTO = new ParticipanteTransformerToDTO();

	public DAOGenerico<Participante> getDao() {
		return this.participanteDao;
	}

	public Transformer getTransformerFromDTO() {
		return this.participanteTransformerFromDTO;
	}

	public Transformer getTransformerToDTO() {
		return this.participanteTransformerToDTO;
	}

	public Collection<ParticipanteDTO> buscarPorCarrera(CarreraDTO carrera) {
		return this.buscarTodos();//TODO: Nahuel, terminas esto?? Gracias
	}

}
