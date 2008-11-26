package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos;

import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate.HibernateDaoFactory;

public abstract class DaoFactory {
	
	public static final Class HIBERNATE=HibernateDaoFactory.class;
	
	public static DaoFactory instance(Class factory){
		try {
			return (DaoFactory) factory.newInstance();
		}catch (Exception ex){
			throw new RuntimeException("No se pudo crear la fabrica: " + factory);
		}
	}
	
	public abstract CaballoDao getCaballoDAO();
	public abstract CarreraDao getCarreraDAO();
	public abstract JockeyDao getJockeyDAO();
	public abstract ParticipanteDao getParticipanteDAO();
}
