package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate;

import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.ApuestaDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.BolsaApuestasDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.CaballoDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.CarreraDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.DaoFactory;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.JockeyDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.ParticipanteDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.ResultadoDao;

public class HibernateDaoFactory extends DaoFactory {
	
	public static HibernateDaoFactory instance = new HibernateDaoFactory();
	
	private CaballoDao caballoDao = null;
	private CarreraDao carreraDao = null;
	private JockeyDao jockeyDao = null;
	private ParticipanteDao participanteDao = null;
	private BolsaApuestasDao bolsaApuestasDao = null;
	private ResultadoDao resultadoDao = null;
	private ApuestaDao apuestaDao = null;
	
	private HibernateDaoFactory() {
	}
	
	public static HibernateDaoFactory getInstance() {
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	private HibernateDaoGenerico generarHiberanteDao(Class claseDao){
		try {
			HibernateDaoGenerico dao = (HibernateDaoGenerico) claseDao.newInstance();
			return dao;
			} catch (Exception ex){
			throw new RuntimeException("No se pudo generar el DAO: " + claseDao, ex);
		}
	}

	@Override
	public CaballoDao getCaballoDAO() {
		if (this.caballoDao == null) {
			this.caballoDao = (CaballoDao) generarHiberanteDao(CaballoDaoHibernate.class);
		}
		return this.caballoDao;
	}

	@Override
	public CarreraDao getCarreraDAO() {
		if (this.carreraDao == null) {
			this.carreraDao = (CarreraDao) generarHiberanteDao(CarreraDaoHibernate.class); 
		}
		return this.carreraDao;
	}

	@Override
	public JockeyDao getJockeyDAO() {
		if (this.jockeyDao == null) {
			this.jockeyDao = (JockeyDao) generarHiberanteDao(JockeyDaoHibernate.class);
		}
		return this.jockeyDao;
	}

	@Override
	public ParticipanteDao getParticipanteDAO() {
		if (this.participanteDao == null) {
			this.participanteDao = (ParticipanteDao) generarHiberanteDao(ParticipanteDaoHibernate.class);
		}
		return this.participanteDao;
	}

	@Override
	public BolsaApuestasDao getBolsaApuestasDAO() {
		if (this.bolsaApuestasDao == null) {
			this.bolsaApuestasDao = (BolsaApuestasDao) generarHiberanteDao(BolsaApuestasDaoHibernate.class);
		}
		return this.bolsaApuestasDao;
	}

	@Override
	public ResultadoDao getResultadoDAO() {
		if (this.resultadoDao == null) {
			this.resultadoDao = (ResultadoDao) generarHiberanteDao(ResultadoDaoHibernate.class);
		}
		return this.resultadoDao;
	}
	
	public ApuestaDao getApuestaDAO() {
		if (this.apuestaDao == null) {
			// TODO: implementar logica
		}
		return this.apuestaDao;
	}

}
