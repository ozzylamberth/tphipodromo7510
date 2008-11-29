package ar.uba.fi.tecnicas.tphipodromo.servicios.impl;

import java.util.Collection;
import java.util.Date;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Carrera;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.CarreraDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.DAOGenerico;
import ar.uba.fi.tecnicas.tphipodromo.servicios.ServicioCarreras;
import ar.uba.fi.tecnicas.tphipodromo.servicios.dtos.CarreraDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.transformers.CarreraTransformerFromDTO;
import ar.uba.fi.tecnicas.tphipodromo.servicios.transformers.CarreraTransformerToDTO;

@SuppressWarnings("serial")
public class ServicioCarrerasImpl extends ServicioIdentificableImpl<Carrera, CarreraDTO> implements ServicioCarreras {
	
	private CarreraDao carreraDao;
	private CarreraTransformerFromDTO carreraTransformerFromDTO = new CarreraTransformerFromDTO();
	private CarreraTransformerToDTO carreraTransformerToDTO = new CarreraTransformerToDTO();
	
	public ServicioCarrerasImpl() {
		this.carreraDao = (CarreraDao) ServicioSpring.getInstance().getBean("carreraDao");
	}

	public DAOGenerico<Carrera> getDao() {
		return this.carreraDao;
	}

	public Transformer getTransformerFromDTO() {
		return this.carreraTransformerFromDTO;
	}

	public Transformer getTransformerToDTO() {
		return this.carreraTransformerToDTO;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<CarreraDTO> buscarPorFecha(Date fecha) {
		Collection<Carrera> carreras = this.carreraDao.buscarPorFecha(fecha); 
		return CollectionUtils.collect(carreras, this.getTransformerToDTO());
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<CarreraDTO> buscarCarrerasApostablesPorFecha(Date fecha) {
		Collection<Carrera> carreras = this.carreraDao.buscarCarrerasApostablesPorFecha(fecha); 
		return CollectionUtils.collect(carreras, this.getTransformerToDTO());
	}

}
