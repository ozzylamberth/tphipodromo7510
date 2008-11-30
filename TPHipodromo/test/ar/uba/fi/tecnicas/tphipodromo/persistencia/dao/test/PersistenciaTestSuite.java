package ar.uba.fi.tecnicas.tphipodromo.persistencia.dao.test;

import junit.framework.TestSuite;

public class PersistenciaTestSuite extends TestSuite {

	public static TestSuite suite() {
		TestSuite suite = new TestSuite();
		
		suite.addTestSuite(BolsaApuestasDaoTest.class);
		suite.addTestSuite(CaballoDaoTest.class);
		suite.addTestSuite(CarreraDaoTest.class);
		suite.addTestSuite(JockeyDaoTest.class);
		suite.addTestSuite(ParticipanteDaoTest.class);
		suite.addTestSuite(ApuestaDaoTest.class);
		
		return suite;
	}
	
}
