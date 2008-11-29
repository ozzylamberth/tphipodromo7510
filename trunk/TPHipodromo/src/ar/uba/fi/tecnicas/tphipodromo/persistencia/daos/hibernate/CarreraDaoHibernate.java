package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate;

import java.sql.Timestamp;
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
			
			Collection<Carrera> listaCarreras = getSession().createQuery("from Carrera c where c.fechaYHora = :p").setLong("p",  fecha.getTime()/1000).list();
			return listaCarreras;
	}
}
