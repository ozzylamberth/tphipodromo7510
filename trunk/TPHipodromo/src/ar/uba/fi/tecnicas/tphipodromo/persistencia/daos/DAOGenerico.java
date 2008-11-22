package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos;

import java.util.Collection;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Identificable;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;

/**
 * Dao generico que permite buscar, insertar y
 * eliminar cualquier tipo de objeto.
 */
public interface DAOGenerico<T extends Identificable> {
	
	/**
	 * Busca todos los objetos de una clase.
	 */
	public Collection<T> buscarTodos();
	
	/**
	 * Busca una instancia de un objeto por id
	 */
	public T buscarPorId(Long id) throws ObjetoInexistenteException;
	
	/**
	 * Persiste un objeto en la base de datos y retorna el id del mismo
	 */
	public Long guardar(T obj);
	
	/**
	 * Elimina un objeto de la base de datos
	 */
	public void borrar(Long id) throws ObjetoInexistenteException;
	
}
