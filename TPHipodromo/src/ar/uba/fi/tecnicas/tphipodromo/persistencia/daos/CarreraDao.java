package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos;

import java.util.Collection;
import java.util.Date;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Carrera;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.MultiplesObjetosException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;

public interface CarreraDao extends DAOGenerico<Carrera>{

	public Carrera buscarPorNombre(String nombre) throws ObjetoInexistenteException, MultiplesObjetosException;
	
	public Collection<Carrera> buscarPorFecha(Date fecha);

}
