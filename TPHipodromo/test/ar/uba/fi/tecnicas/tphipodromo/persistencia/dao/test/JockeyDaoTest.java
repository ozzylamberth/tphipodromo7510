package ar.uba.fi.tecnicas.tphipodromo.persistencia.dao.test;

import java.math.BigDecimal;
import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Jockey;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.JockeyDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate.HibernateDaoFactory;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate.HibernateUtil;

public class JockeyDaoTest extends PersistenciaTestCase{
	
	public void testJockey() throws ObjetoInexistenteException{
			Jockey jockey1 = new Jockey();
			jockey1.setApellido("Wasserman");
			jockey1.setNombre("Damian");
			jockey1.setPeso(new BigDecimal(100));
			
			JockeyDao dao = HibernateDaoFactory.getInstance().getJockeyDAO();
			
			dao.guardar(jockey1);
			
			HibernateUtil.getCurrentSession().clear();
			
			Collection<Jockey> lista = dao.buscarTodos();
			
			assertEquals(1, lista.size());
			
			for( Jockey j : lista){
				assertEquals(jockey1.getApellido(), j.getApellido());
				assertEquals(jockey1.getNombre(), j.getNombre());
				assertEquals(jockey1.getPeso().doubleValue(), j.getPeso().doubleValue());
				dao.borrar(j);
				
			}
			
			
	}
}
