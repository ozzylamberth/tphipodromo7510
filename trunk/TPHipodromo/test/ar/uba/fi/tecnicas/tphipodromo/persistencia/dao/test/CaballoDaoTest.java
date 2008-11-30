package ar.uba.fi.tecnicas.tphipodromo.persistencia.dao.test;

import java.math.BigDecimal;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Caballo;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.CaballoDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.MultiplesObjetosException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate.HibernateDaoFactory;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate.HibernateUtil;

public class CaballoDaoTest extends PersistenciaTestCase {
	
	public void testDao(){
		CaballoDao dao = HibernateDaoFactory.getInstance().getCaballoDAO();
		
		Caballo nuevoCaballo = new Caballo();
		nuevoCaballo.setCaballeriza("Hijop");
		nuevoCaballo.setCriador("criador");
		nuevoCaballo.setEdad(4);
		nuevoCaballo.setNombre("pepeHijop");
		nuevoCaballo.setPelaje("peludo");
		nuevoCaballo.setPeso(new BigDecimal(10));
		nuevoCaballo.setPuraSangre(true);
		dao.guardar(nuevoCaballo);		
		
		Caballo madre = new Caballo();
		madre.setNombre(" Hijop mama caballa");
		madre.setCriador("criador");
		madre.setEdad(10);
		madre.setCaballeriza("caballeriza");
		madre.setPelaje("pelo");
		madre.setPeso(new BigDecimal(16));
		madre.setPuraSangre(false);

		Caballo padre = new Caballo();
		padre.setNombre(" Hijop p apa callo");
		padre.setCriador("crd2");
		padre.setEdad(11);
		padre.setCaballeriza("dsa 2");
		padre.setPelaje("pelo dd pelo");
		padre.setPeso(new BigDecimal(160));
		padre.setPuraSangre(true);
		
		nuevoCaballo.setMadre(madre);
		nuevoCaballo.setPadre(padre);
		
		nuevoCaballo.getEstadisticas().agregarResultado(10);
		nuevoCaballo.getEstadisticas().agregarResultado(9);
		nuevoCaballo.getEstadisticas().agregarResultado(10);
		
		dao.guardar(nuevoCaballo);		
		
		HibernateUtil.getCurrentSession().clear();	
		
		try {
			Caballo caballoLeido = dao.buscarPorNombre("pepeHijop");
			assertEquals(nuevoCaballo.getCaballeriza(), caballoLeido.getCaballeriza());
			assertEquals(nuevoCaballo.getCriador(), caballoLeido.getCriador());
			assertEquals(nuevoCaballo.getEdad(), caballoLeido.getEdad());
			assertEquals(nuevoCaballo.getNombre(), caballoLeido.getNombre());
			dao.borrar(caballoLeido);
			dao.borrar(madre);
			dao.borrar(padre);
		} catch (ObjetoInexistenteException e) {
			fail(e.getMessage());
		} catch (MultiplesObjetosException e) {
			fail(e.getMessage());
		}	

		
	}

}
