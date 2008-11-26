package ar.uba.fi.tecnicas.tphipodromo.persistencia.dao.test;

import java.math.BigDecimal;

import junit.framework.TestCase;
import ar.uba.fi.tecnicas.tphipodromo.modelo.*;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.*;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.DaoFactory;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.JockeyDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate.HibernateDaoFactory;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate.JockeyDaoHibernate;

public class JockeyDaoTest extends TestCase{
	public void testJockey(){
			Jockey jockey1 = new Jockey();
			jockey1.setApellido("Wasserman");
			jockey1.setNombre("Damian");
			jockey1.setPeso(new BigDecimal(100));
			
			DaoFactory factory = new HibernateDaoFactory();
			JockeyDao dao = factory.getJockeyDAO();
			
			dao.guardar(jockey1);
	}
}
