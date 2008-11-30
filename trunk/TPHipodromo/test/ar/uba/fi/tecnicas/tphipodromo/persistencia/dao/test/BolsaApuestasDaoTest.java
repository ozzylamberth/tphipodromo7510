package ar.uba.fi.tecnicas.tphipodromo.persistencia.dao.test;

import ar.uba.fi.tecnicas.tphipodromo.modelo.apuestas.BolsaApuestasConcreta;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.BolsaApuestasDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate.HibernateDaoFactory;

public class BolsaApuestasDaoTest extends PersistenciaTestCase {
	
	public void testDao(){
		BolsaApuestasDao dao = HibernateDaoFactory.getInstance().getBolsaApuestasDAO();
		BolsaApuestasConcreta bolsa = new BolsaApuestasConcreta();
		dao.guardar(bolsa);
	}

}
