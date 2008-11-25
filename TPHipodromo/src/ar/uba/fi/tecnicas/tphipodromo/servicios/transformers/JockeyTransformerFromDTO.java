package ar.uba.fi.tecnicas.tphipodromo.servicios.transformers;

import java.math.BigDecimal;

import org.apache.commons.collections.Transformer;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Caballo;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Jockey;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.CaballoDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.JockeyDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate.HibernateDaoFactory;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.JockeyDTO;

public class JockeyTransformerFromDTO implements Transformer {
	private JockeyDao jockeyDao;

	public JockeyDao getDao(){
		return jockeyDao!=null?jockeyDao:new HibernateDaoFactory().getJockeyDAO(); //TODO: usar bien la factory
	}
	public Object transform(Object arg0) {
		JockeyDTO jockeyDTO = (JockeyDTO) arg0;

		Jockey jockey;
		if( jockeyDTO.getId()!=null ) {
			jockey = buscarJockey(jockeyDTO.getId());
		}else{
			jockey = new Jockey();
		}
		jockey.setId(jockeyDTO.getId());
		jockey.setNombre(jockeyDTO.getNombre());
		jockey.setApellido(jockeyDTO.getApellido());
		jockey.setPeso(new BigDecimal(jockeyDTO.getPeso().toString()));
		return jockey;
	}
	
	private Jockey buscarJockey(Long id) {
		try {
			return this.getDao().buscarPorId(id);
		} catch (ObjetoInexistenteException e) {
			return null;
		}
	}
}
