package ar.uba.fi.tecnicas.tphipodromo.persistencia.dao.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate.HibernateUtil;

import junit.framework.TestCase;

public class PersistenciaTestCase extends TestCase {
	
	public void setUp() throws Exception {
		try {
			Connection connection = null;
			Statement statement = null;
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/mysql", "root", "1");

			statement = connection.createStatement();
			statement.executeUpdate("DROP DATABASE IF EXISTS hipo");

			statement = connection.createStatement();
			statement.executeUpdate("CREATE DATABASE hipo");
			
			connection.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		HibernateUtil.init();
		
		super.setUp();
	}
	
	@Override
	protected void tearDown() throws Exception {
		HibernateUtil.getSessionFactory().close();
		
		super.tearDown();
	}
}
