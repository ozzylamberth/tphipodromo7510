package ar.uba.fi.tecnicas.tphipodromo.servicios.impl;
import org.apache.commons.collections.Transformer;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Caballo;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.CaballoDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.DAOGenerico;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.mock.impl.CaballoDaoMockImpl;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioCaballos;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CaballoDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.transformers.CaballoTransformerFromDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.transformers.CaballoTrasnformerToDTO;

@SuppressWarnings("serial")
public class ServicioCaballosImpl extends ServicioIdentificableImpl<Caballo, CaballoDTO> implements ServicioCaballos {
	
	private CaballoDao caballoDao = new CaballoDaoMockImpl();
	private CaballoTrasnformerToDTO caballoTrasnformerToDTO = new CaballoTrasnformerToDTO();
	private CaballoTransformerFromDTO caballoTransformerFromDTO = new CaballoTransformerFromDTO();
	
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
