package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.mock.impl;

import java.util.Collection;
import java.util.Map;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Identificable;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.DAOGenerico;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;

public abstract class DAOGenericoMockImpl<T extends Identificable> implements DAOGenerico<T> {
	
	protected abstract Map<Long, T> getDBMap();
	/**
	 * Retorna el siguiente id que hay que insertar
	 */
	protected abstract Secuencia getSecuencia();
	
	public Collection<T> buscarTodos() {
		return this.getDBMap().values();
	}
	
	public T buscarPorId(Long id) throws ObjetoInexistenteException {
		T objeto = this.getDBMap().get(id);
		if (objeto == null) {
			throw new ObjetoInexistenteException();
		}
		return objeto;
	}
	
	public T guardar(T objeto) {
		objeto.setId(this.getID(objeto));
		this.getDBMap().put(objeto.getId(), objeto);
		return objeto;
	}
	
	private Long getID(T objeto) {
		if (!new Long(0).equals(objeto.getId())) {
			return objeto.getId();
		} else {
			return new Long(this.getSecuencia().getSiguienteValor());
		}
	}
	
	public void borrar(T obj) throws ObjetoInexistenteException {
		T objeto = this.getDBMap().get(obj.getId());
		if (objeto == null) {
			throw new ObjetoInexistenteException();
		}
		this.getDBMap().remove(obj.getId());
	}
}
