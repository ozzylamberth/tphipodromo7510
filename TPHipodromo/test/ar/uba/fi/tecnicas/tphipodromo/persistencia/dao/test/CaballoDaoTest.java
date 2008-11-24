package ar.uba.fi.tecnicas.tphipodromo.persistencia.dao.test;

import java.math.BigDecimal;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Caballo;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Jockey;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.CaballoDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.DaoFactory;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.JockeyDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate.HibernateDaoFactory;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate.HibernateUtil;
import junit.framework.TestCase;

public class CaballoDaoTest extends TestCase {
	
	public static void main(String[] s){
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
		Caballo padre = new Caballo();
		
		nuevoCaballo.setMadre(madre);
		nuevoCaballo.setPadre(padre);
		
		nuevoCaballo.getEstadisticas().agregarResultado(10);
		nuevoCaballo.getEstadisticas().agregarResultado(9);
		nuevoCaballo.getEstadisticas().agregarResultado(10);
		
		dao.guardar(nuevoCaballo);
		
		HibernateUtil.closeSession();
		org.hsqldb.DatabaseManager.closeDatabases(0);
	}
	
	public void testDao(){

		
	}

}