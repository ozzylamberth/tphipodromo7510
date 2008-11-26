package ar.uba.fi.tecnicas.tphipodromo.persistencia.dao.test;

import java.math.BigDecimal;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Caballo;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.CaballoDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.DaoFactory;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.MultiplesObjetosException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate.HibernateDaoFactory;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate.HibernateUtil;

public class CaballoDaoTest extends PersistenciaTestCase {
	
	public void testDao(){
		DaoFactory factory= new HibernateDaoFactory();
		CaballoDao dao = factory.getCaballoDAO();
		
		Caballo nuevoCaballo = new Caballo();
		nuevoCaballo.setCaballeriza("caballeriza test");
		nuevoCaballo.setCriador("criador");
		nuevoCaballo.setEdad(4);
		nuevoCaballo.setNombre("pepe");
		nuevoCaballo.setPelaje("peludo");
		nuevoCaballo.setPeso(new BigDecimal(10));
		nuevoCaballo.setPuraSangre(true);
		dao.guardar(nuevoCaballo);		
		
		Caballo madre = new Caballo();
		madre.setNombre("mama caballa");
		madre.setCriador("criador");
		madre.setEdad(10);
		madre.setCaballeriza("caballeriza");
		madre.setPelaje("pelo");
		madre.setPeso(new BigDecimal(16));
		madre.setPuraSangre(false);

		Caballo padre = new Caballo();
		padre.setNombre("papa caballo");
		padre.setCriador("criador 2");
		padre.setEdad(11);
		padre.setCaballeriza("caballeriza 2");
		padre.setPelaje("pelo pelo");
		padre.setPeso(new BigDecimal(160));
		padre.setPuraSangre(true);
		
		nuevoCaballo.setMadre(madre);
		nuevoCaballo.setPadre(padre);
		
		nuevoCaballo.getEstadisticas().agregarResultado(10);
		nuevoCaballo.getEstadisticas().agregarResultado(9);
		nuevoCaballo.getEstadisticas().agregarResultado(10);
		
		dao.guardar(nuevoCaballo);		
		
		HibernateUtil.currentSession().clear();	
		
		try {
			Caballo caballoLeido = dao.buscarPorNombre("pepe");
			assertEquals(nuevoCaballo.getCaballeriza(), caballoLeido.getCaballeriza());
			assertEquals(nuevoCaballo.getCriador(), caballoLeido.getCriador());
			assertEquals(nuevoCaballo.getEdad(), caballoLeido.getEdad());
			assertEquals(nuevoCaballo.getNombre(), caballoLeido.getNombre());
			dao.borrar(caballoLeido);
		} catch (ObjetoInexistenteException e) {
			fail(e.getMessage());
		} catch (MultiplesObjetosException e) {
			fail(e.getMessage());
		}	

		
	}

}
