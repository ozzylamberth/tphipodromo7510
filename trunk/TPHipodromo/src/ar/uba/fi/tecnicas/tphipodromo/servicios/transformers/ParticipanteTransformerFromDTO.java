package ar.uba.fi.tecnicas.tphipodromo.servicios.transformers;

import org.apache.commons.collections.Transformer;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Caballo;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Carrera;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Jockey;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Participante;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Resultado;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.TransicionInvalidaEstadoParticipanteException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.CaballoDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.CarreraDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.JockeyDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.ResultadoDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.ParticipanteDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.impl.ServicioSpring;

public class ParticipanteTransformerFromDTO implements Transformer {
	
	private CaballoDao caballoDao;
	private CarreraDao carreraDao;
	private JockeyDao jockeyDao;
	private ResultadoDao resultadoDao;
	
	public ParticipanteTransformerFromDTO() {
		this.caballoDao = (CaballoDao) ServicioSpring.getInstance().getBean("caballoDao");
		this.carreraDao = (CarreraDao) ServicioSpring.getInstance().getBean("carreraDao");
		this.jockeyDao = (JockeyDao) ServicioSpring.getInstance().getBean("jockeyDao"); 
		this.resultadoDao = (ResultadoDao) ServicioSpring.getInstance().getBean("resultadoDao");
	}

	public Object transform(Object arg0) {
		ParticipanteDTO participanteDTO = (ParticipanteDTO) arg0;
		try {
			Caballo caballo = this.caballoDao.buscarPorId(participanteDTO.getCaballoId());
			Carrera carrera = this.carreraDao.buscarPorId(participanteDTO.getCarreraId());
			Jockey jockey = this.jockeyDao.buscarPorId(participanteDTO.getJockeyId());
			Resultado resultado = this.resultadoDao.buscarPorId(participanteDTO.getResultadoId());
			Participante participante = new Participante(caballo, jockey, carrera);
			participante.setId(participanteDTO.getId());
			participante.setNroParticipante(participanteDTO.getNroParticipante());
			participante.setResultado(resultado);
			return participante;
		} catch (TransicionInvalidaEstadoParticipanteException e) {
			return null;
		} catch (ObjetoInexistenteException e) {
			return null;
		}
	}

}
