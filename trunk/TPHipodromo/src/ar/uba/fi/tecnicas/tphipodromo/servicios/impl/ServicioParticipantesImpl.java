package ar.uba.fi.tecnicas.tphipodromo.servicios.impl;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Carrera;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Participante;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.CarreraDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.DAOGenerico;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.ParticipanteDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.mock.impl.CarreraDaoMockImpl;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.mock.impl.ParticipanteDaoMockImpl;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioParticipantes;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ParticipanteDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.EntidadInexistenteException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.transformers.ParticipanteTransformerFromDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.transformers.ParticipanteTransformerToDTO;

@SuppressWarnings("serial")
public class ServicioParticipantesImpl extends ServicioIdentificableImpl<Participante, ParticipanteDTO> implements ServicioParticipantes {
	
	private ParticipanteDao participanteDao = new ParticipanteDaoMockImpl();
	private CarreraDao carreraDao = new CarreraDaoMockImpl();
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

	@SuppressWarnings("unchecked")
	public Collection<ParticipanteDTO> buscarPorCarrera(CarreraDTO carreraDTO) throws EntidadInexistenteException {
		try {
			Carrera carrera = this.carreraDao.buscarPorId(carreraDTO.getId());
			return CollectionUtils.collect(carrera.getParticipantes(), this.getTransformerToDTO());
		} catch (ObjetoInexistenteException e) {
			throw new EntidadInexistenteException();
		}
	}

}
