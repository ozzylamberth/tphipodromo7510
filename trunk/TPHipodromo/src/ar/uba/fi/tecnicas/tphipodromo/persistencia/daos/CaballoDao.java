package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Caballo;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;

public interface CaballoDao extends DAOGenerico<Caballo> {
	
	public Caballo buscarPorNombre(String nombre) throws ObjetoInexistenteException;
	
}
