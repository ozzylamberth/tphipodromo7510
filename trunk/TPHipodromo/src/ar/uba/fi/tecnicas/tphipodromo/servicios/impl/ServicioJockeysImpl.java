package ar.uba.fi.tecnicas.tphipodromo.servicios.impl;

import org.apache.commons.collections.Transformer;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Jockey;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.DAOGenerico;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.JockeyDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.mock.impl.JockeyDaoMockImpl;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioJockeys;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.JockeyDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.transformers.JockeyTransformerFromDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.transformers.JockeyTransformerToDTO;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate.HibernateDaoFactory;

@SuppressWarnings("serial")
public class ServicioJockeysImpl extends ServicioIdentificableImpl<Jockey, JockeyDTO> implements ServicioJockeys {
	
	private JockeyDao jockeyDao;
	private JockeyTransformerFromDTO jockeyTransformerFromDTO = new JockeyTransformerFromDTO();
	private JockeyTransformerToDTO jockeyTransformerToDTO = new JockeyTransformerToDTO();

	public DAOGenerico<Jockey> getDao() {
		if(jockeyDao==null){
			jockeyDao = new HibernateDaoFactory().getJockeyDAO();			
		}
		return this.jockeyDao;
	}

	public Transformer getTransformerFromDTO() {
		return this.jockeyTransformerFromDTO;
	}

	public Transformer getTransformerToDTO() {
		return this.jockeyTransformerToDTO;
	}

}
