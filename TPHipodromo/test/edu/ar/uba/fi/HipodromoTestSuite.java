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
		suite.addTestSuite(ApuestaCuaternaTest.class);
		suite.addTestSuite(ApuestaDobleTest.class);
		suite.addTestSuite(ApuestaImperfectaTest.class);
		suite.addTestSuite(ApuestaTrifectaTest.class);
		suite.addTestSuite(ApuestaTriploTest.class);
		suite.addTestSuite(ExcepcionesApuestaTest.class);
		suite.addTestSuite(ExcepcionesCarreraTest.class);
		suite.addTestSuite(ApuestasPerdidasTest.class);
		return suite;
	}
}
