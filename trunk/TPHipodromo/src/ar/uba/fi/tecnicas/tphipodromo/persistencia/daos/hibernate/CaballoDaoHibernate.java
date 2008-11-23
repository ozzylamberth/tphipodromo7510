package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate;

import java.util.List;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Caballo;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.CaballoDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.MultiplesObjetosException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;

public class CaballoDaoHibernate extends HibernateDaoGenerico<Caballo> implements CaballoDao{

	@Override
	public Caballo buscarPorNombre(String nombre)
			throws ObjetoInexistenteException, MultiplesObjetosException {
		Caballo ejemplo = new Caballo();
		ejemplo.setNombre(nombre);
		List<Caballo> listaCaballos = obtenerPorEjemploExacto(ejemplo);
		
		if (listaCaballos.size()==0)
			throw new ObjetoInexistenteException();
		
		if (listaCaballos.size()>1)
			throw new MultiplesObjetosException();
				
		return listaCaballos.get(0);
	}

}
