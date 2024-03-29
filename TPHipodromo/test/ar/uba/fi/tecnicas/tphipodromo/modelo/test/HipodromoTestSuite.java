package ar.uba.fi.tecnicas.tphipodromo.modelo.test;

import org.apache.log4j.PropertyConfigurator;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class HipodromoTestSuite extends TestCase {
	
	public static TestSuite suite() {
		TestSuite suite = new TestSuite();
		
		PropertyConfigurator.configure(HipodromoTestSuite.class.getResource("/log4j.properties"));
		
		//Apuestas Directas
		suite.addTestSuite(ApuestaGanadorTest.class);
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
		suite.addTestSuite(CarreraTest.class);
		
		return suite;
	}
}
