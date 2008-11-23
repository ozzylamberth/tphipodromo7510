package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate;

import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.CaballoDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.CarreraDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.DaoFactory;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.JockeyDao;

public class HibernateDaoFactory extends DaoFactory {
	
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
		return (CaballoDao) generarHiberanteDao(CaballoDaoHibernate.class);
	}

	@Override
	public CarreraDao getCarreraDAO() {
		return (CarreraDao) generarHiberanteDao(CarreraDaoHibernate.class);
	}

	@Override
	public JockeyDao getJockeyDAO() {
		return (JockeyDao) generarHiberanteDao(JockeyDaoHibernate.class);
	}
}
