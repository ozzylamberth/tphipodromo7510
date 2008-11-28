package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.mock.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Caballo;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Carrera;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Jockey;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Participante;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.HipodromoException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.CarreraDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.MultiplesObjetosException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;

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
		try {
			carrera.setParticipantes(this.getParticipantes(carrera));
		} catch (HipodromoException e) {
			// nada para hacer
		}
		return carrera;
	}
	
	private Carrera getCarrera2() {
		Carrera carrera = new Carrera();
		carrera.setId(new Long(2));
		carrera.setDistancia(new BigDecimal(400));
		carrera.setFechaYHora(new Date());
		carrera.setNombre("Carrera 2");
		carrera.setNumero(2);
		try {
			carrera.setParticipantes(this.getParticipantes(carrera));
		} catch (HipodromoException e) {
			// nada para hacer
		}
		return carrera;
	}
	
	private List<Participante> getParticipantes(Carrera carrera) {
		List<Participante> participantes = new ArrayList<Participante>();
		Caballo caballo = new CaballoDaoMockImpl().buscarTodos().iterator().next();
		Jockey jockey = new JockeyDaoMockImpl().buscarTodos().iterator().next();
		participantes.add(new Participante(caballo, jockey, carrera));
		return participantes;
	}
	
	public Carrera buscarPorNombre(String nombre) throws ObjetoInexistenteException, MultiplesObjetosException {
		return null;
	}
	
}
