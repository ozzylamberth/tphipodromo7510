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
	
	private CaballoDao caballoDao = null;

	@Override
	public CaballoDao getCaballoDAO() {
		if (this.caballoDao == null) {
			this.caballoDao = (CaballoDao) generarHiberanteDao(CaballoDaoHibernate.class);
		}
		return this.caballoDao;
	}

	@Override
	public CarreraDao getCarreraDAO() {
		return (CarreraDao) generarHiberanteDao(CarreraDaoHibernate.class);
	}

	@Override
	public JockeyDao getJockeyDAO() {
		return (JockeyDao) generarHiberanteDao(JockeyDaoHibernate.class);
	}

	@Override
	public ParticipanteDao getParticipanteDAO() {
		return (ParticipanteDao) generarHiberanteDao(ParticipanteDaoHibernate.class);
	}

	@Override
	public BolsaApuestasDao getBolsaApuestasDAO() {
		return (BolsaApuestasDao) generarHiberanteDao(BolsaApuestasDaoHibernate.class);
	}

	@Override
	public ResultadoDao getResultadoDAO() {
		return (ResultadoDao) generarHiberanteDao(ResultadoDaoHibernate.class);
	}
	
	public ApuestaDao getApuestaDAO() {
		// TODO: implementar logica
		return null;
	}

}
