package ar.uba.fi.tecnicas.tphipodromo.servicios.test;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ServiciosTestSuite extends TestCase {
	
	public static TestSuite suite() {
		TestSuite testSuite = new TestSuite();
		testSuite.addTestSuite(ServicioCaballosTest.class);
		testSuite.addTestSuite(ServicioCarrerasTest.class);
		testSuite.addTestSuite(ServicioJockeysTest.class);
		return testSuite;
	}
	
}
