package ar.com.jgrande.servidor.dao;

import java.util.Collection;

import ar.com.jgrande.modelo.Identificable;
import ar.com.jgrande.servidor.dao.exception.ClaseInexistenteException;
import ar.com.jgrande.servidor.dao.exception.ObjetoInexistenteException;

/**
 * Único punto de acceso a la base de datos. Permite buscar, insertar y
 * eliminar cualquier tipo de objeto.
 * 
 * @author Juan
 *
 */
public interface DAOGenerico {
	
	/**
	 * Busca todos los objetos de una clase. Cualquier modificación
	 * realizada a una instancia devuelta se refleja automáticamente
	 * en la base de datos.
	 * 
	 * @param <T> Clase de los objetos que se quieren buscar.
	 * @param clazz Clase de los objetos que se quieren buscar.
	 * @return Colección con todos los objetos de clase <code>T</code>
	 *         guardados en la base de datos.
	 */
	public <T extends Identificable<?>> Collection<T> buscar(Class<T> clazz) throws ClaseInexistenteException;
	
	/**
	 * Busca todos los objetos de una clase cuyos atributos coinciden con
	 * los atributos no nulos de <code>ejemplo</code>. Cualquier modificación
	 * realizada a una instancia devuelta se refleja automáticamente
	 * en la base de datos.
	 * 
	 * @param <T> Clase de los objetos a buscar.
	 * @param ejemplo Objeto cuyos atributos no nulos determinan el criterio de búsqueda.
	 * @return Colección de objetos que matchean la búsqueda.
	 */
	public <T extends Identificable<?>> Collection<T> buscar(T ejemplo) throws ClaseInexistenteException;
	
	/**
	 * Busca una instancia de un objeto por id. Cualquier modificación
	 * realizada a una instancia devuelta se refleja automáticamente
	 * en la base de datos.
	 * 
	 * @param <T> Clase del objeto buscado.
	 * @param <U> Clase del id del objeto buscado.
	 * @param clazz Clase del id del objeto buscado.
	 * @param id Identificador del objeto buscado.
	 * @return Objeto de clase <code>T</code> cuyo identificador
	 *         coincide con <code>id</code>.
	 * @throws ObjetoInexistenteException si el objeto no existe en la base de datos.
	 */
	public <T, U extends Identificable<T>> U buscar(Class<U> clazz, T id) throws ObjetoInexistenteException, ClaseInexistenteException;
	
	/**
	 * Persiste un objeto en la base de datos.
	 * 
	 * @param obj Objeto a persistir
	 */
	public void guardar(Identificable<?> obj);
	
	/**
	 * Elimina un objeto de la base de datos.
	 * 
	 * @param obj Objeto a eliminar.
	 * @throws ObjetoInexistenteException si el objeto no existe en la base de datos.
	 */
	public void borrar(Identificable<?> obj) throws ObjetoInexistenteException, ClaseInexistenteException;
	
}
