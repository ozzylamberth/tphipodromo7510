package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate;

import java.util.List;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Carrera;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.CarreraDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.MultiplesObjetosException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;

public class CarreraDaoHibernate extends HibernateDaoGenerico<Carrera> implements CarreraDao{
	
	@Override
	public Carrera buscarPorNombre(String nombre)
			throws ObjetoInexistenteException, MultiplesObjetosException {
		Carrera ejemplo = new Carrera();
		ejemplo.setNombre(nombre);
		List<Carrera> listaCarreras = obtenerPorEjemploExacto(ejemplo);
		
		if (listaCarreras.size()==0)
			throw new ObjetoInexistenteException();
		
		if (listaCarreras.size()>1)
			throw new MultiplesObjetosException();
				
		return listaCarreras.get(0);
	}
}
