package ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.mock.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Caballo;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Carrera;
import ar.uba.fi.tecnicas.tphipodromo.modelo.EstadoCarrera;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Jockey;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Participante;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.HipodromoException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.CarreraDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.MultiplesObjetosException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;

public class CarreraDaoMockImpl extends DAOGenericoMockImpl<Carrera> implements CarreraDao {
	
	private static Map<Long, Carrera> carreras = null;
	private static Secuencia secuencia = new Secuencia();
	
	public CarreraDaoMockImpl() {
		if (carreras == null) { // inicializo solo una vez
			carreras = new HashMap<Long, Carrera>() ;
			this.guardar(this.getCarrera1());
			this.guardar(this.getCarrera2());
		}
	}
	
	@Override
	protected Map<Long, Carrera> getDBMap() {
		return carreras;
	}
	
	@Override
	protected Secuencia getSecuencia() {
		return secuencia;
	}
	
	private Carrera getCarrera1() {
		Carrera carrera = new Carrera();
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
		Collection<Carrera> result = new ArrayList<Carrera>();
		Iterator<Carrera> it = buscarTodos().iterator();
		while (it.hasNext()) {
			Carrera carrera = (Carrera) it.next();
			if (carrera.getNombre().equals(nombre)) {
				result.add(carrera);
			}
		}
		if (result.size() == 0) {
			throw new ObjetoInexistenteException();
		} else if (result.size() == 1) {
			return result.iterator().next();
		} else {
			throw new MultiplesObjetosException();
		}
	}

	@Override
	public Collection<Carrera> buscarPorFecha(Date fecha) {
		Collection<Carrera> result = new ArrayList<Carrera>();
		Iterator<Carrera> it = buscarTodos().iterator();
		while (it.hasNext()) {
			Carrera carrera = (Carrera) it.next();
			Date diaCarrera = DateUtils.truncate(carrera.getFechaYHora(), Calendar.DATE);
			Date dia = DateUtils.truncate(fecha, Calendar.DATE);
			if (dia.compareTo(diaCarrera) == 0) {
				result.add(carrera);
			}
		}
		return result;
	}

	@Override
	public Collection<Carrera> buscarCarrerasApostablesPorFecha(Date fecha) {
		Collection<Carrera> result = new ArrayList<Carrera>();
		Iterator<Carrera> it = buscarTodos().iterator();
		while (it.hasNext()) {
			Carrera carrera = (Carrera) it.next();
			Date diaCarrera = DateUtils.truncate(carrera.getFechaYHora(), Calendar.DATE);
			Date dia = DateUtils.truncate(fecha, Calendar.DATE);
			if ((dia.compareTo(diaCarrera) == 0) && (EstadoCarrera.ABIERTA_A_APUESTAS.equals(carrera.getEstadoCarrera()))) {
				result.add(carrera);
			}
		}
		return result;
	}

	@Override
	public Collection<Carrera> buscarCarrerasEnInscripcion() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Collection<Carrera> buscarCarrerasApostables() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Collection<Carrera> buscarCarrerasEnCurso() {
		// TODO Auto-generated method stub
		return null;
	}
}
