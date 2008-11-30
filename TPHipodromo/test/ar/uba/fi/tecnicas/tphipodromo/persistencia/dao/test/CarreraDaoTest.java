package ar.uba.fi.tecnicas.tphipodromo.persistencia.dao.test;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import ar.uba.fi.tecnicas.tphipodromo.modelo.Caballo;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Carrera;
import ar.uba.fi.tecnicas.tphipodromo.modelo.EstadoCarrera;
import ar.uba.fi.tecnicas.tphipodromo.modelo.EstadoParticipante;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Jockey;
import ar.uba.fi.tecnicas.tphipodromo.modelo.Participante;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.InscripcionCarreraCerradaException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.ParticipanteNoCalificadoException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.ParticipantesEnDistintasCarrerasException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.TransicionInvalidaEstadoParticipanteException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.CarreraDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.DaoFactory;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.JockeyDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.MultiplesObjetosException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.excepciones.ObjetoInexistenteException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate.HibernateDaoFactory;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate.HibernateUtil;

public class CarreraDaoTest extends PersistenciaTestCase {
	
	public void testDao() throws TransicionInvalidaEstadoParticipanteException, ParticipanteNoCalificadoException, ParticipantesEnDistintasCarrerasException, InscripcionCarreraCerradaException, ObjetoInexistenteException{
		Carrera carreraLeida;

		Carrera carrera = new Carrera();
		
		CarreraDao dao =  HibernateDaoFactory.getInstance().getCarreraDAO();
		
		Jockey jockey1 = new Jockey();
		jockey1.setApellido("Wasserman");
		jockey1.setNombre("Damian");
		jockey1.setPeso(new BigDecimal(100));
		
		Caballo nuevoCaballo = new Caballo();
		nuevoCaballo.setCaballeriza("caballeriza test");
		nuevoCaballo.setCriador("criador");
		nuevoCaballo.setEdad(4);
		nuevoCaballo.setNombre("pepe");
		nuevoCaballo.setPelaje("peludo");
		nuevoCaballo.setPeso(new BigDecimal(10));
		nuevoCaballo.setPuraSangre(true);
		
		Participante part1 = new Participante(nuevoCaballo, jockey1, carrera);
		part1.setNroParticipante(10);
		part1.setCarrera(carrera);
		part1.setEstado(EstadoParticipante.LARGADA_PENDIENTE);
		
		
		carrera.setDistancia(new BigDecimal(130.0));
		Date fecha = new Date();
		carrera.setFechaYHora(fecha);
		carrera.setNombre("ssss3");
		carrera.setNumero(132);
		carrera.setEstadoCarrera(EstadoCarrera.INSCRIPCION_PARTICIPANTES);
		carrera.addParticipante(part1);
		
		dao.guardar(carrera);
		
		HibernateUtil.getCurrentSession().clear();		
		
		try {
			carreraLeida = dao.buscarPorNombre("ssss3");
			assertEquals(carrera.getNumero(), carreraLeida.getNumero());
			assertEquals(carrera.getDistancia().doubleValue(), carreraLeida.getDistancia().doubleValue());
			assertEquals(carrera.getFechaYHora().getTime()/1000, carreraLeida.getFechaYHora().getTime()/1000); //la base no guarda nanosegundos
			assertEquals(carrera.getNombre(), carreraLeida.getNombre());
			dao.borrar(carreraLeida);
		} catch (ObjetoInexistenteException e) {
			fail(e.getMessage());
		} catch (MultiplesObjetosException e) {
			fail(e.getMessage());
		}
		
	}
	
	
	public void testDaoPorFecha() throws TransicionInvalidaEstadoParticipanteException, ParticipanteNoCalificadoException, ParticipantesEnDistintasCarrerasException, InscripcionCarreraCerradaException, ObjetoInexistenteException{
		Carrera carrera = new Carrera();
		CarreraDao dao = HibernateDaoFactory.getInstance().getCarreraDAO();
		
		Jockey jockey1 = new Jockey();
		jockey1.setApellido("Wasserman");
		jockey1.setNombre("Damian");
		jockey1.setPeso(new BigDecimal(100));
		
		Caballo nuevoCaballo = new Caballo();
		nuevoCaballo.setCaballeriza("caballeriza test");
		nuevoCaballo.setCriador("criador");
		nuevoCaballo.setEdad(4);
		nuevoCaballo.setNombre("pepe");
		nuevoCaballo.setPelaje("peludo");
		nuevoCaballo.setPeso(new BigDecimal(10));
		nuevoCaballo.setPuraSangre(true);
		
		Participante part1 = new Participante(nuevoCaballo, jockey1, carrera);
		part1.setNroParticipante(10);
		part1.setCarrera(carrera);
		part1.setEstado(EstadoParticipante.LARGADA_PENDIENTE);
		
		
		carrera.setDistancia(new BigDecimal(130.0));
		Date fecha = new Date();
		carrera.setFechaYHora(fecha);
		carrera.setNombre("ssss3");
		carrera.setNumero(132);
		carrera.setEstadoCarrera(EstadoCarrera.INSCRIPCION_PARTICIPANTES);
		carrera.addParticipante(part1);
		
		dao.guardar(carrera);
		
		HibernateUtil.getCurrentSession().clear();		
		
		Collection<Carrera> carrerasLeida =  dao.buscarPorFecha(new Date());
		
		for( Carrera c : carrerasLeida){
			assertEquals(carrera.getNombre(), c.getNombre());
			dao.borrar(c);
			
		}
	}
}
