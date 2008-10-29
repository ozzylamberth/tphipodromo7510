package ar.com.jgrande.servidor.test;

import junit.framework.TestCase;
import ar.com.jgrande.servidor.dao.DAOGenerico;
import ar.com.jgrande.servidor.dao.impl.DAOGenericoMock;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Scopes;

/**
 * Provee funcionalidad común a todos los tests.
 * 
 * @author Juan
 *
 */
public abstract class TestAbstracto extends TestCase implements Module {
	
	/**
	 * Constructor público.
	 * 
	 */
	public TestAbstracto() {
		Injector injector = Guice.createInjector(this);
		injector.injectMembers(this);
	}

	/**
	 * Configura los bindings de Guice.
	 * 
	 */
	public void configure(Binder binder) {
		// Mapea DAOGenericoMock como implemetación de DAOGenerico
		binder
		.bind(DAOGenerico.class)
		.to(DAOGenericoMock.class)
		.in(Scopes.SINGLETON);
	}
	
}
