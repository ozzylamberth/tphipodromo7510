package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Carrera;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.MultiplesObjetosException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;

public interface CarreraDao extends DAOGenerico<Carrera>{

	Carrera buscarPorNombre(String nombre) throws ObjetoInexistenteException,
			MultiplesObjetosException;

}
