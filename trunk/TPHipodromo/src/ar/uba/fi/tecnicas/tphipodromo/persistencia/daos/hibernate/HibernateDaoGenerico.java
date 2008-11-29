package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Identificable;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.DAOGenerico;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;

public class HibernateDaoGenerico<T extends Identificable>
implements DAOGenerico<T>{
	
	private Class<T> clasePersistente;
	private Session session;
	
	public HibernateDaoGenerico(){
		this.clasePersistente = (Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public void setSession(Session s){
		session = s;
	}
	
	public Session getSession(){
		if (session==null)
			session = HibernateUtil.currentSession();
		
		return session;
	}
	
	public Class<T> getClasePersistente(){
		return this.clasePersistente;
	}

	public void clear() {
		getSession().clear();		
	}

	public void flush() {
		getSession().flush();
	}

	public T guardar(T obj) {
		Transaction t = getSession().beginTransaction();
		getSession().saveOrUpdate(obj);
		t.commit();
		return obj;
	}

	public void refresh(T entidad){
		Transaction t = getSession().beginTransaction();
		getSession().refresh(entidad);
		t.commit();		
	}
	
	public void borrar(T entidad) {
		Transaction t = getSession().beginTransaction();
		getSession().delete(entidad);
		t.commit();
	}

	@SuppressWarnings("unchecked")
	public List<T> obtenerPorEjemplo(T ejemploParametro, String... atributoExcluido) {
		Criteria criterio = getSession().createCriteria(getClasePersistente());
		Example ejemplo = Example.create(ejemploParametro).ignoreCase().enableLike(MatchMode.START);
		
		for(String excluido: atributoExcluido){
			ejemplo.excludeProperty(excluido);
		}
		criterio.add(ejemplo);
		return criterio.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> obtenerPorEjemploExacto(T ejemploParametro, String... atributoExcluido) {
		Criteria criterio = getSession().createCriteria(getClasePersistente());
		Example ejemplo = Example.create(ejemploParametro).ignoreCase();
		
		for(String excluido: atributoExcluido){
			ejemplo.excludeProperty(excluido);
		}
		criterio.add(ejemplo);
		return criterio.list();
	}

	@SuppressWarnings("unchecked")
	public T buscarPorId(Long id) throws ObjetoInexistenteException {
		T objeto = (T) getSession().get(getClasePersistente(), id);
		if (objeto == null) {
			throw new ObjetoInexistenteException();
		}
		return objeto;
	}

	public Collection<T> buscarTodos() {
		return obtenerPorCriterio();
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> obtenerPorCriterio(Criterion... criterios){
		Criteria criterio = getSession().createCriteria(getClasePersistente());
		for (Criterion c:criterios){
			criterio.add(c);
		}
		
		return criterio.list();
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> obtenerPorEjemploOrdenado(Order order, Criterion... criterion) {
		Criteria criteria = getSession().createCriteria(getClasePersistente());
		
		for (Criterion c : criterion)
			criteria.add(c);
		
		if(order!=null)
			criteria.addOrder(order);
		
		return criteria.list();
	}
	
	public List<T> obtenerTodosOrdenado(String clave, boolean ascendente) {
		return obtenerPorEjemploOrdenado(ascendente ? Order.asc(clave) : Order.desc(clave));
	}
	
	@SuppressWarnings("unchecked")
	public List<T> obtenerPorEjemploOrdenado(T entidad,String clave, String... excluirProps) {
		Example ejemplo =  Example.create(entidad);
		
		for (String prop : excluirProps)
			ejemplo.excludeProperty(prop);

		ejemplo.excludeZeroes();
		ejemplo.ignoreCase();
		ejemplo.enableLike(MatchMode.ANYWHERE);
		
		return obtenerPorEjemploOrdenado(Order.asc(clave), ejemplo);
	}
}

