package ar.uba.fi.tecnicas.tphipodromo.persistencia.dao.test;
import java.math.BigDecimal;
import java.util.Date;

import junit.framework.TestCase;
import ar.uba.fi.tecnicas.tphipodromo.modelo.*;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.InscripcionCarreraCerradaException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.ParticipanteNoCalificadoException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.ParticipantesEnDistintasCarrerasException;
import ar.uba.fi.tecnicas.tphipodromo.modelo.excepciones.TransicionInvalidaEstadoParticipanteException;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.*;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.DaoFactory;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.ParticipanteDao;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate.HibernateDaoFactory;
import ar.uba.fi.tecnicas.tphipodromo.persistencia.daos.hibernate.JockeyDaoHibernate;

public class ParticipanteDaoTest extends TestCase{
	public void testParticipante() throws TransicionInvalidaEstadoParticipanteException, ParticipanteNoCalificadoException, ParticipantesEnDistintasCarrerasException, InscripcionCarreraCerradaException{
		Carrera carrera = new Carrera();

		
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
		carrera.setFechaYHora(new Date());
		carrera.setNombre("ssss3");
		carrera.setNumero(132);
		carrera.setEstadoCarrera(EstadoCarrera.INSCRIPCION_PARTICIPANTES);
		carrera.addParticipante(part1);
		
		DaoFactory factory= new HibernateDaoFactory();
		ParticipanteDao dao = factory.getParticipanteDAO();
			//TODO
		dao.guardar(part1);
		}
	}
