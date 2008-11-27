package ar.uba.fi.tecnicas.tphipodromo.persistencia.dao.test;

import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.BolsaApuestasConcreta;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.BolsaApuestasDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.DaoFactory;

public class BolsaApuestasDaoTest extends PersistenciaTestCase {
	
	public void testDao(){
		DaoFactory factory= DaoFactory.instance(DaoFactory.HIBERNATE);
		BolsaApuestasDao dao = factory.getBolsaApuestasDAO();
		
		BolsaApuestasConcreta bolsa = new BolsaApuestasConcreta();
		

		dao.guardar(bolsa);
		
		
	}

}
