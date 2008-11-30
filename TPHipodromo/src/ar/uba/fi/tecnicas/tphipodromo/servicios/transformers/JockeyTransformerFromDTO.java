package ar.uba.fi.tecnicas.tphipodromo.servicios.transformers;

import java.math.BigDecimal;

import org.apache.commons.collections.Transformer;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Jockey;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.JockeyDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate.HibernateDaoFactory;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.JockeyDTO;

public class JockeyTransformerFromDTO implements Transformer {
	private JockeyDao jockeyDao;
	
	public JockeyTransformerFromDTO() {
		this.jockeyDao = HibernateDaoFactory.getInstance().getJockeyDAO();
	}

	public JockeyDao getDao(){
		return jockeyDao;
	}
	public Object transform(Object arg0) {
		JockeyDTO jockeyDTO = (JockeyDTO) arg0;
		Jockey jockey = this.getJockey(jockeyDTO);
		jockey.setNombre(jockeyDTO.getNombre());
		jockey.setApellido(jockeyDTO.getApellido());
		jockey.setPeso(new BigDecimal(jockeyDTO.getPeso().toString()));
		return jockey;
	}
	
	private Jockey getJockey(JockeyDTO jockeyDTO) {
		try {
			return this.getDao().buscarPorId(jockeyDTO.getId());
		} catch (ObjetoInexistenteException e) {
			return new Jockey();
		}
	}
}
