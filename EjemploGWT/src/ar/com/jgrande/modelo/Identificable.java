package ar.com.jgrande.modelo;

/**
 * Interfaz que deben implementar todas las clases con un atributo <code>id</code>.
 * 
 * @author Juan
 *
 * @param <T> Clase del id.
 */
public interface Identificable<T> {
	
	public T getId();

}
