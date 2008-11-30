package ar.uba.fi.tecnicas.tphipodromo.persistencia.dao.test;

import junit.framework.TestCase;

import org.hibernate.Transaction;

import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate.HibernateUtil;

public class PersistenciaTestCase extends TestCase {
	
	private Transaction transaction;
	
	public void setUp() throws Exception {
		HibernateUtil.initTest();
		this.transaction = HibernateUtil.getCurrentSession().beginTransaction();
	}
	
	@Override
	protected void tearDown() throws Exception {
		this.transaction.rollback();
		HibernateUtil.getSessionFactory().close();
	}
	
}
