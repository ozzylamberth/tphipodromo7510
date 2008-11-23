package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.mock.impl;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Caballo;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Carrera;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Jockey;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Participante;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.CaballoDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.CarreraDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.JockeyDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.ParticipanteDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;

public class ParticipanteDaoMockImpl extends DAOGenericoMockImpl<Participante> implements ParticipanteDao {
	
	private CaballoDao caballoDao = new CaballoDaoMockImpl();
	private JockeyDao jockeyDao = new JockeyDaoMockImpl();
	private CarreraDao carreraDao = new CarreraDaoMockImpl();
	
	public ParticipanteDaoMockImpl() {
		this.guardar(this.getParticipante(new Long(1), new Long(1), new Long(1), 1));
		this.guardar(this.getParticipante(new Long(2), new Long(2), new Long(1), 1));
		this.guardar(this.getParticipante(new Long(3), new Long(3), new Long(2), 1));
	}
	
	private Participante getParticipante(Long caballoId, Long jockeyId, Long carreraId, int nroParticipante) {
		try {
			Caballo caballo = this.caballoDao.buscarPorId(caballoId);
			Jockey jockey = this.jockeyDao.buscarPorId(jockeyId);
			Carrera carrera = this.carreraDao.buscarPorId(carreraId);
			Participante participante = new Participante(caballo, jockey, carrera);
			participante.setNroParticipante(nroParticipante);
			return participante;
		} catch (ObjetoInexistenteException e) {
			return null;
		}
	}
	
}
