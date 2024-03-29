package ar.uba.fi.tecnicas.tphipodromo.servicios.impl;
import org.apache.commons.collections.Transformer;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Caballo;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.CaballoDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.DAOGenerico;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate.HibernateDaoFactory;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioCaballos;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.transformers.CaballoTransformerFromDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.transformers.CaballoTransformerToDTO;

@SuppressWarnings("serial")
public class ServicioCaballosImpl extends ServicioIdentificableImpl<Caballo, CaballoDTO> implements ServicioCaballos {
	
	private CaballoDao caballoDao;
	private CaballoTransformerToDTO caballoTrasnformerToDTO = new CaballoTransformerToDTO();
	private CaballoTransformerFromDTO caballoTransformerFromDTO = new CaballoTransformerFromDTO();
	
	public ServicioCaballosImpl() {
		this.caballoDao = HibernateDaoFactory.getInstance().getCaballoDAO();
	}
	
	public DAOGenerico<Caballo> getDao() {
		return this.caballoDao;
	}
	
	public Transformer getTransformerFromDTO() {
		return this.caballoTransformerFromDTO;
	}
	
	public Transformer getTransformerToDTO() {
		return this.caballoTrasnformerToDTO;
	}

}
