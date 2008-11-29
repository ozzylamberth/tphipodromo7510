package ar.uba.fi.tecnicas.tphipodromo.servicios.impl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServicioSpring {
	
	private static ServicioSpring instance = new ServicioSpring();
	
	private ServicioSpring() {
	}
	
	public static ServicioSpring getInstance() {
		return instance;
	}

	public Object getBean(String beanName) {
		String[] paths = {"applicationContext.xml"};
//		String[] paths = {"applicationContextHibernate.xml"};
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(paths);
		return context.getBean(beanName);
	}

}
