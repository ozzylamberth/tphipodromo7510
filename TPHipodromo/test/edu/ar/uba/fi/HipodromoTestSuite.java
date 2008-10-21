package edu.ar.uba.fi;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class HipodromoTestSuite extends TestCase {
	
	public static TestSuite suite() {
		TestSuite suite = new TestSuite();
		suite.addTestSuite(ApuestaGanadorGanadaTest.class);
		suite.addTestSuite(ApuestaSegundoGanadaTest.class);
		suite.addTestSuite(ApuestaTerceroGanadaTest.class);
		suite.addTestSuite(ApuestaExactaTest.class);
		suite.addTestSuite(ExcepcionesCarreraTest.class);
		return suite;
	}
}
