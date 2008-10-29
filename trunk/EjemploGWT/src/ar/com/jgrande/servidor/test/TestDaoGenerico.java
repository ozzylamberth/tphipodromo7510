package ar.com.jgrande.servidor.test;

import java.util.Collection;

import ar.com.jgrande.modelo.UnaClase;
import ar.com.jgrande.servidor.dao.DAOGenerico;
import ar.com.jgrande.servidor.dao.exception.ClaseInexistenteException;
import ar.com.jgrande.servidor.dao.exception.ObjetoInexistenteException;

import com.google.inject.Inject;

/**
 * Clase para testear <code>DAOGenerico</code>.
 * 
 * @author Juan
 *
 */
public class TestDaoGenerico extends TestAbstracto {
	
	/** 
	 * Instancia del DAO. La annotation <code>Inject</code> le informa
	 * a Guice que debe injectar este atributo.
	 * 
	 */
	@Inject private DAOGenerico dao;
	
	/**
	 * Instancia de objetos que se guardar�n en la base de datos.
	 * 
	 */
	private UnaClase obj1, obj2, obj3, obj4;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		obj1 = new UnaClase();
		obj1.setId(1l);
		obj1.setDescripcion("Entonadores");
		
		obj2 = new UnaClase();
		obj2.setId(2l);
		obj2.setDescripcion("Esmaltes sinteticos");
		
		obj3 = new UnaClase();
		obj3.setId(3l);
		obj3.setDescripcion("Convertidores de oxido \"3 en 1\"");
		
		obj4 = new UnaClase();
		obj4.setId(4l);
		obj4.setDescripcion("Esmaltes sinteticos");
	}
	
	public void testDAO() {
		UnaClase aux = null;
		Collection<UnaClase> result;
		UnaClase ejemplo = new UnaClase();
		ejemplo.setDescripcion("Esmaltes sinteticos");
		
		/* busca por id algo y todav�a no se insert� nada */
		try {
			aux = dao.buscar(UnaClase.class, obj1.getId());
			fail("No lanz� excepciones y deber�a haber lanzado ClaseInexistenteException.");
		} catch (ObjetoInexistenteException e) {
			fail("Lanz� ObjetoInexistenteException y deber�a haber lanzado ClaseInexistenteException");
		} catch (ClaseInexistenteException e) {
		}
		
		/* busca por ejemplo con la base vac�a */
		try {
			result = dao.buscar(ejemplo);
			fail("No lanz� excepciones y deber�a haber lanzado ClaseInexistenteException.");
		} catch (ClaseInexistenteException e) {
		}
		
		/* busca todo lo que hay en la base con la base vac�a */
		try {
			result = dao.buscar(UnaClase.class);
			fail("No lanz� excepciones y deber�a haber lanzado ClaseInexistenteException.");
		} catch (ClaseInexistenteException e) {
		}
		
		/* inserta las cuatro categor�as */
		dao.guardar(obj1);
		dao.guardar(obj2);
		dao.guardar(obj3);
		dao.guardar(obj4);
		
		/* buscar la categor�a 1 por id */
		try {
			aux = dao.buscar(UnaClase.class, obj1.getId());
			assertEquals("Encontro una categoria equivocada al buscar por id.", obj1, aux);
		} catch (ObjetoInexistenteException e) {
			fail("Lanz� ObjetoInexistenteException");
		} catch (ClaseInexistenteException e) {
			fail("Lanz� ClaseInexistenteException");
		}
		
		/* busca las categor�as 2 y 4 por descripci�n */
		try {
			result = dao.buscar(ejemplo);
			assertEquals("No devolvio la cantidad de categorias esperada al buscar por ejemplo.", 2, result.size());
			assertFalse("Devolvio la categoria 1 al buscar por ejemplo.", result.contains(obj1));
			assertTrue("No devolvio la categoria 2 al buscar por ejemplo.", result.contains(obj2));
			assertFalse("Devolvio la categoria 3 al buscar por ejemplo.", result.contains(obj3));
			assertTrue("No devolvio la categoria 4 al buscar por ejemplo.", result.contains(obj4));
		} catch (ClaseInexistenteException e) {
			fail("Lanz� ClaseInexistenteException");
		}
		
		/* busca todas las categor�as */
		try {
			result = dao.buscar(UnaClase.class);
			assertEquals("No devolvio la cantidad de categorias esperada al buscar todas.", 4, result.size());
			assertTrue("No devolvio la categoria 1 al buscar todas.", result.contains(obj1));
			assertTrue("No devolvio la categoria 2 al buscar todas.", result.contains(obj2));
			assertTrue("No devolvio la categoria 3 al buscar todas.", result.contains(obj3));
			assertTrue("No devolvio la categoria 4 al buscar todas.", result.contains(obj4));
		} catch (ClaseInexistenteException e) {
			fail("Lanz� ClaseInexistenteException");
		}
		
		/* actualiza la descripci�n de la categor�a 2 */
		obj2.setDescripcion("Fondos convertidores");
		assertNotSame("Actualizar un objeto no actualizo su copia persistida.", obj2.getDescripcion(), aux.getDescripcion());
		
		/* borra la categor�a 1 */
		try {
			dao.borrar(obj1);
		} catch (ObjetoInexistenteException e) {
			fail("Lanz� ObjetoInexistenteException");
		} catch (ClaseInexistenteException e) {
			fail("Lanz� ClaseInexistenteException");
		}
		
		try {
			dao.buscar(UnaClase.class, obj1.getId());
			fail("Encontro un objeto que fue borrado.");
		} catch (ObjetoInexistenteException e1) {
		} catch (ClaseInexistenteException e1) {
			fail("Lanz� ClaseInexistenteException");
		}
		
		/* borra el resto de categor�as */
		try {
			dao.borrar(obj2);
			dao.borrar(obj3);
			dao.borrar(obj4);
		} catch (ObjetoInexistenteException e1) {
			fail("Lanz� ObjetoInexistenteException");
		} catch (ClaseInexistenteException e1) {
			fail("Lanz� ClaseInexistenteException");
		}
		
		try {
			result = dao.buscar(UnaClase.class);
			assertEquals("Quedaron elementos aunque se habian eliminado todos.", 0, result.size());
		} catch (ClaseInexistenteException e) {
			fail("Lanz� ClaseInexistenteException");
		}
	}

}
