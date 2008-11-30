package ar.uba.fi.tecnicas.tphipodromo.servicios.transformers;

import org.apache.commons.collections.Transformer;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Caballo;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Carrera;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Jockey;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Participante;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Resultado;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.TransicionInvalidaEstadoParticipanteException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.ParticipanteDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate.HibernateDaoFactory;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ParticipanteDTO;

public class ParticipanteTransformerFromDTO implements Transformer {
	
	private ParticipanteDao participanteDao;
	
	public ParticipanteTransformerFromDTO() { 
		this.participanteDao = HibernateDaoFactory.getInstance().getParticipanteDAO();
	}

	public Object transform(Object arg0) {
		ParticipanteDTO participanteDTO = (ParticipanteDTO) arg0;
		try {
			Participante participante = this.getParticipante(participanteDTO);
			participante.setNroParticipante(participanteDTO.getNroParticipante());
			participante.setCaballo((Caballo) new CaballoTransformerFromDTO().transform(participanteDTO.getCaballoDTO()));
			participante.setCarrera((Carrera) new CarreraTransformerFromDTO().transform(participanteDTO.getCarreraDTO()));
			participante.setJockey((Jockey) new JockeyTransformerFromDTO().transform(participanteDTO.getJockeyDTO()));
			this.setResultado(participante, participanteDTO);
			return participante;
		} catch (TransicionInvalidaEstadoParticipanteException e) {
			return null;
		} catch (ObjetoInexistenteException e) {
			return null;
		}
	}
	
	private Participante getParticipante(ParticipanteDTO participanteDTO) throws ObjetoInexistenteException {
		try {
			return this.participanteDao.buscarPorId(participanteDTO.getId());
		} catch (ObjetoInexistenteException e) {
			return new Participante();
		}
	}
	
	private void setResultado(Participante participante, ParticipanteDTO participanteDTO) throws TransicionInvalidaEstadoParticipanteException {
		if (participanteDTO.getResultadoDTO() != null) {
			Resultado resultado = (Resultado) new ResultadoTransformerFromDTO().transform(participanteDTO.getResultadoDTO());
			participante.setResultado(resultado);
		}
	}

}
