package ar.uba.fi.tecnicas.tphipodromo.servicios.impl;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Identificable;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.DAOGenerico;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;
import ar.uba.fi.tecnicas.tphipodromo.servicios.excepciones.EntidadInexistenteException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public abstract class ServicioIdentificableImpl <T extends Identificable, DTO> extends RemoteServiceServlet {
	
	public abstract DAOGenerico<T> getDao();
	public abstract Transformer getTransformerToDTO();
	public abstract Transformer getTransformerFromDTO();
	
	@SuppressWarnings("unchecked")
	public Collection<DTO> buscarTodos() { 
		Collection<T> objetos = this.getDao().buscarTodos();
		return CollectionUtils.collect(objetos, this.getTransformerToDTO());
	}
	
	@SuppressWarnings("unchecked")
	public DTO buscarPorId(Long id) throws EntidadInexistenteException {
		T objeto;
		try {
			objeto = this.getDao().buscarPorId(id);
		} catch (ObjetoInexistenteException e) {
			throw new EntidadInexistenteException();
		}
		return ((DTO) this.getTransformerToDTO().transform(objeto));
	}
	
	@SuppressWarnings("unchecked")
	public Long guardar(DTO objetoDTO) {
		T objeto = (T) this.getTransformerFromDTO().transform(objetoDTO);
		objeto = this.getDao().guardar(objeto);
		return objeto.getId();
	}
	
	public void borrar(Long id) throws EntidadInexistenteException {
		try {
			T objeto = this.getDao().buscarPorId(id);
			this.getDao().borrar(objeto);
		} catch (ObjetoInexistenteException e) {
			throw new EntidadInexistenteException();
		}
	}
	
}
