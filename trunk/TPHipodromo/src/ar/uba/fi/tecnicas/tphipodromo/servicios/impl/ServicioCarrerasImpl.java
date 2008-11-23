package ar.uba.fi.tecnicas.tphipodromo.servicios.impl;

import org.apache.commons.collections.Transformer;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Carrera;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.CarreraDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.DAOGenerico;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.mock.impl.CarreraDaoMockImpl;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioCarreras;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.transformers.CarreraTransformerFromDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.transformers.CarreraTransformerToDTO;

@SuppressWarnings("serial")
public class ServicioCarrerasImpl extends ServicioIdentificableImpl<Carrera, CarreraDTO> implements ServicioCarreras {
	
	private CarreraDao carreraDao = new CarreraDaoMockImpl();
	private CarreraTransformerFromDTO carreraTransformerFromDTO = new CarreraTransformerFromDTO();
	private CarreraTransformerToDTO carreraTransformerToDTO = new CarreraTransformerToDTO();

	public DAOGenerico<Carrera> getDao() {
		return this.carreraDao;
	}

	public Transformer getTransformerFromDTO() {
		return this.carreraTransformerFromDTO;
	}

	public Transformer getTransformerToDTO() {
		return this.carreraTransformerToDTO;
	}

}
