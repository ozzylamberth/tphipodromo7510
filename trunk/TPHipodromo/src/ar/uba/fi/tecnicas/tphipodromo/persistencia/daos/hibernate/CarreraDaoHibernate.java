package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Carrera;
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
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2;
		calendar1.setTime(fecha);
		calendar1.set(Calendar.HOUR, 0);
		calendar1.set(Calendar.MINUTE, 0);
		calendar1.set(Calendar.SECOND, 0);
		
		calendar2 = (Calendar)calendar1.clone();
		calendar2.add(Calendar.DAY_OF_YEAR, 1);
		Collection<Carrera> listaCarreras = getSession().createQuery("from Carrera c where c.fechaYHora > :p and c.fechaYHora < :d").setDate("p",  calendar1.getTime()).setDate("d", calendar2.getTime()).list();
		return listaCarreras;
	}

	@Override
	public Collection<Carrera> buscarCarrerasApostablesPorFecha(Date fecha) {
		// TODO implementar logica
		return null;
	}
}
