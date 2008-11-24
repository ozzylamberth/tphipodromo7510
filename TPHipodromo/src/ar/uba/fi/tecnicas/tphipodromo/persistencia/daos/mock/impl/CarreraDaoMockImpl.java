package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.mock.impl;

import java.math.BigDecimal;
import java.util.Date;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Carrera;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.CarreraDao;

public class CarreraDaoMockImpl extends DAOGenericoMockImpl<Carrera> implements CarreraDao {
	
	public CarreraDaoMockImpl() {
		this.guardar(this.getCarrera1());
		this.guardar(this.getCarrera2());
	}
	
	private Carrera getCarrera1() {
		Carrera carrera = new Carrera();
		carrera.setId(new Long(1));
		carrera.setDistancia(new BigDecimal(100));
		carrera.setFechaYHora(new Date());
		carrera.setNombre("Carrera 1");
		carrera.setNumero(1);
		return carrera;
	}
	
	private Carrera getCarrera2() {
		Carrera carrera = new Carrera();
		carrera.setId(new Long(2));
		carrera.setDistancia(new BigDecimal(400));
		carrera.setFechaYHora(new Date());
		carrera.setNombre("Carrera 2");
		carrera.setNumero(2);
		return carrera;
	}
	
}
