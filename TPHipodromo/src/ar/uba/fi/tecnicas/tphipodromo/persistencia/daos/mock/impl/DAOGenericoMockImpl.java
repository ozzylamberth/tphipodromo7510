package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.mock.impl;

import java.util.Collection;
import java.util.HashMap;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Identificable;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.DAOGenerico;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;

public abstract class DAOGenericoMockImpl<T extends Identificable> implements DAOGenerico<T> {
	
	private HashMap<Long, T> objetos = new HashMap<Long, T>();
	
	public Collection<T> buscarTodos() {
		return this.objetos.values();
	}
	
	public T buscarPorId(Long id) throws ObjetoInexistenteException {
		T objeto = this.objetos.get(id);
		if (objeto == null) {
			throw new ObjetoInexistenteException();
		}
		return objeto;
	}
	
	public void guardar(T objeto) {
		this.objetos.put(objeto.getId(), objeto);
	}
	
	public void borrar(Long id) throws ObjetoInexistenteException {
		T objeto = this.objetos.get(id);
		if (objeto == null) {
			throw new ObjetoInexistenteException();
		}
		this.objetos.remove(id);
	}
}
