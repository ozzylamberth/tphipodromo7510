package edu.ar.uba.fi;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class HipodromoTestSuite extends TestCase {
	
	public static TestSuite suite() {
		TestSuite suite = new TestSuite();
		
		//Apuestas Directas
		suite.addTestSuite(ApuestaGanadorGanadaTest.class);
		suite.addTestSuite(ApuestaSegundoTest.class);
		suite.addTestSuite(ApuestaTerceroTest.class);
		
		//Apuestas Combinadas
		suite.addTestSuite(ApuestaExactaTest.class);
		suite.addTestSuite(ApuestaImperfectaTest.class);
		suite.addTestSuite(ApuestaTrifectaTest.class);

		//Apuestas Multiples
		suite.addTestSuite(ApuestaDobleTest.class);
		suite.addTestSuite(ApuestaTriploTest.class);
		suite.addTestSuite(ApuestaCuaternaTest.class);
		
		//Otros
		suite.addTestSuite(ApuestasPerdidasTest.class);
		suite.addTestSuite(CarreraTest.class);
		suite.addTestSuite(ExcepcionesApuestaTest.class);
		
		return suite;
	}
}
