package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate;

import java.sql.Connection;

import org.hibernate.*;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	private static Connection conexion;
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
		
    public static Session sesion;

    @SuppressWarnings("unchecked")
	public static Session currentSession() throws HibernateException{
        if (sesion == null) {
        	if (conexion!=null){
        		sesion = sessionFactory.openSession(conexion);
        	} else {
        		sesion = sessionFactory.openSession();	
        	}
        	
        }
        return sesion;
    }
    
	public static void closeSession() throws HibernateException {
        if (sesion != null){
        	sesion.close();
        	sesion=null;
        }
    }
}
