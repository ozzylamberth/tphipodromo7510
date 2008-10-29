package ar.com.jgrande.servidor.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import ar.com.jgrande.modelo.Identificable;
import ar.com.jgrande.servidor.dao.DAOGenerico;
import ar.com.jgrande.servidor.dao.exception.ClaseInexistenteException;
import ar.com.jgrande.servidor.dao.exception.ObjetoInexistenteException;

public class DAOGenericoMock implements DAOGenerico {
	
	Map <Class<?>, Map<Object, Object>> bd = new HashMap<Class<?>, Map<Object, Object>>();

	public void borrar(Identificable<?> obj) throws ObjetoInexistenteException, ClaseInexistenteException {
		Map <Object, Object> tabla = bd.get(obj.getClass());
		
		if (tabla == null) {
			throw new ClaseInexistenteException();
		}
		
		if(!tabla.containsKey(obj.getId())) {
			throw new ObjetoInexistenteException();
		}
		
		tabla.remove(obj.getId());
	}

	@SuppressWarnings("unchecked")
	public <T extends Identificable<?>> Collection<T> buscar(Class<T> clazz) throws ClaseInexistenteException {
		Map<Object, Object> tabla = bd.get(clazz);
		
		if (tabla == null) {
			throw new ClaseInexistenteException();
		}
		
		return (Collection<T>) tabla.values();
	}

	@SuppressWarnings("unchecked")
	public <T extends Identificable<?>> Collection<T> buscar(T ejemplo) throws ClaseInexistenteException {
		Map<Object, Object> tabla = bd.get(ejemplo.getClass());
		Collection<T> result = new LinkedList<T>();
		
		if (tabla == null) {
			throw new ClaseInexistenteException();
		}
		
		for (Object obj : tabla.values()) {
			boolean equals = true;
			
			for (Method m : ejemplo.getClass().getMethods()) {
				if ((m.getName().startsWith("get") || m.getName().startsWith("is")) && m.getParameterTypes().length == 0) {
					try {
						equals = equals && (m.invoke(obj, (Object[]) null).equals(m.invoke(ejemplo, (Object[]) null)) || m.invoke(ejemplo, (Object[]) null) == null);
					} catch (IllegalArgumentException e) {
						equals = false;
					} catch (IllegalAccessException e) {
						equals = false;
					} catch (InvocationTargetException e) {
						equals = false;
					}
				}
			}
			
			if (equals) {
				result.add((T) obj);
			}
		}
		
		return result;
	}

	@SuppressWarnings("unchecked")
	public <T, U extends Identificable<T>> U buscar(Class<U> clazz, T id) throws ObjetoInexistenteException, ClaseInexistenteException {
		Map<Object, Object> tabla = bd.get(clazz);
		
		if (tabla == null) {
			throw new ClaseInexistenteException();
		}
		
		if (!tabla.containsKey(id)) {
			throw new ObjetoInexistenteException();
		}
		
		return (U) tabla.get(id);
	}

	public void guardar(Identificable< ? > obj) {
		Map<Object, Object> tabla = bd.get(obj.getClass());
		
		if (tabla == null) {
			tabla = new HashMap<Object, Object>();
			bd.put(obj.getClass(), tabla);
		}
		
		tabla.put(obj.getId(), obj);
	}

}
