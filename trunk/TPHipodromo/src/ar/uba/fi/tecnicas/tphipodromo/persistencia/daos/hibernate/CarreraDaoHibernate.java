package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Carrera;
import ar.uba.fi.tecnicas.tphipodromo.modelo.EstadoCarrera;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.CarreraDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.MultiplesObjetosException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;

public class CarreraDaoHibernate extends HibernateDaoGenerico<Carrera> implements CarreraDao{
	
	@SuppressWarnings("unchecked")
	@Override
	public Carrera buscarPorNombre(String nombre)
			throws ObjetoInexistenteException, MultiplesObjetosException {
		
		List listaCarreras = getSession()
		.createQuery("from Carrera c where c.nombre = :p").setString("p", nombre)
		.list();
			
		if (listaCarreras.size()==0)
			throw new ObjetoInexistenteException();
		
		if (listaCarreras.size()>1)
			throw new MultiplesObjetosException();
				
		return (Carrera)listaCarreras.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Carrera> buscarPorFecha(Date fecha)  {

			Date fecha1 = new Date();
			Calendar cal = Calendar.getInstance(); 
			
			cal.setTime(fecha);
			cal.set(Calendar.HOUR,0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND,0);

			fecha = cal.getTime();
			cal.add(Calendar.DATE, 1);
			fecha1 = cal.getTime();
			
			Query q = getSession().createQuery("from Carrera c where c.fechaYHora >= :p and c.fechaYHora < :q");
			q.setDate("p",  fecha);
			q.setDate("q",  fecha1);
			Collection<Carrera> listaCarreras = q.list();
			return listaCarreras;
	}

	@Override
	public Collection<Carrera> buscarCarrerasApostablesPorFecha(Date fecha) {
		Date fecha1 = new Date();
		Calendar cal = Calendar.getInstance(); 
		
		cal.setTime(fecha);
		cal.set(Calendar.HOUR,0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND,0);

		fecha = cal.getTime();
		cal.add(Calendar.DATE, 1);
		fecha1 = cal.getTime();
		
		Query q = getSession().createQuery("from Carrera c where c.fechaYHora >= :p and c.fechaYHora< :q and c.estadoCarrera = :e");
		q.setDate("p",  fecha);
		q.setDate("q",  fecha1);
		q.setString("e", EstadoCarrera.ABIERTA_A_APUESTAS.name());
		Collection<Carrera> listaCarreras = q.list();
		return listaCarreras;
	}
}
