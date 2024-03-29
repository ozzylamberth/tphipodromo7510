package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	public static void init() {
		try {
			// Create the SessionFactory
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure we log the exception, as it might be swallowed
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static void initTest() {
		try {
			// Create the SessionFactory
			sessionFactory = new Configuration().configure("hibernateTest.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure we log the exception, as it might be swallowed
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}
