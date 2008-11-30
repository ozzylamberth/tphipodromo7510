package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

public class HibernateListener implements ServletContextListener, ServletRequestListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			HibernateUtil.init();
			Logger.getLogger(getClass()).info("Hibernate inicializado");
		} catch(HibernateException e) {
			Logger.getLogger(getClass()).error("Error al inicializar Hibernate.", e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		HibernateUtil.getSessionFactory().close();
		Logger.getLogger(getClass()).info("Hibernate apagado.");
	}
	
	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		arg0.getServletRequest().setAttribute("hibernate.transaction", HibernateUtil.getCurrentSession().beginTransaction());
		Logger.getLogger(getClass()).info("Transacción Hibernate comenzada.");
	}
	
	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		((Transaction)arg0.getServletRequest().getAttribute("hibernate.transaction")).commit();
		Logger.getLogger(getClass()).info("Transacción Hibernate terminada.");
	}

}
