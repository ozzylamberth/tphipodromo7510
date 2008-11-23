package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.mock.impl;

import java.util.Collection;
import java.util.HashMap;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Identificable;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.DAOGenerico;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;

public abstract class DAOGenericoMockImpl<T extends Identificable> implements DAOGenerico<T> {
	
	private HashMap<Long, T> objetos = new HashMap<Long, T>();
	private long seq = 0;
	
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
	
	public T guardar(T objeto) {
		objeto.setId(this.getID(objeto));
		this.objetos.put(objeto.getId(), objeto);
		return objeto;
	}
	
	private Long getID(T objeto) {
		if (!new Long(0).equals(objeto.getId())) {
			return objeto.getId();
		} else {
			this.seq++;
			return new Long(seq);
		}
	}
	
	public void borrar(T obj) throws ObjetoInexistenteException {
		T objeto = this.objetos.get(obj.getId());
		if (objeto == null) {
			throw new ObjetoInexistenteException();
		}
		this.objetos.remove(obj.getId());
	}
}
